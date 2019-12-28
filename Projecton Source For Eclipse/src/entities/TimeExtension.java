package entities;

import java.sql.Date;

import assets.StepType;

/**
 * 
 * @author Raviv Komem
 * @see this class represents a single time extension request
 * includes all the fields as described in the time_extension table
 */
public class TimeExtension {

	public TimeExtension(int timeExtensionID, int stepID, String stepType, Date oldDate, Date newDate, String reason,
			String timeExtensionStatus) {
		super();
		this.timeExtensionID = timeExtensionID;
		this.stepID = stepID;
		this.setStepType(stepType);
		this.oldDate = oldDate;
		this.newDate = newDate;
		this.reason = reason;
		this.timeExtensionStatus = timeExtensionStatus;
	}
	
	int timeExtensionID;
	int stepID;
	StepType stepType;
	Date oldDate;
	Date newDate;
	String reason;
	String timeExtensionStatus;
	
	public int getTimeExtensionID() {
		return timeExtensionID;
	}
	public void setTimeExtensionID(int timeExtensionID) {
		this.timeExtensionID = timeExtensionID;
	}
	public int getStepID() {
		return stepID;
	}
	public void setStepID(int stepID) {
		this.stepID = stepID;
	}
	public StepType getStepType() {
		return stepType;
	}
	public void setStepType(String type) {
		for (StepType t : StepType.values())
		{
			if (t.getStepName().equals(type))
			{
				this.stepType = t;
				return;
			}
		}	
		this.stepType = StepType.ERROR;
	}
	public Date getOldDate() {
		return oldDate;
	}
	public void setOldDate(Date oldDate) {
		this.oldDate = oldDate;
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTimeExtensionStatus() {
		return timeExtensionStatus;
	}
	public void setTimeExtensionStatus(String timeExtensionStatus) {
		this.timeExtensionStatus = timeExtensionStatus;
	}
	
}
