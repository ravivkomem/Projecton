package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.DelayReportBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.DelayReport;
import javafx.application.Platform;

public class DelayReportController extends BasicController{

	private DelayReportBoundary myBoundary;

	public DelayReportController(DelayReportBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	public void getAllStepsDate() {
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_FROM_ALL_STEPS,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_DATES_FROM_ALL_STEPS:
					this.unsubscribeFromClientDeliveries();
					myBoundary.displayDealyReport(createDelayReportList(result));
					break;
				default:
					break;
			}
		});
		return;
		
	}
	
	private ArrayList<DelayReport> createDelayReportList(SqlResult result){
		ArrayList<DelayReport> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			DelayReport delayReport=new DelayReport((String)a.get(0), (Date)a.get(1), (Date)a.get(2));
			resultList.add(delayReport);
		}
		return resultList;
	}
}
