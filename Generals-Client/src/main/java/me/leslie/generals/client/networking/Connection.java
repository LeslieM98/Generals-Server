package me.leslie.generals.client.networking;

import com.github.simplenet.Client;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

@Getter
@EqualsAndHashCode
@ToString
public class Connection {
    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            connection = new Connection();
        }
        return connection;
    }

    private final Client client;

    private Connection() {
        client = new Client();
    }

    public void connect(String ip, int port, Runnable onTimeOut) {
        client.connect(ip, port, 10, TimeUnit.SECONDS, onTimeOut);
    }

    public void disconnect() {
        client.close();
    }


}
