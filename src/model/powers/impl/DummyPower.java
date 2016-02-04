package model.powers.impl;

import java.awt.Color;

import model.powers.Power;

public class DummyPower extends Power {

	public DummyPower() {
		super("DummyPower", Color.blue);
	}

	@Override
	public void onClickAction() {
		System.out.println("wololo dummy wololo");
	}

}
