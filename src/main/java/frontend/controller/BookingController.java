package frontend.controller;

import backend.entities.Booking;
import backend.entities.Restaurant;
import backend.services.BookingService;
import frontend.model.RestaurantHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingController {

    private final BookingService bookingService = new BookingService();
    private Restaurant restaurant = RestaurantHandler.getCurrentRestaurant();

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    @FXML private Spinner<Integer> guestsSpinner;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<LocalTime> startTimeBox;


    @FXML private Label statusLabel;

    @FXML
    private void initialize() {

        guestsSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 2)
        );

        for (int h = 12; h <= 20; h++) {
            startTimeBox.getItems().add(LocalTime.of(h, 0));
//            startTimeBox.getItems().add(LocalTime.of(h, 15));
//            startTimeBox.getItems().add(LocalTime.of(h, 30));
        }
    }

    /**
     * Handles the book table button action.
     *
     * Reads user input from the form, validates required fields,
     * creates a booking, and shows the result to the user.
     */
    @FXML
    private void BookTable() {
        try {
            LocalDate date = datePicker.getValue();
            LocalTime startTime = startTimeBox.getValue();

            if (date == null || startTime == null) {
                statusLabel.setText("Please select date and time");
                return;
            }

            LocalTime endTime = startTime.plusHours(2);

            Booking booking = bookingService.bookTable(
                restaurant,
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                guestsSpinner.getValue(),
                startTime,
                endTime,
                date
            );

            statusLabel.setText(
                "Booking confirmed! ID: " + booking.getId()
            );

        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }
    }

//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
}
