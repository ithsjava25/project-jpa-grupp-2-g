package frontend.controller;

import backend.entities.Restaurant;
import frontend.CategoryBox;
import frontend.model.RestaurantHandler;
import frontend.view.RestaurantCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.scene.layout.*;

import java.util.List;


public class MainController {
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private FlowPane restaurantContainer;
    @FXML
    private VBox mainArea;
    @FXML
    private BorderPane categoryContainer;

    @FXML
    private void initialize() {
        List<Restaurant> allRestaurants = RestaurantHandler.getResturantList("");
        CategoryBox.setupCategoryButtons(categoryContainer, this);
        displayRestaurants(allRestaurants);
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = RestaurantHandler.getResturantList(restaurant);
        displayRestaurants(restaurantList);
    }

    private void displayRestaurants(List<Restaurant> restaurants){
        restaurantContainer.getChildren().clear();
        for(Restaurant r : restaurants){
            RestaurantCard restaurantCard = new RestaurantCard(r, mainArea);
            restaurantContainer.getChildren().add(restaurantCard);
        }
    }

    public void filterByCategory(String categoryName) {
        List<Restaurant> filteredList;

        if (categoryName.equalsIgnoreCase("All")) {
            filteredList = RestaurantHandler.getResturantList("");
        } else {
            filteredList = RestaurantHandler.getResturantList(categoryName);
        }

        displayRestaurants(filteredList);
    }


}
