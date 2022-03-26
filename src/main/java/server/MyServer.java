package server;

import constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Логика сервера

 */

public class MyServer {

    /**
     * Активные клиенты
     */

    public List<ClientHandler> clients;

    /**
     * Сервис аутентификации
     */
    private AuthService authService; // создает Юрий

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService(); // инициализация авторизации
            authService.start(); // запуск авторизации
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");

                new ClientHandler(this, socket);
            }

        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop(); // прекращение авторизации
            }
        }
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }




}
