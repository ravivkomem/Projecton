package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.TesterController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.Step;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TesterBoundary implements DataInitializable {

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
    @FXML
    private AnchorPane FailDetailsPane;
    @FXML
    private Text timeDisplayText;
    @FXML
    private TextArea timeremainingField;
    @FXML
    private Button backButton;
    @FXML
    private Button setButton;
    @FXML
    private Button reportfailButton;
    @FXML
    private TextField failuredetailsField;
    @FXML
    private TextField durationtextField;
    @FXML
    private TextField headertextField;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homepageButton;
    @FXML
    private Button analysisreportButton;
    @FXML
    private Button timeExtensionButton;
    @FXML
    private Text pageHeaderText;
    @FXML
    private AnchorPane testWorkPane;
    @FXML
    private ComboBox<String> testapprovalComboBox;

    /* ****************************************
     * ********** Private Variables ***********
     * ***************************************/
    private TesterController myController = new TesterController(this);
    private static final String APROVE_STRING = "Approval";
    private static final String DENY_STRING = "Deny";
    private ChangeRequest currentChangeRequest;
	private Step testerStep;
	private Stage myTimeExtensionStage;
	private Stage myAnalysisReportStage;
    
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    @FXML
    void LogOut(MouseEvent event) {
    	closeMyStages();
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

	@FXML
    void loadHomePage(MouseEvent event) {
		closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void loadPreviousPage(MouseEvent event) {
    	closeMyStages();
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }

    @FXML
    void loadTimeExtensionPage(MouseEvent event) {
    	if (myTimeExtensionStage == null) {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),testerStep);
		} else if (myTimeExtensionStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
		} else {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),testerStep);
		}
    }
    
    @FXML
    void loadAnalysisReport(MouseEvent event) {
    	if (myAnalysisReportStage == null) {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), currentChangeRequest);
		} else if (myAnalysisReportStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Analysis Report Window is already open", 1500, 500, 500);
		} else {
			myAnalysisReportStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.ANALISIS_REPORT_PAGE.getPath(), currentChangeRequest);
		}
    }

    @FXML
    void setReportFail(MouseEvent event) {
    	//currentChangeRequest.setCurrentStep("Execution");
		myController.updateChangeRequestStep(currentChangeRequest,failuredetailsField.getText().toString(),"CLOSED",TimeManager.getCurrentDate());
		myController.updateChangeRequestCurrentStep("EXECUTION_LEADEAR_SUPERVISOR_APPOINT","",currentChangeRequest.getChangeRequestID());
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }

    @FXML
    void setTest(MouseEvent event) {
    	if (testapprovalComboBox.getSelectionModel().isEmpty())
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please select your test result", 1500, 500, 500);
    	}
    	else
    	{
    		switch (testapprovalComboBox.getSelectionModel().getSelectedItem()) {
				case APROVE_STRING:
					// Update Information and move to next step
					myController.updateChangeRequestStep(currentChangeRequest, "-","CLOSED",TimeManager.getCurrentDate());
					myController.updateChangeRequestCurrentStep("CLOSING_STEP","",currentChangeRequest.getChangeRequestID());
					ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
					break;
				case DENY_STRING:
					FailDetailsPane.setVisible(true);
					
					break;
				
				default:
					System.out.println("Error reached default in switch case");
					break;
		    }
    	}
    	
    }
    
    /* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
    public void updateTesterPageToDBSuccessfully(int affectedRows) {
    	if(affectedRows == 1) {
    		Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
    	}else {
    		Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/* Set Text*/
		pageHeaderText.setText("Loading Change Request Information");
		
		/* Change visibilities */
		testWorkPane.setVisible(false);
		FailDetailsPane.setVisible(false);
		
		/* Init combo box */
		testapprovalComboBox.getItems().add(DENY_STRING);
		testapprovalComboBox.getItems().add(APROVE_STRING);
		
		/* Change editiable */
		timeremainingField.setEditable(false);
		
		/* Disable buttons */
		analysisreportButton.setDisable(true);
		timeExtensionButton.setDisable(true);
	}

	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest) data;
		myController.getCurrentStep(currentChangeRequest.getChangeRequestID());
	}

	public void recieveCurrentStep(Step recievedStep) {
		testerStep = recievedStep;
		pageHeaderText.setText("Working on change request No."+ currentChangeRequest.getChangeRequestID());
		testWorkPane.setVisible(true);
		analysisreportButton.setDisable(false);
		timeExtensionButton.setDisable(false);
		
		displayTimeRemaining(testerStep.getEstimatedEndDate());
		
	}
	
	/* *****************************************
     * ********** Private Methods **************
     * *****************************************/
	
	private void closeMyStages() {
    	if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
	}
	
	private void displayTimeRemaining(Date estimatedEndDate) {
		long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), estimatedEndDate);
		if(daysBetween < 0) {
			timeDisplayText.setText("Time Delay");
			timeremainingField.setText(Math.abs(daysBetween) + " Days");
		}
		else if(daysBetween == 0) {
			timeDisplayText.setText("Time Remaining");
			timeremainingField.setText("Last Day");
		}
		else {
			timeDisplayText.setText("Time Remaining");
			timeremainingField.setText(daysBetween + " Days");
		}
	}

}
