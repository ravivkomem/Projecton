package controllers;

import java.sql.Date;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.ExecutionLeaderBoundry;
import client.ClientConsole;
import entities.ExecutionAproves;
import entities.Step;
import javafx.application.Platform;
/**
 * 
 * @author Itay David
 *
 */

public class ExecutionLeaderController extends BasicController {
	private ExecutionLeaderBoundry myBoundry;
	

	public ExecutionLeaderController(ExecutionLeaderBoundry myBoundry) {
		this.myBoundry = myBoundry;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param changeRequestID
	 *	This method initialize step 
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
	 * 
	 * @param changeRequestID
	 * This method update DB change request step to execution approve time
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

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_EXECUTIOM_STEP_DETAILS:
					this.unsubscribeFromClientDeliveries();
					if(result.getResultData().get(0).isEmpty())
					{
						myBoundry.handleDataBaseSelectionError();
						break;
					}
					Step executionStep = new Step(StepType.EXECUTION,(Integer)result.getResultData().get(0).get(0),(Integer) result.getResultData().get(0).get(1),(String) result.getResultData().get(0).get(2),
							(Date) result.getResultData().get(0).get(4), (String) result.getResultData().get(0).get(3),
							(Date) result.getResultData().get(0).get(5), (Date)result.getResultData().get(0).get(6));
					myBoundry.recieveExecutionStep(executionStep);
					break;
				case UPDATE_CHANGE_REQUEST_CURRENT_STEP:
					this.unsubscribeFromClientDeliveries();
					int affectedRows;
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					myBoundry.recieveEstimatedEndDateUpdateStatus(affectedRows);
					break;
					
				case UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID:
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundry.ExecutionAprovedtInsertToDBSuccessfully(affectedRows);
					break;
				case UPDATE_NEW_EXECUTION_APPROVE_TIME_STATUS:
					int affectedRows2;
					affectedRows2 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundry.ExecutionAprovedtInsertToDBSuccessfully(affectedRows2);
					break;
				case SELECT_IF_CURRENT_STEP_CHANGED_TO_EXECUTION_WORK:
					String currentStep = (String)(result.getResultData().get(0).get(0));
					if(currentStep.equals("EXECUTION_WORK"))
					{		
					//myBoundry.ShowCommitButton();
					this.unsubscribeFromClientDeliveries();
					break;
					}
					else if(currentStep.equals("EXECUTION_SET_TIME"))
					{
						myBoundry.chooseAgainTimeForExecution();
						break;
						
					}
					else
					{
						//myBoundry.SupervisorDidNotAproveYet();
						break;
					}
				case SELECT_ESTIMATED_DATE_MINUS_START_DATE:
					Date estimatedDate = (Date)(result.getResultData().get(0).get(0));
					//myBoundry.ShowEstimatedDateMinusStartDate(estimatedDate);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP:
					int affectedRows3;
					affectedRows3 =(Integer) (result.getResultData().get(0).get(0));
					//myBoundry.ShowFinishToast(affectedRows3);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CURRENT_STEP_TO_TESTER:
					this.unsubscribeFromClientDeliveries();
					break;
					
				default:
					break;
			
			}
		});
		return;
		
	}
	/**
	 * 
	 * @param estimatedEndDate
	 * @param executionStepId
	 * This method insert new estimated date to execution step and change request 
	 */
	public void updateExecutionStepEstimatedEndDate(Date estimatedEndDate,Integer executionStepId) {
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedEndDate);
		varArray.add(executionStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * 
	 * @param changeRequestID
	 * This method get current step to check if its execution work
	 */
	public void SelectCurrentStepIfItsExecutionWork(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_IF_CURRENT_STEP_CHANGED_TO_EXECUTION_WORK, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}
	/**
	 * 
	 * @param changeRequestID
	 * This method get estimated date and check with start date 
	 */
	public void SelectEstimatedDateMinusStartDate(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ESTIMATED_DATE_MINUS_START_DATE, varArray);	
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}
	/**
	 * 
	 * @param changeRequestID
	 * @param comment
	 * This method update DB execution step with the comment
	 */
	public void UpdateExecutionLeaderDateAndStatus(Integer changeRequestID,String comment)
	{
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("CLOSE");
		varArray.add(TimeManager.getCurrentDate());
		varArray.add(comment);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
		
	}
	/**
	 * 
	 * @param changeRequestID
	 * This method update current step in DB to tester committee director appoint 
	 */
	public void UpdateCurrentStepOfChangeRequrstFromExecutionWorkToTesterCommitteeDirectorAppoint(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("TESTER_COMMITTEE_DIRECTOR_APPOINT");
		varArray.add("");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CURRENT_STEP_TO_TESTER, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
		
	}
	
	
}

