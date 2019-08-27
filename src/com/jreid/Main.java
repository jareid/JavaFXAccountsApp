package com.jreid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));

        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Accounts");
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller.stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
