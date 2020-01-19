package boundries;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import assets.AttachmentListCell;
import assets.ProjectPages;
import assets.Toast;
import controllers.TimeManager;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import entities.MyFile;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
// TODO: Auto-generated Javadoc

/**
 * Upload Change Request Page (Boundary).
 *
 * @author Ido Kadosh
 */
public class UploadChangeRequestBoundary implements Initializable {
	
	/** The current state description lbl. */
	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	@FXML
	private Label currentStateDescriptionLbl;
	
	/** The change request desc lbl. */
	@FXML
	private Label changeRequestDescLbl;
	
	/** The reason lbl. */
	@FXML
	private Label reasonLbl;
	
	/** The comment lbl. */
	@FXML
	private Label commentLbl;
	
    /** The back btn. */
    @FXML
    private Button backBtn;
    
    /** The browse btn. */
    @FXML
    private Button browseBtn;
    
    /** The logout btn. */
    @FXML
    private Button logoutBtn;
    
    /** The sub system combo box. */
    @FXML
    private ComboBox<String> subSystemComboBox;
    
    /** The submit btn. */
    @FXML
    private Button submitBtn;
    
    /** The current state description field. */
    @FXML
    private TextArea currentStateDescriptionField;
    
    /** The change request description field. */
    @FXML
    private TextArea changeRequestDescriptionField;
    
    /** The reason TA. */
    @FXML
    private TextArea reasonTA;
    
    /** The comment field. */
    @FXML
    private TextArea commentField;
    
    /** The files list view. */
    @FXML
    private ListView<MyFile> filesListView;
    
    /** The file quantity label. */
    @FXML
    private Label fileQuantityLabel;
    
    /** The file size label. */
    @FXML
    private Label fileSizeLabel;
    
    /* ****************************************
     * *********** Static Object **************
     * ****************************************/
    
    /** The Constant MAX_CHAR. */
    private static final int MAX_CHAR = 100;
    
    /** The Constant LIST_ROW_HEIGHT. */
    private static final int LIST_ROW_HEIGHT = 24; // List element height is 24px
    
    /** The Constant FILE_QUANTITY_LIMIT. */
    private static final int FILE_QUANTITY_LIMIT = 3;
    
    /** The Constant FILE_SIZE_LIMIT_MB. */
    private static final int FILE_SIZE_LIMIT_MB = 5;
    
    /* ****************************************
     * ********** Private Object **************
     * ****************************************/
    
    /** The my controller. */
    private UploadChangeRequestController myController= new UploadChangeRequestController(this);////connection to my controller 
    
    /** The new change request. */
    private ChangeRequest newChangeRequest;
    
    /** The current step. */
    private final String CURRENT_STEP = "ANALYZER_AUTO_APPOINT";
    
    /** The list view data. */
    private ObservableList<MyFile> listViewData = FXCollections.observableArrayList();
    
    /** The is over quantity limit. */
    private boolean isOverQuantityLimit = false;
	    
    /* ************************************** *
     * ********** FXML Methods Implementation *
     * ************************************** */
    /**
     * In case this button pressed its open a fileChooser window for user to choose which files to upload  .
     *
     * @param event the event
     */
    @FXML
    void BrowseFileToUpload(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Resource File");
    	List<File> selectedFiles = fileChooser.showOpenMultipleDialog(ProjectFX.mainStage);
    	if (selectedFiles != null && !selectedFiles.isEmpty())
    	{
    		for (int i = 0; i < selectedFiles.size(); i++)
    		{
    			File file = selectedFiles.get(i);
    			if (listViewData.size() < FILE_QUANTITY_LIMIT)
    			{
    				listViewData.add(MyFile.parseToMyFile(file.getPath()));
    			}
    			else
    			{
    				Toast.makeText(ProjectFX.mainStage, "You can only upload up to " + FILE_QUANTITY_LIMIT +" files"
    						, 1500, 500, 500);
    				break;
    			}
    			
    		}
    	}
    }
    
    /**
     * Move the user back to the home page .
     *
     * @param event the event
     */
    @FXML
    void backToHomePage(MouseEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }
    
    /**
     * Disconnect the user from the system and updates the data base. 
     *
     * @param event the event
     */
    @FXML
    void logoutUser(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    /**
     * This method called when pressed the submit button while user upload a new change request 
     * here I am building the change request from the GUI page and send it to the controller, in case the user 
     * did not fill all the necessary fields it shows a message that alert the user to fill all the necessary fields 
     * and just after all the fields are filled the user can submit the change request .
     *
     * @param event the event
     */
    @FXML
    void uploadNewChangeRequest(MouseEvent event) {	
    	String newChangeRequestSelectedSystem= subSystemComboBox.getSelectionModel().getSelectedItem();
    	String newCurrentStateDescription= currentStateDescriptionField.getText();
    	String newChangeRequestDescription = changeRequestDescriptionField.getText();
    	String newInitiator = ProjectFX.currentUser.getUserName();
    	String newChangeRequestComment = commentField.getText();
    	String newChangeRequestExplanation =  reasonTA.getText();
    	String newChangeRequestStatus= "ACTIVE";
    	String HandlerUserName="XXXX";//will be random in the controller 
    	String newCurrentStep= CURRENT_STEP;
    	String newEmail = ProjectFX.currentUser.getEmail();
    	String newJobDescription = ProjectFX.currentUser.getJobDescription();
    	String newFullName = ProjectFX.currentUser.getFullName();
    	Date newEstimatedDate = null;
    	/*in case the user didn't fill all the required fields*/
    	if (subSystemComboBox.getSelectionModel().isEmpty()|| newCurrentStateDescription.equals("")||newChangeRequestDescription.equals("")||newChangeRequestExplanation.equals(""))
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please fill all the required fields", 1500, 500, 500);
    	}
    	/*while the required fields are filled properly create a new change request and send to the controller*/
    	else if (isOverQuantityLimit == true)
    	{
    		Toast.makeText(ProjectFX.mainStage, "You exceeded the file size limit, please delete files", 1500, 500, 500);
    	}
    	else
    	{
			newChangeRequest = new ChangeRequest(newInitiator,newChangeRequestSelectedSystem,
			newCurrentStateDescription,newChangeRequestDescription,newChangeRequestComment,
			newChangeRequestExplanation,TimeManager.getCurrentDate() ,newChangeRequestStatus,HandlerUserName,newCurrentStep,
			newJobDescription,newEmail,newFullName,newEstimatedDate);
			submitBtn.setDisable(true);
			myController.buildChangeRequestBeforeSendToDataBase(newChangeRequest);
    	}
    }
    
    /**
     * This method gets a change request ID and checks whether the change request uploaded successfully to the data base 
     * if there was no problems it calls popUpWindowMessage function that inform the user with the new change request id, else
     * shows an error message .
     *
     * @param changeRequestId the change request id
     */
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
    		myController.sendFilesToServer(listViewData, changeRequestId);
    		commentField.setText("");
    		reasonTA.setText("");
    		changeRequestDescriptionField.setText("");
    		currentStateDescriptionField.setText("");
    		subSystemComboBox.setPromptText("-sub systems-");
    		listViewData.clear();
    		submitBtn.setDisable(false);
    		popUpWindowMessage(AlertType.CONFIRMATION,"Upload Successfuly","Your change request id is :"+changeRequestId+"");
    	}	
    }
    
    /**
     * This method gets the id of the uploaded file as an integer variable, in case the variable is equal to 1 it means that the upload 
     * of the file passed successfully, else make a toast and alert the user that the upload failed .
     *
     * @param fileID the file ID
     */
    public void recieveFileUploadId(int fileID)
    {
    	if(fileID == 1)
    	{
    		/*File uploaded successfully - DO NOTHING */
    	}
    	else
    	{
    		Toast.makeText(ProjectFX.mainStage, "File had problems with upload", 1500, 500, 500);
    	}
    }
    
    /**
     * This method will shows a pop-up window with new change request id after the change request id submitted by user .
     *
     * @param alert the alert
     * @param msg the msg
     * @param mess the mess
     * @return the optional
     */
	public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		return alert2.showAndWait();
	}
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	/*initialize the combo box in this gui page  */
	public void initialize(URL location, ResourceBundle resources) {
		ProjectFX.mainStage.setTitle("ICM - Menu\\Upload Change Request");
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
		
		fileQuantityLabel.setText("Files Quantity 0/"+FILE_QUANTITY_LIMIT);
		fileQuantityLabel.setTextFill(Color.rgb(0, 0, 0));
		fileSizeLabel.setText("File Size 0.0/" + FILE_SIZE_LIMIT_MB + " [MB]");
		fileSizeLabel.setTextFill(Color.rgb(0, 0, 0));
		
		filesListView.setItems(listViewData);
		filesListView.setCellFactory(param -> new AttachmentListCell() {});
		filesListView.prefHeightProperty().bind(Bindings.size(listViewData).add(listViewData.isEmpty() ? 1 : 0).multiply(LIST_ROW_HEIGHT+2));
		
		listViewData.addListener(new ListChangeListener<MyFile>()
				{

					@Override
					public void onChanged(Change<? extends MyFile> change) {
						fileQuantityLabel.setText("Files Quantity " + listViewData.size()+"/"+FILE_QUANTITY_LIMIT);
				    	double bytes = 0;
				    	for (MyFile file : listViewData)
				    	{
				    		bytes += file.getMybytearray().length;
				    	}
						double kilobytes = (bytes / 1024);
						double megabytes = (kilobytes / 1024);
						fileSizeLabel.setText("File Size " + String.format("%.1f", megabytes) +"/" + FILE_SIZE_LIMIT_MB + " [MB]");
						if (megabytes > FILE_SIZE_LIMIT_MB)
						{
							fileSizeLabel.setTextFill(Color.rgb(255, 0, 0)); // Red Color
							isOverQuantityLimit = true;
						}
						else
						{
							fileSizeLabel.setTextFill(Color.rgb(0, 0, 0)); // Black Color
							isOverQuantityLimit = false;
						}
					}
			
				});

		/* Set all the character limitations */
		changeRequestDescriptionField.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHAR)
			{
				changeRequestDescLbl.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHAR));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
		commentField.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHAR)
			{
				commentLbl.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHAR));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
		currentStateDescriptionField.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHAR)
			{
				currentStateDescriptionLbl.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHAR));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
		reasonTA.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHAR)
			{
				reasonLbl.setText(Integer.toString(changeLength) + "/" + Integer.toString(MAX_CHAR));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
	}

}

	
