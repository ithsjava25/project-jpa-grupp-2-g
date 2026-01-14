package frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HeaderManager {

    private final CategoryController helloController;

    public HeaderManager(CategoryController helloController) {
        this.helloController = helloController;
    }

    public void createHeader() {
        StackPane header = new StackPane();
        header.setPrefHeight(100);   // vanlig headerhöjd
        header.setStyle("-fx-background-color: white;");
        header.prefWidthProperty().bind(helloController.getRootPane().widthProperty());

        // bakgrundsbild
        ImageView backgroundImage = new ImageView(
                new Image(getClass().getResource("/images/header.png").toExternalForm())
        );
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);
        backgroundImage.fitWidthProperty().bind(header.widthProperty()); // fyller hela bredden
        backgroundImage.setFitHeight(350); // lagom höjd
        StackPane.setAlignment(backgroundImage, Pos.CENTER);

        // logotyp
        ImageView logo = new ImageView(new Image(getClass().getResource("/images/Logo.png").toExternalForm()));
        logo.setPreserveRatio(true);
        logo.setFitHeight(400); // logotypens storlek
        StackPane.setAlignment(logo, Pos.CENTER); // positionerar logotypen i toppen till vänster
        StackPane.setMargin(logo, new Insets(10, 0, 0, 10)); // flytta ner/åt höger om du vill

        // sökfält
        TextField searchField = new TextField();
        searchField.setPromptText("Search restaurants...");
        searchField.setMaxWidth(250);
        StackPane.setAlignment(searchField, Pos.BOTTOM_RIGHT); // positionerar sökrutan i toppen till höger
        StackPane.setMargin(searchField, new Insets(0, 180, 50, 0)); // flytta ner/åt vänster om du vill

        // lägg logo o sök i headern
        header.getChildren().addAll(backgroundImage, logo, searchField);

        helloController.getRootPane().setTop(header);
    }
}
