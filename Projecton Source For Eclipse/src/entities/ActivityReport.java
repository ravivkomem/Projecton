package entities;

import java.util.ArrayList;

/**
 * The Class ActivityReport.
 *
 * @author Lee Hugi
 * this class represents an activity report
 * includes all the fields as described in the activity report page
 */

public class ActivityReport{
	
	/** The active change request. */
	private int activeChageRequest;
	
	/** The close change request. */
	private int closeChangeRequest;
	
	/** The suspended change request. */
	private int suspendedChangeRequest;
	
	/** The denied change request. */
	private int deniedChangeRequest;
	
	/** The spent work days. */
	private ArrayList<Long> spentWorkDays;
	
	private double std;
	
	private double median;
	

	/**
	 * Instantiates a new activity report.
	 *
	 * @param activeChageRequest the active chage request
	 * @param closeChangeRequest the close change request
	 * @param suspendedChangeRequest the suspended change request
	 * @param deniedChangeRequest the denied change request
	 * @param spentWorkDays the spent work days
	 */
	public ActivityReport(int activeChageRequest, int closeChangeRequest, int suspendedChangeRequest,
			int deniedChangeRequest, ArrayList<Long> spentWorkDays) {
		super();
		this.activeChageRequest = activeChageRequest;
		this.closeChangeRequest = closeChangeRequest;
		this.suspendedChangeRequest = suspendedChangeRequest;
		this.deniedChangeRequest = deniedChangeRequest;
		this.spentWorkDays = spentWorkDays;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	/**
	 * Gets the active chage request.
	 *
	 * @return the active chage request
	 */
	public int getActiveChageRequest() {
		return activeChageRequest;
	}
	
	/**
	 * Sets the active chage request.
	 *
	 * @param activeChageRequest the new active chage request
	 */
	public void setActiveChageRequest(int activeChageRequest) {
		this.activeChageRequest = activeChageRequest;
	}
	
	/**
	 * Gets the close change request.
	 *
	 * @return the close change request
	 */
	public int getCloseChangeRequest() {
		return closeChangeRequest;
	}
	
	/**
	 * Sets the close change request.
	 *
	 * @param closeChangeRequest the new close change request
	 */
	public void setCloseChangeRequest(int closeChangeRequest) {
		this.closeChangeRequest = closeChangeRequest;
	}
	
	/**
	 * Gets the suspended change request.
	 *
	 * @return the suspended change request
	 */
	public int getSuspendedChangeRequest() {
		return suspendedChangeRequest;
	}
	
	/**
	 * Sets the suspended change request.
	 *
	 * @param suspendedChangeRequest the new suspended change request
	 */
	public void setSuspendedChangeRequest(int suspendedChangeRequest) {
		this.suspendedChangeRequest = suspendedChangeRequest;
	}
	
	/**
	 * Gets the denied change request.
	 *
	 * @return the denied change request
	 */
	public int getDeniedChangeRequest() {
		return deniedChangeRequest;
	}
	
	/**
	 * Sets the denied change request.
	 *
	 * @param deniedChangeRequest the new denied change request
	 */
	public void setDeniedChangeRequest(int deniedChangeRequest) {
		this.deniedChangeRequest = deniedChangeRequest;
	}
	
	/**
	 * Gets the spent work days.
	 *
	 * @return the spent work days
	 */
	public ArrayList<Long> getSpentWorkDays() {
		return spentWorkDays;
	}
	
	/**
	 * Sets the spent work days.
	 *
	 * @param spentWorkDays the new spent work days
	 */
	public void setSpentWorkDays(ArrayList<Long> spentWorkDays) {
		this.spentWorkDays = spentWorkDays;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityReport other = (ActivityReport) obj;
		if (activeChageRequest != other.activeChageRequest)
			return false;
		if (closeChangeRequest != other.closeChangeRequest)
			return false;
		if (deniedChangeRequest != other.deniedChangeRequest)
			return false;
		if (Double.doubleToLongBits(median) != Double.doubleToLongBits(other.median))
			return false;
		if (spentWorkDays == null) {
			if (other.spentWorkDays != null)
				return false;
		} else if (!spentWorkDays.equals(other.spentWorkDays))
			return false;
		if (Double.doubleToLongBits(std) != Double.doubleToLongBits(other.std))
			return false;
		if (suspendedChangeRequest != other.suspendedChangeRequest)
			return false;
		return true;
	}


	
}
