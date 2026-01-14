package frontend.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SceneHandler {

    /**
     * Replaces the content of a container with a new FXML-loaded layout.
     * <p>
     * This method loads the specified FXML file from the "/frontend/" directory
     * and injects it into the {@code currentPane}, switching the visible UI view.
     * </p>
     *
     * @param currentPane the target container to be updated.
     * @param fxml        the name of the FXML file (including extension) to load.
     */
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
