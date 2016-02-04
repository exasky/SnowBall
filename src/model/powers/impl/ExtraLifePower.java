package model.powers.impl;

import java.awt.Color;

import model.powers.Power;
import controller.GameController;

public class ExtraLifePower extends Power {

	public ExtraLifePower() {
		super("ExtraLife", Color.green);
	}

	@Override
	public void onHoverAction(){
		GameController.getPlayer().addLife();
	}

	@Override
	public void onClickAction() {}
}
