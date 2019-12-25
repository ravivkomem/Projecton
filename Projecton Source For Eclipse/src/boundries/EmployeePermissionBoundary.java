package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
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
    private TextField permossionTextField;
    @FXML
    private ComboBox<String> newPremissionComboBox;
    @FXML
    private Button setNewPermissionTextField;
    
    private User employeeUser;
	private ArrayList<User> users = new ArrayList<>(); 
	
    @FXML
    void setNewEmployeePermission(MouseEvent event) {
    	if(newPremissionComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
			//enter text "Please select permission"
			return;
		}
		switch (newPremissionComboBox.getSelectionModel().getSelectedItem()) {
		case "INFORMATION_ENGINEER":
			
			break;
		case "SUPERVISOR":
			for(User u: users) {
				if(u.getPermission().equals("SUPERVISOR")) {
					//enter text "There is already user with supervisor permission"
					return;
				}
			}
			//make query that update the permission
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
				//enter text "There is 2 users with this permission"
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