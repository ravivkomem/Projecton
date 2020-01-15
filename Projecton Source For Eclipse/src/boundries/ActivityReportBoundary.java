package boundries;


import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.ActivityReportController;
import controllers.TimeManager;
import controllers.Utilizer;
import entities.ActivityReport;
import entities.ChangeRequest;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

// TODO: Auto-generated Javadoc
/**
 * The Class ActivityReportBoundary.
 *
 * @author Lee Hugi
 * This class control the activity report page
 */
public class ActivityReportBoundary implements Initializable {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/

	/** The start date picker. */
	@FXML
	private DatePicker startDatePicker;
	
	/** The end date picker. */
	@FXML
	private DatePicker endDatePicker;
	
	/** The show details button. */
	@FXML
	private Button showDetailsButton;

	/** The activity report details pane. */
	@FXML
	private AnchorPane activityReportDetailsPane;
	
	/** The request status pie chart. */
	@FXML
	private PieChart requestStatusPieChart;
	
	/** The median text field. */
	@FXML
	private TextField medianTextField;
	
	/** The std text field. */
	@FXML
	private TextField stdTextField;
    
    /** The work days bar chart. */
    @FXML
    private BarChart<String, Number> workDaysBarChart;
    
    /** The work days chart bar category. */
    @FXML
    private CategoryAxis workDaysChartBarCategory;
    
    /** The Days number bar chart. */
    @FXML
    private NumberAxis DaysNumberBarChart;

	 /** The my controller. */
 	/* *************************************
	  * ******* Private Objects *************
	  * *************************************/
	private ActivityReportController myController = new ActivityReportController(this);
	
	/** The activity report. */
	private ActivityReport activityReport;
	
	/** The change request list. */
	private ArrayList<ChangeRequest> changeRequestList;
	
	/** The Constant FIRST_CATAGORY. */
	private static final String FIRST_CATAGORY = "0-10";
	
	/** The Constant SECOND_CATAGORY. */
	private static final String SECOND_CATAGORY = "10-20";
	
	/** The Constant THIRD_CATAGORY. */
	private static final String THIRD_CATAGORY = "20-30";
	
	/** The Constant FOURTH_CATAGORY. */
	private static final String FOURTH_CATAGORY = "30+";

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/

	/**
	 * This method display in the diagrams the report details.
	 *
	 * @param event the event
	 */
	@FXML
	void showActivityReportDetails(MouseEvent event) {
		requestStatusPieChart.getData().clear();
		workDaysBarChart.getData().clear();
		if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
			Toast.makeText(ProjectFX.mainStage, "Please select start date and end date", 1500, 500, 500);
		} else {
			activityReportDetailsPane.setVisible(true);
			Date startDate = Date.valueOf(startDatePicker.getValue());
			Date endDate = Date.valueOf(endDatePicker.getValue());
			Date todayDate = TimeManager.getCurrentDate();
			long daysBetween = TimeManager.getDaysBetween(todayDate, startDate);
			daysBetween--;
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
	 * and create an ActivityReport.
	 *
	 * @param requestList the request list
	 */
	public void createActivityReportList(ArrayList<ChangeRequest> requestList) {
		Date endDate = Date.valueOf(endDatePicker.getValue());
		if(requestList.isEmpty()) {
			activityReportDetailsPane.setVisible(false);
			Toast.makeText(ProjectFX.mainStage, "No request for those dates", 1500, 500, 500);
			return;
		}
		changeRequestList = requestList;
		int active = 0, close = 0, suspended = 0, denied = 0;
		ArrayList<Long> workDays = new ArrayList<>();
		for (int i = 0; i < changeRequestList.size(); i++) {	
			if (changeRequestList.get(i).getStatus().equals("ACTIVE")) {
				active++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays.add(daysBetween);
			} else if (changeRequestList.get(i).getStatus().equals("DENIED")) {
				if(TimeManager.getDaysBetween(changeRequestList.get(i).getEndDate(), endDate) < 0) {
					active++;
				} else {
					denied++; }
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						changeRequestList.get(i).getEndDate());
				workDays.add(daysBetween);
			} else if (changeRequestList.get(i).getStatus().equals("SUSPEND")) {
				suspended++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays.add(daysBetween);
			} else if(changeRequestList.get(i).getStatus().equals("CLOSED")) {
				if(TimeManager.getDaysBetween(changeRequestList.get(i).getEndDate(), endDate) < 0) {
					active++;
				} else {
					close++;
				}
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						changeRequestList.get(i).getEndDate());
				workDays.add(daysBetween);
			}
		}
		displayActivityReport(activityReport = new ActivityReport(active, close, suspended,denied, workDays),workDays);
	}
	
	/**
	 * this method display the report in the page.
	 *
	 * @param report the report
	 * @param workDays the work days
	 */
	private void displayActivityReport(ActivityReport report, ArrayList<Long> workDays) {
		int[] workDaysArray;
		requestStatusPieChart.setTitle("Change Request Status");
		if(report.getDeniedChangeRequest()>0)
			requestStatusPieChart.getData().add( new PieChart.Data("Denied", report.getDeniedChangeRequest()));
		if(report.getCloseChangeRequest() > 0)
			requestStatusPieChart.getData().add( new PieChart.Data("Close", report.getCloseChangeRequest()));
		if(report.getActiveChageRequest() > 0)
			requestStatusPieChart.getData().add( new PieChart.Data("Active", report.getActiveChageRequest()));
		if(report.getSuspendedChangeRequest() > 0)
			requestStatusPieChart.getData().add( new PieChart.Data("Suspended", report.getSuspendedChangeRequest()));
		
		workDaysArray = workDaysCalc(workDays);
		
		XYChart.Series<String,Number> series1 = new XYChart.Series<String, Number>();
		series1.getData().add(new XYChart.Data<String,Number>(FIRST_CATAGORY, workDaysArray[0]));
		series1.getData().add(new XYChart.Data<String,Number>(SECOND_CATAGORY, workDaysArray[1]));
		series1.getData().add(new XYChart.Data<String,Number>(THIRD_CATAGORY, workDaysArray[2]));
		series1.getData().add(new XYChart.Data<String,Number>(FOURTH_CATAGORY, workDaysArray[3]));
		
		workDaysBarChart.getData().addAll(series1);
		if(!workDays.isEmpty()) {
			medianTextField.setText(""+ Utilizer.calcMedian(workDays));
			stdTextField.setText(String.format("%.2f", Utilizer.calcStd(workDays)));
		}
		else {
			Toast.makeText(ProjectFX.mainStage, "there is not change request in sql table", 1500, 500, 500);
		}
	}
	
	/**
	 * the method gets ArrayList of all the work days
	 * sorting the days in 4 category 0-10,10-20,20-30,30+ days.
	 *
	 * @param workDays the work days
	 * @return the int[]
	 */
	public int[] workDaysCalc(ArrayList<Long> workDays) {
		int[] list = { 0, 0, 0, 0 };
		for (int i = 0; i < workDays.size(); i++) {
			if (workDays.get(i) <= 10)
				list[0]++;
			else if (workDays.get(i) <= 20)
				list[1]++;
			else if (workDays.get(i) <= 30)
				list[2]++;
			else if (workDays.get(i) > 30)
				list[3]++;
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		activityReportDetailsPane.setVisible(false);
		startDatePicker.setEditable(false);
		endDatePicker.setEditable(false);
		workDaysBarChart.setTitle("Work Duration");
		workDaysChartBarCategory.setLabel("Work Days");
		workDaysChartBarCategory.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(FIRST_CATAGORY, SECOND_CATAGORY, THIRD_CATAGORY, FOURTH_CATAGORY)));
	}

}