package frontend.controller;


import backend.entities.Booking;
import backend.entities.Restaurant;
import backend.services.BookingService;
import frontend.model.RestaurantHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.LocalTime;

public class RestaurantController {
    @FXML
    private Pane restaurantPageRoot;
    @FXML
    private Label restaurantName;
    @FXML
    private Label restaurantAdress;
    @FXML
    private Label adressIcon;
    @FXML
    private Label categoryIcon;
    @FXML
    private Label restaurantCategory;
    @FXML
    private Label meanPriceIcon;
    @FXML
    private Label restaurantMeanPrice;
    @FXML
    private Label ratingIcon;
    @FXML
    private Label restaurantRating;

    private Restaurant restaurant;

    @FXML
    private void initialize() {
        restaurant = RestaurantHandler.getCurrentRestaurant();

        if (restaurant != null) {
            restaurantName.setText(restaurant.getName());
        }

        setRestaurantData(restaurant);
    }

    private void setRestaurantData(Restaurant restaurant){
        restaurantName.setText(restaurant.getName());
        adressIcon.setText("📍");
        restaurantAdress.setText(restaurant.getAddress());
        categoryIcon.setText("🍴");
        restaurantCategory.setText(restaurant.getCategory());
        meanPriceIcon.setText("$");
        restaurantMeanPrice.setText(restaurant.getMeanPrice().toString() + " kr");
        ratingIcon.setText("⭐ ");
        restaurantRating.setText(String.valueOf(restaurant.getRating()));
    }

}
