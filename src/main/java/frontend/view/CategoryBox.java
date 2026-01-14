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

    /**
     * Custom button representing a restaurant category.
     * <p>
     * Initializes the button with a label and a CSS class, and sets an
     * action to trigger filtering in the provided {@code MainController}.
     * </p>
     *
     * @param categoryName the name of the category to display and filter by.
     * @param controller   the controller used to handle the filtering logic.
     */
    public CategoryBox(String categoryName, MainController controller) {
        super(categoryName);
        this.getStyleClass().add("category-button");

        this.setOnAction(e -> {
            controller.filterByCategory(categoryName);
        });
    }

    /**
     * Dynamically generates and displays category filter buttons.
     * <p>
     * Extracts unique categories from all restaurants, creates a
     * {@link CategoryBox} for each, and places them inside an {@code HBox}
     * at the center of the provided {@code BorderPane}.
     * </p>
     *
     * @param rootPane   the container where the button row will be placed.
     * @param controller the controller that will handle the button actions.
     */
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

