package model;

import java.util.Observable;

public class Player extends Observable{
	
	private int score;
	private int lifes;
	
	private boolean isInvincible;
	
	private PowerList powerList;

	public Player(){
		this.score = 0;
		this.lifes = 1;
		this.isInvincible = false;
		this.powerList = new PowerList();
	}
	
	public void addPoint(){
		addPoints(1);
	}
	
	public void addPoints(int points){
		this.score += points;
		sendNotification(this.score);
	}
	
	public void resetPlayer(){
		this.score = 0;
		this.lifes = 1;
		this.isInvincible = false;
		this.powerList.clear();
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void kill(){
		if (this.isInvincible) return;
		this.lifes--;
		sendNotification("Lifes: " + this.lifes);
	}
	
	public void addLife(){
		this.lifes++;
		sendNotification("Lifes: " + this.lifes);
	}
	
	public int getLifes(){
		return this.lifes;
	}
	
	public PowerList getPowerList(){
		return powerList;
	}
	
	public void setInvincible(boolean isInvincible){
		this.isInvincible = isInvincible;
	}
	
	private void sendNotification(Object arg){
		setChanged();
		notifyObservers(arg);
	}
}