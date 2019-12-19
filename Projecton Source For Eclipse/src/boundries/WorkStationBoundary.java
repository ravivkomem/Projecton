package boundries;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.WorkStationController;
import entities.ChangeRequestNew;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class WorkStationBoundary implements Initializable{

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	
	/*Text Field*/
    @FXML
    private TextField selectChangeRequestIdTextField;
    @FXML
    private TextArea selectedChangeRequestIdTextArea;
	/* TableView */
    @FXML
    private TableView<ChangeRequestNew> changeRequestTableView;
    @FXML
    private TableColumn<ChangeRequestNew, Integer> requestIdColumn;
    @FXML
    private TableColumn<ChangeRequestNew, String> stepColumn;
    @FXML
    private TableColumn<ChangeRequestNew, String> descriptionColumn;
    @FXML
    private TableColumn<ChangeRequestNew, String> subsystemColumn;
    
    /*Buttons*/
    @FXML
    private Button refreshStationButton;
    @FXML
    private Button homePageButton;
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
    private Button logoutButton;
    @FXML
    private Button startChangeRequestWorkButton;
    
    
    /*Private Variables */
    private WorkStationController myController = new WorkStationController(this);
	ObservableList<ChangeRequestNew> list = FXCollections.observableArrayList();
	private ChangeRequestNew clickedChangeRequest;
	
    /* FXML Methods */
    @FXML
    void displayAllWorkChangeRequests(MouseEvent event) {
    	myController.selectAllChangeRequestByHandlerUserName();
    }

    @FXML
    void displayAnalysisStepChangeRequests(MouseEvent event) {
    	myController.selectAnalysisStepChangeRequestByHandlerUserName();
    }

    @FXML
    void displayCommitteDecisionChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewCommitteStepButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void displayExecutionStepChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewExecutionStepButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void displayTesterStepChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewTesterStepButton.getText() + " Button not implemented yet", 1500, 500, 500);
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
    				//ProjectFX.pagingController.loadBoundary(ProjectPages.EXECUTION_LEADER_PAGE.getPath(), clickedChangeRequest);
    				break;
    			
    			/*Committee Director setting tester status */
    			case "TESTER_COMMITTEE_DIRECTOR_APPOINT": 
    				/*TODO: Load the committee director page */
    				Toast.makeText(ProjectFX.mainStage, "Not implemented yet - TESTER_COMMITTEE_DIRECTOR_APPOINT", 1500, 500, 500);
    				break;
    			
    			/*Tester Step Statuses */
    			case "TESTING_WORK":
    				//ProjectFX.pagingController.loadBoundary(ProjectPages.TESTER_PAGE.getPath(), clickedChangeRequest);
    				
    			/*Default -- Do nothing */
    			default:
    				break;
    		
    		}
    		
    	}
    	
    	Toast.makeText(ProjectFX.mainStage, startChangeRequestWorkButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadHomePage(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void refreshStation(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, refreshStationButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void userLogout(MouseEvent event) {
    	/*TODO: Remove user from connected list */
    	ProjectFX.currentUser = null;
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    /* public methods */
    public void loadTableView(List<ChangeRequestNew> changeRequestsList)
    {
    	list.clear();
    	list.addAll(changeRequestsList);
    	changeRequestTableView.setItems(list);
    	changeRequestTableView.setVisible(true);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clickedChangeRequest = null;
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, Integer>("changeRequestID"));
		stepColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, String>("currentStep"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, String>("desiredChangeDescription"));
		subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, String>("selectedSubsystem"));
		
		changeRequestTableView.setRowFactory(tv -> {
		    TableRow<ChangeRequestNew> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
		        {
		        	clickedChangeRequest = row.getItem();
		        	selectChangeRequestIdTextField.setText(Integer.toString(clickedChangeRequest.getChangeRequestID()));
		        	selectedChangeRequestIdTextArea.setText(Integer.toString(clickedChangeRequest.getChangeRequestID()));
		        	
		        }
		    });
		    return row ;
		});
		
		changeRequestTableView.setVisible(false);
		this.displayAllWorkChangeRequests(null);
	}

}
