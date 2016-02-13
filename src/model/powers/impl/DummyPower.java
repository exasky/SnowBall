package model.powers.impl;

import model.powers.Power;

/**
 * Power that will be use only for tests.
 * 
 * @author Simar Jérémy
 *
 */
public class DummyPower extends Power {

	public DummyPower() {
		super(null);
	}

	@Override
	public void onClickAction() {
		System.out.println("WOLOLOL DUMMY WOLOLOL");
	}

}
