package frontend.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageHandler {
    public static void scaleAndCropImage(ImageView imageView, double targetHeight, double targetWidth){
        Image image = imageView.getImage();

        double width = image.getWidth();
        double height = image.getHeight();

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
    }
}
