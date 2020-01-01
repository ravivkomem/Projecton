package boundries;

import java.net.URL;
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
		 
		 
		 comboSelectAnalyizer.getItems().add("itay");
		 comboSelectAnalyizer.getItems().add("itayz");
		 comboSelectAnalyizer.getItems().add("ido");
		 comboSelectAnalyizer.getItems().add("raviv");
		 comboSelectAnalyizer.getItems().add("lee");
		 comboSelectAnalyizer.getItems().add("lior");
		 
		 comboSelectExecutionLeader.getItems().add("itay");
		 comboSelectExecutionLeader.getItems().add("itayz");
		 comboSelectExecutionLeader.getItems().add("ido");
		 comboSelectExecutionLeader.getItems().add("raviv");
		 comboSelectExecutionLeader.getItems().add("lee");
		 comboSelectExecutionLeader.getItems().add("lior");
		 
		 
		 tableChangeRequest.setRowFactory(tv -> {
			    TableRow<ChangeRequest> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	myChangerequest = row.getItem();
			        	if(myChangerequest.getCurrentStep().equals("ANALAYZER_AUTO_APPOINT"))
			        	{
			        		btnExstraDetails.setVisible(true);
			        		btnApproveAppointment.setVisible(true);
			        		btnDenyAppointment.setVisible(true);
			        		txtSystemAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setText(myChangerequest.getHandlerUserName());
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("EXECUTION_LEADEAR_SUPERVISOR_APPOINT"))
			        	{
			        		btnExstraDetails.setVisible(true);
			        		comboSelectExecutionLeader.setVisible(true);
			        		btnSetExecutionLeader.setVisible(true);	
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("ANALYSIS_APPROVE_TIME"))
			        	{
			        		btnExstraDetails.setVisible(true);
			        		btnApproveAnalysisTime.setVisible(true);
			        		btnDenyAnalysisTime.setVisible(true);
			        	}
			        	else if(myChangerequest.getCurrentStep().equals("EXECUTION_APPROVE_TIME"))
			        	{
			        		btnExstraDetails.setVisible(true);
			        		btnApproveExecutionTime.setVisible(true);
			        		btnDenyExecutionTime.setVisible(true);
			        	}
			        	
			        	
			        }
			    });
			    return row ;
			});
		 
		 
		 
		
		 
		
	}

	
	
    @FXML
    void ClickAppointmentFunction(MouseEvent event) 
    {
    	myController.SelectChangeRequestForAppointments();
    		
    }

    
    
    @FXML
    void ClickApprovalFunction(MouseEvent event)
    {

    	myController.SelectAllChangeRequestForApprovals();
    	
    }

    
    
    @FXML
    void ClickBackFunction(MouseEvent event)
    {

    	
    }

    
    
    @FXML
    void ClickClosingStepFunction(MouseEvent event)
    {
    	myController.SelectAllChangeRequestForClose();
    	
    }
    
    

    @FXML
    void ClickHomePageFunction(MouseEvent event)       // Return to home page
    {
    	
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

    	
    	
    }
    
    
    
    @FXML
    void clickOnSelectExecutionLeader(MouseEvent event)
    {

    	
    	
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



	public void ShowSuccessAproveAppoint()
	{
		Toast.makeText(ProjectFX.mainStage, "Approving Analyzer successfuly", 1500, 500, 500);
		
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



	public void ShowAppointExecutionLeaderSuccess()
	{
		Toast.makeText(ProjectFX.mainStage, "Execution Leader Appointment success", 1500, 500, 500);
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
	
	
    public void showDenyAnalysisTime()
    {
    	Toast.makeText(ProjectFX.mainStage, "Deny Analysis time approved", 1500, 500, 500);
		
	}
    
    public void showApproveAnalysisTime()
    {
    	
    	Toast.makeText(ProjectFX.mainStage, "Analysis time approved", 1500, 500, 500);
		
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
    
    public void showApproveExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution time approved", 1500, 500, 500);
		
	}
    
    public void showDenyExecutionTime()
	{
    	Toast.makeText(ProjectFX.mainStage,"Execution time Deny", 1500, 500, 500);
		
	}
	
	
	
	
	public void setVisabilityValse()
	{
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



	



	



	



	



}