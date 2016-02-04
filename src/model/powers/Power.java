package model.powers;

import java.awt.Color;
import java.awt.image.BufferedImage;

import controller.GameController;
import controller.PowerController;

public abstract class Power {
	
	private String powerName;
	private BufferedImage ballImage;

	public Power(String powerName, Color color){
		this.powerName = powerName;
		this.ballImage = PowerController.getBallImage(color);
	}
	
	public void onHoverAction(){
		GameController.getPlayer().getPowerList().addPower(this);
	}
	
	public abstract void onClickAction();
	
	public String getPowerName() {
		return powerName;
	}
	
	public BufferedImage getBallImage(){
		return ballImage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Power))
			return false;
		return ((Power)obj).getPowerName().equals(this.powerName);
	}
	
	@Override
	public String toString() {
		return "POWER: name " + this.powerName;
	}
}
