package boundries;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import entities.ChangeRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class WorkStationBoundary implements Initializable{

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	
	/* TableView */
    @FXML
    private TableView<ChangeRequest> changeRequestTableView;
    @FXML
    private TableColumn<ChangeRequest, Integer> requestIdColumn;
    @FXML
    private TableColumn<ChangeRequest, String> stepColumn;
    @FXML
    private TableColumn<ChangeRequest, String> descriptionColumn;
    @FXML
    private TableColumn<ChangeRequest, String> subsystemColumn;
    
    /*Buttons*/
    @FXML
    private Button refreshStationButton;
    @FXML
    private Button homePageButton;
    @FXML
    private Button viewAllWorkButton;
    @FXML
    private Button viewAnalysisStepWorkButton;
    @FXML
    private Button viewExecutionStepButton;
    @FXML
    private Button viewTesterStepButton;
    @FXML
    private Button viewCommitteStepButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button startChangeRequestWorkButton;
    
    /* FXML Methods */
    @FXML
    void displayAllWorkChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewAllWorkButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void displayAnalysisStepChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewAnalysisStepWorkButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void displayCommitteDecisionChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewCommitteStepButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void displayExecutionStepChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewExecutionStepButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void displayTesterStepChangeRequests(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, viewTesterStepButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadChangeRequestWorkPage(MouseEvent event) {
    	Toast.makeText(ProjectFX.mainStage, startChangeRequestWorkButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void loadHomePage(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ProjectFX.pagingController.loadBoundray(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void refreshStation(ActionEvent event) {

    }

    /* public methods */
    public void loadTableView(List<ChangeRequest> changeRequestsList)
    {
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
