// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

public class ImageDisplay extends ScrollPane{

	
	ImageView imageView;
	
	final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);

	public ImageDisplay() {
		imageView = new ImageView();
		imageView.setPreserveRatio(true);
		this.setContent(imageView);
		
		 zoomProperty.addListener(new InvalidationListener() {
	            @Override
	            public void invalidated(Observable arg0) {
	                imageView.setFitWidth(zoomProperty.get() * 4);
	                imageView.setFitHeight(zoomProperty.get() * 3);
	            }
	        });
		
		this.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        });
	}
	
	/* updates the image displayed */
	public void updateImage(Image image) {
		imageView.setImage(image);
	}
	
}
