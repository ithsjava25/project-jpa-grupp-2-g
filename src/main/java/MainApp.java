import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();

        Scene scene = new Scene(mainView.getView(), 900, 600);
        stage.setTitle("Restaurangbokningar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}