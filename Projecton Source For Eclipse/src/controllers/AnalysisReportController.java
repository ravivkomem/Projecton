package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AnalysisReportBoundary;
import client.ClientConsole;
import javafx.application.Platform;

/**
 * The Class AnalysisReportController.
 *
 * @author Lee Hugi
 * This controller handle with the analysis report page
 */
@SuppressWarnings("serial")
public class AnalysisReportController extends BasicController {
	
	/** The boundary controlled by this controller. */
	AnalysisReportBoundary myBoundary;

	/**
	 * Instantiates a new analysis report controller.
	 *
	 * @param myBoundary - The boundary controlled by this controller
	 */
	public AnalysisReportController(AnalysisReportBoundary myBoundary) {
		this.myBoundary=myBoundary;
	}
	
	/**
	 * this method create sql query that ask for the analysis report
	 * of specific change request.
	 *
	 * @param changeRequestId - The change request id
	 * @return Gets the change requests ID and start a sequence for getting all the information from the DB
	 */
	public void getAnalysisReportByChangeRequestId(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
	
	/**
	 * this method create array list of object from the data base result.
	 *
	 * @param result - The result received from the DB
	 * @return an arrayList of objects that represnts the analysis report information
	 */
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
