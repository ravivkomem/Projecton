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
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

public class TechManagerBoundary implements DataInitializable{

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
    private AnchorPane employeeAnchorPane;
    @FXML
    private AnchorPane reportPageAnchorPane;

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
    
    /*activity report*/
    @FXML
    private AnchorPane activityReportPane;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button showDetailsButton;
    @FXML
    private AnchorPane activityReportDetailsPane;
    @FXML
    private PieChart requestStatusPieChart;
    @FXML
    private TextField medianTextField;
    @FXML
    private TextField stdTextField;
    @FXML
    private TextField distributionTextField;
    @FXML
    private TextField handleDaysTextField;
    
    /*performance report*/
    
    /*delay report*/
    
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
	private User employeeUser;
	private ArrayList<User> users = new ArrayList<>(); 
	TechManagerController myController = new TechManagerController(this);
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	ObservableList<User> employeeList = FXCollections.observableArrayList();

	/* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
	
    @FXML
    void loadEmployeePage(MouseEvent event) {
		reportPageAnchorPane.setVisible(false);
		requestListTable.setVisible(false);
		employeeAnchorPane.setVisible(true);
		myController.getAllTheEmployee();
    }

    @FXML
    void loadHomePage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void loadPreviousPage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void loadReportPage(MouseEvent event) {
    	employeeAnchorPane.setVisible(false);
		requestListTable.setVisible(false);
		reportPageAnchorPane.setVisible(true);
		/*TODO reports*/
    }

    @FXML
    void loadRequestListPage(MouseEvent event) {
    	employeeAnchorPane.setVisible(false);
    	reportPageAnchorPane.setVisible(false);
		requestListTable.setVisible(true);
		myController.getAllTheActiveChangeRequest();
    }

    @FXML
    void loadSpecificReport(MouseEvent event) {
    	/*TODO reports*/
    	if (reportTypeComboBox.getSelectionModel().isEmpty()) {
			Toast.makeText(ProjectFX.mainStage, "Please select report type", 1500, 500, 500);
			return;
		} else {
			switch (reportTypeComboBox.getSelectionModel().getSelectedItem()) {
			case "Activity Report":
				activityReportPane.setVisible(true);
				break;
			case "Performance Report":
				
				break;
			case "Delay Report":
				
				break;
			default:
				break;
			}
		}
    }
    
    @FXML
    void showActivityReportDetails(MouseEvent event) {
    	if(startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
    		Toast.makeText(ProjectFX.mainStage, "Please select start date and end date", 1500, 500, 500);
    	}
    	else {
    		Date startDate = Date.valueOf(startDatePicker.getValue());
    		Date endDate = Date.valueOf(endDatePicker.getValue());
    		Date todayDate = TimeManager.getCurrentDate();
    		long daysBetween = TimeManager.getDaysBetween(todayDate, startDate);
    		if(daysBetween>0) {
    			Toast.makeText(ProjectFX.mainStage, "You can not select a date before today date", 1500, 500, 500);
    		}
    		/*TODO check for end date
    		 * check for endDate>startDate*/
    	}
    	 
    }

    @FXML
    void loagViewPermissionsPage(MouseEvent event) {
    	ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
    	dataList.add(new ArrayList<Object>());
    	dataList.add(new ArrayList<Object>());
    	dataList.add(new ArrayList<Object>());
    	dataList.get(0).add(employeeUser);
    	dataList.get(1).addAll(users);
    	dataList.get(2).add(this);
    	ProjectFX.pagingController.loadAdditionalStage(ProjectPages.EMPLOYEE_PERMISSION.getPath(), dataList);
    }

    @FXML
    void logOutUser(MouseEvent event) {
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }
    
    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
    
    public void setEmployeeListChanges(User employeeNewUser) {
    	employeeUser = employeeNewUser;
    	for(int i=0;i<users.size();i++) {
			if(users.get(i).getUserID() == employeeUser.getUserID()) {
				users.set(i, employeeUser);
				return;
			}
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeAnchorPane.setVisible(false);
		reportPageAnchorPane.setVisible(false);
		requestListTable.setVisible(true);
		
		reportTypeComboBox.getItems().add("Activity Report");
		reportTypeComboBox.getItems().add("Performance Report");
		reportTypeComboBox.getItems().add("Delay Report");
		
		requestIdColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		stepColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("actualStep"));
		descriptionColumn
		.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("desiredChangeDescription"));
		subsystemColumn.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		
		EmployeeNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		
		employeeListTable.setRowFactory(tv -> {
		    TableRow<User> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
		        {
		        	employeeUser = row.getItem();
		            userNameTextField.setText(employeeUser.getUserName());
		            emailTextField.setText(employeeUser.getEmail());
		            positionTextField.setText(employeeUser.getJobDescription());
		            numberTextField.setText(employeeUser.getPhoneNumber());
		            departmentTextField.setText(employeeUser.getDepartment());
		        }
		    });
		    return row ;
		});
		myController.getAllTheActiveChangeRequest();
	}

	@Override
	public void initData(Object data) {
		
	}
	
	public void displayChangeRequestTable(ArrayList<ChangeRequest> resultList) {
		requestList.clear();
		if (!resultList.isEmpty()) {
			requestList.addAll(resultList);
			requestListTable.setItems(requestList);
		}
	}
	
	public void displayAllTheEmployeesTable(ArrayList<User> resultList) {
		employeeList.clear();
		if (!resultList.isEmpty()) {
			users.addAll(resultList);
			employeeList.addAll(resultList);
			employeeListTable.setItems(employeeList);
		}
	}

}
