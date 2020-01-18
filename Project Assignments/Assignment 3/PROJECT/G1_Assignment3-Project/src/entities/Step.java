package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import assets.SqlResult;
import assets.StepType;

// TODO: Auto-generated Javadoc
/** 
 * 
 * @author Raviv Komem
 * This is a class that represent a single step in the process
 * of handling a change request.
 * Every other step may extend this class and add more fields
 */
public class Step {

	/**
	 *  
	 * This is constructor only for the details required for time extension.
	 *
	 * @param type the type
	 * @param stepID the step ID
	 * @param changeRequestID the change request ID
	 * @param estimatedEndDate the estimated end date
	 */
	public Step(StepType type, int stepID, int changeRequestID, Date estimatedEndDate) {
		super();
		this.type = type;
		this.stepID = stepID;
		this.changeRequestID = changeRequestID;
		this.estimatedEndDate = estimatedEndDate;
	}
	
	/**
	 * This is a constructor using all the fields.
	 *
	 * @param type the type
	 * @param stepID the step ID
	 * @param changeRequestID the change request ID
	 * @param handlerUserName the handler user name
	 * @param startDate the start date
	 * @param status the status
	 * @param estimatedEndDate the estimated end date
	 * @param endDate the end date
	 */
	public Step(StepType type, int stepID, int changeRequestID, String handlerUserName, Date startDate, String status,
			Date estimatedEndDate, Date endDate) {
		super();
		this.type = type;
		this.stepID = stepID;
		this.changeRequestID = changeRequestID;
		this.handlerUserName = handlerUserName;
		this.startDate = startDate;
		this.status = status;
		this.estimatedEndDate = estimatedEndDate;
		this.endDate = endDate;
	}
	
	/** The type. */
	StepType type;
	
	/** The step ID. */
	int stepID;
	
	/** The change request ID. */
	int changeRequestID;
	
	/** The handler user name. */
	String handlerUserName;
	
	/** The start date. */
	Date startDate;
	
	/** The status. */
	String status;
	
	/** The estimated end date. */
	Date estimatedEndDate;
	
	/** The end date. */
	Date endDate;
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public StepType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(StepType type) {
		this.type = type;
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
	 * Gets the change request ID.
	 *
	 * @return the change request ID
	 */
	public int getChangeRequestID() {
		return changeRequestID;
	}
	
	/**
	 * Sets the change request ID.
	 *
	 * @param changeRequestID the new change request ID
	 */
	public void setChangeRequestID(int changeRequestID) {
		this.changeRequestID = changeRequestID;
	}
	
	/**
	 * Gets the handler user name.
	 *
	 * @return the handler user name
	 */
	public String getHandlerUserName() {
		return handlerUserName;
	}
	
	/**
	 * Sets the handler user name.
	 *
	 * @param handlerUserName the new handler user name
	 */
	public void setHandlerUserName(String handlerUserName) {
		this.handlerUserName = handlerUserName;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the estimated end date.
	 *
	 * @return the estimated end date
	 */
	public Date getEstimatedEndDate() {
		return estimatedEndDate;
	}
	
	/**
	 * Sets the estimated end date.
	 *
	 * @param estimatedEndDate the new estimated end date
	 */
	public void setEstimatedEndDate(Date estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
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
	
	/**
	 * Parses the sql result to step collection.
	 *
	 * @param sqlResult the sql result
	 * @return the collection
	 */
	public static Collection<Step> parseSqlResultToStepCollection(SqlResult sqlResult) {
		ArrayList<Step> stepList = new ArrayList<Step>();
		try
		{
			for (ArrayList<Object> resultRow : sqlResult.getResultData())
			{
				String stepType = (String) resultRow.get(0);
				int stepID = (int) resultRow.get(1);
				int changeRequestID = (int) resultRow.get(2);
				String handlerUserName = (String) resultRow.get(3);
				Date startDate = (Date) resultRow.get(4);
				String status = (String) resultRow.get(5);
				Date estimatedEndDate = (Date) resultRow.get(6);
				Date endDate = (Date) resultRow.get(7);
				
				StepType currentType = StepType.ERROR;
				for (StepType type : StepType.values())
				{
					if (type.getStepName().equals(stepType))
						currentType = type;
				}
				
				Step currentStep = new Step(currentType, stepID, changeRequestID, handlerUserName,
						startDate, status, estimatedEndDate, endDate);
				stepList.add(currentStep);
			}
			return stepList;
		}
		catch (Exception e)
		{
			return new ArrayList<Step>();
		}
	}
}
