package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.CommitteeDecisionBoundry;
import client.ClientConsole;
import entities.CommitteeComment;
import javafx.application.Platform;

/**
 * @author Lee Hugi
 * This controller handle with the committee decision page
 */

public class CommitteDecisionController extends BasicController{
	
	private CommitteeDecisionBoundry myBoundry;
	
	public CommitteDecisionController(CommitteeDecisionBoundry myBoundry){
		this.myBoundry=myBoundry;
	}
	
	public void getCommentsByRequestId(String id) {
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

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_COMMENTS_BY_REQUEST_ID:
					ArrayList<CommitteeComment> resultList=new ArrayList<>();
					resultList.addAll(this.changeResultToCommitteeComment(result));
					this.unsubscribeFromClientDeliveries();
					myBoundry.handleCommitteeCommentResult(resultList);
					break;
				case INSERT_NEW_COMMITTEE_COMMENT:
					//check what the returns
					this.unsubscribeFromClientDeliveries();
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
			CommitteeComment comment=new CommitteeComment((int)a.get(0), (int)a.get(1),(String)a.get(2));
			resultList.add(comment);
		}
		
		return resultList;
	}
}
