package frontend;

import backend.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Label;

import java.util.List;
import java.util.Objects;

public class HelloController {
    private final HelloModel model = new HelloModel();
    private final ObservableList<Restaurant> filteredRestaurants = FXCollections.observableArrayList();

    @FXML
    public ListView<Restaurant> restaurantView;
    @FXML
    private Label nameApp;
    @FXML
    private TextField searchRestaurantField;

    @FXML
    private void initialize() {
        if (nameApp != null) {
            nameApp.setText(model.getAppName());
        }

        filteredRestaurants.setAll(model.getResturantList(""));
        restaurantView.setItems(filteredRestaurants);
        restaurantView.setCellFactory(list -> restaurantNamePicture());
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        filteredRestaurants.setAll(restaurantList);
    }

    private ListCell<Restaurant> restaurantNamePicture(){
        return new ListCell<>(){
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Restaurant restaurant, boolean empty){
                super.updateItem(restaurant, empty);
                if(empty || restaurant == null){
                    setText("");
                    setGraphic(null);
                } else {
                    setText(restaurant.getName());

                    //Fetch image-path and create graphic
                    String imagePath = "/images/" + restaurant.getImagePath();
                    try{
                        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                        System.out.println("Could not load picture: " + imagePath);
                    }
                }

            }

        };
    }


}
