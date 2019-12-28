package entities;

import java.util.ArrayList;

/**
 * 
 * @author Lee Hugi
 *this class represents an activity report
 *includes all the fields as described in the activity report page
 *
 */

public class ActivityReport {
	private int activeChageRequest;
	private int closeChangeRequest;
	private int suspendedChangeRequest;
	private int deniedChangeRequest;
	private ArrayList<Long> spentWorkDays;
	public ActivityReport(int activeChageRequest, int closeChangeRequest, int suspendedChangeRequest,
			int deniedChangeRequest, ArrayList<Long> spentWorkDays) {
		super();
		this.activeChageRequest = activeChageRequest;
		this.closeChangeRequest = closeChangeRequest;
		this.suspendedChangeRequest = suspendedChangeRequest;
		this.deniedChangeRequest = deniedChangeRequest;
		this.spentWorkDays = spentWorkDays;
	}
	public int getActiveChageRequest() {
		return activeChageRequest;
	}
	public void setActiveChageRequest(int activeChageRequest) {
		this.activeChageRequest = activeChageRequest;
	}
	public int getCloseChangeRequest() {
		return closeChangeRequest;
	}
	public void setCloseChangeRequest(int closeChangeRequest) {
		this.closeChangeRequest = closeChangeRequest;
	}
	public int getSuspendedChangeRequest() {
		return suspendedChangeRequest;
	}
	public void setSuspendedChangeRequest(int suspendedChangeRequest) {
		this.suspendedChangeRequest = suspendedChangeRequest;
	}
	public int getDeniedChangeRequest() {
		return deniedChangeRequest;
	}
	public void setDeniedChangeRequest(int deniedChangeRequest) {
		this.deniedChangeRequest = deniedChangeRequest;
	}
	public ArrayList<Long> getSpentWorkDays() {
		return spentWorkDays;
	}
	public void setSpentWorkDays(ArrayList<Long> spentWorkDays) {
		this.spentWorkDays = spentWorkDays;
	}
	
}
