package model.powers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Observable;

public class PowerList extends Observable {

	protected Map<PowerID, List<Power>> powerCounts;
	private List<PowerID> powerNameList;
	protected int currentPowerIndex;

	public PowerList() {
		this.powerNameList = new ArrayList<PowerID>();
		this.powerCounts = new HashMap<PowerID, List<Power>>();
		this.currentPowerIndex = -1;
	}

	/**
	 * Add a power to the current power list.
	 * 
	 * @param power
	 *            The power to add.
	 */
	public void addPower(Power power) {
		if (this.currentPowerIndex == -1)
			this.currentPowerIndex = 0;

		if (this.powerNameList.contains(power.getPowerID())) {
			this.powerCounts.get(power.getPowerID()).add(power);
		} else {
			this.powerCounts.put(power.getPowerID(), new ArrayList<Power>(Arrays.asList(power)));
			this.powerNameList.add(power.getPowerID());
		}

		if (this.getCurrentPower() == null || this.powerCounts.get(this.getCurrentPower().getPowerID()).size() == 0) {
			switchToNextPower();
		}

		sendNotification(new Object[] { power.getBallImage(), this.powerCounts.get(power.getPowerID()).size() });
	}

	/**
	 * Set the power index to the next valid one
	 */
	public void switchToNextPower() {
		switchToPower(1);
	}

	/**
	 * Set the power index to the previous valid one
	 */
	public void switchToPreviousPower() {
		switchToPower(-1);
	}

	private void switchToPower(int offset) {
		if (this.currentPowerIndex == -1)
			return;

		Integer powerIndex = getValidPowerIndex(offset);
		if (powerIndex != null) {
			this.currentPowerIndex = powerIndex;
			sendNotification(getCurrentPower());
		}
	}

	/**
	 * 
	 * @param offset
	 * @return The next/previous index of an available power. If none of them is
	 *         available, return null
	 */
	private Integer getValidPowerIndex(int offset) {
		int tempIndex = this.currentPowerIndex;
		do {
			tempIndex += offset;
			if (tempIndex < 0)
				tempIndex = this.powerNameList.size() - 1;
			else if (tempIndex >= this.powerNameList.size())
				tempIndex = 0;

			if (tempIndex == this.currentPowerIndex)
				return null;
		} while (this.powerCounts.get(this.powerNameList.get(tempIndex)).size() == 0);

		return tempIndex;
	}

	/**
	 * Simple getter.
	 * 
	 * @return The current power
	 */
	public Power getCurrentPower() {
		if (this.currentPowerIndex == -1)
			return null;

		List<Power> currentPowersList = this.powerCounts.get(this.powerNameList.get(this.currentPowerIndex));
		if (currentPowersList.size() == 0)
			return null;
		return currentPowersList.get(0);
	}

	/**
	 * Remove the current power from the power list and return it
	 * 
	 * @return The current power
	 */
	public Power popCurrentPower() {
		if (this.currentPowerIndex == -1)
			return null;

		List<Power> currentPowersList = this.powerCounts.get(this.powerNameList.get(this.currentPowerIndex));
		if (currentPowersList.size() == 0)
			return null;

		Power remove = currentPowersList.remove(0);
		this.powerCounts.put(remove.getPowerID(), currentPowersList);
		sendNotification(new Object[] { remove.getBallImage(), currentPowersList.size() });

		if (currentPowersList.size() == 0)
			switchToNextPower();

		return remove;
	}

	/**
	 * Reset the power list. It will reset the index and purge the list of the
	 * powers
	 */
	public void reset() {
		this.powerNameList.clear();
		this.powerCounts.clear();
		this.currentPowerIndex = -1;
		sendNotification(null);
	}
}
