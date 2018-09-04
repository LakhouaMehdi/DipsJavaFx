// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import java.util.Random;
import javafx.scene.paint.Color;

public abstract class DIPs {
	
	/* age the image to make it look like old picture */
	public static void age(RGBImage image) {
	    
		short R, G, B;
		
		for(int x = 0; x < image.getWidth(); x++ ) {
			for(int y = 0; y < image.getHeight(); y++ ) {
				
				R = image.GetPixelR(x, y);
				G = image.GetPixelG(x, y);
				B = image.GetPixelB(x, y);
				image.SetPixelB(x, y, (short) (( R + G + B ) / 5) );
				
				B = image.GetPixelB(x, y);
	            image.SetPixelR(x, y, (short) ( B * 1.6 ) );
	            image.SetPixelG(x, y, (short) ( B * 1.6 ) ) ;
	            
			}
		}
		
	}
	
	
	public static void negative(RGBImage image) {

		short R, G, B;
		
		 for (int x = 0; x < image.getWidth(); x++) {
		    for (int y = 0; y < image.getHeight(); y++) {
		    	
		    		R = image.GetPixelR(x, y);
					G = image.GetPixelG(x, y);
					B = image.GetPixelB(x, y);
		    	
		            image.SetPixelR(x, y, (short) (Constants.MAX_PIXEL - R ) );
		            image.SetPixelG(x, y, (short) (Constants.MAX_PIXEL - G ) );
		            image.SetPixelB(x, y, (short) (Constants.MAX_PIXEL - B ) );
		        }
		    }
	}
	
	public static void blackAndWhite(RGBImage image) {
 
		 short R, G, B, newI;
		
		 for (int x = 0; x < image.getWidth(); x++) {
		        for (int y = 0; y < image.getHeight(); y++) {
		        	
		        	R = image.GetPixelR(x, y);
					G = image.GetPixelG(x, y);
					B = image.GetPixelB(x, y);
		        	
					newI = (short) ( (R + G + B)/ 3 );
					image.SetPixelB(x, y, newI);
					image.SetPixelR(x, y, newI);
					image.SetPixelG(x, y, newI);
		        }
		    }
	}
	
	public static void edge(RGBImage image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		short maxPixel = Constants.MAX_PIXEL;
		
		  int             x, y, a, b;
		  
		  	short[][] tmpR = new short[width][height];
		    short[][] tmpG = new short[width][height];
		    short[][] tmpB = new short[width][height];
		    
		    /* copy the image in the new arrays */
		    for (y = 0; y < height; y++){
		        for (x = 0; x < width; x++) {
		            tmpR[x][y] = image.GetPixelR(x, y);
		            tmpG[x][y] = image.GetPixelG(x, y);
		            tmpB[x][y] = image.GetPixelB(x, y);
		        }
		    }
		    
		    int sumR = 0;   /* sum of the intensity differences with neighbors */
		    int sumG = 0;
		    int sumB = 0;
		    
		    for (y = 1; y < height - 1; y++){
		        for (x = 1; x < width - 1; x++){
		        	
		        	for (a = -1; a <= 1; a++) {
		        		for (b = -1; b <= 1; b++){
		                    sumR += (tmpR[x][y] - tmpR[x+a][y+b]);
		                    sumG += (tmpG[x][y] - tmpG[x+a][y+b]);
		                    sumB += (tmpB[x][y] - tmpB[x+a][y+b]);
		                }
		            }
		            image.SetPixelR(x, y, (short) ((sumR > maxPixel) ? maxPixel: (sumR < 0) ? 0: sumR) );
		            image.SetPixelG(x, y, (short) ((sumG > maxPixel) ? maxPixel: (sumG < 0) ? 0: sumG) );
		            image.SetPixelB(x, y, (short) ((sumB > maxPixel) ? maxPixel: (sumB < 0) ? 0: sumB) );
		            sumR = sumG = sumB = 0;
		        }
		    }
		    /* set all four borders to 0 */
		    for (y = 0; y < height; y++) {
		    	image.SetPixelR(0, y, (short) 0);
		    	image.SetPixelG(0, y, (short) 0);
		    	image.SetPixelB(0, y, (short) 0);
		    	
		    	image.SetPixelR(width - 1, y, (short) 0);
		    	image.SetPixelG(width - 1, y, (short) 0);
		    	image.SetPixelB(width - 1, y, (short) 0);
		        
		    }
		    for (x = 0; x < width; x++) {
		    	image.SetPixelR(x, 0, (short) 0);
		    	image.SetPixelG(x, 0, (short) 0);
		    	image.SetPixelB(x, 0, (short) 0);
		    	
		    	image.SetPixelR(x, height - 1, (short) 0);
		    	image.SetPixelG(x, height - 1, (short) 0);
		    	image.SetPixelB(x, height - 1, (short) 0);
		    
		    }
		
	}
	
	public static void sharpen (RGBImage image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		short maxPixel = Constants.MAX_PIXEL;
		
		  int             x, y, a, b;
		  
		  	short[][] tmpR = new short[width][height];
		    short[][] tmpG = new short[width][height];
		    short[][] tmpB = new short[width][height];
		    
		    /* copy the image in the new arrays */
		    for (y = 0; y < height; y++){
		        for (x = 0; x < width; x++) {
		            tmpR[x][y] = image.GetPixelR(x, y);
		            tmpG[x][y] = image.GetPixelG(x, y);
		            tmpB[x][y] = image.GetPixelB(x, y);
		        }
		    }
		    
		    int sumR, sumG, sumB;
		    
		    for (y = 1; y < height - 1; y++){
		        for (x = 1; x < width - 1; x++){
		        	
		        	sumR = image.GetPixelR(x, y);
		        	sumG = image.GetPixelG(x, y);
		        	sumB = image.GetPixelB(x, y);
		        	
		        	for (a = -1; a <= 1; a++) {
		        		for (b = -1; b <= 1; b++){
		                    sumR += (tmpR[x][y] - tmpR[x+a][y+b]);
		                    sumG += (tmpG[x][y] - tmpG[x+a][y+b]);
		                    sumB += (tmpB[x][y] - tmpB[x+a][y+b]);
		                }
		            }
		            image.SetPixelR(x, y, (short) ((sumR > maxPixel) ? maxPixel: (sumR < 0) ? 0: sumR) );
		            image.SetPixelG(x, y, (short) ((sumG > maxPixel) ? maxPixel: (sumG < 0) ? 0: sumG) );
		            image.SetPixelB(x, y, (short) ((sumB > maxPixel) ? maxPixel: (sumB < 0) ? 0: sumB) );
		            sumR = sumG = sumB = 0;
		        }
		    }
		
	}
	
	public static void hFlip(RGBImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		short temp;
		    
		for (int x = 0; x < width/2; x ++) {
		        for (int y = 0; y < height; y ++) {

		            temp = image.GetPixelR(width-1-x, y);            
		            image.SetPixelR(width-1-x, y, image.GetPixelR(x, y) );
		            image.SetPixelR(x, y, temp);
		 
		            temp = image.GetPixelG(width-1-x, y);            
		            image.SetPixelG(width-1-x, y, image.GetPixelG(x, y) );
		            image.SetPixelG(x, y, temp);
		 
		            temp = image.GetPixelB(width-1-x, y);            
		            image.SetPixelB(width-1-x, y, image.GetPixelB(x, y) );
		            image.SetPixelB(x, y, temp);
		       }
		}
	}
	
	public static void vFlip(RGBImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		short temp;
		
		for (int y = 0; y < height/2; y ++) {
			for (int x = 0; x < width; x ++) {

				temp = image.GetPixelR(x, height - 1 - y);            
	            image.SetPixelR(x, height - 1 - y, image.GetPixelR(x, y) );
	            image.SetPixelR(x, y, temp);
				
	            temp = image.GetPixelG(x, height - 1 - y);            
	            image.SetPixelG(x, height - 1 - y, image.GetPixelG(x, y) );
	            image.SetPixelG(x, y, temp);
	            
	            temp = image.GetPixelB(x, height - 1 - y);            
	            image.SetPixelB(x, height - 1 - y, image.GetPixelB(x, y) );
	            image.SetPixelB(x, y, temp);
			}
   	
		}
}
	
	public static void vMirror(RGBImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		for (int y = 0; y < height / 2; y ++) {
			for (int x = 0; x < width; x ++) {
		        
				image.SetPixelR(x, height - 1 - y, image.GetPixelR(x, y) );
				image.SetPixelG(x, height - 1 - y, image.GetPixelG(x, y) );
				image.SetPixelB(x, height - 1 - y, image.GetPixelB(x, y) );
			     
			}
		}
	}
	
	public static void shuffle(RGBImage image, int widthDiv, int heightDiv) {
		
	    int block_cnt = heightDiv * widthDiv;
	    int block_width = image.getWidth()/widthDiv;
	    int block_height = image.getHeight()/heightDiv;
	    int block[][] = new int [widthDiv][heightDiv];
	  
		Random rand = new Random();  

	    /* Initialize block markers to not done (-1) */
	    for (int i = 0; i < widthDiv; i++) {
	        for (int j = 0; j< heightDiv; j++) {
	            block[i][j] = -1;
	        }
	    }

	    while (block_cnt > 0) {
	        /* Generate a random pair of blocks */
	        int dest_height = rand.nextInt(heightDiv);
	        int dest_width = rand.nextInt(widthDiv);
	        int src_height = rand.nextInt(heightDiv);
	        int src_width = rand.nextInt(widthDiv);

	        /* Check if these blocks are already swaped, if not swap blocks */
	        if ((block[dest_width][dest_height] == -1) && (block[src_width][src_height] == -1)) {
	            /* Swap blocks */
	            for (int i = 0; i < block_height; i++) {
	                /* Init src and dest height offset */
	                int h_dest = ((dest_height * block_height) + i) % image.getHeight();
	                int h_src  = ((src_height * block_height) + i) % image.getHeight(); 
	                for (int j = 0; j < block_width; j++) {
	                    short temp;
	                    /* Init src and dest width offset */
	                    int w_src  = ((src_width * block_width) + j) % image.getWidth(); 
	                    int w_dest = ((dest_width * block_width) + j) % image.getWidth();

	                    temp = image.GetPixelR(w_dest, h_dest);
	                    image.SetPixelR(w_dest, h_dest, image.GetPixelR(w_src, h_src) );
	                    image.SetPixelR(w_src, h_src, temp);
	                    
	                    temp = image.GetPixelG(w_dest, h_dest);
	                    image.SetPixelG(w_dest, h_dest, image.GetPixelG(w_src, h_src) );
	                    image.SetPixelG(w_src, h_src, temp);

	                    temp = image.GetPixelB(w_dest, h_dest);
	                    image.SetPixelB(w_dest, h_dest, image.GetPixelB(w_src, h_src) );
	                    image.SetPixelB(w_src, h_src, temp);
	                }
	            }
	            /* Set marker as done */
	            block[dest_width][dest_height] = 1;
	            block[src_width][src_height] = 1;
	            /* Decrease block count */
	            block_cnt -= 2; /* Two blocks are swapped */
	        }
	        
	    }

	}
	
	public static void colorFilter(RGBImage image, Color targetColor, Color replaceColor, int threshold) {
		

	    for (int x = 0; x < image.getWidth() ; x++) {
		for (int y = 0; y < image.getHeight() ; y++) {
	 
		    if ( ( Math.abs( image.GetPixelR(x,y)     - targetColor.getRed()*Constants.MAX_PIXEL ) <= threshold )
	              && ( Math.abs( image.GetPixelG(x,y) - targetColor.getGreen()*Constants.MAX_PIXEL ) <= threshold )
	              && ( Math.abs( image.GetPixelB(x,y) - targetColor.getBlue()*Constants.MAX_PIXEL ) <= threshold ) ) {
	                
		    		image.SetPixelR(x,y,  (short) (replaceColor.getRed()  *Constants.MAX_PIXEL));
	                image.SetPixelG(x,y,  (short) (replaceColor.getGreen()*Constants.MAX_PIXEL));
	                image.SetPixelB(x,y,  (short) (replaceColor.getBlue() *Constants.MAX_PIXEL));
			}
		}
	    }
		
		
	}
	
	public static void crop ( RGBImage image, int xOff, int yOff, int w, int h)  throws Exception {
		
		    int originalW = image.getWidth();
		    int originalH = image.getHeight();
		   
		    if (xOff<0 || yOff<0 || xOff > originalW-1 || yOff > originalH-1 ) {
				throw new Exception();
			}
		    
		    int newW = (xOff+w < originalW) ? w : originalW-xOff ; 
		    int newH = (yOff+h < originalH) ? h : originalH-yOff ; 

		    RGBImage croppedImage = new RGBImage(newW,newH);
		  
		   for (int i=0; i<newW ; i++){
		    	for(int j=0; j<newH ; j++){
		           croppedImage.SetPixelR(i,j, image.GetPixelR(xOff+i, yOff+j));
		           croppedImage.SetPixelG(i,j, image.GetPixelG(xOff+i, yOff+j)); 
		           croppedImage.SetPixelB(i,j, image.GetPixelB(xOff+i, yOff+j));
		        }
		    }
		    
		   image.copy(croppedImage);
		
	}
	
	
	public static void addBorder (RGBImage image, int borderWidth, int borderHeight, Color borderColor) {
		  
			short newR = (short) (borderColor.getRed()   * Constants.MAX_PIXEL);
			short newG = (short) (borderColor.getGreen() * Constants.MAX_PIXEL);
			short newB = (short) (borderColor.getBlue()  * Constants.MAX_PIXEL);
			
		    // top and bottom border
		    for(int x=0; x<image.getWidth(); x++){
		 	for(int y=0; y<borderHeight && y<image.getHeight()/2; y++) {
			  image.SetPixelR(x, 	                   y, newR);
			  image.SetPixelR(x, image.getHeight() -1 -y, newR);

			  image.SetPixelG(x, 	                   y, newG);
			  image.SetPixelG(x, image.getHeight() -1 -y, newG);
			  
			  image.SetPixelB(x, 	                   y, newB);
			  image.SetPixelB(x, image.getHeight() -1 -y, newB);
			}
		}

		    //right and left border
		    for(int y=0; y<image.getHeight(); y++) {        //or border_height to HEIGHT-1-border_height
			for (int x=0; x<borderWidth && x<image.getWidth()/2; x++){

			  image.SetPixelR(					   x, y, newR);
			  image.SetPixelR(image.getWidth() -1 -x, y, newR);

			  image.SetPixelG(					   x, y, newG);
			  image.SetPixelG(image.getWidth() -1 -x, y, newG);
			  
			  image.SetPixelB(					   x, y, newB);
			  image.SetPixelB(image.getWidth() -1 -x, y, newB);
			}
		}
		
		
		
	}
	
	public static void posterize (RGBImage image, int rBits, int gBits, int bBits) {

	    int x,y,i;
	    short newR,newG,newB;

	    for (x=0 ; x< image.getWidth() ; x++) {
	        for(y=0; y< image.getHeight();y++) {
		    newR = (short) (image.GetPixelR(x,y) >> rBits << rBits);
		    newG = (short) (image.GetPixelG(x,y) >> gBits << gBits);
		    newB = (short) (image.GetPixelB(x,y) >> bBits << bBits);
					
		    for (i=0; i < rBits-1; i++ ) {
	                 newR |= (1<< i) ;
	            }

		    for (i=0 ; i < gBits-1; i++){
			 newG |= (1<< i) ;
		    }

		    for (i=0 ; i < bBits-1; i++) {
			 newB |= (1<< i) ;
	            }

		    image.SetPixelR(x, y, newR);
	        image.SetPixelG(x, y, newG);
		    image.SetPixelB(x, y, newB);
		}
	    }
		
	}
	
	public static void spotlight (RGBImage image, int centerX, int centerY, int radius) 
	{
		int width  = image.getWidth();
		int height = image.getHeight();
		
		for(int x=0; x< width ; x++)
		{
			for(int y = 0; y< height;y++)
			{
				if( (centerY -y) * (centerY - y)  + (centerX -x) * (centerX - x) > radius * radius )
				{
					image.SetPixelR(x,y,(short) 0);
					image.SetPixelG(x,y,(short) 0);
					image.SetPixelB(x,y,(short) 0);
				}
			}
		}
			
	}
	
	public static void watermark (RGBImage image, double watermarkRatio, RGBImage watermark) throws Exception {
		
		if (watermark == null) {
			throw new Exception();
		}
		
		    short newR,newG,newB;

		    int w_width = watermark.getWidth();
		    int w_height = watermark.getHeight();

		 
		    for(int x=0; x < image.getWidth(); x++)
		    {
		        for(int y=0; y < image.getHeight(); y++)
		        {
			    if(    watermark.GetPixelR( x % w_width, y % w_height ) != Constants.MAX_PIXEL 
			        && watermark.GetPixelG( x % w_width, y % w_height ) != Constants.MAX_PIXEL 
		            && watermark.GetPixelB( x % w_width, y % w_height ) != Constants.MAX_PIXEL )
			    {
		               newR = (short) (image.GetPixelR(x,y)*watermarkRatio);
		               newG = (short) (image.GetPixelG(x,y)*watermarkRatio);
		               newB = (short) (image.GetPixelB(x,y)*watermarkRatio);

		               image.SetPixelR(x,y, (newR > Constants.MAX_PIXEL) ? Constants.MAX_PIXEL : newR );
		               image.SetPixelG(x,y, (newG > Constants.MAX_PIXEL) ? Constants.MAX_PIXEL : newG );
		               image.SetPixelB(x,y, (newB > Constants.MAX_PIXEL) ? Constants.MAX_PIXEL : newB );
		            } 
		        }
		    }
			
	}
	
	public static void noise (RGBImage image, int noisePercentage){

		Random rand = new Random(); 
		
	    final int width = image.getWidth(), height = image.getHeight();
	    int count = noisePercentage * width * height /100;
	    int x, y;

	    while ( count > 0 )
	    {

	        x = rand.nextInt(width);
	        y= rand.nextInt(height);

            image.SetPixelR(x,y, Constants.MAX_PIXEL);
	        image.SetPixelG(x,y, Constants.MAX_PIXEL);
	        image.SetPixelB(x,y, Constants.MAX_PIXEL);
	        
	        count--;
	    }
	    
	}
	
	
	public static void resize (RGBImage image, int percentage) throws Exception {
		/* resize the image */
		
		    if(percentage == 100)
		    {
		        return;
		    }
		    else 
		    {
			//resize the image
		        int newWidth = (int) (image.getWidth() * (percentage/100.00));
		        int newHeight = (int) (image.getHeight() * (percentage/100.00));
		
		        if ( newWidth == 0 || newHeight == 0) 
		        {
		        	throw new Exception("Image is too small to resize.");
		        }
		        
		        
			RGBImage resizedImage = new RGBImage(newWidth, newHeight);  

			if (percentage > 100) 	      //First case: Making the image bigger.
		        {
		  	    int x, y, oldX, oldY;
			    for(x=0; x<newWidth ; x++)
			    {
			        for(y=0; y<newHeight ; y++)
				{  
			 	    oldX = (int) (x*100.00/percentage); 
				    oldY = (int) (y*100.00/percentage); 
			
				    resizedImage.SetPixelR(x,y, image.GetPixelR(oldX, oldY) );
				    resizedImage.SetPixelG(x,y, image.GetPixelG(oldX, oldY) );
				    resizedImage.SetPixelB(x,y, image.GetPixelB(oldX, oldY) );
				}
		            }
			    
			}	
			else if (percentage < 100)    //Second case: Making the image smaller.
		        {   
				
				
			    int x,y,i,j,oldX1, oldY1, oldX2, oldY2;
			    int sumR, sumG, sumB, count=0;
			    for(x=0; x<newWidth ; x++)
			    {
		                for(y=0; y<newHeight ; y++)
				{
			            //determine the pixels to average.
				    oldX1 = (int) (x / (percentage / 100.00));
				    oldY1 = (int) (y / (percentage / 100.00));
				    oldX2 = (int) ((x+1) / (percentage / 100.00));
				    oldY2 = (int) ((y+1) / (percentage / 100.00));
				    
				    //sum the pixels from the old image.
				    sumR = sumG = sumB = count =0;

				    for(i=oldX1 ; i<oldX2 ; i++)
				    {
				        for(j=oldY1 ; j<oldY2 ; j++)
				        {   count ++;
					    sumR += image.GetPixelR(i,j); 
					    sumG += image.GetPixelG(i,j);
					    sumB += image.GetPixelB(i,j);   
			                }
				    }
				    
		                    //assign the average value to the new image.
				    resizedImage.SetPixelR(x,y, (short) (sumR/count));
				    resizedImage.SetPixelG(x,y, (short) (sumG/count));
				    resizedImage.SetPixelB(x,y, (short) (sumB/count));
				}
			    }
			}
			
		        image.copy(resizedImage);    
		    }
		
	}
	
	public static void brightnessAndContrast(RGBImage image, int brightness, int contrast)
	{

	    double factor = (double) (259 * (contrast+255)) / (double) (255 * (259 - contrast));
	    short newR, newG, newB;
	  
	    for(int x=0; x< image.getWidth() ; x++) {
	        for(int y=0; y< image.getHeight()  ; y++) {
	        
	        newR =  (short) (factor * (image.GetPixelR(x,y) - 128) + 128  + brightness) ;
	        newG =  (short) (factor * (image.GetPixelG(x,y) - 128) + 128  + brightness) ;
	        newB =  (short) (factor * (image.GetPixelB(x,y) - 128) + 128  + brightness) ;
	        
	        image.SetPixelR(x,y, (newR < 0) ? 0 : (newR > 255) ? 255 : newR );
	        image.SetPixelG(x,y, (newG < 0) ? 0 : (newG > 255) ? 255 : newG );
	        image.SetPixelB(x,y, (newB < 0) ? 0 : (newB > 255) ? 255 : newB );
	        }
	    }

	}
	
	
	public static void motionBlur (RGBImage image, int blurAmount) {
		
	    int x,y,i;
	    short temp_r , temp_g , temp_b;

	    for (x = 0; x < image.getWidth() ; x++) 
	    {
	        for (y = 0; y < image.getHeight() ; y++) 
	        {
		    temp_r = (short) (image.GetPixelR(x,y)/2);
		    temp_g = (short) (image.GetPixelG(x,y)/2);
		    temp_b = (short) (image.GetPixelB(x,y)/2);
				
		    for (i = 1; i<= blurAmount ; i++) {
		        if ((x+i) < image.getWidth() ) 
	                {
		  	    temp_r = (short) (temp_r + image.GetPixelR(x+i, y) * 0.5/blurAmount);
			    temp_g = (short) (temp_g + image.GetPixelG(x+i , y) * 0.5/blurAmount);
		  	    temp_b = (short) (temp_b + image.GetPixelB(x+i, y)  * 0.5/blurAmount);
			}
		    }
				
		    image.SetPixelR(x,y,temp_r);
		    image.SetPixelB(x,y,temp_b);
		    image.SetPixelG(x,y,temp_g);
		}
	    }
		
	}
}
