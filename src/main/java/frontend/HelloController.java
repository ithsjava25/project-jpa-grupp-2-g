package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private void initialize() {
        appName.setText(model.getAppName());

        categoryManager = new CategoryManager(this);
        categoryManager.createCategoryGrid();

        bookingMoreGuests = new BookingMoreGuests(numberOfPeopleField, confirmBookingButton, bookingConfirmationLabel);

        // Sätt knappen till disabled till en början
        confirmBookingButton.setDisable(true);

        // EventHandler så att man måste skriva något, annars blir knappen disabled
        numberOfPeopleField.textProperty().addListener((observable, oldValue, newValue) -> {
            bookingMoreGuests.checkBooking();
        });
    }

    @FXML
    public void handleRestaurantSearch() {
    }

    @FXML
    public void checkBooking() {
        // Anropa metoden från BookingMoreGuests för att kontrollera om bokningen är giltig
        bookingMoreGuests.checkBooking();
    }

    @FXML
    public void confirmBooking() {
        // Bekräfta bokningen
        bookingMoreGuests.confirmBooking();

        // användardata (namn, restaurang, antal personer)
        String guestName = "Anna Ziafar";
        String restaurantName = "Erics Pizza";
        String bookingDateTime = "2026-01-10 19:00";
        int numberOfPeople = Integer.parseInt(numberOfPeopleField.getText());

        // bekräftelsesidan med info
        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        BookingConfirmationPage.showConfirmationPage(stage, guestName, restaurantName, bookingDateTime, numberOfPeople);
    }

    public BorderPane getCategoryContainer() {
        return categoryContainer;
    }
}