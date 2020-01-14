package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.DelayReportController;
import controllers.TimeManager;
import controllers.Utilizer;
import entities.DelayReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	private static final String FIRST_CATAGORY = "Lecturer\nStation";
	private static final String SECOND_CATAGORY = "Student\nStation";
	private static final String THIRD_CATAGORY = "Employee\nStation";
	private static final String FOURTH_CATAGORY = "Moodle";
	private static final String FIFTH_CATAGORY = "Library";
	private static final String SIXTH_CATAGORY = "Class\nRooms";
	private static final String SEVENTH_CATAGORY = "Laboratory";
	private static final String EIGHTH_CATAGORY = "Computer\nFarm";
	private static final String NINTH_CATAGORY = "College\nWebsite";
	private static final int CATEGORY = 9;
	
    /* ************************************ 
	 * ******* Public Methods *************
	 * ************************************/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		medianTextField.setEditable(false);
		stdTextField.setEditable(false);
		dealyBarChart.setTitle("");
		delayCategoryBarChart.setLabel("Delay Days");
		delayCategoryBarChart.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(FIRST_CATAGORY, SECOND_CATAGORY, THIRD_CATAGORY, FOURTH_CATAGORY,FIFTH_CATAGORY
                		,SIXTH_CATAGORY,SEVENTH_CATAGORY,EIGHTH_CATAGORY,NINTH_CATAGORY)));
		myController.getAllStepsDate();
	}

	/**
	 * this method gets object with delay report details
	 * the method display the details in the page
	 * @param delayReportList
	 */
	public void displayDealyReport(ArrayList<DelayReport> delayReportList) {
		ArrayList<Long> delayDays = new ArrayList<>();
		int[] delayDaysCntArray;
		delayDays.addAll(createDelayDaysList(delayReportList));
		delayDaysCntArray = createDelayDaysCntArray(delayReportList);
		XYChart.Series<String,Number> series1 = new XYChart.Series<String, Number>();
		series1.getData().add(new XYChart.Data<String,Number>(FIRST_CATAGORY, delayDaysCntArray[0]));
		series1.getData().add(new XYChart.Data<String,Number>(SECOND_CATAGORY, delayDaysCntArray[1]));
		series1.getData().add(new XYChart.Data<String,Number>(THIRD_CATAGORY, delayDaysCntArray[2]));
		series1.getData().add(new XYChart.Data<String,Number>(FOURTH_CATAGORY, delayDaysCntArray[3]));
		series1.getData().add(new XYChart.Data<String,Number>(FIFTH_CATAGORY, delayDaysCntArray[4]));
		series1.getData().add(new XYChart.Data<String,Number>(SIXTH_CATAGORY, delayDaysCntArray[5]));
		series1.getData().add(new XYChart.Data<String,Number>(SEVENTH_CATAGORY, delayDaysCntArray[6]));
		series1.getData().add(new XYChart.Data<String,Number>(EIGHTH_CATAGORY, delayDaysCntArray[7]));
		series1.getData().add(new XYChart.Data<String,Number>(NINTH_CATAGORY, delayDaysCntArray[8]));
		dealyBarChart.getData().addAll(series1);
		if(!delayDays.isEmpty()) {
			medianTextField.setText(Utilizer.calcMedian(delayDays) + "");
			stdTextField.setText(Utilizer.calcStd(delayDays) + "");
		}
		else {
			Toast.makeText(ProjectFX.mainStage, "There is not delay days", 1500, 500, 500);
		}
	}
	
	/**
	 * this method create delay days counter array for display at bar chart 
	 * @param delayReportList
	 * @return
	 */
	private int[] createDelayDaysCntArray(ArrayList<DelayReport> delayReportList) {
		int[] array = new int[CATEGORY];
		for(int i=0;i<delayReportList.size();i++) {
			long daysBetween = TimeManager.getDaysBetween(delayReportList.get(i).getEstimateDate(),
					delayReportList.get(i).getEndDate());
			if(daysBetween > 1) {
				switch (delayReportList.get(i).getSubsystem()) {
				case "Lecturer Information Station":
					array[0]++;
					break;
				case "Student Information Station":
					array[1]++;
					break;
				case "Employee Information Station":
					array[2]++;
					break;
				case "Moodle System":
					array[3]++;
					break;
				case "Library System":
					array[4]++;
					break;
				case "Class Rooms With Computers":
					array[5]++;
					break;
				case "Laboratory":
					array[6]++;
					break;
				case "Computer Farm":
					array[7]++;
					break;
				case "College Website":
					array[8]++;
					break;
				default:
					break;
				}
			}
		}
		return array;
	}
	
	/**
	 * this method create Delay Days List
	 * @param delayReportList
	 * @return
	 */
	private ArrayList<Long> createDelayDaysList(ArrayList<DelayReport> delayReportList) {
		ArrayList<Long> list = new ArrayList<>();
		for(int i = 0 ; i < delayReportList.size() ; i++) {
			long daysBetween = TimeManager.getDaysBetween(delayReportList.get(i).getEstimateDate(),
					delayReportList.get(i).getEndDate());
			if(daysBetween > 1) {
				list.add(Math.abs(daysBetween));
			}	
		}
		return list;
	}

}