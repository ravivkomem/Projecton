package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

// TODO: Auto-generated Javadoc
/**
 * This is the menu boundary.
 *
 * @author Raviv Komem
 */
public class MenuBoundary implements Initializable {

	/** The hello user text field. */
	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
    @FXML
    private Text helloUserTextField;
    
    /** The logout button. */
    @FXML
    private Button logoutButton;
    
    /** The ac dash bord. */
    @FXML
    private AnchorPane acDashBord;
    
    /** The upload request button. */
    @FXML
    private Button uploadRequestButton;
    
    /** The view my requests button. */
    @FXML
    private Button viewMyRequestsButton;
    
    /** The work station button. */
    @FXML
    private Button workStationButton;
    
    /** The supervisor button. */
    @FXML
    private Button supervisorButton;
    
    /** The department head button. */
    @FXML
    private Button departmentHeadButton;
    
    /** The menu image break. */
    @FXML
    private ImageView menuImageBreak;
    
    /** The upload request image break. */
    @FXML
    private ImageView uploadRequestImageBreak;
    
    /** The my requests image break. */
    @FXML
    private ImageView myRequestsImageBreak;
    
    /** The work station department head image break. */
    @FXML
    private ImageView workStation_DepartmentHeadImageBreak;
    
    /** The supervisor image break. */
    @FXML
    private ImageView supervisorImageBreak;
    
    /* *************************************
	 * ********* FXML Methods **************
	 * *************************************/
    /**
     * This method is used to display the current user requests.
     *
     * @param event - mouse click on "my requests" button
     */
    @FXML
    void loadMyRequestsPage(ActionEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.REQUEST_LIST_PAGE.getPath());
    }

    /**
     * This method is used to display the supervisor page.
     *
     * @param event - mouse click on "supervisor" button
     */
    @FXML
    void loadSupervisorPage(ActionEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.SUPERVISOR_PAGE.getPath());
    }

    /**
     * This method is used to display the upload request page.
     *
     * @param event - mouse click on "upload request" button
     */
    @FXML
    void loadUploadRequestPage(ActionEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.UPLOAD_REQUEST_PAGE.getPath());
    }

    /**
     * This method is used to display the tech manager page.
     *
     * @param event - mouse click on "tech manager" button
     */
    @FXML
    void loadDepartmentHeadPage(ActionEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.TECH_MANAGER_PAGE.getPath());
    }
    
    /**
     * This method is used to display the work station page.
     *
     * @param event - mouse click on "workstation" button
     */
    @FXML
    void loadWorkStationPage(ActionEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
    }

    /**
     * This method is used for user logout.
     *
     * @param event - mouse click on "Logout" button
     */
    @FXML
    void userLogout(MouseEvent event) {
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    /**
     * Sets the initial page display according to the user viewing it.
     */
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
		case "SUPERVISOR_COMMITTEE_DIRECTOR":
		case "SUPERVISOR_COMMITTEE_MEMBER":
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
