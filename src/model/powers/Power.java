package model.powers;

import java.awt.image.BufferedImage;

import controller.GameController;
import controller.PowerController;

public abstract class Power {
	
	private PowerID powerId;
	private BufferedImage ballImage;

	public Power(PowerID powerId) {
		this.powerId = powerId;
		this.ballImage = PowerController.getBallImage(powerId);
	}
	
	public void onHoverAction(){
		GameController.getPlayer().getPowerList().addPower(this);
	}
	
	public abstract void onClickAction();
	
	public PowerID getPowerID() {
		return powerId;
	}
	
	public BufferedImage getBallImage(){
		return ballImage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Power))
			return false;
		return ((Power) obj).getPowerID().equals(this.powerId);
	}
	
	@Override
	public String toString() {
		return "POWER: name " + this.powerId;
	}
}
