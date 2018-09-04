// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

public abstract class Constants {
	

	public final static String [] DIPS_NAMES = new String[]
			{"Age", "Black And White" , "Negative", "Edge", "Horizontal Flip", "Vertical Flip", "Vertical Mirror",
				  "Sharpen", "Noise", "Motion Blur", "Resize", "Spotlight", "Brighness And Contrast", "Crop", "Shuffle", 
				  "Posterize", "Border", "Color Filter", "Watermark"
			};

	public final static short MAX_PIXEL = 255;
	
	public final static int INITIAL_FRAME_WIDTH = 1200;
	public final static int INITIAL_FRAME_HEIGHT = 900;
	
	public final static int MINIMUM_FRAME_WIDTH = 700;
	public final static int MINIMUM_FRAME_HEIGHT = 600;
	
	public final static String SIZE_DISPLAY_FORMAT = "~Width={0}, Height={1}~";
	
	public final static int SETTINGS_MENU_MIN_WIDTH = 330;
	public static final double SETTINGS_MENU_MAX_WIDTH = 500;
	public static final double SETTINGS_MENU_MIN_HEIGHT = 0;
	public static final double SETTINGS_MENU_MAX_HEIGHT = 700;
	
	public final static int VERTICAL_MENU_MIN_WIDTH = 150;
	public static final double VERTICAL_MENU_MAX_WIDTH = Double.MAX_VALUE;
	public static final double VERTICAL_MENU_MIN_HEIGHT = 0;
	public static final double VERTICAL_MENU_MAX_HEIGHT = 700;

}