package controllers;

import java.util.ArrayList;

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
		
	}

}
