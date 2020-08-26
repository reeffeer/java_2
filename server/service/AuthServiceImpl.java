package server.service;

import server.intrface.AuthService;

import java.util.LinkedList;
import java.util.List;

public class AuthServiceImpl implements AuthService {

    private List<UserEntry> usersList;

    public AuthServiceImpl() {  // Конструктор класса, в котором создаются и хранятся конкретные пользователи
        this.usersList = new LinkedList<>();
       /* this.usersList.add(new UserEntry("login1", "pass1", "nick1"));
        this.usersList.add(new UserEntry("login2", "pass2", "nick2"));
        this.usersList.add(new UserEntry("login3", "pass3", "nick3"));*/
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getNick(String login, String password) {
        for (UserEntry u : usersList) {

            if (u.login.equals(login) && u.password.equals(password)) {
                return u.nick;
            }

        }
        return null;
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");

    }

    private static class UserEntry { // класс создания записи пользователя
        private String login;
        private String password;
        private String nick;

        public UserEntry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }
}
