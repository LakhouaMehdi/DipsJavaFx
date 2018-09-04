// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import java.io.File;
import java.text.NumberFormat;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SettingsArea extends BorderPane{
	
	public SettingsArea() {
		
		VBox box = new VBox();
		
		/* Brightness/Contrast Settings. */
		Slider brightnessSldr = getSlider(-Constants.MAX_PIXEL, Constants.MAX_PIXEL, DIPsSettings.brightness );
		Slider contrastSldr = getSlider(-Constants.MAX_PIXEL, Constants.MAX_PIXEL, DIPsSettings.contrast );
		
		Label brightnessLbl = getLabel(DIPsSettings.brightness);
		Label contrastLbl = getLabel(DIPsSettings.contrast);

		box.getChildren().add( 
				new TitledPane("Brightness/Contrast Settings", 
				getGrid( new Control [] {new Label("Brightness :"), new Label("Contrast :") },
						 new Control [] {brightnessSldr, contrastSldr},
						 new Control [] {brightnessLbl, contrastLbl} ) 
						) 
				);
		
		/* Blur Settings */
		Slider blurSldr = getSlider(0, 100,  DIPsSettings.blurAmount);
		Label blurLbl = getLabel(DIPsSettings.blurAmount);
		
		box.getChildren().add( 
				new TitledPane("Blur Settings", 
				getGrid( new Control [] {new Label("Blur Amount :")},
						 new Control [] {blurSldr}, 
						 new Control [] {blurLbl} ) 
						) 
				);
		
		/* Noise Settings */
		Slider noiseSldr = getSlider(0, 100,  DIPsSettings.noisePercentage);
		Label noiseLbl = getLabel(DIPsSettings.noisePercentage);
		
		box.getChildren().add( 
				new TitledPane("Noise Settings", 
				getGrid( new Control [] {new Label("Noise Percentage :")},
						 new Control [] {noiseSldr}, 
						 new Control [] {noiseLbl} ) 
						) 
				);
		
		/* Resize Settings */
		Slider resizeSldr = getSlider(1, 500 , DIPsSettings.resizePercentage);
		Label resizeLbl = getLabel(DIPsSettings.resizePercentage );
		
		box.getChildren().add( 
				new TitledPane("Resize Settings", 
				getGrid( new Control [] {new Label("Resize Percentage :")},
						 new Control [] {resizeSldr}, 
						 new Control [] {resizeLbl} ) 
						) 
				);
		
		/* Shuffle Settings */
		Slider shuffleHSldr = getSlider(1, 10,  DIPsSettings.shuffleHeightDiv);
		Slider shuffleWSldr = getSlider(1, 10,  DIPsSettings.shuffleWidthDiv);
		
		Label shuffleHLbl = getLabel(DIPsSettings.shuffleHeightDiv);
		Label shuffleWLbl = getLabel(DIPsSettings.shuffleWidthDiv);
		
		box.getChildren().add( 
				new TitledPane("Shuffle Settings", 
				getGrid( new Control [] {new Label("Width Div :"), new Label("Height Div :")},
						 new Control [] {shuffleWSldr, shuffleHSldr}, 
						 new Control [] {shuffleWLbl, shuffleHLbl}	) 
						) 
				);
		
		/* Posterize Settings */
		Slider rPostSldr = getSlider(2,8, DIPsSettings.rbitPoster);
		Slider gPostSldr = getSlider(2,8, DIPsSettings.gbitPoster);
		Slider bPostSldr = getSlider(2,8, DIPsSettings.bbitPoster);
		
		Label rPostLbl = getLabel(DIPsSettings.rbitPoster);
		Label gPostLbl = getLabel(DIPsSettings.gbitPoster);
		Label bPostLbl = getLabel(DIPsSettings.bbitPoster);
		
		box.getChildren().add( 
				new TitledPane("Posterize Settings", 
				getGrid( new Control [] {new Label("rbit :"), new Label("gbit :"), new Label("bbit :")},
						 new Control [] {rPostSldr, gPostSldr, bPostSldr}, 
						 new Control [] {rPostLbl, gPostLbl, bPostLbl}	) 
						) 
				);
		
		/* Border Settings */
		Slider borderWSldr = getSlider (0, 1000, DIPsSettings.borderWidth);
		Slider borderHSldr = getSlider (0, 1000, DIPsSettings.borderHeight);
	
		Label borderWLbl = getLabel(DIPsSettings.borderWidth);
		Label borderHLbl = getLabel(DIPsSettings.borderHeight);
		
		ColorPicker borderColorPick = getColorPicker( DIPsSettings.borderColor);
	
		box.getChildren().add( 
				new TitledPane("Border Settings", 
				getGrid( new Control [] {new Label("border Width :"), new Label("border Height :"), new Label ("border Color :")},
						 new Control [] {borderWSldr, borderHSldr, borderColorPick}, 
						 new Control [] {borderWLbl, borderHLbl}	) 
						) 
				);
		
		/* Color Filter Settings */
		ColorPicker targetCPick = getColorPicker( DIPsSettings.filterTarget);
		ColorPicker replacementCPick = getColorPicker( DIPsSettings.filterReplace);
		Slider filterThSldr = getSlider(0, 255, DIPsSettings.filterThreshold);
		
		Label filterThLbl = getLabel(DIPsSettings.filterThreshold);
		Button swapBtn = new Button("Swap");
		swapBtn.setOnAction(e -> { 
			Color temp = targetCPick.getValue();
			targetCPick.setValue(replacementCPick.getValue());
			replacementCPick.setValue(temp);
		}
		);
		
		box.getChildren().add( 
				new TitledPane("Color Filter Settings", 
				getGrid( new Control [] {new Label("Selected Color :"), new Label("Threshold :"), new Label ("Replacement Color :"), swapBtn},
						 new Control [] {targetCPick, filterThSldr, replacementCPick}, 
						 new Control [] { new Label("") , filterThLbl}	) 
						) 
				);
		
		/* Crop Settings */
		Slider cropXSldr = getSlider(0, 1000, DIPsSettings.cropXOffset);
		Slider cropYSldr = getSlider(0, 1000, DIPsSettings.cropYOffset);
		Slider cropWSldr = getSlider(0, 1000, DIPsSettings.cropWidth);
		Slider cropHSldr = getSlider(0, 1000, DIPsSettings.cropHeight);
		
		Label cropXLbl = getLabel( DIPsSettings.cropXOffset);
		Label cropYLbl = getLabel( DIPsSettings.cropYOffset);
		Label cropWLbl = getLabel( DIPsSettings.cropWidth);
		Label cropHLbl = getLabel( DIPsSettings.cropHeight);
		
		box.getChildren().add( 
				new TitledPane("Crop Settings", 
				getGrid( new Control [] {new Label("X Offset :"), new Label("Y Offset :"), new Label ("Crop Width :"), new Label ("Crop Height :")},
						 new Control [] {cropXSldr, cropYSldr, cropWSldr, cropHSldr}, 
						 new Control [] { cropXLbl, cropYLbl, cropWLbl, cropHLbl}	) 
						) 
				);
		
		/* Spotlight Settings */
		Slider spotlXSldr = getSlider(0, 1000, DIPsSettings.spotlightCenterX);
		Slider spotlYSldr = getSlider(0, 1000, DIPsSettings.spotlightCenterY);
		Slider spotlRadSldr = getSlider(1, 1000, DIPsSettings.spotlightRadius);
		
		Label spotlXLbl = getLabel( DIPsSettings.spotlightCenterX);
		Label spotlYLbl = getLabel( DIPsSettings.spotlightCenterY);
		Label spotlRadLbl = getLabel( DIPsSettings.spotlightRadius);
		
		box.getChildren().add( 
				new TitledPane("Spotlight Settings", 
				getGrid( new Control [] {new Label("Center X :"), new Label("Center Y :"), new Label ("Radius :") },
						 new Control [] {spotlXSldr, spotlYSldr, spotlRadSldr}, 
						 new Control [] { spotlXLbl, spotlYLbl, spotlRadLbl}	) 
						) 
				);
		
		/* Watermark Settings */
		Slider watermarkRatioSldr = getSlider(0, 3 , DIPsSettings.watermarkRatio);
		
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        
        
    	Button chooseFileBtn = new Button("Choose Watermark File.");
		chooseFileBtn.setOnAction(e-> 
		{
        	File imageFile = fileChooser.showOpenDialog(null);
        	if (imageFile != null) {
        		DIPsSettings.watermarkImage.setValue ( FileIO.getSingleton().loadImage(imageFile) );
        	}
        } );
		
		
		Label watermarkRatioLbl = getLabel( DIPsSettings.watermarkRatio);
		
		box.getChildren().add( 
				new TitledPane("Watermark Settings", 
				getGrid( new Control [] { new Label ("Watermark Ratio :"), chooseFileBtn},
						 new Control [] { watermarkRatioSldr }, 
						 new Control [] { watermarkRatioLbl}	) 
						) 
				);
		
		
		 for ( Node c : box.getChildren()) {
			 if (c instanceof TitledPane)
			 {
				 ((TitledPane)c).setExpanded(false);
			 }
		 }
		 
		 ScrollPane scrollPane = new ScrollPane (box);
		 
		 scrollPane.setFitToWidth(true);
		 scrollPane.setFitToHeight(true);
		 
	     this.setCenter(scrollPane);
	    
	  
	}

	private ColorPicker getColorPicker(ObjectProperty<Color>  property ) {
		ColorPicker colorPicker = new ColorPicker(property.getValue());
	    
	  	//	property.bindBidirectional(colorPicker.valueProperty());
	  	//  colorPicker.valueProperty().bindBidirectional(property);
	 		
	 	/*	colorPicker.valueProperty().addListener(new ChangeListener<Color>() {
	            @Override 
	            public void changed(ObservableValue ov, Color t, Color t1) {                
	                property.setValue(colorPicker.getValue());            
	              
	            }    
	        });*/
		
		Bindings.bindBidirectional(colorPicker.valueProperty(), property);
		
		return colorPicker;
	}
	private Label getLabel(IntegerProperty property) {
		Label label = new Label();
		label.textProperty().bindBidirectional(property, NumberFormat.getNumberInstance());
		return label;
	}
	
	private Label getLabel(DoubleProperty property) {
		Label label = new Label();
		label.textProperty().bindBidirectional(property, NumberFormat.getNumberInstance());
		return label;
	}

	private Slider getSlider(int min, int max, IntegerProperty property) {
		Slider slider = new Slider(min, max, property.get());
		slider.setShowTickLabels(false);
		slider.setShowTickMarks(false);
		slider.setBlockIncrement(1.0);
		
		slider.setTooltip(new Tooltip());
		slider.getTooltip().textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
		
		slider.valueProperty().bindBidirectional(property);
		
		slider.valueProperty().addListener((obs, oldval, newVal) -> 
		{	
			slider.setValue(newVal.intValue());
		});
		
		
		return slider;
	}
	
	private Slider getSlider(int min, int max, DoubleProperty property) {
		Slider slider = new Slider(min, max, property.get());
		slider.setShowTickLabels(false);
		slider.setShowTickMarks(false);
		slider.setBlockIncrement(0.001);
		
		slider.setTooltip(new Tooltip());
		slider.getTooltip().textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
		
		slider.valueProperty().bindBidirectional(property);
		
		
		return slider;
	}
	
	private GridPane getGrid(Control[] col0 , Control[] col1, Control[] col2) {
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5, 5, 5, 5));
		
		for ( int i=0 ; i< col0.length; i++) {
			grid.add(col0[i], 0, i);
		}
		for ( int i=0 ; i< col1.length; i++) {
			grid.add(col1[i], 1, i);
		}
		for ( int i=0 ; i< col2.length; i++) {
			grid.add(col2[i], 2, i);
		}
		return grid;
		
	}
	
}
