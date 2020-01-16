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
 * 
 * @author Itay David
 *
 */

@SuppressWarnings("serial")
public class ExecutionLeaderController extends BasicController {
	
	private ExecutionLeaderBoundry myBoundry;
	
	public ExecutionLeaderController(ExecutionLeaderBoundry myBoundry) {
		this.myBoundry = myBoundry;
	}
	
	/**
	 * Gets the execution step by change request id.
	 *
	 * @param changeRequestID 	This method initialize step
	 * @return the execution step by change request id
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
	 * Update chnage request current step.
	 *
	 * @param newStep the new step
	 * @param handlerUserName the handler user name
	 * @param changeRequestID This method update DB change request step to execution approve time
	 */
	public void updateChnageRequestCurrentStep(String newStep ,String handlerUserName, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStep);
		varArray.add(handlerUserName);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * 
	 * @param changeRequestID
	 * @param executionSummary
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
	 * 
	 * @param changeRequestID
	 * This method update current step in DB to tester committee director appoint 
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
	 * 
	 * @param estimatedEndDate
	 * @param executionStepId
	 * This method insert new estimated date to execution step and change request 
	 */
	public void updateExecutionStepEstimatedEndDate(Date estimatedEndDate,Integer executionStepId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedEndDate);
		varArray.add(executionStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	public void getUpdatedChangeRequest(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_CHANGE_REQUEST_BY_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			int affectedRows;
			switch(result.getActionType())
			{
				case SELECT_EXECUTIOM_STEP_DETAILS:
					this.unsubscribeFromClientDeliveries();
					if(result.getResultData().get(0).isEmpty())
					{
						myBoundry.recieveExecutionStep(null);
					}
					Step executionStep = new Step(StepType.EXECUTION,(Integer)result.getResultData().get(0).get(0),(Integer) result.getResultData().get(0).get(1),(String) result.getResultData().get(0).get(2),
							(Date) result.getResultData().get(0).get(4), (String) result.getResultData().get(0).get(3),
							(Date) result.getResultData().get(0).get(5), (Date)result.getResultData().get(0).get(6));
					myBoundry.recieveExecutionStep(executionStep);
					break;
					
				case UPDATE_CHANGE_REQUEST_CURRENT_STEP:
					this.unsubscribeFromClientDeliveries();
					myBoundry.loadExecutionApproveTimeDisplay();
//					affectedRows = (Integer) (result.getResultData().get(0).get(0));
//					myBoundry.recieveEstimatedEndDateUpdateStatus(affectedRows);
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
					myBoundry.updateChangeRequest(changeRequest);
					break;
					
				case CLOSE_EXECUTION_STEP:
				case UPDATE_CURRENT_STEP_TO_TESTER:
				case UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID:
					this.unsubscribeFromClientDeliveries();
					break;
	
				default:
					break;
			
			}
		});
		return;
		
	}
	

	
	
	
}

