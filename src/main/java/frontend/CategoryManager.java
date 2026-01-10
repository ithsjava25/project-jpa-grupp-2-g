package frontend;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

import javafx.geometry.Pos;


public class CategoryManager {

    private final HelloController helloController;

    public CategoryManager(HelloController helloController) {
        this.helloController = helloController;
    }

    public void createCategoryGrid() {
        BorderPane categoryContainer = helloController.getCategoryContainer();

        if (categoryContainer == null) {
            System.out.println("categoryContainer is not initialized!");
            return;
        }

        HBox categoryBoxContainer = new HBox(15);
        categoryBoxContainer.setAlignment(Pos.CENTER);

        String[] categories = {"PIZZA", "PASTA", "BURGER", "VEGETARIAN", "THAI", "SUSHI"};

        for (String category : categories) {
            Button categoryButton = createCategoryButton(category);
            categoryBoxContainer.getChildren().add(categoryButton);
        }

        categoryContainer.setCenter(categoryBoxContainer);
    }

    private Button createCategoryButton(String category) {
        Button button = new Button(category);
        button.getStyleClass().add("category-button");

        button.setOnAction(e -> System.out.println("Chosen category: " + category));

        return button;
    }
}
