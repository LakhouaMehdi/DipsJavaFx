// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

 
public 
class VerticalMenu extends BorderPane
{                                      
	
	protected String[] buttonNames = null;
	
	protected Button[] buttons = null;
//	private final Color BACKGROUND_COLOR = new Color(245,245,245);	//White version
	private VBox buttonBox = new VBox();
	public int numberOfButtons = 0 ;
	
	
	public VerticalMenu(String [] names) {

		this.buttonNames = names;
		
		this.buttons = new Button [buttonNames.length];
			
			for (int i=0; i< buttonNames.length; i++) 
			{
					buttons[i] = new Button( buttonNames[i]);
					buttons[i].setAlignment(Pos.BASELINE_CENTER);
					buttons[i].setMaxWidth(Double.POSITIVE_INFINITY);
					buttonBox.getChildren().add(buttons[i]);
					numberOfButtons ++ ;
			}
				
		
		ScrollPane scrollPane = new ScrollPane(buttonBox);
	
		 scrollPane.setFitToWidth(true);
		 scrollPane.setFitToHeight(true);
		 
	     this.setCenter(scrollPane);
	}
	
	protected void disableDIPS() {
		for(int i=0; i< buttons.length; i++) {
			buttons[i].setDisable(true);
		}
	}
	
	protected void enableDIPS() {
		for(int i=0; i< buttons.length ; i++) {
			buttons[i].setDisable(false);
		}
	}
	
	
}
//END OF FILE