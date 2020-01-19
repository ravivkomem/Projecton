package entities;

import java.sql.Date;

import assets.StepType;

/**
 * The Class TimeExtension.
 *
 * @author Raviv Komem
 * this class represents a single time extension request
 * includes all the fields as described in the time_extension table
 */
public class TimeExtension {

	/**
	 * Instantiates a new time extension.
	 *
	 * @param timeExtensionID the time extension ID
	 * @param stepID the step ID
	 * @param stepType the step type
	 * @param oldDate the old date
	 * @param newDate the new date
	 * @param reason the reason
	 * @param timeExtensionStatus the time extension status
	 */
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
		actualStep = stepType;
	}
	
	/** The time extension ID. */
	int timeExtensionID;
	
	/** The step ID. */
	int stepID;
	
	/** The step type. */
	StepType stepType;
	
	/** The old date. */
	Date oldDate;
	
	/** The new date. */
	Date newDate;
	
	/** The reason. */
	String reason;
	
	/** The time extension status. */
	String timeExtensionStatus;
	
	private String actualStep;
	/**
	 * Gets the time extension ID.
	 *
	 * @return the time extension ID
	 */
	public int getTimeExtensionID() {
		return timeExtensionID;
	}
	
	/**
	 * Sets the time extension ID.
	 *
	 * @param timeExtensionID the new time extension ID
	 */
	public void setTimeExtensionID(int timeExtensionID) {
		this.timeExtensionID = timeExtensionID;
	}
	
	/**
	 * Gets the step ID.
	 *
	 * @return the step ID
	 */
	public int getStepID() {
		return stepID;
	}
	
	/**
	 * Sets the step ID.
	 *
	 * @param stepID the new step ID
	 */
	public void setStepID(int stepID) {
		this.stepID = stepID;
	}
	
	/**
	 * Gets the step type.
	 *
	 * @return the step type
	 */
	public StepType getStepType() {
		return stepType;
	}
	
	/**
	 * Sets the step type.
	 *
	 * @param type the new step type
	 */
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
	
	/**
	 * Gets the old date.
	 *
	 * @return the old date
	 */
	public Date getOldDate() {
		return oldDate;
	}
	
	/**
	 * Sets the old date.
	 *
	 * @param oldDate the new old date
	 */
	public void setOldDate(Date oldDate) {
		this.oldDate = oldDate;
	}
	
	/**
	 * Gets the new date.
	 *
	 * @return the new date
	 */
	public Date getNewDate() {
		return newDate;
	}
	
	/**
	 * Sets the new date.
	 *
	 * @param newDate the new new date
	 */
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	
	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * Gets the time extension status.
	 *
	 * @return the time extension status
	 */
	public String getTimeExtensionStatus() {
		return timeExtensionStatus;
	}
	
	/**
	 * Sets the time extension status.
	 *
	 * @param timeExtensionStatus the new time extension status
	 */
	public void setTimeExtensionStatus(String timeExtensionStatus) {
		this.timeExtensionStatus = timeExtensionStatus;
	}

	public String getActualStep() {
		return actualStep;
	}

	public void setActualStep(String actualStep) {
		this.actualStep = actualStep;
	}

	public void setStepType(StepType stepType) {
		this.stepType = stepType;
	}
	
}
