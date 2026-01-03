import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainView {

    private BorderPane root;
    private GridPane grid;

    public MainView() {
        root = new BorderPane();
        createCategoryGrid();
    }

    private void createCategoryGrid() {
        grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(20);
        grid.setVgap(20);

        String[] categories = {
                "Pizza",
                "Pasta",
                "Burgare",
                "Vegetariskt",
                "Indiskt",
                "Sushi"
        };

        for (int i = 0; i < categories.length; i++) {
            grid.add(createCategoryBox(categories[i]),
                    i % 3, i / 3);
        }

        root.setCenter(grid);
    }

    private VBox createCategoryBox(String category) {
        Label label = new Label(category);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        VBox box = new VBox(label);
        box.setPadding(new Insets(20));
        box.setPrefSize(180, 120);
        box.setStyle("""
                -fx-border-color: black;
                -fx-border-radius: 8;
                -fx-background-radius: 8;
                """);

        return box;
    }

    public Parent getView() {
        return root;
    }
}