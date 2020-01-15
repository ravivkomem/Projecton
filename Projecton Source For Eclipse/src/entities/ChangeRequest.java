package entities;

import java.sql.Date;

/**
 * The Class ChangeRequest.
 *
 * @author Raviv Komem
 */
public class ChangeRequest {
	
	/**
	 * Instantiates a new change request.
	 *
	 * @param changeRequestID the change request ID
	 * @param initiatorUserName the initiator user name
	 * @param startDate the start date
	 * @param selectedSubsystem the selected subsystem
	 * @param currentStateDescription the current state description
	 * @param desiredChangeDescription the desired change description
	 * @param desiredChangeExplanation the desired change explanation
	 * @param desiredChangeComments the desired change comments
	 * @param status the status
	 * @param currentStep the current step
	 * @param handlerUserName the handler user name
	 * @param endDate the end date
	 */
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
	
	/**
	 * Instantiates a new change request.
	 *
	 * @param changeRequestID the change request ID
	 * @param initiatorUserName the initiator user name
	 * @param startDate the start date
	 * @param selectedSubsystem the selected subsystem
	 * @param currentStateDescription the current state description
	 * @param desiredChangeDescription the desired change description
	 * @param desiredChangeExplanation the desired change explanation
	 * @param desiredChangeComments the desired change comments
	 * @param status the status
	 * @param currentStep the current step
	 * @param handlerUserName the handler user name
	 * @param endDate the end date
	 * @param fullName the full name
	 * @param estimatedEndDate the estimated end date
	 */
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


	/**
	 * Instantiates a new change request.
	 *
	 * @param changeRequestID the change request ID
	 * @param initiatorUserName the initiator user name
	 * @param startDate the start date
	 * @param selectedSubsystem the selected subsystem
	 * @param currentStateDescription the current state description
	 * @param desiredChangeDescription the desired change description
	 * @param desiredChangeExplanation the desired change explanation
	 * @param desiredChangeComments the desired change comments
	 * @param status the status
	 * @param currentStep the current step
	 * @param handlerUserName the handler user name
	 * @param fullName the full name
	 * @param estimatedEndDate the estimated end date
	 */
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



	/**
	 * Instantiates a new change request.
	 *
	 * @param InitiatorUserName the initiator user name
	 * @param selectedSubsystem the selected subsystem
	 * @param currentStateDescription the current state description
	 * @param desiredChangeDescription the desired change description
	 * @param desiredChangeComments the desired change comments
	 * @param desiredChangeExplanation the desired change explanation
	 * @param startDate the start date
	 * @param status the status
	 * @param handlerUserName the handler user name
	 * @param currentStep the current step
	 * @param jobDescription the job description
	 * @param email the email
	 * @param fullName the full name
	 * @param estimatedEndDate the estimated end date
	 */
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
	
	/**
	 * Instantiates a new change request.
	 *
	 * @param InitiatorUserName the initiator user name
	 * @param selectedSubsystem the selected subsystem
	 * @param currentStateDescription the current state description
	 * @param desiredChangeDescription the desired change description
	 * @param desiredChangeComments the desired change comments
	 * @param desiredChangeExplanation the desired change explanation
	 * @param startDate the start date
	 * @param status the status
	 * @param handlerUserName the handler user name
	 * @param currentStep the current step
	 */
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
	
	/**
	 * Instantiates a new change request.
	 *
	 * @param changeRequestID2 the change request ID 2
	 * @param initiatorUserName2 the initiator user name 2
	 * @param startDate2 the start date 2
	 * @param selectedSubsystem2 the selected subsystem 2
	 * @param currentStateDescription2 the current state description 2
	 * @param desiredChangeDescription2 the desired change description 2
	 * @param desiredChangeExplanation2 the desired change explanation 2
	 * @param desiredChangeComments2 the desired change comments 2
	 * @param status2 the status 2
	 * @param currentStep2 the current step 2
	 * @param handlerUserName2 the handler user name 2
	 * @param endDate2 the end date 2
	 * @param email2 the email 2
	 * @param jobDescription2 the job description 2
	 * @param fullName2 the full name 2
	 * @param estimatedDate the estimated date
	 */
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

	/** The change request ID. */
	private Integer changeRequestID;
	
	/** The Initiator user name. */
	private String InitiatorUserName;
	
	/** The start date. */
	private Date startDate;
	
	/** The selected subsystem. */
	private String selectedSubsystem;
	
	/** The current state description. */
	private String currentStateDescription;
	
	/** The desired change description. */
	private String desiredChangeDescription;
	
	/** The desired change explanation. */
	private String desiredChangeExplanation;
	
	/** The desired change comments. */
	private String desiredChangeComments;
	
	/** The status. */
	private String status;
	
	/** The current step. */
	private String currentStep;
	
	/** The handler user name. */
	private String handlerUserName;
	
	/** The end date. */
	private Date endDate;
	
	/** The actual step. */
	private String actualStep;
	
	/** The job description. */
	private String jobDescription;
	
	/** The email. */
	private String email;
	
	/** The full name. */
	private String fullName;
	
	/** The estimated end date. */
	private Date estimatedEndDate;
	
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
	 * Gets the job description.
	 *
	 * @return the job description
	 */
	public String getJobDescription() {
		return jobDescription;
	}

	/**
	 * Sets the job description.
	 *
	 * @param jobDescription the new job description
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the change request ID.
	 *
	 * @return the change request ID
	 */
	public Integer getChangeRequestID() {
		return changeRequestID;
	}
	
	/**
	 * Sets the change request ID.
	 *
	 * @param changeRequestID the new change request ID
	 */
	public void setChangeRequestID(Integer changeRequestID) {
		this.changeRequestID = changeRequestID;
	}
	
	/**
	 * Gets the initiator user name.
	 *
	 * @return the initiator user name
	 */
	public String getInitiatorUserName() {
		return InitiatorUserName;
	}
	
	/**
	 * Sets the initiator user name.
	 *
	 * @param initiatorUserName the new initiator user name
	 */
	public void setInitiatorUserName(String initiatorUserName) {
		InitiatorUserName = initiatorUserName;
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
	 * Gets the selected subsystem.
	 *
	 * @return the selected subsystem
	 */
	public String getSelectedSubsystem() {
		return selectedSubsystem;
	}
	
	/**
	 * Sets the selected subsystem.
	 *
	 * @param selectedSubsystem the new selected subsystem
	 */
	public void setSelectedSubsystem(String selectedSubsystem) {
		this.selectedSubsystem = selectedSubsystem;
	}
	
	/**
	 * Gets the current state description.
	 *
	 * @return the current state description
	 */
	public String getCurrentStateDescription() {
		return currentStateDescription;
	}
	
	/**
	 * Sets the current state description.
	 *
	 * @param currentStateDescription the new current state description
	 */
	public void setCurrentStateDescription(String currentStateDescription) {
		this.currentStateDescription = currentStateDescription;
	}
	
	/**
	 * Gets the desired change description.
	 *
	 * @return the desired change description
	 */
	public String getDesiredChangeDescription() {
		return desiredChangeDescription;
	}
	
	/**
	 * Sets the desired change description.
	 *
	 * @param desiredChangeDescription the new desired change description
	 */
	public void setDesiredChangeDescription(String desiredChangeDescription) {
		this.desiredChangeDescription = desiredChangeDescription;
	}
	
	/**
	 * Gets the desired change explanation.
	 *
	 * @return the desired change explanation
	 */
	public String getDesiredChangeExplanation() {
		return desiredChangeExplanation;
	}
	
	/**
	 * Sets the desired change explanation.
	 *
	 * @param desiredChangeExplanation the new desired change explanation
	 */
	public void setDesiredChangeExplanation(String desiredChangeExplanation) {
		this.desiredChangeExplanation = desiredChangeExplanation;
	}
	
	/**
	 * Gets the desired change comments.
	 *
	 * @return the desired change comments
	 */
	public String getDesiredChangeComments() {
		return desiredChangeComments;
	}
	
	/**
	 * Sets the desired change comments.
	 *
	 * @param desiredChangeComments the new desired change comments
	 */
	public void setDesiredChangeComments(String desiredChangeComments) {
		this.desiredChangeComments = desiredChangeComments;
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
	 * Gets the current step.
	 *
	 * @return the current step
	 */
	public String getCurrentStep() {
		return currentStep;
	}
	
	/**
	 * Sets the current step.
	 *
	 * @param currentStep the new current step
	 */
	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
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
	 * Gets the actual step.
	 *
	 * @return the actual step
	 */
	public String getActualStep() {
		return actualStep;
	}

	/**
	 * Sets the actual step.
	 *
	 * @param actualStep the new actual step
	 */
	public void setActualStep(String actualStep) {
		this.actualStep = actualStep;
	}
	
	/**
	 * Gets the actutal step by current step.
	 *
	 * @param currentStep the current step
	 * @return the actutal step by current step
	 */
	private String getActutalStepByCurrentStep(String currentStep)
	{
		String actualStep;
		switch(currentStep)
		{
		
			case "ANALYZER_AUTO_APPOINT":
			case "ANALYSIS_SET_TIME":
			case "ANALYSIS_APPROVE_TIME":
			case "ANALYSIS_WORK":
				actualStep = "Analysis";
				break;
			
			case "EXECUTION_LEADER_SUPERVISOR_APPOINT":
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
			case "CLOSING_STEP":
				actualStep = "Closing step";
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
