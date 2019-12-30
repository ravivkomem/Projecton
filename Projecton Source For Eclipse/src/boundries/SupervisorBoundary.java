package boundries;

import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import assets.ProjectPages;
import controllers.SupervisorController;
import entities.ChangeRequest;
import entities.CommitteeComment;
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
    private ComboBox<String> comboSelectAnalyizer;
   
    @FXML
    private ComboBox<String> comboSelectExecutionLeader;
    
    
    

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
		 
		 tableChangeRequest.setRowFactory(tv -> {
			    TableRow<ChangeRequest> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
			        {
			        	myChangerequest = row.getItem();
			        	if(myChangerequest.getCurrentStep().equals("ANALYZER_AUTO_APPOINT"))
			        	{
			        		btnExstraDetails.setVisible(true);
			        		btnApproveAppointment.setVisible(true);
			        		btnDenyAppointment.setVisible(true);
			        		txtSystemAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setVisible(true);
			        		txtHandlerNameAutoAppoint.setText(myChangerequest.getHandlerUserName());
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

    	
    }

    
    @FXML
    void clickOnDenyAppointment(MouseEvent event)
    {
    	myController.Change
    	
    	
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



}