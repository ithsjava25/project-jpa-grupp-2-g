package frontend.model;

import backend.entities.Restaurant;
import frontend.view.RestaurantCard;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class ImageHandler {
    public static void scaleAndCropImage(ImageView imageView, double targetWidth, double targetHeight){
        Image image = imageView.getImage();

        double width = image.getWidth();
        double height = image.getHeight();

        //Calculate ratio
        double ratio = Math.min(width / targetWidth, height / targetHeight);
        double viewWidth = targetWidth * ratio;
        double viewHeight = targetHeight * ratio;

        //Centers scale, so it does not start from top left corner
        double x = (width - viewWidth) / 2;
        double y = (height - viewHeight) / 2;

        imageView.setViewport(new Rectangle2D(x, y, viewWidth, viewHeight));
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
    }

    public static Image getRestaurantImage(Restaurant restaurant) {
        String imagePath = "/images/" + restaurant.getImagePath();
        try {
            return new Image(Objects.requireNonNull(ImageHandler.class.getResourceAsStream(imagePath)));
        } catch (Exception e) {
            System.out.println("Could not find: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }

}

