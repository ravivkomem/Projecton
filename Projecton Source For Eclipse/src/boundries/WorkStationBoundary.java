package boundries;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.WorkStationController;
import entities.ChangeRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WorkStationBoundary implements Initializable{

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	
	/*Text Field*/
    @FXML
    private TextArea selectedChangeRequestIdTextArea;
	/* TableView */
    @FXML
    private TableView<ChangeRequest> changeRequestTableView;
    @FXML
    private TableColumn<ChangeRequest, Integer> requestIdColumn;
    @FXML
    private TableColumn<ChangeRequest, String> stepColumn;
    @FXML
    private TableColumn<ChangeRequest, String> descriptionColumn;
    @FXML
    private TableColumn<ChangeRequest, String> subsystemColumn;
    /*Buttons*/
    @FXML
    private Button refreshStationButton;
    @FXML
    private Button homePageButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button viewAllWorkButton;
    @FXML
    private Button viewAnalysisStepWorkButton;
    @FXML
    private Button viewExecutionStepButton;
    @FXML
    private Button viewTesterStepButton;
    @FXML
    private Button viewCommitteStepButton;
    @FXML
    private Button viewTesterAppointButton;
    @FXML
    private Button startChangeRequestWorkButton;
    /*Image Views*/
    @FXML
    private ImageView committeeButtonBreakImage;
    
    
    
    /* ***************************************
     * ********** Private Variables ***********
     * ***************************************/
    private WorkStationController myController = new WorkStationController(this);
	private ObservableList<ChangeRequest> list = FXCollections.observableArrayList();
	private ChangeRequest clickedChangeRequest;
	Stage myTesterAppointStage = null;
	
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    @FXML
    void displayAllWorkChangeRequests(MouseEvent event) {
    	myController.selectAllChangeRequest();
    }

    @FXML
    void displayAnalysisStepChangeRequests(MouseEvent event) {
    	myController.selectAnalysisStepChangeRequest();
    }

    @FXML
    void displayCommitteDecisionChangeRequests(MouseEvent event) {
    	myController.selectCommitteeStepChangeRequest();
    }

    @FXML
    void displayExecutionStepChangeRequests(MouseEvent event) {
    	myController.selectExecutionStepChangeRequest();
    }

    @FXML
    void displayTesterStepChangeRequests(MouseEvent event) {
    	myController.selectTesterStepChangeRequest();
    }
    
    @FXML
    void displayTesterAppointChangeRequest(MouseEvent event) {
    	myController.selectTesterAppointStepChangeRequest();
    }

    @FXML
    void loadChangeRequestWorkPage(MouseEvent event) {
    	/*TODO: Add big switch case */
    	if(clickedChangeRequest == null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please select a change request to work on", 1500, 500, 500);
    	}
    	else
    	{
    		switch(clickedChangeRequest.getCurrentStep())
    		{
    			/*Analysis Step Statuses */
    			case "ANALYSIS_SET_TIME":
    			case "ANALYSIS_APPROVE_TIME":
    			case "ANALYSIS_WORK":
    				//ProjectFX.pagingController.loadBoundary(ProjectPages.ANALYZER_PAGE.getPath(), clickedChangeRequest);
    				break;
    				
    			/*Committee Step Statuses */
    			case "COMMITTEE_WORK":
    				ProjectFX.pagingController.loadBoundary(ProjectPages.COMMITTEE_PAGE.getPath(), clickedChangeRequest);
    				break;
    				
    			/*Execution Step Statuses */
    			case "EXECUTION_SET_TIME":
    			case "EXECUTION_APPROVE_TIME":
    			case "EXECUTION_WORK":
    				ProjectFX.pagingController.loadBoundary(ProjectPages.EXECUTION_LEADER_PAGE.getPath(), clickedChangeRequest);
    				break;
    			
    			/*Committee Director setting tester status */
    			case "TESTER_APPOINT": 
    				if (myTesterAppointStage == null)
    				{
    					myTesterAppointStage = ProjectFX.pagingController.loadAdditionalStage
    							(ProjectPages.APPOINT_TESTER.getPath(),clickedChangeRequest);
    				}
    				else if (myTesterAppointStage.isShowing())
    				{
    					Toast.makeText(ProjectFX.mainStage, "Analysis Report Window is already open", 1500, 500, 500);
    				} 
    				else
    				{
    					myTesterAppointStage.show();
    				}
    				break;
    			
    			/*Tester Step Statuses */
    			case "TESTING_WORK":
    				ProjectFX.pagingController.loadBoundary(ProjectPages.TESTER_PAGE.getPath(), clickedChangeRequest);
    				break;
    				
    			/*Default -- Do nothing */
    			default:
    				Toast.makeText(ProjectFX.mainStage, startChangeRequestWorkButton.getText() + " Button not implemented yet", 1500, 500, 500);
    				break;
    		
    		}
    	}
    	
    }

    @FXML
    void loadHomePage(ActionEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void refreshStation(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, refreshStationButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void userLogout(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    /* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
    
    public void loadTableView(List<ChangeRequest> changeRequestsList)
    {
    	list.clear();
    	list.addAll(changeRequestsList);
    	changeRequestTableView.setItems(list);
    	
    	if (!list.isEmpty())
    	{
    		changeRequestTableView.setVisible(true);
        	selectedChangeRequestIdTextArea.setVisible(true);
    		changeRequestTableView.setVisible(true);
    		refreshStationButton.setVisible(true);
    		startChangeRequestWorkButton.setVisible(true);
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Set the table view */
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		stepColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("actualStep"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		
		changeRequestTableView.setRowFactory(tv -> {
		    TableRow<ChangeRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
		        {
		        	clickedChangeRequest = row.getItem();
		        	selectedChangeRequestIdTextArea.setText(Integer.toString(clickedChangeRequest.getChangeRequestID()));
		        	
		        }
		    });
		    return row ;
		});
		/* Reset all local variables */
		clickedChangeRequest = null;
		/* Hide displays */
		selectedChangeRequestIdTextArea.setEditable(false);
		selectedChangeRequestIdTextArea.setVisible(false);
		changeRequestTableView.setVisible(false);
		refreshStationButton.setVisible(false);
		startChangeRequestWorkButton.setVisible(false);
		viewCommitteStepButton.setVisible(false);
		viewTesterAppointButton.setVisible(false);
		committeeButtonBreakImage.setVisible(false);
		
		/* Displays with preconditions */
		String userPermission = ProjectFX.currentUser.getPermission(); 
		if (userPermission.equals("COMMITTEE_MEMBER"))
		{
			viewCommitteStepButton.setVisible(true);
		}
		else if (userPermission.equals("COMMITTEE_DIRECTOR"))
		{
			viewCommitteStepButton.setVisible(true);
			committeeButtonBreakImage.setVisible(true);
			viewTesterAppointButton.setVisible(true);
		}
		
		/* Call the method to automatically display */
		this.displayAllWorkChangeRequests(null);
	}

}
