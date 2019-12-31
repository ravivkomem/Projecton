package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import controllers.DelayReportController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.DelayReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;

public class DelayReportBoundary implements Initializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
    @FXML
    private BarChart<String, Number> dealyBarChart;

    @FXML
    private CategoryAxis delayCategoryBarChart;

    @FXML
    private NumberAxis delayNumberBarChart;

    @FXML
    private TextField medianTextField;

    @FXML
    private TextField stdTextField;
    
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    
    private DelayReportController myController = new DelayReportController(this);
	private static final String FIRST_CATAGORY = "Lecturer Station";
	private static final String SECOND_CATAGORY = "Student Station";
	private static final String THIRD_CATAGORY = "Employee Station";
	private static final String FOURTH_CATAGORY = "Moodle";
	private static final String FIFTH_CATAGORY = "Library";
	private static final String SIXTH_CATAGORY = "Class Rooms";
	private static final String SEVENTH_CATAGORY = "Laboratory";
	private static final String EIGHTH_CATAGORY = "Computer Farm";
	private static final String NINTH_CATAGORY = "College Website";
	
    /* ************************************ 
	 * ******* Public Methods *************
	 * ************************************/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dealyBarChart.setTitle("");
		delayCategoryBarChart.setLabel("Delay Days");
		delayCategoryBarChart.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(FIRST_CATAGORY, SECOND_CATAGORY, THIRD_CATAGORY, FOURTH_CATAGORY,FIFTH_CATAGORY
                		,SIXTH_CATAGORY,SEVENTH_CATAGORY,EIGHTH_CATAGORY,NINTH_CATAGORY)));
		myController.getAllStepsDate();
	}

	public void displayDealyReport(ArrayList<DelayReport> delayReportList) {
		ArrayList<Long> delayDays = new ArrayList<>();
		int[] delayDaysCntArray;
		delayDays.addAll(createDelayDaysList(delayReportList));
		
	}
	
	private ArrayList<Long> createDelayDaysList(ArrayList<DelayReport> delayReportList) {
		ArrayList<Long> list = new ArrayList<>();
		for(int i = 0 ; i < delayReportList.size() ; i++) {
			if(true) {
				//long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), changeRequestList.get(i).get)
			}
			//long daysBetween		
		}
		return list;
	}

}