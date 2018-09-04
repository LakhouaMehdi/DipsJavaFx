// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/* This class loads and saves the images in jpg format */
public class FileIO {

	private static FileIO fileIO;		/* singleton */
	
	private FileIO() {
	}
	
	/*returns image if image loaded successfully. returns null if not*/
	public synchronized RGBImage loadImage(String path) {
		
		int width, height;
		short[][] R ;
		short[][] G ;
		short[][] B ;
		short[][] alpha ;
		
		BufferedImage bI;
		Color c;
		File iFile;
		
		
		try {
			if (path.equals("") || path == null   ) { 
				throw new Exception(); 
				}
			
			iFile = new File(path + ".jpg");   //path entered without the extension
			bI = ImageIO.read(iFile);
			
			width = bI.getWidth();    		   //get the actual width of the image
			height = bI.getHeight();		   //get the actual height of the image
			
			R = new short[width][height];
			G = new short[width][height];
			B = new short[width][height];
			alpha = new short[width][height];
			
			for (int x =0 ; x < width ; x++) {
				for (int y =0 ; y < height ; y++) {
				c = new Color(bI.getRGB(x, y), true);		//alpha channel is conserved
				R[x][y] = (short) c.getRed();
				G[x][y] = (short) c.getGreen();
				B[x][y] = (short) c.getBlue();
				alpha[x][y] = (short) c.getAlpha();
				}
			}
			
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot load image \"" + path + "\"!\nOperation Aborted!", "Error", JOptionPane.ERROR_MESSAGE );
			   return null ;
			}
		
		//image is loaded only if everything is read properly.
		RGBImage image = new RGBImage(width, height);
		
		image.setR( R );
		image.setG( G );
		image.setB( B );
		
		return image;
	}
	
	
	/*returns image if image loaded successfully. returns null if not*/
	public synchronized RGBImage loadImage(File iFile) {
		
		int width, height;
		short[][] R ;
		short[][] G ;
		short[][] B ;
		short[][] alpha ;
		
		BufferedImage bI;
		Color c;
		
		
		try {
			
			bI = ImageIO.read(iFile);
			
			width = bI.getWidth();    		   //get the actual width of the image
			height = bI.getHeight();		   //get the actual height of the image
			
			R = new short[width][height];
			G = new short[width][height];
			B = new short[width][height];
			alpha = new short[width][height];
			
			for (int x =0 ; x < width ; x++) {
				for (int y =0 ; y < height ; y++) {
				c = new Color(bI.getRGB(x, y), true);		//alpha channel is conserved
				R[x][y] = (short) c.getRed();
				G[x][y] = (short) c.getGreen();
				B[x][y] = (short) c.getBlue();
				alpha[x][y] = (short) c.getAlpha();
				}
			}
			
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot load image \"" + iFile.getAbsolutePath() + "\"!\nOperation Aborted!", "Error", JOptionPane.ERROR_MESSAGE );
			   return null ;
			}
		
		//image is loaded only if everything is read properly.
		RGBImage image = new RGBImage(width, height);
		
		image.setR( R );
		image.setG( G );
		image.setB( B );
		
		return image;
	}
	
	/*returns 0 if image loaded successfully. returns -1 if not*/
	public synchronized int saveImage(String path, final RGBImage image) {
		
		BufferedImage bI;
		Color c;
		File oFile;
		
		int width = image.getWidth();
		int height = image.getHeight();
		short[][] R = image.getR();
		short[][] G = image.getG();
		short[][] B = image.getB();
		
		try {
			
			if (path == null || path.equals("")) { throw new Exception();}
			
			bI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);	//check about alpha
			
			for (int x =0 ; x < width ; x++) {
				for (int y =0 ; y < height ; y++) {
				c = new Color(R[x][y], G[x][y], B[x][y]);
				bI.setRGB(x, y, c.getRGB());
				
				}
			}
			
			oFile = new File(path + ".jpg");
			ImageIO.write(bI, "jpg", oFile  );
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot write file \"" + path + "!\n", "Error",  JOptionPane.ERROR_MESSAGE );
			return -1;
		}
		
		return 0;
	}
	
	
	/*returns 0 if image loaded successfully. returns -1 if not*/
	public synchronized int saveImage(File oFile, final RGBImage image) {
		
		BufferedImage bI;
		Color c;
		
		int width = image.getWidth();
		int height = image.getHeight();
		short[][] R = image.getR();
		short[][] G = image.getG();
		short[][] B = image.getB();;
		
		try {
			
			bI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);	//check about alpha
			
			for (int x =0 ; x < width ; x++) {
				for (int y =0 ; y < height ; y++) {
				c = new Color(R[x][y], G[x][y], B[x][y]);
				bI.setRGB(x, y, c.getRGB());
				
				}
			}
			
			ImageIO.write(bI, "jpg", oFile  );
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Cannot write file \"" + oFile.getAbsolutePath() + "!\n", "Error",  JOptionPane.ERROR_MESSAGE );
			return -1;
		}
		
		return 0;
	}
	
	public static synchronized FileIO getSingleton() {
		if (fileIO == null) {
			fileIO = new FileIO();
		}	
		return fileIO;
	}
	
	
	
	
	
}
