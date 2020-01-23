package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.ActivityReportNewBoundary;
import entities.NewActivityReport;
import javafx.application.Platform;

public class ActivityReportNewController extends BasicController{

	
	private ActivityReportNewBoundary myBoundary;
	private Date startDate;
	private Date endDate;
	private static final int SPLIT_SIZE = 10;
	
	public ActivityReportNewController(ActivityReportNewBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}
	
	public void selectChangeRequestsBetween(Date startDate, Date endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(startDate);
		varArray.add(endDate);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		this.sendSqlActionToClient(sqlAction);
	}
	
	public void insertNewActivityReport(NewActivityReport newReport) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(newReport.getStartDate());
		varArray.add(newReport.getEndDate());
		for(int i = 0; i<10; i++) {
			varArray.add((int)newReport.getNumberOfChangeRequests()[i]);
		}
		varArray.add(newReport.getStd());
		varArray.add(newReport.getMedian());
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ACTIVITY_REPORT, varArray);
		this.sendSqlActionToClient(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN:
					this.unsubscribeFromClientDeliveries();
					ArrayList<Date> dateList;
					dateList = createDateList(result);
					NewActivityReport newReport = createNewActivityReport(dateList);
					myBoundary.displayActivityReport(newReport);
					break;
				case INSERT_NEW_ACTIVITY_REPORT:
					this.unsubscribeFromClientDeliveries();
					break;
				default:
					break;
			}
		});
		return;
		
	}

	public ArrayList<Date> createDateList(SqlResult result) {
		
		try
		{
			ArrayList<Date> dateList = new ArrayList<Date>();
			for (ArrayList<Object> resultRow : result.getResultData()) 
			{
				Date date = (Date)resultRow.get(0);
				dateList.add(date);
			}
			return dateList;
		} 
		catch (Exception e)
		{
			
		}
		return null;
	}
	
	public NewActivityReport createNewActivityReport(ArrayList<Date> dateList)
	{
		if(dateList == null) {
			return null;
		}
		long[] numberOfChangeRequests = new long[SPLIT_SIZE];
		
		long startDateLong = startDate.getTime();
		long endDateLong = endDate.getTime();
		
		long daysBetween = endDateLong-startDateLong;
		long jumpDays = daysBetween/SPLIT_SIZE;
		
		for (Date date : dateList)
		{
			long dateTime = date.getTime();
			long daysSinceStart = dateTime - startDateLong;
			int index =(int)(daysSinceStart/jumpDays);
			if (index == 10)
			{
				index = 9;
			}
			numberOfChangeRequests[index]++;			
		}
		
		return new NewActivityReport(numberOfChangeRequests, startDate, endDate);
		
//		for (int i = 0; i < numberOfChangeRequests.length; i++)
//		{
//			System.out.println("Array index " + i +" Is equal: " + numberOfChangeRequests[i]);	
//		}
	}
	
	public void setDates (Date startDate, Date endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
}
