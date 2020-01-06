package boundries;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.ProjectPages;
import controllers.ExtraDetailsChangeRequestController;
import entities.ChangeRequest;
import entities.MyFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ExtraDetailsChangeRequestBoundary implements DataInitializable {

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
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
    private Button viewUploadFileBtn;

    @FXML
    private TextArea reasonTF;

    @FXML
    private TextArea commentTF;

    @FXML
    private TextField StatusTF;

    @FXML
    private Button logoutUser;

    /* ***************************************
     * ********** Private Objects ***********
     * ***************************************/
    private String previousPagePath;
    private File uploadedFile;
    private ChangeRequest currentChangeRequest;
    private ExtraDetailsChangeRequestController myController = new ExtraDetailsChangeRequestController(this);
    
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    @FXML
    void backBtn(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(previousPagePath);
    }

    @FXML
    void logoutUser(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    @FXML
    void viewUploadFileBtn(MouseEvent event) {
    	myController.getChangeRequestFile(currentChangeRequest.getChangeRequestID());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initiatorNameTF.setEditable(false);
		subSystemTF.setEditable(false);
		RequestedChangeDescTF.setEditable(false);
		reasonTF.setEditable(false);
		commentTF.setEditable(false);
		StatusTF.setEditable(false);
	}
	
	@Override
	public void initData(Object data) {
		currentChangeRequest = (ChangeRequest)((ArrayList<Object>) data).get(0);
		previousPagePath = (String)((ArrayList<Object>) data).get(1);
		initiatorNameTF.setText(currentChangeRequest.getInitiatorUserName());
		subSystemTF.setText(currentChangeRequest.getSelectedSubsystem());
		currentStateDescTA.setText(currentChangeRequest.getCurrentStateDescription());
		RequestedChangeDescTF.setText(currentChangeRequest.getDesiredChangeDescription());
		reasonTF.setText(currentChangeRequest.getDesiredChangeExplanation());
		commentTF.setText(currentChangeRequest.getDesiredChangeComments());
		StatusTF.setText(currentChangeRequest.getStatus());
		reasonTF.setWrapText(true);
		commentTF.setWrapText(true);
		RequestedChangeDescTF.setWrapText(true);
		currentStateDescTA.setWrapText(true);
		
	}

	public void displayFile(MyFile downloadedFile) {
		
		System.out.println(downloadedFile.getFileName());
		
		byte [] mybytearray  = new byte [(int)downloadedFile.getFileName().length()];
		downloadedFile.initArray(mybytearray.length);
		downloadedFile.setSize(mybytearray.length);
	      
		try (FileOutputStream fileOuputStream = new FileOutputStream("R:\\temp.jpg"))
		 {
			 fileOuputStream.write(downloadedFile.getMybytearray());
			 uploadedFile = new File("R:\\temp.jpg");
			 
			 FileOutputStream fos = new FileOutputStream (uploadedFile);
			 BufferedOutputStream bos = new BufferedOutputStream (fos);
			 
			 if(!Desktop.isDesktopSupported()){
		            System.out.println("Desktop is not supported");
		            return;
		        }
		        
		        Desktop desktop = Desktop.getDesktop();
		        if(uploadedFile.exists()) desktop.open(uploadedFile);
		 } 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}