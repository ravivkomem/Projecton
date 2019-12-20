package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.ProjectFX;
import boundries.WorkStationBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;

@SuppressWarnings("serial")
public class WorkStationController extends BasicController{

	private WorkStationBoundary myBoundary;

	public WorkStationController (WorkStationBoundary workStationBoundary)
	{
		this.myBoundary = workStationBoundary;
	}
	
	public void selectAllChangeRequestByHandlerUserName()
	{
		SqlQueryType requiredSqlQueryType;
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		
		switch (ProjectFX.currentUser.getPermission())
		{
			case "INFORMATION_ENGINEER":
			case "SUPERVISOR":
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_NOT_COMMITTEE;
				break;
			case "COMMITTEE_MEMBER":
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_COMMITTEE_MEMBER;
				break;
			case "COMMITTEE_DIRECTOR":
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_COMMITTEE_DIRECTOR;
				break;
			
			default:
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_NOT_COMMITTEE;
				break;
		}
		SqlAction sqlAction = new SqlAction(requiredSqlQueryType, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void selectAnalysisStepChangeRequestByHandlerUserName()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void selectExecutionStepChangeRequestByHandlerUserName()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_EXECUTION_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_NOT_COMMITTEE:
				case SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_COMMITTEE_MEMBER:
				case SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME_COMMITTEE_DIRECTOR:
				case SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME:
				case SELECT_EXECUTION_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME:
					ArrayList<ChangeRequest> resultChangeRequestList = this.parseResultToChangeRequestList(result);
					this.unsubscribeFromClientDeliveries();
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				
				default:
					break;
			}
		});
		return;
	}
	
	private ArrayList<ChangeRequest> parseResultToChangeRequestList (SqlResult result)
	{
		ArrayList<ChangeRequest> changeRequestList = new ArrayList<ChangeRequest>();
		
		for (ArrayList<Object> resultRow : result.getResultData())
		{
			if (!resultRow.isEmpty())
			{
				/*TODO: Maybe check if any of the fields are NULL */
				Integer changeRequestID = (Integer) resultRow.get(0);
				String InitiatorUserName = (String) resultRow.get(1);
				Date startDate = (Date) resultRow.get(2);
				String selectedSubsystem = (String) resultRow.get(3);
				String currentStateDescription = (String) resultRow.get(4);
				String desiredChangeDescription = (String) resultRow.get(5);
				String desiredChangeExplanation = (String) resultRow.get(6);
				String desiredChangeComments = (String) resultRow.get(7);
				String status= (String) resultRow.get(8);
				String currentStep = (String) resultRow.get(9);
				String handlerUserName = (String) resultRow.get(10);
				Date endDate = (Date) resultRow.get(11);
				
				ChangeRequest currentChangeRequest = new ChangeRequest
						(changeRequestID, InitiatorUserName, startDate, selectedSubsystem, currentStateDescription, desiredChangeDescription,
						desiredChangeExplanation, desiredChangeComments, status,currentStep, handlerUserName, endDate);
				changeRequestList.add(currentChangeRequest);
			}
		}
		return changeRequestList;
	}

}
