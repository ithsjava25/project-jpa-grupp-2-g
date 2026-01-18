package frontend.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SceneHandler {

    /**
     * Loads an FXML layout into the specified pane and returns the loader.
     * @param currentPane The container to update.
     * @param fxml        The FXML filename (within /frontend/).
     * @return            The {@link FXMLLoader} instance for controller access,
     */
    public static FXMLLoader switchScene(Pane currentPane, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneHandler.class.getResource("/frontend/" + fxml));
            Parent nextPane = loader.load();

            currentPane.getChildren().clear();
            currentPane.getChildren().setAll(nextPane);

            return loader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
