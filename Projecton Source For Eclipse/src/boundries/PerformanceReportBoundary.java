package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import assets.ProjectPages;
import controllers.PerformanceReportController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.TimeExtension;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PerformanceReportBoundary implements Initializable {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
    @FXML
    private Button closePageButton;
    @FXML
    private Pane reportPane;
    @FXML
    private BarChart<String, Number> extensionDaysBarChart;
    @FXML
    private CategoryAxis changeRequestStepCatagoryAxis;
    @FXML
    private NumberAxis extensionDaysNumberAxis;
    @FXML
    private Text reportHeader;
    @FXML
    private TextField totalDaysTextField;
    @FXML
    private Button displayButton;
    
    /* *************************************
	 * ******** Private Objects ************
	 * *************************************/
    private PerformanceReportController myController = new PerformanceReportController(this);
    
    @FXML
    void closePage(ActionEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void displayReport(ActionEvent event) {
    	myController.getAllExtensionRequestsFromServer();
    }
    
    public void createReport(ArrayList<TimeExtension> timeExtensionList) {
		/* Get graph data */
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
				case TESTEING:
					testingDaysCounter += extensionDays;
					break;
				default:
					System.out.println("ERROR - timeExtension ID " + timeExtension.getTimeExtensionID() +" "
							+ "has invalid step type");
					break;
			}
		}
		
		/*Set Graph Labels*/
		extensionDaysBarChart.setTitle("Extension Days");
		changeRequestStepCatagoryAxis.setLabel("Change Request Step");
		changeRequestStepCatagoryAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList("analysis", "committee", "execution", "tester")));
		extensionDaysNumberAxis.setLabel("Days");
		
		/*Insert Graph Data*/
	    XYChart.Series series1 = new XYChart.Series();   
	    series1.getData().add(new XYChart.Data("analysis", analysisDaysCounter));
        series1.getData().add(new XYChart.Data("committee", committeeDaysCounter));
        series1.getData().add(new XYChart.Data("execution", executionDaysCounter));
        series1.getData().add(new XYChart.Data("tester", testingDaysCounter));    

        extensionDaysBarChart.getData().addAll(series1);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		totalDaysTextField.setVisible(false);
		
	}

	

}
