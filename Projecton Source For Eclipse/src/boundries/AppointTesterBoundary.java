package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.ProjectPages;
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

/**
 * 
 * @author raviv komem
 * This class represents the appoint tester boundary
 * with all the methods and logic implementations
 */
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
    
    /**
     * This method closes the appoint tester page
     * @param event - mouse click on "close" button
     */
    @FXML
    void closeAppointTesterPage(ActionEvent event) {
    	 Stage stage = (Stage) closeButton.getScene().getWindow();
    	 stage.close();
    }

    /**
     * Sets the committee member to test the change request
     * @param event - mouse click on "set" button
     * If a committee member is selected then he is set
     * else an alert will be displayed
     */
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
    	else
    	{
    		committeMembersComboBox.setEditable(false);
    		myController.updateChangeRequestStepAndHandler(committeMembersComboBox.getValue(), myChangeRequest.getChangeRequestID());
    		
    	}
    }

    /* ****************************
   	 * ****** Public Methods ******
   	 * ****************************/
    /**
     * Init all the FXML objects in the boundary
     */
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

	/**
	 * Expects to be called on the initialize of the boundary
	 * will receive change request object
	 */
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

	/**
	 * This method is called by the controller after receiving change request update results
	 * @param affectedRows - number of rows in the database that were affected
	 * According to the value will display proper message
	 */
	public void recieveChangeRequestUpdateResult(int affectedRows) {
		
		if (affectedRows == 1)
		{
			/* Change request update success */
			myController.createNewTesterStep(committeMembersComboBox.getValue(), myChangeRequest.getChangeRequestID());
		}
		else
		{
			Toast.makeText(ProjectFX.mainStage, "Error with connection to Database, please try again", 1500, 500, 500);
			committeMembersComboBox.setEditable(true);
		}
		
	}

	/**
	 * This method is called by the controller after receiving new tester step update results
	 * @param affectedRows - number of rows in the database that were affected
	 * According to the value will display proper message
	 */
	public void recieveNewTesterStepResult(int affectedRows) {
		if (affectedRows == 1)
		{
			Toast.makeText(ProjectFX.mainStage, "Tester was appointed successfully", 1500, 500, 500);
			this.closeAppointTesterPage(new ActionEvent());
			ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
		}
		else
		{
			Toast.makeText(ProjectFX.mainStage, "Error with connection to Database, please try again", 1500, 500, 500);
			committeMembersComboBox.setEditable(true);
		}
		
	}

}
