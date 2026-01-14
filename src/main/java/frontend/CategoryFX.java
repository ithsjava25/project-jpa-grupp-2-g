package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CategoryFX.class.getResource("category-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(getClass().getResource("category.css").toExternalForm());
        stage.setTitle("Restaurangbokningar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}