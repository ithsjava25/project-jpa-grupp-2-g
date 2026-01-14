package frontend.controller;


import backend.entities.Restaurant;
import frontend.model.RestaurantHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import javafx.scene.control.Label;

public class RestaurantController {
    @FXML
    private Pane restaurantRoot;

    @FXML
    private Label restaurantName;

    private Restaurant restaurant;

    @FXML
    private void initialize() {
        restaurant = RestaurantHandler.getCurrentRestaurant();

        if (restaurant != null) {
            restaurantName.setText(restaurant.getName());
        }
    }

}
