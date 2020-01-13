package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.TesterController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.Step;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TesterBoundary implements DataInitializable {

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
    @FXML
    private AnchorPane FailDetailsPane;
    @FXML
    private Label charcterCounterLabel;
    @FXML
    private Text timeDisplayText;
    @FXML
    private TextArea timeremainingField;
    @FXML
    private Button backButton;
    @FXML
    private Button submitTestResultsButton;
    @FXML
    private Button reportfailButton;
    @FXML
    private TextArea failureReportTextArea;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homepageButton;
    @FXML
    private Button analysisreportButton;
    @FXML
    private Button timeExtensionButton;
    @FXML
    private Text pageHeaderText;
    @FXML
    private AnchorPane testWorkPane;
    
    /* Test texts */
    @FXML
    private Text test1Text;
    @FXML
    private Text test2Text;
    @FXML
    private Text test3Text;
    @FXML
    private Text test4Text;
    @FXML
    private Text test5Text;

    /* Radio Buttons */
    @FXML
    private RadioButton test1PassRadioButton;
    @FXML
    private RadioButton test1FailRadioButton;
    @FXML
    private RadioButton test2PassRadioButton;
    @FXML
    private RadioButton test2FailRadioButton;
    @FXML
    private RadioButton test3PassRadioButton;
    @FXML
    private RadioButton test3FailRadioButton;
    @FXML
    private RadioButton test4PassRadioButton;
    @FXML
    private RadioButton test4FailRadioButton;
    @FXML
    private RadioButton test5PassRadioButton;
    @FXML
    private RadioButton test5FailRadioButton;

    /* ****************************************
     * ********** Private Variables ***********
     * ***************************************/
    private TesterController myController = new TesterController(this);
    private final ToggleGroup test1Group = new ToggleGroup();
    private final ToggleGroup test2Group = new ToggleGroup();
    private final ToggleGroup test3Group = new ToggleGroup();
    private final ToggleGroup test4Group = new ToggleGroup();
    private final ToggleGroup test5Group = new ToggleGroup();
    private ChangeRequest currentChangeRequest;
	private Step testerStep;
	private Stage myTimeExtensionStage;
	private Stage myAnalysisReportStage;
    private static final int MAX_CHARS = 600;
	
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    @FXML
    void LogOut(MouseEvent event) {
    	closeMyStages();
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

	@FXML
    void loadHomePage(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void loadPreviousPage(MouseEvent event) {
    	closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }

    @FXML
    void loadTimeExtensionPage(MouseEvent event) {
    	if (myTimeExtensionStage == null) {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),testerStep);
		} else if (myTimeExtensionStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
		} else {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),testerStep);
		}
    }
    
    @FXML
    void loadAnalysisReport(MouseEvent event) {
    	if (myAnalysisReportStage == null) {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), currentChangeRequest);
		} else if (myAnalysisReportStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Analysis Report Window is already open", 1500, 500, 500);
		} else {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), currentChangeRequest);
		}
    }

    @FXML
    void setReportFail(MouseEvent event) {
    	if(failureReportTextArea.getText().equals(""))
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please fill the failure report before submitting ", 1500, 500, 500);
    	}
    	else 
    	{
		myController.closeChangeRequestStep(testerStep.getStepID(), failureReportTextArea.getText());
		myController.advanceChangeRequestStep("EXECUTION_LEADEAR_SUPERVISOR_APPOINT", currentChangeRequest.getChangeRequestID());
		closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    	}
    }

    @FXML
    void submitTestResults(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirm Test Results");
    	alert.setHeaderText("Are you sure you want to submit test results?");
    	alert.setContentText("If all tests passed you will be moved back to the work station\n"
    			+ "Else you will have to add failure report");
    	Optional<ButtonType> res = alert.showAndWait();

        if(res.isPresent()) 
        {
            if(res.get().equals(ButtonType.CANCEL))
            {
            	event.consume();
            	return;
            }
        }
        
    	RadioButton answer1 = (RadioButton) test1Group.getSelectedToggle();
    	RadioButton answer2 = (RadioButton) test2Group.getSelectedToggle();
    	RadioButton answer3 = (RadioButton) test3Group.getSelectedToggle();
    	RadioButton answer4 = (RadioButton) test4Group.getSelectedToggle();
    	RadioButton answer5 = (RadioButton) test5Group.getSelectedToggle();
    	
    	if (isFullyFilled(answer1, answer2, answer3, answer4, answer5))
    	{
    		if (answer1.equals(test1FailRadioButton) || answer2.equals(test2FailRadioButton) || answer3.equals(test3FailRadioButton)
    				|| answer4.equals(test4FailRadioButton) || answer5.equals(test5FailRadioButton))
    		{
    			String failedTestsText = "Failed Tests: \n";
    			if (answer1.equals(test1FailRadioButton))
    			{
    				failedTestsText += test1Text.getText() + "\n";
    			}
    			if (answer2.equals(test2FailRadioButton))
    			{
    				failedTestsText += test2Text.getText() + "\n";
    			}
    			if (answer3.equals(test3FailRadioButton))
    			{
    				failedTestsText += test3Text.getText() + "\n";
    			}
    			if (answer4.equals(test4FailRadioButton))
    			{
    				failedTestsText += test4Text.getText() + "\n";
    			}
    			if (answer5.equals(test5FailRadioButton))
    			{
    				failedTestsText += test5Text.getText() + "\n";
    			}
    			
    			failureReportTextArea.setText(failedTestsText);
    			updateCharcterCounter(null);
    			FailDetailsPane.setVisible(true);
    			testWorkPane.setVisible(false);
    		}
    		/* All tests passed */
    		else
    		{
    			myController.closeChangeRequestStep(testerStep.getStepID(), "");
				myController.advanceChangeRequestStep("CLOSING_STEP",currentChangeRequest.getChangeRequestID());
				closeMyStages();
				ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    		}
    		
    	}
    }
    
    @FXML
    void updateCharcterCounter(KeyEvent event) {
    	charcterCounterLabel.setText(failureReportTextArea.getText().length() + "/ " + MAX_CHARS);
    }

	/* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
    public void updateTesterPageToDBSuccessfully(int affectedRows) {
    	if(affectedRows == 1) {
    		Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
    	}else {
    		Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/* Set Text*/
		pageHeaderText.setText("Loading Change Request Information");
		
		/* Change visibilities */
		testWorkPane.setVisible(false);
		FailDetailsPane.setVisible(false);
		
		/* Init radio button */
		test1PassRadioButton.setToggleGroup(test1Group);
		test1FailRadioButton.setToggleGroup(test1Group);
		
		test2PassRadioButton.setToggleGroup(test2Group);
		test2FailRadioButton.setToggleGroup(test2Group);
		
		test3PassRadioButton.setToggleGroup(test3Group);
		test3FailRadioButton.setToggleGroup(test3Group);
		
		test4PassRadioButton.setToggleGroup(test4Group);
		test4FailRadioButton.setToggleGroup(test4Group);
		
		test5PassRadioButton.setToggleGroup(test5Group);
		test5FailRadioButton.setToggleGroup(test5Group);
		
		/* Change editiable */
		timeremainingField.setEditable(false);
		failureReportTextArea.setWrapText(true);
		
		/* Disable buttons */
		analysisreportButton.setDisable(true);
		timeExtensionButton.setDisable(true);
	
		/* Limit charcters in the failure report text area */
		failureReportTextArea.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= MAX_CHARS ? change : null));
	}

	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest) data;
		myController.getCurrentStep(currentChangeRequest.getChangeRequestID());
	}

	public void recieveCurrentStep(Step recievedStep) {
		testerStep = recievedStep;
		pageHeaderText.setText("Working on change request No."+ currentChangeRequest.getChangeRequestID());
		testWorkPane.setVisible(true);
		analysisreportButton.setDisable(false);
		timeExtensionButton.setDisable(false);
		
		displayTimeRemaining(testerStep.getEstimatedEndDate());
		
	}
	
	/* *****************************************
     * ********** Private Methods **************
     * *****************************************/
	
	private void closeMyStages() {
    	if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
	}
	
	private void displayTimeRemaining(Date estimatedEndDate) {
		long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), estimatedEndDate);
		if(daysBetween < 0) {
			timeDisplayText.setText("Time Delay");
			timeremainingField.setText(Math.abs(daysBetween) + " Days");
		}
		else if(daysBetween == 0) {
			timeDisplayText.setText("Time Remaining");
			timeremainingField.setText("Last Day");
		}
		else {
			timeDisplayText.setText("Time Remaining");
			timeremainingField.setText(daysBetween + " Days");
		}
	}
	
    private boolean isFullyFilled(RadioButton answer1, RadioButton answer2, RadioButton answer3, RadioButton answer4,
			RadioButton answer5) {
    	if (answer1 == null || answer2 == null || answer3 == null || answer4 == null || answer5 == null)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Must conduct all tests");
			String contentText = "Missing Tests: \n";
			if (answer1 == null)
			{
				contentText += test1Text.getText() + "\n";
			}
			if (answer2 == null)
			{
				contentText += test2Text.getText() + "\n";
			}
			if (answer3 == null)
			{
				contentText += test3Text.getText() + "\n";
			}
			if (answer4 == null)
			{
				contentText += test4Text.getText() + "\n";
			}
			if (answer5 == null)
			{
				contentText += test5Text.getText() + "\n";
			}
			alert.setContentText(contentText);
			alert.showAndWait();
			return false;
		}
		return true;
	}

}
