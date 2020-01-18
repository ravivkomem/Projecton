package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import assets.ProjectPages;
import assets.Toast;
import controllers.ExecutionLeaderController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.Step;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * The Class ExecutionLeaderBoundry.
 *
 * @author Itay David
 */
public class ExecutionLeaderBoundry implements Initializable, DataInitializable {
	
	/** The btn home page. */
	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	@FXML
	private Button btnHomePage;
	
	/** The btn analysis report. */
	@FXML
	private Button btnAnalysisReport;
	
	/** The btn time extension. */
	@FXML
	private Button btnTimeExtension;
	
	/** The back button. */
	@FXML
	private Button backButton;
	
	/** The btn log out. */
	@FXML
	private Button btnLogOut;
	
	/** The txt working on change request number. */
	@FXML
	private Text txtWorkingOnChangeRequestNumber;
	
	/** The txt change request details. */
	@FXML
	private TextArea txtChangeRequestDetails;
	
	/** The loading gif. */
	@FXML
    private ImageView loadingGif;
	
	 /** The set time pane. */
 	/* Execution set time fxml objects */
    @FXML
    private GridPane setTimePane;
    
    /** The execution time date picker. */
    @FXML
	private DatePicker executionTimeDatePicker;
    
    /** The btn submit for time required for execution. */
    @FXML
	private Button btnSubmitForTimeRequiredForExecution;
    
    /** The wait approve time pane. */
    /* Execution pending approval fxml objects */
    @FXML
    private GridPane waitApproveTimePane;
    
    /** The time requested label. */
    @FXML
    private Label timeRequestedLabel;
    
    /** The btn refresh. */
    @FXML
	private Button btnRefresh;
    
    /** The execution work pane. */
    /* Execution work fxml objects */
    @FXML
    private GridPane executionWorkPane;
    
    /** The execution summary text area. */
    @FXML
    private TextArea executionSummaryTextArea;
    
    /** The btn commit execution. */
    @FXML
	private Button btnCommitExecution;
    
    /** The execution summary characters label. */
    @FXML
    private Label executionSummaryCharactersLabel;
    
    /** The time remaining txt. */
    /* Time Remaining Area */
	@FXML
	private Text timeRemainingTxt;
	
	/** The time remaining text area. */
	@FXML
	private TextArea timeRemainingTextArea;

    /* ****************************************
     * ********** Static Objects *************
     * ****************************************/
    /** The Constant EXECUTION_SET_TIME. */
    private static final String EXECUTION_SET_TIME = "EXECUTION_SET_TIME";
    
    /** The Constant EXECUTION_APPROVE_TIME. */
    private static final String EXECUTION_APPROVE_TIME = "EXECUTION_APPROVE_TIME";
    
    /** The Constant EXECUTION_WORK. */
    private static final String EXECUTION_WORK = "EXECUTION_WORK";
    
    /** The Constant MAX_CHARS. */
    private static final int MAX_CHARS = 100;
    
	/* ****************************************
     * ********** Private Objects *************
     * ****************************************/
	/** The my controller. */
	private ExecutionLeaderController myController = new ExecutionLeaderController(this);
	
	/** The my change request. */
	private ChangeRequest myChangeRequest;
	
	/** The my time extension stage. */
	private Stage myTimeExtensionStage = null;
	
	/** The my analysis report stage. */
	private Stage myAnalysisReportStage = null;
	
	/** The execution step. */
	private Step executionStep;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	/* ****************************************
     * ********** Init Methods ** *************
     * ****************************************/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ProjectFX.mainStage.setTitle("ICM - Menu\\Work Station\\Execution");
		timeRemainingTextArea.setDisable(true);
		timeRemainingTextArea.setText("Work pending approval");
		timeRemainingTxt.setText("Time Remaining");
		timeRemainingTextArea.setWrapText(true);
		
		btnTimeExtension.setDisable(true);
		
		/* Change visibilities */
		executionWorkPane.setVisible(false);
		setTimePane.setVisible(false);
		waitApproveTimePane.setVisible(false);
		loadingGif.setVisible(false);
		

		/* Change editable */
		executionSummaryTextArea.setWrapText(true);
		txtChangeRequestDetails.setWrapText(true);
		txtChangeRequestDetails.setEditable(false);
		executionTimeDatePicker.setEditable(false);
		
		executionSummaryCharactersLabel.setText("0/" + MAX_CHARS);
		executionSummaryTextArea.setTextFormatter(new TextFormatter<String>(change -> {
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHARS){
				executionSummaryCharactersLabel.setText(Integer.toString(changeLength) + "/" + MAX_CHARS);
				return change;
			}
			else{
				return null;
			}
		}));
	}
	
	/* (non-Javadoc)
	 * @see boundries.DataInitializable#initData(java.lang.Object)
	 */
	@Override
	public void initData(Object data) {
		try
		{
			myChangeRequest = (ChangeRequest) data;
			txtWorkingOnChangeRequestNumber
					.setText("Working On Change Request No. " + myChangeRequest.getChangeRequestID());
			txtChangeRequestDetails.setText(myChangeRequest.getDesiredChangeDescription());
			myController.getExecutionStepByChangeRequestId(myChangeRequest.getChangeRequestID());
		}
		catch (Exception e)
		{
			System.out.println("Error loading the exeution page");
			this.loadPreviousPage(null);
		}
	}
	
	/* ****************************************
     * ********** FXML Methods ** *************
     * ****************************************/
	/**
	 * loading the project home page when clicking on the home page button.
	 *
	 * @param event - mouse click on the "Home" button
	 */
	@FXML
	public void loadHomePage(MouseEvent event) // return home page
	{
		// ((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary
		// window
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	}
	
	/**
	 * Display analysis report stage
	 * This method handle the click on analysis report in menu and open the analysis report of the chosen request
	 * By opening additional stage of analysis report
	 *
	 * @param event - Mouse click on "Analysis Report" button
	 */
	@FXML
	public void displayAnalysisReport(MouseEvent event) // show anaylisis report
	{
		if (myAnalysisReportStage == null) {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), myChangeRequest);
		} else if (myAnalysisReportStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Analysis Report Window is already open", 1500, 500, 500);
		} else {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), myChangeRequest);
		}
	}
	
	/**
	 * Display time extension stage
	 * This method handle the click time extension in menu when execution leader needs more time to execute the request
	 * @param event - Mouse click on the "Time Extension" button
	 */
	@FXML
	public void displayTimeExtension(MouseEvent event) // opet time extension
	{
		if (myTimeExtensionStage == null) {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(), executionStep);
		} else if (myTimeExtensionStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
		} else {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(), executionStep);
		}
	}
	
	/**
	 * Load previous page.
	 * This method handle the click on back in menu and return to the last page
	 * 
	 * @param event - Mouse click on "Back" button 
	 */
	@FXML
	void loadPreviousPage(MouseEvent event) // back to the work station page
	{
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}
	
	/**
	 * Log out user.
	 * This method handle the click on log out
	 * Will perform application logout and also close all the additional stages
	 * And set the page to the login page
	 * 
	 * @param event - Mouse click on the  "LOGOUT" button 
	 */
	@FXML
	void logOutUser(MouseEvent event) // logout from execution page
	{
		ProjectFX.pagingController.userLogout();
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}
	
	/**
	 * Submit execution time.
	 * This method send the time required for execution to the supervisor for approve or deny the time
	 * 1. Checks if the date entered is valid -> if not display proper error message via Toast
	 * 2. Calls the controller methods to update the step estimated end date
	 * and also to update the change request current step
	 * 3. Sets the page displays in order to display to the user that the request is submitted and
	 * now waiting for the supervisor approval
	 * 
	 * @param event - Mouse click on the "Submit" button 
	 */
	@FXML
	public void submitExecutionTime(MouseEvent event) // submit execution time
	{
		if (executionTimeDatePicker.getValue() == null)
		{
			Toast.makeText(ProjectFX.mainStage, "ERROR - please fill required execution date", 1500, 500, 500);
		}
		else
		{
			Date selectedDate = Date.valueOf(executionTimeDatePicker.getValue());
			if (TimeManager.getDaysBetween(selectedDate, TimeManager.getCurrentDate()) > 0) {
				Toast.makeText(ProjectFX.mainStage, "ERROR - The required date already passed\n"
						+ "Please select again", 1500, 500, 500);
			}
			else
			{
				executionStep.setEstimatedEndDate(selectedDate);
				myChangeRequest.setCurrentStep(EXECUTION_APPROVE_TIME);
				myController.updateExecutionStepEstimatedEndDate(selectedDate, executionStep.getStepID());
				myController.updateChangeRequestCurrentStep(EXECUTION_APPROVE_TIME,
						myChangeRequest.getHandlerUserName(), myChangeRequest.getChangeRequestID());
				executionTimeDatePicker.setValue(null);
			}
		}
		
	}
	
	/**
	 * This methods refresh the execution page.
	 * It will get the change request information again by using the controller
	 * And incase the current step is different, the page will be displayed differently
	 * If it is "EXECUTION_SET_TIME" it will display the time submission again
	 * If it is "EXECUTION_WORK" it will display the execution work display
	 *
	 * @param event - mouse click on "Refresh button"
	 * Page refreshed and display may change accordingly
	 */
	@FXML
	public void refreshPage(MouseEvent event) // Refresh button
	{
		loadingGif.setVisible(true);
		myController.getUpdatedChangeRequest(myChangeRequest.getChangeRequestID());
	}
	
	
	/**
	 * Finish execution work.
	 * 
	 * 1. Checks if the execution summary was submitted, if not an error alert will be displayed
	 * 2. Displays information alert for the execution leader to verify that he is indeed finish and willing 
	 * to commit his work.
	 * 3. If the execution leader decided to commit the following will happen:
	 * This method calls the controller to update the DB 
	 * with the execution summary after click finish work
	 * And also advance the change request current step and to close the execution step.
	 * 
	 * @param event - Mouse click on the "Submit" button  
	 * 
	 */
	@FXML
	public void finishExecutionWork(MouseEvent event) // when execution commit working
	{
		Alert alert = new Alert(AlertType.NONE);
		if(executionSummaryTextArea.getText().equals(""))
		{
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Missing information");
			alert.setContentText("Please fill execution summary");
			alert.showAndWait();
		}
		else 
		{
			alert.setAlertType(AlertType.INFORMATION);
			alert.getButtonTypes().remove(ButtonType.OK);
			alert.getButtonTypes().add(ButtonType.CANCEL);
			alert.getButtonTypes().add(ButtonType.YES);
			alert.setTitle("Execution work finished");
			alert.setContentText(String.format("Are you sure you want to commit?"));
			Optional<ButtonType> res = alert.showAndWait();
		
			if (res.isPresent()) 
			{
				if (res.get().equals(ButtonType.CANCEL)) {
					event.consume();
				} 
				else 
				{
					myController.closeExecutionStep(executionStep.getStepID() ,executionSummaryTextArea.getText());
					myController.advanceChangeRequestToTesterStep(
							myChangeRequest.getChangeRequestID());
					myController.updateTimeExtensionDB(executionStep.getStepID(), "Execution");
					ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
				}
			}
		}

	}
	
	
	/* ****************************************
     * ********** Public Methods **************
     * ****************************************/
	/**
	 * Will be called by the controller after he select the change request again.
	 * The new change request is used to decide if the page is in need of a refresh
	 *
	 * @param changeRequest - updated change request
	 */
	public void updateChangeRequest(ChangeRequest changeRequest) {
		myChangeRequest = changeRequest;
		recieveExecutionStep(executionStep); // To load page selection again
	}
	
	/**
	 * This method is in order to receive the execution step from the controller.
	 *
	 * @param executionStep - the current execution step as received
	 * 
	 * Checks if the execution step is valid, if not display proper error message and goes back 
	 * to the previous page
	 * Set the page display according to the change request current status,
	 * Either display of execution set time, wait for supervisor approval or the execution work
	 */
	public void recieveExecutionStep(Step executionStep) {
		loadingGif.setVisible(false);
		if (executionStep == null)
		{
			Toast.makeText(ProjectFX.mainStage, "ERROR - Database error, loading previous page", 1500, 500, 500);
			loadPreviousPage(null);
		}
		else
		{
			this.executionStep = executionStep;
			/* Check the work status */
			switch (myChangeRequest.getCurrentStep()) {
				case EXECUTION_SET_TIME:
					loadExecutionSetTimeDisplay();
					break;
				case EXECUTION_APPROVE_TIME:
					loadExecutionApproveTimeDisplay();
					break;
				case EXECUTION_WORK:
					loadExecutionWorkDisplay();	
					break;
			}
		}
	}
	
	/**
	 * this method is used to receive the estimated end date updated status from the controller.
	 *
	 * @param affectedRows - number of database rows affected
	 * 
	 * the correct behavior should be one row affected
	 * Therefore it will display proper Toast message incase the affected rows are indeed 1 or something diffrent
	 */
	public void recieveEstimatedEndDateUpdateStatus(int affectedRows)
	{
		if (affectedRows == 1)
		{
			Toast.makeText(ProjectFX.mainStage, "Execution work time updated, waiting for superviosr approval",
					1500, 500, 500);
			loadExecutionApproveTimeDisplay();
		}
		else
		{
			Toast.makeText(ProjectFX.mainStage, "Error with database - please submit execution time again",
					1500, 500, 500);
		}
	}
	
	
	/* ****************************************
     * ********* Private Methods **************
     * ****************************************/
	/**
	 * method in order to set the page display for change requests in the 
	 * "EXECUTION_SET_TIME" current step.
	 * 
	 * If this method was called after a refresh action it will display proper
	 * Toast message for the user to know that the supervisor has denied his previous time request
	 * Otherwise it will simply load the decide time displays
	 */
	private void loadExecutionSetTimeDisplay()
	{
		if (waitApproveTimePane.isVisible() == true)
		{
			Toast.makeText(ProjectFX.mainStage, "Supervisor denied your execution time,\n"
					+ "Please fill again", 1500, 500, 500);
		}
		setTimePane.setVisible(true);
		
		waitApproveTimePane.setVisible(false);
		executionWorkPane.setVisible(false);
		
		btnTimeExtension.setDisable(true);
		
		timeRemainingTextArea.setDisable(true);
		timeRemainingTextArea.setText("Work pending approval");
		timeRemainingTxt.setText("Time Remaining");
	}
	
	/**
	 * method in order to set the page display for change requests in the 
	 * "EXECUTION_APPROVE_TIME" current step.
	 */
	public void loadExecutionApproveTimeDisplay()
	{
		waitApproveTimePane.setVisible(true);
		timeRequestedLabel.setText("Time requestsed: " + executionStep.getEstimatedEndDate());
		
		setTimePane.setVisible(false);
		executionWorkPane.setVisible(false);
		
		btnTimeExtension.setDisable(true);
		
		timeRemainingTextArea.setDisable(true);
		timeRemainingTextArea.setText("Work pending approval");
		timeRemainingTxt.setText("Time Remaining");
	}
	
	/**
	 * method in order to set the page display for change requests in the 
	 * "EXECUTION_WORK" current step.
	 */
	private void loadExecutionWorkDisplay()
	{
		executionWorkPane.setVisible(true);
		
		setTimePane.setVisible(false);
		waitApproveTimePane.setVisible(false);
		
		btnTimeExtension.setDisable(false);
		
		timeRemainingTextArea.setDisable(false);
		displayTimeRemaining(executionStep.getEstimatedEndDate());
	}
	
	/**
	 * Display time remaining in the time remaining text area
	 * Either display the time delay, time remaining, or last day
	 *
	 * @param estimatedEndDate - The step estimated end date
	 */
	private void displayTimeRemaining(Date estimatedEndDate) {
		long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), estimatedEndDate);
		if(daysBetween < 0) {
			timeRemainingTxt.setText("Time Delay");
			timeRemainingTextArea.setText(Math.abs(daysBetween) + " Days");
		}
		else if(daysBetween == 0) {
			timeRemainingTxt.setText("Time Remaining");
			timeRemainingTextArea.setText("Last Day");
		}
		else {
			timeRemainingTxt.setText("Time Remaining");
			timeRemainingTextArea.setText(daysBetween + " Days");
		}
	}

}
