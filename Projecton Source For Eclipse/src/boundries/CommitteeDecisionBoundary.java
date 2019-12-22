package boundries;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.prism.impl.ps.CachingEllipseRep;

import assets.ProjectPages;
import assets.Toast;
import controllers.CommitteDecisionController;
import controllers.LoginController;
import entities.ChangeRequest;
import entities.CommitteeComment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommitteeDecisionBoundary implements DataInitializable {

	@FXML
	private AnchorPane addCommentPane;
	@FXML
	private AnchorPane committeeDirectorPane;

	@FXML
	private Button btnHomePage;
	@FXML
	private Button btnAnalysisReport;
	@FXML
	private Button btnAddComment;
	@FXML
	private Button btnCommitteeDirector;
	@FXML
	private Button btnTimeExtension;
	@FXML
	private Button btnSubmitComment;
	@FXML
	private Button btnRefreshTable;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnSendDecision;
	@FXML
	private Button btnLogout;

	// Add comment table
	@FXML
	private TableView<CommitteeComment> commentTable_addComment;
	@FXML
	private TableColumn<CommitteeComment, String> employeeIdAddColumn;
	@FXML
	private TableColumn<CommitteeComment, String> commentAddColumn;

	// request details table
	@FXML
	private TableView<ChangeRequest> requestInfoTable;
	@FXML
	private TableColumn<ChangeRequest, Integer> requestIdColumn;
	@FXML
	private TableColumn<ChangeRequest, String> descriptionColumn;

	// comment director table
	@FXML
	private TableView<CommitteeComment> commentTabelDirector;
	@FXML
	private TableColumn<CommitteeComment, String> employeeIdDirectorColumn;
	@FXML
	private TableColumn<CommitteeComment, String> commentDirectorColumn;

	@FXML
	private TextField addComentTextField;

	@FXML
	private TextArea timeRemainingTextAria;

	@FXML
	private ComboBox<String> decisionComboBox;

	@FXML
	private ImageView image3point1;

	@FXML
	private ImageView image3point2;
	
    @FXML
    private Text timeRemainingTxt;
    
    @FXML
    private Text delayTimeTxt;

	private AnalysisReportBoundary analysisReportBoundary=new AnalysisReportBoundary();
	private CommitteDecisionController myController = new CommitteDecisionController(this);
	private ChangeRequest currentChangeRequest;
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	ObservableList<CommitteeComment> commentList = FXCollections.observableArrayList();
	java.sql.Date updateStepDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	
	Stage myTimeExtensionStage = null;

	@FXML
	void loadAddCommentPage(MouseEvent event) {
		committeeDirectorPane.setVisible(false);
		addCommentPane.setVisible(true);
		myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}

	@FXML
	void loadAnalysisReportPage(MouseEvent event) {
		// give analysis report page the change request id to show the correct report
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ProjectPages.ANALISIS_REPORT_PAGE.getPath()));
			Parent root;
			root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			analysisReportBoundary=fxmlLoader.getController();
			analysisReportBoundary.setCurrentChangeRequest(currentChangeRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void loadCommitteeDirectorPage(MouseEvent event) {
		addCommentPane.setVisible(false);
		committeeDirectorPane.setVisible(true);
		myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}

	@FXML
	void loadHomePage(MouseEvent event) {
		// ((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		myTimeExtensionStage.close();
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
		
	}

	@FXML
	void loadPreviousPage(MouseEvent event) {
		// ((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}

	@FXML
	void loadTimeExtensionPage(MouseEvent event) {
		//give time extension page change request and stuff
		/* try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ProjectPages.TIME_EXTENSION_PAGE.getPath()));
			Parent root;
			root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		} */
		if (myTimeExtensionStage == null)
		{
			myTimeExtensionStage = ProjectFX.pagingController.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath());
		}
		else if (myTimeExtensionStage.isShowing())
		{
			Toast.makeText(ProjectFX.mainStage, "Time Extension Window is already open", 1500, 500, 500);
		} 
		else
		{
			myTimeExtensionStage = ProjectFX.pagingController.loadAdditionalStage(ProjectPages.TIME_EXTENSION_PAGE.getPath());
		}
		
	}

	@FXML
	void refreshTableDetails(MouseEvent event) {
		myController.getCommentsByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}

	@FXML
	void sendDirectorDecision(MouseEvent event) {
		if(decisionComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
			Toast.makeText(ProjectFX.mainStage, "Please select your decision", 1500, 500, 500);
			return;
		}
		switch (decisionComboBox.getSelectionModel().getSelectedItem()) {
		case "Approve":
			/* move to the next step:
			 * update committee_step
			 * update changeRequest table*/ 
			myController.updateCommitteeStepDB("CLOSED",updateStepDate,currentChangeRequest.getChangeRequestID());
			myController.updateChangeRequestCurrentStep("EXECUTION_LEADEAR_SUPERVISOR_APPOINT",
					"",currentChangeRequest.getChangeRequestID());
			break;
		case "Deny":
			/* move to closing step:
			 * update committee_step
			 * update close_step
			 * update changeRequest table*/ 
			myController.updateCommitteeStepDB("CLOSED",updateStepDate,currentChangeRequest.getChangeRequestID());
			myController.insertToClosingStepDbTable(currentChangeRequest.getChangeRequestID(),updateStepDate,"ACTIVE");
			myController.updateChangeRequestCurrentStep("DENY_STEP","",currentChangeRequest.getChangeRequestID());
			break;
		case "More information":
			/* move to analyzer step:
			 * update committee_step
			 * choose analyzer
			 * update changeRequest table*/
			myController.updateCommitteeStepDB("CLOSED",updateStepDate,currentChangeRequest.getChangeRequestID());
			myController.chooseAutomaticallyAnalyzer();
			break;
		default:
			break;
		}
		popUpWindowMessage(AlertType.INFORMATION, "", "Your Decision Upload successfully");
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	}

	@FXML
	void submitComment(MouseEvent event) {
		if (addComentTextField.getText().equals("")) {
			Toast.makeText(ProjectFX.mainStage, "Please add comment first", 1500, 500, 500);
		} else {
			CommitteeComment newComment = new CommitteeComment(currentChangeRequest.getChangeRequestID(),
					ProjectFX.currentUser.getUserName(), addComentTextField.getText());
			myController.insertNewCommentToDB(newComment);
		}
	}

	@FXML
	void userLogout(MouseEvent event) {
		ProjectFX.currentUser = null;
		// ((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}

	public void handleCommitteeCommentResultForTable(ArrayList<CommitteeComment> resultList) {
		commentList.clear();
		if (!resultList.isEmpty()) {
			commentList.addAll(resultList);
			commentTable_addComment.setItems(commentList);
			commentTabelDirector.setItems(commentList);
		}
	}

	public void committeeCommentInsertToDBSuccessfully(int affectedRows) {
		if (affectedRows == 1) {
			Toast.makeText(ProjectFX.mainStage, "The comment uploaded successfully", 1500, 500, 500);
		} else {
			Toast.makeText(ProjectFX.mainStage, "The comment upload failed", 1500, 500, 500);
		}
	}
	
	public void createObjectForUpdateChangeRequestDetails(String handlerUserName) {
		myController.updateChangeRequestCurrentStep("ANALAYZER_AUTO_APPOINT",handlerUserName
				,currentChangeRequest.getChangeRequestID());
	}
	
	/**
	 * this method display time remaining for the step or delay time
	 * @param estimatedEndDate
	 */
	public void displayTimeRemaining(Date estimatedEndDate) {
		Date todayDate=updateStepDate;
		long daysBetween;
		if(estimatedEndDate.before(todayDate)) {
			delayTimeTxt.setVisible(true);
			daysBetween = ChronoUnit.DAYS.between(estimatedEndDate.toLocalDate(), todayDate.toLocalDate());
			timeRemainingTextAria.setText(""+(daysBetween-1));
		}
		else {
			timeRemainingTxt.setVisible(true);
			daysBetween = ChronoUnit.DAYS.between(todayDate.toLocalDate(), estimatedEndDate.toLocalDate());
			timeRemainingTextAria.setText(""+(daysBetween+1));
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//employeeIdAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("employeeUserName"));
		employeeIdAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("employeeUserName"));
		commentAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		descriptionColumn
				.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		employeeIdDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("employeeUserName"));
		commentDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));

		decisionComboBox.getItems().add("Approve");
		decisionComboBox.getItems().add("Deny");
		decisionComboBox.getItems().add("More information");
		
		timeRemainingTextAria.setEditable(false);

		addCommentPane.setVisible(false);
		committeeDirectorPane.setVisible(false);
		btnCommitteeDirector.setVisible(false);
		btnTimeExtension.setVisible(false);
		image3point1.setVisible(false);
		image3point2.setVisible(false);

		switch (ProjectFX.currentUser.getPermission()) {
		case "COMMITTEE_MEMBER":
			break;
		case "COMMITTEE_DIRECTOR":
			btnCommitteeDirector.setVisible(true);
			btnTimeExtension.setVisible(true);
			image3point1.setVisible(true);
			image3point2.setVisible(true);
			break;
		default:
			break;
		}

	}

	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest) data;
		requestList.add(currentChangeRequest);
		requestInfoTable.setItems(requestList);
		myController.getStartTimeFromCommitteeStep(currentChangeRequest.getChangeRequestID());
	}
	
	/*this method will show the window with the new change request id */
	public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		return alert2.showAndWait();
	}

}
