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


// TODO: Auto-generated Javadoc
/**
 * The Class WorkStationBoundary.
 */
public class WorkStationBoundary implements Initializable{

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	
	/** The selected change request id text area. */
	/*Text Field*/
    @FXML
    private TextArea selectedChangeRequestIdTextArea;
	
	/** The change request table view. */
	/* TableView */
    @FXML
    private TableView<ChangeRequest> changeRequestTableView;
    
    /** The request id column. */
    @FXML
    private TableColumn<ChangeRequest, Integer> requestIdColumn;
    
    /** The step column. */
    @FXML
    private TableColumn<ChangeRequest, String> stepColumn;
    
    /** The description column. */
    @FXML
    private TableColumn<ChangeRequest, String> descriptionColumn;
    
    /** The subsystem column. */
    @FXML
    private TableColumn<ChangeRequest, String> subsystemColumn;
    
    /** The refresh station button. */
    /*Buttons*/
    @FXML
    private Button refreshStationButton;
    
    /** The home page button. */
    @FXML
    private Button homePageButton;
    
    /** The logout button. */
    @FXML
    private Button logoutButton;
    
    /** The view all work button. */
    @FXML
    private Button viewAllWorkButton;
    
    /** The view analysis step work button. */
    @FXML
    private Button viewAnalysisStepWorkButton;
    
    /** The view execution step button. */
    @FXML
    private Button viewExecutionStepButton;
    
    /** The view tester step button. */
    @FXML
    private Button viewTesterStepButton;
    
    /** The view committe step button. */
    @FXML
    private Button viewCommitteStepButton;
    
    /** The view tester appoint button. */
    @FXML
    private Button viewTesterAppointButton;
    
    /** The start change request work button. */
    @FXML
    private Button startChangeRequestWorkButton;
    @FXML
    private Button backButton;
    /** The committee button break image. */
    /*Image Views*/
    @FXML
    private ImageView committeeButtonBreakImage;
    
    /** The my controller. */
    /* ***************************************
     * ********** Private Variables ***********
     * ***************************************/
    private WorkStationController myController = new WorkStationController(this);
	
	/** The change request list. */
	private ObservableList<ChangeRequest> changeRequestList = FXCollections.observableArrayList();
	
	/** The clicked change request. */
	private ChangeRequest clickedChangeRequest;
	
	/** The my tester appoint stage. */
	private Stage myTesterAppointStage = null;
	
	/** The current filter. */
	private WorkStationFilter currentFilter = WorkStationFilter.ALL_CHANGE_REQUEST;
	
    /**
     * Display all work change requests.
     *
     * @param event the event
     */
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    @FXML
    void displayAllWorkChangeRequests(MouseEvent event) {
    	myController.selectAllChangeRequest();
    }

    @FXML
    void loadPreviousPage(ActionEvent event) {
    	loadHomePage(null);
    }
    /**
     * Display analysis step change requests.
     *
     * @param event the event
     */
    @FXML
    void displayAnalysisStepChangeRequests(MouseEvent event) {
    	myController.selectAnalysisStepChangeRequest();
    }

    /**
     * Display committe decision change requests.
     *
     * @param event the event
     */
    @FXML
    void displayCommitteDecisionChangeRequests(MouseEvent event) {
    	myController.selectCommitteeStepChangeRequest();
    }

    /**
     * Display execution step change requests.
     *
     * @param event the event
     */
    @FXML
    void displayExecutionStepChangeRequests(MouseEvent event) {
    	myController.selectExecutionStepChangeRequest();
    }

    /**
     * Display tester step change requests.
     *
     * @param event the event
     */
    @FXML
    void displayTesterStepChangeRequests(MouseEvent event) {
    	myController.selectTesterStepChangeRequest();
    }
    
    /**
     * Display tester appoint change request.
     *
     * @param event the event
     */
    @FXML
    void displayTesterAppointChangeRequest(MouseEvent event) {
    	myController.selectTesterAppointStepChangeRequest();
    }

    /**
     * Load change request work page.
     *
     * @param event the event
     */
    @FXML
    void loadChangeRequestWorkPage(MouseEvent event) {
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
    				ProjectFX.pagingController.loadBoundary(ProjectPages.ANALYZER_PAGE.getPath(), clickedChangeRequest);
    				closeMyStages();
    				break;
    				
    			/*Committee Step Statuses */
    			case "COMMITTEE_WORK":
    				ProjectFX.pagingController.loadBoundary(ProjectPages.COMMITTEE_PAGE.getPath(), clickedChangeRequest);
    				closeMyStages();
    				break;
    				
    			/*Execution Step Statuses */
    			case "EXECUTION_SET_TIME":
    			case "EXECUTION_APPROVE_TIME":
    			case "EXECUTION_WORK":
    				ProjectFX.pagingController.loadBoundary(ProjectPages.EXECUTION_LEADER_PAGE.getPath(), clickedChangeRequest);
    				closeMyStages();
    				break;
    			
    			/*Committee Director setting tester status */
    			case "TESTER_COMMITTEE_DIRECTOR_APPOINT": 
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
    				closeMyStages();
    				break;
    				
    			/*Default -- Do nothing */
    			default:
    				Toast.makeText(ProjectFX.mainStage, startChangeRequestWorkButton.getText() + " Button not implemented yet", 1500, 500, 500);
    				break;
    		
    		}
    	}
    	
    }

    /**
     * Load home page.
     *
     * @param event the event
     */
    @FXML
    void loadHomePage(ActionEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
		closeMyStages();
    }

    /**
     * Refresh station.
     *
     * @param event the event
     */
    @FXML
    void refreshStation(ActionEvent event) {
    	switch (currentFilter)
    	{
			case ALL_CHANGE_REQUEST:
				this.displayAllWorkChangeRequests(null);
				break;
			case ANALYSIS_STEP:
				this.displayAnalysisStepChangeRequests(null);
				break;
			case COMMITTEE_STEP:
				this.displayCommitteDecisionChangeRequests(null);
				break;
			case EXECUTION_STEP:
				this.displayExecutionStepChangeRequests(null);
				break;
			case TESTER_APPOINT_STEP:
				this.displayTesterAppointChangeRequest(null);
				break;
			case TESTING_STEP:
				this.displayTesterStepChangeRequests(null);
				break;
			default:
				System.out.println("Need to implement switch case for: "+currentFilter);
				this.displayAllWorkChangeRequests(null);
				break;
    	}
    }

    /**
     * User logout.
     *
     * @param event the event
     */
    @FXML
    void userLogout(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
		closeMyStages();
    }

    /* *****************************************
     * ********** Public Methods ***************
     * *****************************************/
    
    /**
     * Load table view.
     *
     * @param recievedChangeRequestList the recieved change request list
     */
    public void loadTableView(List<ChangeRequest> recievedChangeRequestList)
    {
    	changeRequestList.clear();
    	changeRequestList.addAll(recievedChangeRequestList);
    	changeRequestTableView.setItems(changeRequestList);
    	clickedChangeRequest = null;
    	selectedChangeRequestIdTextArea.setText("");
    }
    
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProjectFX.mainStage.setTitle("ICM - Menu\\Work Station");
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
		viewCommitteStepButton.setVisible(false);
		viewTesterAppointButton.setVisible(false);
		committeeButtonBreakImage.setVisible(false);
		
		/* Displays with preconditions */
		String userPermission = ProjectFX.currentUser.getPermission(); 
		if (userPermission.equals("COMMITTEE_MEMBER") || userPermission.equals("SUPERVISOR_COMMITTEE_MEMBER"))
		{
			viewCommitteStepButton.setVisible(true);
		}
		else if (userPermission.equals("COMMITTEE_DIRECTOR") || userPermission.equals("SUPERVISOR_COMMITTEE_DIRECTOR"))
		{
			viewCommitteStepButton.setVisible(true);
			committeeButtonBreakImage.setVisible(true);
			viewTesterAppointButton.setVisible(true);
		}
		
		/* Call the method to automatically display */
		this.displayAllWorkChangeRequests(null);
	}
	
	/**
	 * Sets the filter type.
	 *
	 * @param filter the new filter type
	 */
	synchronized public void setFilterType (WorkStationFilter filter)
	{
		this.currentFilter = filter;
	}
	
	/**
	 * Close my stages.
	 */
	/* ******************************
	 * ******* Private Methods ******
	 * ******************************/
	private void closeMyStages()
	{
		if (myTesterAppointStage != null)
		{
			myTesterAppointStage.close();
		}
			
	}
	
	/**
	 * The Enum WorkStationFilter.
	 */
	/* ******************************
	 * ********* Enumerators ********
	 * ******************************/
	public enum WorkStationFilter
	{
		
		/** The all change request. */
		ALL_CHANGE_REQUEST,
		
		/** The analysis step. */
		ANALYSIS_STEP,
		
		/** The committee step. */
		COMMITTEE_STEP,
		
		/** The execution step. */
		EXECUTION_STEP,
		
		/** The tester appoint step. */
		TESTER_APPOINT_STEP,
		
		/** The testing step. */
		TESTING_STEP;
	}

}


