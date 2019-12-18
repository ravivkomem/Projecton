package boundries;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import assets.ProjectPages;
import assets.Toast;
import controllers.CommitteDecisionController;
import controllers.DemoLandingController;
import controllers.PagingController;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UploadChangeRequestBoundary implements Initializable {

	    @FXML
	    private Button backBtn;

	    @FXML
	    private Button browseBtn;

	    @FXML
	    private Button logoutBtn;

	    @FXML
	    private ComboBox<String> subSystemComboBox;

	    @FXML
	    private Button submitBtn;

	    @FXML
	    private TextField currentStateDescriptionField;

	    @FXML
	    private TextField changeRequestDescriptionField;

	    @FXML
	    private TextField reasonField;

	    @FXML
	    private TextField commentField;

	    @FXML
	    private TextField uploadedFileNameField;
	    
	    @FXML
	    private TextField changeRequestIdField;
	    
	    private UploadChangeRequestController myController= new UploadChangeRequestController(this);
	    private ChangeRequest newChangeRequest;
	    java.sql.Date uploadChangeRequestDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	 
	    
	    

	    @FXML
	    void BrowseFileToUpload(MouseEvent event) {
	    	
	    }

	    @FXML
	    void backToHomePage(MouseEvent event) {
	    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			ProjectFX.pagingController.loadBoundray(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    void logoutUser(MouseEvent event) {
	    	/*TODO: Remove user from connected list */
	    	ProjectFX.currentUser = null;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			ProjectFX.pagingController.loadBoundray(ProjectPages.LOGIN_PAGE.getPath());
	    }

	    @FXML
	    /*Create new change request via boundray page */
	    void uploadNewChangeRequest(MouseEvent event) throws InterruptedException {

	    	String newChangeRequestSelectedSystem= subSystemComboBox.getSelectionModel().getSelectedItem();
	    	String newCurrentStateDescription= currentStateDescriptionField.getText();
	    	String newChangeRequestDescription = changeRequestDescriptionField.getText();
	    	String newInitiator = ProjectFX.currentUser.getUserName();
	    	String newChangeRequestComment = commentField.getText();
	    	String newChangeRequestExplanation =  reasonField.getText();
	    	String newChangeRequestDocuments = uploadedFileNameField.getText();
	    	String newChangeRequestDate = uploadChangeRequestDate.toString();
	    	String newChangeRequestStatus= "Active";
	    	String HandlerUserName="Lior";//TODO needs to be random
	    	String newCurrentStep= "Analysis";
	    	/*incase the user didnt fill all the required fields*/
	    	if (newChangeRequestSelectedSystem.equals("")|| newCurrentStateDescription.equals("")||newChangeRequestDescription.equals("")||newChangeRequestExplanation.equals(""))
	    	{
	    		//check the case while all the fields are empty 
	    		Toast.makeText(ProjectFX.mainStage, "Please fill all the required fields", 1500, 500, 500);
	    	}
	    	/*while the required fields are filled properly create a new change request and send to the controller*/
	    	else
	    		{
	    			newChangeRequest = new ChangeRequest(newInitiator,newChangeRequestSelectedSystem,
	    			newCurrentStateDescription,newChangeRequestDescription,newChangeRequestComment,
	    			newChangeRequestDocuments,newChangeRequestExplanation,newChangeRequestDate,newChangeRequestStatus,HandlerUserName,newCurrentStep);
	    			myController.insertNewChangeRequest(newChangeRequest);
	    			//Toast.makeText(ProjectFX.mainStage, "Change request submitted", 1500, 500, 500);
	    		}
	    }
	    public void printMessageToUserUploadedNewChangeRequest(int affectedRows){
	    	if (affectedRows==1)
	    	{
	    		Toast.makeText(ProjectFX.mainStage, "Change request submitted", 1500, 500, 500);
	    		//newChangeRequest.getChangeRequestID()
	    		//Integer id=newChangeRequest.getChangeRequestID();
	    		//System.out.println(id.toString());
	    	}
	    	else
	    	{
	    		Toast.makeText(ProjectFX.mainStage, "The change request did not upload successfully", 1500, 500, 500);
	    		//TODO: what else ?
	    		
	    	}
	    }
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			subSystemComboBox.getItems().add("Lecturer Information Station");
			subSystemComboBox.getItems().add("Student Information Station");
			subSystemComboBox.getItems().add("Employee Information Station");
			subSystemComboBox.getItems().add("Moodle System");
			subSystemComboBox.getItems().add("Library System");
			subSystemComboBox.getItems().add("Class Rooms With Computers");
			subSystemComboBox.getItems().add("Laboratory");
			subSystemComboBox.getItems().add("Computer Farm");
			subSystemComboBox.getItems().add("College Website");
			changeRequestIdField.setVisible(false);
			
		}
		

	}

	
