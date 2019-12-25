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
	
    @FXML
    void setNewEmployeePermission(MouseEvent event) {
    	if(newPremissionComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
    		errorText.setText("Please select permission");
			return;
		}
		switch (newPremissionComboBox.getSelectionModel().getSelectedItem()) {
		case "INFORMATION_ENGINEER":
			myController.updateEmployeePermission("INFORMATION_ENGINEER",employeeUser.getUserID());
			break;
		case "SUPERVISOR":
			for(User u: users) {
				if(u.getPermission().equals("SUPERVISOR")) {
					errorText.setText("There is already user with supervisor permission");
					return;
				}
			}
			myController.updateEmployeePermission("SUPERVISOR",employeeUser.getUserID());
			break;
		case "COMMITTEE_MEMBER":
			int cnt=0;
			for(User u: users) {
				if(u.getPermission().equals("COMMITTEE_MEMBER")) {
					cnt++;
					return;
				}
			}
			if(cnt==2) {
				errorText.setText("There are already 2 users with this permission");
			}
			else {
				myController.updateEmployeePermission("COMMITTEE_MEMBER",employeeUser.getUserID());
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
		users.addAll(((ArrayList<ArrayList<User>>) data).get(0));
		employeeNameText.setText("Permission: "+employeeUser.getFirstName()+" "+employeeUser.getLastName());
		permossionTextField.setText(employeeUser.getPermission());
	}

}