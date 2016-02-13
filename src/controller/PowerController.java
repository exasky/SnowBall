package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import model.powers.PowerID;
import model.powers.PowerList;
import model.powers.impl.OneShotPower;

public class PowerController {

	private static Map<PowerID, BufferedImage> actionlessBallImageMap;
	private static Map<PowerID, BufferedImage> poweredBallImageMap;

	public static PowerList powerList;

	static {
		actionlessBallImageMap = new HashMap<PowerID, BufferedImage>();
		poweredBallImageMap = new HashMap<PowerID, BufferedImage>();

		try {
			actionlessBallImageMap.put(PowerID.NONE, ImageIO.read(PowerController.class.getResource("/WhiteSnowBall.png")));
			actionlessBallImageMap.put(PowerID.ExtraLife, ImageIO.read(PowerController.class.getResource("/GreenSnowBall.png")));

			poweredBallImageMap.put(PowerID.OneShot,
					ImageIO.read(PowerController.class.getClass().getResource("/BlueSnowBall.png")));
			poweredBallImageMap.put(PowerID.Shield, ImageIO.read(PowerController.class.getResource("/RedSnowBall.png")));

			OneShotPower.oneShotImage = ImageIO.read(PowerController.class.getResource("/OneShot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		powerList = GameController.getPlayer().getPowerList();
	}

	/**
	 * 
	 * @param powerID
	 *            The id of the power.
	 * @return The BufferedImage linked to the powerID
	 */
	public static BufferedImage getBallImage(PowerID powerID) {
		return (actionlessBallImageMap.get(powerID) != null) ? actionlessBallImageMap.get(powerID) : poweredBallImageMap
				.get(powerID);
	}

	/**
	 * 
	 * @return A list of images which refers to powers that have a click action.
	 */
	public static Collection<BufferedImage> getPoweredImages() {
		return poweredBallImageMap.values();
	}

	/**
	 * Switch to the next/previous power according to the wheelRotation.<br/>
	 * If wheel down, the switch to the next power. otherwise, switch to the
	 * previous one.
	 * 
	 * @param wheelRotation
	 */
	public static void switchPower(int wheelRotation) {
		if (wheelRotation == 1) // Wheel down
			powerList.switchToNextPower();
		else
			// Wheel up
			powerList.switchToPreviousPower();
	}

	public static void resetPower() {
		powerList.reset();
	}
}
