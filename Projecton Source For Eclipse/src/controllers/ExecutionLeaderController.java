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

public class ExecutionLeaderController extends BasicController {
	private ExecutionLeaderBoundry myBoundry;
	

	public ExecutionLeaderController(ExecutionLeaderBoundry myBoundry) {
		this.myBoundry = myBoundry;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				
				case INSERT_NEW_EXECUTION_ESTIMATED_TIME:
					int affectedRows;
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
					myBoundry.ShowCommitButton();
					myBoundry.setflag();
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
						myBoundry.SupervisorDidNotAproveYet();
						break;
					}
				case SELECT_ESTIMATED_DATE_MINUS_START_DATE:
					Date estimatedDate = (Date)(result.getResultData().get(0).get(0));
					myBoundry.ShowEstimatedDateMinusStartDate(estimatedDate);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP:
					int affectedRows3;
					affectedRows3 =(Integer) (result.getResultData().get(0).get(0));
					myBoundry.ShowFinishToast(affectedRows3);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CURRENT_STEP_TO_TESTER:
					this.unsubscribeFromClientDeliveries();
				break;
				case SELECT_EXECUTIOM_STEP_DETAILS:
					if(result.getResultData().get(0)==null)
					{
						myBoundry.ErrorInLoadingExecutionPage();
						break;
					}
					Step executionStep=new Step(StepType.EXECUTION,(Integer)result.getResultData().get(0).get(0),(Integer) result.getResultData().get(0).get(1),(String) result.getResultData().get(0).get(2),
							(Date) result.getResultData().get(0).get(4), (String) result.getResultData().get(0).get(3),
							(Date) result.getResultData().get(0).get(5), (Date)result.getResultData().get(0).get(6));
					myBoundry.SetStep(executionStep);
					this.unsubscribeFromClientDeliveries();
					break;
					
				default:
					break;
			
			}
		});
		return;
		
	}

	public void insertNewEstimatedDateToExecutionStepAndChangeRequestIDStep(Date estimatedDateChoosen,Integer changeRequestID) {
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedDateChoosen);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_EXECUTION_ESTIMATED_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	public void updateNewChangeRequestIdStepToExecutionApprovedTime(Integer changeRequestID)
	{
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("EXECUTION_APPROVE_TIME");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_NEW_EXECUTION_APPROVE_TIME_STATUS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

	public void SelectCurrentStepIfItsExecutionWork(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_IF_CURRENT_STEP_CHANGED_TO_EXECUTION_WORK, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

	public void SelectEstimatedDateMinusStartDate(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ESTIMATED_DATE_MINUS_START_DATE, varArray);	
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

	public void UpdateExecutionLeaderDateAndStatus(Integer changeRequestID,String comment)
	{
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("CLOSE");
		varArray.add(myBoundry.getDate());
		varArray.add(comment);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
		
	}

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

	public void GetStepDetails(Integer changeRequestID)
	{
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_EXECUTIOM_STEP_DETAILS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}
	
}

