package model.powers.impl;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.powers.Power;
import model.powers.PowerID;
import controller.GameController;

/**
 * Add a shield to the play that let him be invincible for 3 seconds.
 * 
 * @author Simar Jérémy
 *
 */
public class ShieldPower extends Power{
	
	public ShieldPower() {
		super(PowerID.Shield);
	}

	@Override
	public void onClickAction() {
		Timer invincibleTimer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.setGameCursor(Cursor.getDefaultCursor());
				GameController.getPlayer().setInvincible(false);
			}
		});
		GameController.setGameCursor(new Cursor(Cursor.HAND_CURSOR));
		GameController.getPlayer().setInvincible(true);
		invincibleTimer.setRepeats(false);
		invincibleTimer.start();
	}

}
