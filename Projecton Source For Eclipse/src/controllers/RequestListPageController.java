package controllers;

import java.sql.Date;
import java.util.ArrayList;
import entities.ChangeRequest;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.Toast;
import boundries.ProjectFX;
import boundries.RequestListPageBoundary;
import client.ClientConsole;
import javafx.application.Platform;

@SuppressWarnings("serial")
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
					ArrayList<ChangeRequest> resultList = this.parseSqlResultToChangeRequestArrayList(result);
					this.unsubscribeFromClientDeliveries();
					myBoundary.displayAllChangeRequestsForSpecifcUser(resultList);
				
				
			default:
				break;
				
			}
		});
		
	}
	public ArrayList<ChangeRequest> parseSqlResultToChangeRequestArrayList(SqlResult result)
	{
		ArrayList<ChangeRequest> resultList = new ArrayList <>();
		
		for(ArrayList<Object> resultRow: result.getResultData()) {
			try
			{
				Integer changeRequestID = (int)resultRow.get(0);
				String InitiatorUserName = (String)resultRow.get(1);
				Date startDate = (Date)resultRow.get(2);
				String selectedSubsystem = (String)resultRow.get(3);
				String currentStateDescription = (String)resultRow.get(4);
				String desiredChangeDescription = (String)resultRow.get(5);
				String desiredChangeExplanation = (String)resultRow.get(6);
				String desiredChangeComments = (String)resultRow.get(7);
				String status = (String)resultRow.get(8);
				String currentStep = (String)resultRow.get(9);
				String handlerUserName = (String)resultRow.get(10);
				Date endDate = (Date)resultRow.get(11);
				
				ChangeRequest currentChangeRequest = new ChangeRequest(
						changeRequestID, InitiatorUserName, startDate, selectedSubsystem, currentStateDescription, desiredChangeDescription,
						desiredChangeExplanation, desiredChangeComments, status, currentStep, handlerUserName, endDate);
				
				resultList.add(currentChangeRequest);
				
			}
			catch (Exception e)
			{
				Toast.makeText(ProjectFX.mainStage, "ERROR IN CONTROLLER", 1500, 500, 500);
			}
		}
		
		return resultList;
		
		
	}
	public void fillNecessaryFieldsInTable()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}

}
