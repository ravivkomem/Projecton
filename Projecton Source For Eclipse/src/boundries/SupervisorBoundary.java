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
import entities.User;
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
// TODO: Auto-generated Javadoc

/**
 * The Class SupervisorBoundary.
 *
 * @author Itay David
 */
public class SupervisorBoundary implements Initializable {
	
	/* *******************************
	 * ****** Side Bar Menu **********
	 * *******************************/
    /** The home page button */
    @FXML
    private Button btnHomePage;
    /** The request list button */
    @FXML
    private Button btnRequestList;
    /** The appointment button */
    @FXML
    private Button btnAppointment;
    /** The approval button */
    @FXML
    private Button btnApproval;
    /** The closing step  */
    @FXML
    private Button btnClosingStep;
    /** The btn log out. */
    @FXML
    private Button btnLogOut;
    /** The btn back. */
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
    /** The change request table */
    @FXML
    private Label listElementsCounterLabel;
    /** The table explanation text */
    @FXML
    private Text tableExplanationText;
    
    /* *******************************
	 * ***** Change Request Table ****
	 * *******************************/
    @FXML
    private TableView<ChangeRequest> tableChangeRequest;
    /** The table column request ID. */
    @FXML
    private TableColumn<ChangeRequest, Integer> tableColumnRequestID;

    /** The table column status. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnStatus;

    /** The table column description. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnDescription;

    /** The table column sub system. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnSubSystem;

    /** The table column current step. */
    @FXML
    private TableColumn<ChangeRequest, String> tableColumnCurrentStep;
    
    /* *******************************
	 * ***** Time Extension Table ****
	 * *******************************/
    /** The table time extension. */
    @FXML
    private TableView<TimeExtension> tableTimeExtension;
    /** The table coulmn step type. */
    @FXML
    private TableColumn<TimeExtension, String> tableCoulmnStepType;

    /** The table coulmn old date. */
    @FXML
    private TableColumn<TimeExtension, Date> tableCoulmnOldDate;

    /** The table coulmn new date. */
    @FXML
    private TableColumn<TimeExtension, Date> tableCoulmnNewDate;

    /** The table coulmn reason. */
    @FXML
    private TableColumn<TimeExtension, String> tableCoulmnReason;
    
    
    /* *******************************
	 * ***** Auto Appoint Analyzer ***
	 * *******************************/
    @FXML
    private GridPane analyzerAutoAppointGridPane;
    /** The txt handler name auto appoint. */
    @FXML
    private TextField txtHandlerNameAutoAppoint;
    /** The btn approve appointment. */
    @FXML
    private Button btnApproveAppointment;
    /** The btn deny appointment. */
    @FXML
    private Button btnDenyAppointment;
    
    /* *********************************
	 * ** Supervisor Appoint Analyzer **
	 * *********************************/
    @FXML
    private GridPane analyzerSupervisorAppointGridPane;
    /** The set analyzer button */
    @FXML
    private Button btnSetAnalyzer;
    /** The combo select analyizer. */
    @FXML
    private ComboBox<String> comboSelectAnalyizer;
    
    /* *********************************
	 * *** Execution Leader Appoint ****
	 * *********************************/
    @FXML
    private GridPane executionLeaderAppointGridPane;
    /** The combo select execution leader. */
    @FXML
    private ComboBox<String> comboSelectExecutionLeader;
    /** The btn set execution leader. */
    @FXML
    private Button btnSetExecutionLeader;
    
    /* *********************************
	 * ***** Analysis Time Pending *****
	 * *********************************/
    @FXML
    private GridPane analysisTimePendingGridPane;
    /** The txt field estimated time. */
    @FXML
    private TextField analysisRequiredTimeTextField;
    /** The btn approve analysis time. */
    @FXML
    private Button btnApproveAnalysisTime;
    /** The btn deny analysis time. */
    @FXML
    private Button btnDenyAnalysisTime;
    
    /* *********************************
	 * **** Execution Time Pending *****
	 * *********************************/
    @FXML
    private GridPane executionTimePendingGridLayout;
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
    @FXML
    private GridPane timeExtensionRequestGridPane;
    @FXML
    private Label currentDeadlineDateLabel;
    @FXML
    private Label requestedTimeExtensionDateLabel;
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
    @FXML
    private GridPane closingStepGridPane;
    @FXML
    private Label closingStepDescriptionLabel;
    /** The txt send message to initiator. */
    @FXML
    private TextArea emailMessageTextArea;
    @FXML
    private Label initatorNameLabel;
    @FXML
    private Label jobDescriptionLabel;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private Label emailMessageCharLabel;
    /** The btn send. */
    @FXML
    private Button sendEmailToInitatorButton;
    
    /* *******************************
   	 * * Change Request In Progress **
   	 * *******************************/
    @FXML
    private GridPane changeRequestInProgressGridPane;
    @FXML
    private GridPane changeRequestFinishedGridPane;
    @FXML
    private GridPane suspendedChangeRequestGridPane;
    
    /* *******************************
	 * ***** Constants Objects *******
	 * *******************************/
    private static final String ALL_CHANGE_REQUESTS = "All Change Requests";
    private static final String APPOINTMENTS = "Appointments";
    private static final String TIME_APPROVAL = "Time Approvals";
    private static final String TIME_EXTENSIONS = "Time Extensions";
    private static final String CLOSING_STEP = "Closing Step";
   // private static final String SUSPENSIONS = "Suspended Change Requests";
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
    /**  The email sender */
    private EmailTLS emailSender = new EmailTLS();
    	
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
     * Click home page function.
     *
     * @param event This method handle click on home page in menu
     */
    @FXML
    void loadHomePage(MouseEvent event)       // Return to home page
    {
    	setAllDisplaysVisibilityOff();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    /**
     * Click log out function.
     *
     * @param event This method handle click on log out
     */
    @FXML
    void ClickLogOutFunction(MouseEvent event)    // Log Out from supervisor page
    {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }
    
    /**
     * Click back function.
     * @param event This method handle click on back in menu
     */
    @FXML
    void ClickBackFunction(MouseEvent event)
    {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    
    
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
     * Click request list function.
     *
     * @param event This method handle click on request list in menu
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
	 * Click appointment function.
	 *
	 * @param event This method handle click on appointment in menu
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
     * Click approval function.
     *
     * @param event This method handle click on approval in menu
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
     * Click on time extension.
     *
     * @param event the event
     */
    @FXML
    void clickOnTimeExtension(MouseEvent event)
    {
    	tableChangeRequest.setVisible(false);
    	tableTimeExtension.setVisible(true);
    	setAllDisplaysVisibilityOff();
    	filterTypeText.setText(TIME_EXTENSIONS);
    	myController.SelectAllTimeExtensions();
    }
    
    
    /**
     * Click closing step function.
     *
     * @param event This method handle click on closing step in menu
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
     * Click on approve appointment.
     *
     * @param event This method update DB when click on approve appointment of analyzer
     */
    @FXML
    void clickOnApproveAppointment(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.changeCurrentStepToAnalysisSetTime(selectedChangeRequest.getChangeRequestID());
    	myController.InsertNewAnalysisStepAfterApprove(selectedChangeRequest.getChangeRequestID(),selectedChangeRequest.getHandlerUserName(),TimeManager.getCurrentDate(),"ACTIVE");
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Appoint analyzer", TimeManager.getCurrentDate());
    	updateTablesUsingLastFilter();
    }
    
    /**
     * Click on deny appointment.
     *
     * @param event This method update DB when click on deny appointment of analyzer
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
     * Click on set analyzer.
     *
     * @param event This method update DB when click on set analyzer
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
        			ProjectFX.currentUser.getUserName(), "Appoint analyzer", TimeManager.getCurrentDate());
    		updateTablesUsingLastFilter();
    	}	
    }
    
    /* ****************************************
     * ***** Execution Leader Appoint ******
     * ****************************************/
    /**
     * Click on set execution leader.
     *
     * @param event This method update DB when click on set execution leader
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
		    			ProjectFX.currentUser.getUserName(), "Appoint execution", TimeManager.getCurrentDate());
			  updateTablesUsingLastFilter();
		  }	  
	}
    
  /* ****************************************
   * ******* Analysis Time Pending **********
   * ****************************************/
	  /**
	 * Click on approve analysis time.
	 *
	 * @param event This method update DB when click on approve analysis time
	 */
    @FXML
    void clickOnApproveAnalysisTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.approvedAnalysisTime("ANALYSIS_WORK",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Approve analysis time", TimeManager.getCurrentDate());
    	updateTablesUsingLastFilter();
    }  
	
    /**
	 * Click on deny analysis time.
	 *
	 * @param event This method update DB when click on deny execution leader
	 */
    @FXML
    void clickOnDenyAnalysisTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.denyAnalysisTime("ANALYSIS_SET_TIME",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Deny Analysis time", TimeManager.getCurrentDate());
    	updateTablesUsingLastFilter();	
    }
	  
    /* ****************************************
     * ******* Execution Time Pending **********
     * ****************************************/  
    /**
     * Click on approve execution time.
     *
     * @param event This method update DB when click on approve execution time
     */
    @FXML
    void clickOnApproveExecutionTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.approvedExecutionTime("EXECUTION_WORK",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Approve execution time", TimeManager.getCurrentDate());
    	updateTablesUsingLastFilter();
    }
    
    /**
     * Click on deny execution time.
     *
     * @param event This method update DB when click on deny execution time
     */
    @FXML
    void clickOnDenyExecutionTime(MouseEvent event)
    {
    	setAllDisplaysVisibilityOff();
    	myController.denyExecutionTime("EXECUTION_SET_TIME",selectedChangeRequest.getChangeRequestID());
    	myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    			ProjectFX.currentUser.getUserName(), "Deny execution time", TimeManager.getCurrentDate());
    	updateTablesUsingLastFilter();
    }   
	  
    /* ****************************************
     * *********** Time Extensions ************
     * ****************************************/
    /**
     * Click on approve time extension.
     *
     * @param event the event
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
    	    			ProjectFX.currentUser.getUserName(), "Approve analysis time extension", TimeManager.getCurrentDate());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateAnalysisStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Committee"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve committee time extension", TimeManager.getCurrentDate());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateCommitteeStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Execution"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve execution time extension", TimeManager.getCurrentDate());
    			myController.updateTimeExtensionStatus("APPROVED",selectedTimeExtension.getStepID());
    			myController.updateExecutionStepEstimatedEndDate(selectedTimeExtension.getNewDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Testing"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Approve testing time extension", TimeManager.getCurrentDate());
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
     * Click on deny time extension.
     *
     * @param event the event
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
    	    			ProjectFX.currentUser.getUserName(), "Deny analysis time extension", TimeManager.getCurrentDate());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateAnalysisStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Committee"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny committee time extension", TimeManager.getCurrentDate());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateCommitteeStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Execution"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny execution time extension", TimeManager.getCurrentDate());
    			myController.updateTimeExtensionStatusAfterDeny("DENY",selectedTimeExtension.getStepID());
    			myController.updateExecutionStepEstimatedEndDate(selectedTimeExtension.getOldDate(),selectedTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Testing"))
    		{
    			myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
    	    			ProjectFX.currentUser.getUserName(), "Deny testing time extension", TimeManager.getCurrentDate());
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
    
    
	public void recieveAllInformationEngineers(ArrayList<String> informationEngineers)
	{
		for(int i=0;i<informationEngineers.size();i++)
		{
			comboSelectAnalyizer.getItems().add(informationEngineers.get(i));
			comboSelectExecutionLeader.getItems().add(informationEngineers.get(i));
		}	
	}

   

	public void handleChangerequestResultForTable(ArrayList<ChangeRequest> resultList)
	{
		observableChangeRequestList.clear();
		if (!resultList.isEmpty()) {
			observableChangeRequestList.addAll(resultList);
		}
		listElementsCounterLabel.setText(Integer.toString(observableChangeRequestList.size()));
	}
	
	
	/**
	 * Handle time extension for table.
	 *
	 * @param resultList the result list
	 */
	public void handleTimeExtensionForTable(ArrayList<TimeExtension> resultList)
	{
		observableTimeExtensionList.clear();
		if (!resultList.isEmpty()) {
			observableTimeExtensionList.addAll(resultList);
		}
		listElementsCounterLabel.setText(Integer.toString(observableTimeExtensionList.size()));
	}
   

    
    

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
     * Click on send.
     *
     * @param event the event
     */
    @FXML
    void clickOnSend(MouseEvent event)
    {
    	if(emailMessageTextArea.getText().equals("")) {
    		Toast.makeText(ProjectFX.mainStage, "Please write a message first", 1500, 500, 500);
    	}else {
        	//myController.getUserEmail(selectedChangeRequest.getInitiatorUserName());
    		myController.inserntNewSupervisorUpdate(selectedChangeRequest.getChangeRequestID(), 
	    			ProjectFX.currentUser.getUserName(), "Closed change request", TimeManager.getCurrentDate());
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
     * Send email to initiator user.
     *
     * @param initiator the initiator
     */
    public void sendEmailToInitiatorUser(User initiator) {
    	
    }   
    
    /**
     * Gets the execution end date.
     *
     * @param res This method get execution end date
     * @return the execution end date
     */
	public void getExecutionEndDate(Date res)
	{
		res = TimeManager.addDays(res, 1);
		executionRequiredTimeTextField.setText(res.toString());
	}

	/**
	 * Gets the analysis end date.
	 *
	 * @param res This method get analysis end date
	 * @return the analysis end date
	 */
	public void getAnalysisEndDate(Date res)
	{
		res = TimeManager.addDays(res, 1);
		analysisRequiredTimeTextField.setText(res.toString());	
	}
	

    
    
     
    /**
     * This method set all gui into invisible.
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
     * Show appoint execution leader success.
     */
    public void ShowAppointExecutionLeaderSuccess()
	{
		Toast.makeText(ProjectFX.mainStage, "Execution leader assigned successfully", 1500, 500, 500);
	}
	
	/**
	 * Show success aprove appoint.
	 */
	public void ShowSuccessAproveAppoint()
	{
		Toast.makeText(ProjectFX.mainStage, "Analyzer assigned successfully", 1500, 500, 500);
	}
    
    /**
     * Show deny analysis time.
     */
    public void showDenyAnalysisTime()
    {
    	Toast.makeText(ProjectFX.mainStage, "Analysis requested time denied", 1500, 500, 500);
	}
    
    /**
     * Show approve analysis time.
     */
    public void showApproveAnalysisTime()
    {	
    	Toast.makeText(ProjectFX.mainStage, "Analysis requested time approved", 1500, 500, 500);	
	}
    
    /**
     * Show approve execution time.
     */
    public void showApproveExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution requested time approved", 1500, 500, 500);
	}
    
    /**
     * Show deny execution time.
     */
    public void showDenyExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution requested time denied", 1500, 500, 500);
	}
    
	/**
	 * Show change request suspended.
	 */
	public void showChangeRequestSuspended()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request is Suspended", 1500, 500, 500);
	}

	/**
	 * Show change request unsuspended.
	 */
	public void showChangeRequestUnsuspended()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request is Active", 1500, 500, 500);
	}
	

	/**
	 * Show change request closed.
	 */
	public void showChangeRequestClosed()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request closed successfully", 1500, 500, 500);
	}
	
	/**
	 * Show analyzer supervisor appoint toast.
	 *
	 * @param affectedRows the affected rows
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
	 * Show success analyzer appoint.
	 *
	 * @param affectedRows2 the affected rows 2
	 */
	public void ShowSuccessAnalyzerAppoint(int affectedRows2)
	{
		if(affectedRows2==1)
			Toast.makeText(ProjectFX.mainStage, "Analyzer assigned successfully", 1500, 500, 500);
		else
			Toast.makeText(ProjectFX.mainStage, "Database error, please contact system admin", 1500, 500, 500);	
	}

	/**
	 * Show approve time extension.
	 */
	public void showApproveTimeExtension()
	{
		Toast.makeText(ProjectFX.mainStage, "Time extension approved", 1500, 500, 500);
	}

	/**
	 * Show deny time extension.
	 */
	public void showDenyTimeExtension()
	{
		Toast.makeText(ProjectFX.mainStage, "Time extension denied", 1500, 500, 500);
	}
}