package boundries;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.ExecutionLeaderController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.ExecutionAproves;
import entities.Step;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private Button btnRefresh;
	@FXML
	private Text timeRemainingTxt;
	@FXML
	private Text delayTimeTxt;
	@FXML
	private Text txtRefresh;
	@FXML
	private Text txtBuildChangeRequestDetails;
	@FXML
	private Text txtBuildEnterTimeRequiredForExecution;
	@FXML
	private Text txtWaitingForTomeApprovalPopUp;
	@FXML
	private Button backButton;
	@FXML
	private Text txtWorkingOnChangeRequestNumber;
	@FXML
	private Button btnCommitExcution;
	@FXML
	private TextArea timeRemainingTextArea;
	@FXML
	private DatePicker txtTimeForExecution;
	@FXML
	private Button btnSubmitForTimeRequiredForExecution;
	@FXML
	private Button btnLogOut;
	@FXML
	private Button btnCommitExecution;
	@FXML
	private TextArea txtChangeRequestDetails;
	@FXML
	private Button btnHomePage;
	@FXML
	private Button btnAnalysisReport;
	@FXML
	private Button btnTimeExtension;
	@FXML
	private Text txtDetailsWorkedOn;
    @FXML
    private TextArea txtFieldForDetailsWorkedOn;
    @FXML
    private Label detailsLabel;

	/* ****************************************
     * ********** Private Objects *************
     * ****************************************/
	private ExecutionLeaderController myController = new ExecutionLeaderController(this);
	private ChangeRequest myChangerequest;
	private int flag;
	private Stage myTimeExtensionStage = null;
	private Stage myAnalysisReportStage = null;
	private Step executionStep;
	
	/* ****************************************
     * ********** Init Methods ** *************
     * ****************************************/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		/* Change visibilities */
		txtWaitingForTomeApprovalPopUp.setVisible(false);
		btnCommitExecution.setVisible(false);
		btnRefresh.setVisible(false);
		timeRemainingTextArea.setVisible(false);
		delayTimeTxt.setVisible(false);
		timeRemainingTxt.setVisible(false);
		txtFieldForDetailsWorkedOn.setVisible(false);
		txtRefresh.setVisible(false);
		txtDetailsWorkedOn.setVisible(false);
		txtWorkingOnChangeRequestNumber.setVisible(true);
		detailsLabel.setVisible(false);
		txtFieldForDetailsWorkedOn.setWrapText(true);
		
		/* Change editable */
		txtChangeRequestDetails.setEditable(false);
		txtTimeForExecution.setEditable(false);
		txtChangeRequestDetails.setWrapText(true);
		flag = 0;
		
		txtFieldForDetailsWorkedOn.setTextFormatter(new TextFormatter<String>(change -> {
			int changeLength = change.getControlNewText().length();
			if (changeLength <= 100){
				detailsLabel.setText(Integer.toString(changeLength) + "/ " + 100);
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
			myChangerequest = (ChangeRequest) data;
			txtWorkingOnChangeRequestNumber
					.setText("Working On Change Request No. " + myChangerequest.getChangeRequestID());
			txtChangeRequestDetails.setText(myChangerequest.getDesiredChangeDescription());
			myController.getExecutionStepByChangeRequestId(myChangerequest.getChangeRequestID());
		}
		catch (Exception e)
		{
			System.out.println("Error loading the exeution page");
			this.loadPreviousPage(null);
		}
	}
	
	public void setflag() {
		flag = 1;
	}
	/* ****************************************
     * ********** FXML Methods ** *************
     * ****************************************/
	/**
	 * @param event
	 * This method send the time required for execution to the supervisor
	 */
	@FXML
	public void SendTimeRequiredForExecutionToSupervisor(MouseEvent event) // submit execution time
	{
		if (txtTimeForExecution.getValue() == null) 
			Toast.makeText(ProjectFX.mainStage, "Please add Time Execution first", 1500, 500, 500);
		else
		{
		Date date = Date.valueOf(txtTimeForExecution.getValue());
		if (TimeManager.getDaysBetween(date, TimeManager.getCurrentDate()) > 0) {
			Toast.makeText(ProjectFX.mainStage, "Invalid date, please select a date again", 1500, 500, 500);
		} else {
			long count = 0;

			if (txtTimeForExecution.getValue() == null) {
				Toast.makeText(ProjectFX.mainStage, "Please add Time Execution first", 1500, 500, 500);
			} else if (txtTimeForExecution.getValue() != null) {
				count = TimeManager.getDaysBetween(executionStep.getStartDate(),
						Date.valueOf(txtTimeForExecution.getValue()));
			}
			if (count < 0) {
				Toast.makeText(ProjectFX.mainStage, "Please choose other day", 1500, 500, 500);
			} else if (count > 0) {
				txtWaitingForTomeApprovalPopUp.setVisible(true);
				txtRefresh.setVisible(true);
				Date estimatedDateChoosen = Date.valueOf(txtTimeForExecution.getValue());
				myController.insertNewEstimatedDateToExecutionStepAndChangeRequestIDStep(estimatedDateChoosen,
						myChangerequest.getChangeRequestID());
				myController.updateNewChangeRequestIdStepToExecutionApprovedTime(myChangerequest.getChangeRequestID());
				btnRefresh.setVisible(true);
				txtBuildEnterTimeRequiredForExecution.setVisible(false);
				txtTimeForExecution.setVisible(false);
				btnSubmitForTimeRequiredForExecution.setVisible(false);

			}
		}
		}
	}
	/**
	 * 
	 * @param affectedRows
	 * This method make toast after upload time for execution
	 */
	@FXML
	public void ExecutionAprovedtInsertToDBSuccessfully(int affectedRows) {
		if (affectedRows == 1) {
			Toast.makeText(ProjectFX.mainStage, "The ExecutionTime uploaded successfully", 1500, 500, 500);
		} else {
			Toast.makeText(ProjectFX.mainStage, "The  ExecutionTime  upload failed", 1500, 500, 500);
		}

	}
	/**
	 * 
	 * @param event
	 * This method update DB after click finish work
	 */
	@FXML
	public void UpdateChangeRequestStepAndExecutionLeaderStatus(MouseEvent event) // when execution commit working
	{
		if(txtFieldForDetailsWorkedOn.getText().equals(""))
		{
			Toast.makeText(ProjectFX.mainStage, "First enter details you worked on", 1500, 500, 500);
		}
		else {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.getButtonTypes().remove(ButtonType.OK);
		alert.getButtonTypes().add(ButtonType.CANCEL);
		alert.getButtonTypes().add(ButtonType.YES);
		alert.setTitle("COMMIT PRESSED");
		alert.setContentText(String.format("Are you sure you want to commit?"));
		Optional<ButtonType> res = alert.showAndWait();

		if (res.isPresent()) {
			if (res.get().equals(ButtonType.CANCEL)) {
				event.consume();
			} else {
				myController.UpdateExecutionLeaderDateAndStatus(myChangerequest.getChangeRequestID(),txtFieldForDetailsWorkedOn.getText());
				myController.UpdateCurrentStepOfChangeRequrstFromExecutionWorkToTesterCommitteeDirectorAppoint(
						myChangerequest.getChangeRequestID());
				ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
			}
		}
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
	 * 
	 * @param event
	 * This method handle the click on log out
	 */
	@FXML
	void LogOutFromExecutionLeaderPage(MouseEvent event) // logout from execution page
	{
		ProjectFX.pagingController.userLogout();
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}
	/**
	 * 
	 * @param event
	 * This method handle the click time extension in menu
	 */
	@FXML
	public void OpenTimeExtensionPageFromExecutionLeaderPage(MouseEvent event) // opet time extension
	{
		if (!myChangerequest.getCurrentStep().equals("EXECUTION_WORK")) {
			Toast.makeText(ProjectFX.mainStage, "Supervisor did not approve yet the time for execution", 1500, 500,
					500);
		} else {
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
	}
	/**
	 * 
	 * @param event
	 * This method handle the click on home page in menu
	 */
	@FXML
	public void ReturnToHomePageFromExecutionLeaderPage(MouseEvent event) // return home page
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
	public void ShowAnalysisReportFromExecutionLeaderPage(MouseEvent event) // show anaylisis report
	{
		if (myAnalysisReportStage == null) {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), myChangerequest);
		} else if (myAnalysisReportStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Analysis Report Window is already open", 1500, 500, 500);
		} else {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), myChangerequest);
		}
	}
	/**
	 * 
	 * @param event
	 * This method handle the click on refresh to check if supervisor approve estimated time 
	 */
	@FXML
	public void GetAgainTheChangeRequestToSeeStatus(MouseEvent event) // Refresh button
	{

		// myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());

		if (flag == 0)
			myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());
		else
			myController.SelectEstimatedDateMinusStartDate(myChangerequest.getChangeRequestID());
	}
	/**
	 * This method update page after supervisor approve time
	 */
	public void ShowCommitButton() {

		Toast.makeText(ProjectFX.mainStage, "Supervisor approved your estimated time", 1500, 500, 500);
		btnRefresh.setVisible(false);
		txtFieldForDetailsWorkedOn.setVisible(true);
		detailsLabel.setVisible(true);
		txtDetailsWorkedOn.setVisible(true);
		txtRefresh.setVisible(false);
		timeRemainingTextArea.setEditable(false);
		txtWaitingForTomeApprovalPopUp.setVisible(false);
		btnCommitExecution.setVisible(true);
		myController.SelectEstimatedDateMinusStartDate(myChangerequest.getChangeRequestID());
	}
	
	/**
	 * 
	 * @param estimatedEndDate
	 * This method update the time left for execution
	 */

	public void ShowEstimatedDateMinusStartDate(Date estimatedEndDate) {
		Date todayDate = TimeManager.getCurrentDate();
		long daysBetween;
		if (estimatedEndDate.before(todayDate)) {
			delayTimeTxt.setVisible(true);
			timeRemainingTextArea.setVisible(true);
			daysBetween = ChronoUnit.DAYS.between(estimatedEndDate.toLocalDate(), todayDate.toLocalDate());
			timeRemainingTextArea.setText("" + (daysBetween - 1) + " Days");
		} else {
			timeRemainingTxt.setVisible(true);
			timeRemainingTextArea.setVisible(true);
			daysBetween = ChronoUnit.DAYS.between(todayDate.toLocalDate(), estimatedEndDate.toLocalDate());
			timeRemainingTextArea.setText("" + (daysBetween + 1) + " Days");
		}

	}
	/**
	 * 
	 * @param affectedrows
	 * Toast
	 */
	public void ShowFinishToast(int affectedrows) {
		// TODO Auto-generated method stub
		if (affectedrows == 1)
			Toast.makeText(ProjectFX.mainStage, "Execution Step is finished", 1500, 500, 500);

	}
	/**
	 * 
	 * @param affectedrows
	 * Toast
	 */
	public void SupervisorDidNotAproveYet() {

		Toast.makeText(ProjectFX.mainStage, "Supervisor did not approve yet", 1500, 500, 500);
		txtWaitingForTomeApprovalPopUp.setVisible(true);
	}
	
	/**
	 * This method is in order to recieve the execution step from the controller
	 * @param executionStep - the current execution step as recieved
	 * Starts the page work progress
	 */
	public void recieveExecutionStep(Step executionStep) {
		this.executionStep = executionStep;
		if (!(executionStep.getEstimatedEndDate() == null)) {
			btnRefresh.setVisible(true);
			txtBuildEnterTimeRequiredForExecution.setVisible(false);
			txtTimeForExecution.setVisible(false);
			btnSubmitForTimeRequiredForExecution.setVisible(false);
			myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());
		}
	}
	/**
	 * 
	 * 
	 * Toast
	 */
	public void handleDataBaseSelectionError() {
		Toast.makeText(ProjectFX.mainStage, "Error - Could not load the page", 1500, 500, 500);
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}
		/**
		 * 
		 * 
		 * Toast
		 */
	public void chooseAgainTimeForExecution()
	{
		Toast.makeText(ProjectFX.mainStage, "Supervisor did not approve your estimated time, please choose another date", 1500, 500, 500);
		txtTimeForExecution.setVisible(true);
		txtBuildEnterTimeRequiredForExecution.setVisible(true);
		btnSubmitForTimeRequiredForExecution.setVisible(true);
		txtWaitingForTomeApprovalPopUp.setVisible(false);
		txtTimeForExecution.setValue(null);
		txtRefresh.setVisible(false);
		btnRefresh.setVisible(false);
		
		
	}

}
