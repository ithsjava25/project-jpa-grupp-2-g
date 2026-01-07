package frontend;

import backend.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.scene.control.Label;

import java.util.List;

public class HelloController {
    private final HelloModel model = new HelloModel();
    private final ObservableList<Restaurant> filteredRestaurants = FXCollections.observableArrayList();

    @FXML
    public ListView<Restaurant> restaurantView;
    @FXML
    private Label nameApp;
    @FXML
    private TextField searchRestaurantField;

    @FXML
    private void initialize() {
        if (nameApp != null) {
            nameApp.setText(model.getAppName());
        }

        filteredRestaurants.setAll(model.getResturantList(""));
        restaurantView.setItems(filteredRestaurants);
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        filteredRestaurants.setAll(restaurantList);
    }


}
