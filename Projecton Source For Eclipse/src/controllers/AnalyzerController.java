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
import javafx.application.Platform;

/**
 * The Class AnalyzerController.
 *
 * @author Lior Kauffman
 * This controller handle with the Analyzer page.
 */
@SuppressWarnings("serial")
public class AnalyzerController extends BasicController {
	
	/**  The boundary controlled by this controller. */
	private AnalyzerBoundary myBoundary;
	
	
	/**
	 * Instantiates a new analyzer controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public AnalyzerController(AnalyzerBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	/* **************************************
	 * ************ Public Methods **********
	 * **************************************/
	
	/**
	 * This method create sql query that ask for analysis step by change request id form analysis_step table.
	 *
	 * @param changeRequestID the change request ID
	 * @return the current step
	 */
	public void getCurrentStep(Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_STEP_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * This method create sql query that update the estimated date in the analysis_step table.
	 *
	 * @param analysisStepId the analysis step id
	 * @param estimatedDate the estimated date
	 */
	public void updateAnalysisStepEstimatedEndDate(Integer analysisStepId,Date estimatedDate) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedDate);
		varArray.add(analysisStepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYSIS_STEP_ESTIMATED_DATE,varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * This method create sql query that update the current step by change request id in the change_request table.
	 *
	 * @param currentstep the currentstep
	 * @param handlerUserName the handler user name
	 * @param changeRequestId the change request id
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
	 * This method create sql query that update current step, handler user name 
	 * and date by change request id in the change_request table.
	 * 
	 * @param changeRequest - the change request 
	 * @param currentStep - the current step
	 * @param handlerUserName - the handler user name
	 * @param date - the date
	 */
	public void updateChangeRequestCurrentStepAndHandlerName(ChangeRequest changeRequest,String currentStep,String handlerUserName,Date date) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentStep);
		varArray.add(handlerUserName);
		varArray.add(date);
		varArray.add(changeRequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * This method create sql query that update analysis step with analysis report details in the
	 * analysis_step table.
	 *
	 * @param changerequest the change request
	 * @param date the date
	 * @param Status the status
	 * @param AnalysisReportHeader the analysis report header
	 * @param AnalysisReportDescription the analysis report description
	 * @param AnalysisReportAdvantages the analysis report advantages
	 * @param AnalysisReportDuration the analysis report duration
	 * @param AnalysisReportConstraints the analysis report constraints
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
	 * This method create sql query that insert new committee step to committee_step table.
	 *
	 * @param ChangeRequestId the change request id
	 * @param UserName the user name
	 * @param StartDate the start date
	 * @param Status the status
	 * @param estimatedDate the estimated date
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
	 * This method create sql query that ask for the user with committee director permission.
	 *
	 * @return the committee director
	 */
	public void getCommitteeDirector() {
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_COMMITTEE_DIRECTOR,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * This method create sql query that update the time extension if necessary .
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
	
	/**
	 * Gets the change request from the database by change request id.
	 *
	 * @param changeRequestId the change request id
	 * @return the change request by id
	 */
	public void getChangeRequestById(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_CHANGE_REQUEST_BY_ID,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
		switch (result.getActionType()) {
		
			case UPDATE_CHANGE_REQUEST_CURRENTSTEP:
			case UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME:
			case UPDATE_ANALYSIS_STEP_ESTIMATED_DATE:
			case UPDATE_ANALYSIS_STEP_CLOSE:
				this.unsubscribeFromClientDeliveries();
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
			case SELECT_CHANGE_REQUEST_BY_ID:
				this.unsubscribeFromClientDeliveries();
				myBoundary.getCurrentChangeRequestAfterRefresh(parseSqlResultToChangeRequest(result));
				break;
			default:
				break;
		}
		});
		return;
	}
	
	/**
	 * Parses the sql result to change request.
	 *
	 * @param result the result
	 * @return The change request the result represents
	 */
	private ChangeRequest parseSqlResultToChangeRequest(SqlResult result) {
		ChangeRequest request = new ChangeRequest((Integer)result.getResultData().get(0).get(0),
				(String)result.getResultData().get(0).get(1),(Date)result.getResultData().get(0).get(2),
				(String)result.getResultData().get(0).get(3), (String)result.getResultData().get(0).get(4), 
				(String)result.getResultData().get(0).get(5), (String)result.getResultData().get(0).get(6), 
				(String)result.getResultData().get(0).get(7), (String)result.getResultData().get(0).get(8), 
				(String)result.getResultData().get(0).get(9), (String)result.getResultData().get(0).get(10), 
				(String)result.getResultData().get(0).get(14), (Date)result.getResultData().get(0).get(15));
		return request;
	}
	
	/**
	 * This method create new step from the sqlResult.
	 *
	 * @param result the result
	 * @return the step the result represnts
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
