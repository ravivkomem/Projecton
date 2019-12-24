package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.AppointTesterBoundary;

@SuppressWarnings("serial")
public class AppointTesterController extends BasicController{

	private AppointTesterBoundary myBoundary;
	
	public AppointTesterController(AppointTesterBoundary appointTesterBoundary)
	{
		this.myBoundary = appointTesterBoundary;
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub
		
	}

	public void getAllCommitteMembers() {
		/* Create sql action */
		ArrayList<Object> varArray = new ArrayList<Object>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CHANGE_REQUEST , varArray);
		
		this.sendSqlActionToClient(sqlAction);
	}

}
