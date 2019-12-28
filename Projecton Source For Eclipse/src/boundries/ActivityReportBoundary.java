package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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

public class ActivityReportBoundary implements DataInitializable{
	
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
			myController.getAllChangeRequest();
		}
	}

    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
    
    public void createActivityReportList(ArrayList<ChangeRequest> requestList) {
    	changeRequestList = requestList;
    	int active = 0, close = 0, suspended = 0, denied = 0, workDatys = 0;
    	for(int i=0;i<changeRequestList.size();i++) {
    		if(changeRequestList.get(i).getCurrentStep().equals("ACTIVE")) {
    			//TODO
    		}
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		activityReportDetailsPane.setVisible(false);
	}

	@Override
	public void initData(Object data) {
		
	}

}