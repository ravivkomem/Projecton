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
 * 
 * @author Itay David
 *
 */
public class ExecutionLeaderBoundry implements Initializable, DataInitializable {
	
	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	@FXML
	private Button btnHomePage;
	@FXML
	private Button btnAnalysisReport;
	@FXML
	private Button btnTimeExtension;
	@FXML
	private Button backButton;
	@FXML
	private Button btnLogOut;
	@FXML
	private Text txtWorkingOnChangeRequestNumber;
	@FXML
	private TextArea txtChangeRequestDetails;
	@FXML
    private ImageView loadingGif;
	
	 /* Execution set time fxml objects */
    @FXML
    private GridPane setTimePane;
    @FXML
	private DatePicker executionTimeDatePicker;
    @FXML
	private Button btnSubmitForTimeRequiredForExecution;
    
    /* Execution pending approval fxml objects */
    @FXML
    private GridPane waitApproveTimePane;
    @FXML
    private Label timeRequestedLabel;
    @FXML
	private Button btnRefresh;
    
    /* Execution work fxml objects */
    @FXML
    private GridPane executionWorkPane;
    @FXML
    private TextArea executionSummaryTextArea;
    @FXML
	private Button btnCommitExecution;
    @FXML
    private Label executionSummaryCharactersLabel;
    
    /* Time Remaining Area */
	@FXML
	private Text timeRemainingTxt;
	@FXML
	private TextArea timeRemainingTextArea;

    /* ****************************************
     * ********** Static Objects *************
     * ****************************************/
    private static final String EXECUTION_SET_TIME = "EXECUTION_SET_TIME";
    private static final String EXECUTION_APPROVE_TIME = "EXECUTION_APPROVE_TIME";
    private static final String EXECUTION_WORK = "EXECUTION_WORK";
    private static final int MAX_CHARS = 100;
    
	/* ****************************************
     * ********** Private Objects *************
     * ****************************************/
	private ExecutionLeaderController myController = new ExecutionLeaderController(this);
	private ChangeRequest myChangeRequest;
	private Stage myTimeExtensionStage = null;
	private Stage myAnalysisReportStage = null;
	private Step executionStep;
	
	/* ****************************************
     * ********** Init Methods ** *************
     * ****************************************/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
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
	 * loading the project home page
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
	 * 
	 * @param event
	 * This method handle the click on analysis report in menu
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
	 * 
	 * @param event
	 * This method handle the click time extension in menu
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
	 * 
	 * @param event
	 * This method handle the click on back in menu
	 */
	@FXML
	void loadPreviousPage(MouseEvent event) // back to the work station page
	{
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}
	
	/**
	 * @param event
	 * This method handle the click on log out
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
	 * @param event
	 * This method send the time required for execution to the supervisor
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
				myController.updateChnageRequestCurrentStep(EXECUTION_APPROVE_TIME,
						myChangeRequest.getHandlerUserName(), myChangeRequest.getChangeRequestID());
			}
		}
		
	}
	
	/**
	 * This methods refreshed the execution page
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
	 * 
	 * @param event
	 * This method update DB after click finish work
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
					myController.closeExecutionStep(myChangeRequest.getChangeRequestID(),executionSummaryTextArea.getText());
					myController.advanceChangeRequestToTesterStep(
							myChangeRequest.getChangeRequestID());
					ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
				}
			}
		}

	}
	
	
	/* ****************************************
     * ********** Public Methods **************
     * ****************************************/
	/**
	 * Will be called by the controller after he select the change request again
	 * @param changeRequest - updated change request
	 */
	public void updateChangeRequest(ChangeRequest changeRequest) {
		myChangeRequest = changeRequest;
		recieveExecutionStep(executionStep); // To load page selection again
	}
	
	/**
	 * This method is in order to recieve the execution step from the controller
	 * @param executionStep - the current execution step as recieved
	 * Starts the page work progress
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
	 * this method is used to recieve the estimated end date updated status from the controller
	 * @param affectedRows - number of database rows affected
	 * the correct behaviour should be one row affected
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
	 * "EXECUTION_SET_TIME" current step
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
	 * "EXECUTION_APPROVE_TIME" current step
	 */
	private void loadExecutionApproveTimeDisplay()
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
	 * "EXECUTION_WORK" current step
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
	 * @param estimatedEndDate
	 * This method update the time left for execution
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
