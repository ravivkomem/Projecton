package boundries;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.AnalyzerController;
import controllers.TimeManager;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnalyzerBoundary implements DataInitializable {
	private ChangeRequest currentChangeRequest;
	private AnalyzerController myController = new AnalyzerController(this);
	java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	public static final String ANALYSIS_SET_TIME = "ANALYSIS_SET_TIME";
	public static final String ANALYSIS_APPROVE_TIME = "ANALYSIS_APPROVE_TIME";
	public static final String ANALYSIS_WORK = "ANALYSIS_WORK";
	private String durationtime;
	@FXML
    private AnchorPane datePane;
	  @FXML
	    private Button dateButton;
	  @FXML
	    private Button logoutButton;

	    @FXML
	    private Button backButton;

	    @FXML
	    private Button homepageButton;

	    @FXML
	    private Button requestdetailsButton;

	    @FXML
	    private Button createreportButton;

	    @FXML
	    private Button timeextensionButton;

	    @FXML
	    private TableView<ChangeRequest> tableView;

	    @FXML
	    private TableColumn<ChangeRequest, Integer> requestIdColumn;

	    @FXML
	    private TableColumn<ChangeRequest, String> stepColumn;

	    @FXML
	    private TableColumn<ChangeRequest, String> descriptionColumn;

	    @FXML
	    private TableColumn<ChangeRequest, String> subsystemColumn;

	    @FXML
	    private AnchorPane createReportPane;

	    @FXML
	    private TextField descriptiontextField;

	    @FXML
	    private TextField advantagestextField;

	    @FXML
	    private TextField constraintstextField;

	    @FXML
	    private DatePicker timedurationPicker;

	    @FXML
	    private ComboBox<String> subsystemComboBox;

	    @FXML
	    private Button submitButton;

	    @FXML
	    private Text pageHeaderText;
	    @FXML
	    private Text notificationText;

	    @FXML
	    void LogOut(MouseEvent event) {
	    	ProjectFX.pagingController.userLogout();
			ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	    }

	   @FXML
	    void loadCreateReport(MouseEvent event) {

	    }
	    @FXML
	    void submit(MouseEvent event) {
	    /*	switch(subsystemComboBox.getSelectionModel().getSelectedItem())
	    	{
	    	case MOODLE_SYSTEM:
	    		
	    		break;
	    	case EMPLOYEE_INFORMATION_STATION:
	    		
	    		break;
	    	case LIBRARY_SYSTEM:
	    		
	    		break;
	    	case LABORATORY:
	    		
	    		break;
	    	case CLASS_ROOMS_WITH_COMPUTERS:
	    		
	    		break;
	    	}*/
	    	//"UPDATE icm.analysis_step SET EndDate = ?,Status = ?,AnalysisReportDescription = ?,AnalysisReportAdvantages = ?,AnalysisReportConstraints = ? WHERE ChangeRequestID = ?";
	    	myController.updateChangeRequestCurrentStepAndHandlerName(currentChangeRequest,"COMMITTEE_WORK", "-");
	    	myController.updateAnalysisStepClose(currentChangeRequest, TimeManager.getCurrentDate(), "Close",descriptiontextField.getText(),advantagestextField.getText(),constraintstextField.getText());
	    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	    }
	    @FXML
	    void setDate(MouseEvent event) {
	    	if(timedurationPicker.getValue() == null) {
	    		Toast.makeText(ProjectFX.mainStage, "Please select start date and end date", 1500, 500, 500);
	    	}
	    	else {
	    		Date date = Date.valueOf(timedurationPicker.getValue());
	    		
	    		Date todayDate = TimeManager.getCurrentDate();
	    		long daysBetween = TimeManager.getDaysBetween(todayDate, date);
	    		if(daysBetween<0) {
	    			Toast.makeText(ProjectFX.mainStage, "You can not select a date before today date", 1500, 500, 500);
	    		}else {
	    			myController.updateAnalysisStepEstimatedEndDate(currentChangeRequest, date);
	    			myController.updateChangeRequestCurrentStep(currentChangeRequest, ANALYSIS_APPROVE_TIME);
	    		}
	    		
	    		/*TODO check for end date
	    		 * check for endDate>startDate*/
	    	}

	    }

	    @FXML
	    void loadHomePage(MouseEvent event) {
	    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    void loadPreviousPage(MouseEvent event) {
	    	ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	    }

	    @FXML
	    void loadRequestDetails(MouseEvent event) {
ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath());
	    }

	    @FXML
	    void loadTimeExtensionPage(MouseEvent event) {
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
	    public void updateTesterPageToDBSuccessfully(int affectedRows) {
	    	if(affectedRows == 1) {
	    		Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
	    	}else {
	    		Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
	    	}
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		/*subsystemComboBox.getItems().add(CLASS_ROOMS_WITH_COMPUTERS);
		subsystemComboBox.getItems().add(EMPLOYEE_INFORMATION_STATION);
		subsystemComboBox.getItems().add(LIBRARY_SYSTEM);
		subsystemComboBox.getItems().add(MOODLE_SYSTEM);
		subsystemComboBox.getItems().add(LABORATORY);*/
		tableView.setVisible(true);
		createReportPane.setVisible(false);
		datePane.setVisible(false);
		notificationText.setVisible(false);
		switch(currentChangeRequest.getCurrentStep()) {
		case ANALYSIS_SET_TIME:
			datePane.setVisible(true);

			break;
		case ANALYSIS_APPROVE_TIME:
			tableView.setVisible(false);
			createReportPane.setVisible(false);
			datePane.setVisible(false);
			notificationText.setVisible(true);
			break;
		case ANALYSIS_WORK:
			createReportPane.setVisible(true);
			tableView.setVisible(false);
			datePane.setVisible(false);
			notificationText.setVisible(false);
			
			
			break;
			
		}
		
		
		
	}

	@Override
	public void initData(Object data) {
		// TODO Auto-generated method stub
		currentChangeRequest = (ChangeRequest)data;
		pageHeaderText.setText("Working on change request No."+ currentChangeRequest.getChangeRequestID());
		// load table and header
		// switch case on currentChangeRequest.getCurrentStep
		// if ANALYSIS_SET_TIME - display datepicker
		// if ANALYSIS_APPROVE_TIME - display proper message
		// if ANALYSIS_WORK - display all relevant text fields
	}

}
