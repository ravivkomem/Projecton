package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.ActivityReportController;
import controllers.ActivityReportNewController;
import controllers.TimeManager;
import entities.NewActivityReport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ActivityReportNewBoundary implements Initializable{

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button showDetailsButton;
    @FXML
    private AnchorPane activityReportDetailsPane;
    @FXML
    private BarChart<String, Number> activityBarChart;
    @FXML
    private CategoryAxis activityCategory;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private TextField medianTextField;
    @FXML
    private TextField stdTextField;
    
 	/* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    
    private ActivityReportNewController myController = new ActivityReportNewController(this);
    
private static final String[] CATAGORY = new String[10];
    
    @FXML
    void showActivityReportDetails(MouseEvent event) {
    	activityBarChart.getData().clear();
		if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
			activityReportDetailsPane.setVisible(false);
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
				activityReportDetailsPane.setVisible(false);
				Toast.makeText(ProjectFX.mainStage, "Please choose a valid date", 1500, 500, 500);
				return;
			}
			myController.selectChangeRequestsBetween(startDate, endDate);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i = 0;i<10;i++) {
			CATAGORY[i] = ""+(i+1);
		}
		
	}

	public void displayActivityReport(NewActivityReport newReport) {
		long[] changeRequestNumber = newReport.getNumberOfChangeRequests();
		long startDateTime = newReport.getStartDate().getTime();
		long endDateTime = newReport.getEndDate().getTime();
		long daysBetween = endDateTime - startDateTime;
		long jumpDays = daysBetween/changeRequestNumber.length;
		
		XYChart.Series<String,Number> series1 = new XYChart.Series<String, Number>();
		
		for(int i = 0; i < changeRequestNumber.length ; i++)
		{
			long catgoryDateTime = startDateTime + jumpDays*i;
			long nextDateTime = catgoryDateTime+jumpDays;
			Date catgoryDate = new Date(catgoryDateTime);
			Date nextDate = new Date(nextDateTime);
			String catgoryName = catgoryDate.toString() +" - " + nextDate.toString();
			series1.getData().add(new XYChart.Data<String,Number>(CATAGORY[i], changeRequestNumber[i]));
		}
		series1.setName("Number Of Change Requests Starting From " + newReport.getStartDate().toString() + 
				" With jumps of " + (int)jumpDays/86400000 );
		activityBarChart.getData().addAll(series1);
		activityCategory.setLabel("Split to days");
		numberAxis.setLabel("Days");
		medianTextField.setText(newReport.getMedian()+"");
		stdTextField.setText(newReport.getStd()+"");

		myController.insertNewActivityReport(newReport);
	}


}
