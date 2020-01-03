package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.TesterBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.Step;
import javafx.application.Platform;

@SuppressWarnings("serial")
public class TesterController extends BasicController {
	
	private TesterBoundary myBoundary;

	public TesterController(TesterBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	
	public void getStartTimeFromTesterStep(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_TESTER_STEP_START_DATE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void updateChangeRequestStep(ChangeRequest changerequest, String failReport,String Status ,Date date) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(failReport);
		varArray.add(Status);
		varArray.add(date);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TESTER_STEP,varArray);
		
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void updateChangeRequestCurrentStep(String currentStep, String HamdlerUserName, Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentStep);
		varArray.add(HamdlerUserName);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
		switch (result.getActionType()) {
			case UPDATE_TESTER_STEP:
				int affectedRows;
				affectedRows = (Integer) (result.getResultData().get(0).get(0));
				this.unsubscribeFromClientDeliveries();
				myBoundary.updateTesterPageToDBSuccessfully(affectedRows);
				
				break;
			case SELECT_TESTER_STEP_START_DATE:
				Date estimatedEndDate = (Date) (result.getResultData().get(0).get(0));
				myBoundary.displayTimeRemaining(estimatedEndDate);
				break;
			case SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID:
				Step recievedStep = this.parseSqlResultToTesterStep(result);
				myBoundary.getCurrentStep(recievedStep);
				break;
			default:
				break;
			}
		});
		return;
	}

	private Step parseSqlResultToTesterStep(SqlResult result) {
		
		StepType testerType = StepType.TESTING;
		int testerStepId = (int) result.getResultData().get(0).get(0);
		int changeRequestId = (int) result.getResultData().get(0).get(1);
		String handlerUserName = (String) result.getResultData().get(0).get(3);
		Date startDate = (Date) result.getResultData().get(0).get(4);
		String status = (String) result.getResultData().get(0).get(5);
		Date estimatedEndDate = (Date) result.getResultData().get(0).get(6);
		Date endDate = (Date) result.getResultData().get(0).get(7);
		
		Step step = new Step(testerType, testerStepId, changeRequestId, handlerUserName, startDate, status,
				estimatedEndDate, endDate);
		return step;
	}


	public void getCurrentStep(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
}
