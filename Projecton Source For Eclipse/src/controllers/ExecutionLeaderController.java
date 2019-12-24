package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.ExecutionLeaderBoundry;
import client.ClientConsole;
import entities.ExecutionAproves;
import javafx.application.Platform;

public class ExecutionLeaderController extends BasicController {
	private ExecutionLeaderBoundry myBoundry;
	

	public ExecutionLeaderController(ExecutionLeaderBoundry myBoundry) {
		this.myBoundry = myBoundry;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case INSERT_NEW_EXECUTION_ESTIMATED_TIME:
					int affectedRows;
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundry.ExecutionAprovedtInsertToDBSuccessfully(affectedRows);
					break;
				default:
					break;
			}
		});
		return;
		
	}

	public void insertNewEstimatedDateToExecutionStepAndChangeRequestIDStep(Date estimatedDateChoosen,Integer changeRequestID) {
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(estimatedDateChoosen);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_EXECUTION_ESTIMATED_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	public void updateNewChangeRequestIdStepToExecutionApprovedTime(Integer changeRequestID)
	{
		// TODO Auto-generated method stub
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("EXECUTION_APPROVE_TIME");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_NEW_EXECUTION_APPROVE_TIME_STATUS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);	
	}
	
}

