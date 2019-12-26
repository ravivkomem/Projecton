package boundries;

import java.awt.Window;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


import controllers.EmployeePermissionController;
import entities.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeePermissionBoundary implements DataInitializable{

    @FXML
    private Text employeeNameText;
    @FXML
    private Text errorText;
    
    @FXML
    private TextField permossionTextField;
    @FXML
    private ComboBox<String> newPremissionComboBox;
    @FXML
    private Button setNewPermissionTextField;
    
    private User employeeUser;
	private ArrayList<User> users = new ArrayList<>(); 
	private EmployeePermissionController myController = new EmployeePermissionController(this);
	private TechManagerBoundary techManagerBoundry;
	
    @FXML
    void setNewEmployeePermission(MouseEvent event) {
    	if(newPremissionComboBox.getSelectionModel().isEmpty()) {
    		errorText.setText("Please select permission");
    		errorText.setVisible(true);
			return;
		}
		switch (newPremissionComboBox.getSelectionModel().getSelectedItem()) {
		case "INFORMATION_ENGINEER":
			errorText.setVisible(false);
			employeeUser.setPermission("INFORMATION_ENGINEER");
			employeeUser.setJobDescription("Information Engineer");
			myController.updateEmployeePermission("INFORMATION_ENGINEER","Information Engineer",employeeUser.getUserID());
			techManagerBoundry.setEmployeeListChanges(employeeUser);
			break;
		case "SUPERVISOR":
			errorText.setVisible(false);
			for(User u: users) {
				if(u.getPermission().equals("SUPERVISOR")) {
					handleSupervasior(u,employeeUser);
					((Node) event.getSource()).getScene().getWindow().hide();
				}
			}
			//Check if this user is committee for give him SUPERVISOR_COMMITTEE permission
			employeeUser.setPermission("SUPERVISOR");
			employeeUser.setJobDescription("Supervisor");
			myController.updateEmployeePermission("SUPERVISOR","Supervisor",employeeUser.getUserID());
			techManagerBoundry.setEmployeeListChanges(employeeUser);
			break;
		case "COMMITTEE_MEMBER":
			errorText.setVisible(false);
			int cnt=0;
			for(User u: users) {
				if(u.getPermission().equals("COMMITTEE_MEMBER")) {
					cnt++;
				}
			}
			if(cnt==2) {
				errorText.setText("There are already 2 users with this permission");
				errorText.setVisible(true);
			}
			else {
				employeeUser.setPermission("COMMITTEE_MEMBER");
				employeeUser.setJobDescription("Committee member");
				myController.updateEmployeePermission("COMMITTEE_MEMBER","Committee member",employeeUser.getUserID());
				techManagerBoundry.setEmployeeListChanges(employeeUser);
			}
			break;
		case "COMMITTEE_DIRECTOR":
			errorText.setVisible(false);
			for(User u: users) {
				if(u.getPermission().equals("COMMITTEE_DIRECTOR")) {
					//handleSupervasior(u,employeeUser);
					((Node) event.getSource()).getScene().getWindow().hide();
				}
			}
			employeeUser.setPermission("COMMITTEE_DIRECTOR");
			employeeUser.setJobDescription("Committee Director");
			myController.updateEmployeePermission("COMMITTEE_DIRECTOR","Committee Director",employeeUser.getUserID());
			techManagerBoundry.setEmployeeListChanges(employeeUser);
			break;
		default:
			break;
		}
		popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
		((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    /**
     * this method handle with the problem that tech manager give permission that already exist
     * to anther user
     * @param newSupervasior
     * @param oldSuperVaser
     */
    private void handleSupervasior(User newSupervasior, User oldSuperVaser) {
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with supervisor permission\nDo you want to replace?");
        if(result.get() == ButtonType.OK) {
//        	if(newSupervasior.getPermission().equals("COMMITTEE_MEMBER")) {
//        		
//        	}
//        	else if(newSupervasior.getPermission().equals("COMMITTEE_DIRECTOR")) {
//        		
//        	}
        	//else{
        	newSupervasior.setPermission("SUPERVISOR");
        	newSupervasior.setJobDescription("Supervisor");
        	oldSuperVaser.setPermission("INFORMATION_ENGINEER");
        	oldSuperVaser.setJobDescription("Information Engineer");
        	myController.updateEmployeePermission("SUPERVISOR","Supervisor",newSupervasior.getUserID());
        	myController.updateEmployeePermission("INFORMATION_ENGINEER","Information Engineer",oldSuperVaser.getUserID());
        	techManagerBoundry.setEmployeeListChanges(newSupervasior);
        	techManagerBoundry.setEmployeeListChanges(oldSuperVaser);
        }
        else if(result.get() == ButtonType.CANCEL||result.get() == ButtonType.CLOSE){
        	System.out.println("cancel");
        }
    }
    
    private void handleCommitteeDirector(User newSupervasior, User oldSuperVaser) {
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with supervisor permission\nDo you want to replace?");
        /*TODO the same like handleSupervisor*/
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		newPremissionComboBox.getItems().add("INFORMATION_ENGINEER");
		newPremissionComboBox.getItems().add("SUPERVISOR");
		newPremissionComboBox.getItems().add("COMMITTEE_MEMBER");
		newPremissionComboBox.getItems().add("COMMITTEE_DIRECTOR");
	}

	@Override
	public void initData(Object data) {
		employeeUser = (User)(((ArrayList<ArrayList<Object>>) data).get(0).get(0));
		users.addAll(((ArrayList<ArrayList<User>>) data).get(1));
		techManagerBoundry = (TechManagerBoundary)(((ArrayList<ArrayList<Object>>) data).get(2).get(0));
		employeeNameText.setText("Permission: "+employeeUser.getFirstName()+" "+employeeUser.getLastName());
		permossionTextField.setText(employeeUser.getPermission());
	}
	
	/* this method will show the window with the new change request id */
	public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		return alert2.showAndWait();
	}

}