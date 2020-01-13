package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AnalysisReportBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;

/**
 * 
 * @author Lee Hugi
 *This controller handle with the analysis report page
 *
 */
public class AnalysisReportController extends BasicController {
	
	AnalysisReportBoundary myBoundary;

	public AnalysisReportController(AnalysisReportBoundary myBoundary) {
		this.myBoundary=myBoundary;
	}
	
	public void getAnalysisReportByChangeRequestId(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
			case SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID:
				ArrayList<Object> resultList = createArrayListFromResult(result);
				myBoundary.displayAnalysisReport(resultList);
				break;
			default:
				break;
			}
		});
		return;
	}
	
	private ArrayList<Object> createArrayListFromResult(SqlResult result){
		ArrayList<Object> resultList = new ArrayList<>();
		if(!result.getResultData().isEmpty()) {
			resultList.add(result.getResultData().get(0).get(2));
			resultList.add(result.getResultData().get(0).get(7));
			resultList.add(result.getResultData().get(0).get(8));
			resultList.add(result.getResultData().get(0).get(9));
			resultList.add(result.getResultData().get(0).get(10));
			resultList.add(result.getResultData().get(0).get(11));
			return resultList;
		}
		return null;
	}

}
