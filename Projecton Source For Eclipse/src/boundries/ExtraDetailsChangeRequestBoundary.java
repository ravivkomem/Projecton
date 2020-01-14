package boundries;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class ExtraDetailsChangeRequestBoundary implements DataInitializable {

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	@FXML
	private TextField currentStepTF;
	@FXML
	private TextField estimatedTimeForStepTF;
    @FXML
    private Button backBtn;
    @FXML
    private TextField initiatorNameTF;
    @FXML
    private TextField subSystemTF;
    @FXML
    private TextArea currentStateDescTA;
    @FXML
    private TextArea RequestedChangeDescTF;
    @FXML
    private TextArea reasonTF;
    @FXML
    private TextArea commentTF;
    @FXML
    private TextField StatusTF;
    @FXML
    private Label filesErrorLabel;
    @FXML
    private Button logoutUser;
    @FXML
    private ListView<MyFile> fileListView;

    /* ***************************************
     * ********** Private Objects ***********
     * ***************************************/
    private static final int LIST_ROW_HEIGHT = 24;
    //private MyFile currentFile;
    private String previousPagePath;
    //private File downloadedFile;
    private ChangeRequest currentChangeRequest;
    private ExtraDetailsChangeRequestController myController = new ExtraDetailsChangeRequestController(this);
    private ObservableList<MyFile> fileList = FXCollections.observableArrayList();
    
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
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

    @FXML
    void logoutUser(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

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
	
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Object data) {
		try 
		{
			currentChangeRequest = (ChangeRequest)((ArrayList<Object>) data).get(0);
			previousPagePath = (String)((ArrayList<Object>) data).get(1);
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
			if (currentChangeRequest.getEstimatedEndDate()==null)
				estimatedTimeForStepTF.setText("In Evaluation");
			else
				estimatedTimeForStepTF.setText(currentChangeRequest.getEstimatedEndDate().toString());
			
			
			myController.getChangeRequestFiles(currentChangeRequest.getChangeRequestID());
		}
		catch (Exception e)
		{
			System.out.println("Couldn't init data in Extra details page");
			this.logoutUser(null);
		}
	}

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

}