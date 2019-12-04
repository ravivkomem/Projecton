package controllers;

import java.util.ArrayList;

import boundries.DemoLandingBoundry;
import client.ChatClient;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;
import other.ServerEvent;
import other.SqlAction;
import other.SqlQueryType;
import other.SqlResult;

public class DemoLandingController extends ServerEvent {

	private DemoLandingBoundry myBoundry;
	
	public DemoLandingController(DemoLandingBoundry myBoundry) 
	{
		this.myBoundry = myBoundry;
	}
	
	public void getChangeRequestById (String changeRequestId)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CHANGE_REQUEST_BY_ID, varArray);
		ChatClient.changeRequestByIdListeners.add(this);
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getChangeRequestByIdResultDelivery(SqlResult results) {
		Platform.runLater(() -> {
			ArrayList<ChangeRequest> changeRequestList=new ArrayList<>();
			for(ArrayList<Object> rowList: results.getResultData()) {
				Integer changeRequestID = (Integer) rowList.get(0);
				String changeRequestIntiatorName = (String) rowList.get(1);
				String selectSysystem = (String) rowList.get(2);
				String currentStateDiscription = (String) rowList.get(3);
				String changeRequestDescription = (String) rowList.get(4);
				String changeRequestStatus = (String) rowList.get(5);
				String handleName = (String) rowList.get(6);
				changeRequestList.add
				(new ChangeRequest(changeRequestID,changeRequestIntiatorName,selectSysystem,currentStateDiscription,
						changeRequestDescription,changeRequestStatus,handleName));
			}
			myBoundry.displayChangeRequestTable(changeRequestList);
		});
	}
}