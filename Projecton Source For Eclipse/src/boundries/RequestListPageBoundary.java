package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.PagingController;
import controllers.RequestListPageController;
import controllers.TimeManager;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RequestListPageBoundary implements Initializable {

	@FXML
	private AnchorPane searchByDatePane;

	@FXML
	private AnchorPane searchByIdPane;

	@FXML
	private TextField enterIdTextField;

	@FXML
	private AnchorPane searchByStatusPane;

	@FXML
	private ComboBox<String> selectStatusComboBox;
	/* Table FXML */
	@FXML
	private TableView<ChangeRequest> basicDetailsTbl;

	@FXML
	private TableColumn<ChangeRequest, Integer> requstIdClm;

	@FXML
	private TableColumn<ChangeRequest, String> statusClm;

	@FXML
	private TableColumn<ChangeRequest, String> subSystemClm;

	@FXML
	private TableColumn<ChangeRequest, Date> startDateClm;
	/* FXML Text */
	@FXML
	private Text noSubmitingRequest;
	@FXML
	private Text selectedChangeRequestIdText;

	/* FXML DatePickers */

	@FXML
	private DatePicker fromDatePicker;

	@FXML
	private DatePicker toDatePicker;

	/* Button FXML */

	@FXML
	private Button btnHomePage;

	@FXML
	private Button viewMyRequestsButton;

	@FXML
	private Button searchByIdBtn;

	@FXML
	private Button searchByStatusBtn;

	@FXML
	private Button searchByDateBtn;

	@FXML
	private Button viewExtraDetailsBtn;

	@FXML
	private Button backBtn;

	@FXML
	private Button logoutUser;
	@FXML
	private TextField displaySpecificID;

	private RequestListPageController myController = new RequestListPageController(this);
	ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
	ChangeRequest currentChangeRequest;

	/* FXML Methods */

	@FXML
	void backToPrevPage(MouseEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	}

	@FXML
	void extraDetailsShows(MouseEvent event) {
		ArrayList<Object> dataList = new ArrayList<>();
		if (currentChangeRequest == null) {
			Toast.makeText(ProjectFX.mainStage, "Please select row from the table", 1500, 500, 500);
		} else {
			dataList.add(currentChangeRequest);
			dataList.add(ProjectPages.REQUEST_LIST_PAGE.getPath());
			ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(), dataList);
		}

	}

	@FXML
	void logout(MouseEvent event) {
		ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	}

	@FXML
	void loadHomePage(MouseEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	}

	@FXML
	void loadMyRequestsPage(MouseEvent event) {
		ProjectFX.pagingController.loadBoundary(ProjectPages.REQUEST_LIST_PAGE.getPath());
	}

	@FXML
	void loadPageSearchByDate(MouseEvent event) {
		searchByDatePane.setVisible(true);
		searchByIdPane.setVisible(false);
		searchByStatusPane.setVisible(false);
		noSubmitingRequest.setVisible(false);
	}

	@FXML
	void loadSearchByIdPage(MouseEvent event) {
		searchByDatePane.setVisible(false);
		searchByIdPane.setVisible(true);
		searchByStatusPane.setVisible(false);
		noSubmitingRequest.setVisible(false);
		enterIdTextField.setText("");
	}

	@FXML
	void loadSearchByStatusPage(MouseEvent event) {
		searchByDatePane.setVisible(false);
		searchByIdPane.setVisible(false);
		searchByStatusPane.setVisible(true);
		noSubmitingRequest.setVisible(false);
	}

	@FXML
	void searchById(MouseEvent event) {
		noSubmitingRequest.setVisible(false);
		if(enterIdTextField.getText()==null)
		{
			Toast.makeText(ProjectFX.mainStage, "Please enter a valid number", 1500, 500, 500);
		}
		else 
		{
			if (enterIdTextField.getText().matches("[0-9]+"))
			{
				Integer idNumber=Integer.parseInt(enterIdTextField.getText());
				myController.getChangeRequestsByIdSearch(idNumber);
			}
			else 
			{
				Toast.makeText(ProjectFX.mainStage, "Please enter a valid number", 1500, 500, 500);
			}
		}
		
		
	}

	@FXML
    void searchByDate(MouseEvent event) {
		noSubmitingRequest.setVisible(false);
    	if (fromDatePicker.getValue()==null || toDatePicker.getValue()==null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please pick a date", 1500, 500, 500);
    	}
    	else
    	{
    		Date from,to;
    		from=Date.valueOf(fromDatePicker.getValue());
    		to=Date.valueOf(toDatePicker.getValue());
    		if ((TimeManager.getDaysBetween(TimeManager.getCurrentDate(), from))>0)
    		{
    			Toast.makeText(ProjectFX.mainStage, "Please pick a valid date", 1500, 500, 500);
    		}
    		else if ((TimeManager.getDaysBetween(from,to)<0))
    		{
    			Toast.makeText(ProjectFX.mainStage, "Please pick a valid date", 1500, 500, 500);
    		}
    		else
    			myController.getChangeRequestsByDateSearch(from, to);
    	}
    	
    }

	@FXML
	void searchByStatus(MouseEvent event) {
		noSubmitingRequest.setVisible(false);
		if (selectStatusComboBox.getSelectionModel().isEmpty())
		{
			Toast.makeText(ProjectFX.mainStage, "Please pick status", 1500, 500, 500);
		}
		else 
		{
			myController.getChangeRequestByStatus(selectStatusComboBox.getSelectionModel().getSelectedItem());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectStatusComboBox.getItems().add("Active");
		selectStatusComboBox.getItems().add("Close");
		selectStatusComboBox.getItems().add("Denied");
		selectStatusComboBox.getItems().add("Suspended");
		requstIdClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		statusClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("status"));
		subSystemClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		startDateClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Date>("StartDate"));
		noSubmitingRequest.setVisible(false);
		displaySpecificID.setEditable(false);
		selectedChangeRequestIdText.setVisible(true);
		fromDatePicker.setEditable(false);
		toDatePicker.setEditable(false);
		myController.fillNecessaryFieldsInTable();
		basicDetailsTbl.setRowFactory(tv -> {
			TableRow<ChangeRequest> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
					currentChangeRequest = row.getItem();
					displaySpecificID.setText(currentChangeRequest.getChangeRequestID().toString());
				}
			});
			return row;
		});
	}

	public void displayAllChangeRequestsForSpecifcUser(ArrayList<ChangeRequest> resultList) {

		requestList.clear();
		if (!resultList.isEmpty()) {
			viewExtraDetailsBtn.setVisible(true);
			requestList.addAll(resultList);
			basicDetailsTbl.setItems(requestList);
		} else {
			noSubmitingRequest.setText("There are no change requests submitted");
			noSubmitingRequest.setVisible(true);
			viewExtraDetailsBtn.setVisible(false);
			displaySpecificID.setVisible(false);
			selectedChangeRequestIdText.setVisible(false);
		}

	}
	public void displayChangeRequestsByFilter(ArrayList<ChangeRequest> resultList)
	{
		requestList.clear();
		if (!resultList.isEmpty()) {
			viewExtraDetailsBtn.setVisible(true);
			requestList.addAll(resultList);
			basicDetailsTbl.setItems(requestList);
		} else {
			noSubmitingRequest.setText("There are no matching change requests");
			noSubmitingRequest.setVisible(true);
			viewExtraDetailsBtn.setVisible(false);
			displaySpecificID.setVisible(false);
			selectedChangeRequestIdText.setVisible(false);
		}
		
	}

}
