package controllers;

import java.util.ArrayList;
import entities.ChangeRequest;
import entities.CommitteeComment;
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
		ArrayList<ChangeRequest> resultList=new ArrayList <>();
		for(ArrayList<Object> arr: result.getResultData()) {
			ChangeRequest currentChangeRequest=new ChangeRequest((int)arr.get(0),(Date)arr.get(2)),(String)arr.get(3),(String)arr.get(4),(String)arr.get(5),(String)arr.get(6),(String)arr.get(7),(String)arr.get(8),(String)arr.get(9),(String)arr.get(10),(String)arr.get(11),(String)arr.get(12))
			resultList.add(currentChangeRequest);
		}
		return resultList;
		
		
	}
	public void fillNecessaryFieldsInTable()
	{
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

}
