package controllers;

import java.sql.Date;
import java.util.ArrayList;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.ExecutionLeaderBoundry;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.Step;
import javafx.application.Platform;


/**
 * The Class ExecutionLeaderController.
 *
 * @author Itay David
 */
@SuppressWarnings("serial")
public class ExecutionLeaderController extends BasicController {
	
	/** The my boundary controlled by this controller. */
	private ExecutionLeaderBoundry myBoundary;
	
	/**
	 * Instantiates a new execution leader controller.
	 *
	 * @param myBoundary the boundary controlled by this controller
	 */
	public ExecutionLeaderController(ExecutionLeaderBoundry myBoundary) {
		this.myBoundary = myBoundary;
	}
	
	/**
	 * Gets the execution step by change request id.
	 * 	This method initialize step
	 * 
	 * @param changeRequestID - The change request ID
	 */
	public void getExecutionStepByChangeRequestId(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_EXECUTIOM_STEP_DETAILS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}
	
	/**
	 * Update change request current step.
	 *
	 * @param newStep the new step
	 * @param handlerUserName the handler user name
	 * @param changeRequestID This method update DB change request step to execution approve time
	 * 
	 * Send SQL action to the server in order to update the change request with the sent ID
	 * To have the sent step and the following handler user name
	 * 
	 * This method is called by the boundary after submission of the execution time required,
	 * In order for the change request to have the step of "EXECUTION_APPRIVE_TIME"
	 */
	public void updateChangeRequestCurrentStep(String newStep ,String handlerUserName, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStep);
		varArray.add(handlerUserName);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Close execution step.
	 *
	 * @param executionStepId the execution step id
	 * @param executionSummary - the execution summary entered by the execution leader
	 * 
	 * Send SQL action to the server in order to close the execution step with the following ID
	 * And also for the step to include the execution summary for future logs and displays
	 *
	 * This method update DB execution step with the comment
	 */
	public void closeExecutionStep(Integer executionStepId,String executionSummary)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("CLOSE");
		varArray.add(TimeManager.getCurrentDate());
		varArray.add(executionSummary);
		varArray.add(executionStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.CLOSE_EXECUTION_STEP, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Advance change request to tester step.
	 *
	 * @param changeRequestID - the change request ID
	 * 
	 * This method update current step of the change request sent in DB to tester committee director appoint
	 */
	public void advanceChangeRequestToTesterStep(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("TESTER_COMMITTEE_DIRECTOR_APPOINT");
		varArray.add("");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CURRENT_STEP_TO_TESTER, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * This method insert new estimated date to execution step and change request .
	 *
	 * @param estimatedEndDate - The estimated end date
	 * @param executionStepId - The execution step ID
	 * 
	 *  Send SQL action to the server in order to update the execution step with the new estimated end date
	 */
	public void updateExecutionStepEstimatedEndDate(Date estimatedEndDate,Integer executionStepId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedEndDate);
		varArray.add(executionStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Gets the updated change request.
	 *
	 * @param changeRequestID the change request ID
	 * 
	 * This method is used to get the change request with the sent ID
	 * In order for the boundary to decide if the the change request step has changed
	 * since the last time it was seen.
	 */
	public void getUpdatedChangeRequest(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_CHANGE_REQUEST_BY_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * this method create sql query that update the time extension if necessary .
	 *
	 * @param stepID the step ID
	 * @param stepType the step type
	 * 
	 * Send SQL action to the server to automatically close the time extension incase
	 * there was an active time extension request and the step has already finished.
	 * Because if the execution leader finished his execution without time extension it was not necessary
	 * and should be active anymore in the database
	 */
	public void updateTimeExtensionDB(int stepID, String stepType) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(stepID);
		varArray.add(stepType);
		SqlAction sqlAction = new SqlAction(SqlQueryType.AUTOMATIC_CLOSE_NEW_TIME_EXTENSION,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
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
				case SELECT_EXECUTIOM_STEP_DETAILS:
					this.unsubscribeFromClientDeliveries();
					if(result.getResultData().get(0).isEmpty())
					{
						myBoundary.recieveExecutionStep(null);
					}
					Step executionStep = new Step(StepType.EXECUTION,(Integer)result.getResultData().get(0).get(0),(Integer) result.getResultData().get(0).get(1),(String) result.getResultData().get(0).get(2),
							(Date) result.getResultData().get(0).get(4), (String) result.getResultData().get(0).get(3),
							(Date) result.getResultData().get(0).get(5), (Date)result.getResultData().get(0).get(6));
					myBoundary.recieveExecutionStep(executionStep);
					break;
					
				case UPDATE_CHANGE_REQUEST_CURRENT_STEP:
					this.unsubscribeFromClientDeliveries();
					myBoundary.loadExecutionApproveTimeDisplay();
					break;
					
					
				case SELECT_CHANGE_REQUEST_BY_ID:
					this.unsubscribeFromClientDeliveries();
					ArrayList<Object> resultRow = result.getResultData().get(0);
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
					myBoundary.updateChangeRequest(changeRequest);
					break;
					
				case CLOSE_EXECUTION_STEP:
				case UPDATE_CURRENT_STEP_TO_TESTER:
				case UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID:
				case AUTOMATIC_CLOSE_NEW_TIME_EXTENSION:
					this.unsubscribeFromClientDeliveries();
					break;
	
				default:
					break;
			
			}
		});
		return;
		
	}
	

	
	
	
}

