package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.Toast;
import boundries.CommitteeDecisionBoundary;
import boundries.ProjectFX;
import client.ClientConsole;
import entities.CommitteeComment;
import javafx.application.Platform;

/**
 * @author Lee Hugi
 * This controller handle with the committee decision page
 */

public class CommitteDecisionController extends BasicController{
	
	private CommitteeDecisionBoundary myBoundary;
	
	public CommitteDecisionController(CommitteeDecisionBoundary myBoundary){
		this.myBoundary=myBoundary;
	}
	
	public void getCommentsByRequestId(int id) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(id);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMENTS_BY_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void insertNewCommentToDB(CommitteeComment newComment) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newComment.getRequestId());
		varArray.add(newComment.getEmployeeId());
		varArray.add(newComment.getComment());
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COMMITTEE_COMMENT,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void updateCommitteeStepDB(String status,Date date,Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(status);
		varArray.add(date);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_COMMITTEE_STEP,varArray);
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
					/*TODO check if affected rows == 1*/
					break;
				default:
					break;
			}
		});
		return;
		
	}
	
	private ArrayList<CommitteeComment> changeResultToCommitteeComment(SqlResult result){
		ArrayList<CommitteeComment> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			CommitteeComment comment=new CommitteeComment((int)a.get(0), (int)a.get(1),(int)a.get(2),
					(String)a.get(3));
			resultList.add(comment);
		}
		
		return resultList;
	}

	
}
