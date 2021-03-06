package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import assets.ProjectPages;
import assets.Toast;
import controllers.CommitteeDecisionController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Class CommitteeDecisionBoundary.
 *
 * @author Lee Hugi
 * This class control the committee decision page with all the methods and logic implementations
 */
public class CommitteeDecisionBoundary implements DataInitializable {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/** The add comment pane. */
	@FXML
	private AnchorPane addCommentPane;
	
	/** The committee director pane. */
	@FXML
	private AnchorPane committeeDirectorPane;

	/** The home page button. */
	@FXML
	private Button btnHomePage;
	
	/** The analysis report button. */
	@FXML
	private Button btnAnalysisReport;
	
	/** The add comment button. */
	@FXML
	private Button btnAddComment;
	
	/** The committee director button. */
	@FXML
	private Button btnCommitteeDirector;
	
	/** The time extension button. */
	@FXML
	private Button btnTimeExtension;
	
	/** The submit comment button. */
	@FXML
	private Button btnSubmitComment;
	
	/** The refresh table button. */
	@FXML
	private Button btnRefreshTable;
	
	/** The back button. */
	@FXML
	private Button btnBack;
	
	/** The send decision button. */
	@FXML
	private Button btnSendDecision;
	
	/** The logout button. */
	@FXML
	private Button btnLogout;

	// Add comment table
	/** The comment table. */
	@FXML
	private TableView<CommitteeComment> commentTable_addComment;
	
	/** The employee id column. */
	@FXML
	private TableColumn<CommitteeComment, String> employeeIdAddColumn;
	
	/** The comment column. */
	@FXML
	private TableColumn<CommitteeComment, String> commentAddColumn;

	// request details table
	/** The request information table. */
	@FXML
	private TableView<ChangeRequest> requestInfoTable;
	
	/** The request id column. */
	@FXML
	private TableColumn<ChangeRequest, Integer> requestIdColumn;
	
	/** The description column. */
	@FXML
	private TableColumn<ChangeRequest, String> descriptionColumn;

	// comment director table
	/** The comment table in the director page. */
	@FXML
	private TableView<CommitteeComment> commentTabelDirector;
	
	/** The employee id column. */
	@FXML
	private TableColumn<CommitteeComment, String> employeeIdDirectorColumn;
	
	/** The comment column. */
	@FXML
	private TableColumn<CommitteeComment, String> commentDirectorColumn;

    /** The add comment text field. */
    @FXML
    private TextArea addCommentTextField;

	/** The time remaining text area. */
	@FXML
	private TextArea timeRemainingTextAria;

	/** The decision combo box. */
	@FXML
	private ComboBox<String> decisionComboBox;

	/** The image 3 point 1. */
	@FXML
	private ImageView image3point1;
	
	/** The image 3 point 2. */
	@FXML
	private ImageView image3point2;

	/** The time remaining text. */
	@FXML
	private Text timeRemainingTxt;

	/** The delay time text. */
	@FXML
	private Text delayTimeTxt;
	
	/** The change request no text. */
	@FXML
	private Text changeRequestNoText;
	
    /** The deny comment text area. */
    @FXML
    private TextArea denyCommentTextArea;//need to handle
    
    /** The send deny comment button. */
    @FXML
    private Button sendDenyCommentBtn;
    
    /** The committee comment label. */
    @FXML
    private Label committeeCommentLabel;
    
    /** The deny comment label. */
    @FXML
    private Label denyCommentLabel;
    @FXML
    private Text informationText;

     /* *************************************
	  * ******* Private Objects *************
	  * *************************************/
    
	/** The my controller. */
     private CommitteeDecisionController myController = new CommitteeDecisionController(this);
	
	/** The current change request. */
	private ChangeRequest currentChangeRequest;
	
	/** The committee step. */
	private Step committeeStep;
	
	/** The request list. */
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	
	/** The comment list. */
	ObservableList<CommitteeComment> commentList = FXCollections.observableArrayList();
	
	/** The update step date. */
	java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	 
 	/** The Constant MAX_DENY_CHARS. */
 	private static final int MAX_DENY_CHARS = 100;
	 
 	/** The Constant MAX_COMMITTEE_CHARS. */
 	private static final int MAX_COMMITTEE_CHARS = 200;

	/** The time extension stage. */
	Stage myTimeExtensionStage = null;
	
	/** The analysis report stage. */
	Stage myAnalysisReportStage = null;

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
	
	/**
	 * This method handle with press on send deny decision
	 * the method call the controller for update the sql table in the data base just if
	 * the user field the text area.
	 *
	 * @param event
	 */
    @FXML
    void sendDenyDecisionAndComment(MouseEvent event) {
    	
    	if(denyCommentTextArea.getText().equals("")) {
    		Toast.makeText(ProjectFX.mainStage, "Please write your comment", 1500, 500, 500);
    	}else {
    		myController.updateCommitteeStepDB("CLOSED", updateStepDate, denyCommentTextArea.getText(),
        			currentChangeRequest.getChangeRequestID());
    		myController.insertToClosingStepDbTable(currentChangeRequest.getChangeRequestID(), updateStepDate,
    				"ACTIVE");
    		myController.updateChangeRequestCurrentStep("DENY_STEP", "", currentChangeRequest.getChangeRequestID());
    		popUpWindowMessage(AlertType.INFORMATION, "Update Successfully", "Your Decision Upload successfully");
			ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    	}
    }
	
    /**
     * This method show the committee comment page.
     *
     * @param event the event
     */
	@FXML
	void loadAddCommentPage(MouseEvent event) {
		committeeDirectorPane.setVisible(false);
		addCommentPane.setVisible(true);
		myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID(),committeeStep.getStepID());
	}

	/**
	 * This method open in another window the analysis report page.
	 *
	 * @param event the event
	 */
	@FXML
	void loadAnalysisReportPage(MouseEvent event) {
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

	/**
	 * This method show committee director page when the user with committee director permission
	 * press on committee director button.
	 *
	 * @param event the event
	 */
	@FXML
	void loadCommitteeDirectorPage(MouseEvent event) {
		addCommentPane.setVisible(false);
		committeeDirectorPane.setVisible(true);
		myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID(),committeeStep.getStepID());
	}

	/**
	 * This method close the committee decision page and open the menu page
	 * when the user press on home page button.
	 *
	 * @param event the event
	 */
	@FXML
	void loadHomePage(MouseEvent event) {
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	}

	/**
	 * This method open the previous page when the user press on back button.
	 *
	 * @param event the event
	 */
	@FXML
	void loadPreviousPage(MouseEvent event) {
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}

	/**
	 * This method open the time extension page in another window.
	 *
	 * @param event the event
	 */
	@FXML
	void loadTimeExtensionPage(MouseEvent event) {
		if (myTimeExtensionStage == null) {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),committeeStep);
		} else if (myTimeExtensionStage.isShowing()) {
			Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
		} else {
			myTimeExtensionStage = ProjectFX.pagingController
					.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath(),committeeStep);
		}
	}

	/**
	 * This method refresh the comment table.
	 *
	 * @param event the event
	 */
	@FXML
	void refreshTableDetails(MouseEvent event) {
		myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID(),committeeStep.getStepID());
	}

	/**
	 * This method send to the data base the decision of the committee director
	 * according to his choice in the comboBox.
	 *
	 * @param event the event
	 */
	@FXML
	void sendDirectorDecision(MouseEvent event) {
		if (decisionComboBox.getSelectionModel().isEmpty()) {
			Toast.makeText(ProjectFX.mainStage, "Please select your decision", 1500, 500, 500);
			return;
		} else {
			switch (decisionComboBox.getSelectionModel().getSelectedItem()) {
			case "Approve":
				/*
				 * move to the next step:
				 * update committee_step 
				 * update changeRequest table
				 */
				myController.updateCommitteeStepDB("CLOSED", updateStepDate,"", currentChangeRequest.getChangeRequestID());
				myController.updateChangeRequestCurrentStep("EXECUTION_LEADER_SUPERVISOR_APPOINT", "",
						currentChangeRequest.getChangeRequestID());
				break;
			case "Deny":
				/*
				 * move to closing step: 
				 * update committee_step 
				 * update close_step 
				 * update changeRequest table
				 */
				informationText.setText("Please write your deny decision");
				denyCommentLabel.setVisible(true);
				sendDenyCommentBtn.setVisible(true);
				denyCommentTextArea.setVisible(true);
				decisionComboBox.setVisible(false);
				btnSendDecision.setVisible(false);
				return;
			case "More information":
				/*
				 * move to analyzer step: 
				 * update committee_step 
				 * choose analyzer update
				 * changeRequest table
				 */
				myController.updateCommitteeStepDB("CLOSED", updateStepDate,"", currentChangeRequest.getChangeRequestID());
				myController.chooseAutomaticallyAnalyzer(currentChangeRequest.getSelectedSubsystem());
				break;
			default:
				break;
			}
			myController.updateTimeExtensionDB(committeeStep.getStepID(),"Committee");
			popUpWindowMessage(AlertType.INFORMATION, "Update Successfully", "Your Decision Upload successfully");
			ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
		}
	}

	/**
	 * press on the submit button will send the comment that the committee field in the
	 * text area to the data base.
	 *
	 * @param event the event
	 */
	@FXML
	void submitComment(MouseEvent event) {
		if (addCommentTextField.getText().equals("")) {
			Toast.makeText(ProjectFX.mainStage, "Please add comment first", 1500, 500, 500);
		} else {
			CommitteeComment newComment = new CommitteeComment(currentChangeRequest.getChangeRequestID(),
					ProjectFX.currentUser.getUserName(), addCommentTextField.getText());
			myController.insertNewCommentToDB(newComment,committeeStep.getStepID());
		}
	}

	/**
	 * This method will logout the user from the program
	 *
	 * @param event the event
	 */
	@FXML
	void userLogout(MouseEvent event) {
		ProjectFX.pagingController.userLogout();
		if (!(myTimeExtensionStage == null))
			myTimeExtensionStage.close();
		if (!(myAnalysisReportStage == null))
			myAnalysisReportStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}

	/*
	 * ************************************* 
	 * ******* Public Methods *************
	 * ************************************/
	
	/**
	 * The method get ArrayList of comments from the database and
	 * display the comment in the comment table.
	 *
	 * @param resultList the result list
	 */
	public void handleCommitteeCommentResultForTable(ArrayList<CommitteeComment> resultList) {
		commentList.clear();
		if (!resultList.isEmpty()) {
			commentList.addAll(resultList);
			commentTable_addComment.setItems(commentList);
			commentTabelDirector.setItems(commentList);
		}
	}

	/**
	 * This method gets int object.
	 * If the object equals to 1 the data base is update successfully.
	 *
	 * @param affectedRows the affected rows
	 */
	public void committeeCommentInsertToDBSuccessfully(int affectedRows) {
		if (affectedRows == 1) {
			Toast.makeText(ProjectFX.mainStage, "The comment uploaded successfully", 1500, 500, 500);
			myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID(),committeeStep.getStepID());
			addCommentTextField.setText("");
			committeeCommentLabel.setText("0/ " + MAX_COMMITTEE_CHARS);
		} else {
			Toast.makeText(ProjectFX.mainStage, "The comment upload failed", 1500, 500, 500);
		}
	}

	/**
	 * This method gets the handler user name.
	 * The method update the current step in the change_request table in the data base.
	 *
	 * @param handlerUserName the handler user name
	 */
	public void createObjectForUpdateChangeRequestDetails(String handlerUserName) {
		myController.updateChangeRequestCurrentStep("ANALYZER_AUTO_APPOINT", handlerUserName,
				currentChangeRequest.getChangeRequestID());
	}

	/**
	 * This method gets estimated end date from the step table in the database,
	 * and display time remaining or delay time for the user in the time remaining text field.
	 *
	 * @param estimatedEndDate the estimated end date
	 */
	public void displayTimeRemaining(Date estimatedEndDate) {
		//Date todayDate = updateStepDate;
		long daysBetween = TimeManager.getDaysBetween(TimeManager.getCurrentDate(), estimatedEndDate);
		if(daysBetween < 0) {
			delayTimeTxt.setVisible(true);
			timeRemainingTextAria.setText(Math.abs(daysBetween) + " Days");
		}
		else if(daysBetween == 0) {
			timeRemainingTxt.setVisible(true);
			timeRemainingTextAria.setText("Last Day");
		}
		else {
			timeRemainingTxt.setVisible(true);
			timeRemainingTextAria.setText(daysBetween + " Days");
		}
	}

	/**
	 * Creates the committee step details.
	 *
	 * @param resultStep the result step
	 */
	public void createCommitteStepDetails(Step resultStep) {
		committeeStep = resultStep;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProjectFX.mainStage.setTitle("ICM - Menu\\Work Station\\Committee");
		
		employeeIdAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("employeeUserName"));
		commentAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		descriptionColumn
				.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		employeeIdDirectorColumn
				.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("employeeUserName"));
		commentDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));

		decisionComboBox.getItems().add("Approve");
		decisionComboBox.getItems().add("Deny");
		decisionComboBox.getItems().add("More information");

		timeRemainingTextAria.setEditable(false);
		addCommentTextField.setWrapText(true);
		denyCommentTextArea.setWrapText(true);
		
		addCommentPane.setVisible(false);
		committeeDirectorPane.setVisible(false);
		
		addCommentTextField.setTextFormatter(new TextFormatter<String>(change -> {
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_COMMITTEE_CHARS){
				committeeCommentLabel.setText(Integer.toString(changeLength) + "/" + MAX_COMMITTEE_CHARS);
				return change;
			}
			else{
				return null;
			}
		}));
				
		denyCommentTextArea.setTextFormatter(new TextFormatter<String>(change -> {
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_DENY_CHARS){
				denyCommentLabel.setText(Integer.toString(changeLength) + "/" + MAX_DENY_CHARS);
				return change;
			}
			else{
				return null;
			}
		}));
		
		switch (ProjectFX.currentUser.getPermission()) {
		case "COMMITTEE_MEMBER":
			btnCommitteeDirector.setDisable(true);
			btnTimeExtension.setDisable(true);
			break;
		case "SUPERVISOR_COMMITTEE_MEMBER":
			btnCommitteeDirector.setDisable(true);
			btnTimeExtension.setDisable(true);
			break;
		case "SUPERVISOR_COMMITTEE_DIRECTOR":
			btnCommitteeDirector.setVisible(true);
			btnTimeExtension.setVisible(true);
			break;
		case "COMMITTEE_DIRECTOR":
			btnCommitteeDirector.setVisible(true);
			btnTimeExtension.setVisible(true);
			break;
		default:
			break;
		}

	}

	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest) data;
		changeRequestNoText.setText("Change Request No." + currentChangeRequest.getChangeRequestID());
		requestList.add(currentChangeRequest);
		requestInfoTable.setItems(requestList);
		myController.getStartTimeFromCommitteeStep(currentChangeRequest.getChangeRequestID());
		myController.getCommitteeStepDetails(currentChangeRequest.getChangeRequestID());
	}

	/**
	 * This method will show up window with the message that the method gets.
	 *
	 * @param alert the alert
	 * @param msg the message
	 * @param mess the mess
	 * @return the optional
	 */
	public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		alert2.setContentText("Press OK to move to work station");
		return alert2.showAndWait();
	}

}
