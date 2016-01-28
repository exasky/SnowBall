package model.powers;

import java.awt.Color;

import controller.GameController;
import model.Power;

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
