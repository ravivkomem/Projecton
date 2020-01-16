package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.AnalyzerBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.Step;
import entities.User;
import javafx.application.Platform;

/**
 * 
 * @author Lior Kauffman
 *This controller handle with the Analyzer page
 */
@SuppressWarnings("serial")
public class AnalyzerController extends BasicController {
	
	private AnalyzerBoundary myBoundary;
	
	public AnalyzerController(AnalyzerBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	/* **************************************
	 * ************ Public Methods **********
	 * **************************************/
	/**
	 * this method create sql query that ask from data base analysis_step for change request
	 * @param changeRequestID
	 */
	public void getCurrentStep(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_STEP_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	/**
	 * this method create sql query that update the estimatedDate by analysisStepId in the ANALYSIS_STEP data base
	 * @param analysisStepId
	 * @param estimatedDate
	 */
	public void updateAnalysisStepEstimatedEndDate(Integer analysisStepId,Date estimatedDate) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedDate);
		varArray.add(analysisStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYSIS_STEP_ESTIMATED_DATE,varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	/**
	 * this method create sql query that update the current step by id to data base
	 * @param changeRequestID
	 * @param handlerUserName
	 * @param currentstep
	 */
	public void updateChangeRequestCurrentStep(String currentstep, String handlerUserName, Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentstep);
		varArray.add(handlerUserName);
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP,varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * this method create sql query that update current step handler user name and date by change request id to change request data base
	 * @param changerequest
	 * @param currentstep
	 * @param handlerusername
	 * @param date
	 */
	
	public void updateChangeRequestCurrentStepAndHandlerName(ChangeRequest changerequest,String currentstep,String handlerusername,Date date) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentstep);
		varArray.add(handlerusername);
		varArray.add(date);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	/**
	 * this method create sql query that update analysis step data base to closed
	 * @param changerequest
	 * @param date
	 * @param Status
	 * @param AnalysisReportHeader
	 * @param AnalysisReportDescription
	 * @param AnalysisReportAdvantages
	 * @param AnalysisReportDuration
	 * @param AnalysisReportConstraints
	 */
	
	//"UPDATE icm.analysis_step SET EndDate = ?,Status = ?,AnalysisReportHeader = ?,AnalysisReportDescription = ?,AnalysisReportAdvantages = ?,AnalysisReportDuration = ?,AnalysisReportConstraints = ? WHERE ChangeRequestID = ?";
	public void updateAnalysisStepClose(ChangeRequest changerequest,Date date,String Status,String AnalysisReportHeader, String AnalysisReportDescription,String AnalysisReportAdvantages,Date AnalysisReportDuration,String AnalysisReportConstraints) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(date);
		varArray.add(Status);
		varArray.add(AnalysisReportHeader);
		varArray.add(AnalysisReportDescription);
		varArray.add(AnalysisReportAdvantages);
		varArray.add(AnalysisReportDuration);
		varArray.add(AnalysisReportConstraints);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYSIS_STEP_CLOSE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	/**
	 * this method create sql query that insert new committee step to data base
	 * @param changeRequestID
	 * @param UserName
	 * @param StartDate
	 * @param Status
	 * @param estimatedDate
	 * 
	 */
	public void insertNewCommitteeStep(Integer ChangeRequestId, String UserName,Date StartDate,String Status,Date estimatedDate) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ChangeRequestId);
		varArray.add(UserName);
		varArray.add(StartDate);
		varArray.add(Status);
		varArray.add(estimatedDate);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COMMITTEE_STEP_FROM_ANALYZER,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	/**
	 * this method create sql query that ask for committee director
	 * 
	 */
	public void getCommitteeDirector() {
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_COMMITTEE_DIRECTOR,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * this method create sql query that update the time extension if necessary .
	 *
	 * @param stepID the step ID
	 * @param stepType the step type
	 */
	public void updateTimeExtensionDB(int stepID, String stepType) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(stepID);
		varArray.add(stepType);
		SqlAction sqlAction = new SqlAction(SqlQueryType.AUTOMATIC_CLOSE_NEW_TIME_EXTENSION,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}	

	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
		switch (result.getActionType()) {
		
			case UPDATE_CHANGE_REQUEST_CURRENTSTEP:
			case UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME:
			case UPDATE_ANALYSIS_STEP_ESTIMATED_DATE:
			case UPDATE_ANALYSIS_STEP_CLOSE:
//				int affectedRows;
//				affectedRows = (Integer) (result.getResultData().get(0).get(0));
				this.unsubscribeFromClientDeliveries();
//				myBoundary.updateTesterPageToDBSuccessfully(affectedRows);
				break;
			case UPDATE_CHANGE_REQUEST_CURRENT_STEP:
				this.unsubscribeFromClientDeliveries();
				break;
			case SELECT_ANALYSIS_STEP_BY_CHANGE_REQUEST_ID:
				this.unsubscribeFromClientDeliveries();
				Step recievedStep = this.parseSqlResultToAnalysisStep(result);
				myBoundary.recieveCurrentStep(recievedStep);
				break;
			case GET_COMMITTEE_DIRECTOR:
				this.unsubscribeFromClientDeliveries();
				String UserName =(String) (result.getResultData().get(0).get(0));
				myBoundary.getCommitteeDirectorUserName(UserName);
				break;
			case INSERT_NEW_COMMITTEE_STEP_FROM_ANALYZER:
				this.unsubscribeFromClientDeliveries();
				break;
			case AUTOMATIC_CLOSE_NEW_TIME_EXTENSION:
				this.unsubscribeFromClientDeliveries();
				break;
			default:
				break;
		}
		});
		return;
	}
	
	/**
	 * this method create new step from sqlResult
	 * @param result
	 */
	private Step parseSqlResultToAnalysisStep(SqlResult result) {
		
		StepType testerType = StepType.ANALYSIS;
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
