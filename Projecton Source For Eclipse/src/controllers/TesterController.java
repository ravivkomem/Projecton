package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.TesterBoundary;
import entities.Step;
import javafx.application.Platform;

/**
 * The Class TesterController.
 *
 * @author Raviv Komem
 */
@SuppressWarnings("serial")
public class TesterController extends BasicController {
	
	/** The boundary controlled by this controller */
	private TesterBoundary myBoundary;

	/**
	 * Instantiates a new tester controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public TesterController(TesterBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}
	
	/**
	 * This method get change request id
	 * and set in motion sql action to get the most recent tester step for that change request.
	 *
	 * @param changeRequestID the change request ID
	 * @return the current step
	 */
	public void getCurrentStep(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Close change request step.
	 *
	 * @param testerStepId the tester step id
	 * @param failReport the fail report
	 */
	public void closeChangeRequestStep(Integer testerStepId, String failReport) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(failReport);
		varArray.add("CLOSED");
		varArray.add(TimeManager.getCurrentDate());
		varArray.add(testerStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TESTER_STEP,varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Advance change request step.
	 *
	 * @param nextStep the next step
	 * @param changeRequestID the change request ID
	 */
	public void advanceChangeRequestStep(String nextStep, Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(nextStep);
		varArray.add("");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP,varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Creates the new closing step.
	 *
	 * @param changeRequestID the change request ID
	 */
	public void createNewClosingStep(int changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(TimeManager.getCurrentDate());
		varArray.add("ACTIVE");
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CLOSING_STEP,varArray);
		this.sendSqlActionToClient(sqlAction);
	}

	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
			case AUTOMATIC_CLOSE_NEW_TIME_EXTENSION:
				this.unsubscribeFromClientDeliveries();
				break;
			case INSERT_NEW_CLOSING_STEP:
				this.unsubscribeFromClientDeliveries();
				break;
			default:
				break;
			}
		});
		return;
	}

	/**
	 * This method receives SqlResult and parse it to Step
	 * Be careful as this method expect the fields to be presented in a specific order.
	 *
	 * @param result the result
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

	/**
	 * Automatic close new time extension.
	 *
	 * @param testerStep the tester step
	 */
	public void automaticCloseNewTimeExtension(Step testerStep) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(testerStep.getStepID());
		varArray.add(testerStep.getType().toString());
		SqlAction sqlAction = new SqlAction(SqlQueryType.AUTOMATIC_CLOSE_NEW_TIME_EXTENSION,varArray);
		this.sendSqlActionToClient(sqlAction);
		
	}
}
