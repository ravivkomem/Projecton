package boundries;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CommitteeDecisionBoundary implements Initializable, DataInitializable {

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
	private TableColumn<CommitteeComment, Integer> employeeIdAddColumn;
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
	private TableColumn<CommitteeComment, Integer> employeeIdDirectorColumn;
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

	private CommitteDecisionController myController = new CommitteDecisionController(this);
	private ChangeRequest currentChangeRequest;
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	ObservableList<CommitteeComment> commentList = FXCollections.observableArrayList();

	@FXML
	void loadAddCommentPage(MouseEvent event) {
		committeeDirectorPane.setVisible(false);
		addCommentPane.setVisible(true);
		myController.getCommentsByRequestId(String.valueOf(currentChangeRequest.getChangeRequestID()));
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void loadCommitteeDirectorPage(MouseEvent event) {
		addCommentPane.setVisible(false);
		committeeDirectorPane.setVisible(true);
		myController.getCommentsByRequestId(String.valueOf(currentChangeRequest.getChangeRequestID()));
	}

	@FXML
	void loadHomePage(MouseEvent event) {
		// ((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary
		// window
		ProjectFX.pagingController.loadBoundray(ProjectPages.MENU_PAGE.getPath());
	}

	@FXML
	void loadPreviousPage(MouseEvent event) {
		// ((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary
		// window
		ProjectFX.pagingController.loadBoundray(ProjectPages.WORK_STATION_PAGE.getPath());
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

	@FXML
	void refreshTableDetails(MouseEvent event) {
		myController.getCommentsByRequestId(String.valueOf(currentChangeRequest.getChangeRequestID()));
	}

	@FXML
	void sendDirectorDecision(MouseEvent event) {
		switch (decisionComboBox.getSelectionModel().getSelectedItem()) {
		case "Approve":
			// move to the next step
			break;
		case "Deny":
			// move to closing step
			break;
		case "More information":
			// ?
			break;
		default:
			break;
		}

	}

	@FXML
	void submitComment(MouseEvent event) {
		if (addComentTextField.getText().equals("")) {
			Toast.makeText(ProjectFX.mainStage, "Please add comment first", 1500, 500, 500);
		} else {
			CommitteeComment newComment = new CommitteeComment(currentChangeRequest.getChangeRequestID(),
					ProjectFX.currentUser.getUserID(), addComentTextField.getText());
			myController.insertNewCommentToDB(newComment);
		}
	}

	@FXML
	void userLogout(MouseEvent event) {
		ProjectFX.currentUser = null;
		// ((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ProjectFX.pagingController.loadBoundray(ProjectPages.LOGIN_PAGE.getPath());
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeIdAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, Integer>("employeeId"));
		commentAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		descriptionColumn
				.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("changeRequestDescription"));
		employeeIdDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, Integer>("employeeId"));
		commentDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));

		requestList.add(currentChangeRequest);
		requestInfoTable.setItems(requestList);

		decisionComboBox.getItems().add("Approve");
		decisionComboBox.getItems().add("Deny");
		decisionComboBox.getItems().add("More information");

		// initialize timeRemainingTextAria

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
	}

}
