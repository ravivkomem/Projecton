package unittests;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlResult;
import entities.ActivityReport;

public class StubDatabase implements ISqlConnection{
	private ArrayList<Object> dateList;
	private ArrayList<ActivityReport> reportList;
	private int num = 0;

	@Override
	public SqlResult getResult(SqlAction sqlAction) {
		SqlResult result = null;
		switch(sqlAction.getActionType()) {
		case SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN:
			if (sqlAction.getActionVars() == null)
			{
				return null;
			}
			else if (sqlAction.getActionVars().isEmpty())
			{
				return null;
			}
			else if (sqlAction.getActionVars().size() != 2)
			{
				return null;
			}
			
			if (!(sqlAction.getActionVars().get(0) instanceof Date))
			{
				return null;
			}
			
			if (!(sqlAction.getActionVars().get(1) instanceof Date))
			{
				return null;
			}
			
			Date startDate = (Date)sqlAction.getActionVars().get(0);
			Date endDate = (Date)sqlAction.getActionVars().get(1);
			ArrayList<Object> selectedDatesList = new ArrayList<Object>();
			
			for (Object obj : dateList)
			{
				Date date = (Date) obj;
				long dateTime = date.getTime();
				if (dateTime >= startDate.getTime() || dateTime <= endDate.getTime())
				{
					selectedDatesList.add(obj);
				}
			}
			result = new SqlResult(selectedDatesList, sqlAction.getActionType());
			break;
			
		case INSERT_NEW_ACTIVITY_REPORT:
			if(sqlAction.getActionVars().size() != 14) {
				result = null;
			}
			else
			{
				result = new SqlResult(1, sqlAction.getActionType());
			}
			break;
		default:
			break;
		}
		return result;
	}
	
	public void setDateList(ArrayList<Object> dateList){
		this.dateList = dateList;
	}
	
	public void setNum(int n) {
		num = n;
	}

}
