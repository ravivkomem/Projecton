package entities;

import java.sql.Date;

/**
 * 
 * @author Lee Hugi
 * this class represents a delay report
 * includes all the fields as described in the delay report page
 *
 */
public class DelayReport {
	private String subsystem;
	private Date estimateDate;
	private Date endDate;
	
	public DelayReport(String subsystem, Date estimateDate, Date endDate) {
		super();
		this.subsystem = subsystem;
		this.estimateDate = estimateDate;
		this.endDate = endDate;
	}

	public String getSubsystem() {
		return subsystem;
	}
	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
