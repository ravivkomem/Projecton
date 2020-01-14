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
		
		actualStep=getActutalStepByCurrentStep(currentStep);
		
	}
	
	public ChangeRequest(Integer changeRequestID, String initiatorUserName, Date startDate, String selectedSubsystem,
			String currentStateDescription, String desiredChangeDescription, String desiredChangeExplanation,
			String desiredChangeComments, String status, String currentStep, String handlerUserName, Date endDate,
			String fullName, Date estimatedEndDate) {
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
		this.fullName = fullName;
		this.estimatedEndDate = estimatedEndDate;
		actualStep=getActutalStepByCurrentStep(currentStep);
	}


	public ChangeRequest(Integer changeRequestID, String initiatorUserName, Date startDate, String selectedSubsystem,
			String currentStateDescription, String desiredChangeDescription, String desiredChangeExplanation,
			String desiredChangeComments, String status, String currentStep, String handlerUserName, String fullName,
			Date estimatedEndDate) {
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
		this.fullName = fullName;
		this.estimatedEndDate = estimatedEndDate;
		actualStep=getActutalStepByCurrentStep(currentStep);
	}



	public ChangeRequest(String InitiatorUserName,String selectedSubsystem,String currentStateDescription,String desiredChangeDescription,
			String desiredChangeComments,String desiredChangeExplanation,Date startDate, String status,String handlerUserName,String currentStep,String jobDescription,String email,String fullName,Date estimatedEndDate)
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
		this.jobDescription=jobDescription;
		this.email=email;
		this.fullName=fullName;
		this.estimatedEndDate=estimatedEndDate;
		
		actualStep=getActutalStepByCurrentStep(currentStep);
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
		actualStep=getActutalStepByCurrentStep(currentStep);
	}
	
	public ChangeRequest(Integer changeRequestID2, String initiatorUserName2, Date startDate2,
			String selectedSubsystem2, String currentStateDescription2, String desiredChangeDescription2,
			String desiredChangeExplanation2, String desiredChangeComments2, String status2, String currentStep2,
			String handlerUserName2, Date endDate2, String email2, String jobDescription2, String fullName2,
			Date estimatedDate) 
	{
		changeRequestID=changeRequestID2;
		InitiatorUserName=initiatorUserName2;
		startDate=startDate2;
		selectedSubsystem=selectedSubsystem2;
		currentStateDescription=currentStateDescription2;
		desiredChangeDescription=desiredChangeDescription2;
		desiredChangeExplanation=desiredChangeExplanation2;
		desiredChangeComments=desiredChangeComments2;
		status=status2;
		handlerUserName=handlerUserName2;
		endDate=endDate2;
		currentStep=currentStep2;
		jobDescription=jobDescription2;
		email=email2;
		fullName=fullName2;
		estimatedEndDate=estimatedDate;
		actualStep=getActutalStepByCurrentStep(currentStep);
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
	private String actualStep;
	private String jobDescription;
	private String email;
	private String fullName;
	private Date estimatedEndDate;
	
	public Date getEstimatedEndDate() {
		return estimatedEndDate;
	}
	public void setEstimatedEndDate(Date estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

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

	public String getActualStep() {
		return actualStep;
	}

	public void setActualStep(String actualStep) {
		this.actualStep = actualStep;
	}
	private String getActutalStepByCurrentStep(String currentStep)
	{
		String actualStep;
		switch(currentStep)
		{
		
			case "AUTO_ANALYZER_APPOINT":
			case "ANALYSIS_SET_TIME":
			case "ANALYSIS_APPROVE_TIME":
			case "ANALYSIS_WORK":
				actualStep = "Analysis";
				break;
				
			case "EXECUTION_SET_TIME":
			case "EXECUTION_APPROVE_TIME":
			case "EXECUTION_WORK":
				actualStep = "Execution";
				break;
			
			case "TESTING_WORK":
				actualStep = "Testing";
				break;
			
			case "COMMITTEE_WORK":
				actualStep = "Committee";
				break;
				
			case "FINISH":
				actualStep = "Finish";
				break;
				
			case "DENY_STEP":
				actualStep= "Deny Step";
				break;
				
			case "CLOSE_STEP":
				actualStep="Close Step";
				break;
				
			case "TESTER_COMMITTEE_DIRECTOR_APPOINT":
				actualStep="Tester Appoint";
				break;
				
			default:
				actualStep = currentStep;
				break;
		}
		return actualStep;
	}
	
}
