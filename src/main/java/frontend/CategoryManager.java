package frontend;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class CategoryManager {

    private final HelloController helloController;

    public CategoryManager(HelloController helloController) {
        this.helloController = helloController;
    }

    public void createCategoryGrid() {
        // hämta BorderPane från HelloController
        BorderPane categoryContainer = helloController.getCategoryContainer();

        if (categoryContainer == null) {
            System.out.println("categoryContainer is not initialized!");
            return;
        }

        // Skapa en HBox som kommer hålla kategoriboxarna
        HBox categoryBoxContainer = new HBox(15);  // Justera mellanrum mellan rutorna
        categoryBoxContainer.setAlignment(Pos.CENTER);  // Centrerar innehållet horisontellt

        // Kategorinamn (kan justeras senare)
        String[] categories = {"PIZZA", "PASTA", "BURGER", "VEGETARIAN", "THAI", "SUSHI"};

        // Lägg till alla kategoriboxar i HBox
        for (String category : categories) {
            Button categoryButton = createCategoryButton(category);
            categoryBoxContainer.getChildren().add(categoryButton);
        }

        // Sätt HBox i BorderPane
        categoryContainer.setCenter(categoryBoxContainer);
    }

    private Button createCategoryButton(String category) {
        // Skapa en Button för varje kategori
        Button button = new Button(category);
        button.getStyleClass().add("category-button");

        // Lägg till klick- och hover-effekter för varje kategori
        button.setOnAction(e -> System.out.println("Chosen category: " + category));

        return button;
    }
}
