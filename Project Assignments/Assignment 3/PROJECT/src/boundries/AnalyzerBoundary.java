package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * The Class AnalyzerBoundary.
 *
 * @author Lior Kauffman
 * This class represents the analyzer boundary
 * with all the methods and logic implementations
 */
public class AnalyzerBoundary implements DataInitializable {

	/** The date pane. */
	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	@FXML
	private AnchorPane datePane;
	
	/** The date button. */
	@FXML
	private Button dateButton;
	
	/** The logout button. */
	@FXML
	private Button logoutButton;
	
	/** The back button. */
	@FXML
	private Button backButton;
	
	/** The home page button. */
	@FXML
	private Button homepageButton;
	
	/** The request details button. */
	@FXML
	private Button requestdetailsButton;
	
	/** The create report button. */
	@FXML
	private Button createreportButton;
	
	/** The time extension button. */
	@FXML
	private Button timeextensionButton;
	
	/** The table view. */
	@FXML
	private TableView<ChangeRequest> tableView;
	
	/** The request id column. */
	@FXML
	private TableColumn<ChangeRequest, Integer> requestIdColumn;
	
	/** The step column. */
	@FXML
	private TableColumn<ChangeRequest, String> stepColumn;
	
	/** The description column. */
	@FXML
	private TableColumn<ChangeRequest, String> descriptionColumn;
	
	/** The subsystem column. */
	@FXML
	private TableColumn<ChangeRequest, String> subsystemColumn;
	
	/** The create report pane. */
	@FXML
	private AnchorPane createReportPane;
	
	/** The description text area. */
	@FXML
	private TextArea descriptiontextArea;
	
	/** The advantages text area. */
	@FXML
	private TextArea advantagestextArea;
	
	/** The constraints text area. */
	@FXML
	private TextArea constraintstextArea;
	
	/** The header text area. */
	@FXML
    private TextArea headertextArea;
    
    /** The duration date picker. */
    @FXML
    private DatePicker durationDatePicker;
    
    /** The time display text. */
    @FXML
    private Text timeDisplayText;
    
    /** The time remaining text area. */
    @FXML
    private TextArea timeRemainingTextArea;
	
	/** The time duration picker. */
	@FXML
	private DatePicker timedurationPicker;
	
	/** The subsystem combo box. */
	@FXML
	private ComboBox<String> subsystemComboBox;
	
	/** The submit button. */
	@FXML
	private Button submitButton;
	
	/** The page header text. */
	@FXML
	private Text pageHeaderText;
	
	/** The notification text. */
	@FXML
	private Text notificationText;
    
    /** The header character counter label. */
    @FXML
    private Label headerCharcterCounterLabel;
    
    /** The description character counter label. */
    @FXML
    private Label descriptionCharcterCounterLabel;
    
    /** The advantages character counter label. */
    @FXML
    private Label advantagesCharcterCounterLabel;
    
    /** The constraints character counter label. */
    @FXML
    private Label constraintsCharcterCounterLabel;
    
    /** The duration character counter label. */
    @FXML
    private Label durationCharcterCounterLabel;
    
    @FXML
    private Button btnRefresh;

	
	/* ****************************************
     * ********** Private Variables ***********
     * ***************************************/
    /** The my controller. */
	private AnalyzerController myController = new AnalyzerController(this);
	
	/** The Constant ANALYSIS_SET_TIME. */
	public static final String ANALYSIS_SET_TIME = "ANALYSIS_SET_TIME";
	
	/** The Constant ANALYSIS_APPROVE_TIME. */
	public static final String ANALYSIS_APPROVE_TIME = "ANALYSIS_APPROVE_TIME";
	
	/** The Constant ANALYSIS_WORK. */
	public static final String ANALYSIS_WORK = "ANALYSIS_WORK";
	
	/** The Constant MAX_CHARS. */
	public static final int MAX_CHARS = 100;
	
	/** The current change request. */
	private ChangeRequest currentChangeRequest;
	
	/** The analyzer step. */
	private Step analyzerStep;
	
	/** The my time extension stage. */
	private Stage myTimeExtensionStage;
	
	/** The change request list. */
	private ObservableList<ChangeRequest> changeRequestList = FXCollections.observableArrayList();
	
	/* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
	 /**
	 * This method load the home page.
	 *
	 * @param event - mouse click
	 */
	@FXML
	void loadHomePage(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	}
	 
 	/**
 	 * This method load the previous page.
 	 *
 	 * @param event - mouse click
 	 */

	@FXML
	void loadPreviousPage(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}

	/**
	 * Log out.
	 *
	 * @param event the event
	 */
	@FXML
	void LogOut(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}
	 
 	/**
 	 * This method load extra details page with the details of the current change request.
 	 *
 	 * @param event - mouse click
 	 */
	
	@FXML
	void loadRequestDetails(MouseEvent event) {
		//closeMyStages();
		ArrayList<Object> dataList = new ArrayList<>();
    	dataList.add(currentChangeRequest);
    	dataList.add(ProjectPages.ANALYZER_PAGE.getPath());
    	ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),dataList);
		
	}
	
	/**
	 * this method open the time extension page in another window.
	 *
	 * @param event the event
	 */
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
	 
 	/**
 	 * This method display the time remaining for the current step,
 	 * the method compares between  the estimatedEndDate and the current date,
 	 * and then show delay time or time remaining to the user
 	 *
 	 * @param estimatedEndDate the estimated end date
 	 */
	private void displayTimeRemaining(Date estimatedEndDate) {
		long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), estimatedEndDate);
		if(daysBetween < 0) {
			timeDisplayText.setText("Time Delay");
			timeRemainingTextArea.setText(Math.abs(daysBetween) + " Days");
		}
		else if(daysBetween == 0) {
			timeDisplayText.setText("Time Remaining");
			timeRemainingTextArea.setText("Last Day");
		}
		else {
			timeDisplayText.setText("Time Remaining");
			timeRemainingTextArea.setText(daysBetween + " Days");
		}
	}
	
	/**
	 * This method update the current step and the EstimatedEndDate in the database.
	 * The method check if all the fields are field and then send all the details to the database.
	 *
	 * @param event - mouse click
	 */
	@FXML
	void submit(MouseEvent event) {

		if (headertextArea.getText().equals("")||descriptiontextArea.getText().equals("")||advantagestextArea.getText().equals("")
				||constraintstextArea.getText().equals("")|| durationDatePicker.getValue()==null)
		{
			Toast.makeText(ProjectFX.mainStage, "Please fill all the required fields", 1500, 500, 500);
			return;
		}
		Date date = Date.valueOf(durationDatePicker.getValue());
		if(TimeManager.getDaysBetween(TimeManager.getCurrentDate(), date)<0) {
			Toast.makeText(ProjectFX.mainStage, "Please fill valid date", 1500, 500, 500);
		}
		else
		{
			myController.updateChangeRequestCurrentStepAndHandlerName(currentChangeRequest, "COMMITTEE_WORK", "-",date);
			myController.updateAnalysisStepClose(currentChangeRequest, TimeManager.getCurrentDate(), "CLOSED",headertextArea.getText(),
			descriptiontextArea.getText(), advantagestextArea.getText(), Date.valueOf(durationDatePicker.getValue()), constraintstextArea.getText());
			myController.updateTimeExtensionDB(analyzerStep.getStepID(),"Analysis");
			myController.getCommitteeDirector();
		}
	}
	 
 	/**
 	 * This method update EstimatedEndDate to the current step in the database,
 	 * first the method check if the date is valid and then send it to the database.
 	 *
 	 * @param event the new date
 	 */
	
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
				notificationText.setText("Work pending supervisor approval\nTime requestsed: " + 
				selectedDate.toString()+"\nRefresh Page (To see if approved)");
				notificationText.setVisible(true);
				btnRefresh.setVisible(true);
				timedurationPicker.setValue(null);
			}
		}
	}
	
	/**
	 * This method refresh the page if the user press on the refresh button.
	 * @param event
	 */
    @FXML
    void refreshPage(MouseEvent event) {
    	btnRefresh.setVisible(false);
    	notificationText.setVisible(false);
    	myController.getChangeRequestById(currentChangeRequest.getChangeRequestID());
    }


	/* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
    
    /**
     * The method gets ChangeRequest object after the user press refresh,
     * the method update the currentChangeRequest with the new request that received.
     * @param request - The change request
     */
    public void getCurrentChangeRequestAfterRefresh(ChangeRequest request) {
    	currentChangeRequest = request;
    	recieveCurrentStep(analyzerStep);
    }
    

	public void updateTesterPageToDBSuccessfully(int affectedRows) {
		if (affectedRows == 1) {
			Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
		} else 
		{
			Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
		}
	}
	 
 	/**
 	 * This method counts the length of advantagestextArea and display it in the label.
 	 *
 	 * @param event the event
 	 */

	 @FXML
	    void updateAdvantagesCharcterCounter(KeyEvent event) {
		 advantagesCharcterCounterLabel.setText(advantagestextArea.getText().length() + "/ " + MAX_CHARS);

	    }
	 
 	/**
 	 * This method counts the length of constraintstextArea and display it in the label.
 	 *
 	 * @param event the event
 	 */

    @FXML
    void updateConstraintsCharcterCounter(KeyEvent event) {
    	constraintsCharcterCounterLabel.setText(constraintstextArea.getText().length() + "/" + MAX_CHARS);
    }

    /**
     * This method counts the length of descriptiontextArea and display it in the label.
     *
     * @param event the event
     */
    @FXML
    void updateDescriptionCharcterCounter(KeyEvent event) {
    	descriptionCharcterCounterLabel.setText(descriptiontextArea.getText().length() + "/" + MAX_CHARS);

    }
    
    /**
     * This method insert new row to committee_step table in the database, 
     * and load the work station page.
     *
     * @param name the committee director user name
     */

    public void getCommitteeDirectorUserName(String name) {
    	myController.insertNewCommitteeStep(currentChangeRequest.getChangeRequestID(), name, TimeManager.getCurrentDate(), "ACTIVE", 
    			TimeManager.addDays(TimeManager.getCurrentDate(), 7));
    	popUpWindowMessage(AlertType.INFORMATION,"Update Success","Your decision upload successfully");
    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }
   	

	   @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		   ProjectFX.mainStage.setTitle("ICM - Menu\\Work Station\\Analysis");
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
		timeRemainingTextArea.setEditable(false);
		
		/* Disable buttons */
		requestdetailsButton.setDisable(true);
		
		advantagestextArea.setWrapText(true);
		constraintstextArea.setWrapText(true);
		descriptiontextArea.setWrapText(true);
		headertextArea.setWrapText(true);
		
		timeextensionButton.setDisable(true);
		
		timedurationPicker.setEditable(false);
		durationDatePicker.setEditable(false);
		headerCharcterCounterLabel.setText("0/20");
		headertextArea.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= 20)
			{
				headerCharcterCounterLabel.setText(Integer.toString(changeLength) + "/" + Integer.toString(20));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
		advantagestextArea.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHARS)
			{
				advantagesCharcterCounterLabel.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHARS));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
		
		constraintstextArea.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHARS)
			{
				constraintsCharcterCounterLabel.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHARS));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
		descriptiontextArea.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHARS)
			{
				descriptionCharcterCounterLabel.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHARS));
				return change;
			}
			else
			{
				return null;
			}
		}));
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
	
	/**
	 * The method gets recievedStep - the analysis current step,
	 *  and show all the step details in the page filtered by the current step of the change request.
	 *
	 * @param recievedStep the received step
	 */
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
				timeRemainingTextArea.setDisable(true);
				datePane.setVisible(true);
				break;
			case ANALYSIS_APPROVE_TIME:
				timeRemainingTextArea.setDisable(true);
				createReportPane.setVisible(false);
				datePane.setVisible(false);
				notificationText.setText("Work pending supervisor approval\nTime requestsed: " + 
						analyzerStep.getEstimatedEndDate()+"\nRefresh Page (To see if approved)");
				notificationText.setVisible(true);
				btnRefresh.setVisible(true);
				break;
			case ANALYSIS_WORK:
				timeRemainingTextArea.setDisable(false);
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
	
	/**
	 * this method close the time extension stages if it's open.
	 */
	private void closeMyStages() {
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
	}
	
	/**
	 * this method will show up window with the message that the method gets.
	 *
	 * @param alert the alert
	 * @param msg the msg
	 * @param mess the mess
	 * @return the optional
	 */
	private static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		return alert2.showAndWait();
	}

}
