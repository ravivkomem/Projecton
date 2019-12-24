package boundries;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.ExecutionLeaderController;
import entities.ChangeRequest;
import entities.ExecutionAproves;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	    private int flag=0;
	    java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	   
	   
	    
	    
	    
		@Override
		public void initData(Object data) {
			// TODO Auto-generated method stub
			myChangerequest = (ChangeRequest) data;
			txtWorkingOnChangeRequestNumber.setText("Working On Change Request Nomber " + myChangerequest.getChangeRequestID());
			txtChangeRequestDetails.setText(myChangerequest.getDesiredChangeDescription());
			
			
			
		}
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) 
		{
			//myChangerequest=new ChangeRequest(2,"lee", "Moodle", "Bad","good", "active", "itay");
			txtWaitingForTomeApprovalPopUp.setVisible(false);
			btnCommitExecution.setVisible(false);
			txtWorkingOnChangeRequestNumber.setVisible(true);
			btnRefresh.setVisible(false);	
			//txtChangeRequestDetails.setText(myChangerequest.getChangeRequestDescription());	
		}
		
		public void setflag()
		{
			flag=1;
		}
	    
		@FXML
	    void SendTimeRequiredForExecutionToSupervisor(MouseEvent event)  // submiting execution time
	    {
	    	
	    	if (txtTimeForExecution.getValue().equals(null)) {
				Toast.makeText(ProjectFX.mainStage, "Please add Time Execution first", 1500, 500, 500);
			} else {
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
	    void UpdateChangeRequestStepAndExecutionLeaderStatus(MouseEvent event)  // when execution commit working
	    {
	    	
	    }

	    @FXML
	    void BackToLastPageFromExecutionPage(MouseEvent event)       // back to the work station page
	    {
	    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	    }

	    @FXML
	    void LogOutFromExecutionLeaderPage(MouseEvent event)        // logout from execution page
	    {
	       	/*TODO: Remove user from connected list */
	    	ProjectFX.currentUser = null;
			//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	    }

	    @FXML
	    void OpenTimeExtensionPageFromExecutionLeaderPage(MouseEvent event)    // opet time extension
	    {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ProjectPages.TIME_EXTENSION_PAGE.getPath()));
				Parent root;
				root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }

	    @FXML
	    void ReturnToHomePageFromExecutionLeaderPage(MouseEvent event)     // return home page
	    {
	     	//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    void ShowAnalysisReportFromExecutionLeaderPage(MouseEvent event)   // show anaylisis report
	    {
	    	// give analysis report page the change request id to show the correct report
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ProjectPages.ANALISIS_REPORT_PAGE.getPath()));
				Parent root;
				root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void GetAgainTheChangeRequestToSeeStatus(MouseEvent event)  // Refresh button
	    {
	    	if(flag==0)
	    	myController.SelectCurrentStepIfItsExecutionWork(myChangerequest.getChangeRequestID());
	    	else
	    	myController.SelectEstimatedDateMinusStartDate(myChangerequest.getChangeRequestID());	
	    }

		public void ShowCommitButton()
		{
			
			Toast.makeText(ProjectFX.mainStage, "Supervisor approved your estimated time", 1500, 500, 500);
			btnCommitExecution.setVisible(true);
		}

		public void ShowEstimatedDateMinusStartDate(Date estimatedEndDate)
		{	
			Date todayDate=updateStepDate;
			long daysBetween;
			if(estimatedEndDate.before(todayDate)) {
				delayTimeTxt.setVisible(true);
				daysBetween = ChronoUnit.DAYS.between(estimatedEndDate.toLocalDate(), todayDate.toLocalDate());
				timeRemainingTextAria.setText(""+(daysBetween-1)+" Days");
			}
			else {
				timeRemainingTxt.setVisible(true);
				daysBetween = ChronoUnit.DAYS.between(todayDate.toLocalDate(), estimatedEndDate.toLocalDate());
				timeRemainingTextAria.setText(""+(daysBetween+1)+" Days");
			}
			
		}

	
	}

