package model.powers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.GameController;
import model.Power;

public class ShieldPower extends Power{
	
	public ShieldPower() {
		super("Shield", Color.red);
	}

	@Override
	public void onClickAction() {
		Timer invincibleTimer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getMainPanel().setCursor(Cursor.getDefaultCursor());
				GameController.getPlayer().setInvincible(false);
				System.out.println(System.currentTimeMillis());
			}
		});
		System.out.println(System.currentTimeMillis());
		GameController.getMainPanel().setCursor(new Cursor(Cursor.HAND_CURSOR));
		GameController.getPlayer().setInvincible(true);
		Timer.setLogTimers(true);
		invincibleTimer.setRepeats(false);
		invincibleTimer.start();
	}

}
