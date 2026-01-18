package frontend.controller;

import backend.entities.Restaurant;
import frontend.CategoryBox;
import frontend.model.RestaurantHandler;
import frontend.model.SceneHandler;
import frontend.view.RestaurantCard;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.util.List;


public class MainController {
    @FXML private HeaderController headerController;

    @FXML private FlowPane restaurantContainer;
    @FXML private VBox mainArea;
    @FXML private VBox categoryAndRestaurant;
    @FXML private BorderPane categoryContainer;

    @FXML
    private void initialize() {
        if(headerController != null)
            headerController.setMainController(this);

        List<Restaurant> allRestaurants = RestaurantHandler.getResturantList("");
        CategoryBox.setupCategoryButtons(categoryContainer, this);
        displayRestaurants(allRestaurants);
    }

    /**
     * Displays a list of restaurants in the UI.
     * Clears the current view and adds a card for each restaurant.
     */
    protected void displayRestaurants(List<Restaurant> restaurants){
        restaurantContainer.getChildren().clear();
        for(Restaurant r : restaurants){
            RestaurantCard restaurantCard = new RestaurantCard(r, this);
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


    public void performSearch(String restaurant) {
        List<Restaurant> restaurantList = RestaurantHandler.getResturantList(restaurant);

        if(!mainArea.getChildren().contains(categoryAndRestaurant)){
            showGallery();
        }

        displayRestaurants(restaurantList);
    }

    public void showGallery() {
        mainArea.getChildren().clear();
        mainArea.getChildren().addAll(categoryAndRestaurant);
    }

    public void showRestaurantPage(){
        FXMLLoader loader = SceneHandler.switchScene(mainArea, "restaurant-view.fxml");

        if(loader != null){
            RestaurantController controller = loader.getController();
            controller.setMainController(this);
        }
    }
}
