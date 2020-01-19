package controllers;

import java.sql.Date;
import java.util.ArrayList;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.SupervisorBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.TimeExtension;
import javafx.application.Platform;


/**
 * The Class SupervisorController.
 *
 * @author Itay David
 */
@SuppressWarnings("serial")
public class SupervisorController extends BasicController
{
	/** The boundary controlled by this controller */
	private SupervisorBoundary myBoundary;

	/**
	 * Instantiates a new supervisor controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public SupervisorController(SupervisorBoundary myBoundary)
	{
		this.myBoundary = myBoundary;
	}

	/**
	 *  The method insert new supervisor update to data base
	 * @param id - The change request ID
	 * @param name - The supervisor user name
	 * @param essence - The update essence
	 * @param updateDate - The update date
	 * @param fullName - The supervisor full name
	 */
	public void inserntNewSupervisorUpdate(Integer id, String name, String essence, Date updateDate, String fullName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(id);
		varArray.add(name);
		varArray.add(essence);
		varArray.add(updateDate);
		varArray.add(fullName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_SUPERVISOR_UPDATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				
				case SELECT_ALL_CHANGE_REQUEST:
					ArrayList<ChangeRequest> resultList=new ArrayList<>();
					resultList.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList);
					break;
				case SELECT_ALL_TIME_EXTENSIONS:
					ArrayList<TimeExtension> resultList4=new ArrayList<>();
					resultList4.addAll(this.changeResultToTimeExtension(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleTimeExtensionForTable(resultList4);
					break;
					
				case SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS:
					ArrayList<ChangeRequest> resultList2=new ArrayList<>();
					resultList2.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList2);
					break;
				case SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS:
					ArrayList<ChangeRequest> resultList3=new ArrayList<>();
					resultList3.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList3);
					break;
				case SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE:
					ArrayList<ChangeRequest> resultList5=new ArrayList<>();
					resultList5.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList5);
					break;
				case UPDATE_CURRENT_STEP_TO_ANALYZER_SUPERVISOR_APPOINT:
					int affectedRows;
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowAnalyzerSupervisorAppointToast(affectedRows);
					break;	
				case UPDATE_ANALYZER_BY_SUPERVISOR:
					int affectedRows2;
					affectedRows2 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowSuccessAnalyzerAppoint(affectedRows2);
					break;
				case INSERT_NEW_ANALYSIS_STEP:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_STEP_TO_ANALYSIS_SET_TIME:
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowSuccessAproveAppoint();
					break;
				case UPDATE_NEW_EXECUTION_LEADER:	
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowAppointExecutionLeaderSuccess();
					break;
				case INSERT_NEW_EXECUTION_STEP:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_ANALYSIS_SET_TIME:
					myBoundary.showDenyAnalysisTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_ANALYSIS_SET_TIME:	
					myBoundary.showApproveAnalysisTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_EXECUTION_SET_TIME:
					myBoundary.showApproveExecutionTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_EXECUTION_SET_TIME:
					myBoundary.showDenyExecutionTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_ALL_ENGINEERS:
					ArrayList<String> employees;
					employees = this.createArrayListOfUserName(result);
					myBoundary.recieveAllInformationEngineers(employees);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STATUS_TO_CLOSED:
					myBoundary.showChangeRequestClosed();
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_EXECUTION_ESTIMATED_DATE:
					Date res = (Date)result.getResultData().get(0).get(0);
					myBoundary.getExecutionRequiredTime(res);
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_ANALYSIS_ESTIMATED_DATE:
					Date res2 = (Date)result.getResultData().get(0).get(0);
					myBoundary.getAnalysisRequiredTime(res2);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_TIME_EXTENSION_STATUS_TO_APPROVED:
					myBoundary.showApproveTimeExtension();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_ANALYSIS_STEP_ESTIMATED_END_DATE:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_COMMITTEE_STEP_ESTIMATED_END_DATE:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_TESTER_STEP_ESTIMATED_END_DATE:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_TIME_EXTENSION_STATUS_TO_DENY:
					myBoundary.showDenyTimeExtension();
					this.unsubscribeFromClientDeliveries();
					break;
				case INSERT_NEW_SUPERVISOR_UPDATE:
					this.unsubscribeFromClientDeliveries();
					break;
				default:
					break;
			}
		});
		return;	
	}

/**
 * The method create ArrayList From Users
 * 
 * @param result the result received from the DB
 * @return An arraylist of user names
 */
	private ArrayList<String> createArrayListOfUserName(SqlResult result)
	{
		ArrayList<String> res = new ArrayList<String>();
		for(ArrayList<Object> user : result.getResultData())
		{
			res.add((String)user.get(1));
		}
		return res;
		
	}

/**
 * This method parse result from DB into change requests
 * 
 * @param result the result
 * @return ArrayList of ChangeRequest
 */
	private ArrayList<ChangeRequest> changeResultToChangerequest(SqlResult result)    
	{
		ArrayList<ChangeRequest> resultList=new ArrayList<>();
		for(ArrayList<Object> resultRow: result.getResultData())
		{
			int changeRequestId = (Integer)resultRow.get(0);
			String initiatorUserName = (String)resultRow.get(1);
			Date startDate = (Date)resultRow.get(2);
			String selectedSubsystem = (String)resultRow.get(3);
			String currentStateDescription = (String)resultRow.get(4);
			String desiredChangeDescription = (String)resultRow.get(5);
			String desiredChangeExplanation = (String)resultRow.get(6);
			String desiredChangeComments = (String)resultRow.get(7);
			String status = (String)resultRow.get(8);
			String currentStep = (String)resultRow.get(9);
			String handlerUserName = (String)resultRow.get(10);
			Date endDate = (Date)resultRow.get(11);
			String email = (String)resultRow.get(12);
			String jobDescription = (String)resultRow.get(13);
			String fullName = (String)resultRow.get(14);
			Date estimatedDate = (Date)resultRow.get(15);
			
			ChangeRequest changeRequest = new ChangeRequest(
					changeRequestId, initiatorUserName, startDate, selectedSubsystem,
					currentStateDescription, desiredChangeDescription, desiredChangeExplanation, desiredChangeComments,
					status, currentStep, handlerUserName, endDate, email, jobDescription, fullName, estimatedDate);
			resultList.add(changeRequest);
		}
		return resultList;	
	}
	
	
	/**
	 * Create array list of time extension from sql result
	 *
	 * @param result the result
	 * @return  array list of time extension
	 */
	private ArrayList<TimeExtension> changeResultToTimeExtension(SqlResult result)
	{
		ArrayList<TimeExtension> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData())
		{
			TimeExtension timeExtension = new TimeExtension((Integer)a.get(0),(Integer)a.get(1),(String)a.get(2),
					(Date)a.get(3),(Date)a.get(4),(String)a.get(5),(String)a.get(6));
			resultList.add(timeExtension);
		}
		return resultList;	
	}


/**
 * This method get all the change requests.
 * Send SQL action to the server to get all the change requests
 */
	public void getAllChangeRequests()      
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * This method get all the change request for appointments.
 * Send SQL action to the server to get all the appointments
 * I.E change requests that require analysis appointment (either approve system auto appoint)
 * or for the supervisor to set an analyzer, or execution leader
 */
	public void SelectChangeRequestForAppointments()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * This method get all the change request for approvals.
 * Send SQL action to the server to get all the request for approvals
 * I.E change requests that require analysis time approval or execution time approval
 */
	public void SelectAllChangeRequestForApprovals()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


	/**
	 * This method get all the change request for close requests.
	 * Send SQL action to the server to get all the change requests that require the supervisor to close them
	 * Either change requests in Deny Step or in Close Step.
	 */
	public void SelectAllChangeRequestForClose()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * This method change the step into supervisor appointment
 * Send SQL action to the server to update the change request current step to "ANALYZER_SUPERVISOR_APPOINT"
 * @param changeRequestID - The change request ID
 */
	public void changeCurrentStepFromAnalyzerAutoAppoint(int changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("ANALYZER_SUPERVISOR_APPOINT");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CURRENT_STEP_TO_ANALYZER_SUPERVISOR_APPOINT, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * Update new analyzer by supervisor.
 *
 * @param newAnalyzerBySupervisorAppoint the new analyzer by supervisor appoint
 * @param changeRequestID - The change request ID
 * 
 * Send SQL action to the server to update the handler user name of the change request ID
 */
	public void UpdateNewAnalyzerBySupervisor(String newAnalyzerBySupervisorAppoint, Integer changeRequestID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("ANALYSIS_SET_TIME");
		varArray.add(newAnalyzerBySupervisorAppoint);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYZER_BY_SUPERVISOR, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * Insert new analysis step.
 *
 * @param changeRequestID the change request ID
 * @param handlerUserName the user that will handle the analysis
 * @param updateStepDate the update step date
 * @param status - the status of the analysis step (Should be "ACTIVE")
 * 
 * Send SQL Action to the server to insert new analysis step
 */
	public void InsertNewAnalysisStep(Integer changeRequestID, String handlerUserName, Date updateStepDate,
			String status)
	{
	
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(handlerUserName);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ANALYSIS_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * Change current step to analysis set time.
 *
 * @param changeRequestID - The change request ID
 * 
 * 
 * Send SQL Action to the server to set the change request current step to "ANALYSIS_SET_TIME"
 */
	public void changeCurrentStepToAnalysisSetTime(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("ANALYSIS_SET_TIME");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STEP_TO_ANALYSIS_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * Insert new analysis step after approve.
 *
 * @param changeRequestID the change request ID
 * @param handlerUserName the handler user name
 * @param updateStepDate the update step date
 * @param status - the step status
 * 
 * Send SQL Action to the server to insert new analysis step
 */
	public void InsertNewAnalysisStepAfterApprove(Integer changeRequestID, String handlerUserName, Date updateStepDate,
			String status)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(handlerUserName);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ANALYSIS_STEP_AFTER_APPROVE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * Update execution leader by supervisor.
 *
 * @param executionLeader the execution leader
 * @param nextStep the next step
 * @param changeRequestID 
 * 
 * Send SQL Action to the server to update the change request current step and handler user name
 * (Will be called when a change request is moved to the execution step)
 */
	public void UpdateExecutionLeaderBySupervisor(String executionLeader,String nextStep, Integer changeRequestID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		
		varArray.add(executionLeader);
		varArray.add(nextStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_NEW_EXECUTION_LEADER, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * Insert new execution leader step.
 *
 * @param changeRequestID the change request ID
 * @param handlerUserName the handler user name
 * @param updateStepDate the update step date
 * @param status - the step status
 * 
 * Send SQL Action to the server to insert new execution step
 */
	public void InsertNewExecutionLeaderStep(Integer changeRequestID, String handlerUserName, Date updateStepDate,
			String status)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(handlerUserName);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_EXECUTION_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * This method update change request after deny analysis time
 *
 * @param lastStep the last step
 * @param changeRequestID  - the change request ID
 * 
 * Send SQL Action to the server to update the change request current step back to the last step
 * Because the analysis time was denied, the analyzer shall request time again
 */
	public void denyAnalysisTime(String lastStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(lastStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_ANALYSIS_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
		
	}


/**
 * This method update change request after approve analysis time
 *
 * @param nextStep the next step
 * @param changeRequestID - the change request ID
 * 
 * Send SQL Action to the server update the change request current step to the next step
 * Will be called after analysis time was approved by the supervisor
 */
	public void approvedAnalysisTime(String nextStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(nextStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_ANALYSIS_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 *This method update change request table after supervisor approve execution time
 *
 * @param nextStep the next step
 * @param changeRequestID - the change request ID
 * 
 * Send SQL Action to the server update the change request current step to the next step
 * Will be called after execution time was approved
 */
	public void approvedExecutionTime(String nextStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(nextStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_EXECUTION_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * This method update change request step after supervisor deny execution time
 *
 * @param lastStep the last step
 * @param changeRequestID - the change request ID
 * 
 * 
 * Send SQL Action to the server update the change request current step to the last step
 * Will be called after execution time was denied by the supervisor
 * Therefore the execution leader will have to select time again for his step
 */
	public void denyExecutionTime(String lastStep, Integer changeRequestID)
	{

		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(lastStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_EXECUTION_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	
	/**
	 * This method is called by the supervisor boundary during the initallize 
	 * Send SQL action to the server inorder to get all the system information engineers
	 */
	public void setComboBox()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_ENGINEERS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * Sets the status to closed.
 *
 * @param updateStepDate the update step date
 * @param newStatus the new status
 * @param newStep the new step
 * @param changeRequestID - Change request ID
 * 
 * Send SQL action to the server to update the change request status to closed and the current step to finished
 */
	public void setStatusToClosed(Date updateStepDate,String newStatus,String newStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStatus);
		varArray.add(newStep);
		varArray.add(updateStepDate);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_CLOSED, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * Gets the execution estimated date.
 *This method is called by the supervisor boundary
 *
 * @param changeRequestID - The change request ID
 * 
 * Send SQL action to the server to get the execution leader estimated date for completion of his stage
 */
	public void getExecutionEstimatedDate(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_EXECUTION_ESTIMATED_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * Gets the analysis estimated date.
 *
 * @param changeRequestID - the change request Id
 * 
 * Send SQL action to the server to get the analysis estimated date for completion of his stage
 * 
 */
	public void getAnalysisEstimatedDate(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_ESTIMATED_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}


/**
 * This method update close step end date
 *
 * @param updateStepDate the update step date
 * @param newStatus the new status
 * @param changeRequestID  - the change request ID
 * 
 * Send SQL action to the server to update the change request end date in the DB
 */
	public void setEndDate(Date updateStepDate,String newStatus, Integer changeRequestID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(updateStepDate);
		varArray.add(newStatus);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_END_DATE_IN_CLOSING_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}

	/**
	 * Select all time extensions.
	 * 
	 * Send SQL action to the server in order to get all the time extensions in the DB
	 * With the status of 'NEW' to represent that this is a new time extension that require
	 * the supervisor attention to either deny or approve.
	 * 
	 */
	public void selectNewTimeExtensions()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_TIME_EXTENSIONS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

	/**
	 * Update time extension status.
	 *
	 * @param status - The new status
	 * @param timeExtensionID - The time extensions ID
	 * 
	 * Send SQL action to the server to update the status of the time extension with the following ID
	 */
	public void updateTimeExtensionStatus(String status, int timeExtensionID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(status);
		varArray.add(timeExtensionID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TIME_EXTENSION_STATUS_TO_APPROVED, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}

	/**
	 * Update analysis step estimated end date.
	 *
	 * @param newDate the new date
	 * @param stepID the step ID
	 * 
	 * Send SQL action to the server to update the estimated end date of 
	 *  Analysis step with the following ID 
	 *  to be the new date
	 */
	public void updateAnalysisStepEstimatedEndDate(Date newDate, int stepID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newDate);
		varArray.add(stepID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYSIS_STEP_ESTIMATED_END_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	/**
	 * Update committee step estimated end date.
	 *
	 * @param newDate the new date
	 * @param stepID the step ID
	 * 
	 *  Send SQL action to the server to update the estimated end date of 
	 *  Committee step with the following ID 
	 *  to be the new date
	 */
	public void updateCommitteeStepEstimatedEndDate(Date newDate, int stepID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newDate);
		varArray.add(stepID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_COMMITTEE_STEP_ESTIMATED_END_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	/**
	 * Update execution step estimated end date.
	 *
	 * @param newDate the new date
	 * @param stepID the step ID
	 * 
	 *  Send SQL action to the server to update the estimated end date of 
	 *  Execution step with the following ID 
	 *  to be the new date
	 */
	public void updateExecutionStepEstimatedEndDate(Date newDate, int stepID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newDate);
		varArray.add(stepID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}

	/**
	 * Update testing step estimated end date.
	 *
	 * @param newDate the new date
	 * @param stepID the step ID
	 * 
	 *  Send SQL action to the server to update the estimated end date of 
	 *  Testing step with the following ID 
	 *  to be the new date
	 */
	public void updateTestingStepEstimatedEndDate(Date newDate, int stepID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newDate);
		varArray.add(stepID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TESTER_STEP_ESTIMATED_END_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}

	/**
	 * Update time extension status after deny.
	 *
	 * @param status the status
	 * @param stepID the step ID
	 * 
	 * Send SQL action to the server to update the time extension status to "DENY"
	 * after the supervisor press on "Deny" button in the boundary display
	 */
	public void updateTimeExtensionStatusAfterDeny(String status, int stepID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(status);
		varArray.add(stepID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TIME_EXTENSION_STATUS_TO_DENY, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}
	
}
