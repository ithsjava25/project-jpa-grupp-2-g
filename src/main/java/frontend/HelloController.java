package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HelloController {
    private final HelloModel model = new HelloModel();
    private CategoryManager categoryManager;

    @FXML
    private Label appName;
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private BorderPane categoryContainer;
    @FXML
    private GridPane grid;

    @FXML
    private void initialize() {
        appName.setText(model.getAppName());
        categoryManager = new CategoryManager(this);
        categoryManager.createCategoryGrid();
    }

    @FXML
    public void handleRestaurantSearch() {}

    public GridPane getGrid() {
        return grid;
    }

    public BorderPane getCategoryContainer() {
        return categoryContainer;
    }
}