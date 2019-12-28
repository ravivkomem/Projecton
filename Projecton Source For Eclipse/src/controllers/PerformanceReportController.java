package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import entities.TimeExtension;
import boundries.PerformanceReportBoundary;
import javafx.application.Platform;

public class PerformanceReportController extends BasicController {

	private PerformanceReportBoundary myBoundary;
	
	public PerformanceReportController(PerformanceReportBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}
	
	public void getAllExtensionRequestsFromServer()
	{
		/*Creating Sql Action*/
		ArrayList<Object> varArray = new ArrayList<Object>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_APPROVED_TIME_EXTNESIONS, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch (result.getActionType()) {
			
			case SELECT_ALL_APPROVED_TIME_EXTNESIONS:
				this.unsubscribeFromClientDeliveries();
				ArrayList<TimeExtension> timeExtensionList = this.parseSqlResultToTimeExtensionList(result);
				myBoundary.createReport(timeExtensionList);
				break;

			default:
				break;
			}
		});
		return;
	}

	private ArrayList<TimeExtension> parseSqlResultToTimeExtensionList(SqlResult result) {
		ArrayList<TimeExtension> timeExtensionList = new ArrayList<TimeExtension>();
		for (ArrayList<Object> resultRow : result.getResultData())
		{
			int timeExtensionID = (int) resultRow.get(0);
			int stepID = (int) resultRow.get(1);
			String stepType = (String) resultRow.get(2);
			Date oldDate = (Date) resultRow.get(3);
			Date newDate = (Date) resultRow.get(4);
			String reason = (String) resultRow.get(5);
			String status = (String) resultRow.get(6);
			TimeExtension currentTimeExtension = new TimeExtension(timeExtensionID, stepID,
					stepType, oldDate, newDate, reason, status);
			timeExtensionList.add(currentTimeExtension);
		}
		return timeExtensionList;
	}
}
