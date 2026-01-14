package frontend.model;

import backend.entities.Restaurant;
import frontend.view.RestaurantCard;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class ImageHandler {

    /**
     * Scales and crops an image within an {@code ImageView} to fit specific dimensions.
     * <p>
     * This method calculates a centered viewport based on the target aspect ratio,
     * effectively performing a "center-crop"
     * </p>
     * * @param imageView    the view containing the image to be adjusted.
     * @param targetWidth  the desired width of the view.
     * @param targetHeight the desired height of the view.
     */
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

    /**
     * Retrieves the image associated with a specific restaurant.
     * The method constructs a full resource path using the base directory "/images/"
     *
     * @param restaurant the restaurant entity containing the image filename.
     * @return an {@link Image} object if the resource is found,
     * or {@code null} if the path is invalid or the file is missing.
     */
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

