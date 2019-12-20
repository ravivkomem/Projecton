package entities;

import java.sql.Date;

public class ChangeRequest {
	
	public ChangeRequest(Integer changeRequestID, String initiatorUserName, Date startDate, String selectedSubsystem,
			String currentStateDescription, String desiredChangeDescription, String desiredChangeExplanation,
			String desiredChangeComments, String status, String currentStep, String handlerUserName, Date endDate) {
		super();
		this.changeRequestID = changeRequestID;
		InitiatorUserName = initiatorUserName;
		this.startDate = startDate;
		this.selectedSubsystem = selectedSubsystem;
		this.currentStateDescription = currentStateDescription;
		this.desiredChangeDescription = desiredChangeDescription;
		this.desiredChangeExplanation = desiredChangeExplanation;
		this.desiredChangeComments = desiredChangeComments;
		this.status = status;
		this.currentStep = currentStep;
		this.handlerUserName = handlerUserName;
		this.endDate = endDate;
	}
	
	public ChangeRequest(String InitiatorUserName,String selectedSubsystem,String currentStateDescription,String desiredChangeDescription,
			String desiredChangeComments,String desiredChangeExplanation,Date startDate, String status,String handlerUserName,String currentStep)
	{
		this.InitiatorUserName=InitiatorUserName;
		this.selectedSubsystem=selectedSubsystem;
		this.currentStateDescription=currentStateDescription;
		this.desiredChangeDescription=desiredChangeDescription;
		this.desiredChangeComments=desiredChangeComments;
		this.desiredChangeExplanation=desiredChangeExplanation;
		this.startDate=startDate;
		this.status=status;
		this.handlerUserName=handlerUserName;
		this.currentStep=currentStep;	
	}
	
	private Integer changeRequestID;
	private String InitiatorUserName;
	private Date startDate;
	private String selectedSubsystem;
	private String currentStateDescription;
	private String desiredChangeDescription;
	private String desiredChangeExplanation;
	private String desiredChangeComments;
	private String status;
	private String currentStep;
	private String handlerUserName;
	private Date endDate;
	
	public Integer getChangeRequestID() {
		return changeRequestID;
	}
	public void setChangeRequestID(Integer changeRequestID) {
		this.changeRequestID = changeRequestID;
	}
	public String getInitiatorUserName() {
		return InitiatorUserName;
	}
	public void setInitiatorUserName(String initiatorUserName) {
		InitiatorUserName = initiatorUserName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getSelectedSubsystem() {
		return selectedSubsystem;
	}
	public void setSelectedSubsystem(String selectedSubsystem) {
		this.selectedSubsystem = selectedSubsystem;
	}
	public String getCurrentStateDescription() {
		return currentStateDescription;
	}
	public void setCurrentStateDescription(String currentStateDescription) {
		this.currentStateDescription = currentStateDescription;
	}
	public String getDesiredChangeDescription() {
		return desiredChangeDescription;
	}
	public void setDesiredChangeDescription(String desiredChangeDescription) {
		this.desiredChangeDescription = desiredChangeDescription;
	}
	public String getDesiredChangeExplanation() {
		return desiredChangeExplanation;
	}
	public void setDesiredChangeExplanation(String desiredChangeExplanation) {
		this.desiredChangeExplanation = desiredChangeExplanation;
	}
	public String getDesiredChangeComments() {
		return desiredChangeComments;
	}
	public void setDesiredChangeComments(String desiredChangeComments) {
		this.desiredChangeComments = desiredChangeComments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}
	public String getHandlerUserName() {
		return handlerUserName;
	}
	public void setHandlerUserName(String handlerUserName) {
		this.handlerUserName = handlerUserName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
