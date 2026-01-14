package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class HelloController {

    private final HelloModel model = new HelloModel();

    @FXML
    private BorderPane rootPane;

    @FXML
    private ImageView logoImage;

    @FXML
    private StackPane headerContainer;

    @FXML
    private BorderPane categoryContainer;

    @FXML
    private TextField searchRestaurantField;

    @FXML
    private GridPane grid;

    private CategoryManager categoryManager;
    private HeaderManager headerManager;

    @FXML
    private void initialize() {
        // header
        headerManager = new HeaderManager(this);
        headerManager.createHeader();

        // kategorigrid
        categoryManager = new CategoryManager(this);
        categoryManager.createCategoryGrid();
    }

    @FXML
    private void handleRestaurantSearch() {
        // filtrera restauranger
    }

    public BorderPane getRootPane() {
        return rootPane;
    }

    public BorderPane getCategoryContainer() {
        return categoryContainer;
    }

    public GridPane getGrid() {
        return grid;
    }

    // getter för headerContainer
    public StackPane getHeaderContainer() {
        return headerContainer;
    }
}