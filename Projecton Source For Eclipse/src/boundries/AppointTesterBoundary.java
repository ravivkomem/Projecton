package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.AppointTesterController;
import entities.ChangeRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppointTesterBoundary implements DataInitializable{

	/* **************************
	 * ****** FXML Objects ******
	 * **************************/
	
	/*ComboBox*/
    @FXML
    private ComboBox<String> committeMembersComboBox;
    /*Text*/
    @FXML
    private Text changeRequestNumberTextField;
    /*Table View*/
    @FXML
    private TableView<ChangeRequest> changeRequestDetailsTableView;
    @FXML
    private TableColumn<ChangeRequest, String> systemColumn;
    @FXML
    private TableColumn<ChangeRequest, String> changeDescriptionColumn;
    /*Button*/
    @FXML
    private Button setButton;
    @FXML
    private Button closeButton;
    
    /* **************************
	 * **** Private Objects *****
	 * **************************/
    private ChangeRequest myChangeRequest;
    private ObservableList<ChangeRequest> list = FXCollections.observableArrayList();
    private AppointTesterController myController = new AppointTesterController(this);
    private Alert alert = new Alert (AlertType.NONE);

    /* **************************
	 * ****** FXML Methods ******
	 * **************************/
    @FXML
    void closeAppointTesterPage(ActionEvent event) {
    	 Stage stage = (Stage) closeButton.getScene().getWindow();
    	 stage.close();
    }

    @FXML
    void setCommitteMember(ActionEvent event) {
    	if (committeMembersComboBox.getValue() == null)
    	{
    		alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText("ComboBox Error");
    		alert.setContentText("Please select committee member");
    		alert.showAndWait();
    	}
    }

    /* ****************************
   	 * ****** Public Methods ******
   	 * ****************************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Set the table view */
		systemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		changeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		
		changeRequestNumberTextField.setText("Loading information....");
		
		/*Hide all the objecs that require information */
		committeMembersComboBox.setVisible(false);
		changeRequestDetailsTableView.setVisible(false);
		setButton.setVisible(false);
	}

	@Override
	public void initData(Object data) {
		
		/*Get the change request from the caller page */
		if (data instanceof ChangeRequest)
		{
			ChangeRequest changeRequest = (ChangeRequest)data;
			myChangeRequest = changeRequest;
			myController.getAllCommitteMembers();
			list.add(myChangeRequest);
			
		}
		
	}

	/** 
	 * This method is for getting all the committeeMembers 
	 * Once the boundary receive it it will initialize the combobox and all the displays
	 * @param committeeMembersList
	 */
	public void recieveAllCommitteeMembers(ArrayList<String> committeeMembersList) {
		
		if (committeeMembersList.isEmpty())
		{
			Toast.makeText(ProjectFX.mainStage, "Error recieving committee members", 1500, 500, 500);
			this.closeAppointTesterPage(null);
		}
		else
		{
			committeMembersComboBox.setVisible(true);
			changeRequestDetailsTableView.setVisible(true);
			setButton.setVisible(true);
			committeMembersComboBox.getItems().addAll(committeeMembersList);
			changeRequestDetailsTableView.getItems().addAll(list);
			changeRequestNumberTextField.setText("Change Request No." + myChangeRequest.getChangeRequestID());
			
		}
		
	}

}
