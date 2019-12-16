package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.prism.impl.ps.CachingEllipseRep;

import assets.ProjectPages;
import controllers.CommitteDecisionController;
import controllers.LoginController;
import entities.ChangeRequest;
import entities.CommitteeComment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CommitteeDecisionBoundry implements Initializable{

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
    
    //Add comment table
    @FXML
    private TableView<CommitteeComment> commentTable_addComment;
    @FXML
    private TableColumn<CommitteeComment, Integer> employeeIdAddColumn;
    @FXML
    private TableColumn<CommitteeComment, String> commentAddColumn;

    //request details table
    @FXML
    private TableView<ChangeRequest> requestInfoTable;
    @FXML
    private TableColumn<ChangeRequest, Integer> requestIdColumn;
    @FXML
    private TableColumn<ChangeRequest, String> descriptionColumn;

    //comment director table
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
    private ComboBox<?> decisionComboBox;

   private CommitteDecisionController myController= new CommitteDecisionController(this);
   private ChangeRequest currentChangeRequest;
   ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
   ObservableList<CommitteeComment> commentList = FXCollections.observableArrayList();
   
   
   public void setCurrentChangeRequest(ChangeRequest currentChangeRequest) {
	this.currentChangeRequest = currentChangeRequest;
   }

   @FXML
   void loadAddCommentPage(MouseEvent event) {
	   addCommentPane.setVisible(true);
	   committeeDirectorPane.setVisible(false);
	   myController.getCommentsByRequestId(String.valueOf(currentChangeRequest.getChangeRequestID()));
   }
    
    @FXML
    void loadAnalysisReportPage(MouseEvent event) {
    	
    }

    @FXML
    void loadCommitteeDirectorPage(MouseEvent event) {
    	
    }

    @FXML
    void loadHomePage(MouseEvent event) {

    }

    @FXML
    void loadPreviousPage(MouseEvent event) {

    }

    @FXML
    void loadTimeExtensionPage(MouseEvent event) {

    }

    @FXML
    void refreshTableDetails(MouseEvent event) {

    }

    @FXML
    void sendDirectorDecision(MouseEvent event) {

    }

    @FXML
    void submitComment(MouseEvent event) {
    	
    }

    @FXML
    void userLogout(MouseEvent event) {
    	/*TODO: Remove user from connected list */
    	ProjectFX.currentUser = null;
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ProjectFX.pagingController.loadBoundray(ProjectPages.LOGIN_PAGE.getPath());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeIdAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, Integer>("employeeId"));
		commentAddColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("changeRequestDescription"));
		employeeIdDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, Integer>("employeeId"));
		commentDirectorColumn.setCellValueFactory(new PropertyValueFactory<CommitteeComment, String>("comment"));
		
		addCommentPane.setVisible(false);
		committeeDirectorPane.setVisible(false);
		btnCommitteeDirector.setVisible(false);;
	    btnTimeExtension.setVisible(false);
	    
		switch (ProjectFX.currentUser.getPermission()){
		case "COMMITTEE_MEMBER":
			break;
		case "COMMITTEE_DIRECTOR":
			btnCommitteeDirector.setVisible(true);;
		    btnTimeExtension.setVisible(true);
			break;
		default:
			break;
		}
		
	}

}
