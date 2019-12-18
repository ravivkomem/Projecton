package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.UploadChangeRequestBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.ChangeRequestNew;
import entities.CommitteeComment;
import javafx.application.Platform;

public class UploadChangeRequestController extends BasicController {

private UploadChangeRequestBoundary myBoundary;
	
	public UploadChangeRequestController(UploadChangeRequestBoundary myBoundry){
		this.myBoundary=myBoundary;
	}
	/*building the querey and update the database */
	public void insertNewChangeRequest(ChangeRequest newchangerequest)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		/*add current step field*/
		varArray.add(newchangerequest.getInitiator());
		varArray.add(newchangerequest.getChangeRequestDate());
		varArray.add(newchangerequest.getSelectSysystem());
		varArray.add(newchangerequest.getCurrentStateDiscription());
		varArray.add(newchangerequest.getChangeRequestDescription());
		varArray.add(newchangerequest.getChangeRequestExplanation());
		varArray.add(newchangerequest.getChangeRequestComment());
		varArray.add(newchangerequest.getChangeRequestStatus());
		varArray.add(newchangerequest.getChangeRequestStep());
		varArray.add(newchangerequest.getHandler());
		varArray.add(newchangerequest.getChangeRequestDocuments());
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CHANGE_REQUEST,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		}
	
	
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case INSERT_NEW_CHANGE_REQUEST:
					this.unsubscribeFromClientDeliveries();
					int affectedRows;
					/* At most only one result, because update query only return one value */
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					/*TODO: If 0 then display Error message */
					//if (affectedRows==0)
					myBoundary.printMessageToUserUploadedNewChangeRequest(affectedRows);
					/* If 1 then do another query for the new change request ID */
					break;
				
				default:
					break;
			}
		});
		return;
		
	}

}
