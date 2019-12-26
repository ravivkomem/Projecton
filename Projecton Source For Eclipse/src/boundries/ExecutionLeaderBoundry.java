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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

	public class ExecutionLeaderBoundry implements Initializable,DataInitializable {
		@FXML
		private Button btnRefresh;
		
		@FXML
		private Text timeRemainingTxt;
		
	    @FXML
	    private Text delayTimeTxt;
		
		@FXML
		private Text txtBuildChangeRequestDetails;
		
		@FXML
		 private Text txtBuildEnterTimeRequiredForExecution;
		
		@FXML
		private Text txtWaitingForTomeApprovalPopUp;

	    @FXML
	    private Button btnBack;
	    
	    @FXML
	    private Text txtWorkingOnChangeRequestNumber;

	    @FXML
	    private Button btnCommitExcution;
	    
	    @FXML
	    private TextArea timeRemainingTextAria;

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
	    
	    
 
	    private ExecutionLeaderController myController = new ExecutionLeaderController(this);
	    private ChangeRequest myChangerequest;
	    private int flag;
	    java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Stage myTimeExtensionStage = null;
		Stage myAnalysisReportStage= null;
		Step executionStep;
		Step executionStepMain;
	    
	    public Date getDate()
	    {
	    	return updateStepDate;
	    }
	   
	   
	    
	    
	    
		@Override
		public void initData(Object data) {
			// TODO Auto-generated method stub
			myChangerequest = (ChangeRequest) data;
			txtWorkingOnChangeRequestNumber.setText("Working On Change Request Nomber " + myChangerequest.getChangeRequestID());
			txtChangeRequestDetails.setText(myChangerequest.getDesiredChangeDescription());
			myController.GetStepDetails(myChangerequest.getChangeRequestID());
			
		
		}
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) 
		{
			//myChangerequest=new ChangeRequest(2,"lee", "Moodle", "Bad","good", "active", "itay");
			txtWaitingForTomeApprovalPopUp.setVisible(false);
			btnCommitExecution.setVisible(false);
			btnRefresh.setVisible(false);
			txtWorkingOnChangeRequestNumber.setVisible(true);
			txtChangeRequestDetails.setEditable(false);
			timeRemainingTextAria.setVisible(false);
			delayTimeTxt.setVisible(false);
			timeRemainingTxt.setVisible(false);
			flag=0;
			
			
			//txtChangeRequestDetails.setText(myChangerequest.getChangeRequestDescription());	
		}
		
		public void setflag()
		{
			flag=1;
		}
	    
		@FXML
	    public void SendTimeRequiredForExecutionToSupervisor(MouseEvent event)  // submit execution time
	    {
			long count=0;
	    	
	    	if (txtTimeForExecution.getValue()==null)
	    	{
				Toast.makeText(ProjectFX.mainStage, "Please add Time Execution first", 1500, 500, 500);
			}
	    	else if(txtTimeForExecution.getValue()!=null)
	    	{
	    		count = TimeManager.getDaysBetween(executionStep.getStartDate(),Date.valueOf(txtTimeForExecution.getValue()));
	    	}
	    	 if(count<0)
	    	{
	    		Toast.makeText(ProjectFX.mainStage, "Please choose other day", 1500, 500, 500);
	    	}
	    	else if(count>0)
	    	{
				txtWaitingForTomeApprovalPopUp.setVisible(true);
				Date estimatedDateChoosen = Date.valueOf(txtTimeForExecution.getValue());
				myController.insertNewEstimatedDateToExecutionStepAndChangeRequestIDStep(estimatedDateChoosen,myChangerequest.getChangeRequestID());
				myController.updateNewChangeRequestIdStepToExecutionApprovedTime(myChangerequest.getChangeRequestID());
				btnRefresh.setVisible(true);
				txtBuildEnterTimeRequiredForExecution.setVisible(false);
				txtTimeForExecution.setVisible(false);
				btnSubmitForTimeRequiredForExecution.setVisible(false);
			
			}
	    }
	    @FXML
		public void ExecutionAprovedtInsertToDBSuccessfully(int affectedRows)
	    {
			if (affectedRows == 1) {
				Toast.makeText(ProjectFX.mainStage, "The ExecutionTime uploaded successfully", 1500, 500, 500);
			} else {
				Toast.makeText(ProjectFX.mainStage, "The  ExecutionTime  upload failed", 1500, 500, 500);
			}

		}
	       
	    @FXML
	    public void UpdateChangeRequestStepAndExecutionLeaderStatus(MouseEvent event)  // when execution commit working
	    {
	    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
	         alert.getButtonTypes().remove(ButtonType.OK);
	         alert.getButtonTypes().add(ButtonType.CANCEL);
	         alert.getButtonTypes().add(ButtonType.YES);
	         alert.setTitle("COMMIT PRESSED");
	         alert.setContentText(String.format("Are you sure you want to commit?"));
	         Optional<ButtonType> res = alert.showAndWait();

	         if(res.isPresent()) 
	         {
	             if(res.get().equals(ButtonType.CANCEL))
	             {
	             	event.consume();
	             }
	             else
	             {
	            	 myController.UpdateExecutionLeaderDateAndStatus(myChangerequest.getChangeRequestID());
	     	    	myController.UpdateCurrentStepOfChangeRequrstFromExecutionWorkToTesterCommitteeDirectorAppoint(myChangerequest.getChangeRequestID());
	     	    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	             }
	         }
	    	
	    	
	    	
	    }

	    @FXML
	    void BackToLastPageFromExecutionPage(MouseEvent event)       // back to the work station page
	    {
	    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	    }

	    @FXML
	    void LogOutFromExecutionLeaderPage(MouseEvent event)        // logout from execution page
	    {
	    	ProjectFX.pagingController.userLogout();
			if (!(myTimeExtensionStage == null))
				myTimeExtensionStage.close();
			if (!(myAnalysisReportStage == null))
				myAnalysisReportStage.close();
			ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	    }

	    @FXML
	    public void OpenTimeExtensionPageFromExecutionLeaderPage(MouseEvent event)    // opet time extension
	    {
	    	if (myTimeExtensionStage == null)
			{
				myTimeExtensionStage = ProjectFX.pagingController.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),executionStep);
			}
			else if (myTimeExtensionStage.isShowing())
			{
				Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
			} 
			else
			{
				myTimeExtensionStage = ProjectFX.pagingController.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),executionStep);
			}

	    }

	    @FXML
	    public void ReturnToHomePageFromExecutionLeaderPage(MouseEvent event)     // return home page
	    {
	     	//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
	    	if(!(myTimeExtensionStage == null))
				myTimeExtensionStage.close();
			if(!(myAnalysisReportStage == null))
				myAnalysisReportStage.close();
			ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    public void ShowAnalysisReportFromExecutionLeaderPage(MouseEvent event)   // show anaylisis report
	    {
	    	if (myAnalysisReportStage == null)
			{
				myAnalysisReportStage = ProjectFX.pagingController.loadAdditionalStage
						(ProjectPages.ANALISIS_REPORT_PAGE.getPath(),myChangerequest);
			}
			else if (myAnalysisReportStage.isShowing())
			{
				Toast.makeText(ProjectFX.mainStage, "Analysis Report Window is already open", 1500, 500, 500);
			} 
			else
			{
				myAnalysisReportStage = ProjectFX.pagingController.loadAdditionalStage
						(ProjectPages.ANALISIS_REPORT_PAGE.getPath(),myChangerequest);
			}
	    }
	    
	    @FXML
	    public void GetAgainTheChangeRequestToSeeStatus(MouseEvent event)  // Refresh button
	    {
	    	
	    //	 myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());
	    	 
	    	if(flag==0)
	    		myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());
	    	else
	    		myController.SelectEstimatedDateMinusStartDate(myChangerequest.getChangeRequestID());	
	    }

		public void ShowCommitButton()
		{
			
			Toast.makeText(ProjectFX.mainStage, "Supervisor approved your estimated time", 1500, 500, 500);
			timeRemainingTextAria.setEditable(false);
			txtWaitingForTomeApprovalPopUp.setVisible(false);
			btnCommitExecution.setVisible(true);
			myController.SelectEstimatedDateMinusStartDate(myChangerequest.getChangeRequestID());
		}

		public void ShowEstimatedDateMinusStartDate(Date estimatedEndDate)
		{	
			Date todayDate=updateStepDate;
			long daysBetween;
			if(estimatedEndDate.before(todayDate)) {
				delayTimeTxt.setVisible(true);
				timeRemainingTextAria.setVisible(true);
				daysBetween = ChronoUnit.DAYS.between(estimatedEndDate.toLocalDate(), todayDate.toLocalDate());
				timeRemainingTextAria.setText(""+(daysBetween-1)+" Days");
			}
			else {
				timeRemainingTxt.setVisible(true);
				timeRemainingTextAria.setVisible(true);
				daysBetween = ChronoUnit.DAYS.between(todayDate.toLocalDate(), estimatedEndDate.toLocalDate());
				timeRemainingTextAria.setText(""+(daysBetween+1)+" Days");
			}
			
		}

		public void ShowFinishToast(int affectedrows)
		{
			// TODO Auto-generated method stub
			if(affectedrows==1)
			Toast.makeText(ProjectFX.mainStage, "Execution Step is finished", 1500, 500, 500);
			 
			
		}





		public void SupervisorDidNotAproveYet()
		{
				
				Toast.makeText(ProjectFX.mainStage, "Supervisor did not approve yet", 1500, 500, 500);
				txtWaitingForTomeApprovalPopUp.setVisible(true);
		}





		public void SetStep(Step executionStep2)
		{
			executionStep=executionStep2;
			if(!(executionStep.getEstimatedEndDate()==null)) {
				btnRefresh.setVisible(true);
				txtBuildEnterTimeRequiredForExecution.setVisible(false);
				txtTimeForExecution.setVisible(false);
				btnSubmitForTimeRequiredForExecution.setVisible(false);
				 myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());
			}
		}





		public void ErrorInLoadingExecutionPage()
		{
			Toast.makeText(ProjectFX.mainStage, "Problam in loading this changetequest", 1500, 500, 500);
			ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
		}

	
	}

