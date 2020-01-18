package boundries;

import java.net.URL;
import java.text.DecimalFormat;
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

/**
 * The Class DelayReportBoundary.
 * @author Lee Hugi
 * This class control the delay report page
 *
 */
public class DelayReportBoundary implements Initializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
    /** The delay bar chart. */
	@FXML
    private BarChart<String, Number> dealyBarChart;
    
    /** The delay category bar chart. */
    @FXML
    private CategoryAxis delayCategoryBarChart;
    
    /** The delay number bar chart. */
    @FXML
    private NumberAxis delayNumberBarChart;

    /** The median text field. */
    @FXML
    private TextField medianTextField;
    
    /** The std text field. */
    @FXML
    private TextField stdTextField;
    @FXML
    private TextField avgTextField;
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    
    /** The my controller. */
    private DelayReportController myController = new DelayReportController(this);
	
	/** The Constant FIRST_CATAGORY. */
	private static final String FIRST_CATAGORY = "Lecturer\nStation";
	
	/** The Constant SECOND_CATAGORY. */
	private static final String SECOND_CATAGORY = "Student\nStation";
	
	/** The Constant THIRD_CATAGORY. */
	private static final String THIRD_CATAGORY = "Employee\nStation";
	
	/** The Constant FOURTH_CATAGORY. */
	private static final String FOURTH_CATAGORY = "Moodle";
	
	/** The Constant FIFTH_CATAGORY. */
	private static final String FIFTH_CATAGORY = "Library";
	
	/** The Constant SIXTH_CATAGORY. */
	private static final String SIXTH_CATAGORY = "Class\nRooms";
	
	/** The Constant SEVENTH_CATAGORY. */
	private static final String SEVENTH_CATAGORY = "Laboratory";
	
	/** The Constant EIGHTH_CATAGORY. */
	private static final String EIGHTH_CATAGORY = "Computer\nFarm";
	
	/** The Constant NINTH_CATAGORY. */
	private static final String NINTH_CATAGORY = "College\nWebsite";
	
	/** The Constant CATEGORY. */
	private static final int CATEGORY = 9;
	
    /* ************************************ 
	 * ******* Public Methods *************
	 * ************************************/

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		medianTextField.setEditable(false);
		stdTextField.setEditable(false);
		avgTextField.setEditable(false);
		dealyBarChart.setTitle("");
		delayCategoryBarChart.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(FIRST_CATAGORY, SECOND_CATAGORY, THIRD_CATAGORY, FOURTH_CATAGORY,FIFTH_CATAGORY
                		,SIXTH_CATAGORY,SEVENTH_CATAGORY,EIGHTH_CATAGORY,NINTH_CATAGORY)));
		myController.getAllStepsDate();
	}

	/**
	 * this method gets object with delay report details
	 * the method display the details in the page.
	 *
	 * @param delayReportList the delay report list
	 */
	public void displayDealyReport(ArrayList<DelayReport> delayReportList) {
		ArrayList<Long> delayDays = new ArrayList<>();
		int[] deleysCounter;
		delayDays.addAll(createDelayDaysList(delayReportList));
		deleysCounter = createDelayDaysCntArray(delayReportList);
		long[] delayDaysArr;
		delayDaysArr = createDelayDaysArray(delayReportList);
		long[] medianArr;
		medianArr = createMedianArr(delayReportList);
		long[] standartDeviationArr;
		standartDeviationArr = createStandartDeviationArr(delayReportList);
		
		XYChart.Series<String,Number> series1 = new XYChart.Series<String, Number>();
		series1.getData().add(new XYChart.Data<String,Number>(FIRST_CATAGORY, deleysCounter[0]));
		series1.getData().add(new XYChart.Data<String,Number>(SECOND_CATAGORY, deleysCounter[1]));
		series1.getData().add(new XYChart.Data<String,Number>(THIRD_CATAGORY, deleysCounter[2]));
		series1.getData().add(new XYChart.Data<String,Number>(FOURTH_CATAGORY, deleysCounter[3]));
		series1.getData().add(new XYChart.Data<String,Number>(FIFTH_CATAGORY, deleysCounter[4]));
		series1.getData().add(new XYChart.Data<String,Number>(SIXTH_CATAGORY, deleysCounter[5]));
		series1.getData().add(new XYChart.Data<String,Number>(SEVENTH_CATAGORY, deleysCounter[6]));
		series1.getData().add(new XYChart.Data<String,Number>(EIGHTH_CATAGORY, deleysCounter[7]));
		series1.getData().add(new XYChart.Data<String,Number>(NINTH_CATAGORY, deleysCounter[8]));
		series1.setName("Delay Counter");
		dealyBarChart.getData().addAll(series1);
		
		XYChart.Series<String,Number> series2 = new XYChart.Series<String, Number>();
		series2.getData().add(new XYChart.Data<String,Number>(FIRST_CATAGORY, deleysCounter[0] == 0 ? 0 :delayDaysArr[0]/deleysCounter[0]));
		series2.getData().add(new XYChart.Data<String,Number>(SECOND_CATAGORY, deleysCounter[1] == 0 ? 0 :delayDaysArr[1]/deleysCounter[1]));
		series2.getData().add(new XYChart.Data<String,Number>(THIRD_CATAGORY, deleysCounter[2] == 0 ? 0 :delayDaysArr[2]/deleysCounter[2]));
		series2.getData().add(new XYChart.Data<String,Number>(FOURTH_CATAGORY, deleysCounter[3] == 0 ? 0 :delayDaysArr[3]/deleysCounter[3]));
		series2.getData().add(new XYChart.Data<String,Number>(FIFTH_CATAGORY, deleysCounter[4] == 0 ? 0 :delayDaysArr[4]/deleysCounter[4]));
		series2.getData().add(new XYChart.Data<String,Number>(SIXTH_CATAGORY, deleysCounter[5] == 0 ? 0 :delayDaysArr[5]/deleysCounter[5]));
		series2.getData().add(new XYChart.Data<String,Number>(SEVENTH_CATAGORY, deleysCounter[6] == 0 ? 0 :delayDaysArr[6]/deleysCounter[6]));
		series2.getData().add(new XYChart.Data<String,Number>(EIGHTH_CATAGORY, deleysCounter[7] == 0 ? 0 :delayDaysArr[7]/deleysCounter[7]));
		series2.getData().add(new XYChart.Data<String,Number>(NINTH_CATAGORY, deleysCounter[8] == 0 ? 0 :delayDaysArr[8]/deleysCounter[8]));
		series2.setName("Avg Delay Days");
		dealyBarChart.getData().addAll(series2);
		
		XYChart.Series<String,Number> series3 = new XYChart.Series<String, Number>();
		series3.getData().add(new XYChart.Data<String,Number>(FIRST_CATAGORY, medianArr[0]));
		series3.getData().add(new XYChart.Data<String,Number>(SECOND_CATAGORY, medianArr[1]));
		series3.getData().add(new XYChart.Data<String,Number>(THIRD_CATAGORY, medianArr[2]));
		series3.getData().add(new XYChart.Data<String,Number>(FOURTH_CATAGORY, medianArr[3]));
		series3.getData().add(new XYChart.Data<String,Number>(FIFTH_CATAGORY, medianArr[4]));
		series3.getData().add(new XYChart.Data<String,Number>(SIXTH_CATAGORY, medianArr[5]));
		series3.getData().add(new XYChart.Data<String,Number>(SEVENTH_CATAGORY, medianArr[6]));
		series3.getData().add(new XYChart.Data<String,Number>(EIGHTH_CATAGORY, medianArr[7]));
		series3.getData().add(new XYChart.Data<String,Number>(NINTH_CATAGORY, medianArr[8]));
		series3.setName("Median Delay Days");
		dealyBarChart.getData().addAll(series3);
		
		XYChart.Series<String,Number> series4 = new XYChart.Series<String, Number>();
		series4.getData().add(new XYChart.Data<String,Number>(FIRST_CATAGORY, standartDeviationArr[0]));
		series4.getData().add(new XYChart.Data<String,Number>(SECOND_CATAGORY, standartDeviationArr[1]));
		series4.getData().add(new XYChart.Data<String,Number>(THIRD_CATAGORY, standartDeviationArr[2]));
		series4.getData().add(new XYChart.Data<String,Number>(FOURTH_CATAGORY, standartDeviationArr[3]));
		series4.getData().add(new XYChart.Data<String,Number>(FIFTH_CATAGORY, standartDeviationArr[4]));
		series4.getData().add(new XYChart.Data<String,Number>(SIXTH_CATAGORY, standartDeviationArr[5]));
		series4.getData().add(new XYChart.Data<String,Number>(SEVENTH_CATAGORY, standartDeviationArr[6]));
		series4.getData().add(new XYChart.Data<String,Number>(EIGHTH_CATAGORY, standartDeviationArr[7]));
		series4.getData().add(new XYChart.Data<String,Number>(NINTH_CATAGORY, standartDeviationArr[8]));
		series4.setName("Standart Deviation");
		dealyBarChart.getData().addAll(series4);
		
		if(!delayDays.isEmpty()) {
			DecimalFormat df2 = new DecimalFormat("##.##");
			medianTextField.setText(df2.format(Utilizer.calcMedian(delayDays)));
			stdTextField.setText(df2.format(Utilizer.calcStd(delayDays)));
			avgTextField.setText(df2.format(Utilizer.calcAvg(delayDays)));
			delayCategoryBarChart.setLabel("Subsytem");
			delayNumberBarChart.setLabel("Quantity");
			
		}
		else {
			Toast.makeText(ProjectFX.mainStage, "There is not delay days", 1500, 500, 500);
		}
	}
	
	private long[] createMedianArr(ArrayList<DelayReport> delayReportList)
	{
		long[] array = new long[CATEGORY];
		ArrayList<Long> lecturerInformationList = new ArrayList<Long>();
		ArrayList<Long> studendtInformationList = new ArrayList<Long>();
		ArrayList<Long> employeeInformationList = new ArrayList<Long>();
		ArrayList<Long> moodleSystemList = new ArrayList<Long>();
		ArrayList<Long> librarayList = new ArrayList<Long>();
		ArrayList<Long> classRoomsWithComputersList = new ArrayList<Long>();
		ArrayList<Long> laboratoryList = new ArrayList<Long>();
		ArrayList<Long> computerFarmList = new ArrayList<Long>();
		ArrayList<Long> collegeWebsiteList = new ArrayList<Long>();
		
		
		for (DelayReport report : delayReportList)
		{
			long daysBetween = TimeManager.getDaysBetween(report.getEstimateDate(),
					report.getEndDate());
			if(daysBetween > 0) 
			{
				switch (report.getSubsystem()) {
				case "Lecturer Information Station":
					lecturerInformationList.add(daysBetween);
					break;
				case "Student Information Station":
					studendtInformationList.add(daysBetween);
					break;
				case "Employee Information Station":
					employeeInformationList.add(daysBetween);
					break;
				case "Moodle System":
					moodleSystemList.add(daysBetween);
					break;
				case "Library System":
					librarayList.add(daysBetween);
					break;
				case "Class Rooms With Computers":
					classRoomsWithComputersList.add(daysBetween);
					break;
				case "Laboratory":
					laboratoryList.add(daysBetween);
					break;
				case "Computer Farm":
					computerFarmList.add(daysBetween);
					break;
				case "College Website":
					collegeWebsiteList.add(daysBetween);
					break;
				default:
					break;
				}
			}
			
		}
		array[0] = (long) Utilizer.calcMedian(lecturerInformationList);
		array[1] = (long) Utilizer.calcMedian(studendtInformationList);
		array[2] = (long) Utilizer.calcMedian(employeeInformationList);
		array[3] = (long) Utilizer.calcMedian(moodleSystemList);
		array[4] = (long) Utilizer.calcMedian(librarayList);
		array[5] = (long) Utilizer.calcMedian(classRoomsWithComputersList);
		array[6] = (long) Utilizer.calcMedian(laboratoryList);
		array[7] = (long) Utilizer.calcMedian(computerFarmList);
		array[8] = (long) Utilizer.calcMedian(collegeWebsiteList);
		
		return array;
	}
	
	private long[] createStandartDeviationArr(ArrayList<DelayReport> delayReportList) {
		long[] array = new long[CATEGORY];
		ArrayList<Long> lecturerInformationList = new ArrayList<Long>();
		ArrayList<Long> studendtInformationList = new ArrayList<Long>();
		ArrayList<Long> employeeInformationList = new ArrayList<Long>();
		ArrayList<Long> moodleSystemList = new ArrayList<Long>();
		ArrayList<Long> librarayList = new ArrayList<Long>();
		ArrayList<Long> classRoomsWithComputersList = new ArrayList<Long>();
		ArrayList<Long> laboratoryList = new ArrayList<Long>();
		ArrayList<Long> computerFarmList = new ArrayList<Long>();
		ArrayList<Long> collegeWebsiteList = new ArrayList<Long>();
		
		
		for (DelayReport report : delayReportList)
		{
			long daysBetween = TimeManager.getDaysBetween(report.getEstimateDate(),
					report.getEndDate());
			if(daysBetween > 0) 
			{
				switch (report.getSubsystem()) {
				case "Lecturer Information Station":
					lecturerInformationList.add(daysBetween);
					break;
				case "Student Information Station":
					studendtInformationList.add(daysBetween);
					break;
				case "Employee Information Station":
					employeeInformationList.add(daysBetween);
					break;
				case "Moodle System":
					moodleSystemList.add(daysBetween);
					break;
				case "Library System":
					librarayList.add(daysBetween);
					break;
				case "Class Rooms With Computers":
					classRoomsWithComputersList.add(daysBetween);
					break;
				case "Laboratory":
					laboratoryList.add(daysBetween);
					break;
				case "Computer Farm":
					computerFarmList.add(daysBetween);
					break;
				case "College Website":
					collegeWebsiteList.add(daysBetween);
					break;
				default:
					break;
				}
			}
			
		}
		array[0] = (long) Utilizer.calcStd(lecturerInformationList);
		array[1] = (long) Utilizer.calcStd(studendtInformationList);
		array[2] = (long) Utilizer.calcStd(employeeInformationList);
		array[3] = (long) Utilizer.calcStd(moodleSystemList);
		array[4] = (long) Utilizer.calcStd(librarayList);
		array[5] = (long) Utilizer.calcStd(classRoomsWithComputersList);
		array[6] = (long) Utilizer.calcStd(laboratoryList);
		array[7] = (long) Utilizer.calcStd(computerFarmList);
		array[8] = (long) Utilizer.calcStd(collegeWebsiteList);
		return array;
	}

	private long[] createDelayDaysArray(ArrayList<DelayReport> delayReportList) {
		long[] array = new long[CATEGORY];
		for(int i=0;i<delayReportList.size();i++) {
			long daysBetween = TimeManager.getDaysBetween(delayReportList.get(i).getEstimateDate(),
					delayReportList.get(i).getEndDate());
			if(daysBetween > 0) {
				switch (delayReportList.get(i).getSubsystem()) {
				case "Lecturer Information Station":
					array[0] += daysBetween;
					break;
				case "Student Information Station":
					array[1] += daysBetween;
					break;
				case "Employee Information Station":
					array[2] += daysBetween;
					break;
				case "Moodle System":
					array[3] += daysBetween;
					break;
				case "Library System":
					array[4] += daysBetween;
					break;
				case "Class Rooms With Computers":
					array[5] += daysBetween;
					break;
				case "Laboratory":
					array[6] += daysBetween;
					break;
				case "Computer Farm":
					array[7] += daysBetween;
					break;
				case "College Website":
					array[8] += daysBetween;
					break;
				default:
					System.out.println("Reached here with: " + delayReportList.get(i).getSubsystem());
					break;
				}
			}
		}
		return array;
	}

	/**
	 * this method create delay days counter array for display at bar chart .
	 *
	 * @param delayReportList the delay report list
	 * @return the int[]
	 */
	private int[] createDelayDaysCntArray(ArrayList<DelayReport> delayReportList) {
		int[] array = new int[CATEGORY];
		for(int i=0;i<delayReportList.size();i++) {
			long daysBetween = TimeManager.getDaysBetween(delayReportList.get(i).getEstimateDate(),
					delayReportList.get(i).getEndDate());
			if(daysBetween > 0) {
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
	 * this method create Delay Days List.
	 *
	 * @param delayReportList the delay report list
	 * @return the array list
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