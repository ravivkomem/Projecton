package boundries;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import com.sun.javafx.scene.control.SelectedCellsMap;

import assets.ProjectPages;
import assets.Toast;
import controllers.TimeManager;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class UploadChangeRequestBoundary implements Initializable {
	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
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
    @FXML
    private ListView<File> filesListView;
    
    /* ****************************************
     * ********** Private Object **************
     * ****************************************/
    private static final int MAX_CHAR = 100;
    private static final int LIST_ROW_HEIGHT = 24; // List element hight is 24px
    private static HashMap<String, Image> mapOfFileExtToSmallIcon = new HashMap<String, Image>();
    private UploadChangeRequestController myController= new UploadChangeRequestController(this);////connection to my controller 
    private ChangeRequest newChangeRequest;
    private final String CURRENT_STEP = "ANALYZER_AUTO_APPOINT";
    private ObservableList<File> listViewData = FXCollections.observableArrayList();
	    
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    @FXML
    void BrowseFileToUpload(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Resource File");
    	List<File> selectedFiles = fileChooser.showOpenMultipleDialog(ProjectFX.mainStage);
    	/*TODO: add size limitations */
//    	double bytes = file.length();
//		double kilobytes = (bytes / 1024);
//		double megabytes = (kilobytes / 1024);
    	if (!selectedFiles.isEmpty())
    	{
    		String filesStr = "";
    		for (int i = 0; i < selectedFiles.size(); i++)
    		{
    			File file = selectedFiles.get(i);
    			filesStr += file.getPath();
    			if (i != (selectedFiles.size() -1))
    			{
    				filesStr += "; ";
    			}
    			listViewData.add(file);
    		}
    		uploadedFileNameField.setText(filesStr);
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
    			newChangeRequestExplanation,TimeManager.getCurrentDate() ,newChangeRequestStatus,HandlerUserName,newCurrentStep);
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
    		myController.sendFilesToServer(listViewData, changeRequestId);
    		
    		/* TODO: Consider what to do with successful upload */
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
		
		filesListView.setItems(listViewData);
		
		
		filesListView.setCellFactory(param -> new AttachmentListCell() {});
		
		filesListView.prefHeightProperty().bind(Bindings.size(listViewData).add(listViewData.isEmpty() ? 1 : 0).multiply(LIST_ROW_HEIGHT+2));

		/* Set all the character limitations */
		changeRequestDescriptionField.setTextFormatter(new TextFormatter<String>(change -> 
		{
			int changeLength = change.getControlNewText().length();
			if (changeLength <= MAX_CHAR)
			{
				changeRequestDescLbl.setText(Integer.toString(changeLength) + " / " + Integer.toString(MAX_CHAR));
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
				commentLbl.setText(Integer.toString(changeLength) + " / " + Integer.toString(MAX_CHAR));
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
				currentStateDescriptionLbl.setText(Integer.toString(changeLength) + " / " + Integer.toString(MAX_CHAR));
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
				reasonLbl.setText(Integer.toString(changeLength) + " / " + Integer.toString(MAX_CHAR));
				return change;
			}
			else
			{
				return null;
			}
		}));
		
	}
	
	private static String getFileExt(String fname) {
        String ext = ".";
        int p = fname.lastIndexOf('.');
        if (p >= 0) {
            ext = fname.substring(p);
        }
        return ext.toLowerCase();
    }
	
	private static javax.swing.Icon getJSwingIconFromFileSystem(File file) {
		
        FileSystemView view = FileSystemView.getFileSystemView();
        javax.swing.Icon icon = view.getSystemIcon(file);

        return icon;
    }
	
	private static Image getFileIcon(String fname) {
        final String ext = getFileExt(fname);

        Image fileIcon = mapOfFileExtToSmallIcon.get(ext);
        if (fileIcon == null) {

            javax.swing.Icon jswingIcon = null; 

            File file = new File(fname);
            if (file.exists()) {
                jswingIcon = getJSwingIconFromFileSystem(file);
            }
            else {
                File tempFile = null;
                try {
                    tempFile = File.createTempFile("icon", ext);
                    jswingIcon = getJSwingIconFromFileSystem(tempFile);
                }
                catch (IOException ignored) {
                    // Cannot create temporary file. 
                }
                finally {
                    if (tempFile != null) tempFile.delete();
                }
            }

            if (jswingIcon != null) {
                fileIcon = jswingIconToImage(jswingIcon);
                mapOfFileExtToSmallIcon.put(ext, fileIcon);
            }
        }

        return fileIcon;
    }
	
	 private static Image jswingIconToImage(javax.swing.Icon jswingIcon) {
	        BufferedImage bufferedImage = new BufferedImage(jswingIcon.getIconWidth(), jswingIcon.getIconHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        jswingIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
	        return SwingFXUtils.toFXImage(bufferedImage, null);
	 }
	 
	 private static class AttachmentListCell extends ListCell<File> {
	    
		private final Image buttonImage = new Image(getClass().getResourceAsStream("close_symbol.png"));
		private HBox hbox = new HBox(5);
        private Label label = new Label("");
        private Pane pane = new Pane();
        private ImageView imageView = new ImageView();
        private Button button = new Button();
		 
		public AttachmentListCell() {
			super();
			
			hbox.getChildren().addAll(button, label, pane);
			
            HBox.setHgrow(pane, Priority.ALWAYS);
            ImageView buttomImageView = new ImageView(buttonImage);
            buttomImageView.setFitHeight(LIST_ROW_HEIGHT);
            buttomImageView.setPreserveRatio(true);
            button.setGraphic(buttomImageView);
            button.setPadding(Insets.EMPTY);

            button.setOnAction(event -> getListView().getItems().remove(getItem()));
			
		}
		
		 @Override
	     public void updateItem(File item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty || item == null || item.getName().equals("")) {
	                setGraphic(null);
	                setText(null);
	            } else {
	                Image fxImage = getFileIcon(item.getName());
	                imageView = new ImageView(fxImage);
	                label.setGraphic(imageView);
	                label.setText(item.getName());
	                setGraphic(hbox);
	            }
	        }
	    }

}

	
