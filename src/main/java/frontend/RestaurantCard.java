package frontend;

import backend.entities.Restaurant;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class RestaurantCard extends VBox {

    public RestaurantCard(Restaurant restaurant){
        super(10);
        this.getStyleClass().add("restaurantBox");

        setUpImage(restaurant);
        setUpNameAndRating(restaurant);
    }

    private void setUpImage(Restaurant restaurant){
        ImageView imageView = new ImageView();
        String imagePath = "/images/" + restaurant.getImagePath();

        try{
            Image image = new Image(Objects.requireNonNull(RestaurantCard.class.getResourceAsStream(imagePath)));
            imageView.setImage(image);
            ImageHandler.scaleAndCropImage(imageView, 100, 150);

            //Rounds upper two corners of image, so it matches corners of VBox (container)
            Rectangle clip = new Rectangle();
            clip.widthProperty().bind(imageView.fitWidthProperty());
            clip.heightProperty().bind(imageView.fitHeightProperty().add(50));
            clip.setArcWidth(30);
            clip.setArcHeight(30);

            imageView.setClip(clip);
            this.getChildren().add(imageView);

        } catch (Exception e) {
            imageView.setImage(null);
            System.out.println("Could not find: " + imagePath);
        }
    }

    private void setUpNameAndRating(Restaurant restaurant){
        HBox container = new HBox();

        Label name = new Label(restaurant.getName());
        Label rating = new Label("⭐ " + restaurant.getRating());

        //Used to push rating to the right side of the box
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        container.getChildren().addAll(name, spacer, rating);
        this.getChildren().add(container);
    }
}
