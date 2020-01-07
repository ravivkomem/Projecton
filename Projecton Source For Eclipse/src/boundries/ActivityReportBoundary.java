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

public class ActivityReportBoundary implements Initializable {

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
    private BarChart<String, Number> workDaysBarChart;
    @FXML
    private CategoryAxis workDaysChartBarCategory;
    @FXML
    private NumberAxis DaysNumberBarChart;

	 /* *************************************
	  * ******* Private Objects *************
	  * *************************************/
	private ActivityReportController myController = new ActivityReportController(this);
	private ActivityReport activityReport;
	private ArrayList<ChangeRequest> changeRequestList;
	private static final String FIRST_CATAGORY = "0-10";
	private static final String SECOND_CATAGORY = "10-20";
	private static final String THIRD_CATAGORY = "20-30";
	private static final String FOURTH_CATAGORY = "30+";

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/

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
	 * and create an ActivityReport
	 * @param requestList
	 */
	public void createActivityReportList(ArrayList<ChangeRequest> requestList) {
		if(requestList.isEmpty())
			return;
		changeRequestList = requestList;
		int active = 0, close = 0, suspended = 0, denied = 0;
		ArrayList<Long> workDays = new ArrayList<>();
		for (int i = 0; i < changeRequestList.size(); i++) {
			if (changeRequestList.get(i).getStatus().equals("Active")) {
				active++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays.add(daysBetween);
			} else if (changeRequestList.get(i).getStatus().equals("DENIED")) {
				denied++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						changeRequestList.get(i).getEndDate());
				workDays.add(daysBetween);
			} else if (changeRequestList.get(i).getStatus().equals("SUSPENDED")) {
				suspended++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						TimeManager.getCurrentDate());
				workDays.add(daysBetween);
			} else if(changeRequestList.get(i).getStatus().equals("CLOSE")) {
				close++;
				long daysBetween = TimeManager.getDaysBetween(changeRequestList.get(i).getStartDate(),
						changeRequestList.get(i).getEndDate());
				workDays.add(daysBetween);
			}
		}
		displayActivityReport(activityReport = new ActivityReport(active, close, suspended,denied, workDays),workDays);
	}
	
	/**
	 * this method display the report in the page
	 * @param report
	 * @param workDays 
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