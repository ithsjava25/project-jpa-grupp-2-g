package frontend;

import backend.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

public class HelloController {
    private final HelloModel model = new HelloModel();
    private CategoryManager categoryManager;
    private BookingMoreGuests bookingMoreGuests;

    @FXML
    private Label appName;
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private TextField numberOfPeopleField;
    @FXML
    private Button confirmBookingButton;
    @FXML
    private Label bookingConfirmationLabel;
    @FXML
    private BorderPane categoryContainer;
    @FXML
    private FlowPane restaurantContainer;

    @FXML
    private void initialize() {
        appName.setText(model.getAppName());

        categoryManager = new CategoryManager(this);
        categoryManager.createCategoryGrid();

        bookingMoreGuests = new BookingMoreGuests(numberOfPeopleField, confirmBookingButton, bookingConfirmationLabel);

        confirmBookingButton.setDisable(true);

        numberOfPeopleField.textProperty().addListener((observable, oldValue, newValue) -> {
            bookingMoreGuests.checkBooking();
        });

        List<Restaurant> allRestaurants = model.getResturantList("");
        displayRestaurants(allRestaurants);
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        displayRestaurants(restaurantList);
    }

    private void displayRestaurants(List<Restaurant> restaurants){
        restaurantContainer.getChildren().clear();
        for(Restaurant r : restaurants){
            RestaurantCard restaurantCard = new RestaurantCard(r);
            restaurantContainer.getChildren().add(restaurantCard);
        }
    }

    @FXML
    public void checkBooking() {
        bookingMoreGuests.checkBooking();
    }

    @FXML
    public void confirmBooking() {
        bookingMoreGuests.confirmBooking();

        String guestName = "Anna Ziafar";
        String restaurantName = "Erics Pizza";
        String bookingDateTime = "2026-01-10 19:00";
        int numberOfPeople = Integer.parseInt(numberOfPeopleField.getText());

        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        BookingConfirmationPage.showConfirmationPage(stage, guestName, restaurantName, bookingDateTime, numberOfPeople);
    }

    public BorderPane getCategoryContainer() {
        return categoryContainer;
    }
}