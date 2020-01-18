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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Class TesterBoundary.
 *
 * @author raviv komem
 * This class represents the tester boundary
 * with all the methods and logic implementations
 * This boundary is used for the tester stage in ICM system
 */
public class TesterBoundary implements DataInitializable {

	/** The Fail details pane. */
	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
    @FXML
    private AnchorPane FailDetailsPane;
    
    /** The charcter counter label. */
    @FXML
    private Label charcterCounterLabel;
    
    /** The time display text. */
    @FXML
    private Text timeDisplayText;
    
    /** The timeremaining field. */
    @FXML
    private TextArea timeremainingField;
    
    /** The back button. */
    @FXML
    private Button backButton;
    
    /** The submit test results button. */
    @FXML
    private Button submitTestResultsButton;
    
    /** The reportfail button. */
    @FXML
    private Button reportfailButton;
    
    /** The failure report text area. */
    @FXML
    private TextArea failureReportTextArea;
    
    /** The logout button. */
    @FXML
    private Button logoutButton;
    
    /** The homepage button. */
    @FXML
    private Button homepageButton;
    
    /** The analysisreport button. */
    @FXML
    private Button analysisreportButton;
    
    /** The time extension button. */
    @FXML
    private Button timeExtensionButton;
    
    /** The page header text. */
    @FXML
    private Text pageHeaderText;
    
    /** The test work pane. */
    @FXML
    private AnchorPane testWorkPane;
    
    /** The test 1 text. */
    /* Test texts */
    @FXML
    private Text test1Text;
    
    /** The test 2 text. */
    @FXML
    private Text test2Text;
    
    /** The test 3 text. */
    @FXML
    private Text test3Text;
    
    /** The test 4 text. */
    @FXML
    private Text test4Text;
    
    /** The test 5 text. */
    @FXML
    private Text test5Text;

    /** The test 1 pass radio button. */
    /* Radio Buttons */
    @FXML
    private RadioButton test1PassRadioButton;
    
    /** The test 1 fail radio button. */
    @FXML
    private RadioButton test1FailRadioButton;
    
    /** The test 2 pass radio button. */
    @FXML
    private RadioButton test2PassRadioButton;
    
    /** The test 2 fail radio button. */
    @FXML
    private RadioButton test2FailRadioButton;
    
    /** The test 3 pass radio button. */
    @FXML
    private RadioButton test3PassRadioButton;
    
    /** The test 3 fail radio button. */
    @FXML
    private RadioButton test3FailRadioButton;
    
    /** The test 4 pass radio button. */
    @FXML
    private RadioButton test4PassRadioButton;
    
    /** The test 4 fail radio button. */
    @FXML
    private RadioButton test4FailRadioButton;
    
    /** The test 5 pass radio button. */
    @FXML
    private RadioButton test5PassRadioButton;
    
    /** The test 5 fail radio button. */
    @FXML
    private RadioButton test5FailRadioButton;

    /* ****************************************
     * ********** Static Objects **************
     * ****************************************/
    /** The Constant MAX_CHARS. */
    private static final int MAX_CHARS = 600;
    
    /* ****************************************
     * ********** Private Objects *************
     * ****************************************/
    /** The my controller. */
    private TesterController myController = new TesterController(this);
    /** The test 1 group. */
    private final ToggleGroup test1Group = new ToggleGroup();
    /** The test 2 group. */
    private final ToggleGroup test2Group = new ToggleGroup();
    /** The test 3 group. */
    private final ToggleGroup test3Group = new ToggleGroup();
    /** The test 4 group. */
    private final ToggleGroup test4Group = new ToggleGroup();
    /** The test 5 group. */
    private final ToggleGroup test5Group = new ToggleGroup();
    /** The current change request. */
    private ChangeRequest currentChangeRequest;
	/** The tester step. */
	private Step testerStep;
	/** The my time extension stage. */
	private Stage myTimeExtensionStage;
	/** The my analysis report stage. */
	private Stage myAnalysisReportStage;
	
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
	/**
     * This method is used to log out from the system.
     *
     * @param event - mouse click on "logout" button
     * 
     * Closes all the open additional stages
     * Perform application user logout
     * Load the project "Login page"
     */
    @FXML
    void LogOut(MouseEvent event) {
    	closeMyStages();
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    /**
     * This method is used to load the home page.
     *
     * @param event - mouse click on "Home" button
     * 
     * Moves the main stage to the menu page
     */
	@FXML
    void loadHomePage(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

	/**
	 * This method is used to load the previous page.
	 *
	 * @param event - mouse click on "Back" button
	 * 
	 * Moves the main stage to the previous page
	 */
    @FXML
    void loadPreviousPage(MouseEvent event) {
    	closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }

    /**
     * This method is used to open additional page for requesting time extension.
     *
     * @param event - mouse click on "Time Extension" button
     * 
     * Another stage is displayed
     */
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
    
    /**
     * This method is used to open additional page for displaying analysis report.
     *
     * @param event - mouse click on "Analysis report" button
     * 
     * Another stage is displayed
     */
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

    /**
     * This method is used to report the test fail.
     *
     * @param event - mouse click on "Submit Report Fail" button
     * 
     * Updates the database accordingly and moves back to the workstation page
     */
    @FXML
    void setReportFail(MouseEvent event) {
		if (failureReportTextArea.getText().equals("")) {
			Toast.makeText(ProjectFX.mainStage, "Please fill the failure report before submitting ", 1500, 500, 500);
		} else {
			myController.closeChangeRequestStep(testerStep.getStepID(), failureReportTextArea.getText());
			myController.advanceChangeRequestStep("EXECUTION_LEADER_SUPERVISOR_APPOINT",
					currentChangeRequest.getChangeRequestID());
			myController.automaticCloseNewTimeExtension(testerStep);
			myController.createNewClosingStep(testerStep.getChangeRequestID());
			closeMyStages();
			ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    	}
    }

    /**
     * This method is used to submit the the test results.
     *
     * @param event - mouse click on "Submit Test Results" button
     * 1. Opens confirmation alert for the submission
     * 2. Checks if all fields are filled properly
     * 3. If all tests passed ---> Updates the database accordingly and moves back to the workstation page
     * else --> Display report fail pane
     */
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
    			FailDetailsPane.setVisible(true);
    			testWorkPane.setVisible(false);
    		}
    		/* All tests passed */
    		else
    		{
    			myController.closeChangeRequestStep(testerStep.getStepID(), "");
				myController.advanceChangeRequestStep("CLOSING_STEP",currentChangeRequest.getChangeRequestID());
				myController.automaticCloseNewTimeExtension(testerStep);
				myController.createNewClosingStep(testerStep.getChangeRequestID());
				closeMyStages();
				ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    		}
    		
    	}
    }

	/* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
    /**
	 * This method is called by the controller after receiving database update results.
	 *
	 * @param affectedRows - number of rows in the database that were affected
	 * 
	 * According to the value will display proper message,
	 * the correct behavior we expect is to have only one affected Row
	 * So the messages will be displayed according to this behavior
	 */
    public void updateTesterPageToDBSuccessfully(int affectedRows) {
    	if(affectedRows == 1) {
    		Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
    	}else {
    		Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
    	}
    }

    /**
     * Init all the FXML objects in the boundary.
     *
     * Used to set all the displays, and to group all the radio buttons into 
     * thier test groups.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ProjectFX.mainStage.setTitle("ICM - Menu\\Work Station\\Testing");
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
		
		/* Change editable */
		timeremainingField.setEditable(false);
		failureReportTextArea.setWrapText(true);
		
		/* Disable buttons */
		analysisreportButton.setDisable(true);
		timeExtensionButton.setDisable(true);
	
		/* Limit characters in the failure report text area */
		failureReportTextArea.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHARS)
			{
				charcterCounterLabel.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHARS));
				return change;
			}
			else
			{
				return null;
			}
		}));
       
		

	}

	/**
	 * Expects to be called on the initialize of the boundary
	 * will receive change request object.
	 *
	 * @param data the data
	 */
	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest) data;
		myController.getCurrentStep(currentChangeRequest.getChangeRequestID());
	}

	/**
	 * Called by the boundary controller,
	 * After retrieving the proper step from the database of the current change request.
	 *
	 * @param recievedStep - Current tester step object
	 * Allows the work on this page to start
	 */
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
	/**
	 * Method to close all the opened stages from this page.
	 */
	private void closeMyStages() {
    	if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
	}
	
	/**
	 * Method to display the time remaining from the estimated end date to current date.
	 *
	 * @param estimatedEndDate - Change request estimated end date
	 * Will display proper text in the time remaining text field
	 */
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
	
	/**
	 * Method to check if all the tests were conducted.
	 *
	 * @param answer1 - Test1 answer
	 * @param answer2 - Test2 answer
	 * @param answer3 - Test3 answer
	 * @param answer4 - Test4 answer
	 * @param answer5 - Test5 answer
	 * 
	 * @return true if all are filled, else false
	 */
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
