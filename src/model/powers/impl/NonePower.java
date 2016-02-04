package model.powers.impl;

import java.awt.Color;

import model.powers.Power;

public class NonePower extends Power {

	public NonePower() {
		super("NONE", Color.white);
	}

	@Override
	public void onHoverAction() {}

	@Override
	public void onClickAction() {}

}
