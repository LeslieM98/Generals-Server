package me.leslie.generals.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Connect.fxml"));
        loader.setController(new Controller());
        Parent root = loader.load();
        primaryStage.setTitle("Generals");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void run(String[] args) {
        launch(args);
    }
}
