package frontend.controller;

import backend.entities.Restaurant;
import frontend.model.RestaurantHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class HeaderController {
    @FXML private TextField searchRestaurantField;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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
        mainController.performSearch(restaurant);
    }
}
