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
			createPermissionToOneUser(employeeUser);
			break;
		case "SUPERVISOR":
			errorText.setVisible(false);
			for(User u: users) {
				if(u.getPermission().equals("SUPERVISOR")) {
					handleSupervasior(employeeUser,u);
					((Node) event.getSource()).getScene().getWindow().hide();
				}
			}
			handleSupervisorOneUser(employeeUser);
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
				handleCommitteeMemberOneUser(employeeUser);
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
			handleCommitteeDirectorOneUser(employeeUser);
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
		handleCommitteeMember(employeeUser,oldMember);
    }
    
    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
    
    private void createPermissionToOneUser(User newUser) {
    	myController.updateEmployeePermission(newUser.getPermission(),newUser.getJobDescription(),newUser.getUserID());
    	techManagerBoundry.setEmployeeListChanges(newUser);
    }
    
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
    
    private void handleCommitteeDirectorOneUser(User newDirector) {
    	switch (newDirector.getPermission()) {
    	case "COMMITTEE_MEMBER":
    		newDirector.setPermission("COMMITTEE_DIRECTOR");
    		newDirector.setJobDescription("Committee Director");
			createPermissionToOneUser(newDirector);
			break;
    	case "SUPERVISOR":
    		newDirector.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
    		newDirector.setJobDescription("Supervisor Committee Director");
        	createPermissionToOneUser(newDirector);
        	break;
    	case "SUPERVISOR_COMNITTEE_MEMBER":
    		newDirector.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
    		newDirector.setJobDescription("Supervisor Committee Director");
        	createPermissionToOneUser(newDirector);
        	break;
    	case "INFORMATION_ENGINEER":
    		newDirector.setPermission("COMMITTEE_DIRECTOR");
    		newDirector.setJobDescription("Committee Director");
			createPermissionToOneUser(newDirector);
    		break;
    	default:
    		break;
    	}
    }
    
    /**
     * this method gives to one user committee member permission
     * @param newMember
     */
    private void handleCommitteeMemberOneUser(User newMember) {
    	switch (newMember.getPermission()) {
    	case "COMNITTEE_DIRECTOR":
    		newMember.setPermission("COMMITTEE_MEMBER");
        	newMember.setJobDescription("Committee Member");
        	createPermissionToOneUser(newMember);
        	break;
    	case "SUPERVISOR":
    		newMember.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
        	newMember.setJobDescription("Supervisor Committee Member");
        	createPermissionToOneUser(newMember);
        	break;
    	case "SUPERVISOR_COMNITTEE_DIRECTOR":
    		newMember.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
        	newMember.setJobDescription("Supervisor Committee Member");
        	createPermissionToOneUser(newMember);
    		break;
    	case "INFORMATION_ENGINEER":
    		newMember.setPermission("COMMITTEE_MEMBER");
        	newMember.setJobDescription("Committee Member");
        	createPermissionToOneUser(newMember);
    		break;
    	default:
    		break;
    	}
    }
    
    /**
     * this method gives to one user supervisor permission
     * @param newSupervisor
     */
    private void handleSupervisorOneUser(User newSupervisor) {
    	switch (newSupervisor.getPermission()) {
    	case "COMNITTEE_DIRECTOR":
    		newSupervisor.setPermission("SUPERVISOR_COMNITTEE_DIRECTOR");
    		newSupervisor.setJobDescription("Supervisor Committee Director");
			createPermissionToOneUser(newSupervisor);
    		break;
    	case "COMMITTEE_MEMBER":
    		newSupervisor.setPermission("SUPERVISOR_COMNITTEE_MEMBER");
    		newSupervisor.setJobDescription("Supervisor Committee Member");
			createPermissionToOneUser(newSupervisor);
			break;
    	case "INFORMATION_ENGINEER":
    		newSupervisor.setPermission("INFORMATION_ENGINEER");
    		newSupervisor.setJobDescription("Information Engineer");
    		createPermissionToOneUser(newSupervisor);
    	default:
    		break;
    	}	
    }
    
    /**
     * this method handle with committee member permission
     * @param newMember
     * @param oldMember
     */
    private void handleCommitteeMember(User newMember,User oldMember) {
    	//handle newMember
    	if(newMember.getPermission().equals("SUPERVISER")) {
    		newMember.setPermission("SUPERVISER_COMMITTEE_MEMBER");
    		newMember.setJobDescription("Supervisor Committee Member");
    	} else if(newMember.getPermission().equals("COMMITTEE_DIRECTOR")) {
    		newMember.setPermission("COMMITTEE_MEMBER");
    		newMember.setJobDescription("Committee Member");
    	} else if(newMember.getPermission().equals("SUPERVISER_COMMITTEE_DIRECTOR")) {
    		newMember.setPermission("SUPERVISER_COMMITTEE_MEMBER");
    		newMember.setJobDescription("Supervisor Committee Member");
    	}else {
    		newMember.setPermission("COMMITTEE_MEMBER");
    		newMember.setJobDescription("Committee Member");
    	}
    	//handle oldMember
    	if(oldMember.getPermission().equals("SUPERVISER_COMMITTEE_MEMBER")) {
    			oldMember.setPermission("SUPERVISOR");
    			oldMember.setJobDescription("Supervisor");
    	}
    	else {
    			oldMember.setPermission("INFORMATION_ENGINEER");
    			oldMember.setJobDescription("Information Emgineer");
    	}
    	this.createPermissoinsToUsers(newMember, oldMember);
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
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with supervisor permission\nDo you want to replace?");
        if (result.get() == ButtonType.OK) {
        	//handle newDirector
			if (newDirector.getPermission().equals("SUPERVISER_COMMITTEE_MEMBER")) {
				newDirector.setPermission("SUPERVISER_COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Supervisor Committee Director");
			} else if (newDirector.getPermission().equals("SUPERVISER")) {
				newDirector.setPermission("SUPERVISER_COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Supervisor Committee Director");
			}else {
				if(newDirector.getPermission().equals("COMMITTEE_MEMBER"))
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
			}
			//handle oldDirector
			if (oldDirector.getPermission().equals("SUPERVISER_COMMITTEE_DIRECTOR")) {
				oldDirector.setPermission("SUPERVISER");
				oldDirector.setJobDescription("Superviser");
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
		permossionTextField.setEditable(false);
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