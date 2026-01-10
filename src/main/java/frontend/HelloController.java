package frontend;

import backend.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;

import java.util.List;

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
    private FlowPane restaurantContainer;

    @FXML
    private void initialize() {
        // Set app name
        appName.setText(model.getAppName());

        // Initialize CategoryManager and create category grid
        categoryManager = new CategoryManager(this);
        categoryManager.createCategoryGrid();

        // Display all restaurants initially
        List<Restaurant> allRestaurants = model.getResturantList("");
        displayRestaurants(allRestaurants);
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event) {
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        displayRestaurants(restaurantList);
    }

    private void displayRestaurants(List<Restaurant> restaurants) {
        restaurantContainer.getChildren().clear();
        for (Restaurant r : restaurants) {
            RestaurantCard restaurantCard = new RestaurantCard(r);
            restaurantContainer.getChildren().add(restaurantCard);
        }
    }

    public GridPane getGrid() {
        return grid;
    }

    public BorderPane getCategoryContainer() {
        return categoryContainer;
    }
}