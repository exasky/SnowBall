package model.powers.impl;

import model.powers.Power;
import model.powers.PowerID;

/**
 * Default power. It doesn't do anything and is used on white snow balls.
 * 
 * @author Simar Jérémy
 *
 */
public class NonePower extends Power {

	public NonePower() {
		super(PowerID.NONE);
	}

	@Override
	public void onHoverAction() {}

	@Override
	public void onClickAction() {}

}
