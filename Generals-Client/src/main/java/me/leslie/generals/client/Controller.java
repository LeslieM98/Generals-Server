package me.leslie.generals.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.leslie.generals.client.networking.Connection;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    @FXML
    private Button connect;

    @FXML
    void initialize() {
        assert ip != null : "fx:id=\"ip\" was not injected: check your FXML file 'Connect.fxml'.";
        assert port != null : "fx:id=\"port\" was not injected: check your FXML file 'Connect.fxml'.";
        assert connect != null : "fx:id=\"connect\" was not injected: check your FXML file 'Connect.fxml'.";

        Connection.getInstance().getClient().onConnect(() -> Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Connected successfully");
            alert.setHeaderText(String.format("Connected to Server: %s:%s", ip.getText(), port.getText()));
            alert.showAndWait();
        }));

        connect.setOnAction(x -> {
            String ipAddr = ip.getText();
            String portText = port.getText();
            try {
                int portNum = Integer.parseInt(portText);
                Connection.getInstance().connect(
                        ipAddr,
                        portNum,
                        () -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Connection Error");
                            alert.setContentText(String.format("Could not connect to Server (Connection timed out): %s:%d", ipAddr, portNum));
                            alert.showAndWait();
                        });
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Connection Error");
                alert.setContentText("Invalid port");
                alert.showAndWait();
            }
        });
    }
}
