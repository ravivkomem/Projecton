package boundries;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.WorkStationController;
import entities.ChangeRequestNew;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class WorkStationBoundary implements Initializable{

	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
	
	/*Text Field*/
    @FXML
    private TextField selectChangeRequestIdTextField;
	/* TableView */
    @FXML
    private TableView<ChangeRequestNew> changeRequestTableView;
    @FXML
    private TableColumn<ChangeRequestNew, Integer> requestIdColumn;
    @FXML
    private TableColumn<ChangeRequestNew, String> stepColumn;
    @FXML
    private TableColumn<ChangeRequestNew, String> descriptionColumn;
    @FXML
    private TableColumn<ChangeRequestNew, String> subsystemColumn;
    
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
    
    
    /*Private Variables */
    private WorkStationController myController = new WorkStationController(this);
	ObservableList<ChangeRequestNew> list = FXCollections.observableArrayList();
	
    /* FXML Methods */
    @FXML
    void displayAllWorkChangeRequests(MouseEvent event) {
    	myController.selectAllChangeRequestByHandlerUserName(ProjectFX.currentUser.getUserName());
    }

    @FXML
    void displayAnalysisStepChangeRequests(MouseEvent event) {
    	myController.selectAnalysisStepChangeRequestByHandlerUserName(ProjectFX.currentUser.getUserName());
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
    	Toast.makeText(ProjectFX.mainStage, refreshStationButton.getText() + " Button not implemented yet", 1500, 500, 500);
    }

    @FXML
    void userLogout(MouseEvent event) {
    	/*TODO: Remove user from connected list */
    	ProjectFX.currentUser = null;
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ProjectFX.pagingController.loadBoundray(ProjectPages.LOGIN_PAGE.getPath());
    }

    /* public methods */
    public void loadTableView(List<ChangeRequestNew> changeRequestsList)
    {
    	list.clear();
    	list.addAll(changeRequestsList);
    	changeRequestTableView.setVisible(true);
    	changeRequestTableView.setItems(list);
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, Integer>("changeRequestID"));
		stepColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, String>("currentStep"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, String>("desiredChangeDescription"));
		subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequestNew, String>("selectedSubsystem"));
		
		changeRequestTableView.setRowFactory(tv -> {
		    TableRow<ChangeRequestNew> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
		        {
		        	ChangeRequestNew clickedChangeRequest = row.getItem();
		        	selectChangeRequestIdTextField.setText(Integer.toString(clickedChangeRequest.getChangeRequestID()));
		        	
		        }
		    });
		    return row ;
		});
		
		changeRequestTableView.setVisible(false);
		this.displayAllWorkChangeRequests(null);
	}

}
