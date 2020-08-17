import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends JFrame {

    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;

    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) throws IOException {
        try {
            openConnection();
            closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Thread threadIn = new Thread(()-> {
                try {
                    while (true) {
                        if (!socket.isConnected()) {
                            System.out.println("Нет связи с сервером");
                            break;
                        }

                        String strFromServer = in.readUTF();

                        if (strFromServer.equalsIgnoreCase("/end")) {
                            System.out.println("Сервер отключился");
                            break;
                        }

                        System.out.println("Сервер сказал: " + strFromServer);
                    }
                } catch (Exception e) {
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
            } catch (Exception e){
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


    public static void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
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
