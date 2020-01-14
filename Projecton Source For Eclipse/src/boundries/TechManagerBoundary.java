package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.TechManagerController;
import controllers.TimeManager;
import entities.ChangeRequest;
import entities.SubsystemSupporter;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TechManagerBoundary implements Initializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/*button*/
    @FXML
    private Button btnBack;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnRequestList;
    @FXML
    private Button btnEmployee;
    @FXML
    private Button btnReportPage;
    @FXML
    private Button viewReportButton;
    @FXML
    private Button viewRequestDetailsButton;

    @FXML
    private AnchorPane requestListPane;
    @FXML
    private AnchorPane employeeAnchorPane;
    @FXML
    private AnchorPane reportPageAnchorPane;
    @FXML
    private AnchorPane reportDisplayAnchorPane;

    /*employee table*/
    @FXML
    private TableView<User> employeeListTable;
    @FXML
    private TableColumn<User, Integer> clmEmployeId;
    @FXML
    private TableColumn<User, String> EmployeeNameColumn;

    /*employee details*/
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField departmentTextField;

    @FXML
    private Hyperlink hlViewPermission;

    /*request list table*/
    @FXML
    private TableView<ChangeRequest> requestListTable;
    @FXML
    private TableColumn<ChangeRequest, Integer> requestIdColumn;
    @FXML
    private TableColumn<ChangeRequest, String> stepColumn;
    @FXML
    private TableColumn<ChangeRequest, String> descriptionColumn;
    @FXML
    private TableColumn<ChangeRequest, String> subsystemColumn;

    @FXML
    private ComboBox<String> reportTypeComboBox;
    
    @FXML
    private TableView<SubsystemSupporter> subsystemSupporterTable;
    @FXML
    private TableColumn<SubsystemSupporter, String> subsystemSupporterColumn;
    
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    private ChangeRequest currentChangeRequest;
	private User employeeUser;
	private ArrayList<User> users = new ArrayList<>(); 
	TechManagerController myController = new TechManagerController(this);
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	ObservableList<User> employeeList = FXCollections.observableArrayList();
	ObservableList<SubsystemSupporter> subsystemList = FXCollections.observableArrayList();

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
	
	/**
	 * this method load in click on button extra details for the current change request
	 * @param event
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
     * the method load employee page when the user press on employee button
     * @param event
     */
    @FXML
    void loadEmployeePage(MouseEvent event) {
		reportPageAnchorPane.setVisible(false);
		requestListPane.setVisible(false);
		employeeAnchorPane.setVisible(true);
		myController.getAllTheEmployee();
    }

    /**
     * the method will close this page and open the menu page if the user press on home page button
     * @param event
     */
    @FXML
    void loadHomePage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    /**
     * the method will close this page and open the previous page if the user press on back button
     * @param event
     */
    @FXML
    void loadPreviousPage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    /**
     * this method show the report page
     * @param event
     */
    @FXML
    void loadReportPage(MouseEvent event) {
    	employeeAnchorPane.setVisible(false);
    	requestListPane.setVisible(false);
		reportPageAnchorPane.setVisible(true);
    }

    /**
     * this method show request list page when the user click on request list button
     * @param event
     */
    @FXML
    void loadRequestListPage(MouseEvent event) {
    	employeeAnchorPane.setVisible(false);
    	reportPageAnchorPane.setVisible(false);
    	requestListPane.setVisible(true);
		myController.getAllTheActiveChangeRequest();
    }

    /**
     * this method open the specific report that the user choose in the comboBox
     * @param event
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
     * this method open new window for the employee permission page
     * @param event
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
     * the method search the new user in the user list as per user name
     * @param employeeNewUser
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
	 * the method gets change request list and display the list in the change request table
	 * @param resultList
	 */
	public void displayChangeRequestTable(ArrayList<ChangeRequest> resultList) {
		requestList.clear();
		if (!resultList.isEmpty()) {
			requestList.addAll(resultList);
			requestListTable.setItems(requestList);
		}
	}
	
	/**
	 * the method gets users list and display the list in the employee table
	 * @param resultList
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
	 * this method gets subsystem supporter list and display the list in the subsystem table
	 * @param resultList
	 */
	public void displaySubsystemTable(ArrayList<SubsystemSupporter> resultList) {
		subsystemList.clear();
		if (!resultList.isEmpty()) {
			subsystemList.addAll(resultList);
			subsystemSupporterTable.setItems(subsystemList);
		}
	}

}
