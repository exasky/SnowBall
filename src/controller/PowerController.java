package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class PowerController {
	
	private static Map<Color, BufferedImage> ballImageMap;
	
	static {
		ballImageMap = new HashMap<Color, BufferedImage>();
		
		try {
			ballImageMap.put(Color.blue, ImageIO.read(PowerController.class.getClass().getResource("/BlueSnowBall.png")));
			ballImageMap.put(Color.white, ImageIO.read(PowerController.class.getResource("/WhiteSnowBall.png")));
			ballImageMap.put(Color.green, ImageIO.read(PowerController.class.getResource("/GreenSnowBall.png")));
			ballImageMap.put(Color.red, ImageIO.read(PowerController.class.getResource("/RedSnowBall.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getBallImage(Color ballColor){
		return ballImageMap.get(ballColor);
	}
}
