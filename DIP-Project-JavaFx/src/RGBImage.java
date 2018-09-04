// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class RGBImage {

	private SimpleIntegerProperty width = new SimpleIntegerProperty(this, "width");
	private SimpleIntegerProperty height = new SimpleIntegerProperty(this, "height");
	
	private short[][] R ;
	private short[][] G ;
	private short[][] B ;
	
	public RGBImage(int w, int h) {
		
		width.set(w);
		height.set(h);
	setR(new short[w][h]);
	setG(new short[w][h]);
	setB(new short[w][h]);
	}
	
	public short GetPixelR(int x, int y) {
    	return R[x][y] ; 
    }
    
    public short GetPixelG(int x, int y) {
    	return G[x][y] ;
    }
	
    public short GetPixelB(int x, int y) {
    	return B[x][y] ;
    }
	
    public void SetPixelR(int x, int y, short value) {
    	this.R[x][y] = value; 
    }
    
    public void SetPixelG(int x, int y, short value) {
    	this.G[x][y] = value;
    }
	
    public void SetPixelB(int x, int y, short value) {
    	this.B[x][y] = value;
    }
    
	public int getWidth() {
		return width.get();
	}
	
	public void setWidth(int w) {
		width.set(w);
	}
	
	public int getHeight() {
		return height.get();
	}
	
	public void setHeight(int h) {
		height.set(h);
	}
	
	public short[][] getR() {
		return R;
	}

	public void setR(short[][] r) {
		R = r;
	}

	public short[][] getG() {
		return G;
	}

	public void setG(short[][] g) {
		G = g;
	}

	public short[][] getB() {
		return B;
	}

	public void setB(short[][] b) {
		B = b;
	}


	public synchronized Image toImage() {
		
		BufferedImage bI;
		Color c;
		
		try {
			bI = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);	//check about alpha
	
			for (int x =0 ; x < getWidth() ; x++) {
				for (int y =0 ; y < getHeight() ; y++) {
					c = new Color(R[x][y], G[x][y], B[x][y]);
					bI.setRGB(x, y, c.getRGB());
				}
			}
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, 
										  "Error updating the image!\n" + ex.getMessage() +"\n Please load a new image.", 
										  "Error",  
										  JOptionPane.ERROR_MESSAGE );
			return null;
		}
		return SwingFXUtils.toFXImage(bI,null) ;
		
	}



	public void copy(final RGBImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		setWidth( w );
		setHeight( h );
		
		setR(new short[w][h]);
		setG(new short[w][h]);
		setB(new short[w][h]);
		
		for (int x =0 ; x < w ; x++) {
			for (int y =0 ; y < h ; y++) {
				this.SetPixelR(x, y, image.GetPixelR(x, y));
				this.SetPixelG(x, y, image.GetPixelG(x, y));
				this.SetPixelB(x, y, image.GetPixelB(x, y));
				
			}
		}
	}

	public IntegerProperty getWidthProperty() {
		return width;
	}

	public IntegerProperty getHeightProperty() {
		return height;
	}
	
}