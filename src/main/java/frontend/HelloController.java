package frontend;

import backend.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    private final HelloModel model = new HelloModel();

    @FXML
    private Label appName;
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private FlowPane restaurantContainer;

    @FXML
    private void initialize() {
        if (appName != null) {
            appName.setText(model.getAppName());
        }

        List<Restaurant> allRestaurants = model.getResturantList("");
        displayRestaurants(allRestaurants);
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        displayRestaurants(restaurantList);
    }

    private void openBookingView(Restaurant restaurant) {
        System.out.println("Clicked restaurant: " + restaurant.getName());

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/frontend/booking-view.fxml")
            );

            Parent root = loader.load();

            BookingController controller = loader.getController();
            controller.setRestaurant(restaurant);

            Stage stage = (Stage) restaurantContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Book table – " + restaurant.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void displayRestaurants(List<Restaurant> restaurants){
        restaurantContainer.getChildren().clear();
        for(Restaurant r : restaurants){
            RestaurantCard restaurantCard = new RestaurantCard(r, this::openBookingView);
            restaurantContainer.getChildren().add(restaurantCard);
        }
    }

}
