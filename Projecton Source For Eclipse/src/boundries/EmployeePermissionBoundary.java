package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.EmployeePermissionController;
import entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
					errorText.setText("There is already user with supervisor permission");
					errorText.setVisible(true);
					return;
				}
			}
			employeeUser.setPermission("SUPERVISOR");
			employeeUser.setJobDescription("Supervisor");
			myController.updateEmployeePermission("SUPERVISOR","Supervisor",employeeUser.getUserID());
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
			}
			break;
		default:
			break;
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		newPremissionComboBox.getItems().add("INFORMATION_ENGINEER");
		newPremissionComboBox.getItems().add("SUPERVISOR");
		newPremissionComboBox.getItems().add("COMMITTEE_MEMBER");
	}

	@Override
	public void initData(Object data) {
		employeeUser = (User)(((ArrayList<ArrayList<Object>>) data).get(0).get(0));
		users.addAll(((ArrayList<ArrayList<User>>) data).get(1));
		techManagerBoundry = (TechManagerBoundary)(((ArrayList<ArrayList<Object>>) data).get(2).get(0));
		employeeNameText.setText("Permission: "+employeeUser.getFirstName()+" "+employeeUser.getLastName());
		permossionTextField.setText(employeeUser.getPermission());
	}

}