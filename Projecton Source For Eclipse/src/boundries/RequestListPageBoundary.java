package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.PagingController;
import controllers.RequestListPageController;
import controllers.UploadChangeRequestController;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class RequestListPageBoundary implements Initializable {
	/*Table FXML*/
    @FXML
    private TableView<ChangeRequest> basicDetailsTbl;

    @FXML
    private TableColumn<ChangeRequest, Integer> requstIdClm;

    @FXML
    private TableColumn<ChangeRequest, String> statusClm;

    @FXML
    private TableColumn<ChangeRequest, String> descClm;

    @FXML
    private TableColumn<ChangeRequest, String> subSystemClm;
    /*FXML TextField*/
    @FXML
    private Text noSubmitingRequest;

    /*Button FXML*/
    @FXML
    private Button viewExtraDetailsBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button logoutUser;
    @FXML
    private TextField displaySpecificID;
    
    private RequestListPageController myController= new RequestListPageController(this);
    ObservableList<ChangeRequest> requestList = FXCollections.observableArrayList();
    ChangeRequest currentChangeRequest;
    
    /*FXML Methods*/

    @FXML
    void backToPrevPage(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void extraDetailsShows(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.EXTRA_DETAILS_PAGE.getPath(),currentChangeRequest);
    	
    }

    @FXML
    void logout(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		requstIdClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, Integer>("changeRequestID"));
		statusClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("status"));
		descClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("currentStateDescription"));
		subSystemClm.setCellValueFactory(new PropertyValueFactory<ChangeRequest, String>("selectedSubsystem"));
		noSubmitingRequest.setVisible(false);
		displaySpecificID.setEditable(false);
		myController.fillNecessaryFieldsInTable();
		basicDetailsTbl.setRowFactory(tv -> {
		    TableRow<ChangeRequest> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY)
		        {
		        	currentChangeRequest = row.getItem();
		        	displaySpecificID.setText(currentChangeRequest.getChangeRequestID().toString());
		        }
		    });
		    return row ;
		});
	}
	public void displayAllChangeRequestsForSpecifcUser(ArrayList<ChangeRequest> resultList)
	{
		
		requestList.clear();
		if (!resultList.isEmpty()) 
		{
			requestList.addAll(resultList);
			basicDetailsTbl.setItems(requestList); 
		}
		else
		{
			noSubmitingRequest.setVisible(true);
			viewExtraDetailsBtn.setVisible(false);
			displaySpecificID.setVisible(false);
		}
		
	}

}
