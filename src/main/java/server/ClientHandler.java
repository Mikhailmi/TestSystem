package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean isAuthorized = false;
    private static Statement stmt;

    public ClientHandler(MyServer server, Socket socket) {

        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {

            });
            executorService.shutdown();

            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    authentication();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();

                } finally {
                    closeConnection();
                }
            }).start();

        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public synchronized void authentication() throws IOException, SQLException {
        while (true) {


            if () { // если клиент перешел в окно авторизации

                if () { // если логин и пароль заполнены

                    if () { // если пользователь авторизовался
                        server.subscribe(this);
                        isAuthorized = true;
                        return;
                    } else { // Если данная учетная запись уже используется
                        System.out.println("Учетная запись уже используется");
                    }
                } else {
                    System.out.println("Неверные логин и пароль");
                }



            }
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        System.out.println("Соединение закрыто");

        try {
            in.close();
        } catch (IOException ex) {
            //ignore  ex.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException ex) {
            // ignore  ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
            // ignore ex.printStackTrace();
        }
    }


    }

