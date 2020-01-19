package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.TimeExtensionBoundary;
import entities.Step;
import javafx.application.Platform;

/**
 * The Class TimeExtensionController.
 *
 * @author Raviv Komem
 */
@SuppressWarnings("serial")
public class TimeExtensionController extends BasicController {

	/** The my boundary. */
	private TimeExtensionBoundary myBoundary;
	
	/**
	 * Instantiates a new time extension controller.
	 *
	 * @param timeExtensionBoundary the time extension boundary
	 */
	public TimeExtensionController (TimeExtensionBoundary timeExtensionBoundary)
	{
		this.myBoundary = timeExtensionBoundary;
	}
	
	/**
	 * Submit time extension request.
	 *
	 * @param step the step
	 * @param newEstimatedEndDate the new estimated end date
	 * @param timeExtensionReason the time extension reason
	 */
	public void submitTimeExtensionRequest(Step step, Date newEstimatedEndDate, String timeExtensionReason)
	{
		/*Creating the SqlAction */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(step.getStepID());
		varArray.add(step.getType().getStepName());
		varArray.add(step.getEstimatedEndDate());
		varArray.add(newEstimatedEndDate);
		varArray.add(timeExtensionReason);
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_TIME_EXTENSION, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Verify no previous extensions.
	 *
	 * @param step the step
	 */
	public void verifyNoPreviousExtensions(Step step)
	{
		/*Creating the SqlAction */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(step.getStepID());
		varArray.add(step.getType().getStepName());
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.COUNT_TIME_EXTENSION_BY_STEP, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
