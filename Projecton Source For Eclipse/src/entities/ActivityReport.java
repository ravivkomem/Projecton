package entities;

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
	private long spentWorkDays;
	
	public ActivityReport(int activeChageRequest, int closeChangeRequest, int suspendedChangeRequest,
			int deniedChangeRequest, long spentWorkDays) {
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

	public long getSpentWorkDays() {
		return spentWorkDays;
	}

	public void setSpentWorkDays(long spentWorkDays) {
		this.spentWorkDays = spentWorkDays;
	}
	
}
