package boundries;

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

public class EmployeePermissionBoundary implements DataInitializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
    @FXML
    private Text employeeNameText;
    @FXML
    private Text errorText;
    @FXML
    private ComboBox<String> committeeMemberComboBox;
    @FXML
    private TextField permossionTextField;
    @FXML
    private ComboBox<String> newPremissionComboBox;
    @FXML
    private Button setNewPermissionTextField;
    @FXML
    private Button replaceMemberButton;
    
    /* *************************************
   	 * ******* Private Objects *************
   	 * *************************************/
    private User employeeUser;
	private ArrayList<User> users = new ArrayList<>(); 
	private EmployeePermissionController myController = new EmployeePermissionController(this);
	private TechManagerBoundary techManagerBoundry;
	
	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
	
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
					handleSupervasior(employeeUser,u);
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
					committeeMemberComboBox.getItems().add(u.getUserName());
					cnt++;
				}
			}
			if(cnt==2) {
				Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
		        		+ "2 users with committee member permission\nDo you want to replace?");
		    	if (result.get() == ButtonType.OK) {
		    		setNewPermissionTextField.setVisible(false);
		    		committeeMemberComboBox.setVisible(true);
		    		replaceMemberButton.setVisible(true);
		    	}
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
					handleCommitteeDirector(employeeUser,u);
					((Node) event.getSource()).getScene().getWindow().hide();
				}
			}
			//Check if this user is committee for give him SUPERVISOR_COMMITTEE permission
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
    
    @FXML
    void replaceCommitteMember(MouseEvent event) {
    	if(committeeMemberComboBox.getSelectionModel().isEmpty()) {
    		errorText.setText("Please choose commitee member");
    		return;
    	}
    	User oldMember = null;
    	for(User u : users) {
    		if(u.getUserName().equals(committeeMemberComboBox.getSelectionModel().getSelectedItem())) {
    			oldMember = u;
    		}
    	}
		handelCommitteeMember(employeeUser,oldMember);
    }
    
    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
    
    /**
     * this method gets to users and change there permission
     * @param newUser
     * @param oldUser
     */
    private void createPermissoinsToUsers(User newUser, User oldUser) {
    	myController.updateEmployeePermission(newUser.getPermission(),newUser.getJobDescription(),newUser.getUserID());
    	myController.updateEmployeePermission(oldUser.getPermission(),oldUser.getJobDescription(),oldUser.getUserID());
    	techManagerBoundry.setEmployeeListChanges(newUser);
    	techManagerBoundry.setEmployeeListChanges(oldUser);
    }
    
    /**
     * this method handle with committee member permission
     * @param newMember
     * @param oldMember
     */
    private void handelCommitteeMember(User newMember,User oldMember) {
    	int flag = 0;
    	if(newMember.getPermission().equals("SUPERVISER")) {
    		newMember.setPermission("SUPERVISER_COMMITTEE_MEMBER");
    		newMember.setJobDescription("Supervisor Committee Member");
    	} else if(newMember.getPermission().equals("COMMITTEE_DIRECTOR")) {
    		flag = 1;
    		newMember.setPermission("COMMITTEE_MEMBER");
    		newMember.setJobDescription("Committee Member");
    	} else if(newMember.getPermission().equals("SUPERVISER_COMMITTEE_DIRECTOR")) {
    		flag = 1;
    		newMember.setPermission("SUPERVISER_COMMITTEE_MEMBER");
    		newMember.setJobDescription("Supervisor Committee Member");
    	}else {
    		newMember.setPermission("COMMITTEE_MEMBER");
    		newMember.setJobDescription("Committee Member");
    	}
    	if(oldMember.getPermission().equals("SUPERVISER_COMMITTEE_MEMBER")) {
    		if(flag==0) {
    			oldMember.setPermission("SUPERVISOR");
    			oldMember.setJobDescription("Supervisor");
    		}
    		else {
    			oldMember.setPermission("SUPERVISER_COMMITTEE_DIRECTOR");
    			oldMember.setJobDescription("Supervisor Committee Director");
    		}
    	}
    	else {
    		if(flag==0) {
    			oldMember.setPermission("INFORMATION_ENGINEER");
    			oldMember.setJobDescription("Information Emgineer");
    		}
    		else {
    			oldMember.setPermission("COMMITTEE_DIRECTOR");
    			oldMember.setJobDescription("Committee Director");
    		}
    	}
    	
    }
    
    /**
     * this method handle with the problem that tech manager give permission that already exist
     * to anther user
     * @param newSupervisor
     * @param oldSupervisor
     */
    private void handleSupervasior(User newSupervisor, User oldSupervisor) {
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with supervisor permission\nDo you want to replace?");
		if (result.get() == ButtonType.OK) {
			if (newSupervisor.getPermission().equals("COMMITTEE_MEMBER")) {
				newSupervisor.setPermission("SUPERVISER_COMMITTEE_MEMBER");
				newSupervisor.setJobDescription("Supervisor Committee Member");
			} else if (newSupervisor.getPermission().equals("COMMITTEE_DIRECTOR")) {
				newSupervisor.setPermission("SUPERVISER_COMMITTEE_DIRECTOR");
				newSupervisor.setJobDescription("Supervisor Committee Director");
			} else {
				newSupervisor.setPermission("SUPERVISOR");
				newSupervisor.setJobDescription("Supervisor");
			}
			if (oldSupervisor.getPermission().equals("SUPERVISER_COMMITTEE_MEMBER")) {
				oldSupervisor.setPermission("COMMITTEE_MEMBER");
				oldSupervisor.setJobDescription("Committee Member");
			} else if (oldSupervisor.getPermission().equals("SUPERVISER_COMMITTEE_DIRECTOR")) {
				oldSupervisor.setPermission("COMMITTEE_DIRECTOR");
				oldSupervisor.setJobDescription("Committee Director");
			} else {
				oldSupervisor.setPermission("INFORMATION_ENGINEER");
				oldSupervisor.setJobDescription("Information Engineer");
			}
			this.createPermissoinsToUsers(newSupervisor, oldSupervisor);
		}
    }
    
    /**
     * this method handle with the problem that tech manager give permission that already exist
     * to anther user
     * @param newSupervasior
     * @param oldSuperVaser
     */
    private void handleCommitteeDirector(User newDirector, User oldDirector) {
    	int flag=0;
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with supervisor permission\nDo you want to replace?");
        if (result.get() == ButtonType.OK) {
			if (newDirector.getPermission().equals("SUPERVISER_COMMITTEE_MEMBER")) {
				newDirector.setPermission("SUPERVISER_COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Supervisor Committee Director");
				flag=1;
			} else if (newDirector.getPermission().equals("SUPERVISER")) {
				newDirector.setPermission("SUPERVISER_COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Supervisor Committee Director");
			}else {
				if(newDirector.getPermission().equals("COMMITTEE_MEMBER"))
					flag = 1;
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
			}
			if (oldDirector.getPermission().equals("SUPERVISER_COMMITTEE_DIRECTOR")) {
				oldDirector.setPermission("SUPERVISER");
				oldDirector.setJobDescription("Superviser");
			} else if(flag == 1) {
				oldDirector.setPermission("COMMITTEE_MEMBER");
				oldDirector.setJobDescription("Committee Member");
			}
			else {
				oldDirector.setPermission("INFORMATION_ENGINEER");
				oldDirector.setJobDescription("Information Engineer");
			}
			this.createPermissoinsToUsers(newDirector, oldDirector);
        }
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
		
//		for(User u: users) {
//			committeeMemberComboBox.getItems().add(u.getFirstName()+" "+ u.getLastName());
//		}
	}
	
	/* this method will show the window with the new change request id */
	public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		return alert2.showAndWait();
	}

}