package frontend;

import backend.entities.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.util.List;

public class HelloController {
    private final HelloModel model = new HelloModel();
    private final ObservableList<Restaurant> filteredRestaurants = FXCollections.observableArrayList();

    @FXML
    private Label appName;
    @FXML
    private TextField searchRestaurantField;
    @FXML
    private ListView<Restaurant> restaurantView;

    @FXML
    private BorderPane categoryContainer;

    private GridPane grid;

    @FXML
    private void initialize() {

        if (appName != null) {
            appName.setText(model.getAppName());
        }

        // restauranger
        filteredRestaurants.setAll(model.getResturantList(""));
        restaurantView.setItems(filteredRestaurants);

        // kategorigrid
        createCategoryGrid();
    }

    @FXML
    public void handleRestaurantSearch(ActionEvent event){
        String restaurant = searchRestaurantField.getText();
        List<Restaurant> restaurantList = model.getResturantList(restaurant);
        filteredRestaurants.setAll(restaurantList);
    }

    private void createCategoryGrid() {
        grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setAlignment(Pos.CENTER);

        String[] categories = {"PIZZA", "PASTA", "BURGER", "VEGETARIAN", "THAI", "SUSHI"};

        for (int i = 0; i < categories.length; i++) {
            grid.add(createCategoryBox(categories[i]), i % 3, i / 3);
        }

        categoryContainer.setCenter(grid);
    }

    private VBox createCategoryBox(String category) {
        Label label = new Label(category);
        label.getStyleClass().add("category-label");

        VBox box = new VBox(label);
        box.getStyleClass().add("category-box");
        box.setAlignment(Pos.CENTER);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, 0.2));
        shadow.setRadius(10);
        box.setEffect(shadow);

        box.setOnMouseClicked(e -> System.out.println("Chosen category: " + category));
        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #e0e0e0; -fx-border-radius: 12; -fx-background-radius: 12;"));
        box.setOnMouseExited(e -> box.setStyle(""));

        return box;
    }
}