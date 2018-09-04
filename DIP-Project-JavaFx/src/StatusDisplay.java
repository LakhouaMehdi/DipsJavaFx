// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import java.text.MessageFormat;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class StatusDisplay extends BorderPane {
	
	private Label messageLabel;
	private Label sizeLabel;
	
	public StatusDisplay() {
		super();
		this.setBorder(
				new Border(
						new BorderStroke(
								Color.DARKGRAY, BorderStrokeStyle.SOLID, 
								CornerRadii.EMPTY, 
								new BorderWidths(1) )
						  )
				      );
		
		messageLabel = new Label("Ready ...");
		sizeLabel = new Label("");
		
		this.setLeft(messageLabel);
		this.setRight(sizeLabel);
	}
	
	/* updates the message displayed on the left size */
	public void updateMessage(String message) {
		messageLabel.setText(message);	
	}
	
	/* binds the width and height properties of the image to the size label text */
	public void bindImageToDisplay(final RGBImage image) {
		
		StringBinding binding = Bindings.createStringBinding(
	            () -> MessageFormat.format(Constants.SIZE_DISPLAY_FORMAT , image.getWidth(), image.getHeight()),
	            image.getWidthProperty(),
	            image.getHeightProperty()
	            );
		this.sizeLabel.textProperty().bind(	binding );
	
	}
	
	
}
