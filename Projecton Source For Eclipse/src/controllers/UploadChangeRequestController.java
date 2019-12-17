package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.UploadChangeRequestBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.CommitteeComment;

public class UploadChangeRequestController extends BasicController {

private UploadChangeRequestBoundary myBoundary;
	
	public UploadChangeRequestController(UploadChangeRequestBoundary myBoundry){
		this.myBoundary=myBoundary;
	}
	/*building the querey and update the database */
	public void insertNewChangeRequest(ChangeRequest newchangerequest)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newchangerequest.getInitiator());
		varArray.add(newchangerequest.getChangeRequestDate());
		varArray.add(newchangerequest.getSelectSysystem());
		varArray.add(newchangerequest.getCurrentStateDiscription());
		varArray.add(newchangerequest.getChangeRequestDescription());
		varArray.add(newchangerequest.getChangeRequestExplanation());
		varArray.add(newchangerequest.getChangeRequestComment());
		varArray.add(newchangerequest.getChangeRequestStatus());
		varArray.add(newchangerequest.getHandler());
		varArray.add(newchangerequest.getChangeRequestDocuments());
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CHANGE_REQUEST,varArray);
		/*ask raviv */
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		}
	
	
	
	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub
		
	}

}
