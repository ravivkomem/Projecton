package controllers;

import java.util.ArrayList;

import assets.ServerEvent;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.DemoLandingBoundry;
import client.ChatClient;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;

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
			ChangeRequest changeRequest = null;
			/* At most only one result, because change request ID is primary key */
			if (!results.getResultData().isEmpty())
			{
				ArrayList<Object> resultList = results.getResultData().get(0);
				
				Integer changeRequestID = (Integer) resultList.get(0);
				String changeRequestIntiatorName = (String) resultList.get(1);
				String selectSysystem = (String) resultList.get(2);
				String currentStateDiscription = (String) resultList.get(3);
				String changeRequestDescription = (String) resultList.get(4);
				String changeRequestStatus = (String) resultList.get(5);
				String handleName = (String) resultList.get(6);
				changeRequest = new ChangeRequest(changeRequestID,changeRequestIntiatorName,selectSysystem,currentStateDiscription,
						changeRequestDescription,changeRequestStatus,handleName);
			}
			ChatClient.changeRequestByIdListeners.remove(this);
			myBoundry.displayChangeRequestDetails(changeRequest);
		});
	}
	
	public void updateChangeRequest(ChangeRequest changedChangeRequest)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changedChangeRequest.getInitiator());
		varArray.add(changedChangeRequest.getSelectSysystem());
		varArray.add(changedChangeRequest.getCurrentStateDiscription());
		varArray.add(changedChangeRequest.getChangeRequestDescription());
		varArray.add(changedChangeRequest.getChangeRequestStatus());
		varArray.add(changedChangeRequest.getHandler());
		varArray.add(changedChangeRequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_BY_ID, varArray);
		ChatClient.updateChangeRequestByIdListeners.add(this);
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}
	
	public void updateChangeRequestByIdResultDelivery(SqlResult results) {
		Platform.runLater(() -> {
			int affectedRows;
			/* At most only one result, because update query only return one value */
			affectedRows = (Integer) (results.getResultData().get(0).get(0));
			ChatClient.updateChangeRequestByIdListeners.remove(this);
			myBoundry.getChangeRequestUpdateDetails(affectedRows);
		});
	}
}