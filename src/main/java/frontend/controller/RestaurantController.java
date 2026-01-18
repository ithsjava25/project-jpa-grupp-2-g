package frontend.controller;

import backend.entities.Restaurant;
import frontend.model.ImageHandler;
import frontend.model.RestaurantHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RestaurantController {
    @FXML private Label restaurantName;
    @FXML private Label restaurantAdress;
    @FXML private Label restaurantCategory;
    @FXML private Label restaurantMeanPrice;
    @FXML private Label restaurantRating;
    @FXML private ImageView restaurantImage;

    @FXML private Label adressIcon;
    @FXML private Label categoryIcon;
    @FXML private Label meanPriceIcon;
    @FXML private Label ratingIcon;

    private MainController mainController;
    private Restaurant restaurant;

    @FXML
    private void initialize() {
        restaurant = RestaurantHandler.getCurrentRestaurant();

        if (restaurant != null) {
            restaurantName.setText(restaurant.getName());
        }

        setRestaurantData(restaurant);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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
        restaurantImage.setImage(ImageHandler.getRestaurantImage(restaurant));
        ImageHandler.scaleAndCropImage(restaurantImage, 350, 250);
    }

    @FXML
    private void returnToMain(){
        if(mainController != null)
            mainController.showGallery();
    }

}
