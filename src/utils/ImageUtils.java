package utils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Power;

public class ImageUtils {
	
	public static void createNewBallImage(){
		try {
			BufferedImage image = ImageIO.read(Power.class.getResource("/Snowball.png"));
			
			for (int xx = 0; xx < image.getWidth(); xx++) {
	            for (int yy = 0; yy < image.getHeight(); yy++) {
	                int[] pixels = image.getRaster().getPixel(xx, yy, (int[]) null);
	                pixels[0] = 255;
	                image.getRaster().setPixel(xx, yy, pixels);
	            }
	        }
			
			File outputfile = new File("./RedSnowBall.png");
		    ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void truncateImage(){
		try {
			BufferedImage image = ImageIO.read(Power.class.getResource("/WhiteSnowBall.png"));
			BufferedImage subimage = image.getSubimage(2, 2, 12, 12);

			File outputfile = new File("./WhiteSnowBall.png");
		    ImageIO.write(subimage, "png", outputfile);
		} catch(IOException e) {e.printStackTrace();}
	
	}

	public static void main(String args[]){
		truncateImage();
	}
}