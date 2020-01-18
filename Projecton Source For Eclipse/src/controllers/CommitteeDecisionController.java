package controllers;

import java.sql.Date;
import java.util.ArrayList;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.CommitteeDecisionBoundary;
import client.ClientConsole;
import entities.CommitteeComment;
import entities.Step;
import javafx.application.Platform;

/**
 * The Class CommitteDecisionController.
 *
 * @author Lee Hugi
 * This controller handle with the committee decision page.
 */
@SuppressWarnings("serial")
public class CommitteeDecisionController extends BasicController{
	
	/** The boundary controlled by this controller. */
	private CommitteeDecisionBoundary myBoundary;
	
	/**
	 * Instantiates a new committee decision controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public CommitteeDecisionController(CommitteeDecisionBoundary myBoundary){
		this.myBoundary=myBoundary;
	}
	
	/**
	 * this method send query that ask from the data base committee comments.
	 *
	 * @param id the id
	 * @param stepId the step id
	 * @return the comments by change request id
	 */
	public void getCommentsByChangeRequestId(int id, int stepId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(id);
		varArray.add(stepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMENTS_BY_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * This method send query that insert to the data base comment.
	 *
	 * @param newComment the new comment
	 * @param stepId the step id
	 */
	public void insertNewCommentToDB(CommitteeComment newComment, Integer stepId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newComment.getRequestId());
		varArray.add(newComment.getEmployeeUserName());
		varArray.add(newComment.getComment());
		varArray.add(stepId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COMMITTEE_COMMENT,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * This method send query and update the committee step table in the data base.
	 *
	 * @param status the status
	 * @param date the date
	 * @param denyComment the deny comment
	 * @param changeRequestID the change request ID
	 */
	public void updateCommitteeStepDB(String status,Date date,String denyComment, Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(status);
		varArray.add(date);
		varArray.add(denyComment);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_COMMITTEE_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * This method send query and update the change request table in the data base.
	 *
	 * @param currentStep the current step
	 * @param handlerUserName the handler user name
	 * @param changeRequestID the change request ID
	 */
	public void updateChangeRequestCurrentStep(String currentStep, String handlerUserName, Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentStep);
		varArray.add(handlerUserName);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * This method send query that insert new row in the closing step data base table.
	 *
	 * @param changeRequestID the change request ID
	 * @param StartStepDate the start step date
	 * @param Status the status
	 */
	public void insertToClosingStepDbTable(Integer changeRequestID, Date StartStepDate, String Status) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(StartStepDate);
		varArray.add(Status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CLOSING_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * Send query that ask for the start time from committee step table.
	 *
	 * @param changeRequestId the change request id
	 * @return the start time from committee step
	 */
	public void getStartTimeFromCommitteeStep(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMITTEE_STEP_START_DATE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 *  This method send query that ask for committee step details from the data base.
	 *
	 * @param changeRequestId the change request id
	 * @return the committee step details
	 */
	public void getCommitteeStepDetails(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMITTEE_STEP_DETAILS,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * this method send query that update the time extension table.
	 *
	 * @param stepID the step ID
	 * @param stepType the step type
	 */
	public void updateTimeExtensionDB(int stepID, String stepType) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(stepID);
		varArray.add(stepType);
		SqlAction sqlAction = new SqlAction(SqlQueryType.AUTOMATIC_CLOSE_NEW_TIME_EXTENSION,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_COMMENTS_BY_REQUEST_ID:
					ArrayList<CommitteeComment> resultList=new ArrayList<>();
					resultList.addAll(this.changeResultToCommitteeComment(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleCommitteeCommentResultForTable(resultList);
					break;
				case INSERT_NEW_COMMITTEE_COMMENT:
					/* insert query return only one int value */
					int affectedRows;
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundary.committeeCommentInsertToDBSuccessfully(affectedRows);
					break;
				case UPDATE_COMMITTEE_STEP:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_CURRENT_STEP:
					this.unsubscribeFromClientDeliveries();
					break;
				case INSERT_NEW_CLOSING_STEP:
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_HANDLER_USER_NAME_BY_SYSTEM:
					this.unsubscribeFromClientDeliveries();
					String handlerUserName=(String)result.getResultData().get(0).get(0);
					myBoundary.createObjectForUpdateChangeRequestDetails(handlerUserName);
					break;
				case SELECT_COMMITTEE_STEP_START_DATE:
					this.unsubscribeFromClientDeliveries();
					Date estimatedEndDate = (Date) (result.getResultData().get(0).get(0));
					myBoundary.displayTimeRemaining(estimatedEndDate);
					break;
				case SELECT_COMMITTEE_STEP_DETAILS:
					this.unsubscribeFromClientDeliveries();
					Step newStep =  new Step(StepType.COMMITTEE, (Integer)result.getResultData().get(0).get(0),
							(Integer)result.getResultData().get(0).get(1),(String)result.getResultData().get(0).get(2),
							(Date)result.getResultData().get(0).get(3),(String)result.getResultData().get(0).get(6),
							(Date)result.getResultData().get(0).get(4),(Date)result.getResultData().get(0).get(5));
					myBoundary.createCommitteStepDetails(newStep);
					break;
				case AUTOMATIC_CLOSE_NEW_TIME_EXTENSION:
					this.unsubscribeFromClientDeliveries();
					break;
				default:
					break;
			}
		});
		return;
		
	}
	
	/**
	 * This method create array list of committee comment from the data base result.
	 *
	 * @param result the result
	 * @return Array list of comments
	 */
	private ArrayList<CommitteeComment> changeResultToCommitteeComment(SqlResult result){
		ArrayList<CommitteeComment> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			CommitteeComment comment=new CommitteeComment((int)a.get(0), (int)a.get(2),(String)a.get(3),
					(String)a.get(4));
			resultList.add(comment);
		}
		return resultList;
	}

	/**
	 * This method send query that ask for subsystem supporter user.
	 *
	 * @param subsystem the subsystem
	 */
	public void chooseAutomaticallyAnalyzer(String subsystem){
		ArrayList<Object> dataList=new ArrayList<>();
		dataList.add(subsystem);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_HANDLER_USER_NAME_BY_SYSTEM,dataList);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
}
