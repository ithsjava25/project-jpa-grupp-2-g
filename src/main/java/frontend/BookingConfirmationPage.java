package frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookingConfirmationPage {

    public static void showConfirmationPage(Stage stage, String guestName, String restaurantName, String bookingDateTime, int numberOfPeople) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        // bekräftelsemeddelandet
        Text confirmationMessage = new Text("Thank you for your booking, " + guestName + "!");
        confirmationMessage.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Agency FB'; -fx-fill: #C16D3C;");

        // info om bokningen
        Text bookingDetails = new Text("You have booked a table at " + restaurantName + " on " + bookingDateTime + " for " + numberOfPeople + " people.");
        bookingDetails.setStyle("-fx-font-size: 18px; -fx-font-family: 'Agency FB'; -fx-fill: #C16D3C;");

        // e-postbekräftelse
        Text confirmationEmailMessage = new Text("A confirmation will be sent to your email address.");
        confirmationEmailMessage.setStyle("-fx-font-size: 18px; -fx-font-family: 'Agency FB'; -fx-fill: #C16D3C;");


        // knapp för att gå tillbaka till startsidan
        Button returnButton = new Button("Back to main page");
        returnButton.setStyle("-fx-padding: 10px 20px; -fx-font-size: 14px;");
        returnButton.setOnAction(e -> {
        });

        layout.getChildren().addAll(confirmationMessage, bookingDetails, confirmationEmailMessage, returnButton);

        // skapa och visa scenen
        Scene scene = new Scene(layout, 600, 300);
        stage.setTitle("Booking confirmed!");
        stage.setScene(scene);
        stage.show();
    }
}