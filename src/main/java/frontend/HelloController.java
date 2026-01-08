package frontend;

import backend.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

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
        container.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView();
        String imagePath = "/images/" + restaurant.getImagePath();
        try{
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
        } catch (Exception e) {
            imageView.setImage(null);
            System.out.println("Could not find: " + imagePath);
        }

        Label name = new Label(restaurant.getName());

        container.getChildren().addAll(imageView, name);
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
