package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.TimeExtensionBoundary;
import entities.Step;
import javafx.application.Platform;

@SuppressWarnings("serial")
public class TimeExtensionController extends BasicController {

	private TimeExtensionBoundary myBoundary;
	
	public TimeExtensionController (TimeExtensionBoundary timeExtensionBoundary)
	{
		this.myBoundary = timeExtensionBoundary;
	}
	
	public void submitTimeExtensionRequest(Step step, Date newEstimatedEndDate, String timeExtensionReason)
	{
		/*Creating the SqlAction */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(step.getStepID());
		varArray.add(step.getType().getStepName());
		varArray.add(newEstimatedEndDate);
		varArray.add(timeExtensionReason);
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_TIME_EXTENSION, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	public void verifyNoPreviousExtensions(Step step)
	{
		/*Creating the SqlAction */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(step.getStepID());
		varArray.add(step.getType().getStepName());
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.COUNT_TIME_EXTENSION_BY_STEP, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch (result.getActionType())
			{
				case INSERT_NEW_TIME_EXTENSION:
					int affectedRows = (int) result.getResultData().get(0).get(0);
					myBoundary.recieveSubmissionAnswer(affectedRows);
					this.unsubscribeFromClientDeliveries();
					break;
				
				case COUNT_TIME_EXTENSION_BY_STEP:
					long timeExtensionCounter = (Long) result.getResultData().get(0).get(0);
					myBoundary.recieveTimeExtensionCounter(timeExtensionCounter);
					this.unsubscribeFromClientDeliveries();
					break;
				default:
					break;
			}
		});
		
		return;
	}

}
