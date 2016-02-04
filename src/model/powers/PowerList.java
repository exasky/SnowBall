package model.powers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Observable;

public class PowerList extends Observable{
	
	protected Map<String,Integer> powerCounts;
	private List<Power> powerList;
	protected int currentPowerIndex;
	
	public PowerList() {
		this.powerList = new ArrayList<Power>();
		this.powerCounts = new HashMap<String, Integer>();
		this.currentPowerIndex = -1;
	}
	
	public void clear(){
		this.powerList.clear();
		this.powerCounts.clear();
		this.currentPowerIndex = -1;
	}
	
	public void addPower(Power power) {
		if (this.currentPowerIndex == -1) this.currentPowerIndex = 0;
		if (this.powerList.contains(power)) {
			this.powerCounts.put(power.getPowerName(), this.powerCounts.get(power.getPowerName()) + 1);
		} else {
			this.powerCounts.put(power.getPowerName(), 1);
			this.powerList.add(power);
		}
		
		sendNotification(new Object[]{power.getBallImage(),this.powerCounts.get(power.getPowerName())});
	}
	
	public void switchToNextPower(){
		switchToPower(1);
	}
	
	public void switchToPreviousPower(){
		switchToPower(-1);
	}
	
	private void switchToPower(int offset){
		if (this.currentPowerIndex == -1) return;
		
		Integer powerIndex = getValidPowerIndex(offset);
		if (powerIndex != null) {
			this.currentPowerIndex = powerIndex;
			sendNotification(getCurrentPower());
		}
	}
	
	/**
	 * 
	 * @param offset
	 * @return The next/previous index of an available power.
	 * 		   If none of them is available, return null 
	 */
	private Integer getValidPowerIndex(int offset){
		int tempIndex = this.currentPowerIndex;
		do {
			tempIndex += offset;
			if (tempIndex < 0)
				tempIndex = this.powerList.size() -1;
			else if (tempIndex >= this.powerList.size())
				tempIndex = 0;
			
			if (tempIndex == this.currentPowerIndex) return null;
		} while (this.powerCounts.get(this.powerList.get(tempIndex).getPowerName()) == 0);

		return tempIndex;
	}
	
	public Power getCurrentPower(){
		if (this.currentPowerIndex == -1) return null;
		return this.powerList.get(this.currentPowerIndex);
	}
	
	public Power popCurrentPower(){
		if (this.currentPowerIndex == -1) return null;
		
		Power power = this.powerList.get(this.currentPowerIndex);
		Integer powerCount = this.powerCounts.get(power.getPowerName());
		if (powerCount == 0) return null;
		
		this.powerCounts.put(power.getPowerName(), powerCount - 1);
		sendNotification(new Object[]{power.getBallImage(),this.powerCounts.get(power.getPowerName())});
		
		if (powerCount -1 == 0)
			switchToNextPower();
		
		return power;
	}
}
