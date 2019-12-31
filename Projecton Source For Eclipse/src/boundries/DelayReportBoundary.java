package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import controllers.DelayReportController;
import controllers.TimeManager;
import entities.ChangeRequest;
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
	private static final String FIRST_CATAGORY = "0-5";
	private static final String SECOND_CATAGORY = "5-10";
	private static final String THIRD_CATAGORY = "10-15";
	private static final String FOURTH_CATAGORY = "15+";
    
    /* ************************************ 
	 * ******* Public Methods *************
	 * ************************************/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dealyBarChart.setTitle("");
		delayCategoryBarChart.setLabel("Delay Days");
		delayCategoryBarChart.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(FIRST_CATAGORY, SECOND_CATAGORY, THIRD_CATAGORY, FOURTH_CATAGORY)));
		myController.getAllChangeRequest();
	}

	public void displayDealyReport(ArrayList<ChangeRequest> changeRequestList) {
		ArrayList<Long> delayDays = new ArrayList<>();
		int[] delayDaysCntArray;
		delayDays.addAll(createDelayDaysList(changeRequestList));
		
	}
	
	private ArrayList<Long> createDelayDaysList(ArrayList<ChangeRequest> changeRequestList) {
		ArrayList<Long> list = new ArrayList<>();
		for(int i = 0 ; i < changeRequestList.size() ; i++) {
			if(changeRequestList.get(i).getStatus().equals("Active")) {
				//long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), changeRequestList.get(i).get)
			}
			//long daysBetween		
		}
		return list;
	}

}