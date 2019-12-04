package entity;

public class ChangeRequest {
	private int changeRequestID;
	private String initiator;
	private String selectSysystem;
	private String currentStateDiscription;
	private String changeRequestDescription;
	private String changeRequestStatus;
	private String handler;
	
	private String changeRequestComment;
	private String changeRequestDocuments;
	private String changeRequestExplanation;
	private String changeRequestStep;
	private String stepLeader;
	
	public ChangeRequest(String selectSysystem, String currentStateDiscription, String changeRequestDescription,
			int changeRequestID, String changeRequestExplanation, String changeRequestIntiatorName,
			String changeRequestStatus, String handleName) {
		super();
		this.selectSysystem = selectSysystem;
		this.currentStateDiscription = currentStateDiscription;
		this.changeRequestDescription = changeRequestDescription;
		this.changeRequestID = changeRequestID;
		this.changeRequestExplanation = changeRequestExplanation;
		this.initiator = changeRequestIntiatorName;
		this.changeRequestStatus = changeRequestStatus;
		this.handler = handleName;
	}


	public ChangeRequest(int changeRequestID, String initiator, String selectSysystem, String currentStateDiscription,
			String changeRequestDescription, String changeRequestStatus, String handler) {
		super();
		this.changeRequestID = changeRequestID;
		this.initiator = initiator;
		this.selectSysystem = selectSysystem;
		this.currentStateDiscription = currentStateDiscription;
		this.changeRequestDescription = changeRequestDescription;
		this.changeRequestStatus = changeRequestStatus;
		this.handler = handler;
	}


	public int getChangeRequestID() {
		return changeRequestID;
	}


	public void setChangeRequestID(int changeRequestID) {
		this.changeRequestID = changeRequestID;
	}


	public String getInitiator() {
		return initiator;
	}


	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}


	public String getSelectSysystem() {
		return selectSysystem;
	}


	public void setSelectSysystem(String selectSysystem) {
		this.selectSysystem = selectSysystem;
	}


	public String getCurrentStateDiscription() {
		return currentStateDiscription;
	}


	public void setCurrentStateDiscription(String currentStateDiscription) {
		this.currentStateDiscription = currentStateDiscription;
	}


	public String getChangeRequestDescription() {
		return changeRequestDescription;
	}


	public void setChangeRequestDescription(String changeRequestDescription) {
		this.changeRequestDescription = changeRequestDescription;
	}


	public String getChangeRequestStatus() {
		return changeRequestStatus;
	}


	public void setChangeRequestStatus(String changeRequestStatus) {
		this.changeRequestStatus = changeRequestStatus;
	}


	public String getHandler() {
		return handler;
	}


	public void setHandler(String handler) {
		this.handler = handler;
	}


	public String getChangeRequestComment() {
		return changeRequestComment;
	}


	public void setChangeRequestComment(String changeRequestComment) {
		this.changeRequestComment = changeRequestComment;
	}


	public String getChangeRequestDocuments() {
		return changeRequestDocuments;
	}


	public void setChangeRequestDocuments(String changeRequestDocuments) {
		this.changeRequestDocuments = changeRequestDocuments;
	}


	public String getChangeRequestExplanation() {
		return changeRequestExplanation;
	}


	public void setChangeRequestExplanation(String changeRequestExplanation) {
		this.changeRequestExplanation = changeRequestExplanation;
	}


	public String getChangeRequestStep() {
		return changeRequestStep;
	}


	public void setChangeRequestStep(String changeRequestStep) {
		this.changeRequestStep = changeRequestStep;
	}


	public String getStepLeader() {
		return stepLeader;
	}


	public void setStepLeader(String stepLeader) {
		this.stepLeader = stepLeader;
	}


}
