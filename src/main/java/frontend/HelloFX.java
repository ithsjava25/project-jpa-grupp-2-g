package frontend;

import backend.ConnectionProvider;
import backend.DataSeeder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Restaurangbokningar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ConnectionProvider.initialize();
        DataSeeder.populateDatabase();
        launch();
    }

}
