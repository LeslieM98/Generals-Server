package me.leslie.generals.server;

import com.github.simplenet.Server;

public class App {

    public static void main(String[] args) {
        Server server = new Server();
        server.bind("localhost", 8080);
        System.out.println("Server launched on localhost:8080");
        server.onConnect(x -> System.out.println("Client connected"));
    }
}
