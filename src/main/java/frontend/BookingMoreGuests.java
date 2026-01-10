package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookingMoreGuests {

    private TextField numberOfPeopleField;
    private Button confirmBookingButton;
    private Label bookingConfirmationLabel;

    public BookingMoreGuests(TextField numberOfPeopleField, Button confirmBookingButton, Label bookingConfirmationLabel) {
        this.numberOfPeopleField = numberOfPeopleField;
        this.confirmBookingButton = confirmBookingButton;
        this.bookingConfirmationLabel = bookingConfirmationLabel;
    }

    // Kontrollera om antalet personer är större än 8
    public void checkBooking() {
        try {
            int numberOfPeople = Integer.parseInt(numberOfPeopleField.getText());
            if (numberOfPeople > 8) {
                confirmBookingButton.setDisable(false);  // aktivera knapp om mer än 8 personer
            } else {
                confirmBookingButton.setDisable(true);   // inaktivera knappen om mindre än eller lika med 8 personer
            }
        } catch (NumberFormatException e) {
            confirmBookingButton.setDisable(true);  // om input inte är ett giltigt tal
        }
    }

    // bekräfta bokningen o visa meddelande
    public void confirmBooking() {
        bookingConfirmationLabel.setVisible(true);
    }
}