package boundries;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

	public class ExecutionLeaderBoundry implements Initializable,DataInitializable {
		@FXML
		private Text txtWaitingForTomeApprovalPopUp;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnCommitExcution;

	    @FXML
	    private TextField txtTimeForExecution;

	    @FXML
	    private Button btnSubmitForTimeRequiredForExecution;

	    @FXML
	    private Button btnLogOut;

	    @FXML
	    private TextArea txtChangeRequestDetails;

	    @FXML
	    private ImageView btnHomePage;

	    @FXML
	    private ImageView btnAnalysisReport;

	    @FXML
	    private ImageView btnTimeExtension;
	    
	    
	    
	    
	    
	    private ExecutionLeaderController myController = new ExecutionLeaderController(this);
	    private ChangeRequest myChangerequest;
	    
	    
	    
		@Override
		public void initData(Object data) {
			// TODO Auto-generated method stub
			myChangerequest = (ChangeRequest) data;
		}
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) 
		{
			//myChangerequest=new ChangeRequest(2,"lee", "Moodle", "Bad","good", "active", "itay");
			txtWaitingForTomeApprovalPopUp.setVisible(false);
			//txtChangeRequestDetails.setText(myChangerequest.getChangeRequestDescription());	
		}
	    
		@FXML
	    void SendTimeRequiredForExecutionToSupervisor(MouseEvent event)
	    {
	    	
	    	if (txtTimeForExecution.getText().equals("")) {
				Toast.makeText(ProjectFX.mainStage, "Please add Time Execution first", 1500, 500, 500);
			} else {
				txtWaitingForTomeApprovalPopUp.setVisible(true);
				ExecutionAproves executionAprove = new ExecutionAproves(txtTimeForExecution.getText());
				myController.insertNewExecutionAprovetToDB(executionAprove);
				
			}
	    }
	    @FXML
		public void ExecutionAprovedtInsertToDBSuccessfully(int affectedRows) {
			if (affectedRows == 1) {
				Toast.makeText(ProjectFX.mainStage, "The ExecutionTime uploaded successfully", 1500, 500, 500);
			} else {
				Toast.makeText(ProjectFX.mainStage, "The  ExecutionTime  upload failed", 1500, 500, 500);
			}

		}
	    
	    @FXML
	    void UpdateDBForFinishExecution(MouseEvent event)
	    {

	    }

	    @FXML
	    void BackToLastPageFromExecutionPage(MouseEvent event)
	    {
	    	ProjectFX.pagingController.loadBoundray(ProjectPages.WORK_STATION_PAGE.getPath());
	    }

	    @FXML
	    void LogOutFromExecutionLeaderPage(MouseEvent event)
	    {
	       	/*TODO: Remove user from connected list */
	    	ProjectFX.currentUser = null;
			//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			ProjectFX.pagingController.loadBoundray(ProjectPages.LOGIN_PAGE.getPath());
	    }

	    @FXML
	    void OpenTimeExtensionPageFromExecutionLeaderPage(MouseEvent event)
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
	    void ReturnToHomePageFromExecutionLeaderPage(MouseEvent event)
	    {
	     	//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			ProjectFX.pagingController.loadBoundray(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    void ShowAnalysisReportFromExecutionLeaderPage(MouseEvent event)
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

	
	}

