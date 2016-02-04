package model.powers;

import model.powers.impl.DummyPower;
import model.powers.impl.ExtraLifePower;
import model.powers.impl.NonePower;
import model.powers.impl.ShieldPower;

public class PowerListTest {
	public static void main(String[] args){
		PowerList powerList2 = new PowerList();
		
		powerList2.addPower(new DummyPower());
		powerList2.addPower(new DummyPower());
		powerList2.addPower(new ExtraLifePower());
		powerList2.addPower(new ShieldPower());
		powerList2.addPower(new NonePower());
		
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
		powerList2.popCurrentPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		powerList2.popCurrentPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		powerList2.popCurrentPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
		powerList2.switchToNextPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
		powerList2.switchToNextPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
		powerList2.switchToNextPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
		powerList2.switchToPreviousPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
		powerList2.switchToPreviousPower();
		System.out.println(powerList2.getCurrentPower() + "; " + powerList2.currentPowerIndex + "; " + powerList2.powerCounts.toString());
		
	}
}
