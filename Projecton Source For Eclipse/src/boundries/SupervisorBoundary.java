package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
// TODO: Auto-generated Javadoc

/**
 * The Class SupervisorBoundary.
 *
 * @author Itay David
 */
public class SupervisorBoundary implements Initializable {

    /** The btn home page. */
    @FXML
    private Button btnHomePage;

    /** The btn request list. */
    @FXML
    private Button btnRequestList;

    /** The btn appointment. */
    @FXML
    private Button btnAppointment;

    /** The btn approval. */
    @FXML
    private Button btnApproval;

    /** The btn closing step. */
    @FXML
    private Button btnClosingStep;

    /** The btn log out. */
    @FXML
    private Button btnLogOut;

    /** The btn back. */
    @FXML
    private Button btnBack;
    
    /** The btn exstra details. */
    @FXML
    private Button btnExstraDetails;
    
    
    /** The btn set analyzer. */
    @FXML
    private Button btnSetAnalyzer;
    
    
    /** The btn approve analysis time. */
    @FXML
    private Button btnApproveAnalysisTime;
    

    /** The btn deny analysis time. */
    @FXML
    private Button btnDenyAnalysisTime;
    
    
    
    
    /** The combo select analyizer. */
    @FXML
    private ComboBox<String> comboSelectAnalyizer;
   
    /** The combo select execution leader. */
    @FXML
    private ComboBox<String> comboSelectExecutionLeader;
    
    
    /** The btn set execution leader. */
    @FXML
    private Button btnSetExecutionLeader;

    /** The btn approve appointment. */
    @FXML
    private Button btnApproveAppointment;

    /** The btn deny appointment. */
    @FXML
    private Button btnDenyAppointment;

    /** The btn send. */
    @FXML
    private Button btnSend;

    /** The txt send message to initiator. */
    @FXML
    private TextArea txtSendMessageToInitiator;
    
    /** The txt system auto appoint. */
    @FXML
    private Text txtSystemAutoAppoint;

    /** The txt handler name auto appoint. */
    @FXML
    private TextField txtHandlerNameAutoAppoint;
    
    /** The btn deny execution time. */
    @FXML
    private Button btnDenyExecutionTime;

    /** The btn approve execution time. */
    @FXML
    private Button btnApproveExecutionTime;
    
    /** The btn approve time extension. */
    @FXML
    private Button btnApproveTimeExtension;

    /** The btn deny time extension. */
    @FXML
    private Button btnDenyTimeExtension;
    
    /** The btn time extension. */
    @FXML
    private Button btnTimeExtension;
    
    
    /** The txt field estimated time. */
    @FXML
    private TextField txtFieldEstimatedTime;

    /** The txt execution estimated time. */
    @FXML
    private Text txtExecutionEstimatedTime;

    /** The txt analysis estimated time. */
    @FXML
    private Text txtAnalysisEstimatedTime;
    
    /** The txt text change. */
    @FXML
    private Text txtTextChange;

    /** The txt text explantion. */
    @FXML
    private Text txtTextExplantion;
    
    
    
    /** The table change request. */
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
    
    
    
    
    
    /** The table time extension. */
    @FXML
    private TableView<TimeExtension> tableTimeExtension;

    /** The table coulmn step id. */
    @FXML
    private TableColumn<TimeExtension,Integer> tableCoulmnStepId;

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
    
    
    
    // Vars //
    
    /** The my controller. */
    private SupervisorController myController = new SupervisorController(this);
    
    /** The my changerequest. */
    private ChangeRequest myChangerequest;
    
    /** The my time extension. */
    private TimeExtension myTimeExtension;
    
    /** The update step date. */
    java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    
    /** The request list. */
    ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
    
    /** The extension list. */
    ObservableList<TimeExtension> extensionList = FXCollections.observableArrayList();
    
    /** The email. */
    private EmailTLS email = new EmailTLS();
    
    /** The my time extension stage. */
    Stage myTimeExtensionStage = null;
	
	/** The my analysis report stage. */
	Stage myAnalysisReportStage= null;
    	
	/**
	 * This method initialize all GUI.
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		ProjectFX.mainStage.setTitle("ICM - Menu\\Supervisor");
		 tableColumnRequestID.setCellValueFactory(new PropertyValueFactory<ChangeRequest,Integer>("changeRequestID"));
		 tableColumnCurrentStep.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("actualStep"));
		 tableColumnDescription.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("currentStateDescription"));
		 tableColumnStatus.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("status"));
		 tableColumnSubSystem.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("selectedSubsystem"));
		 txtFieldEstimatedTime.setEditable(false);
		 txtHandlerNameAutoAppoint.setEditable(false);
		 setVisabilityValse();
		 txtTextChange.setVisible(true);
		 txtTextChange.setText("Hello and have a nice working day");
		 txtSendMessageToInitiator.setWrapText(true);
		 
		 myController.SelectAllChangeRequest();
		 
		 tableCoulmnStepId.setCellValueFactory(new PropertyValueFactory<TimeExtension,Integer>("StepID"));
		 tableCoulmnStepType.setCellValueFactory(new PropertyValueFactory<TimeExtension,String>("StepType"));
		 tableCoulmnOldDate.setCellValueFactory(new PropertyValueFactory<TimeExtension,Date>("OldDate"));
		 tableCoulmnNewDate.setCellValueFactory(new PropertyValueFactory<TimeExtension,Date>("NewDate"));
		 tableCoulmnReason.setCellValueFactory(new PropertyValueFactory<TimeExtension,String>("Reason"));
		 
		 setComboBoxUsers1();
		 
		 tableTimeExtension.setRowFactory(tv -> {
			    TableRow<TimeExtension> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	myTimeExtension = row.getItem();
			        	btnApproveTimeExtension.setVisible(true);
			        	btnDenyTimeExtension.setVisible(true);
			        	txtTextExplantion.setVisible(true);
			        }
			    });
			    return row ;
			});
 
		 tableChangeRequest.setRowFactory(tv -> {
			    TableRow<ChangeRequest> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	myChangerequest = row.getItem();
			        	if(myChangerequest.getCurrentStep().equals("ANALYZER_AUTO_APPOINT"))
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{	
			        		setVisabilityValse();
			        		txtTextChange.setVisible(true);
			            	txtTextChange.setText("Employee appointment table is ready to use");
			            	txtTextExplantion.setVisible(true);
			            	txtTextExplantion.setText("Please confirm or decline the auto-analyzer appointment");
			        		btnExstraDetails.setDisable(false);
			        		btnApproveAppointment.setVisible(true);
			        		btnDenyAppointment.setVisible(true);
			        		txtSystemAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setText(myChangerequest.getHandlerUserName());
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPEND"))
			        		{
			        		setVisabilityValse();
			        		btnExstraDetails.setDisable(false);
			        		
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("ANALYZER_SUPERVISOR_APPOINT"))
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{
			        			setVisabilityValse();
			        			txtTextChange.setVisible(true);
				            	txtTextChange.setText("Employee appointment table is ready to use");
				            	txtTextExplantion.setVisible(true);
				            	txtTextExplantion.setText("Please select an analyzer for the request");
			        			btnExstraDetails.setDisable(false);
			        			comboSelectAnalyizer.setVisible(true);
			        			btnSetAnalyzer.setVisible(true);
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPEND"))
			        		{
			        		setVisabilityValse();
			        		btnExstraDetails.setDisable(false);
			        		
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        		
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("EXECUTION_LEADER_SUPERVISOR_APPOINT"))
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{	
			        		setVisabilityValse();
			        		txtTextChange.setVisible(true);
			            	txtTextChange.setText("Employee appointment table is ready to use");
			            	txtTextExplantion.setVisible(true);
			            	txtTextExplantion.setText("Please select execution leader for the request");
			        		btnExstraDetails.setDisable(false);
			        		comboSelectExecutionLeader.setVisible(true);
			        		btnSetExecutionLeader.setVisible(true);	
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPEND"))
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        			
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("ANALYSIS_APPROVE_TIME"))
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{	
			        		setVisabilityValse();
			        		txtTextChange.setVisible(true);
			            	txtTextChange.setText("Approval and Rejection Table is ready to use");
			            	txtTextExplantion.setVisible(true);
			            	txtTextExplantion.setText("Please confirm or reject the time set by the analyzer");
			        		btnExstraDetails.setDisable(false);
			        		btnApproveAnalysisTime.setVisible(true);
			        		btnDenyAnalysisTime.setVisible(true);
			        		txtAnalysisEstimatedTime.setVisible(true);
			        		txtFieldEstimatedTime.setVisible(true);
			        		myController.getAnalysisEstimatedDate(myChangerequest.getChangeRequestID());
			        		
			        		
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPEND"))
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
				        
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        		
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("EXECUTION_APPROVE_TIME"))
			        	{
			        		
			   
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{	
			        		setVisabilityValse();
			        		txtTextChange.setVisible(true);
			            	txtTextChange.setText("Approval and Rejection Table is ready to use");
			            	txtTextExplantion.setVisible(true);
			            	txtTextExplantion.setText("Please confirm or reject the time set by the execution leader");
			        		btnExstraDetails.setDisable(false);
			        		btnApproveExecutionTime.setVisible(true);
			        		btnDenyExecutionTime.setVisible(true);
			        		txtExecutionEstimatedTime.setVisible(true);
			        		txtFieldEstimatedTime.setVisible(true);
			        		myController.getExecutionEstimatedDate(myChangerequest.getChangeRequestID());
			        		}
			        		else if (myChangerequest.getStatus().equals("SUSPEND"))
			        		{
			        			setVisabilityValse();
				        		btnExstraDetails.setDisable(false);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("CLOSING_STEP"))
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{
			        		setVisabilityValse();
			        		txtTextChange.setVisible(true);
			            	txtTextChange.setText("Request closing table is ready to use");
			            	txtTextExplantion.setVisible(true);
			        		btnExstraDetails.setDisable(false);
			        		btnSend.setVisible(true);
			        		txtSendMessageToInitiator.setVisible(true);
			        		txtTextExplantion.setText("Send an email to the request initiator and close the request");
			        		}
			        	}
			        	else if((myChangerequest.getCurrentStep().equals("DENY_STEP")))
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{
			        		setVisabilityValse();
			        		txtTextChange.setVisible(true);
			            	txtTextChange.setText("Request closing table is ready to use");
			            	txtTextExplantion.setVisible(true);
			            	txtTextExplantion.setText("Please close the request");
			        		btnExstraDetails.setDisable(false);
			        		btnSend.setVisible(true);
			        		txtSendMessageToInitiator.setVisible(true);
			        		txtTextExplantion.setText("Send an email to the request initiator and close the request");
			        		}
			        		
			        	}
			        	else
			        	{
			        		if(myChangerequest.getStatus().equals("ACTIVE"))
			        		{	
			        		setVisabilityValse();
			        		txtTextChange.setVisible(false);
			            	txtTextExplantion.setVisible(true);
			            	txtTextExplantion.setText("Request in one of the promotions process, you can view more details at this time");
			        		btnExstraDetails.setDisable(false);
			        		btnExstraDetails.setVisible(true);
			        		}
			        		else if (myChangerequest.getStatus().equals("SUSPEND"))
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setDisable(false);
			        		}
			        	}	
			        }
			    });
			    return row ;
			});	
	}
	
	
	/**
	 * Sets the combo box 2.
	 *
	 * @param employees This method set the employees into the combo box
	 */
	public void SetComboBox2(ArrayList<String> employees)
	{
		for(int i=0;i<employees.size();i++)
		{
			comboSelectAnalyizer.getItems().add(employees.get(i));
			comboSelectExecutionLeader.getItems().add(employees.get(i));
		}	
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
    	setVisabilityValse();
    	txtTextChange.setVisible(true);
    	txtTextChange.setText("Employee appointment table is ready to use");
    	txtTextExplantion.setVisible(true);
    	txtTextExplantion.setText("Please select a request you would like to process");
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
    	setVisabilityValse();
    	txtTextChange.setVisible(true);
    	txtTextChange.setText("Approval and Rejection Table is ready to use");
    	txtTextExplantion.setVisible(true);
    	txtTextExplantion.setText("Please select a request you would like to process");
    	myController.SelectAllChangeRequestForApprovals();
    }

    
    /**
     * Click back function.
     *
     * @param event This method handle click on back in menu
     */
    @FXML
    void ClickBackFunction(MouseEvent event)
    {
    	if(!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if(!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    
    /**
     * This method set combo box.
     */
    void setComboBoxUsers1()
    {
    	myController.setComboBox();
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
    	setVisabilityValse();
    	txtTextChange.setVisible(true);
    	txtTextChange.setText("Request closing table is ready to use");
    	txtTextExplantion.setVisible(true);
    	txtTextExplantion.setText("Please select a request you would like to process");
    	myController.SelectAllChangeRequestForClose();
    }
    
    /**
     * Click home page function.
     *
     * @param event This method handle click on home page in menu
     */
    @FXML
    void ClickHomePageFunction(MouseEvent event)       // Return to home page
    {
    	setVisabilityValse();
    	if(!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if(!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
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
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    /**
     * Click request list function.
     *
     * @param event This method handle click on request list in menu
     */
    @FXML
    void ClickRequestListFunction(MouseEvent event)
    {
    	tableChangeRequest.setVisible(true);
    	setVisabilityValse();
    	txtTextChange.setVisible(true);
    	txtTextChange.setText("All change requests are up to date and ready to use");
    	txtTextExplantion.setVisible(true);
    	txtTextExplantion.setText("Please select a request to work on, you can also use specific filters: Appointment,Approval or Closing Step");
    	myController.SelectAllChangeRequest();	
    }
   
/**
 * Handle changerequest result for table.
 *
 * @param resultList This method insert all change request into the table
 */
	public void handleChangerequestResultForTable(ArrayList<ChangeRequest> resultList)     // set all change requests in the table
	{
		requestList.clear();
		if (!resultList.isEmpty()) {
			requestList.addAll(resultList);
			tableChangeRequest.setItems(requestList);
		}
	}
	
	
	/**
	 * Handle time extension for table.
	 *
	 * @param resultList the result list
	 */
	public void handleTimeExtensionForTable(ArrayList<TimeExtension> resultList)
	{
		extensionList.clear();
		if (!resultList.isEmpty()) {
			extensionList.addAll(resultList);
			tableTimeExtension.setItems(extensionList);
		}
	}
	
	
	 /**
 	 * Click on exstra details.
 	 *
 	 * @param event This method handle click on extra details
 	 */
    @FXML
    void clickOnExstraDetails(MouseEvent event)
    {
    	//ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),myChangerequest);	
    	if(myChangerequest == null) {
    		Toast.makeText(ProjectFX.mainStage, "Please select request from the table", 1500, 500, 500);
    	} else {
    		ArrayList<Object> dataList = new ArrayList<>();
        	dataList.add(myChangerequest);
        	dataList.add(ProjectPages.SUPERVISOR_PAGE.getPath());
        	ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),dataList);
    	}	
    }
    
    
    /**
     * Click on select analyizer.
     *
     * @param event the event
     */
    @FXML
    void clickOnSelectAnalyizer(MouseEvent event)
    {
    	
    	
    	
    }
    
    /**
     * Click on approve appointment.
     *
     * @param event This method update DB when click on approve appointment of analyzer
     */
    @FXML
    void clickOnApproveAppointment(MouseEvent event)
    {
    	myController.changeCurrentStepToAnalysisSetTime(myChangerequest.getChangeRequestID());
    	myController.InsertNewAnalysisStepAfterApprove(myChangerequest.getChangeRequestID(),myChangerequest.getHandlerUserName(),updateStepDate,"ACTIVE");
    	myController.SelectChangeRequestForAppointments();
    	txtTextExplantion.setVisible(false);
    	txtSystemAutoAppoint.setVisible(false);
    	txtHandlerNameAutoAppoint.setVisible(false);
    	btnApproveAppointment.setVisible(false);
    	btnDenyAppointment.setVisible(false);
    	btnSetAnalyzer.setVisible(false);
    	btnSetExecutionLeader.setVisible(false);
    	btnApproveExecutionTime.setVisible(false);
    	btnDenyExecutionTime.setVisible(false);
    	btnApproveAnalysisTime.setVisible(false);
    	btnDenyAnalysisTime.setVisible(false);
    	comboSelectAnalyizer.setVisible(false);
    	comboSelectExecutionLeader.setVisible(false);
    	txtExecutionEstimatedTime.setVisible(false);
    	txtFieldEstimatedTime.setVisible(false);
    	tableTimeExtension.setVisible(false);
		 btnApproveTimeExtension.setVisible(false);
		 btnDenyTimeExtension.setVisible(false);	
    }

    /**
     * Click on deny appointment.
     *
     * @param event This method update DB when click on deny appointment of analyzer
     */
    @FXML
    void clickOnDenyAppointment(MouseEvent event)
    {
    	myController.changeCurrentStepFromAnalyzerAutoAppoint(myChangerequest.getChangeRequestID());
    	txtTextExplantion.setVisible(true);
    	txtTextExplantion.setText("Please select an analyzer for the request");
    	btnApproveAppointment.setVisible(false);
    	btnDenyAppointment.setVisible(false);
    	txtSystemAutoAppoint.setVisible(false);
    	txtHandlerNameAutoAppoint.setVisible(false);
    	comboSelectAnalyizer.setVisible(true);
    	btnSetAnalyzer.setVisible(true);   
    	myController.SelectChangeRequestForAppointments();	
    }

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
    		myController.UpdateNewAnalyzerBySupervisor(comboSelectAnalyizer.getSelectionModel().getSelectedItem()
    				,myChangerequest.getChangeRequestID());
    		myController.InsertNewAnalysisStep(myChangerequest.getChangeRequestID()
    				,comboSelectAnalyizer.getSelectionModel().getSelectedItem(),updateStepDate,"ACTIVE");
    		myController.SelectChangeRequestForAppointments();
    		txtTextExplantion.setVisible(false);
    		txtSystemAutoAppoint.setVisible(false);
        	txtHandlerNameAutoAppoint.setVisible(false);
        	btnApproveAppointment.setVisible(false);
        	btnDenyAppointment.setVisible(false);
        	btnSetAnalyzer.setVisible(false);
        	btnSetExecutionLeader.setVisible(false);
        	btnApproveExecutionTime.setVisible(false);
        	btnDenyExecutionTime.setVisible(false);
        	btnApproveAnalysisTime.setVisible(false);
        	btnDenyAnalysisTime.setVisible(false);
        	comboSelectAnalyizer.setVisible(false);
        	comboSelectExecutionLeader.setVisible(false);
        	txtExecutionEstimatedTime.setVisible(false);
        	txtFieldEstimatedTime.setVisible(false);
        	 tableTimeExtension.setVisible(false);
    		 btnApproveTimeExtension.setVisible(false);
    		 btnDenyTimeExtension.setVisible(false);
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
    	if(txtSendMessageToInitiator.getText().equals("")) {
    		Toast.makeText(ProjectFX.mainStage, "Please write a message first", 1500, 500, 500);
    	}else {
    		txtTextExplantion.setText("");
    		txtSendMessageToInitiator.setVisible(false);
    		btnSend.setVisible(false);
        	myController.getUserEmail(myChangerequest.getInitiatorUserName());
        	myController.setStatusToClosed(updateStepDate,"CLOSED","FINISH",myChangerequest.getChangeRequestID());
        	myController.setEndDate(updateStepDate,"CLOSED",myChangerequest.getChangeRequestID());
        	myController.SelectAllChangeRequestForClose();
    	}
    }
    
//    /**
//     * Click on close request.
//     *
//     * @param event This method update DB when click on close request
//     */
//    @FXML
//    void clickOnCloseRequest(MouseEvent event)
//    {
//    	myController.setStatusToClosed(updateStepDate,"CLOSED","FINISH",myChangerequest.getChangeRequestID());
//    	myController.setEndDate(updateStepDate,"CLOSED",myChangerequest.getChangeRequestID());
//    	myController.SelectAllChangeRequestForClose();
//    	txtTextExplantion.setVisible(false);	
//    }
    
    /**
     * Click on select execution leader.
     *
     * @param event the event
     */
    @FXML
    void clickOnSelectExecutionLeader(MouseEvent event)
    { 	
    }

	
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
			  myController.UpdateExecutionLeaderBySupervisor(comboSelectExecutionLeader.getSelectionModel().getSelectedItem()
  				,"EXECUTION_SET_TIME",myChangerequest.getChangeRequestID());
			  myController.InsertNewExecutionLeaderStep(myChangerequest.getChangeRequestID()
				,comboSelectExecutionLeader.getSelectionModel().getSelectedItem(),updateStepDate,"ACTIVE");
			  myController.SelectChangeRequestForAppointments();
				txtTextExplantion.setVisible(false);
				txtSystemAutoAppoint.setVisible(false);
		    	txtHandlerNameAutoAppoint.setVisible(false);
		    	btnApproveAppointment.setVisible(false);
		    	btnDenyAppointment.setVisible(false);
		    	btnSetAnalyzer.setVisible(false);
		    	btnSetExecutionLeader.setVisible(false);
		    	btnApproveExecutionTime.setVisible(false);
		    	btnDenyExecutionTime.setVisible(false);
		    	btnApproveAnalysisTime.setVisible(false);
		    	btnDenyAnalysisTime.setVisible(false);
		    	comboSelectAnalyizer.setVisible(false);
		    	comboSelectExecutionLeader.setVisible(false);
		    	txtExecutionEstimatedTime.setVisible(false);
		    	txtFieldEstimatedTime.setVisible(false);
		    	 tableTimeExtension.setVisible(false);
				 btnApproveTimeExtension.setVisible(false);
				 btnDenyTimeExtension.setVisible(false);	
		  }	  
	}

	/**
	 * Click on deny analysis time.
	 *
	 * @param event This method update DB when click on deny execution leader
	 */
    @FXML
    void clickOnDenyAnalysisTime(MouseEvent event)
    {
    	myController.denyAnalysisTime("ANALYSIS_SET_TIME",myChangerequest.getChangeRequestID());
    	myController.SelectAllChangeRequestForApprovals();
    	txtTextExplantion.setVisible(false);
    	txtSystemAutoAppoint.setVisible(false);
    	txtHandlerNameAutoAppoint.setVisible(false);
    	btnApproveAppointment.setVisible(false);
    	btnDenyAppointment.setVisible(false);
    	btnSetAnalyzer.setVisible(false);
    	btnSetExecutionLeader.setVisible(false);
    	btnApproveExecutionTime.setVisible(false);
    	btnDenyExecutionTime.setVisible(false);
    	btnApproveAnalysisTime.setVisible(false);
    	btnDenyAnalysisTime.setVisible(false);
    	comboSelectAnalyizer.setVisible(false);
    	comboSelectExecutionLeader.setVisible(false);
    	txtExecutionEstimatedTime.setVisible(false);
    	txtFieldEstimatedTime.setVisible(false);
    	 tableTimeExtension.setVisible(false);
		 btnApproveTimeExtension.setVisible(false);
		 btnDenyTimeExtension.setVisible(false);	
    }
    
    /**
     * Click on approve analysis time.
     *
     * @param event This method update DB when click on approve analysis time
     */
    @FXML
    void clickOnApproveAnalysisTime(MouseEvent event)
    {
    	myController.approvedAnalysisTime("ANALYSIS_WORK",myChangerequest.getChangeRequestID());
    	myController.SelectAllChangeRequestForApprovals();
    	txtTextExplantion.setVisible(false);
    	txtSystemAutoAppoint.setVisible(false);
    	txtHandlerNameAutoAppoint.setVisible(false);
    	btnApproveAppointment.setVisible(false);
    	btnDenyAppointment.setVisible(false);
    	btnSetAnalyzer.setVisible(false);
    	btnSetExecutionLeader.setVisible(false);
    	btnApproveExecutionTime.setVisible(false);
    	btnDenyExecutionTime.setVisible(false);
    	btnApproveAnalysisTime.setVisible(false);
    	btnDenyAnalysisTime.setVisible(false);
    	comboSelectAnalyizer.setVisible(false);
    	comboSelectExecutionLeader.setVisible(false);
    	txtExecutionEstimatedTime.setVisible(false);
    	txtFieldEstimatedTime.setVisible(false);
    	txtAnalysisEstimatedTime.setVisible(false);
    	tableTimeExtension.setVisible(false);
		 btnApproveTimeExtension.setVisible(false);
		 btnDenyTimeExtension.setVisible(false);
    }
	
	
    /**
     * Click on approve execution time.
     *
     * @param event This method update DB when click on approve execution time
     */
    @FXML
    void clickOnApproveExecutionTime(MouseEvent event)
    {
    	myController.approvedExecutionTime("EXECUTION_WORK",myChangerequest.getChangeRequestID());
    	myController.SelectAllChangeRequestForApprovals();
    	txtTextExplantion.setVisible(false);
    	txtSystemAutoAppoint.setVisible(false);
    	txtHandlerNameAutoAppoint.setVisible(false);
    	btnApproveAppointment.setVisible(false);
    	btnDenyAppointment.setVisible(false);
    	btnSetAnalyzer.setVisible(false);
    	btnSetExecutionLeader.setVisible(false);
    	btnApproveExecutionTime.setVisible(false);
    	btnDenyExecutionTime.setVisible(false);
    	btnApproveAnalysisTime.setVisible(false);
    	btnDenyAnalysisTime.setVisible(false);
    	comboSelectAnalyizer.setVisible(false);
    	comboSelectExecutionLeader.setVisible(false);
    	txtExecutionEstimatedTime.setVisible(false);
    	txtFieldEstimatedTime.setVisible(false);
    	 tableTimeExtension.setVisible(false);
		 btnApproveTimeExtension.setVisible(false);
		 btnDenyTimeExtension.setVisible(false);
    }
    
    /**
     * Click on deny execution time.
     *
     * @param event This method update DB when click on deny execution time
     */
    @FXML
    void clickOnDenyExecutionTime(MouseEvent event)
    {
    	myController.denyExecutionTime("EXECUTION_SET_TIME",myChangerequest.getChangeRequestID());
    	myController.SelectAllChangeRequestForApprovals();
    	txtTextExplantion.setVisible(false);
    	txtSystemAutoAppoint.setVisible(false);
    	txtHandlerNameAutoAppoint.setVisible(false);
    	btnApproveAppointment.setVisible(false);
    	btnDenyAppointment.setVisible(false);
    	btnSetAnalyzer.setVisible(false);
    	btnSetExecutionLeader.setVisible(false);
    	btnApproveExecutionTime.setVisible(false);
    	btnDenyExecutionTime.setVisible(false);
    	btnApproveAnalysisTime.setVisible(false);
    	btnDenyAnalysisTime.setVisible(false);
    	comboSelectAnalyizer.setVisible(false);
    	comboSelectExecutionLeader.setVisible(false);
    	txtExecutionEstimatedTime.setVisible(false);
    	txtFieldEstimatedTime.setVisible(false);
    	tableTimeExtension.setVisible(false);
		btnApproveTimeExtension.setVisible(false);
		btnDenyTimeExtension.setVisible(false);
    }  
    
    /**
     * Send email to initiator user.
     *
     * @param initiator the initiator
     */
    public void sendEmailToInitiatorUser(User initiator) {
    	email.sendMessage(initiator.getEmail(), "Closed Request", 
    			MessagesCreator.supervisorCloseChangeRequest(initiator.getFullName(),
    					txtSendMessageToInitiator.getText()));
    	txtSendMessageToInitiator.setText("");
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
		txtFieldEstimatedTime.setText(res.toString());
	}

	/**
	 * Gets the analysis end date.
	 *
	 * @param res2 This method get analysis end date
	 * @return the analysis end date
	 */
	public void getAnalysisEndDate(Date res2)
	{
		res2 = TimeManager.addDays(res2, 1);
		txtFieldEstimatedTime.setText(res2.toString());	
	}
	
    /**
     * Click on time extension.
     *
     * @param event the event
     */
    @FXML
    void clickOnTimeExtension(MouseEvent event)
    {
    	myController.SelectAllTimeExtensions();
    	setVisabilityValse();
    	tableChangeRequest.setVisible(false);
    	tableTimeExtension.setVisible(true);
    	tableTimeExtension.setLayoutX(159);
    	tableTimeExtension.setLayoutY(91);
    	btnApproveTimeExtension.setVisible(false);
    	btnDenyTimeExtension.setVisible(false);
    	txtTextExplantion.setText("Please select a request to approve or reject an extension");	
    	txtTextExplantion.setVisible(true);
    }
    
    /**
     * Click on approve time extension.
     *
     * @param event the event
     */
    @FXML
    void clickOnApproveTimeExtension(MouseEvent event)
    {
    	if(myTimeExtension==null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please select requset for time extension", 1500, 500, 500);
    	}
    	else
    	{
    		String stepType = myTimeExtension.getStepType().getStepName();
    		if(stepType.equals("Analysis"))
    		{
    			myController.updateTimeExtensionStatus("APPROVED",myTimeExtension.getStepID());
    			myController.updateAnalysisStepEstimatedEndDate(myTimeExtension.getNewDate(),myTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Committee"))
    		{
    			myController.updateTimeExtensionStatus("APPROVED",myTimeExtension.getStepID());
    			myController.updateCommitteeStepEstimatedEndDate(myTimeExtension.getNewDate(),myTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Execution"))
    		{
    			myController.updateTimeExtensionStatus("APPROVED",myTimeExtension.getStepID());
    			myController.updateExecutionStepEstimatedEndDate(myTimeExtension.getNewDate(),myTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Testing"))
    		{
    			myController.updateTimeExtensionStatus("APPROVED",myTimeExtension.getStepID());
    			myController.updateTestingStepEstimatedEndDate(myTimeExtension.getNewDate(),myTimeExtension.getStepID());
    		}
    		myController.SelectAllTimeExtensions();
    		btnApproveTimeExtension.setVisible(false);
    		btnDenyTimeExtension.setVisible(false);
    		txtTextExplantion.setVisible(false);	
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
    	if(myTimeExtension==null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please select requset for time extension", 1500, 500, 500);
    	}
    	else
    	{
    		String stepType = myTimeExtension.getStepType().getStepName();
    		if(stepType.equals("Analysis"))
    		{
    			myController.updateTimeExtensionStatusAfterDeny("DENY",myTimeExtension.getStepID());
    			myController.updateAnalysisStepEstimatedEndDate(myTimeExtension.getOldDate(),myTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Committee"))
    		{
    			myController.updateTimeExtensionStatusAfterDeny("DENY",myTimeExtension.getStepID());
    			myController.updateCommitteeStepEstimatedEndDate(myTimeExtension.getOldDate(),myTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Execution"))
    		{
    			myController.updateTimeExtensionStatusAfterDeny("DENY",myTimeExtension.getStepID());
    			myController.updateExecutionStepEstimatedEndDate(myTimeExtension.getOldDate(),myTimeExtension.getStepID());
    		}
    		else if(stepType.equals("Testing"))
    		{
    			myController.updateTimeExtensionStatusAfterDeny("DENY",myTimeExtension.getStepID());
    			myController.updateTestingStepEstimatedEndDate(myTimeExtension.getOldDate(),myTimeExtension.getStepID());
    		}
    		myController.SelectAllTimeExtensions();
    		btnApproveTimeExtension.setVisible(false);
    		btnDenyTimeExtension.setVisible(false);
    		txtTextExplantion.setVisible(false);
    	}
    }
     
    /**
     * This method set all gui into invisible.
     */
    public void setVisabilityValse()
	{
    	 txtAnalysisEstimatedTime.setVisible(false);
    	 txtExecutionEstimatedTime.setVisible(false);
    	 txtFieldEstimatedTime.setVisible(false);
		 comboSelectAnalyizer.setVisible(false);
		 comboSelectExecutionLeader.setVisible(false);
		 btnApproveAppointment.setVisible(false);
		 btnDenyAppointment.setVisible(false);
		 btnExstraDetails.setVisible(true);
		 btnExstraDetails.setDisable(true);
		 txtSystemAutoAppoint.setVisible(false);
		 txtHandlerNameAutoAppoint.setVisible(false);
		 btnSetAnalyzer.setVisible(false);
		 btnSetExecutionLeader.setVisible(false);
		 btnSend.setVisible(false);
		 txtSendMessageToInitiator.setVisible(false);
		 btnApproveAnalysisTime.setVisible(false);
		 btnDenyAnalysisTime.setVisible(false);
		 btnApproveExecutionTime.setVisible(false);
		 btnDenyExecutionTime.setVisible(false);
		 txtTextChange.setVisible(false);
		 txtTextExplantion.setVisible(false);
		 tableTimeExtension.setVisible(false);
		 btnApproveTimeExtension.setVisible(false);
		 btnDenyTimeExtension.setVisible(false);
	}
    
    //**********************************TOASTS*************************************************//
    
    
    
	/**
     * Show appoint execution leader success.
     */
    public void ShowAppointExecutionLeaderSuccess()
	{
		Toast.makeText(ProjectFX.mainStage, "Execution Leader Appointment success", 1500, 500, 500);
	}
	
	/**
	 * Show success aprove appoint.
	 */
	public void ShowSuccessAproveAppoint()
	{
		Toast.makeText(ProjectFX.mainStage, "Approving Analyzer successfuly", 1500, 500, 500);
	}
    
    /**
     * Show deny analysis time.
     */
    public void showDenyAnalysisTime()
    {
    	Toast.makeText(ProjectFX.mainStage, "Deny Analysis time approved", 1500, 500, 500);
	}
    
    /**
     * Show approve analysis time.
     */
    public void showApproveAnalysisTime()
    {	
    	Toast.makeText(ProjectFX.mainStage, "Analysis time approved", 1500, 500, 500);	
	}
    
    /**
     * Show approve execution time.
     */
    public void showApproveExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution time approved", 1500, 500, 500);
	}
    
    /**
     * Show deny execution time.
     */
    public void showDenyExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution time Deny", 1500, 500, 500);
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
		Toast.makeText(ProjectFX.mainStage,"Change Request is Closed", 1500, 500, 500);
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
			Toast.makeText(ProjectFX.mainStage, "Problam in update current step", 1500, 500, 500);
	}

	/**
	 * Show success analyzer appoint.
	 *
	 * @param affectedRows2 the affected rows 2
	 */
	public void ShowSuccessAnalyzerAppoint(int affectedRows2)
	{
		if(affectedRows2==1)
			Toast.makeText(ProjectFX.mainStage, "Your Analyzer Appoint Approved", 1500, 500, 500);
		else
			Toast.makeText(ProjectFX.mainStage, "Analyzer Appoint did not success", 1500, 500, 500);	
	}

	/**
	 * Show approve time extension.
	 */
	public void showApproveTimeExtension()
	{
		Toast.makeText(ProjectFX.mainStage, "Your time extension approved", 1500, 500, 500);
	}

	/**
	 * Show deny time extension.
	 */
	public void showDenyTimeExtension()
	{
		Toast.makeText(ProjectFX.mainStage, "Your time extension denied", 1500, 500, 500);
	}
}