package boundries;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import assets.AttachmentListCellNonRemoveable;
import controllers.ExtraDetailsChangeRequestController;
import entities.ChangeRequest;
import entities.MyFile;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
// TODO: Auto-generated Javadoc

/**
 * Extra details for specific change request page (Boundary).
 *
 * @author Ido Kadosh
 */
public class ExtraDetailsChangeRequestBoundary implements DataInitializable {

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	
	/** The current step TF. */
	@FXML
	private TextField currentStepTF;
	
	/** The estimated time for step TF. */
	@FXML
	private TextField estimatedTimeForStepTF;
	
    /** The back btn. */
    @FXML
    private Button backBtn;
    
    /** The initiator name TF. */
    @FXML
    private TextField initiatorNameTF;
    
    /** The sub system TF. */
    @FXML
    private TextField subSystemTF;
    
    /** The current state desc TA. */
    @FXML
    private TextArea currentStateDescTA;
    
    /** The Requested change desc TF. */
    @FXML
    private TextArea RequestedChangeDescTF;
    
    /** The reason TF. */
    @FXML
    private TextArea reasonTF;
    
    /** The comment TF. */
    @FXML
    private TextArea commentTF;
    
    /** The Status TF. */
    @FXML
    private TextField StatusTF;
    
    /** The files error label. */
    @FXML
    private Label filesErrorLabel;
    
    /** The logout user. */
    @FXML
    private Button logoutUser;
    
    /** The file list view. */
    @FXML
    private ListView<MyFile> fileListView;
    
    /** The suspend button. */
    @FXML
    private Button suspendButton;
    
    /** The page title. */
    @FXML
    private Text pageTitle;

    /* ***************************************
     * ********** Private Objects ***********
     * ***************************************/
    
    /** The Constant LIST_ROW_HEIGHT. */
    private static final int LIST_ROW_HEIGHT = 24;
    
    /** The previous page path. */
    //private MyFile currentFile;
    private String previousPagePath;
    
    /** The current change request. */
    //private File downloadedFile;
    private ChangeRequest currentChangeRequest;
    
    /** The my controller. */
    private ExtraDetailsChangeRequestController myController = new ExtraDetailsChangeRequestController(this);
    
    /** The file list. */
    private ObservableList<MyFile> fileList = FXCollections.observableArrayList();
    
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    
    /**
     * By pressing this button the user goes back to the prevoius page .
     *
     * @param event the event
     */
    @FXML
    void backBtn(MouseEvent event) {
    	if (previousPagePath.equals(ProjectPages.ANALYZER_PAGE.getPath()))
    	{
    		ProjectFX.pagingController.loadBoundary(previousPagePath, currentChangeRequest);
    	}
    	else
    	{
    		ProjectFX.pagingController.loadBoundary(previousPagePath);
    	}
    		
    }

    /**
     * By pressing this button the user disconnects from the system .
     *
     * @param event the event
     */
    @FXML
    void logoutUser(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/* FXML Objects init */
		
		initiatorNameTF.setEditable(false);
		subSystemTF.setEditable(false);
		RequestedChangeDescTF.setEditable(false);
		reasonTF.setEditable(false);
		commentTF.setEditable(false);
		StatusTF.setEditable(false);
		currentStateDescTA.setEditable(false);
		currentStepTF.setEditable(false);
		estimatedTimeForStepTF.setEditable(false);
		
		fileListView.setItems(fileList);
		fileListView.setCellFactory(param -> {
			AttachmentListCellNonRemoveable<MyFile> cell = new AttachmentListCellNonRemoveable<MyFile>();
			cell.setOnMouseClicked(event -> {
				if (!cell.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() >= 2)
				{
					MyFile myFile = cell.getItem();
					FileChooser fileChooser = new FileChooser();
			    	fileChooser.setTitle("Save As...");
			    	File selectedFile = fileChooser.showSaveDialog(null);
			    	
			    	if (selectedFile != null)
			    	{
			    		downloadFile(myFile, selectedFile.getPath());
			    	}
				}
			});
			
			return cell;
		});
		fileListView.prefHeightProperty().bind(Bindings.size(fileList).add(fileList.isEmpty() ? 1 : 0).multiply(LIST_ROW_HEIGHT));
		
	}
	
	/* (non-Javadoc)
	 * @see boundries.DataInitializable#initData(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Object data) {
		
		try 
		{
			currentChangeRequest = (ChangeRequest)((ArrayList<Object>) data).get(0);
			previousPagePath = (String)((ArrayList<Object>) data).get(1);
			if (previousPagePath.equals(ProjectPages.REQUEST_LIST_PAGE.getPath()))
			{
				ProjectFX.mainStage.setTitle("ICM - Menu\\Request List\\Extra Details");
			} 
			else if (previousPagePath.equals(ProjectPages.SUPERVISOR_PAGE.getPath()))
			{
				ProjectFX.mainStage.setTitle("ICM - Menu\\Supervisor\\Extra Details");
			}
			else if (previousPagePath.equals(ProjectPages.TECH_MANAGER_PAGE.getPath()))
			{
				ProjectFX.mainStage.setTitle("ICM - Menu\\Tech Manager\\Extra Details");
			}
			else if (previousPagePath.equals(ProjectPages.ANALYZER_PAGE.getPath()))
			{
				ProjectFX.mainStage.setTitle("ICM - Menu\\Work Station\\Analysis\\Extra Details");
			}
			else
			{
				ProjectFX.mainStage.setTitle("ICM - Information Change Mangement System");
			}
			
			initiatorNameTF.setText(currentChangeRequest.getInitiatorUserName());
			subSystemTF.setText(currentChangeRequest.getSelectedSubsystem());
			currentStateDescTA.setText(currentChangeRequest.getCurrentStateDescription());
			RequestedChangeDescTF.setText(currentChangeRequest.getDesiredChangeDescription());
			reasonTF.setText(currentChangeRequest.getDesiredChangeExplanation());
			commentTF.setText(currentChangeRequest.getDesiredChangeComments());
			String userDisplayedStatus = currentChangeRequest.getStatus();
			userDisplayedStatus = String.valueOf(userDisplayedStatus.charAt(0)).toUpperCase() + userDisplayedStatus.substring(1).toLowerCase();
			StatusTF.setText(userDisplayedStatus);
			reasonTF.setWrapText(true);
			commentTF.setWrapText(true);
			RequestedChangeDescTF.setWrapText(true);
			currentStateDescTA.setWrapText(true);
			filesErrorLabel.setVisible(false);
			currentStepTF.setText(currentChangeRequest.getActualStep());
			pageTitle.setText("Change Request No. "+currentChangeRequest.getChangeRequestID()+" Details");
			/*Gets the time for each step */
			if (!(currentChangeRequest.getCurrentStep().equals("ANALYSIS_WORK")||currentChangeRequest.getCurrentStep().equals("COMMITTEE_WORK")
				||currentChangeRequest.getCurrentStep().equals("EXECUTION_WORK")||currentChangeRequest.getCurrentStep().equals("TESTING_WORK")))
			{
				estimatedTimeForStepTF.setText("In Evaluation");
			}
			else if (currentChangeRequest.getCurrentStep().equals("CLOSING_STEP"))
			{
				estimatedTimeForStepTF.setText(currentChangeRequest.getEstimatedEndDate().toString());
			}
			else
			{
				getEstimatedEndTimeForEachStep();
			}
			/**/
			if (currentChangeRequest.getStatus().equals("CLOSED")||currentChangeRequest.getStatus().equals("DENIED"))
			{
				estimatedTimeForStepTF.setText("");
				estimatedTimeForStepTF.setDisable(true);
			}
			if (currentChangeRequest.getCurrentStep().equals("FINISH"))
			{
				suspendButton.setDisable(true);
			}
			myController.getChangeRequestFiles(currentChangeRequest.getChangeRequestID());
		}
		catch (Exception e)
		{
			System.out.println("Couldn't init data in Extra details page");
			this.logoutUser(null);
		}
		if (ProjectFX.currentUser.getPermission().equals("SUPERVISOR") ||
				ProjectFX.currentUser.getPermission().equals("SUPERVISOR_COMMITTEE_MEMBER") ||
				ProjectFX.currentUser.getPermission().equals("SUPERVISOR_COMMITTEE_DIRECTOR"))
		{
			suspendButton.setVisible(true);
			suspendButton.setDisable(true);
			suspendButton.setText("Suspend");
			if (currentChangeRequest.getStatus().equals("ACTIVE"))
			{
				suspendButton.setDisable(false);
			} 
			else if (currentChangeRequest.getStatus().equals("SUSPEND"))
			{
				suspendButton.setText("Un-Suspend");
			}
				
		}
		if(ProjectFX.currentUser.getPermission().equals("INFORMATION_ENGINEERING_DEPARTMENT_HEAD")) {
			suspendButton.setVisible(true);
			suspendButton.setText("Un-Suspend");
			suspendButton.setDisable(true);
			if (currentChangeRequest.getStatus().equals("SUSPEND"))
			{
				suspendButton.setDisable(false);
			}
			else if (currentChangeRequest.getStatus().equals("ACTIVE"))
			{
				suspendButton.setDisable(true);
				suspendButton.setText("Suspend");
			} 
		}
	}
	
	/**
	 * This method shows all the files that uploaded for this specific request .
	 *
	 * @param downloadedFiles the downloaded files
	 */
	public void recieveFileList(List<MyFile> downloadedFiles) {
		if (downloadedFiles!=null && !downloadedFiles.isEmpty())
		{
			fileList.addAll(downloadedFiles);
		}
		else
		{
			fileListView.setDisable(false);
			filesErrorLabel.setVisible(true);
		}
	}
	
	/**
	 * This method allows to the user to download the files if necessary .
	 *
	 * @param myFile the my file
	 * @param path the path
	 */
	private void downloadFile(MyFile myFile,String path) {
		int i = myFile.getFileName().lastIndexOf('.');
		String extension = "";
		if (i > 0) {
		    extension = myFile.getFileName().substring(i+1);
		}
		
		File file = new File(path+"."+extension);
		System.out.println(myFile.getFileName());
		FileOutputStream fos;
		try 
		{
			fos = new FileOutputStream (file);
			BufferedOutputStream bos = new BufferedOutputStream (fos);
			try 
			{
				 bos.write(myFile.getMybytearray(),0,myFile.getSize());
				 bos.flush();
				 fos.flush();
				 Toast.makeText(ProjectFX.mainStage, "File downloaded successfully to the selected location ", 1500, 500, 500);
				 bos.close();
			}
			catch (IOException e) 
			{
				
			}
		} catch (FileNotFoundException e1) {
		}
		
		
	}
	
	/**
	 * In case the user have a supervisor permission, this button allows to the supervisor to 
	 * suspend or unsuspend a specific request .
	 *
	 * @param event the event
	 */
    @FXML
    void suspendChangeRequest(MouseEvent event) {
    	String updateStatus = null;
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Please confirm your decision");
    	if (currentChangeRequest.getStatus().equals("ACTIVE"))
    	{
    		alert.setContentText("Are you sure you want to SUSPEND the change request?");
    	}
    	else
    	{
    		alert.setContentText("Are you sure you want to UN-SUSPEND the change request?");
    	}
    	Optional<ButtonType> res = alert.showAndWait();
        if(res.isPresent()) 
        {
            if(res.get().equals(ButtonType.CANCEL))
            {
            	event.consume();
            	return;
            }
        }
    	
    	if (currentChangeRequest.getStatus().equals("ACTIVE"))
        {
        	updateStatus="SUSPEND";
        	currentChangeRequest.setStatus("SUSPEND");
        	StatusTF.setText("Suspend");
        	suspendButton.setText("Un-Suspend");
        	suspendButton.setDisable(true);
        }
        else if (currentChangeRequest.getStatus().equals("SUSPEND"))
        {
        	updateStatus="ACTIVE";
        	currentChangeRequest.setStatus("ACTIVE");
        	StatusTF.setText("Active");
        	suspendButton.setText("Suspend");
        	suspendButton.setDisable(true);
        }
        myController.updateStatusBySupervisor(currentChangeRequest.getChangeRequestID(), updateStatus);
    }
    /**/
    public void getEstimatedEndTimeForEachStep()
    {
    		myController.updateEstimatedTimeByStep(currentChangeRequest.getChangeRequestID(), currentChangeRequest.getCurrentStep());
    }
    public void parseEstimatedEndTime(String timeForStep)
    {
    	estimatedTimeForStepTF.setText(timeForStep);
    }
}