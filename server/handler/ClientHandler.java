package server.handler;

import server.intrface.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//ответственнен за рассылку сообщений и за соединение каждого клиента к серверу по сокету
//обработчик клиентов, создается для каждого свой объект этого класса
public class ClientHandler {

    private Server server;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String nick;

    private Thread thread;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.nick = "";
            new Thread(() -> { //Создаем поток чтения сообщений от клиента
                try {
                    if (authentication()) {
                        thread.start();
                    }
                    readMsg();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }


    private boolean authentication() throws IOException {
        while (true) {
            String str = inputStream.readUTF();
            if (str.startsWith("/auth")) {
                String[] dataArray = str.split("\\s");
                String nick = server.getAuthService().getNick(dataArray[1], dataArray[2]);
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                       sendMsg("/authOk" + nick);
                       this.nick = nick;
                       server.broadcastMsg(this.nick + " joined to chat");
                       server.subscribe(this);
                        return false;
                    } else {
                        sendMsg("You're already logged in");
                    }
                } else {
                    sendMsg("Incorrect password or login");
                }
            }
        }
    }

    public void readMsg() {
        try {
            while (true) {
                String clientStr = inputStream.readUTF();
                System.out.println("From " + nick + ": " + clientStr);
                if (clientStr.startsWith("/")) {
                    if (clientStr.equals("/exit")) {
                       // return;
                        break;
                    }
                    if (clientStr.startsWith("/w")) {
                        String[] tokens = clientStr.split("\\s");
                        String nickName = tokens[1];
                        String msg = clientStr.substring(4 + nickName.length());
                        server.sendPrivateMsg(this, nickName, msg);
                    }
                    continue;
                }
                server.broadcastMsg(this.nick + ": " + clientStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(String msg) {
        try {
            outputStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMsg(this.nick + " left the chat");

        try {
           inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
