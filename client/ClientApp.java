package client;

import client.service.ClientService;
import client.service.Window;

public class ClientApp {

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
                clientService.start();

    }
}
