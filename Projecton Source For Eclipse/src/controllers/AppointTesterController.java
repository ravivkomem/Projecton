package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AppointTesterBoundary;
import entities.User;
import javafx.application.Platform;

@SuppressWarnings("serial")
public class AppointTesterController extends BasicController{

	private AppointTesterBoundary myBoundary;
	
	public AppointTesterController(AppointTesterBoundary appointTesterBoundary)
	{
		this.myBoundary = appointTesterBoundary;
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_ALL_COMMITTEE_MEMBERS:
					this.unsubscribeFromClientDeliveries();
					myBoundary.recieveAllCommitteeMembers(this.parseSqlResultToArrayListString(result));
					break;
				
				default:
					break;
			}
		});
		return;
		
	}

	public void getAllCommitteMembers() {
		/* Create sql action */
		ArrayList<Object> varArray = new ArrayList<Object>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_COMMITTEE_MEMBERS , varArray);
		
		this.sendSqlActionToClient(sqlAction);
	}
	
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

}
