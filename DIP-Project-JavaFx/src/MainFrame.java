// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017          

import java.io.File;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;

/*Main Frame of the application.
*/

/*JavaFx terminology, 
 * Entire window Stage
 * inside the window Scene
 */

public class MainFrame extends Application {  
	
	private FileIO fileIO= FileIO.getSingleton();
	
	private RGBImage originalImage ; //the image originally loaded by the user.
	private RGBImage modifiedImage ;  //the image on which the DIPs are executed
	
	private FileChooser fileChooser ;

	private ImageDisplay imageDisplay ;
	private StatusDisplay statusDisplay;
	private VerticalMenu leftDipsMenu ;
	private TopMenu topMenu;
	private SettingsArea settingsArea;
	
	
	public static void main(String[] args) {
        Application.launch(args);
    }
	
	@Override
    public void start(Stage window) {
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
      
        buildStage(window);
        
        addActions(window);
       
        leftDipsMenu.disableDIPS();
        topMenu.disableMenus();
        
        window.show();
    }
    
	private void buildStage(Stage window) {
		
		//set title of the window
        window.setTitle("Digital Image Processing");
        
        BorderPane borderLayout = new BorderPane();
        Scene mainScene= new Scene(borderLayout, Constants.INITIAL_FRAME_WIDTH, Constants.INITIAL_FRAME_HEIGHT, Color.WHITE);
        
        window.setMinHeight(Constants.MINIMUM_FRAME_HEIGHT);
        window.setMinWidth(Constants.MINIMUM_FRAME_WIDTH);
        
        borderLayout.prefHeightProperty().bind(mainScene.heightProperty());
        borderLayout.prefWidthProperty().bind(mainScene.widthProperty());
        
        //Create the top Menu
        topMenu = new TopMenu();
        
    	//create the image display area
        imageDisplay = new ImageDisplay();
        
        //create the status display area
    	statusDisplay = new StatusDisplay();
    	
    	//create the menu of dips on the left
        leftDipsMenu = new VerticalMenu(Constants.DIPS_NAMES);
        
        //create the settings area on the right
        settingsArea = new SettingsArea();
        
        /*
        leftDipsMenu.setMinWidth(Constants.VERTICAL_MENU_MIN_WIDTH);
        settingsArea.setMinWidth(Constants.SETTINGS_MENU_MIN_WIDTH);
        */
        
        borderLayout.setTop(topMenu);
        borderLayout.setLeft(leftDipsMenu);
        borderLayout.setCenter(imageDisplay);
        borderLayout.setRight(settingsArea);
        borderLayout.setBottom(statusDisplay);
        
        window.setScene(mainScene);
	} //End of Build Stage
	
	 private void addActions(Stage window) {
	    	
	    	topMenu.loadMenuItem.setOnAction( e ->
	        {
	        	File imageFile = fileChooser.showOpenDialog(window);
	        	if (imageFile != null) 
	        	{
	        		originalImage = fileIO.loadImage(imageFile);
	        		modifiedImage = fileIO.loadImage(imageFile);
	        		
	        		if (originalImage != null && modifiedImage != null) 
	        		{
	        					/*enable the dips and other menus*/
	        			leftDipsMenu.enableDIPS(); 
	        			topMenu.enableMenus();
	        					/*bind the image width and height properties to the display*/
	        		
	        			statusDisplay.bindImageToDisplay(modifiedImage);
	        	
	        			statusDisplay.updateMessage("Image Loaded Successfully." ); 
	        			imageDisplay.updateImage(modifiedImage.toImage());
	        		}
	        		else
	        		{ 
	        			statusDisplay.updateMessage("Image Load Failed." ); 
	        		}
	        	}   	
	        } );
	        
	        topMenu.saveMenuItem.setOnAction(e ->
	        {
	        	File imageFile = fileChooser.showSaveDialog(window);
	        	if (imageFile != null) 
	        	{
	        		if (fileIO.saveImage(imageFile, modifiedImage) == 0 ) 
	        			statusDisplay.updateMessage("Image Saved Successfully." ); 
	        		else
	        			statusDisplay.updateMessage("Image Save Failed." );    		
	            }
	        } );
	        
	        topMenu.undoAllActions.setOnAction(e ->
	        {
	        	modifiedImage.copy(originalImage);
	        	imageDisplay.updateImage(modifiedImage.toImage());
	        	statusDisplay.updateMessage("Original image recovered.");
	        	
	        });
	        
	        for (int i =0; i< leftDipsMenu.numberOfButtons ; i++) {
	            final int index = i;
	            leftDipsMenu.buttons[index].setOnAction(e -> performDIP(modifiedImage, Constants.DIPS_NAMES[index])); 
	        }
	        
	    }//End of Add Actions
	 
	 
	 private synchronized void performDIP(RGBImage image, String dipName) {
	    	
	    	switch (dipName) {
	    	case "Age": 
	    				DIPs.age(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Black And White" : 
	    				DIPs.blackAndWhite(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Negative" : 
	    				DIPs.negative(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Color Filter" : 
	    				DIPs.colorFilter(image, DIPsSettings.filterTarget.get(), DIPsSettings.filterReplace.get(), DIPsSettings.filterThreshold.get());
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Edge" : 
	    				DIPs.edge(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Horizontal Flip" : 
	    				DIPs.hFlip(image);	
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Vertical Flip" : 
	    				DIPs.vFlip(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Vertical Mirror" : 
	    				DIPs.vMirror(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Shuffle" : 
	    				DIPs.shuffle(image, DIPsSettings.shuffleWidthDiv.get(), DIPsSettings.shuffleHeightDiv.get());
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Border" : 
	    				DIPs.addBorder(image, DIPsSettings.borderWidth.get(), DIPsSettings.borderHeight.get(), DIPsSettings.borderColor.getValue()); 				
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Noise" : 
	    				DIPs.noise(image, DIPsSettings.noisePercentage.get());
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Sharpen" : 
	    				DIPs.sharpen(image);
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Crop" : 
	    				try {
	    					DIPs.crop(image, DIPsSettings.cropXOffset.get(), DIPsSettings.cropYOffset.get(), 
	    							DIPsSettings.cropWidth.get(), DIPsSettings.cropHeight.get());
	    					statusDisplay.updateMessage(dipName + " executed.");
	    					}
	    				catch (Exception ex) {
	    					statusDisplay.updateMessage(dipName + " failed for invalid arguments.");
	    				}
	    				break;
	    	
	    	case "Spotlight" : 
	    				DIPs.spotlight(image, DIPsSettings.spotlightCenterX.get(), DIPsSettings.spotlightCenterY.get(), DIPsSettings.spotlightRadius.get());
	    				statusDisplay.updateMessage(dipName + " executed.");
	    				break;
	    	case "Watermark" :
	    		
	    			try {
	    				DIPs.watermark(image, DIPsSettings.watermarkRatio.get(), DIPsSettings.watermarkImage.get());
	    				statusDisplay.updateMessage(dipName + " executed.");
	    			} catch (Exception e) {
	    				statusDisplay.updateMessage(dipName + " failed. Please pick a watermark file.");
	    			}
	    			break;
			case "Resize" : 
						try {
							DIPs.resize(image, DIPsSettings.resizePercentage.get());
							statusDisplay.updateMessage(dipName + " executed.");
							}
						catch (OutOfMemoryError e ) {
								JOptionPane.showMessageDialog(null, "There is not enough heap memory to complete this operation. Please close some programs and try again.", "Error", JOptionPane.ERROR_MESSAGE );
								statusDisplay.updateMessage(dipName + " failed.");	
								this.topMenu.undoAllActions.fire();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage() , "Error", JOptionPane.ERROR_MESSAGE );
							statusDisplay.updateMessage(dipName + " failed.");
						}
						break;
			case "Posterize" :
							DIPs.posterize(image, DIPsSettings.rbitPoster.get(), DIPsSettings.gbitPoster.get(), DIPsSettings.bbitPoster.get());
							statusDisplay.updateMessage(dipName + " executed.");
						break;
			case "Motion Blur" : 
						DIPs.motionBlur(image, DIPsSettings.blurAmount.get());
						statusDisplay.updateMessage(dipName + " executed.");
						break;
			case "Brighness And Contrast" : 
						DIPs.brightnessAndContrast(image, DIPsSettings.brightness.get(), DIPsSettings.contrast.get());
						statusDisplay.updateMessage(dipName + " executed.");
						break;
			default: statusDisplay.updateMessage("Invalid DIP detected.");
				break;
	    	}
	    	
	    	imageDisplay.updateImage(image.toImage());
	    	
	    }//End of perform DIP
	 

}
	