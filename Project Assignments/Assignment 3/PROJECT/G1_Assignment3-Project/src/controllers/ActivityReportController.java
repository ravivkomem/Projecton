package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.ActivityReportBoundary;
import client.ClientConsole;
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
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(start);
		varArray.add(end);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_DATE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_ALL_CHANGE_REQUESTS_BY_DATE:
					this.unsubscribeFromClientDeliveries();
					myBoundary.createActivityReportList(createChangeRequestList(result));
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

}
