package boundries;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import entity.ChangeRequest;
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
import other.ServerEvent;
import other.SqlResult;

public class DemoLandingBoundries implements Initializable {

    @FXML
    private TextField changeRequestTextField;

    @FXML
    private Button viewButton;

    @FXML
    private TableView<ChangeRequest> changeRequestDetailsTableView;
    
    @FXML
    private TableColumn<ChangeRequest, String> subsystemColumn;
    
    @FXML
    private TableColumn<ChangeRequest, String> initaitorColumn;

    @FXML
    private TableColumn<ChangeRequest, String> currentStateColumn;

    @FXML
    private TableColumn<ChangeRequest, String> changeDescriptionColumn;

    @FXML
    private TableColumn<ChangeRequest, String> handlerColumn;
    
    @FXML
    private TableColumn<ChangeRequest, String> statusColumn;
    
    @FXML
    private Button updateButton;
    
    private DemoLandingController demoLandingController = new DemoLandingController(this);
    
    ObservableList<ChangeRequest> list;
    
    @FXML
    void displayChangeRequestDetails(MouseEvent event) {
    	System.out.println(changeRequestTextField.getText());
    	changeRequestDetailsTableView.setVisible(true);
    	updateButton.setVisible(true);
    	//ChatClient.changeRequestByIdListeners.add(this);
    	demoLandingController.getChangeRequestById(changeRequestTextField.getText());
    }
    @FXML
    void updateChangeRequestTable(MouseEvent event) {
    	
    }
    public void displayChangeRequestTable(ArrayList<ChangeRequest> results)
    {
    	list = FXCollections.observableArrayList(results);
    	changeRequestDetailsTableView.setItems(list);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		handlerColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("intiator"));
    	subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectSysystem"));
    	currentStateColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("currentStateDiscription"));
    	changeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("changeRequestDescription"));
    	statusColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("changeRequestStatus"));
    	handlerColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("handler"));
	}
    

}
