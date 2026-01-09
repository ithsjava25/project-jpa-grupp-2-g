package frontend;

import backend.entities.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Objects;

public class HelloController {
    private final HelloModel model = new HelloModel();

    @FXML
    private Label appName;
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private FlowPane restaurantContainer;

    @FXML
    private void initialize() {
        if (appName != null) {
            appName.setText(model.getAppName());
        }

        List<Restaurant> allRestaurants = model.getResturantList("");
        displayRestaurants(allRestaurants);
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        displayRestaurants(restaurantList);
    }

    private VBox createRestaurantContainer(Restaurant restaurant){
        VBox container = new VBox(10);
        container.getStyleClass().add("restaurantBox");

        ImageView imageView = new ImageView();
        String imagePath = "/images/" + restaurant.getImagePath();
        try{
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);

            //Rounds upper two corners of image, so it matches corners of VBox (container)
            Rectangle clip = new Rectangle();
            clip.widthProperty().bind(imageView.fitWidthProperty());
            clip.heightProperty().bind(imageView.fitHeightProperty().add(50));
            clip.setArcWidth(30);
            clip.setArcHeight(30);

            imageView.setClip(clip);

        } catch (Exception e) {
            imageView.setImage(null);
            System.out.println("Could not find: " + imagePath);
        }

        HBox hbox = displayNameAndRating(restaurant);

        container.getChildren().addAll(imageView, hbox);
        return container;
    }

    public HBox displayNameAndRating(Restaurant restaurant){
        HBox container = new HBox();

        Label name = new Label(restaurant.getName());
        Label rating = new Label("⭐ " + restaurant.getRating());

        //Used to push rating to the right side of the box
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        container.getChildren().addAll(name, spacer, rating);
        return container;
    }

    private void displayRestaurants(List<Restaurant> restaurants){
        restaurantContainer.getChildren().clear();
        for(Restaurant r : restaurants){
            VBox box = createRestaurantContainer(r);
            restaurantContainer.getChildren().add(box);
        }
    }

}
