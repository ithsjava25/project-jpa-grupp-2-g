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
        guestsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 2));
        for (int h = 12; h <= 20; h++) {
            startTimeBox.getItems().add(LocalTime.of(h, 0));
        }
    }

    @FXML
    private void BookTable() {
        try {
            LocalDate date = datePicker.getValue();
            LocalTime startTime = startTimeBox.getValue();

            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    date == null || startTime == null) {
                statusLabel.setText("Please fill in all required fields");
                statusLabel.setStyle("-fx-text-fill: red;");
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

            statusLabel.setText("Booking confirmed! ID: " + booking.getId());
            statusLabel.setStyle("-fx-text-fill: green;");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking confirmed");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Booking confirmed for " + firstNameField.getText() + " " + lastNameField.getText() + "\n" +
                            "Restaurant: " + restaurant.getName() + "\n" +
                            "Date & Time: " + date + " at " + startTime + "\n" +
                            "Guests: " + guestsSpinner.getValue()
            );
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.lookup(".content.label").setStyle("-fx-text-fill: #CF6720; -fx-font-weight: bold; -fx-font-family: 'Agency FB'; -fx-font-size: 16px;"
            );

            alert.showAndWait();

            firstNameField.clear();
            lastNameField.clear();
            phoneField.clear();
            emailField.clear();
            guestsSpinner.getValueFactory().setValue(1);
            datePicker.setValue(null);
            startTimeBox.getSelectionModel().clearSelection();

        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}