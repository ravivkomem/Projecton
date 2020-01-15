package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import controllers.PerformanceReportController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.Step;
import entities.TimeExtension;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

// TODO: Auto-generated Javadoc
/**
 * This is the boundary for the performance report displayed on the tech manager page.
 *
 * @author Raviv Komem
 */
public class PerformanceReportBoundary implements Initializable {

	/** The extension days bar chart. */
	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
    @FXML
    private BarChart<String, Number> extensionDaysBarChart;
    
    /** The change request step catagory axis. */
    @FXML
    private CategoryAxis changeRequestStepCatagoryAxis;
    
    /** The extension days number axis. */
    @FXML
    private NumberAxis extensionDaysNumberAxis;
    
    /** The report header. */
    @FXML
    private Text reportHeader;
    
    /** The total extension days text field. */
    @FXML
    private TextField totalExtensionDaysTextField;
    
    /** The total repeating days text field. */
    @FXML
    private TextField totalRepeatingDaysTextField;
    
    /** The total deviation days text field. */
    @FXML
    private TextField totalDeviationDaysTextField;
    
    /** The my controller. */
    /* *************************************
	 * ******** Private Objects ************
	 * *************************************/
    private PerformanceReportController myController = new PerformanceReportController(this);
    
    /** The Constant ANALYSIS. */
    private static final String ANALYSIS = "Analysis";
    
    /** The Constant COMMITTEE. */
    private static final String COMMITTEE = "Committee";
    
    /** The Constant EXECUTION. */
    private static final String EXECUTION = "Execution";
    
    /** The Constant TESTING. */
    private static final String TESTING = "Testing";
    
    /* *************************************
	 * ********* FXML Methods **************
	 * *************************************/

    /**
     * Asks the controller for all relevant data.
     *
     * @param arg0 the arg 0
     * @param arg1 the arg 1
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	totalExtensionDaysTextField.setEditable(false);
    	totalRepeatingDaysTextField.setEditable(false);
    	
		extensionDaysBarChart.setTitle("Performance Graph");
		extensionDaysBarChart.setLegendSide(Side.RIGHT);
		changeRequestStepCatagoryAxis.setLabel("Change Request Step");
		changeRequestStepCatagoryAxis.setCategories(FXCollections.<String>observableArrayList(
        Arrays.asList(ANALYSIS, COMMITTEE, EXECUTION, TESTING)));
		extensionDaysNumberAxis.setLabel("Days");
		
		myController.getAllExtensionRequestsFromServer();
    	myController.getAllRepeatingStepsFromServer();
    	myController.getAllChangeRequestsWithDeviations();
	}
    
    /* *************************************
   	 * ********* Public Methods ************
   	 * *************************************/
    /**
     * Adding the extension days to the report.
     *
     * @param timeExtensionList - all the approved time extensions
     * Dividing all the time extensions to the different steps and counting it
     */
    public void addExtensionDaysToReport(ArrayList<TimeExtension> timeExtensionList) {
    	long analysisDaysCounter = 0;
		long committeeDaysCounter = 0;
		long executionDaysCounter = 0;
		long testingDaysCounter = 0;
		for (TimeExtension timeExtension : timeExtensionList)
		{
			long extensionDays = TimeManager.getDaysBetween(timeExtension.getOldDate(), timeExtension.getNewDate());
			switch (timeExtension.getStepType())
			{
				case ANALYSIS:
					analysisDaysCounter += extensionDays;
					break;
				case COMMITTEE:
					committeeDaysCounter += extensionDays;
					break;
				case EXECUTION:
					executionDaysCounter += extensionDays;
					break;
				case TESTING:
					testingDaysCounter += extensionDays;
					break;
				default:
					System.out.println("ERROR - timeExtension ID " + timeExtension.getTimeExtensionID() +" "
							+ "has invalid step type");
					break;
			}
		}
				
		/*Insert Graph Data*/
	    XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
	    series.setName("Extension Days");
	    series.getData().add(new XYChart.Data<String,Number>(ANALYSIS, analysisDaysCounter));
        series.getData().add(new XYChart.Data<String,Number>(COMMITTEE, committeeDaysCounter));
        series.getData().add(new XYChart.Data<String,Number>(EXECUTION, executionDaysCounter));
        series.getData().add(new XYChart.Data<String,Number>(TESTING, testingDaysCounter));    

        extensionDaysBarChart.getData().add(series);
        totalExtensionDaysTextField.setText(Long.toString(analysisDaysCounter+committeeDaysCounter+executionDaysCounter+testingDaysCounter));
	}

    /**
     * Adding the repeating steps to the report.
     *
     * @param repeatingStepList - All the steps that were repeating (not included first time conducting the step)
     */
	public void addRepeatingStepsToReport(ArrayList<Step> repeatingStepList) {
		long analysisDaysCounter = 0;
		long committeeDaysCounter = 0;
		long executionDaysCounter = 0;
		long testingDaysCounter = 0;
		for (Step step : repeatingStepList)
		{
			long additionalDays = 0;
			if (step.getEndDate() == null)
			{
				additionalDays = TimeManager.getDaysBetween(step.getStartDate(), TimeManager.getCurrentDate());
			}
			else
			{
				additionalDays = TimeManager.getDaysBetween(step.getStartDate(), step.getEndDate());
			}
			switch (step.getType())
			{
				case ANALYSIS:
					analysisDaysCounter += additionalDays;
					break;
				case COMMITTEE:
					committeeDaysCounter += additionalDays;
					break;
				case EXECUTION:
					executionDaysCounter += additionalDays;
					break;
				case TESTING:
					testingDaysCounter += additionalDays;
					break;
				default:
					System.out.println("ERROR - timeExtension ID " + step.getStepID() +" "
							+ "has invalid step type");
					break;
			}
		}
		
		/*Insert Graph Data*/
		XYChart.Series<String,Number> series2 = new XYChart.Series<String, Number>();
	    series2.setName("Repeating Steps");
	    series2.getData().add(new XYChart.Data<String,Number>(ANALYSIS, analysisDaysCounter));
	    series2.getData().add(new XYChart.Data<String,Number>(COMMITTEE, committeeDaysCounter));
	    series2.getData().add(new XYChart.Data<String,Number>(EXECUTION, executionDaysCounter));
	    series2.getData().add(new XYChart.Data<String,Number>(TESTING, testingDaysCounter));    
	    
        extensionDaysBarChart.getData().add(series2);
        totalRepeatingDaysTextField.setText(Long.toString(analysisDaysCounter+committeeDaysCounter+executionDaysCounter+testingDaysCounter));
	}

	/**
	 * All the change requests that had deviation from their original analysis.
	 *
	 * @param deviationChangeRequestList the deviation change request list
	 */
	public void addDeviationChangeRequestsToReport(ArrayList<ChangeRequest> deviationChangeRequestList) {
		long totalDeviationDays = 0;
		for (ChangeRequest changeRequest : deviationChangeRequestList)
		{
			totalDeviationDays += TimeManager.getDaysBetween(changeRequest.getEstimatedEndDate(), changeRequest.getEndDate());
		}
		
		totalDeviationDaysTextField.setText(Long.toString(totalDeviationDays));
	}
}
