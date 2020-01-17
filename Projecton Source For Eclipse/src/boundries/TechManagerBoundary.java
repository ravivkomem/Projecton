package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import assets.ProjectPages;
import assets.Toast;
import controllers.TechManagerController;
import entities.ChangeRequest;
import entities.SubsystemSupporter;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * The Class TechManagerBoundary.
 * @author Lee Hugi
 *
 */
public class TechManagerBoundary implements Initializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/** The btn back. */
	/*button*/
    @FXML
    private Button btnBack;
    
    /** The btn log out. */
    @FXML
    private Button btnLogOut;
    
    /** The btn home page. */
    @FXML
    private Button btnHomePage;
    
    /** The btn request list. */
    @FXML
    private Button btnRequestList;
    
    /** The btn employee. */
    @FXML
    private Button btnEmployee;
    
    /** The btn report page. */
    @FXML
    private Button btnReportPage;
    
    /** The view report button. */
    @FXML
    private Button viewReportButton;
    
    /** The view request details button. */
    @FXML
    private Button viewRequestDetailsButton;

    /** The request list pane. */
    @FXML
    private AnchorPane requestListPane;
    
    /** The employee anchor pane. */
    @FXML
    private AnchorPane employeeAnchorPane;
    
    /** The report page anchor pane. */
    @FXML
    private AnchorPane reportPageAnchorPane;
    
    /** The report display anchor pane. */
    @FXML
    private AnchorPane reportDisplayAnchorPane;

    /** The employee list table. */
    /*employee table*/
    @FXML
    private TableView<User> employeeListTable;
    
    /** The column employee id. */
    @FXML
    private TableColumn<User, Integer> clmEmployeId;
    
    /** The Employee name column. */
    @FXML
    private TableColumn<User, String> EmployeeNameColumn;

    /** The user name text field. */
    /*employee details*/
    @FXML
    private TextField userNameTextField;
    
    /** The email text field. */
    @FXML
    private TextField emailTextField;
    
    /** The position text field. */
    @FXML
    private TextField positionTextField;
    
    /** The number text field. */
    @FXML
    private TextField numberTextField;
    
    /** The department text field. */
    @FXML
    private TextField departmentTextField;

    /** The hl view permission. */
    @FXML
    private Hyperlink hlViewPermission;

    /** The request list table. */
    /*request list table*/
    @FXML
    private TableView<ChangeRequest> requestListTable;
    
    /** The request id column. */
    @FXML
    private TableColumn<ChangeRequest, Integer> requestIdColumn;
    
    /** The step column. */
    @FXML
    private TableColumn<ChangeRequest, String> stepColumn;
    
    /** The description column. */
    @FXML
    private TableColumn<ChangeRequest, String> descriptionColumn;
    
    /** The subsystem column. */
    @FXML
    private TableColumn<ChangeRequest, String> subsystemColumn;

    /** The report type combo box. */
    @FXML
    private ComboBox<String> reportTypeComboBox;
    
    /** The subsystem supporter table. */
    @FXML
    private TableView<SubsystemSupporter> subsystemSupporterTable;
    
    /** The subsystem supporter column. */
    @FXML
    private TableColumn<SubsystemSupporter, String> subsystemSupporterColumn;
    
    @FXML
    private Button btnSuspensions;
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    
    /** The current change request. */
    private ChangeRequest currentChangeRequest;
	
	/** The employee user. */
	private User employeeUser;
	
	/** The users. */
	private ArrayList<User> users = new ArrayList<>(); 
	
	/** The my controller. */
	TechManagerController myController = new TechManagerController(this);
	
	/** The request list. */
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	
	/** The employee list. */
	ObservableList<User> employeeList = FXCollections.observableArrayList();
	
	/** The subsystem list. */
	ObservableList<SubsystemSupporter> subsystemList = FXCollections.observableArrayList();

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
	
    @FXML
    void clickSuspensions(MouseEvent event) {
    	reportPageAnchorPane.setVisible(false);
		requestListPane.setVisible(true);
		employeeAnchorPane.setVisible(false);
    	myController.SelectChangeRequestForSuspensions();	
    	
    }
    
	/**
	 * this method load in click on button extra details for the current change request.
	 *
	 * @param event the event
	 */
    @FXML
    void loadExtraDetailsPage(MouseEvent event) {
    	if(currentChangeRequest == null) {
    		Toast.makeText(ProjectFX.mainStage, "Please select request from the table", 1500, 500, 500);
    	} else {
    		ArrayList<Object> dataList = new ArrayList<>();
        	dataList.add(currentChangeRequest);
        	dataList.add(ProjectPages.TECH_MANAGER_PAGE.getPath());
        	ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),dataList);
    	}
    }
	
    /**
     * the method load employee page when the user press on employee button.
     *
     * @param event the event
     */
    @FXML
    void loadEmployeePage(MouseEvent event) {
		reportPageAnchorPane.setVisible(false);
		requestListPane.setVisible(false);
		employeeAnchorPane.setVisible(true);
		myController.getAllTheEmployee();
    }

    /**
     * the method will close this page and open the menu page if the user press on home page button.
     *
     * @param event the event
     */
    @FXML
    void loadHomePage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    /**
     * the method will close this page and open the previous page if the user press on back button.
     *
     * @param event the event
     */
    @FXML
    void loadPreviousPage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    /**
     * this method show the report page.
     *
     * @param event the event
     */
    @FXML
    void loadReportPage(MouseEvent event) {
    	employeeAnchorPane.setVisible(false);
    	requestListPane.setVisible(false);
		reportPageAnchorPane.setVisible(true);
    }

    /**
     * this method show request list page when the user click on request list button.
     *
     * @param event the event
     */
    @FXML
    void loadRequestListPage(MouseEvent event) {
    	employeeAnchorPane.setVisible(false);
    	reportPageAnchorPane.setVisible(false);
    	requestListPane.setVisible(true);
		myController.getAllTheActiveChangeRequest();
    }

    /**
     * this method open the specific report that the user choose in the comboBox.
     *
     * @param event the event
     */
    @FXML
    void loadSpecificReport(MouseEvent event) {
    	if (reportTypeComboBox.getSelectionModel().isEmpty()) {
			Toast.makeText(ProjectFX.mainStage, "Please select report type", 1500, 500, 500);
			return;
		} else {
			switch (reportTypeComboBox.getSelectionModel().getSelectedItem()) {
			case "Activity Report":
				reportDisplayAnchorPane.getChildren().setAll((AnchorPane) ProjectFX.pagingController.loadBoundaryInPane(ProjectPages.ACTIVITY_REPORT_PAGE.getPath()));
				break;
			case "Performance Report":
				reportDisplayAnchorPane.getChildren().setAll((AnchorPane) ProjectFX.pagingController.loadBoundaryInPane(ProjectPages.PERFORMANCE_REPORT_PAGE.getPath()));
				break;
			case "Delay Report":
				reportDisplayAnchorPane.getChildren().setAll((AnchorPane) ProjectFX.pagingController.loadBoundaryInPane(ProjectPages.DELAY_REPORT_PAGE.getPath()));
				break;
			default:
				break;
			}
		}
    }

    /**
     * this method open new window for the employee permission page.
     *
     * @param event the event
     */
    @FXML
    void loagViewPermissionsPage(MouseEvent event) {
    	ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
    	if(employeeUser == null) {
    		Toast.makeText(ProjectFX.mainStage, "Please select employee from the table", 1500, 500, 500);
    		return;
    	}
    	if(employeeUser.getPermission().equals("INFORMATION_ENGINEERING_DEPARTMENT_HEAD")) {
    		Toast.makeText(ProjectFX.mainStage, "You can NOT edit this user permission", 1500, 500, 500);
		} else {
			dataList.add(new ArrayList<Object>());
			dataList.add(new ArrayList<Object>());
			dataList.add(new ArrayList<Object>());
			dataList.get(0).add(employeeUser);
			dataList.get(1).addAll(users);
			dataList.get(2).add(this);
			ProjectFX.pagingController.loadAdditionalStage(ProjectPages.EMPLOYEE_PERMISSION.getPath(), dataList);
		}
    }

    /**
     * Log out user.
     *
     * @param event the event
     */
    @FXML
    void logOutUser(MouseEvent event) {
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }
    
    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
    
    /**
     * the method gets new employee user and replace the user in the same old user
     * the method search the new user in the user list as per user name.
     *
     * @param employeeNewUser the new employee list
     */
    public void setEmployeeListChanges(User employeeNewUser) {
    	employeeUser = employeeNewUser;
    	for(int i=0;i<users.size();i++) {
			if(users.get(i).getUserID() == employeeUser.getUserID()) {
				users.set(i, employeeUser);
				userNameTextField.setText("");
	            emailTextField.setText("");
	            positionTextField.setText("");
	            numberTextField.setText("");
	            departmentTextField.setText("");
	            subsystemList.clear();
	            subsystemSupporterTable.setItems(subsystemList);
				return;
			}
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProjectFX.mainStage.setTitle("ICM - Menu\\Tech Manager");
		employeeAnchorPane.setVisible(false);
		reportPageAnchorPane.setVisible(false);
		requestListPane.setVisible(true);
		
		userNameTextField.setEditable(false);
		emailTextField.setEditable(false);
		positionTextField.setEditable(false);
		numberTextField.setEditable(false);
		departmentTextField.setEditable(false);
		
		reportTypeComboBox.getItems().add("Activity Report");
		reportTypeComboBox.getItems().add("Performance Report");
		reportTypeComboBox.getItems().add("Delay Report");
		
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		stepColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("actualStep"));
		descriptionColumn
		.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		
		EmployeeNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
		
	    subsystemSupporterColumn.setCellValueFactory(new PropertyValueFactory<SubsystemSupporter, String>("subsystem"));;
		
		employeeListTable.setRowFactory(tv -> {
		    TableRow<User> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY){
		        	employeeUser = row.getItem();
		            userNameTextField.setText(employeeUser.getUserName());
		            emailTextField.setText(employeeUser.getEmail());
		            positionTextField.setText(employeeUser.getJobDescription());
		            numberTextField.setText(employeeUser.getPhoneNumber());
		            departmentTextField.setText(employeeUser.getDepartment());
		            myController.getSubsystemSupporterByUserName(employeeUser.getUserName());
		        }
		    });
		    return row ;
		});
		
		requestListTable.setRowFactory(tv -> {
		    TableRow<ChangeRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY){
		        	currentChangeRequest = row.getItem();
		        }
		    });
		    return row ;
		});
		myController.getAllTheActiveChangeRequest();
	}
	
	/**
	 * the method gets change request list and display the list in the change request table.
	 *
	 * @param resultList the result list
	 */
	public void displayChangeRequestTable(ArrayList<ChangeRequest> resultList) {
		requestList.clear();
		if (!resultList.isEmpty()) {
			requestList.addAll(resultList);
			requestListTable.setItems(requestList);
		}
	}
	
	/**
	 * the method gets users list and display the list in the employee table.
	 *
	 * @param resultList the result list
	 */
	public void displayAllTheEmployeesTable(ArrayList<User> resultList) {
		employeeList.clear();
		if (!resultList.isEmpty()) {
			users.addAll(resultList);
			employeeList.addAll(resultList);
			employeeListTable.setItems(employeeList);
		}
	}

	/**
	 * this method gets subsystem supporter list and display the list in the subsystem table.
	 *
	 * @param resultList the result list
	 */
	public void displaySubsystemTable(ArrayList<SubsystemSupporter> resultList) {
		subsystemList.clear();
		if (!resultList.isEmpty()) {
			subsystemList.addAll(resultList);
			subsystemSupporterTable.setItems(subsystemList);
		}
	}

}
