package boundries;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import controllers.DemoLandingController;
import entities.ChangeRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import other.ServerEvent;
import other.SqlResult;
import other.Toast;

public class DemoLandingBoundry implements Initializable {

	/* FXML Text Fields */
    @FXML private TextField changeRequestTextField;
    @FXML private TextField intiatorNameTextField;
    @FXML private TextField subsystemTextField;
    @FXML private TextField currentStateTextField;
    @FXML private TextField statusTextField;
    @FXML private TextField changeDescriptionTextField;
    @FXML private TextField handlerNameTextField;

    /* FXML Buttons */
    @FXML private Button viewButton;
    @FXML private Button updateButton;
    
    /* FXML TableView */
    @FXML private TableView<ChangeRequest> changeRequestDetailsTableView;
    @FXML private TableColumn<ChangeRequest, String> initaitorColumn;
    @FXML private TableColumn<ChangeRequest, String> subsystemColumn;
    @FXML private TableColumn<ChangeRequest, String> currentStateColumn;
    @FXML private TableColumn<ChangeRequest, String> changeDescriptionColumn;
    @FXML private TableColumn<ChangeRequest, String> handlerColumn;  
    @FXML private TableColumn<ChangeRequest, String> statusColumn;
   
    /* Boundry Stage */
    @FXML private AnchorPane stagePane;
    
    /* Local variables */
    private ChangeRequest currentChangeRequest;
    private DemoLandingController demoLandingController = new DemoLandingController(this);
    ObservableList<ChangeRequest> list =  FXCollections.observableArrayList();
    
    /* Methods */
    @FXML
    void getChangeRequestDetails(MouseEvent event) {
    	/*TODO: Change visibility to be true once result received */
    	changeRequestDetailsTableView.setVisible(true);
    	updateButton.setVisible(true);
    	demoLandingController.getChangeRequestById(changeRequestTextField.getText());
    	
    }
    
    public void displayChangeRequestDetails(ChangeRequest result)
    {
    	list.clear();
    	if (result == null)
    	{
    		/*TODO: Error no result found */
    	}
    	else
    	{
    		/*TODO: Display the TableView and TextFields */
    		list.add(result);
        	this.currentChangeRequest = result;
        	changeRequestDetailsTableView.setItems(list);
        	
        	intiatorNameTextField.setText(currentChangeRequest.getInitiator());
        	subsystemTextField.setText(currentChangeRequest.getSelectSysystem());
        	currentStateTextField.setText(currentChangeRequest.getCurrentStateDiscription());
        	statusTextField.setText(currentChangeRequest.getChangeRequestStatus());
        	changeDescriptionTextField.setText(currentChangeRequest.getChangeRequestDescription());
        	handlerNameTextField.setText(currentChangeRequest.getHandler());
    	}	
    }
    
    
    @FXML
    void updateChangeRequestDetails(MouseEvent event) {
    	
    	/*TODO: Consider do something for empty text field */
    	currentChangeRequest.setInitiator(intiatorNameTextField.getText());
    	currentChangeRequest.setSelectSysystem(subsystemTextField.getText());
    	currentChangeRequest.setCurrentStateDiscription(currentStateTextField.getText());   
     	currentChangeRequest.setChangeRequestStatus(statusTextField.getText());
    	currentChangeRequest.setChangeRequestDescription(changeDescriptionTextField.getText());
    	currentChangeRequest.setHandler(handlerNameTextField.getText());
    	demoLandingController.updateChangeRequest(currentChangeRequest);
    }
    
    public void getChangeRequestUpdateDetails (int affectedRows)
    {
    	if (affectedRows <= 0)
    	{
    		/*TODO: Error no update */
    	}
    	else 
    	{
    		/*TODO: Add pop up window for successful update */
    		Toast.makeText(ProjectFX.mainStage, "Update Finished Successfully", 3000, 500, 500);
    		demoLandingController.getChangeRequestById(Integer.toString(currentChangeRequest.getChangeRequestID()));
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initaitorColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("initiator"));
    	subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectSysystem"));
    	currentStateColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("currentStateDiscription"));
    	changeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("changeRequestDescription"));
    	statusColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("changeRequestStatus"));
    	handlerColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("handler"));
	}  

}
