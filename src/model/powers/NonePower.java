package model.powers;

import java.awt.Color;

import model.Power;

public class NonePower extends Power {

	public NonePower() {
		super("NONE", Color.white);
	}

	@Override
	public void onHoverAction() {}

	@Override
	public void onClickAction() {}

}
