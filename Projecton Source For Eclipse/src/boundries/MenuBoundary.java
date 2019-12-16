package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MenuBoundary implements Initializable {

	/* FXML Objects */
    @FXML
    private Text helloUserTextField;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private Button uploadRequestButton;
    @FXML
    private Button viewMyRequestsButton;
    @FXML
    private Button workStationButton;
    @FXML
    private Button techManagerButton;
    @FXML
    private ImageView menuImageBreak;
    @FXML
    private ImageView uploadRequestImageBreak;
    @FXML
    private ImageView myRequestsImageBreak;
    @FXML
    private ImageView workStationImageBreak;
    @FXML
    private ImageView techManagerImageBreak;

    /*Boundary Methods*/
    @FXML
    void loadMyRequestsPage(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewMyRequestsButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadTechManagerPage(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, techManagerButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadUploadRequestPage(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, uploadRequestButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadWorkStationPage(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, workStationButton.getText() + " Button not implemented yet", 1500, 500, 500);
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
	    uploadRequestButton.setVisible(false);
	    viewMyRequestsButton.setVisible(false);
	    workStationButton.setVisible(false);
	    techManagerButton.setVisible(false);
	    uploadRequestImageBreak.setVisible(false);
	    myRequestsImageBreak.setVisible(false);
	    workStationImageBreak.setVisible(false);
	    techManagerImageBreak.setVisible(false);

	    
		helloUserTextField.setText("Hello " + ProjectFX.currentUser.getFirstName() + " " + ProjectFX.currentUser.getLastName());
		
		/* Displaying the proper buttons */
		switch (ProjectFX.currentUser.getPermission())
		{
		case "SUPERVISOR":
			uploadRequestButton.setVisible(true);
			uploadRequestImageBreak.setVisible(true);
			
		    viewMyRequestsButton.setVisible(true);
		    myRequestsImageBreak.setVisible(true);
		    
		    workStationButton.setVisible(true);
		    workStationImageBreak.setVisible(true);
		    
		    techManagerButton.setVisible(true);
		    techManagerImageBreak.setVisible(true);
		    break;
		    
		case "INFORMATION_ENGINEER":
			uploadRequestButton.setVisible(true);
			uploadRequestImageBreak.setVisible(true);
			
		    viewMyRequestsButton.setVisible(true);
		    myRequestsImageBreak.setVisible(true);
		    
		    workStationButton.setVisible(true);
		    workStationImageBreak.setVisible(true);
		    break;
		    
		case "BASIC_USER":
			uploadRequestButton.setVisible(true);
			uploadRequestImageBreak.setVisible(true);
			
			viewMyRequestsButton.setVisible(true);
			myRequestsImageBreak.setVisible(true);
			break;
			
		default:
			break;
		}
		
		
	}

}
