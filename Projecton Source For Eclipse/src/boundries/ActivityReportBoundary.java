package boundries;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.ActivityReportController;
import controllers.TimeManager;
import entities.ActivityReport;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ActivityReportBoundary implements DataInitializable {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/

	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;
	@FXML
	private Button showDetailsButton;

	@FXML
	private AnchorPane activityReportDetailsPane;
	@FXML
	private PieChart requestStatusPieChart;
	@FXML
	private TextField medianTextField;
	@FXML
	private TextField stdTextField;
	@FXML
	private TextField distributionTextField;
	@FXML
	private TextField handleDaysTextField;

	 /* *************************************
	  * ******* Private Objects *************
	  * *************************************/
	private ActivityReportController myController = new ActivityReportController(this);
	private ActivityReport activityReport;
	private ArrayList<ChangeRequest> changeRequestList;

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/

	@FXML
	void showActivityReportDetails(MouseEvent event) {
		if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
			Toast.makeText(ProjectFX.mainStage, "Please select start date and end date", 1500, 500, 500);
		} else {
			activityReportDetailsPane.setVisible(true);
			Date startDate = Date.valueOf(startDatePicker.getValue());
			Date endDate = Date.valueOf(endDatePicker.getValue());
			Date todayDate = TimeManager.getCurrentDate();
			long daysBetween = TimeManager.getDaysBetween(todayDate, startDate);
			if (daysBetween > 0) {
				Toast.makeText(ProjectFX.mainStage, "You can not select a date before today date", 1500, 500, 500);
				return;
			}
			daysBetween = TimeManager.getDaysBetween(todayDate, endDate);
			if (daysBetween > 0) {
				Toast.makeText(ProjectFX.mainStage, "You can not select a date before today date", 1500, 500, 500);
				return;
			}
			daysBetween = TimeManager.getDaysBetween(startDate, endDate);
			if (daysBetween < 0) {
				Toast.makeText(ProjectFX.mainStage, "Please choose a valid date", 1500, 500, 500);
				return;
			}
			myController.getAllChangeRequest(startDate,endDate);
		}
	}

	/*
	 * ************************************* 
	 * ******* Public Methods *************
	 * ************************************/

	/**
	 * this method gets ChangeRequest list
	 * and create an ActivityReport
	 * @param requestList
	 */
	public void createActivityReportList(ArrayList<ChangeRequest> requestList) {
		changeRequestList = requestList;
		int active = 0, close = 0, suspended = 0, denied = 0;
		long workDays = 0;
		for (int i = 0; i < changeRequestList.size(); i++) {
			if (changeRequestList.get(i).getStatus().equals("Active")) {
				active++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays += daysBetween;
			} else if (changeRequestList.get(i).getCurrentStep().equals("Denied")) {
				denied++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						changeRequestList.get(i).getEndDate());
				workDays += daysBetween;
			} else if (changeRequestList.get(i).getCurrentStep().equals("Suspended")) {
				suspended++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays += daysBetween;
			} else if(changeRequestList.get(i).getCurrentStep().equals("Close")) {
				close++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						changeRequestList.get(i).getEndDate());
				workDays += daysBetween;
			}
		}
		displayActivityReport(activityReport = new ActivityReport(active, close, suspended,denied, workDays));
		
	}
	
	/**
	 * this method display the report in the page
	 * @param report
	 */
	private void displayActivityReport(ActivityReport report) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(report.getActiveChageRequest());
		list.add(report.getCloseChangeRequest());
		list.add(report.getSuspendedChangeRequest());
		list.add(report.getDeniedChangeRequest());
		requestStatusPieChart.getData().add( new PieChart.Data("Active", report.getActiveChageRequest()));
		requestStatusPieChart.getData().add( new PieChart.Data("Close", report.getCloseChangeRequest()));
		requestStatusPieChart.getData().add( new PieChart.Data("Suspended", report.getSuspendedChangeRequest()));
		requestStatusPieChart.getData().add( new PieChart.Data("Denied", report.getDeniedChangeRequest()));
		handleDaysTextField.setText(""+report.getSpentWorkDays());
		medianTextField.setText(""+calcMedian(list));
		stdTextField.setText("");
		distributionTextField.setText("");
		
	}
	
	private double calcMedian(ArrayList<Integer> list) {
		Collections.sort(list);
		double median;
		if (list.size() % 2 == 0)
		    median = ((double)list.get(list.size()/2) + (double)list.get(list.size()/2 - 1))/2;
		else
		    median = (double) list.get(list.size()/2);
		return median;	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		activityReportDetailsPane.setVisible(false);
		startDatePicker.setEditable(false);
		endDatePicker.setEditable(false);
	}

	@Override
	public void initData(Object data) {

	}

}