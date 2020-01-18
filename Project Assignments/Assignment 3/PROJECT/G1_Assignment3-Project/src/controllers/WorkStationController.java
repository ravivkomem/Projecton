package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.ProjectFX;
import boundries.WorkStationBoundary;
import boundries.WorkStationBoundary.WorkStationFilter;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;

/**
 * The Class WorkStationController.
 *
 * @author Raviv Komem
 */
@SuppressWarnings("serial")
public class WorkStationController extends BasicController{

	/** The my boundary. */
	private WorkStationBoundary myBoundary;

	/**
	 * Instantiates a new work station controller.
	 *
	 * @param workStationBoundary the work station boundary
	 */
	public WorkStationController (WorkStationBoundary workStationBoundary)
	{
		this.myBoundary = workStationBoundary;
	}
	
	/**
	 * Select all change request.
	 */
	public void selectAllChangeRequest()
	{
		SqlQueryType requiredSqlQueryType;
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		
		switch (ProjectFX.currentUser.getPermission())
		{
			case "INFORMATION_ENGINEER":
			case "SUPERVISOR":
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_NOT_COMMITTEE;
				break;
			case "COMMITTEE_MEMBER":
			case "SUPERVISOR_COMMITTEE_MEMBER":
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_MEMBER;
				break;
			case "COMMITTEE_DIRECTOR":
			case "SUPERVISOR_COMMITTEE_DIRECTOR":
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_DIRECTOR;
				break;
			
			default:
				requiredSqlQueryType = SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_NOT_COMMITTEE;
				break;
		}
		SqlAction sqlAction = new SqlAction(requiredSqlQueryType, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * Select analysis step change request.
	 */
	public void selectAnalysisStepChangeRequest()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * Select committee step change request.
	 */
	public void selectCommitteeStepChangeRequest()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMITTEE_STEP_CHANGE_REQUESTS, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * Select execution step change request.
	 */
	public void selectExecutionStepChangeRequest()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_EXECUTION_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	
	/**
	 * Select tester appoint step change request.
	 */
	public void selectTesterAppointStepChangeRequest()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_TESTER_APPOINT_CHANGE_REQUESTS, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * Select tester step change request.
	 */
	public void selectTesterStepChangeRequest()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(ProjectFX.currentUser.getUserName());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_TESTER_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);

	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
	@Override
	public void getResultFromClient(SqlResult result) {
		
		Platform.runLater(() -> {
			ArrayList<ChangeRequest> resultChangeRequestList;
			switch(result.getActionType())
			{
				case SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_NOT_COMMITTEE:
				case SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_MEMBER:
				case SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_DIRECTOR:
					this.unsubscribeFromClientDeliveries();
					resultChangeRequestList = this.parseResultToChangeRequestList(result);
					myBoundary.setFilterType(WorkStationFilter.ALL_CHANGE_REQUEST);
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				case SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME:
					this.unsubscribeFromClientDeliveries();
					resultChangeRequestList = this.parseResultToChangeRequestList(result);
					myBoundary.setFilterType(WorkStationFilter.ANALYSIS_STEP);
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				case SELECT_COMMITTEE_STEP_CHANGE_REQUESTS:
					this.unsubscribeFromClientDeliveries();
					resultChangeRequestList = this.parseResultToChangeRequestList(result);
					myBoundary.setFilterType(WorkStationFilter.COMMITTEE_STEP);
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				case SELECT_EXECUTION_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME:
					this.unsubscribeFromClientDeliveries();
					resultChangeRequestList = this.parseResultToChangeRequestList(result);
					myBoundary.setFilterType(WorkStationFilter.EXECUTION_STEP);
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				case SELECT_TESTER_APPOINT_CHANGE_REQUESTS:
					this.unsubscribeFromClientDeliveries();
					resultChangeRequestList = this.parseResultToChangeRequestList(result);
					myBoundary.setFilterType(WorkStationFilter.TESTER_APPOINT_STEP);
					myBoundary.loadTableView(resultChangeRequestList);
					break;
				case SELECT_TESTER_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME:
					this.unsubscribeFromClientDeliveries();
					resultChangeRequestList = this.parseResultToChangeRequestList(result);
					myBoundary.setFilterType(WorkStationFilter.TESTING_STEP);
					myBoundary.loadTableView(resultChangeRequestList);
					break;

				default:
					break;
			}
		});
		return;
	}
	
	/**
	 * Parses the result to change request list.
	 *
	 * @param result the result
	 * @return the array list
	 */
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
