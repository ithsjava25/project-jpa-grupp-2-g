package frontend;

import backend.entities.Restaurant;
import frontend.controller.MainController;
import frontend.model.RestaurantHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoryBox extends Button{

    public CategoryBox(String categoryName, MainController controller) {
        super(categoryName);
        this.getStyleClass().add("category-button");

        this.setOnAction(e -> {
            controller.filterByCategory(categoryName);
        });
    }

    public static void setupCategoryButtons(BorderPane rootPane, MainController controller) {
        List<Restaurant> allRestaurants = RestaurantHandler.getResturantList("");
        Set<String> uniqueCategories = new HashSet<>();

        for (Restaurant r : allRestaurants) {
            if (r.getCategory() != null) {
                uniqueCategories.add(r.getCategory());
            }
        }

        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);

        buttonRow.getChildren().add(new CategoryBox("All", controller));
        for (String category : uniqueCategories) {
            buttonRow.getChildren().add(new CategoryBox(category, controller));
        }

        rootPane.setCenter(buttonRow);
    }

}

