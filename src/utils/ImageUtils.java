package utils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.powers.Power;

public class ImageUtils {
	
	public static void createNewBallImage(){
		try {
			BufferedImage image = ImageIO.read(Power.class.getResource("/SnowBall.png.orig"));
			
			for (int xx = 0; xx < image.getWidth(); xx++) {
	            for (int yy = 0; yy < image.getHeight(); yy++) {
	                int[] pixels = image.getRaster().getPixel(xx, yy, (int[]) null);
					pixels[0] = pixels[0] + pixels[0] / 3;
					pixels[1] = pixels[1] + pixels[1] / 3;
					pixels[2] = 255;
	                image.getRaster().setPixel(xx, yy, pixels);
	            }
	        }
			
			File outputfile = new File("./Test.png");
		    ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void truncateImage(){
		try {
			BufferedImage image = ImageIO.read(Power.class.getResource("/GreenSnowBall.png"));
			BufferedImage subimage = image.getSubimage(2, 2, 12, 12);

			File outputfile = new File("./Out.png");
		    ImageIO.write(subimage, "png", outputfile);
		} catch(IOException e) {e.printStackTrace();}
	
	}

	public static void main(String args[]){
		truncateImage();
	}
}