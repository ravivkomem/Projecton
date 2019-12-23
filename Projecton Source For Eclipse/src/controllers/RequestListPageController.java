package controllers;

import java.util.ArrayList;
import entities.ChangeRequest;
import entities.ChangeRequest;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.RequestListPageBoundary;
import boundries.UploadChangeRequestBoundary;
import client.ClientConsole;
import javafx.application.Platform;

public class RequestListPageController extends BasicController {
	private RequestListPageBoundary myBoundary;

	public RequestListPageController(RequestListPageBoundary myBoundary)
	{
		this.myBoundary=myBoundary;
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> 
		{
			switch(result.getActionType())
			{
			case SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER:
				ArrayList<ChangeRequest> resultList =new ArrayList<>();
				this.unsubscribeFromClientDeliveries();
				
				
			default:
				break;
				
			}
		});
		
	}
	public ArrayList<ChangeRequest> getChangeRequestNecessaryFields(SqlResult result)
	{
		ArrayList<ChangeRequest>
		
	}
	public void fillNecessaryFieldsInTable()
	{
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

}
