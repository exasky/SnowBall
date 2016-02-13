package model.powers.impl;

import java.awt.image.BufferedImage;

import model.powers.Power;
import model.powers.PowerID;
import controller.GameController;

/**
 * Fire a bullet that annihilate other snow balls (powered included)
 * 
 * @author Simar Jérémy
 *
 */
public class OneShotPower extends Power {
	
	public static BufferedImage oneShotImage;

	public int from_x;
	public int y;

	public OneShotPower() {
		super(PowerID.OneShot);
	}

	@Override
	public void onClickAction() {
		GameController.shotFire(this);
	}

}
