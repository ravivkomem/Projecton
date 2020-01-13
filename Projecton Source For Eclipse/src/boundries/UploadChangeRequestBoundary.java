package boundries;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import assets.ProjectPages;
import assets.Toast;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class UploadChangeRequestBoundary implements Initializable {
	/*FXML ELEMENTS*/
		@FXML
		private Label currentStateDescriptionLbl;
	
    	@FXML
    	private Label changeRequestDescLbl;

    	@FXML
    	private Label reasonLbl;

    	@FXML
    	private Label commentLbl;
		
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
	    private TextArea currentStateDescriptionField;

	    @FXML
	    private TextArea changeRequestDescriptionField;

	    @FXML
	    private TextArea reasonTA;
	    
	    @FXML
	    private TextArea commentField;

	    @FXML
	    private TextField uploadedFileNameField;
	    
	    
	    
	    private static final int MAX_CHAR=100;
	    private UploadChangeRequestController myController= new UploadChangeRequestController(this);////connection to my controller 
	    private ChangeRequest newChangeRequest;
	    private final String CURRENT_STEP = "ANALYZER_AUTO_APPOINT";
	    java.sql.Date uploadChangeRequestDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	    
	    /*FXML METHODES*/
	    @FXML
	    void BrowseFileToUpload(MouseEvent event) {
	    	FileChooser fileChooser = new FileChooser();
	    	fileChooser.setTitle("Select Resource File");
	    	File selectedFile = fileChooser.showOpenDialog(ProjectFX.mainStage);
	    	if (selectedFile != null)
	    	{
	    		uploadedFileNameField.setText(selectedFile.getPath());
	    	}
	    }

	    @FXML
	    /*move the user back to the home page */
	    void backToHomePage(MouseEvent event) {
			ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    /*Logout the user from the system*/
	    void logoutUser(MouseEvent event) {
	    	ProjectFX.pagingController.userLogout();
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
	    	String newChangeRequestExplanation =  reasonTA.getText();
	    	Date newChangeRequestDate = uploadChangeRequestDate;
	    	String newChangeRequestStatus= "ACTIVE";
	    	String HandlerUserName="XXXX";//will be random in the controller 
	    	String newCurrentStep= CURRENT_STEP;
	    	/*in case the user didn't fill all the required fields*/
	    	if (subSystemComboBox.getSelectionModel().isEmpty()|| newCurrentStateDescription.equals("")||newChangeRequestDescription.equals("")||newChangeRequestExplanation.equals(""))
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
	    		myController.sendFilesToServer(uploadedFileNameField.getText(), changeRequestId);
	    		
	    		commentField.setText("");
	    		reasonTA.setText("");
	    		changeRequestDescriptionField.setText("");
	    		currentStateDescriptionField.setText("");
	    		uploadedFileNameField.setText("");
	    		subSystemComboBox.setPromptText("-sub systems-");
	    		popUpWindowMessage(AlertType.CONFIRMATION,"Upload Successfuly","Your change request id is :"+changeRequestId+"");	
	    	}	
	    }
	    
	    public void recieveFileUploadId(int fileID)
	    {
	    	if(fileID == 1)
	    	{
	    		/*File uploaded successfully - DO NOTHING */
	    	}
	    	else
	    	{
	    		/*TODO: maybe delete the change request */
	    		Toast.makeText(ProjectFX.mainStage, "File had problems with upload", 1500, 500, 500);
	    	}
	    }
	    
	    /*this method will show the window with the new change request id */
		public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
			Alert alert2 = new Alert(alert);
			alert2.setTitle(msg);
			alert2.setHeaderText(mess);
			return alert2.showAndWait();
		}
	    
		@Override
		/*initialize the combo box in this gui page  */
		public void initialize(URL location, ResourceBundle resources) {
			uploadedFileNameField.setEditable(false);
			
			subSystemComboBox.getItems().add("Lecturer Information Station");
			subSystemComboBox.getItems().add("Student Information Station");
			subSystemComboBox.getItems().add("Employee Information Station");
			subSystemComboBox.getItems().add("Moodle System");
			subSystemComboBox.getItems().add("Library System");
			subSystemComboBox.getItems().add("Class Rooms With Computers");
			subSystemComboBox.getItems().add("Laboratory");
			subSystemComboBox.getItems().add("Computer Farm");
			subSystemComboBox.getItems().add("College Website");
			commentField.setWrapText(true);
			reasonTA.setWrapText(true);
			changeRequestDescriptionField.setWrapText(true);
			currentStateDescriptionField.setWrapText(true);
			
		}

	    @FXML
	    void updateCountChangeRequestDesc(KeyEvent event) {
	    	changeRequestDescLbl.setText(changeRequestDescriptionField.getText().length()+"/" +MAX_CHAR);
	    }

	    @FXML
	    void updateCountComment(KeyEvent event) {
	    	commentLbl.setText(commentField.getText().length()+"/" +MAX_CHAR);
	    }

	    @FXML
	    void updateCountCurrentStateDesc(KeyEvent event) {
	    	currentStateDescriptionLbl.setText(currentStateDescriptionField.getText().length()+"/" +MAX_CHAR);
	    }

	    @FXML
	    void updateCountReason(KeyEvent event) {
	    	reasonLbl.setText(reasonTA.getText().length()+"/" +MAX_CHAR);
	    }
	}

	
