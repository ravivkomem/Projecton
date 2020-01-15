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
import javafx.stage.Stage;

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

    @FXML
    private ComboBox<String> subsystemComboBox;
    @FXML
    private Button setSubsystemButton;
    
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
	
	/**
	 * this method update subsystem supporter
	 * @param event
	 */
    @FXML
    void updateSupportSubsystem(MouseEvent event) {
    	if(subsystemComboBox.getSelectionModel().isEmpty()) {
    		popUpWindowMessage(AlertType.INFORMATION, "", "Please choose subsystem first");
    	} else {
    		Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "Are sure you"
        			+ " want to appoint this user?");
        	if (result.get() == ButtonType.OK) {
        		myController.updateSubsystemSupporter(subsystemComboBox.getSelectionModel().getSelectedItem(),
        				employeeUser.getUserName());
        		popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
        		((Node) event.getSource()).getScene().getWindow().hide();
        	}
    	}
    	
    }
	
    /**
     * this method update to users there permission
     * @param event
     */
    @FXML
    void setNewEmployeePermission(MouseEvent event) {
    	if(newPremissionComboBox.getSelectionModel().isEmpty()) {
    		errorText.setText("Please select permission");
    		errorText.setVisible(true);
			return;
		}
		switch (newPremissionComboBox.getSelectionModel().getSelectedItem()) {
		case "Information Engineer":
			errorText.setVisible(false);
			employeeUser.setPermission("INFORMATION_ENGINEER");
			employeeUser.setJobDescription("Information Engineer");
			createPermissionToOneUser(employeeUser);
			popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
			Stage stage = (Stage) errorText.getScene().getWindow();
			stage.close();
			break;
		case "Supervisor":
			errorText.setVisible(false);
			for(User u: users) {
				if(u.getPermission().equals("SUPERVISOR")||
						u.getPermission().equals("SUPERVISOR_COMMITTEE_MEMBER")||
						u.getPermission().equals("SUPERVISOR_COMMITTEE_DIRECTOR")) {
					if(u.getUserName().equals(employeeUser.getUserName())) {
						handleSupervisorOneUser(employeeUser);
						techManagerBoundry.setEmployeeListChanges(employeeUser);
					}
					else
						handleSupervasior(employeeUser,u);
					return;
				}
			}
			handleSupervisorOneUser(employeeUser);
			break;
		case "Committee Member":
			errorText.setVisible(false);
			int cnt=0;
			for(User u: users) {
				if(u.getPermission().equals("COMMITTEE_MEMBER")||
						u.getPermission().equals("SUPERVISOR_COMMITTEE_MEMBER")) {
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
		case "Committee Director":
			errorText.setVisible(false);
			for(User u: users) {
				if(u.getPermission().equals("COMMITTEE_DIRECTOR")||
						u.getPermission().equals("SUPERVISOR_COMMITTEE_DIRECTOR")) {
					if(u.getUserName().equals(employeeUser.getUserName())) {
						handleCommitteeDirectorOneUser(employeeUser);
						techManagerBoundry.setEmployeeListChanges(employeeUser);
					}
					else
						handleCommitteeDirector(employeeUser,u);
					return;
				}
			}
			handleCommitteeDirectorOneUser(employeeUser);
			break;
		case "Subsystem Supporter":
			/*TODO: support to subsystem*/
			setNewPermissionTextField.setVisible(false);
			
		    subsystemComboBox.setVisible(true);
		    setSubsystemButton.setVisible(true);
		    break;
		default:
			break;
		}
    }
    
    @FXML
    void replaceCommitteMember(MouseEvent event) {
    	if(committeeMemberComboBox.getSelectionModel().isEmpty()) {
    		errorText.setText("Please choose committee member");
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
    
    /**
     * this method gets user and update his new permission in data base
     * @param newUser
     */
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
    
    /**
     * this method handle with permission to new committee director
     * the method check what the current permission of the user and change the permission
     * to committee director or to supervisor committee director if necessary
     * @param newDirector
     */
    private void handleCommitteeDirectorOneUser(User newDirector) {
    	Optional<ButtonType> result;
    	switch (newDirector.getPermission()) {
    	case "COMMITTEE_MEMBER":
    		newDirector.setPermission("COMMITTEE_DIRECTOR");
    		newDirector.setJobDescription("Committee Director");
			createPermissionToOneUser(newDirector);
			break;
    	case "SUPERVISOR":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is supervisor", 
    				"Do you want to add the permission?");
			if (result.get() == ButtonType.OK) {
				newDirector.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Supervisor Committee Director");
			} else {
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
			}
        	createPermissionToOneUser(newDirector);
        	break;
    	case "SUPERVISOR_COMMITTEE_MEMBER":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is supervisor Committee Member", 
    				"Do you want to add the permission?");
			if (result.get() == ButtonType.OK) {
				newDirector.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Supervisor Committee Director");
			} else {
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
			}
        	createPermissionToOneUser(newDirector);
        	break;
    	case "SUPERVISOR_COMMITTEE_DIRECTOR":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor Committee Director", 
    				"Do you want to change the permission?");
			if (result.get() == ButtonType.OK) {
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
			}
    		break;
    	case "INFORMATION_ENGINEER":
    		newDirector.setPermission("COMMITTEE_DIRECTOR");
    		newDirector.setJobDescription("Committee Director");
			createPermissionToOneUser(newDirector);
    		break;
    	default:
    		break;
    	}
		popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
		Stage stage = (Stage) errorText.getScene().getWindow();
		stage.close();
    }
    
    /**
     * this method gives to one user committee member permission
     * @param newMember
     */
    private void handleCommitteeMemberOneUser(User newMember) {
    	Optional<ButtonType> result;
    	switch (newMember.getPermission()) {
    	case "COMMITTEE_DIRECTOR":
    		newMember.setPermission("COMMITTEE_MEMBER");
        	newMember.setJobDescription("Committee Member");
        	createPermissionToOneUser(newMember);
        	break;
    	case "SUPERVISOR":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is supervisor", 
    				"Do you want to add the permission?");
    		if (result.get() == ButtonType.OK) {
    			newMember.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
    			newMember.setJobDescription("Supervisor Committee Member");
    		} else {
    			newMember.setPermission("COMMITTEE_MEMBER");
            	newMember.setJobDescription("Committee Member");
    		}
        	createPermissionToOneUser(newMember);
        	break;
    	case "SUPERVISOR_COMMITTEE_DIRECTOR":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is supervisor Committee Director", 
    				"Do you want to add the permission?");
    		if (result.get() == ButtonType.OK) {
    			newMember.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
    			newMember.setJobDescription("Supervisor Committee Member");
    		} else {
    			newMember.setPermission("COMMITTEE_MEMBER");
            	newMember.setJobDescription("Committee Member");
    		}
        	createPermissionToOneUser(newMember);
    		break;
    	case "SUPERVISOR_COMMITTEE_MEMBER":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor Committee Member", 
    				"Do you want to change the permission?");
			if (result.get() == ButtonType.OK) {
				newMember.setPermission("COMMITTEE_MEMBER");
            	newMember.setJobDescription("Committee Member");
			}
    		break;
    	case "INFORMATION_ENGINEER":
    		newMember.setPermission("COMMITTEE_MEMBER");
        	newMember.setJobDescription("Committee Member");
        	createPermissionToOneUser(newMember);
    		break;
    	default:
    		break;
    	}
    	popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
		Stage stage = (Stage) errorText.getScene().getWindow();
		stage.close();
    }
    
    /**
     * this method gives to one user supervisor permission
     * @param newSupervisor
     */
    private void handleSupervisorOneUser(User newSupervisor) {
    	Optional<ButtonType> result;
		switch (newSupervisor.getPermission()) {
		case "COMMITTEE_DIRECTOR":
			result = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Committee Director",
					"Do you want to add the permission?");
			if (result.get() == ButtonType.OK) {
				newSupervisor.setPermission("SUPERVISOR_COMNITTEE_DIRECTOR");
				newSupervisor.setJobDescription("Supervisor Committee Director");
			} else {
				newSupervisor.setPermission("SUPERVISOR");
				newSupervisor.setJobDescription("Supervisor");
			}
			createPermissionToOneUser(newSupervisor);
			break;
		case "COMMITTEE_MEMBER":
			result = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Committee Member",
					"Do you want to add the permission?");
			if (result.get() == ButtonType.OK) {
				newSupervisor.setPermission("SUPERVISOR_COMNITTEE_MEMBER");
				newSupervisor.setJobDescription("Supervisor Committee Member");
			} else {
				newSupervisor.setPermission("SUPERVISOR");
				newSupervisor.setJobDescription("Supervisor");
			}
			createPermissionToOneUser(newSupervisor);
			break;
	 	case "SUPERVISOR_COMMITTEE_MEMBER":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor Committee Member", 
    				"Do you want to change the permission?");
			if (result.get() == ButtonType.OK) {
				newSupervisor.setPermission("SUPERVISOR");
				newSupervisor.setJobDescription("Supervisor");
			}
    		break;
		case "SUPERVISOR_COMMITTEE_DIRECTOR":
    		result=popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor Committee Director", 
    				"Do you want to change the permission?");
			if (result.get() == ButtonType.OK) {
				newSupervisor.setPermission("SUPERVISOR");
				newSupervisor.setJobDescription("Supervisor");
			}
    		break;
		case "INFORMATION_ENGINEER":
			newSupervisor.setPermission("SUPERVISOR");
			newSupervisor.setJobDescription("Supervisor");
			createPermissionToOneUser(newSupervisor);
		default:
			break;
		}	
		popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
		Stage stage = (Stage) errorText.getScene().getWindow();
		stage.close();
    }
    
    /**
     * this method handle with committee member permission
     * if there is already user with committee member permission
     * @param newMember
     * @param oldMember
     */
    private void handleCommitteeMember(User newMember,User oldMember) {
    	//handle newMember
    	Optional<ButtonType> result;
    	switch (newMember.getPermission()) {
    	case "SUPERVISOR":
    		result = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor", 
    				"Do you want to add the permission?");
    		if (result.get() == ButtonType.OK) {
    			newMember.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
        		newMember.setJobDescription("Supervisor Committee Member");
    		} else {
    			newMember.setPermission("COMMITTEE_MEMBER");
        		newMember.setJobDescription("Committee Member");
    		}
    		break;
    	case "COMMITTEE_DIRECTOR":
    		newMember.setPermission("COMMITTEE_MEMBER");
    		newMember.setJobDescription("Committee Member");
    		break;
    	case "SUPERVISOR_COMMITTEE_DIRECTOR":
    		result = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor Committee Director", 
    				"Do you want to add the permission?");
    		if (result.get() == ButtonType.OK) {
    			newMember.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
        		newMember.setJobDescription("Supervisor Committee Member");
    		} else {
    			newMember.setPermission("COMMITTEE_MEMBER");
        		newMember.setJobDescription("Committee Member");
    		}
    	case "INFORMATION_ENGINEER":
    		newMember.setPermission("COMMITTEE_MEMBER");
    		newMember.setJobDescription("Committee Member");
    		break;
    	default:
    		break;
    	}
    	
    	//handle oldMember
    	if(oldMember.getPermission().equals("SUPERVISOR_COMMITTEE_MEMBER")) {
    			oldMember.setPermission("SUPERVISOR");
    			oldMember.setJobDescription("Supervisor");
    	}
    	else {
    			oldMember.setPermission("INFORMATION_ENGINEER");
    			oldMember.setJobDescription("Information Emgineer");
    	}
    	this.createPermissoinsToUsers(newMember, oldMember);
    	popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
		Stage stage = (Stage) errorText.getScene().getWindow();
		stage.close();
    }
    
    /**
     * this method handle with the problem that tech manager gives supervisor permission
     * that already exist to anther user
     * @param newSupervisor
     * @param oldSupervisor
     */
    private void handleSupervasior(User newSupervisor, User oldSupervisor) {
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with supervisor permission\nDo you want to replace?");
		if (result.get() == ButtonType.OK) {
			Optional<ButtonType> rs;
			
			//handle newSupervisor
			switch (newSupervisor.getPermission()) {
			case "COMMITTEE_MEMBER":
				rs = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Committee Member", 
	    				"Do you want to add the permission?");
	    		if (rs.get() == ButtonType.OK) {
	    			newSupervisor.setPermission("SUPERVISOR_COMMITTEE_MEMBER");
					newSupervisor.setJobDescription("Supervisor Committee Member");
	    		} else {
	    			newSupervisor.setPermission("SUPERVISOR");
					newSupervisor.setJobDescription("Supervisor");
	    		}
			case "COMMITTEE_DIRECTOR":
				rs = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Committee Director", 
	    				"Do you want to add the permission?");
	    		if (rs.get() == ButtonType.OK) {
	    			newSupervisor.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
					newSupervisor.setJobDescription("Supervisor Committee Director");
	    		} else {
	    			newSupervisor.setPermission("SUPERVISOR");
					newSupervisor.setJobDescription("Supervisor");
	    		}
				break;
			case "INFORMATION_ENGINEER":
				newSupervisor.setPermission("SUPERVISOR");
				newSupervisor.setJobDescription("Supervisor");
				break;
			default:
				break;
			}

			//handle oldSupervisor
			if (oldSupervisor.getPermission().equals("SUPERVISOR_COMMITTEE_MEMBER")) {
				oldSupervisor.setPermission("COMMITTEE_MEMBER");
				oldSupervisor.setJobDescription("Committee Member");
			} else if (oldSupervisor.getPermission().equals("SUPERVISOR_COMMITTEE_DIRECTOR")) {
				oldSupervisor.setPermission("COMMITTEE_DIRECTOR");
				oldSupervisor.setJobDescription("Committee Director");
			} else {
				oldSupervisor.setPermission("INFORMATION_ENGINEER");
				oldSupervisor.setJobDescription("Information Engineer");
			}
			this.createPermissoinsToUsers(newSupervisor, oldSupervisor);
			popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
			Stage stage = (Stage) errorText.getScene().getWindow();
			stage.close();
		}
    }
    
    /**
     * this method handle with the problem that tech manager gives committee director permission
     * that already exist to anther user
     * @param newSupervasior
     * @param oldSuperVaser
     */
    private void handleCommitteeDirector(User newDirector, User oldDirector) {
        Optional<ButtonType> result = popUpWindowMessage(AlertType.CONFIRMATION, "", "There is already "
        		+ "user with committee director permission\nDo you want to replace?");
        if (result.get() == ButtonType.OK) {
        	Optional<ButtonType> rs;
        	
        	//handle newDirector
			switch (newDirector.getPermission()) {
			case "SUPERVISOR":
				rs = popUpWindowMessage(AlertType.CONFIRMATION, "Permission Conflict",
						"The user is Supervisor,\nDo you want to add the permission?");
				if (rs.get() == ButtonType.OK) {
					newDirector.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
					newDirector.setJobDescription("Supervisor Committee Director");
				} else {
					newDirector.setPermission("COMMITTEE_DIRECTOR");
					newDirector.setJobDescription("Committee Director");
				}
				break;
			case "SUPERVISOR_COMMITTEE_MEMBER":
				rs = popUpWindowMessage(AlertType.CONFIRMATION, "The user is Supervisor Committee Member",
						"Do you want to add the permission?");
				if (rs.get() == ButtonType.OK) {
					newDirector.setPermission("SUPERVISOR_COMMITTEE_DIRECTOR");
					newDirector.setJobDescription("Supervisor Committee Director");
				} else {
					newDirector.setPermission("COMMITTEE_DIRECTOR");
					newDirector.setJobDescription("Committee Director");
				}
				break;
			case "COMMITTEE_MEMBER":
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
				break;
			case "INFORMATION_ENGINEER":
				newDirector.setPermission("COMMITTEE_DIRECTOR");
				newDirector.setJobDescription("Committee Director");
			default:
				break;
			}
			
			//handle oldDirector
			if (oldDirector.getPermission().equals("SUPERVISOR_COMMITTEE_DIRECTOR")) {
				oldDirector.setPermission("SUPERVISOR");
				oldDirector.setJobDescription("Supervisor");
			}
			else {
				oldDirector.setPermission("INFORMATION_ENGINEER");
				oldDirector.setJobDescription("Information Engineer");
			}
			this.createPermissoinsToUsers(newDirector, oldDirector);
			popUpWindowMessage(AlertType.INFORMATION, "", "The Permission Update successfully");
			Stage stage = (Stage) errorText.getScene().getWindow();
			stage.close();
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		permossionTextField.setEditable(false);
		subsystemComboBox.setVisible(false);
	    setSubsystemButton.setVisible(false);
	    
		newPremissionComboBox.getItems().add("Information Engineer");
		newPremissionComboBox.getItems().add("Supervisor");
		newPremissionComboBox.getItems().add("Committee Member");
		newPremissionComboBox.getItems().add("Committee Director");
		newPremissionComboBox.getItems().add("Subsystem Supporter");
		
		subsystemComboBox.getItems().add("Lecturer Information Station");
		subsystemComboBox.getItems().add("Student Information Station");
		subsystemComboBox.getItems().add("Employee Information Station");
		subsystemComboBox.getItems().add("Moodle System");
		subsystemComboBox.getItems().add("Library System");
		subsystemComboBox.getItems().add("Class Rooms With Computers");
		subsystemComboBox.getItems().add("Laboratory");
		subsystemComboBox.getItems().add("Computer Farm");
		subsystemComboBox.getItems().add("College Website");
	}

	@Override
	public void initData(Object data) {
		employeeUser = (User)(((ArrayList<ArrayList<Object>>) data).get(0).get(0));
		users.addAll(((ArrayList<ArrayList<User>>) data).get(1));
		techManagerBoundry = (TechManagerBoundary)(((ArrayList<ArrayList<Object>>) data).get(2).get(0));
		employeeNameText.setText("Permission: "+employeeUser.getFirstName()+" "+employeeUser.getLastName());
		permossionTextField.setText(employeeUser.getJobDescription());
	}
	
	/**
	 * this method will show up window with the msg that the method gets
	 * @param alert
	 * @param msg
	 * @param mess
	 * @return
	 */
	public static Optional<ButtonType> popUpWindowMessage(AlertType alert, String msg, String mess) {
		Alert alert2 = new Alert(alert);
		alert2.setTitle(msg);
		alert2.setHeaderText(mess);
		return alert2.showAndWait();
	}

}