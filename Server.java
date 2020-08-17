import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

        public static void main(String[] args) {
            try {
                startServer();
                closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static void startServer() throws IOException {
        socket = null;
        serverSocket = new ServerSocket(8189);
        System.out.println("Сервер запущен, ожидаем подключения...");
        socket = serverSocket.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Thread threadIn = new Thread(() -> {
            System.out.println("Клиент подключился");
            try {
                while (true) {
                    if (!socket.isConnected()) {
                        System.out.println("Нет связи с клиентом");
                        break;
                    }

                    String str = in.readUTF();

                    if (str.equalsIgnoreCase("/end")) {
                        System.out.println("Клиент отключился");
                        break;
                    }

                    System.out.println("Клиент сказал:" + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread threadOut = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            try {
                while (true) {
                    if (!socket.isConnected()) {
                        break;
                    }

                    String str = sc.nextLine();
                    out.writeUTF(str);

                    if (str.equalsIgnoreCase("/end")) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sc.close();
        });
        threadIn.start();
        threadOut.start();
        try {
            threadIn.join();
            threadOut.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection() throws IOException {
        in.close();
        out.close();

        if (!socket.isClosed()) {
            socket.close();
        }
        if (!serverSocket.isClosed()) {
            serverSocket.close();
        }
    }

}
