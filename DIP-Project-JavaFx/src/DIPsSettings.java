// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public abstract class DIPsSettings {

	public static IntegerProperty brightness = new SimpleIntegerProperty(129);
	public static IntegerProperty contrast = new SimpleIntegerProperty(156);
	
	public static IntegerProperty noisePercentage = new SimpleIntegerProperty(20);
	public static IntegerProperty blurAmount = new SimpleIntegerProperty(10);
	
	public static IntegerProperty resizePercentage = new SimpleIntegerProperty(200);
	
	public static IntegerProperty shuffleHeightDiv = new SimpleIntegerProperty(4);
	public static IntegerProperty shuffleWidthDiv = new SimpleIntegerProperty(4);
	
	public static IntegerProperty rbitPoster = new SimpleIntegerProperty(2);
	public static IntegerProperty gbitPoster = new SimpleIntegerProperty(2);
	public static IntegerProperty bbitPoster = new SimpleIntegerProperty(2);
	
	public static IntegerProperty borderWidth = new SimpleIntegerProperty(64);
	public static IntegerProperty borderHeight = new SimpleIntegerProperty(32);
	public static ObjectProperty<Color> borderColor = new SimpleObjectProperty<Color>(Color.AQUA);
	
	public static ObjectProperty<Color> filterTarget = new SimpleObjectProperty<Color>(Color.DEEPPINK);
	public static ObjectProperty<Color> filterReplace = new SimpleObjectProperty<Color>(Color.VIOLET);
	public static IntegerProperty filterThreshold = new SimpleIntegerProperty(50);
	
	public static IntegerProperty cropXOffset= new SimpleIntegerProperty(0);
	public static IntegerProperty cropYOffset= new SimpleIntegerProperty(0);
	public static IntegerProperty cropWidth= new SimpleIntegerProperty(200);
	public static IntegerProperty cropHeight= new SimpleIntegerProperty(200);
	
	public static IntegerProperty spotlightCenterX = new SimpleIntegerProperty(0);
	public static IntegerProperty spotlightCenterY = new SimpleIntegerProperty(0);
	public static IntegerProperty spotlightRadius = new SimpleIntegerProperty(20);
	
	
	public static DoubleProperty watermarkRatio = new SimpleDoubleProperty(1.45);
	public static ObjectProperty<RGBImage> watermarkImage = new SimpleObjectProperty<RGBImage>(null);
	
	
	
}
