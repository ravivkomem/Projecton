package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.EmailTLS;
import assets.MessagesCreator;
import assets.ProjectPages;
import assets.Toast;
import controllers.SupervisorController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.TimeExtension;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


/**
 * The Class SupervisorBoundary.
 *
 * @author Itay David
 */
public class SupervisorBoundary implements Initializable {
	
	/* *******************************
	 * ****** Side Bar Menu **********
	 * *******************************/
    /**  The home page button. */
    @FXML
    private Button btnHomePage;
    
    /**  The request list button. */
    @FXML
    private Button btnRequestList;
    
    /**  The appointment button. */
    @FXML
    private Button btnAppointment;
    
    /**  The approval button. */
    @FXML
    private Button btnApproval;
    
    /**  The closing step button. */
    @FXML
    private Button btnClosingStep;
    
    /**  The log out button. */
    @FXML
    private Button btnLogOut;
    
    /**  The back button. */
    @FXML
    private Button btnBack;

    
    /* *******************************
	 * ********* Main Area  **********
	 * *******************************/
    /** The extra details button. */
    @FXML
    private Button extraDetailsButton;
    /** The filter type text. */
    @FXML
    private Text filterTypeText;
    
    /**  The change request table. */
    @FXML
    private Label listElementsCounterLabel;
    
    /**  The table explanation text. */
    @FXML
    private Text tableExplanationText;
    
    /* *******************************
	 * ***** Change Request Table ****
	 * *******************************/
    /**  The change request table view. */
    @FXML
    private TableView<ChangeRequest> tableChangeRequest;
    /** The change request table column request ID. */
    @FXML
    private TableColumn<ChangeRequest, Integer> tableColumnRequestID;

    /** The change request table column status. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnStatus;

    /** The change request table column description. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnDescription;

    /** The change request table column sub system. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnSubSystem;

    /** The change request table column current step. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnCurrentStep;
    
    /* *******************************
	 * ***** Time Extension Table ****
	 * *******************************/
    /** The table time extension. */
    @FXML
    private TableView<TimeExtension> tableTimeExtension;
    /** The time extension table column step type. */
    @FXML
    private TableColumn<TimeExtension, String> tableCoulmnStepType;

    /** The time extension table column old date. */
    @FXML
    private TableColumn<TimeExtension, Date> tableCoulmnOldDate;

    /** The time extension table column new date. */
    @FXML
    private TableColumn<TimeExtension, Date> tableCoulmnNewDate;

    /** The time extension table column reason. */
    @FXML
    private TableColumn<TimeExtension, String> tableCoulmnReason;
    
    /* *******************************
	 * ***** Auto Appoint Analyzer ***
	 * *******************************/
    /** The analyzer auto appoint grid pane. */
    @FXML
    private GridPane analyzerAutoAppointGridPane;
    
    /**  The handler name auto appoint text. */
    @FXML
    private TextField txtHandlerNameAutoAppoint;
    
    /**  The approve analyzer appointment button. */
    @FXML
    private Button btnApproveAppointment;
    /** The deny analyzer appointment button. */
    @FXML
    private Button btnDenyAppointment;
    
    /* *********************************
	 * ** Supervisor Appoint Analyzer **
	 * *********************************/
    /**  The analyzer supervisor appoint grid pane. */
    @FXML
    private GridPane analyzerSupervisorAppointGridPane;
    /**  The set analyzer button. */
    @FXML
    private Button btnSetAnalyzer;
    /** The combo select analyzer. */
    @FXML
    private ComboBox<String> comboSelectAnalyizer;
    /* *********************************
	 * *** Execution Leader Appoint ****
	 * *********************************/
    /** The execution leader appoint grid pane. */
    @FXML
    private GridPane executionLeaderAppointGridPane;
    /** The combo select execution leader. */
    @FXML
    private ComboBox<String> comboSelectExecutionLeader;
    /**  The set execution leader button. */
    @FXML
    private Button btnSetExecutionLeader;
    
    /* *********************************
	 * ***** Analysis Time Pending *****
	 * *********************************/
    /** The analysis time pending grid pane. */
    @FXML
    private GridPane analysisTimePendingGridPane;  
    /**  The field estimated time text field. */
    @FXML
    private TextField analysisRequiredTimeTextField;
    
    /**  The approve analysis time button. */
    @FXML
    private Button btnApproveAnalysisTime;
    
    /**  The deny analysis time button. */
    @FXML
    private Button btnDenyAnalysisTime;
    
    /* *********************************
   	 * **** Execution Time Pending *****
   	 * *********************************/
    /** The execution time pending grid layout. */
    @FXML
    private GridPane executionTimePendingGridLayout;
    /** The execution required time text field. */
    @FXML
    private TextField executionRequiredTimeTextField;
    /** The btn approve execution time. */
    @FXML
    private Button btnApproveExecutionTime;
    /** The btn deny execution time. */
    @FXML
    private Button btnDenyExecutionTime;
    
    /* *********************************
	 * ********* Time Extension ********
	 * *********************************/
    /** The time extension request grid pane. */
    @FXML
    private GridPane timeExtensionRequestGridPane;    
    /** The current deadline date label. */
    @FXML
    private Label currentDeadlineDateLabel;
    /** The requested time extension date label. */
    @FXML
    private Label requestedTimeExtensionDateLabel;
    /** The reason label. */
    @FXML
    private Label reasonLabel;
    /** The btn approve time extension. */
    @FXML
    private Button btnApproveTimeExtension;
    /** The btn deny time extension. */
    @FXML
    private Button btnDenyTimeExtension; 
    /** The btn time extension. */
    @FXML
    private Button btnTimeExtension;
    
    /* *******************************
   	 * ********* Closing Step ********
   	 * *******************************/
    /** The closing step grid pane. */
    @FXML
    private GridPane closingStepGridPane;
    /** The closing step description label. */
    @FXML
    private Label closingStepDescriptionLabel;
    /** The txt send message to initiator. */
    @FXML
    private TextArea emailMessageTextArea;
    /** The initiator name label. */
    @FXML
    private Label initatorNameLabel;
    /** The job description label. */
    @FXML
    private Label jobDescriptionLabel;
    /** The email address label. */
    @FXML
    private Label emailAddressLabel;
    /** The email message char label. */
    @FXML
    private Label emailMessageCharLabel;
    /** The btn send. */
    @FXML
    private Button sendEmailToInitatorButton;
    
    /* *******************************
   	 * * Change Request In Progress **
   	 * *******************************/
    /** The change request in progress grid pane. */
    @FXML
    private GridPane changeRequestInProgressGridPane;
    /** The change request finished grid pane. */
    @FXML
    private GridPane changeRequestFinishedGridPane;
    /** The suspended change request grid pane. */
    @FXML
    private GridPane suspendedChangeRequestGridPane;
    
    /* *******************************
	 * ***** Constants Objects *******
	 * *******************************/
    /** The Constant ALL_CHANGE_REQUESTS. */
    private static final String ALL_CHANGE_REQUESTS = "All Change Requests";
    
    /** The Constant APPOINTMENTS. */
    private static final String APPOINTMENTS = "Appointments";
    
    /** The Constant TIME_APPROVAL. */
    private static final String TIME_APPROVAL = "Time Approvals";
    
    /** The Constant TIME_EXTENSIONS. */
    private static final String TIME_EXTENSIONS = "Time Extensions";
    
    /** The Constant CLOSING_STEP. */
    private static final String CLOSING_STEP = "Closing Step";
   
   /** The Constant MAX_CHARS. */
    private static final int MAX_CHARS = 200;

    /* *******************************
	 * ****** Private Objects ********
	 * *******************************/
    /** The supervisor boundary controller. */
    private SupervisorController myController = new SupervisorController(this);
    /** The selected change request. */
    private ChangeRequest selectedChangeRequest;
    /** The selected time extension. */
    private TimeExtension selectedTimeExtension;
    /** The observable change request list. */
    private ObservableList<ChangeRequest> observableChangeRequestList = FXCollections.observableArrayList();
    /** The extension list. */
    private ObservableList<TimeExtension> observableTimeExtensionList = FXCollections.observableArrayList();
    /**   The email sender. */
    private EmailTLS emailSender = new EmailTLS();
    	
    /* (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    /* ****************************************
     * *********** Init Method ****************
     * ****************************************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		/* Set project title */
		ProjectFX.mainStage.setTitle("ICM - Menu\\Supervisor");
		 
		tableChangeRequest.setVisible(true);
    	tableTimeExtension.setVisible(false);
		
		tableTimeExtension.setLayoutX(tableChangeRequest.getLayoutX());
	    tableTimeExtension.setLayoutY(tableChangeRequest.getLayoutY());
	    
	    tableChangeRequest.setItems(observableChangeRequestList);
	    tableTimeExtension.setItems(observableTimeExtensionList);
		 
		 /* Set all panes */
		analyzerAutoAppointGridPane.setVisible(false);
		double gridLayoutX = analyzerAutoAppointGridPane.getLayoutX();
		double gridLayoutY = analyzerAutoAppointGridPane.getLayoutY();
		
		closingStepGridPane.setVisible(false);
		closingStepGridPane.setLayoutX(gridLayoutX);
		closingStepGridPane.setLayoutY(gridLayoutY);
		
		analyzerSupervisorAppointGridPane.setVisible(false);
		analyzerSupervisorAppointGridPane.setLayoutX(gridLayoutX);
		analyzerSupervisorAppointGridPane.setLayoutY(gridLayoutY);
		
		executionLeaderAppointGridPane.setVisible(false);
		executionLeaderAppointGridPane.setLayoutX(gridLayoutX);
		executionLeaderAppointGridPane.setLayoutY(gridLayoutY);
		
		analysisTimePendingGridPane.setVisible(false);
		analysisTimePendingGridPane.setLayoutX(gridLayoutX);
		analysisTimePendingGridPane.setLayoutY(gridLayoutY);
		
		executionTimePendingGridLayout.setVisible(false);
		executionTimePendingGridLayout.setLayoutX(gridLayoutX);
		executionTimePendingGridLayout.setLayoutY(gridLayoutY);
		
		timeExtensionRequestGridPane.setVisible(false);
		timeExtensionRequestGridPane.setLayoutX(gridLayoutX);
		timeExtensionRequestGridPane.setLayoutY(gridLayoutY);
		
		changeRequestInProgressGridPane.setVisible(false);
		changeRequestInProgressGridPane.setLayoutX(gridLayoutX);
		changeRequestInProgressGridPane.setLayoutY(gridLayoutY);
		
		changeRequestFinishedGridPane.setVisible(false);
		changeRequestFinishedGridPane.setLayoutX(gridLayoutX);
		changeRequestFinishedGridPane.setLayoutY(gridLayoutY);
		
		suspendedChangeRequestGridPane.setVisible(false);
		suspendedChangeRequestGridPane.setLayoutX(gridLayoutX);
		suspendedChangeRequestGridPane.setLayoutY(gridLayoutY);
		
		/* Init data from controller */
		 myController.getAllChangeRequests();
		 myController.setComboBox();
		
		 /* Init change request table view */
		 tableColumnRequestID.setCellValueFactory(new PropertyValueFactory<ChangeRequest,Integer>("changeRequestID"));
		 tableColumnCurrentStep.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("actualStep"));
		 tableColumnDescription.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("currentStateDescription"));
		 tableColumnStatus.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("actualStatus"));
		 tableColumnSubSystem.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("selectedSubsystem"));
		 /* Init time extension table view*/
		 tableCoulmnStepType.setCellValueFactory(new PropertyValueFactory<TimeExtension,String>("actualStep"));
		 tableCoulmnOldDate.setCellValueFactory(new PropertyValueFactory<TimeExtension,Date>("OldDate"));
		 tableCoulmnNewDate.setCellValueFactory(new PropertyValueFactory<TimeExtension,Date>("NewDate"));
		 tableCoulmnReason.setCellValueFactory(new PropertyValueFactory<TimeExtension,String>("Reason"));
		 /* Init main area */
		 filterTypeText.setVisible(true);
		 filterTypeText.setText(ALL_CHANGE_REQUESTS);
		 listElementsCounterLabel.setVisible(true);
		 listElementsCounterLabel.setText("0");
		 extraDetailsButton.setDisable(true);
		 tableExplanationText.setVisible(true);
		 
		 /* Text editable and wrap */
		 analysisRequiredTimeTextField.setEditable(false);
		 executionRequiredTimeTextField.setEditable(false);
		 txtHandlerNameAutoAppoint.setEditable(false);
		 emailMessageTextArea.setWrapText(true);
		 
		 /* Sets call back methods */
		 tableTimeExtension.setRowFactory(tv -> {
			    TableRow<TimeExtension> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	setAllDisplaysVisibilityOff();
			        	selectedTimeExtension = row.getItem();
			        	timeExtensionRequestGridPane.setVisible(true);
			        	currentDeadlineDateLabel.setText(selectedTimeExtension.getOldDate().toString());;
			        	requestedTimeExtensionDateLabel.setText(selectedTimeExtension.getNewDate().toString());
			        	reasonLabel.setText(selectedTimeExtension.getReason());	
			        }
			    });
			    return row ;
			});
 
		 tableChangeRequest.setRowFactory(tv -> {
			    TableRow<ChangeRequest> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	selectedChangeRequest = row.getItem();
			        	setAllDisplaysVisibilityOff();
			        	extraDetailsButton.setDisable(false);
			    			        	
			        	if (selectedChangeRequest.getStatus().equals("ACTIVE"))
			        	{
			        		switch (selectedChangeRequest.getCurrentStep())
			        		{
			        			case "ANALYZER_AUTO_APPOINT":
			        				analyzerAutoAppointGridPane.setVisible(true);
			        				txtHandlerNameAutoAppoint.setText(selectedChangeRequest.getHandlerUserName());
			        				break;
			        			case "ANALYZER_SUPERVISOR_APPOINT":
			        				analyzerSupervisorAppointGridPane.setVisible(true);
			        				break;
			        			case "EXECUTION_LEADER_SUPERVISOR_APPOINT":
			        				executionLeaderAppointGridPane.setVisible(true);
			        				break;
			        			case "ANALYSIS_APPROVE_TIME":
			        				analysisTimePendingGridPane.setVisible(true);
			        				myController.getAnalysisEstimatedDate(selectedChangeRequest.getChangeRequestID());
			        				break;
			        			case "EXECUTION_APPROVE_TIME":
			        				executionTimePendingGridLayout.setVisible(true);
			        				myController.getExecutionEstimatedDate(selectedChangeRequest.getChangeRequestID());
			        				break;
			        			case "CLOSING_STEP":
			        				closingStepGridPane.setVisible(true);
			        				closingStepDescriptionLabel.setText("This step was closed by your staff");
				        			initatorNameLabel.setText(selectedChangeRequest.getInitiatorUserName());
				        			jobDescriptionLabel.setText(selectedChangeRequest.getJobDescription());
				        			emailAddressLabel.setText(selectedChangeRequest.getEmail());
				        			break;
			        			case "DENY_STEP":
			        				closingStepGridPane.setVisible(true);
			        				closingStepDescriptionLabel.setText("This step was denied by your staff");
				        			initatorNameLabel.setText(selectedChangeRequest.getInitiatorUserName());
				        			jobDescriptionLabel.setText(selectedChangeRequest.getJobDescription());
				        			emailAddressLabel.setText(selectedChangeRequest.getEmail());
				        			break;
			        			
				        		/* All other active steps */
				        		default:
				        			changeRequestInProgressGridPane.setVisible(true);
				        			break;	
			        		}
			        		
			        	}
			        	else if (selectedChangeRequest.getStatus().equals("SUSPEND"))
			        	{
			        		suspendedChangeRequestGridPane.setVisible(true);
			        	}
			        	else
			        	{
			        		changeRequestFinishedGridPane.setVisible(true);
			        	}
			        }
			    });
			    return row ;
			});
		 
		 emailMessageTextArea.setTextFormatter(new TextFormatter<String>(change -> {
				int changeLength = change.getControlNewText().length();
				if (changeLength <= MAX_CHARS){
					emailMessageCharLabel.setText(Integer.toString(changeLength) + "/" + MAX_CHARS);
					return change;
				}
				else{
					return null;
				}
			}));
	}
	
	/* ****************************************
     * ****** Page Loading Methods ************
     * ****************************************/
	/**
	 * Load home page.
	 *
	 * @param event - Mouse click on the "Home page" button
	 */
    @FXML
    void loadHomePage(MouseEvent event)       // Return to home page
    {
    	setAllDisplaysVisibilityOff();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    
    /**
     * Perform application logout and load the login page.
     *
     * @param event - Mouse click on "Logout" button
     */
    @FXML
    void ClickLogOutFunction(MouseEvent event)    // Log Out from supervisor page
    {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }
    
    /**
     * Load previous page.
     *
     * @param event - Mouse click on "Back" button
     */
    @FXML
    void ClickBackFunction(MouseEvent event)
    {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    
    /**
     * Load extra details page.
     *
     * Display the selected change request extra details page
     * Incase there isn't any selected change request it will instead display a Toast error message
     * 
     * @param event - Mouse click on "Load extra details" button
     */
    @FXML
    void clickOnExstraDetails(MouseEvent event)
    {	
    	if(selectedChangeRequest == null) {
    		Toast.makeText(ProjectFX.mainStage, "Please select request from the table", 1500, 500, 500);
    	} else {
    		ArrayList<Object> dataList = new ArrayList<>();
        	dataList.add(selectedChangeRequest);
        	dataList.add(ProjectPages.SUPERVISOR_PAGE.getPath());
        	ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),dataList);
    	}	
    }
    
    /* ****************************************
     * ****** Side Bar Menu *******************
     * ****************************************/
    /**
     * displays all the change requests in the system
     * It will be displayed in the change requests table view in the middle
     * of the page display as set in the scene builder
     *
     * @param event - Mouse click on "All change requets" button
     */
    @FXML
    void displayAllChangeRequests(MouseEvent event)
    {
    	tableChangeRequest.setVisible(true);
    	tableTimeExtension.setVisible(false);
    	setAllDisplaysVisibilityOff();
    	filterTypeText.setText(ALL_CHANGE_REQUESTS);
    	myController.getAllChangeRequests();	
    }
    
    /**
     * Displays all the change requests that require supervisor appointments
     * Will be displayed in the change requests table view
     *
     * @param event - Mouse click on the appointments button
     */
    @FXML
    void ClickAppointmentFunction(MouseEvent event) 
    {
    	tableChangeRequest.setVisible(true);
    	tableTimeExtension.setVisible(false);
    	setAllDisplaysVisibilityOff();
    	filterTypeText.setText(APPOINTMENTS);
    	myController.SelectChangeRequestForAppointments();	
    }
    

    /**
     * Displays all the change requests that require supervisor approval of the 
     * time set by the step leader. (Only in the analysis and executions steps according to the client
     * requirements).
     * 
     * It will be displayed in the change request table view
     *
     * @param event - Mouse click on the "Approval" button
     */
    @FXML
    void ClickApprovalFunction(MouseEvent event)
    {
    	tableChangeRequest.setVisible(true);
    	tableTimeExtension.setVisible(false);
    	setAllDisplaysVisibilityOff();
    	filterTypeText.setText(TIME_APPROVAL);
    	myController.SelectAllChangeRequestForApprovals();
    }
    
    /**
     * Displays all the change requests that have an active time extension request
     * that require the supervisor to handle (Either approve or deny)
     * 
     * It will be displayed in the time extension table.
     * Changes the visibility of the two page tables (i.e change request table will be invisible while the 
     * time extension table will be visible)
     *
     * @param event - Mouse click on the "Time Extension" button
     */
    @FXML
    void clickOnTimeExtension(MouseEvent event)
    {
    	tableChangeRequest.setVisible(false);
    	tableTimeExtension.setVisible(true);
    	setAllDisplaysVisibilityOff();
    	filterTypeText.setText(TIME_EXTENSIONS);
    	myController.selectNewTimeExtensions();
    }
    
    
    /**
     *  Displays all the change requests that require the supervisor to close them
     *  either change request in the "CLOSING_STEP" or in the "DENY_STEP"
     *  It will be displayed in the change requests tableview
     *
     * @param event - Mouse click on the "Closing Step" button
     */
    @FXML
    void ClickClosingStepFunction(MouseEvent event)
    {
    	
    	tableChangeRequest.setVisible(true);
    	tableTimeExtension.setVisible(false);
    	setAllDisplaysVisibilityOff();
    	filterTypeText.setText(CLOSING_STEP);
    	myController.SelectAllChangeRequestForClose();
    }
	
    /* ****************************************
     * ******* Analyzer Auto Appoint **********
     * ****************************************/
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the selection of the analyzer in the DB
     *
     * @param event - Mouse click on "Approve Appointment" button
     */
    @FXML
    void clickOnApproveAppointment(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.changeCurrentStepToAnalysisSetTime(selectedChangeRequest.getChangeRequestID());
    	myController.InsertNewAnalysisStepAfterApprove(selectedChangeRequest.getChangeRequestID(),selectedChangeRequest.getHandlerUserName(),TimeManager.getCurrentDate(),"ACTIVE");
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Appoint analyzer", TimeManager.getCurrentDate(),
    			ProjectFX.currentUser.getFullName());
    	updateTablesUsingLastFilter();
    }
    
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the selection of the analyzer in the DB
     *
     * @param event - Mouse click on "Deny Appointment" button
     */
    @FXML
    void clickOnDenyAppointment(MouseEvent event)
    {
    	analyzerAutoAppointGridPane.setVisible(false);
    	analyzerSupervisorAppointGridPane.setVisible(true);
    	for (ChangeRequest cr : observableChangeRequestList)
    	{
    		if (cr.getChangeRequestID().equals(selectedChangeRequest.getChangeRequestID()))
    		{
    			cr.setActualStep("Supervisor Appoint Analyzer");
    		}
    	}
    	
    	myController.changeCurrentStepFromAnalyzerAutoAppoint(selectedChangeRequest.getChangeRequestID());
    }
    
    /* ****************************************
     * ***** Analyzer Supervisor Appoint ******
     * ****************************************/
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the selection of the analyzer in the DB
     *
     * @param event - Mouse click on "Set analyzer" button
     */
    @FXML
    void clickOnSetAnalyzer(MouseEvent event)
    {
    	if (comboSelectAnalyizer.getSelectionModel().isEmpty()) 
			Toast.makeText(ProjectFX.mainStage, "Please select your decision", 1500, 500, 500);
    	else
    	{
    		setAllDisplaysVisibilityOff();
    		myController.UpdateNewAnalyzerBySupervisor(comboSelectAnalyizer.getSelectionModel().getSelectedItem()
    				,selectedChangeRequest.getChangeRequestID());
    		myController.InsertNewAnalysisStep(selectedChangeRequest.getChangeRequestID()
    				,comboSelectAnalyizer.getSelectionModel().getSelectedItem(),TimeManager.getCurrentDate(),"ACTIVE");
    		myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
        			ProjectFX.currentUser.getUserName(), "Appoint analyzer", TimeManager.getCurrentDate(),
        			ProjectFX.currentUser.getFullName());
    		updateTablesUsingLastFilter();
    	}	
    }
    
    /* ****************************************
     * ***** Execution Leader Appoint ******
     * ****************************************/
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the selection of the execution leader in the DB
     *
     * @param event - Mouse click on "Set Execution" button
     */
	  @FXML
	void clickOnSetExecutionLeader(MouseEvent event)
	{
		  if (comboSelectExecutionLeader.getSelectionModel().isEmpty()) 
				Toast.makeText(ProjectFX.mainStage, "Please select your decision", 1500, 500, 500);
		  else
		  { 
			  setAllDisplaysVisibilityOff();
			  myController.UpdateExecutionLeaderBySupervisor(comboSelectExecutionLeader.getSelectionModel().getSelectedItem()
  				,"EXECUTION_SET_TIME",selectedChangeRequest.getChangeRequestID());
			  myController.InsertNewExecutionLeaderStep(selectedChangeRequest.getChangeRequestID()
				,comboSelectExecutionLeader.getSelectionModel().getSelectedItem(),TimeManager.getCurrentDate(),"ACTIVE");
			  myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
		    			ProjectFX.currentUser.getUserName(), "Appoint execution", TimeManager.getCurrentDate(),
		    			ProjectFX.currentUser.getFullName());
			  updateTablesUsingLastFilter();
		  }	  
	}
    
  /* ****************************************
   * ******* Analysis Time Pending **********
   * ****************************************/
	  /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the approval of analysis time in the DB
     *
     * @param event - Mouse click on "Approve Analysis Time" button
     */
    @FXML
    void clickOnApproveAnalysisTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.approvedAnalysisTime("ANALYSIS_WORK",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Approve analysis time", TimeManager.getCurrentDate(),
    			ProjectFX.currentUser.getFullName());
    	updateTablesUsingLastFilter();
    }  
	
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the denial of analysis time in the DB
     *
     * @param event - Mouse click on "Deny Analysis Time" button
     */
    @FXML
    void clickOnDenyAnalysisTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.denyAnalysisTime("ANALYSIS_SET_TIME",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Deny Analysis time", TimeManager.getCurrentDate(),
    			ProjectFX.currentUser.getFullName());
    	updateTablesUsingLastFilter();	
    }
	  
    /* ****************************************
     * ******* Execution Time Pending **********
     * ****************************************/  
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the approval of execution time in the DB
     *
     * @param event - Mouse click on "Approve Execution Time" button
     */
    @FXML
    void clickOnApproveExecutionTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.approvedExecutionTime("EXECUTION_WORK",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Approve execution time", TimeManager.getCurrentDate(),
    			ProjectFX.currentUser.getFullName());
    	updateTablesUsingLastFilter();
    }
    
    /**
     * This method sets the visibly to display only the change requests table
     * with the last filter selected.
     * Also calls the controller function in order to update the denial of execution time in the DB
     *
     * @param event - Mouse click on "Deny Execution Time" button
     */
    @FXML
    void clickOnDenyExecutionTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.denyExecutionTime("EXECUTION_SET_TIME",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Deny execution time", TimeManager.getCurrentDate(),
    			ProjectFX.currentUser.getFullName());
    	updateTablesUsingLastFilter();
    }   
	  
    /* ****************************************
     * *********** Time Extensions ************
     * ****************************************/
    /**
     * This method change the visibility of the page to contain only the menu bar
     * and the time extension table view, also calls the controller to update
     * the time extension table in the DB with the supervisor approval.
     * 
     * @param event - Mouse click on the "Approve Time Extension" button
     */
    @FXML
    void clickOnApproveTimeExtension(MouseEvent event)
    {
    	if(selectedTimeExtension==null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please select requset for time extension", 1500, 500, 500);
    	}
    	else
    	{
    		String stepType = selectedTimeExtension.getStepType().getStepName();
    		if(stepType.equals("Analysis"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve analysis time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateAnalysisStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Committee"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve committee time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateCommitteeStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Execution"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve execution time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateExecutionStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Testing"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve testing time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateTestingStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		setAllDisplaysVisibilityOff();
    		updateTablesUsingLastFilter();
    		emailSender.sendMessage("leehugi93@gmail.com", "Approved time extension",
    				"Supervisor has approved:\n"
    				+ "Time Extension ID " + selectedTimeExtension.getStepID() + " For step " + stepType +"\n"
    				+ "Have a nice day");
    	}	
    }
    
    /**
     * This method change the visibility of the page to contain only the menu bar
     * and the time extension table view, also calls the controller to update
     * the time extension table in the DB with the supervisor denial.
     * 
     * @param event - Mouse click on the "Deny Time Extension" button
     */
    @FXML
    void clickOnDenyTimeExtension(MouseEvent event)
    {
    	if(selectedTimeExtension==null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please select requset for time extension", 1500, 500, 500);
    	}
    	else
    	{
    		String stepType = selectedTimeExtension.getStepType().getStepName();
    		if(stepType.equals("Analysis"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny analysis time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateAnalysisStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Committee"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny committee time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateCommitteeStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Execution"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny execution time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateExecutionStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Testing"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny testing time extension", TimeManager.getCurrentDate(),
    	    			ProjectFX.currentUser.getFullName());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateTestingStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		setAllDisplaysVisibilityOff();
    		updateTablesUsingLastFilter();
    	}
    }
     
    /* ****************************************
     * *********** Closing Step ***************
     * ****************************************/
    
    /**
     * This method is for the controller to send a list of all the information engineers 
     * that exists in the DB, for the boundary to update the comboboxes of the analyzers and execution leaders
     * 
     * @param informationEngineers - List of all the information engineers user names
     */
	public void recieveAllInformationEngineers(ArrayList<String> informationEngineers)
	{
		for(int i=0;i<informationEngineers.size();i++)
		{
			comboSelectAnalyizer.getItems().add(informationEngineers.get(i));
			comboSelectExecutionLeader.getItems().add(informationEngineers.get(i));
		}	
	}

   
	
	/**
	 * This method is called by the controller, in order to update the change request tableview
	 * with all the change requests as received from the DB by the selected filter as described in the above functions.
	 * Also updates the label that contains the quantity of elements in the table
	 * 
	 * @param changeRequestsList - All the change requests that were selected by the controller
	 */
	public void handleChangerequestResultForTable(ArrayList<ChangeRequest> changeRequestsList)
	{
		observableChangeRequestList.clear();
		if (!changeRequestsList.isEmpty()) {
			observableChangeRequestList.addAll(changeRequestsList);
		}
		listElementsCounterLabel.setText(Integer.toString(observableChangeRequestList.size()));
	}
	
	
	/**
	 * This method is called by the controller, in order to update the time extensions tableview
	 * with all the time extensions as received from the DB by the selected filter as described in the above functions.
	 * Also updates the label that contains the quantity of elements in the table
	 * 
	 * @param timeExtensionsList - All the time extensions selected by the controller
	 */
	public void handleTimeExtensionForTable(ArrayList<TimeExtension> timeExtensionsList)
	{
		observableTimeExtensionList.clear();
		if (!timeExtensionsList.isEmpty()) {
			observableTimeExtensionList.addAll(timeExtensionsList);
		}
		listElementsCounterLabel.setText(Integer.toString(observableTimeExtensionList.size()));
	}

    /**
     * Update tables using last filter.
     * This method is used to "refresh" the table after supervisor actions
     * that impacted the change requests / time extensions in the corresponding tables
     * 
     */
    private void updateTablesUsingLastFilter() {
    	switch (filterTypeText.getText())
    	{
    		case ALL_CHANGE_REQUESTS:
    			displayAllChangeRequests(null);
    			break;
    		case APPOINTMENTS:
    			ClickAppointmentFunction(null);
    			break;
    		case CLOSING_STEP:
    			ClickClosingStepFunction(null);
    			break;
    		case TIME_APPROVAL:
    			ClickApprovalFunction(null);
    			break;
    		case TIME_EXTENSIONS:
    			clickOnTimeExtension(null);
    			break;
    		default:
    			System.out.println("Reached here without filter");
    			break;
    	}
	}

    /**
     * This method send mail to the initiator and closes the step.
     * Also writes the tech manager in the mail (In BCC option) incase the step was closed
     * If it was denied only a mail to the initator will be send
     *
     * @param event - Mouse click on "Send" button
     */
    @FXML
    void clickOnSend(MouseEvent event)
    {
    	if(emailMessageTextArea.getText().equals("")) {
    		Toast.makeText(ProjectFX.mainStage, "Please write a message first", 1500, 500, 500);
    	}else {
        	//myController.getUserEmail(selectedChangeRequest.getInitiatorUserName());
    		myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
	    			ProjectFX.currentUser.getUserName(), "Closed change request", TimeManager.getCurrentDate(),
	    			ProjectFX.currentUser.getFullName());
        	myController.setStatusToClosed(TimeManager.getCurrentDate(),"CLOSED","FINISH",selectedChangeRequest.getChangeRequestID());
        	myController.setEndDate(TimeManager.getCurrentDate(),"CLOSED",selectedChangeRequest.getChangeRequestID());
        	if (selectedChangeRequest.getCurrentStep().equals("CLOSING_STEP"))
        	{
        		emailSender.sendMessage(selectedChangeRequest.getEmail(), "leehugi93@gmail.com" ,"Closed Request", 
            			MessagesCreator.supervisorCloseChangeRequest(selectedChangeRequest.getFullName(), emailMessageTextArea.getText()));
        	}
        	else
        	{
        		emailSender.sendMessage(selectedChangeRequest.getEmail(),"Denied request", 
            			MessagesCreator.supervisorCloseChangeRequest(selectedChangeRequest.getFullName(), emailMessageTextArea.getText()));
        	}			
        	emailMessageTextArea.setText("");
        	setAllDisplaysVisibilityOff();
        	updateTablesUsingLastFilter();
    	}
    }
      
    /**
     * Gets the execution required time as submitted by the execution leader
     * Displays that date the execution required time text field for the supervisor to view
     * this method is called by the controller in order to update the display with data from the DB
     *
     * @param executionRequiredTime - The date that was asked by the execution leader
     */
	public void getExecutionRequiredTime(Date executionRequiredTime)
	{
		executionRequiredTime = TimeManager.addDays(executionRequiredTime, 1);
		executionRequiredTimeTextField.setText(executionRequiredTime.toString());
	}

	/**
     * Gets the execution required time as submitted by the analyzer
     * Displays that date the analysis required time text field for the supervisor to view
     * this method is called by the controller in order to update the display with data from the DB
     *
     * @param analysisRequiredTime - The date that was asked by the execution leader
     */
	public void getAnalysisRequiredTime(Date analysisRequiredTime)
	{
		analysisRequiredTime = TimeManager.addDays(analysisRequiredTime, 1);
		analysisRequiredTimeTextField.setText(analysisRequiredTime.toString());	
	}
	
    /**
     * This method is used in order to "reset" the page displays
     * Sets all the different grid panes visibility to false
     * (Does not include the side bar menu and the main area which will be handled diffrently)
     */
    public void setAllDisplaysVisibilityOff()
	{
    	 extraDetailsButton.setDisable(true);
    	 analyzerAutoAppointGridPane.setVisible(false);
    	 analyzerSupervisorAppointGridPane.setVisible(false);
    	 executionLeaderAppointGridPane.setVisible(false);
    	 analysisTimePendingGridPane.setVisible(false);
    	 executionTimePendingGridLayout.setVisible(false);
    	 timeExtensionRequestGridPane.setVisible(false);
    	 closingStepGridPane.setVisible(false);
    	 changeRequestFinishedGridPane.setVisible(false);
    	 changeRequestInProgressGridPane.setVisible(false);
    	 suspendedChangeRequestGridPane.setVisible(false);
	}
    
    //**********************************TOASTS*************************************************//
    
	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
     * Show appoint execution leader success.
     */
    public void ShowAppointExecutionLeaderSuccess()
	{
		Toast.makeText(ProjectFX.mainStage, "Execution leader assigned successfully", 1500, 500, 500);
	}
	
	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show success approve appoint.
	 */
	public void ShowSuccessAproveAppoint()
	{
		Toast.makeText(ProjectFX.mainStage, "Analyzer assigned successfully", 1500, 500, 500);
	}
    
    /**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
     * Show deny analysis time.
     */
    public void showDenyAnalysisTime()
    {
    	Toast.makeText(ProjectFX.mainStage, "Analysis requested time denied", 1500, 500, 500);
	}
    
    /**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
     * Show approve analysis time.
     */
    public void showApproveAnalysisTime()
    {	
    	Toast.makeText(ProjectFX.mainStage, "Analysis requested time approved", 1500, 500, 500);	
	}
    
    /**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
     * 
     * Show approve execution time.
     */
    public void showApproveExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution requested time approved", 1500, 500, 500);
	}
    
    /**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
     * 
     * Show deny execution time.
     */
    public void showDenyExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution requested time denied", 1500, 500, 500);
	}
    
	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show change request suspended.
	 */
	public void showChangeRequestSuspended()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request is Suspended", 1500, 500, 500);
	}

	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show change request unsuspended.
	 */
	public void showChangeRequestUnsuspended()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request is Active", 1500, 500, 500);
	}
	

	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show change request closed.
	 */
	public void showChangeRequestClosed()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request closed successfully", 1500, 500, 500);
	}
	
	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show analyzer supervisor appoint toast.
	 *
	 * @param affectedRows - affected rows in the database (Expecting only one row to be affected)
	 */
	public void ShowAnalyzerSupervisorAppointToast(int affectedRows)
	{
		if(affectedRows==1) {
			//Toast.makeText(ProjectFX.mainStage, "Please Set an Analyzer", 1500, 500, 500);
		}
		else
			Toast.makeText(ProjectFX.mainStage, "Database error, please contact system admin", 1500, 500, 500);
	}

	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show success analyzer appoint.
	 *
	 * @param affectedRows - affected rows in the database (Expecting only one row to be affected)
	 */
	public void ShowSuccessAnalyzerAppoint(int affectedRows)
	{
		if(affectedRows==1)
			Toast.makeText(ProjectFX.mainStage, "Analyzer assigned successfully", 1500, 500, 500);
		else
			Toast.makeText(ProjectFX.mainStage, "Database error, please contact system admin", 1500, 500, 500);	
	}

	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show approve time extension.
	 */
	public void showApproveTimeExtension()
	{
		Toast.makeText(ProjectFX.mainStage, "Time extension approved", 1500, 500, 500);
	}

	/**
	 * This method is called by to controller to indicate the database update status
	 * in order to have user feedback in the page displays.
	 * 
	 * Show deny time extension.
	 */
	public void showDenyTimeExtension()
	{
		Toast.makeText(ProjectFX.mainStage, "Time extension denied", 1500, 500, 500);
	}
}