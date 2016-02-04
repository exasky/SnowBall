package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class PowerController {
	
	private static Map<Color, BufferedImage> actionlessBallImageMap;
	private static Map<Color, BufferedImage> poweredBallImageMap;
	
	static {
		actionlessBallImageMap = new HashMap<Color, BufferedImage>();
		poweredBallImageMap = new HashMap<Color, BufferedImage>();
		
		try {
			actionlessBallImageMap.put(Color.white, ImageIO.read(PowerController.class.getResource("/WhiteSnowBall.png")));
			actionlessBallImageMap.put(Color.green, ImageIO.read(PowerController.class.getResource("/GreenSnowBall.png")));
			
			poweredBallImageMap.put(Color.blue, ImageIO.read(PowerController.class.getClass().getResource("/BlueSnowBall.png")));
			poweredBallImageMap.put(Color.red, ImageIO.read(PowerController.class.getResource("/RedSnowBall.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getBallImage(Color ballColor){
		return (actionlessBallImageMap.get(ballColor) != null) ? actionlessBallImageMap.get(ballColor) : poweredBallImageMap.get(ballColor);
	}
	
	public static Collection<BufferedImage> getPowerImages(){
		return poweredBallImageMap.values();
	}
	
	public static void switchPower(int wheelRotation){
		if (wheelRotation == 1)	//Wheel down
			GameController.getPlayer().getPowerList().switchToNextPower();
		else					//Wheel up
			GameController.getPlayer().getPowerList().switchToPreviousPower();
	}
}
