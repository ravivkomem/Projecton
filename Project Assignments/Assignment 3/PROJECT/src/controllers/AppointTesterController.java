package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AppointTesterBoundary;
import javafx.application.Platform;

/**
 * The Class AppointTesterController.
 * @author Raviv Komem
 *
 */
@SuppressWarnings("serial")
public class AppointTesterController extends BasicController{

	/** The boundary controlled by this controller. */
	private AppointTesterBoundary myBoundary;
	
	/**
	 * Instantiates a new appoint tester controller.
	 *
	 * @param appointTesterBoundary -  the appoint tester boundary
	 */
	public AppointTesterController(AppointTesterBoundary appointTesterBoundary)
	{
		this.myBoundary = appointTesterBoundary;
	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			int affectedRows;
			switch(result.getActionType())
			{
				case SELECT_ALL_COMMITTEE_MEMBERS:
					this.unsubscribeFromClientDeliveries();
					myBoundary.recieveAllCommitteeMembers(this.parseSqlResultToArrayListString(result));
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AND_HANDLER:
					this.unsubscribeFromClientDeliveries();
					affectedRows = (int) result.getResultData().get(0).get(0);
					myBoundary.recieveChangeRequestUpdateResult(affectedRows);
				case INSERT_NEW_TESTER_STEP:
					this.unsubscribeFromClientDeliveries();
					affectedRows = (int) result.getResultData().get(0).get(0);
					myBoundary.recieveNewTesterStepResult(affectedRows);
					
				default:
					break;
			}
		});
		return;
		
	}

	/**
	 * Gets the all committee members.
	 *
	 *  send SQL action to the DB to get all the committee members
	 */
	public void getAllCommitteeMembers() {
		/* Create sql action */
		ArrayList<Object> varArray = new ArrayList<Object>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_COMMITTEE_MEMBERS , varArray);
		
		this.sendSqlActionToClient(sqlAction);
	}
	
	/**
	 * Parses the sql result to array list string.
	 *
	 * @param result the result
	 * @return An arraylist of strings
	 */
	private ArrayList<String> parseSqlResultToArrayListString (SqlResult result)
	{
		ArrayList<String> committeeMembersList = new ArrayList<String>();
		
		for (ArrayList<Object> resultRow : result.getResultData())
		{
			String committeeMemberUserName = (String) resultRow.get(0);
			committeeMembersList.add(committeeMemberUserName);
		}
		
		return committeeMembersList;
	}

	/**
	 * Update change request step and handler.
	 *
	 * @param committeeMemberSelected the committee member selected
	 * @param changeRequestId the change request id
	 *  Send SQL action to the server to update the change request step and handler
	 */
	public void updateChangeRequestStepAndHandler(String committeeMemberSelected, int changeRequestId) {
		
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add("TESTING_WORK");
		varArray.add(committeeMemberSelected);
		varArray.add(changeRequestId);
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AND_HANDLER, varArray);
		this.sendSqlActionToClient(sqlAction);
	}

	/**
	 * Creates the new tester step.
	 *
	 * @param committeeMemberSelected the committee member selected
	 * @param changeRequestID the change request ID
	 *  Send SQL action to insert new tester step in the DB
	 */
	public void createNewTesterStep(String committeeMemberSelected, int changeRequestID) {
		
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		varArray.add(committeeMemberSelected);
		varArray.add(TimeManager.getCurrentDate());
		/* Story request for 7 days */
		varArray.add(TimeManager.addDays(TimeManager.getCurrentDate(), 7));
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_TESTER_STEP, varArray);
		this.sendSqlActionToClient(sqlAction);
	}

}
