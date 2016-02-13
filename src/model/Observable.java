package model;

public class Observable extends java.util.Observable {

	public void sendNotification(Object arg){
		setChanged();
		notifyObservers(arg);
	}
}
