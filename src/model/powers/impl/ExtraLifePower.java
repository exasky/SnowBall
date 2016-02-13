package model.powers.impl;

import model.powers.Power;
import model.powers.PowerID;
import controller.GameController;

/**
 * Add an extra life to the player.
 * 
 * @author Simar Jérémy
 *
 */
public class ExtraLifePower extends Power {

	public ExtraLifePower() {
		super(PowerID.ExtraLife);
	}

	@Override
	public void onHoverAction(){
		GameController.getPlayer().addLife();
	}

	@Override
	public void onClickAction() {}
}
