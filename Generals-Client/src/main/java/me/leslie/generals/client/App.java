package me.leslie.generals.client;

import com.github.simplenet.Client;

public class App {
    public static void main(String[] args) {
        Client client = new Client();
        client.connect("localhost", 8080);
    }
}
