package frontend.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SceneHandler {

    public static void switchScene(Pane currentPane, String fxml){
        try {
            Parent nextPane = FXMLLoader.load(Objects.requireNonNull(SceneHandler.class.getResource("/frontend/" + fxml)));
            currentPane.getChildren().removeAll();
            currentPane.getChildren().setAll(nextPane);
        } catch (IOException e) {
            System.err.println("Something went wrong when trying to switch scene.");
            e.printStackTrace();
        }
    }

}
