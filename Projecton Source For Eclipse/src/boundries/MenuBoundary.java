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
    private Button supervisorButton;
    @FXML
    private Button departmentHeadButton;
    @FXML
    private ImageView menuImageBreak;
    @FXML
    private ImageView uploadRequestImageBreak;
    @FXML
    private ImageView myRequestsImageBreak;
    @FXML
    private ImageView workStation_DepartmentHeadImageBreak;
    @FXML
    private ImageView supervisorImageBreak;
    
    /*Boundary Methods*/
    @FXML
    void loadMyRequestsPage(ActionEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.REQUEST_LIST_PAGE.getPath());
    }

    @FXML
    void loadSupervisorPage(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, supervisorButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadUploadRequestPage(ActionEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.UPLOAD_REQUEST_PAGE.getPath());
    }

    @FXML
    void loadDepartmentHeadPage(ActionEvent event) {
    	Toast.makeText(ProjectFX.mainStage, departmentHeadButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }
    
    @FXML
    void loadWorkStationPage(ActionEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }

    @FXML
    void userLogout(MouseEvent event) {
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    /*Hide all buttons*/
		uploadRequestButton.setVisible(false);
	    viewMyRequestsButton.setVisible(false);
	    workStationButton.setVisible(false);
	    departmentHeadButton.setVisible(false);
	    supervisorButton.setVisible(false);
	    /*Hide all image breaks*/
	    uploadRequestImageBreak.setVisible(false);
	    myRequestsImageBreak.setVisible(false);
	    workStation_DepartmentHeadImageBreak.setVisible(false);
	    supervisorImageBreak.setVisible(false);

		helloUserTextField.setText("Hello " + ProjectFX.currentUser.getFirstName() + " " + ProjectFX.currentUser.getLastName());
		
		/* Displaying the proper buttons */
		switch (ProjectFX.currentUser.getPermission())
		{
		
		case "INFORMATION_ENGINEERING_DEPARTMENT_HEAD":
			uploadRequestButton.setVisible(true);
			uploadRequestImageBreak.setVisible(true);
			
		    viewMyRequestsButton.setVisible(true);
		    myRequestsImageBreak.setVisible(true);
		    
		    departmentHeadButton.setVisible(true);
		    workStation_DepartmentHeadImageBreak.setVisible(true);
		    break;
		    
		case "SUPERVISOR":
			uploadRequestButton.setVisible(true);
			uploadRequestImageBreak.setVisible(true);
			
		    viewMyRequestsButton.setVisible(true);
		    myRequestsImageBreak.setVisible(true);
		    
		    workStationButton.setVisible(true);
		    workStation_DepartmentHeadImageBreak.setVisible(true);
		    
		    supervisorButton.setVisible(true);
		    supervisorImageBreak.setVisible(true);
		    break;
		    
		/*Same displays for committee members and information engineers*/
		case "COMMITTEE_DIRECTOR":
		case "COMMITTEE_MEMBER":
		case "INFORMATION_ENGINEER":
			uploadRequestButton.setVisible(true);
			uploadRequestImageBreak.setVisible(true);
			
		    viewMyRequestsButton.setVisible(true);
		    myRequestsImageBreak.setVisible(true);
		    
		    workStationButton.setVisible(true);
		    workStation_DepartmentHeadImageBreak.setVisible(true);
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
