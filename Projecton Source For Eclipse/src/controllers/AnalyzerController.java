package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AnalyzerBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;

public class AnalyzerController extends BasicController {
	private AnalyzerBoundary myBoundary;
	public AnalyzerController(AnalyzerBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}
	/*sqlArray[SqlQueryType.INSERT_NEW_REPORT.getCode()]=
			"INSERT INTO icm.analysis_step(ChangeRequestID,Status,AnalysisReportDescription,AnalysisReportAdvantages,AnalysisReportConstraints,EndDate)"
			+ " VALUES (?,?,?,?,?,?)";*/

	public void updateChangeRequestCurrentStep(ChangeRequest changerequest, String currentstep) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentstep);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENTSTEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	public void updateAnalysisStepEstimatedEndDate(ChangeRequest changerequest,Date date) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(date);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYSIS_STEP_ESTIMATED_DATE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	public void updateChangeRequestCurrentStepAndHandlerName(ChangeRequest changerequest,String currentstep,String handlerusername) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentstep);
		varArray.add(handlerusername);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	//"UPDATE icm.analysis_step SET EndDate = ?,Status = ?,AnalysisReportHeader = ?,AnalysisReportDescription = ?,AnalysisReportAdvantages = ?,AnalysisReportDuration = ?,AnalysisReportConstraints = ? WHERE ChangeRequestID = ?";
	public void updateAnalysisStepClose(ChangeRequest changerequest,Date date,String Status,String AnalysisReportDescription,String AnalysisReportAdvantages,String AnalysisReportConstraints) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(date);
		varArray.add(Status);
		varArray.add(AnalysisReportDescription);
		varArray.add(AnalysisReportAdvantages);
		
		varArray.add(AnalysisReportConstraints);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYSIS_STEP_CLOSE,varArray);
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
			int affectedRows;
			affectedRows = (Integer) (result.getResultData().get(0).get(0));
			this.unsubscribeFromClientDeliveries();
			myBoundary.updateTesterPageToDBSuccessfully(affectedRows);
			
			break;
		

		default:
			break;
		}
		});
		return;
	}

}
