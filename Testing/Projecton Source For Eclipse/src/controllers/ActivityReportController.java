package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.Toast;
import boundries.ActivityReportBoundary;
import boundries.ProjectFX;
import client.ClientConsole;
import entities.ActivityReport;
import entities.ChangeRequest;
import javafx.application.Platform;

/**
 * The Class ActivityReportController.
 *
 * @author Lee Hugi
 * This controller handle with the activity report page
 */
@SuppressWarnings("serial")
public class ActivityReportController extends BasicController{

	/** The boundary controlled by this controller. */
	ActivityReportBoundary myBoundary;
	Date activityReportEndDate;
	/**
	 * Instantiates a new activity report controller.
	 *
	 * @param myBoundary - The boundary controlled by this controller
	 */
	public ActivityReportController(ActivityReportBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	/**
	 * the method create sql query with the start and end date and
	 * gets from the data base all the change request between those dates.
	 *
	 * @param start - The start date for the report
	 * @param end - The end date for the report
	 * 
	 * Send SQL action to the server to get all change request between those days
	 */
	public void getAllChangeRequest(Date start,Date end) {
		activityReportEndDate = end;
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(start);
		varArray.add(end);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_DATE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void insertNewActivityReport(ActivityReport activityReport) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(activityReport.getActiveChageRequest());
		varArray.add(activityReport.getCloseChangeRequest());
		varArray.add(activityReport.getDeniedChangeRequest());
		varArray.add(activityReport.getSuspendedChangeRequest());
		varArray.add(activityReport.getMedian());
		varArray.add(activityReport.getStd());
		varArray.add(activityReport.getSpentWorkDays().get(0));
		varArray.add(activityReport.getSpentWorkDays().get(1));
		varArray.add(activityReport.getSpentWorkDays().get(2));
		varArray.add(activityReport.getSpentWorkDays().get(3));
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ACTIVITY_REPORT,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_ALL_CHANGE_REQUESTS_BY_DATE:
					this.unsubscribeFromClientDeliveries();
					ActivityReport report;
					report = createActivityReport(createChangeRequestList(result));
					//insertNewActivityReport(report);
					myBoundary.displayActivityReport(report,report.getSpentWorkDays());
					break;
				case INSERT_NEW_ACTIVITY_REPORT:
					//TODO: save the report in the database
					//checkInsertSuccessfully();
					break;
				default:
					break;
			}
		});
		return;
	}
	
	/**
	 * this method gets the result from the data base and create array list of change request.
	 *
	 * @param result - The result recieved from the DB
	 * @return An arraylist of change requests that this result represents
	 */
	private ArrayList<ChangeRequest> createChangeRequestList(SqlResult result){
		ArrayList<ChangeRequest> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			ChangeRequest changeRequest=new ChangeRequest((Integer)a.get(0), (String)a.get(1), (Date)a.get(2), (String)a.get(3),
					(String)a.get(4), (String)a.get(5), (String)a.get(6),(String)a.get(7), (String)a.get(8),
					(String)a.get(9), (String)a.get(10), (Date)a.get(11));
			resultList.add(changeRequest);
		}
		return resultList;
	}
	
	public ActivityReport createActivityReport(ArrayList<ChangeRequest> requestList) {
		if(requestList.isEmpty()) {
			//return;
		}
		int active = 0, close = 0, suspended = 0, denied = 0;
		ArrayList<Long> workDays = new ArrayList<>();
		for (int i = 0; i < requestList.size(); i++) {	
			if (requestList.get(i).getStatus().equals("ACTIVE")) {
				active++;
				long daysBetween = TimeManager.getDaysBetween(requestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays.add(daysBetween);
			} else if (requestList.get(i).getStatus().equals("DENIED")) {
				if(TimeManager.getDaysBetween(requestList.get(i).getEndDate(), activityReportEndDate) < 0) {
					active++;
				} else {
					denied++; }
				long daysBetween = TimeManager.getDaysBetween(requestList.get(i).getStartDate(),
						requestList.get(i).getEndDate());
				workDays.add(daysBetween);
			} else if (requestList.get(i).getStatus().equals("SUSPEND")) {
				suspended++;
				long daysBetween = TimeManager.getDaysBetween(requestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays.add(daysBetween);
			} else if(requestList.get(i).getStatus().equals("CLOSED")) {
				if(TimeManager.getDaysBetween(requestList.get(i).getEndDate(), activityReportEndDate) < 0) {
					active++;
				} else {
					close++;
				}
				long daysBetween = TimeManager.getDaysBetween(requestList.get(i).getStartDate(),
						requestList.get(i).getEndDate());
				workDays.add(daysBetween);
			}
		}
		ActivityReport activityReport = new ActivityReport(active, close, suspended,denied, workDays);
		activityReport.setMedian(Utilizer.calcMedian(workDays));
		activityReport.setStd(Utilizer.calcStd(workDays));
		return activityReport;
		}

}
