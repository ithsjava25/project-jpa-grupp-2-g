package frontend.controller;

import backend.entities.Restaurant;
import frontend.model.AppHandler;
import frontend.model.RestaurantHandler;
import frontend.view.RestaurantCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.List;

public class MainController {
    private final AppHandler model = new AppHandler();

    @FXML
    private Label appName;
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private FlowPane restaurantContainer;
    @FXML
    private VBox mainArea;

    @FXML
    private void initialize() {
        if (appName != null) {
            appName.setText(model.getAppName());
        }

        List<Restaurant> allRestaurants = RestaurantHandler.getResturantList("");
        displayRestaurants(allRestaurants);
    }


    /**
     * Handles restaurant search input.
     *
     * Searches for restaurants based on the user's input
     * and updates the displayed list.
     */
    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = RestaurantHandler.getResturantList(restaurant);
        displayRestaurants(restaurantList);
    }


    /**
     * Displays a list of restaurants in the UI.
     *
     * Clears the current view and adds a card for each restaurant.
     */
    private void displayRestaurants(List<Restaurant> restaurants){
        restaurantContainer.getChildren().clear();
        for(Restaurant r : restaurants){
            RestaurantCard restaurantCard = new RestaurantCard(r, mainArea);
            restaurantContainer.getChildren().add(restaurantCard);
        }
    }

}
