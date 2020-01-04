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
	
	/**
	 * This method get change request id
	 * and set in motion sql action to get the most recent tester step for that change request
	 * @param changeRequestID
	 */
	public void getCurrentStep(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
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
				this.unsubscribeFromClientDeliveries();
				int affectedRows;
				affectedRows = (Integer) (result.getResultData().get(0).get(0));
				myBoundary.updateTesterPageToDBSuccessfully(affectedRows);
				break;
			case SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID:
				this.unsubscribeFromClientDeliveries();
				Step recievedStep = this.parseSqlResultToTesterStep(result);
				myBoundary.recieveCurrentStep(recievedStep);
				break;
			default:
				break;
			}
		});
		return;
	}

	/**
	 * This method receives SqlResult and parse it to Step
	 * Be careful as this method expect the fields to be presented in a specific order
	 * @param result
	 * @return Step
	 */
	private Step parseSqlResultToTesterStep(SqlResult result) {
		
		StepType testerType = StepType.TESTING;
		int testerStepId = (int) result.getResultData().get(0).get(0);
		int changeRequestId = (int) result.getResultData().get(0).get(1);
		String handlerUserName = (String) result.getResultData().get(0).get(2);
		Date startDate = (Date) result.getResultData().get(0).get(3);
		String status = (String) result.getResultData().get(0).get(4);
		Date estimatedEndDate = (Date) result.getResultData().get(0).get(5);
		Date endDate = (Date) result.getResultData().get(0).get(6);
		
		Step step = new Step(testerType, testerStepId, changeRequestId, handlerUserName, startDate, status,
				estimatedEndDate, endDate);
		return step;
	}
}
