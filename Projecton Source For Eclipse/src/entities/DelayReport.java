package entities;

import java.sql.Date;

public class DelayReport {
	private int changeRequestId;
	private Date estimateDate;
	private Date endDate;
	
	public DelayReport(int changeRequestId, Date estimateDate, Date endDate) {
		super();
		this.changeRequestId = changeRequestId;
		this.estimateDate = estimateDate;
		this.endDate = endDate;
	}

	public int getChangeRequestId() {
		return changeRequestId;
	}

	public void setChangeRequestId(int changeRequestId) {
		this.changeRequestId = changeRequestId;
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
