package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PowerList {
	
	private Map<String,Integer> powerCounts;
	private List<Power> powerList;
	private int currentPowerIndex;
	
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
	
	public boolean addPower(Power power) {
		if (this.currentPowerIndex == -1) this.currentPowerIndex = 0;
		if (this.powerList.contains(power)) {
			this.powerCounts.put(power.getPowerName(), this.powerCounts.get(power.getPowerName()) + 1);
			return false;
		} else {
			this.powerCounts.put(power.getPowerName(), 1);
			return this.powerList.add(power);
		}
	}
	
	public void switchToNextPower(){
		if (this.currentPowerIndex == -1) return;
		this.currentPowerIndex++;
		if (this.currentPowerIndex > this.powerList.size())
			this.currentPowerIndex = 0;
	}
	
	public void switchToPreviousPower(){
		if (this.currentPowerIndex == -1) return;
		this.currentPowerIndex--;
		if (this.currentPowerIndex < 0)
			this.currentPowerIndex = this.powerList.size() - 1 ;
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
		return power;
	}
}
