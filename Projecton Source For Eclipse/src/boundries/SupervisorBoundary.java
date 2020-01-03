package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.SupervisorController;
import entities.ChangeRequest;
import entities.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SupervisorBoundary implements Initializable,DataInitializable {

    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnRequestList;

    @FXML
    private Button btnAppointment;

    @FXML
    private Button btnApproval;

    @FXML
    private Button btnClosingStep;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnExstraDetails;
    
    
    @FXML
    private Button btnSetAnalyzer;
    
    
    @FXML
    private Button btnApproveAnalysisTime;
    

    @FXML
    private Button btnDenyAnalysisTime;
    
    
    
    
    @FXML
    private ComboBox<String> comboSelectAnalyizer;
   
    @FXML
    private ComboBox<String> comboSelectExecutionLeader;
    
    
    @FXML
    private Button btnSetExecutionLeader;

    @FXML
    private Button btnApproveAppointment;

    @FXML
    private Button btnDenyAppointment;
    
    @FXML
    private Button btnCloseTheRequest;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtSendMessageToInitiator;
    
    @FXML
    private Text txtSystemAutoAppoint;

    @FXML
    private TextField txtHandlerNameAutoAppoint;
    
    @FXML
    private Button btnDenyExecutionTime;

    @FXML
    private Button btnApproveExecutionTime;
    
    
    @FXML
    private Button btnSuspend;
    

    @FXML
    private Button btnUnsuspend;
    
    @FXML
    private TextField txtFieldEstimatedTime;

    @FXML
    private Text txtExecutionEstimatedTime;

    @FXML
    private Text txtAnalysisEstimatedTime;

    
    
    
    
    @FXML
    private TableView<ChangeRequest> tableChangeRequest;

    @FXML
    private TableColumn<ChangeRequest, Integer> tableColumnRequestID;

    @FXML
    private TableColumn<ChangeRequest, String> tableColumnStatus;

    @FXML
    private TableColumn<ChangeRequest, String> tableColumnDescription;

    @FXML
    private TableColumn<ChangeRequest, String> tableColumnSubSystem;

    @FXML
    private TableColumn<ChangeRequest, String> tableColumnCurrentStep;
    
    
    
    
    // Vars //
    
    private SupervisorController myController = new SupervisorController(this);
    private ChangeRequest myChangerequest;
    java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
    private Step SupervisorStep;
    Stage myTimeExtensionStage = null;
	Stage myAnalysisReportStage= null;
    
    
    
    
    
	
	
	@Override
	public void initData(Object data)
	{
		
		
	}

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		 tableColumnRequestID.setCellValueFactory(new PropertyValueFactory<ChangeRequest,Integer>("changeRequestID"));
		 tableColumnCurrentStep.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("currentStep"));
		 tableColumnDescription.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("currentStateDescription"));
		 tableColumnStatus.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("status"));
		 tableColumnSubSystem.setCellValueFactory(new PropertyValueFactory<ChangeRequest,String>("selectedSubsystem"));
		 
		 setVisabilityValse();
		 
		 myController.SelectAllChangeRequest();
		 
		 setComboBoxUsers1();
		 
		 tableChangeRequest.setRowFactory(tv -> {
			    TableRow<ChangeRequest> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	myChangerequest = row.getItem();
			        	if(myChangerequest.getCurrentStep().equals("ANALAYZER_AUTO_APPOINT"))
			        	{
			        		if(myChangerequest.getStatus().equals("Active"))
			        		{	
			        		setVisabilityValse();
			        		btnSuspend.setVisible(true);
			        		btnExstraDetails.setVisible(true);
			        		btnApproveAppointment.setVisible(true);
			        		btnDenyAppointment.setVisible(true);
			        		txtSystemAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setText(myChangerequest.getHandlerUserName());
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPENDED"))
			        		{
			        		setVisabilityValse();
			        		btnExstraDetails.setVisible(true);
			        		btnUnsuspend.setVisible(true);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setVisible(true);
			        		}
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("EXECUTION_LEADEAR_SUPERVISOR_APPOINT"))
			        	{
			        		if(myChangerequest.getStatus().equals("Active"))
			        		{	
			        		setVisabilityValse();
			        		btnSuspend.setVisible(true);
			        		btnExstraDetails.setVisible(true);
			        		comboSelectExecutionLeader.setVisible(true);
			        		btnSetExecutionLeader.setVisible(true);	
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPENDED"))
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setVisible(true);
			        			btnUnsuspend.setVisible(true);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setVisible(true);
			        		}
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("ANALYSIS_APPROVE_TIME"))
			        	{
			        		if(myChangerequest.getStatus().equals("Active"))
			        		{	
			        		setVisabilityValse();
			        		btnExstraDetails.setVisible(true);
			        		btnSuspend.setVisible(true);
			        		btnApproveAnalysisTime.setVisible(true);
			        		btnDenyAnalysisTime.setVisible(true);
			        		txtAnalysisEstimatedTime.setVisible(true);
			        		txtFieldEstimatedTime.setVisible(true);
			        		myController.getAnalysisEstimatedDate(myChangerequest.getChangeRequestID());
			        		
			        		
			        		}
			        		else if(myChangerequest.getStatus().equals("SUSPENDED"))
			        		{
			        			setVisabilityValse();
				        		btnExstraDetails.setVisible(true);
				        		btnUnsuspend.setVisible(true);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
				        		btnExstraDetails.setVisible(true);
			        		}
			        		
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("EXECUTION_APPROVE_TIME"))
			        	{
			        		
			   
			        		if(myChangerequest.getStatus().equals("Active"))
			        		{	
			        		setVisabilityValse();
			        		btnSuspend.setVisible(true);
			        		btnExstraDetails.setVisible(true);
			        		btnApproveExecutionTime.setVisible(true);
			        		btnDenyExecutionTime.setVisible(true);
			        		txtExecutionEstimatedTime.setVisible(true);
			        		txtFieldEstimatedTime.setVisible(true);
			        		myController.getExecutionEstimatedDate(myChangerequest.getChangeRequestID());
			        		
			        		
			        		}
			        		else if (myChangerequest.getStatus().equals("SUSPENDED"))
			        		{
			        			setVisabilityValse();
				        		btnUnsuspend.setVisible(true);
				        		btnExstraDetails.setVisible(true);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setVisible(true);
			        		}
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("CLOSING_STEP"))
			        	{
			        		setVisabilityValse();
			        		btnExstraDetails.setVisible(true);
			        		btnSend.setVisible(true);
			        		txtSendMessageToInitiator.setVisible(true);
			        		btnCloseTheRequest.setVisible(true);
			        	}
			        	else
			        	{
			        		if(myChangerequest.getStatus().equals("Active"))
			        		{	
			        		setVisabilityValse();
			        		btnSuspend.setVisible(true);
			        		btnExstraDetails.setVisible(true);
			        		}
			        		else if (myChangerequest.getStatus().equals("SUSPENDED"))
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setVisible(true);
			        			btnUnsuspend.setVisible(true);
			        		}
			        		else
			        		{
			        			setVisabilityValse();
			        			btnExstraDetails.setVisible(true);
			        		}
			        	}
			        	
			        	
			        }
			    });
			    return row ;
			});
		 
		 
		 
		
		 
		
	}
	
	
	
	public void SetComboBox2(ArrayList<String> employees)
	{
		for(int i=0;i<employees.size();i++)
		{
			comboSelectAnalyizer.getItems().add(employees.get(i));
			comboSelectExecutionLeader.getItems().add(employees.get(i));
		}
			
	}

	
	
    @FXML
    void ClickAppointmentFunction(MouseEvent event) 
    {
    	setVisabilityValse();
    	myController.SelectChangeRequestForAppointments();
    		
    }

    
    
    @FXML
    void ClickApprovalFunction(MouseEvent event)
    {
    	setVisabilityValse();
    	myController.SelectAllChangeRequestForApprovals();
    	
    }

    
    
    @FXML
    void ClickBackFunction(MouseEvent event)
    {
    	
    	if(!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if(!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    	
    }
    
    void setComboBoxUsers1()
    {
    	myController.setComboBox();
    }
    

    
    
    @FXML
    void ClickClosingStepFunction(MouseEvent event)
    {
    	setVisabilityValse();
    	myController.SelectAllChangeRequestForClose();
    	
    }
    
    

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
    
    

    
    
    @FXML
    void ClickRequestListFunction(MouseEvent event)
    {
    	setVisabilityValse();
    	myController.SelectAllChangeRequest();
    		
    }


    
    

	public void handleChangerequestResultForTable(ArrayList<ChangeRequest> resultList)     // set all change requests in the table
	{
		
		requestList.clear();
		if (!resultList.isEmpty()) {
			requestList.addAll(resultList);
			tableChangeRequest.setItems(requestList);
		}
		
		
	}
	
	

    @FXML
    void clickOnExstraDetails(MouseEvent event)
    {
    	
    	
    	ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),myChangerequest);
    	
    	
    }
    
    
    @FXML
    void clickOnSelectAnalyizer(MouseEvent event)
    {
    	
    	
    	
    }
    
    
    @FXML
    void clickOnApproveAppointment(MouseEvent event)
    {
    	myController.changeCurrentStepToAnalysisSetTime(myChangerequest.getChangeRequestID());
    	myController.InsertNewAnalysisStepAfterApprove(myChangerequest.getChangeRequestID(),myChangerequest.getHandlerUserName(),updateStepDate,"ACTIVE");
    	
    }

    
    @FXML
    void clickOnDenyAppointment(MouseEvent event)
    {
    	myController.changeCurrentStepFromAnalyzerAutoAppoint(myChangerequest.getChangeRequestID());
    	comboSelectAnalyizer.setVisible(true);
    	btnSetAnalyzer.setVisible(true);    	
    }
    
    
    
    
    

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
    	}
    	
    }
    
    
    

    @FXML
    void clickOnSend(MouseEvent event)
    {

    	
    }
    
    
    

    @FXML
    void clickOnCloseRequest(MouseEvent event)
    {

    	myController.setStatusToClosed("CLOSED",myChangerequest.getChangeRequestID());
    	
    }
    
    
    
    @FXML
    void clickOnSelectExecutionLeader(MouseEvent event)
    {

    	
    	
    }







	
	
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
		  }
		  
	}




	

    @FXML
    void clickOnDenyAnalysisTime(MouseEvent event)
    {
    	myController.denyAnalysisTime("ANALYSIS_SET_TIME",myChangerequest.getChangeRequestID());
    	
    }
    
    
    @FXML
    void clickOnApproveAnalysisTime(MouseEvent event)
    {
    	myController.approvedAnalysisTime("ANALYSIS_WORK",myChangerequest.getChangeRequestID());
    }
	
	

    @FXML
    void clickOnApproveExecutionTime(MouseEvent event)
    {
    	myController.approvedExecutionTime("EXECUTION_WORK",myChangerequest.getChangeRequestID());
    	
    }
    
    @FXML
    void clickOnDenyExecutionTime(MouseEvent event)
    {
    	myController.denyExecutionTime("EXECUTION_SET_TIME",myChangerequest.getChangeRequestID());
    	
    }  
    
    
    
	public void getExecutionEndDate(Date res)
	{
		
		txtFieldEstimatedTime.setText(res.toString());
		
	}



	public void getAnalysisEndDate(Date res2)
	{
		
		txtFieldEstimatedTime.setText(res2.toString());
		
	}
    
    
    
    
    public void setVisabilityValse()
	{
    	 txtAnalysisEstimatedTime.setVisible(false);
    	 txtExecutionEstimatedTime.setVisible(false);
    	 txtFieldEstimatedTime.setVisible(false);
		 btnSuspend.setVisible(false);
		 btnUnsuspend.setVisible(false);
		 comboSelectAnalyizer.setVisible(false);
		 comboSelectExecutionLeader.setVisible(false);
		 btnApproveAppointment.setVisible(false);
		 btnDenyAppointment.setVisible(false);
		 btnExstraDetails.setVisible(false);
		 txtSystemAutoAppoint.setVisible(false);
		 txtHandlerNameAutoAppoint.setVisible(false);
		 btnSetAnalyzer.setVisible(false);
		 btnSetExecutionLeader.setVisible(false);
		 btnSend.setVisible(false);
		 txtSendMessageToInitiator.setVisible(false);
		 btnCloseTheRequest.setVisible(false);
		 btnApproveAnalysisTime.setVisible(false);
		 btnDenyAnalysisTime.setVisible(false);
		 btnApproveExecutionTime.setVisible(false);
		 btnDenyExecutionTime.setVisible(false);
	}
    
    //**********************************TOASTS*************************************************//
    
    
    
	public void ShowAppointExecutionLeaderSuccess()
	{
		Toast.makeText(ProjectFX.mainStage, "Execution Leader Appointment success", 1500, 500, 500);
	}
	
    
	
	public void ShowSuccessAproveAppoint()
	{
		Toast.makeText(ProjectFX.mainStage, "Approving Analyzer successfuly", 1500, 500, 500);
		
	}
    
    public void showDenyAnalysisTime()
    {
    	Toast.makeText(ProjectFX.mainStage, "Deny Analysis time approved", 1500, 500, 500);
		
	}
    
    public void showApproveAnalysisTime()
    {
    	
    	Toast.makeText(ProjectFX.mainStage, "Analysis time approved", 1500, 500, 500);
		
	}
    
    public void showApproveExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution time approved", 1500, 500, 500);
		
	}
    
    public void showDenyExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution time Deny", 1500, 500, 500);
		
	}
    
    
    @FXML
    void clickOnSuspend(MouseEvent event)
    {
    	myController.suspendChangeRequest("SUSPENDED",myChangerequest.getChangeRequestID());
    	
    }
    
    

    @FXML
    void clickOnUnsuspend(MouseEvent event)
    {

    	myController.unsuspendChangeRequest("Active",myChangerequest.getChangeRequestID());
    }
    
    
	public void showChangeRequestSuspended()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request is Suspended", 1500, 500, 500);
		
	}



	public void showChangeRequestUnsuspended()
	{
		Toast.makeText(ProjectFX.mainStage,"Change Request is Active", 1500, 500, 500);
		
	}
	
	
	
	public void showChangeRequestClosed()
	{

		Toast.makeText(ProjectFX.mainStage,"Change Request is Closed", 1500, 500, 500);
		
	}

	
	
	public void ShowAnalyzerSupervisorAppointToast(int affectedRows)
	{
		if(affectedRows==1)
		Toast.makeText(ProjectFX.mainStage, "Please Set an Analyzer", 1500, 500, 500);
		else
			Toast.makeText(ProjectFX.mainStage, "Problam in update current step", 1500, 500, 500);
	}



	public void ShowSuccessAnalyzerAppoint(int affectedRows2)
	{
		if(affectedRows2==1)
			Toast.makeText(ProjectFX.mainStage, "Your Analyzer Appoint Approved", 1500, 500, 500);
		else
			Toast.makeText(ProjectFX.mainStage, "Analyzer Appoint did not success", 1500, 500, 500);	
	}









	
	
	
	










	



	



	



	



}