package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.LoginPageBoundary;
import boundries.WorkStationBoundary;
import client.ClientConsole;
import entities.ChangeRequestNew;
import entities.User;
import javafx.application.Platform;

@SuppressWarnings("serial")
public class WorkStationController extends BasicController{

	private WorkStationBoundary myBoundary;

	public WorkStationController (WorkStationBoundary workStationBoundary)
	{
		this.myBoundary = workStationBoundary;
	}
	
	public void selectAllChangeRequestByHandlerUserName(String handlerUserName)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(handlerUserName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void selectAnalysisStepChangeRequestByHandlerUserName(String handlerUserName)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(handlerUserName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME:
				case SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME:
					ArrayList<ChangeRequestNew> resultChangeRequestList = this.parseResultToChangeRequestList(result);
					this.unsubscribeFromClientDeliveries();
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				
				default:
					break;
			}
		});
		return;
	}
	
	private ArrayList<ChangeRequestNew> parseResultToChangeRequestList (SqlResult result)
	{
		ArrayList<ChangeRequestNew> changeRequestList = new ArrayList<ChangeRequestNew>();
		
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
				String uploadedFiles = (String) resultRow.get(11);
				Date endDate = (Date) resultRow.get(12);
				
				ChangeRequestNew currentChangeRequest = new ChangeRequestNew
						(changeRequestID, InitiatorUserName, startDate, selectedSubsystem, currentStateDescription, desiredChangeDescription,
						desiredChangeExplanation, desiredChangeComments, status,currentStep, handlerUserName, uploadedFiles, endDate);
				changeRequestList.add(currentChangeRequest);
			}
		}
		return changeRequestList;
	}

}
