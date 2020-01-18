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
import entities.User;
import javafx.application.Platform;


/**
 * The Class SupervisorController.
 *
 * @author Itay David
 */
@SuppressWarnings("serial")
public class SupervisorController extends BasicController
{
	
	
	

	/** The my boundary. */
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
	 * Gets the user email.
	 *
	 * @param userName the user name
	 * @return the user email
	 */
	public void getUserEmail(String userName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_USER_EMAIL, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
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
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
					int affectedRows3;
					affectedRows3 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					//myBoundary.ShowSuccessAnalyzerAppoint(affectedRows2);
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
				case UPDATE_CHANGE_REQUEST_STATUS_TO_SUSPENDED:
					myBoundary.showChangeRequestSuspended();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STATUS_TO_ACTIVE:
					myBoundary.showChangeRequestUnsuspended();
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
					myBoundary.getExecutionEndDate(res);
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_ANALYSIS_ESTIMATED_DATE:
					Date res2 = (Date)result.getResultData().get(0).get(0);
					myBoundary.getAnalysisEndDate(res2);
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
				case SELECT_USER_EMAIL:
					this.unsubscribeFromClientDeliveries();
					User user = new User((String)result.getResultData().get(0).get(3),
							(String) result.getResultData().get(0).get(4), (String) result.getResultData().get(0).get(5));
					myBoundary.sendEmailToInitiatorUser(user);
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
 * Creates the array list of user name.
 *
 * @param result the result
 * @return The method create ArrayList From Users
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
 * Change result to changerequest.
 *
 * @param result the result
 * @return This method filter result from DB into change requests
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
	 * Change result to time extension.
	 *
	 * @param result the result
	 * @return the array list
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
	 */
	public void SelectAllChangeRequestForClose()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * Change current step from analyzer auto appoint.
 *
 * @param changeRequestID This method change the step into supervisor appointment
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
 * @param changeRequestID This method update new analyzer by the supervisor
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
 * @param selectedItem the selected item
 * @param updateStepDate the update step date
 * @param status This method create new analysis step
 */
	public void InsertNewAnalysisStep(Integer changeRequestID, String selectedItem, Date updateStepDate,
			String status)
	{
	
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(selectedItem);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ANALYSIS_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}


/**
 * Change current step to analysis set time.
 *
 * @param changeRequestID This method change the step into analysis set time
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
 * @param status This method insert new step into analysis table after supervisor approvment
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
 * @param changeRequestID This method update execution leader for a change request after supervisor appoint
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
 * @param status This method insert new execution step
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
 * Deny analysis time.
 *
 * @param lastStep the last step
 * @param changeRequestID This method update change request after deny analysis time
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
 * Approved analysis time.
 *
 * @param nextStep the next step
 * @param changeRequestID This method update change request after approve analysis time
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
 * Approved execution time.
 *
 * @param nextStep the next step
 * @param changeRequestID This method update change request table after supervisor approve execution time
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
 * Deny execution time.
 *
 * @param lastStep the last step
 * @param changeRequestID This method update change request step after supervisor deny execution time
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
 * Suspend change request.
 *
 * @param newStatus the new status
 * @param changeRequestID This method update change request status to suspended
 */
	public void suspendChangeRequest(String newStatus,Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStatus);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_SUSPENDED, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
		
	}


/**
 * Unsuspend change request.
 *
 * @param newStatus the new status
 * @param changeRequestID This method update change request status into active
 */
	public void unsuspendChangeRequest(String newStatus, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStatus);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_ACTIVE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}


/**
 * This method insert into the combo box all the engineers.
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
 * @param changeRequestID This method update change request status into closed
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
 *
 * @param changeRequestID This method get execution estimated time for work
 * @return the execution estimated date
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
 * @param changeRequestID This method get analysis estimated time
 * @return the analysis estimated date
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
 * Sets the end date.
 *
 * @param updateStepDate the update step date
 * @param newStatus the new status
 * @param changeRequestID This method update close step end date
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
 */
public void SelectAllTimeExtensions()
{
	ArrayList<Object> varArray = new ArrayList<>();
	SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_TIME_EXTENSIONS, varArray);
	this.subscribeToClientDeliveries(); // subscribe to listener array
	ClientConsole.client.handleMessageFromClientUI(sqlAction);	
}



/**
 * Update time extension status.
 *
 * @param status the status
 * @param stepID the step ID
 */
public void updateTimeExtensionStatus(String status, int stepID)
{
	ArrayList<Object> varArray = new ArrayList<>();
	varArray.add(status);
	varArray.add(stepID);
	SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TIME_EXTENSION_STATUS_TO_APPROVED, varArray);
	this.subscribeToClientDeliveries(); // subscribe to listener array
	ClientConsole.client.handleMessageFromClientUI(sqlAction);
	
}



/**
 * Update analysis step estimated end date.
 *
 * @param newDate the new date
 * @param stepID the step ID
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
