package frontend;

import backend.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
        if (appName != null) {
            appName.setText(model.getAppName());
        }

        categoryManager = new CategoryManager(this);
        categoryManager.createCategoryGrid();
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event) {
    }

    public GridPane getGrid() {
        return grid;
    }

    public BorderPane getCategoryContainer() {
        return categoryContainer;
    }
}