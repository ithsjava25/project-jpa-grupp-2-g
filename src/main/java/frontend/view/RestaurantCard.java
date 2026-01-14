package frontend.view;

import backend.entities.Restaurant;
import frontend.model.ImageHandler;
import frontend.model.RestaurantHandler;
import frontend.model.SceneHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class RestaurantCard extends VBox {

    public RestaurantCard(Restaurant restaurant, Pane pane){
        super(10);
        this.getStyleClass().add("restaurantBox");
        this.setOnMouseClicked(e -> {
            RestaurantHandler.setCurrentRestaurant(restaurant);
            SceneHandler.switchScene(pane, "restaurant-view.fxml");
        });

        ImageView view = new ImageView();
        view.setImage(ImageHandler.getRestaurantImage(restaurant));
        ImageHandler.scaleAndCropImage(view, 100, 150);

        //Rounds upper two corners of image, so it matches corners of VBox (container)
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(view.fitWidthProperty());
        clip.heightProperty().bind(view.fitHeightProperty().add(50));
        clip.setArcWidth(30);
        clip.setArcHeight(30);

        view.setClip(clip);
        this.getChildren().add(view);
        setUpNameAndRating(restaurant);
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
