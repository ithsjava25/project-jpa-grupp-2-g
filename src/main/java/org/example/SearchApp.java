package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchApp extends Application {

    @Override
    public void start(Stage stage) {
        TextField searchField = new TextField();
        searchField.setPromptText("Sök restaurang...");

        VBox root = new VBox(10, searchField);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Sök");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
