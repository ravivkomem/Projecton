package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AnalysisReportBoundary;
import client.ClientConsole;
import entities.ChangeRequest;

public class AnalysisReportController extends BasicController {
	
	AnalysisReportBoundary myBoundary;

	public AnalysisReportController(AnalysisReportBoundary myBoundary) {
		this.myBoundary=myBoundary;
	}
	
	public void askForAnalysisReportByChangeRequestId(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub

	}

}
