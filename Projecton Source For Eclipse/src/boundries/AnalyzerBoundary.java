package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.AnalyzerController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnalyzerBoundary implements DataInitializable {

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	@FXML
	private AnchorPane datePane;
	@FXML
	private Button dateButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Button backButton;
	@FXML
	private Button homepageButton;
	@FXML
	private Button requestdetailsButton;
	@FXML
	private Button createreportButton;
	@FXML
	private Button timeextensionButton;
	@FXML
	private TableView<ChangeRequest> tableView;
	@FXML
	private TableColumn<ChangeRequest, Integer> requestIdColumn;
	@FXML
	private TableColumn<ChangeRequest, String> stepColumn;
	@FXML
	private TableColumn<ChangeRequest, String> descriptionColumn;
	@FXML
	private TableColumn<ChangeRequest, String> subsystemColumn;
	@FXML
	private AnchorPane createReportPane;
	@FXML
	private TextArea descriptiontextArea;
	@FXML
	private TextArea advantagestextArea;
	@FXML
	private TextArea constraintstextArea;
	@FXML
    private TextArea headertextArea;
	@FXML
	private TextArea durationtextArea;
    @FXML
    private Text timeDisplayText;
    @FXML
    private TextArea timeRemainingArea;
	@FXML
	private DatePicker timedurationPicker;
	@FXML
	private ComboBox<String> subsystemComboBox;
	@FXML
	private Button submitButton;
	@FXML
	private Text pageHeaderText;
	@FXML
	private Text notificationText;
    @FXML
    private Label headerCharcterCounterLabel;
    @FXML
    private Label descriptionCharcterCounterLabel;
    @FXML
    private Label advantagesCharcterCounterLabel;
    @FXML
    private Label constraintsCharcterCounterLabel;
    @FXML
    private Label durationCharcterCounterLabel;

	
	/* ****************************************
     * ********** Private Variables ***********
     * ***************************************/
	private AnalyzerController myController = new AnalyzerController(this);
	public static final String ANALYSIS_SET_TIME = "ANALYSIS_SET_TIME";
	public static final String ANALYSIS_APPROVE_TIME = "ANALYSIS_APPROVE_TIME";
	public static final String ANALYSIS_WORK = "ANALYSIS_WORK";
	public static final int MAX_CHARS = 100;
	private ChangeRequest currentChangeRequest;
	private Step analyzerStep;
	private Stage myTimeExtensionStage;
	private ObservableList<ChangeRequest> changeRequestList = FXCollections.observableArrayList();
	
	/* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
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
	void LogOut(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}
	
	@FXML
	void loadRequestDetails(MouseEvent event) {
		//closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath());
	}

	@FXML
	void loadTimeExtensionPage(MouseEvent event) {
		if (myTimeExtensionStage == null) {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(), analyzerStep);
		} else if (myTimeExtensionStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
		} else {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(), analyzerStep);
		}
	}

	@FXML
	void loadCreateReport(MouseEvent event) {

	}
	private void displayTimeRemaining(Date estimatedEndDate) {
		long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), estimatedEndDate);
		if(daysBetween < 0) {
			timeDisplayText.setText("Time Delay");
			timeRemainingArea.setText(Math.abs(daysBetween) + " Days");
		}
		else if(daysBetween == 0) {
			timeDisplayText.setText("Time Remaining");
			timeRemainingArea.setText("Last Day");
		}
		else {
			timeDisplayText.setText("Time Remaining");
			timeRemainingArea.setText(daysBetween + " Days");
		}
	}

	@FXML
	void submit(MouseEvent event) {
		/*
		 * switch(subsystemComboBox.getSelectionModel().getSelectedItem()) { case
		 * MOODLE_SYSTEM:
		 * 
		 * break; case EMPLOYEE_INFORMATION_STATION:
		 * 
		 * break; case LIBRARY_SYSTEM:
		 * 
		 * break; case LABORATORY:
		 * 
		 * break; case CLASS_ROOMS_WITH_COMPUTERS:
		 * 
		 * break; }
		 */
		// "UPDATE icm.analysis_step SET EndDate = ?,Status =
		// ?,AnalysisReportDescription = ?,AnalysisReportAdvantages =
		// ?,AnalysisReportConstraints = ? WHERE ChangeRequestID = ?";
		myController.updateChangeRequestCurrentStepAndHandlerName(currentChangeRequest, "COMMITTEE_WORK", "-");
		myController.updateAnalysisStepClose(currentChangeRequest, TimeManager.getCurrentDate(), "Close",headertextArea.getText(),
				descriptiontextArea.getText(), advantagestextArea.getText(),durationtextArea.getText(), constraintstextArea.getText());
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}

	@FXML
	void setDate(MouseEvent event) {
		if (timedurationPicker.getValue() == null) {
			Toast.makeText(ProjectFX.mainStage, "Please enter date", 1500, 500, 500);
		} 
		else 
		{
			Date selectedDate = Date.valueOf(timedurationPicker.getValue());

			Date todayDate = TimeManager.getCurrentDate();
			long daysBetween = TimeManager.getDaysBetween(todayDate, selectedDate);
			if (daysBetween <= 0) {
				Toast.makeText(ProjectFX.mainStage, "You can not select a date before today", 1500, 500, 500);
			} 
			else 
			{
				analyzerStep.setEstimatedEndDate(selectedDate);
				myController.updateAnalysisStepEstimatedEndDate(analyzerStep.getStepID(), selectedDate);
				myController.updateChangeRequestCurrentStep(ANALYSIS_APPROVE_TIME, analyzerStep.getHandlerUserName(), analyzerStep.getChangeRequestID());
				createReportPane.setVisible(false);
				datePane.setVisible(false);
				notificationText.setVisible(true);
			}
		}
	}

	/* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
	public void updateTesterPageToDBSuccessfully(int affectedRows) {
		if (affectedRows == 1) {
			Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
		} else 
		{
			Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
		}
	}
	 @FXML
	    void updateAdvantagesCharcterCounter(KeyEvent event) {
		 advantagesCharcterCounterLabel.setText(advantagestextArea.getText().length() + "/ " + MAX_CHARS);

	    }

	    @FXML
	    void updateConstraintsCharcterCounter(KeyEvent event) {
	    	constraintsCharcterCounterLabel.setText(constraintstextArea.getText().length() + "/" + MAX_CHARS);
	    }

	    @FXML
	    void updateDescriptionCharcterCounter(KeyEvent event) {
	    	descriptionCharcterCounterLabel.setText(descriptiontextArea.getText().length() + "/" + MAX_CHARS);

	    }

	    @FXML
	    void updateDurationCharcterCounter(KeyEvent event) {
	    	durationCharcterCounterLabel.setText(durationtextArea.getText().length() + "/" + MAX_CHARS);

	    }

	    @FXML
	    void updateHeaderCharcterCounter(KeyEvent event) {
	    	headerCharcterCounterLabel.setText(headertextArea.getText().length() + "/" + MAX_CHARS);

	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/* Init table view */
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		stepColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("actualStep"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		
		/* Change visibilities */
		tableView.setVisible(false);
		createReportPane.setVisible(false);
		datePane.setVisible(false);
		notificationText.setVisible(false);
		
		/* Change Texts */
		pageHeaderText.setText("Loading change request information");
		timeRemainingArea.setEditable(false);
		
		/* Disable buttons */
		requestdetailsButton.setDisable(true);
		timeextensionButton.setDisable(true);
	}

	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest) data;
		myController.getCurrentStep(currentChangeRequest.getChangeRequestID());
		
		
		// load table and header
		// switch case on currentChangeRequest.getCurrentStep
		// if ANALYSIS_SET_TIME - display datepicker
		// if ANALYSIS_APPROVE_TIME - display proper message
		// if ANALYSIS_WORK - display all relevant text fields
	}
	
	public void recieveCurrentStep(Step recievedStep) {
		analyzerStep = recievedStep;
		
		/* Set work view */
		pageHeaderText.setText("Working on change request No." + currentChangeRequest.getChangeRequestID());
		tableView.setVisible(true);
		changeRequestList.clear();
		changeRequestList.add(currentChangeRequest);
		tableView.setItems(changeRequestList);
		requestdetailsButton.setDisable(false);
		
		/* Check the work status */
		switch (currentChangeRequest.getCurrentStep()) {
			case ANALYSIS_SET_TIME:
				datePane.setVisible(true);
				break;
			case ANALYSIS_APPROVE_TIME:
				createReportPane.setVisible(false);
				datePane.setVisible(false);
				notificationText.setVisible(true);
				break;
			case ANALYSIS_WORK:
				timeextensionButton.setDisable(false);
				
				createReportPane.setVisible(true);
				datePane.setVisible(false);
				notificationText.setVisible(false);
				displayTimeRemaining(analyzerStep.getEstimatedEndDate());
				break;
		}
	}
	
	/* *****************************************
     * ********** Private Methods **************
     * *****************************************/
	private void closeMyStages() {
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
	}

}
