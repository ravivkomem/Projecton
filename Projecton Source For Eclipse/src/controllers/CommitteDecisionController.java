package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.CommitteeDecisionBoundry;
import client.ClientConsole;

public class CommitteDecisionController extends BasicController{
	
	private CommitteeDecisionBoundry myBoundry;
	
	public CommitteDecisionController(CommitteeDecisionBoundry myBoundry){
		this.myBoundry=myBoundry;
	}
	
	public void getCommentsByRequestId(String id) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(id);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMENTS_BY_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub
		
	}
}
