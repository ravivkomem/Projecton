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

// TODO: Auto-generated Javadoc
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
	
	/** The homepage button. */
	@FXML
	private Button homepageButton;
	
	/** The requestdetails button. */
	@FXML
	private Button requestdetailsButton;
	
	/** The createreport button. */
	@FXML
	private Button createreportButton;
	
	/** The timeextension button. */
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
	
	/** The descriptiontext area. */
	@FXML
	private TextArea descriptiontextArea;
	
	/** The advantagestext area. */
	@FXML
	private TextArea advantagestextArea;
	
	/** The constraintstext area. */
	@FXML
	private TextArea constraintstextArea;
	
	/** The headertext area. */
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
	
	/** The timeduration picker. */
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
    
    /** The header charcter counter label. */
    @FXML
    private Label headerCharcterCounterLabel;
    
    /** The description charcter counter label. */
    @FXML
    private Label descriptionCharcterCounterLabel;
    
    /** The advantages charcter counter label. */
    @FXML
    private Label advantagesCharcterCounterLabel;
    
    /** The constraints charcter counter label. */
    @FXML
    private Label constraintsCharcterCounterLabel;
    
    /** The duration charcter counter label. */
    @FXML
    private Label durationCharcterCounterLabel;

	
	/** The my controller. */
	/* ****************************************
     * ********** Private Variables ***********
     * ***************************************/
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
 	 * This method display the time remaining for the current step if estimatedEndDate > current date or the delay time if estimatedEndDate < current in days.
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
	 * This method check date propriety and update the current step and the EstimatedEndDate to DB.
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
 	 * This method check date propriety and update the current step and the EstimatedEndDate to DB.
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
				notificationText.setVisible(true);
			}
		}
	}

	/**
	 * Update tester page to DB successfully.
	 *
	 * @param affectedRows the affected rows
	 */
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
	 
 	/**
 	 * This method count the length of advantagestextArea and display it.
 	 *
 	 * @param event the event
 	 */

	 @FXML
	    void updateAdvantagesCharcterCounter(KeyEvent event) {
		 advantagesCharcterCounterLabel.setText(advantagestextArea.getText().length() + "/ " + MAX_CHARS);

	    }
	 
 	/**
 	 * This method count the length of constraintstextArea and display it.
 	 *
 	 * @param event the event
 	 */

    @FXML
    void updateConstraintsCharcterCounter(KeyEvent event) {
    	constraintsCharcterCounterLabel.setText(constraintstextArea.getText().length() + "/" + MAX_CHARS);
    }

    /**
     * Update description charcter counter.
     *
     * @param event the event
     */
    @FXML
    void updateDescriptionCharcterCounter(KeyEvent event) {
    	descriptionCharcterCounterLabel.setText(descriptiontextArea.getText().length() + "/" + MAX_CHARS);

    }
    
    /**
     * This method count the length of headertextArea and display it.
     *
     * @param event the event
     */

  
    @FXML
    void updateHeaderCharcterCounter(KeyEvent event) {
    	headerCharcterCounterLabel.setText(headertextArea.getText().length() + "/" + MAX_CHARS);

    }
    
    /**
     * This method insert new change request to Committee_Step table in the DB and load the work station page.
     *
     * @param name the name
     * @return the committee director user name
     */

    public void getCommitteeDirectorUserName(String name) {
    	myController.insertNewCommitteeStep(currentChangeRequest.getChangeRequestID(), name, TimeManager.getCurrentDate(), "ACTIVE", 
    			TimeManager.addDays(TimeManager.getCurrentDate(), 7));
    	popUpWindowMessage(AlertType.INFORMATION,"Update Success","Your decision upload successfully");
    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }
   	
	   /* (non-Javadoc)
	    * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	    */
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
		timeRemainingTextArea.setEditable(false);
		
		/* Disable buttons */
		requestdetailsButton.setDisable(true);
		
		
		
		advantagestextArea.setWrapText(true);
		constraintstextArea.setWrapText(true);
		descriptiontextArea.setWrapText(true);
		headertextArea.setWrapText(true);
		
		timeextensionButton.setDisable(true);
		advantagestextArea.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= MAX_CHARS ? change : null));
		constraintstextArea.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= MAX_CHARS ? change : null));
		descriptiontextArea.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= MAX_CHARS ? change : null));
		headertextArea.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= MAX_CHARS ? change : null));
		timedurationPicker.setEditable(false);
		 durationDatePicker.setEditable(false);
	}

	/* (non-Javadoc)
	 * @see boundries.DataInitializable#initData(java.lang.Object)
	 */
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
	 * Recieve current step.
	 *
	 * @param recievedStep the recieved step
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
	
	/**
	 * Close my stages.
	 */
	/* *****************************************
     * ********** Private Methods **************
     * *****************************************/
	private void closeMyStages() {
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
	}
	
	/**
	 * this method will show up window with the msg that the method gets.
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
