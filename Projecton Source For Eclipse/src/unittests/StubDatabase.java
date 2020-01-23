package unittests;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlResult;
import entities.ActivityReport;

public class StubDatabase implements ISqlConnection{
	private ArrayList<Object> changeRequest;
	private ArrayList<ActivityReport> reportList;
	private int num = 0;

	@Override
	public SqlResult getResult(SqlAction sqlAction) {
		SqlResult result = null;
		switch(sqlAction.getActionType()) {
		case SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN:
			result = new SqlResult(changeRequest, sqlAction.getActionType());
			break;
		case INSERT_NEW_ACTIVITY_REPORT:
			result = new SqlResult(num, sqlAction.getActionType());
			break;
		default:
			break;
		}
		return result;
	}
	
	public void setChangeRequest(ArrayList<ActivityReport> reports){
		reportList.clear();
		reportList.addAll(reports);
	}
	
	public void setNum(int n) {
		num = n;
	}

}
