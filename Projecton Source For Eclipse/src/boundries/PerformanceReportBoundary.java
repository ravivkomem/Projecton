package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import assets.ProjectPages;
import controllers.PerformanceReportController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.Step;
import entities.TimeExtension;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PerformanceReportBoundary implements Initializable {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
    @FXML
    private BarChart<String, Number> extensionDaysBarChart;
    @FXML
    private CategoryAxis changeRequestStepCatagoryAxis;
    @FXML
    private NumberAxis extensionDaysNumberAxis;
    @FXML
    private Text reportHeader;
    @FXML
    private TextField totalExtensionDaysTextField;
    @FXML
    private TextField totalRepeatingDaysTextField;
    @FXML
    private TextField totalDeviationDaysTextField;
    
    /* *************************************
	 * ******** Private Objects ************
	 * *************************************/
    private PerformanceReportController myController = new PerformanceReportController(this);
    private static final String ANALYSIS = "Analysis";
    private static final String COMMITTEE = "Committee";
    private static final String EXECUTION = "Execution";
    private static final String TESTING = "Testing";
    
    /* *************************************
	 * ********* FXML Methods **************
	 * *************************************/
    @FXML
    void closePage(ActionEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    
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

	public void addDeviationChangeRequestsToReport(ArrayList<ChangeRequest> deviationChangeRequestList) {
		long totalDeviationDays = 0;
		for (ChangeRequest changeRequest : deviationChangeRequestList)
		{
			totalDeviationDays += TimeManager.getDaysBetween(changeRequest.getEstimatedEndDate(), changeRequest.getEndDate());
		}
		
		totalDeviationDaysTextField.setText(Long.toString(totalDeviationDays));
	}
}
