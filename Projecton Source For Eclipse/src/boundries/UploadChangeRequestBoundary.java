package boundries;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import assets.ProjectPages;
import assets.Toast;
import controllers.CommitteDecisionController;
import controllers.PagingController;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	/*FXML ELEMENTS*/
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
	    
	    
	    
	    
	    private UploadChangeRequestController myController= new UploadChangeRequestController(this);////connection to my controller 
	    private ChangeRequest newChangeRequest;
	    private final String CURRENT_STEP = "ANALAYZER_AUTO_APPOINT";
	    java.sql.Date uploadChangeRequestDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	    
	    /*FXML METHODES*/
	    @FXML
	    void BrowseFileToUpload(MouseEvent event) {
	    	
	    }

	    @FXML
	    /*move the user back to the home page */
	    void backToHomePage(MouseEvent event) {
			ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    /*Logout the user from the system*/
	    void logoutUser(MouseEvent event) {
	    	/*TODO: Remove user from connected list */
	    	ProjectFX.currentUser = null;
			ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	    }

	    @FXML
	    /*Create new change request via boundray page */
	    void uploadNewChangeRequest(MouseEvent event) {

	    	String newChangeRequestSelectedSystem= subSystemComboBox.getSelectionModel().getSelectedItem();
	    	String newCurrentStateDescription= currentStateDescriptionField.getText();
	    	String newChangeRequestDescription = changeRequestDescriptionField.getText();
	    	String newInitiator = ProjectFX.currentUser.getUserName();
	    	String newChangeRequestComment = commentField.getText();
	    	String newChangeRequestExplanation =  reasonField.getText();
	    	String newChangeRequestDocuments = uploadedFileNameField.getText();
	    	String newChangeRequestDate = uploadChangeRequestDate.toString();
	    	String newChangeRequestStatus= "Active";
	    	String HandlerUserName="XXXX";//will be random in the controller 
	    	String newCurrentStep= CURRENT_STEP;
	    	/*incase the user didnt fill all the required fields*/
	    	if (newChangeRequestSelectedSystem.equals("")|| newCurrentStateDescription.equals("")||newChangeRequestDescription.equals("")||newChangeRequestExplanation.equals(""))
	    	{
	    		Toast.makeText(ProjectFX.mainStage, "Please fill all the required fields", 1500, 500, 500);
	    	}
	    	/*while the required fields are filled properly create a new change request and send to the controller*/
	    	else
	    		{
	    			newChangeRequest = new ChangeRequest(newInitiator,newChangeRequestSelectedSystem,
	    			newCurrentStateDescription,newChangeRequestDescription,newChangeRequestComment,
	    			newChangeRequestExplanation,newChangeRequestDate,newChangeRequestStatus,HandlerUserName,newCurrentStep);
	    			myController.buildChangeRequestBeforeSendToDataBase(newChangeRequest);
	    		}
	    }
	    /*incase the query succeeded display window with the new change request id */
	    public void displayChangeRequestId(int changeRequestId){
	    	/*incase there was a problem*/
	    	if(changeRequestId == -1)
	    	{
	    		Toast.makeText(ProjectFX.mainStage, "The change request did not upload successfully", 1500, 500, 500);
	    	}
	    	/*before showing the window with the new change request id 
	    	 * initialize the fields for the next change request*/
	    	else
	    	{
	    		 commentField.setText("");
	    		 reasonField.setText("");
	    		 changeRequestDescriptionField.setText("");
	    		 currentStateDescriptionField.setText("");
	    		 uploadedFileNameField.setText("");
	    		 subSystemComboBox.setPromptText("-sub systems-");
	    		message(AlertType.INFORMATION,"Upload Successfuly","Your change request id is :"+changeRequestId+"");	
	    	}	
	    }
	    
	    /*this method will show the window with the new change request id */
		public static Optional<ButtonType> message(AlertType alert, String msg, String mess) {
			Alert alert2 = new Alert(alert);
			alert2.setTitle(msg);
			alert2.setHeaderText(mess);
			return alert2.showAndWait();
		}
	    
		@Override
		/*initialize the combo box in this gui page  */
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
			
			
		}
		
	}

	
