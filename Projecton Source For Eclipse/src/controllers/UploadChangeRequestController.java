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
import javafx.scene.control.Alert.AlertType;
import java.math.BigInteger; 

public class UploadChangeRequestController extends BasicController {

	private UploadChangeRequestBoundary myBoundary;
	private ChangeRequest currentChangeRequest;
	
	public UploadChangeRequestController(UploadChangeRequestBoundary myBoundary){
		this.myBoundary=myBoundary;
	}
	/*building the querey and update the database */
	public void insertNewChangeRequest(ChangeRequest newchangerequest)
	{
		currentChangeRequest = newchangerequest;
		/*TODO: Function to get list of all information engineer (INFORMATION_ENGINEER, SUPERVISOR,
		 * COMMITTE_MEMBER, COMMITTEE_DIRECTOR)
		 * Set to change request handler from Random of list (0 to list.size -1 )
		 */
		
		
		/*TODO: all this in another function after you recieved handler name*/
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
					int changeRequestId = -1;
					this.unsubscribeFromClientDeliveries();
					/*TODO: Check if result is empty ---> something wrong happend */
					if (true /*NOT EMPTY */)
					{
						changeRequestId = ((BigInteger) (result.getResultData().get(0).get(0))).intValue();
					}
					myBoundary.displayChangeRequestId(changeRequestId);
					break;
				
				default:
					break;
			}
		});
		return;	
	}

}
