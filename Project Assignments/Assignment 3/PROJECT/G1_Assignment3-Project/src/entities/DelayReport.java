package entities;

import java.sql.Date;

/**
 * The Class DelayReport.
 *
 * @author Lee Hugi
 * this class represents a delay report
 * includes all the fields as described in the delay report page
 */
public class DelayReport {
	
	/** The subsystem. */
	private String subsystem;
	
	/** The estimate date. */
	private Date estimateDate;
	
	/** The end date. */
	private Date endDate;
	
	/**
	 * Instantiates a new delay report.
	 *
	 * @param subsystem the subsystem
	 * @param estimateDate the estimate date
	 * @param endDate the end date
	 */
	public DelayReport(String subsystem, Date estimateDate, Date endDate) {
		super();
		this.subsystem = subsystem;
		this.estimateDate = estimateDate;
		this.endDate = endDate;
	}

	/**
	 * Gets the subsystem.
	 *
	 * @return the subsystem
	 */
	public String getSubsystem() {
		return subsystem;
	}
	
	/**
	 * Sets the subsystem.
	 *
	 * @param subsystem the new subsystem
	 */
	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	/**
	 * Gets the estimate date.
	 *
	 * @return the estimate date
	 */
	public Date getEstimateDate() {
		return estimateDate;
	}
	
	/**
	 * Sets the estimate date.
	 *
	 * @param estimateDate the new estimate date
	 */
	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
