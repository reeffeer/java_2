package server.service;

import server.handler.ClientHandler;
import server.intrface.AuthService;
import server.intrface.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

//Работа сервера
public class ServerImpl implements Server {

    private List<ClientHandler> clients = new LinkedList<>();
    private AuthService authService = new AuthServiceImpl();; //по этому интерфейсу будем получать и останавливать клиента (управлять доступом клиента к чату)

    public ServerImpl() {
        try {
            // создаем серверный сокет на определенном порту
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is ready");
            //authService


            while (true) {
                // ждём подключений клиентов
                System.out.println("Server is waiting for connection");
                Socket clientSocket = serverSocket.accept();
                // запускаем сервис аутентификации
                authService.start();
                // создаём обработчик клиента, который подключился к серверу
                ClientHandler client = new ClientHandler(this, clientSocket);
                clients.add(client);
                System.out.println("Client is connected");
                new ClientHandler(this, clientSocket);
             /*   // каждое подключение клиента обрабатываем в новом потоке
                new Thread((Runnable) client).start();*/
            }
        } catch (IOException e) {
            System.out.println("Server problem");
            e.printStackTrace();
        } /*finally {
            if (authService != null) {
                authService.stop();
            }
        }*/
    }

    @Override
    // Метод проверки дублирования клиента
    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler c : clients) {
            if (c.getNick() != null && c.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    @Override
    // Метод рассылки сообщений списку
    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }
    }


    @Override
    // Метод добавления клиента в список рассылки сообщений
    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    @Override
    // Метод удаления клиента из рассылки
    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    @Override
    // Метод возвращающий список авторизованных пользователей
    public AuthService getAuthService() {
        return authService;
    }

    @Override
    // Метод отсылки приватного сообщения
    public void sendPrivateMsg(ClientHandler fromClient, String toClient, String msg) {
        for (ClientHandler с : clients) {
            if (с.getNick().equals(toClient)) {
                с.sendMsg("От " + fromClient.getNick() + ": " + msg);
                fromClient.sendMsg("Кому " + toClient + ": " + msg);
                return;
            } else {
                fromClient.sendMsg(toClient + " не подключен к чату");
            }
        }
    }

  /*  //отправка сообщения
    @Override
    public synchronized void sendMsgToClient(ClientHandler from, String toNick, String msg) {
        for (ClientHandler c : clients) {
            if (c.getNick().equals(toNick)) {
                c.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("client " + toNick + ": " + msg);
                return;
            }
        }
    }*/

    @Override
    public synchronized void broadcastClientList() {
        StringBuilder builder = new StringBuilder("/clients");
        for (ClientHandler c : clients) {
            builder.append(c.getNick() + " ");
        }
        broadcastMsg(builder.toString());
    }
}
