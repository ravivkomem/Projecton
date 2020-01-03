package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import assets.SqlResult;
import assets.StepType;

/** 
 * 
 * @author Raviv Komem
 * This is a class that represent a single step in the process
 * of handling a change request.
 * Every other step may extend this class and add more fields
 */
public class Step {

	/** 
	 * This is constructor only for the details required for time extension
	 * @param type
	 * @param stepID
	 * @param changeRequestID
	 * @param estimatedEndDate
	 */
	public Step(StepType type, int stepID, int changeRequestID, Date estimatedEndDate) {
		super();
		this.type = type;
		this.stepID = stepID;
		this.changeRequestID = changeRequestID;
		this.estimatedEndDate = estimatedEndDate;
	}
	
	/**
	 * This is a constructor using all the fields
	 * @param type
	 * @param stepID
	 * @param changeRequestID
	 * @param handlerUserName
	 * @param startDate
	 * @param status
	 * @param estimatedEndDate
	 * @param endDate
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
	
	StepType type;
	int stepID;
	int changeRequestID;
	String handlerUserName;
	Date startDate;
	String status;
	Date estimatedEndDate;
	Date endDate;
	
	public StepType getType() {
		return type;
	}
	public void setType(StepType type) {
		this.type = type;
	}
	public int getStepID() {
		return stepID;
	}
	public void setStepID(int stepID) {
		this.stepID = stepID;
	}
	public int getChangeRequestID() {
		return changeRequestID;
	}
	public void setChangeRequestID(int changeRequestID) {
		this.changeRequestID = changeRequestID;
	}
	public String getHandlerUserName() {
		return handlerUserName;
	}
	public void setHandlerUserName(String handlerUserName) {
		this.handlerUserName = handlerUserName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEstimatedEndDate() {
		return estimatedEndDate;
	}
	public void setEstimatedEndDate(Date estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
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
