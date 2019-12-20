package boundries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.TesterController;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TesterBoundary implements DataInitializable {
	private ChangeRequest currentChangeRequest;
private TesterController mycontroller = new TesterController(this);
    @FXML
    private AnchorPane FailDetailsPane;

	    @FXML
	    private Button backButton;
	    @FXML
	    private Button setButton;
	    @FXML
	    private Button reportfailButton;
	    @FXML
	    private TextField failuredetailsField;
	    @FXML
	    private Button logoutButton;
	    @FXML
	    private Button homepageButton;
	    @FXML
	    private Button analysisreportButton;

	    @FXML
	    private TextArea timeremainingField;

	    @FXML
	    private ComboBox<String> testapprovalComboBox;

	    @FXML
	    void LogOut(MouseEvent event) {
	    	ProjectFX.currentUser = null;
			//((Node) event.getSource()).getScene().getWindow().hide(); 		// hiding primary window
			ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
	    }

	    @FXML
	    void loadAnalysisReport(MouseEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ProjectPages.ANALISIS_REPORT_PAGE.getPath()));
				Parent root;
				root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void loadHomePage(MouseEvent event) {
	    //	((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
	    }

	    @FXML
	    void loadPreviousPage(MouseEvent event) {
	    //	((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			ProjectFX.pagingController.loadBoundary(ProjectPages.WORK_STATION_PAGE.getPath());
	    }

	    @FXML
	    void loadTimeExtensionPage(MouseEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ProjectPages.TIME_EXTENSION_PAGE.getPath()));
				Parent root;
				root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }

	    @FXML
	    void setReportFail(MouseEvent event) {
	    	currentChangeRequest.setCurrentStep("Execution");
			mycontroller.updateChangeRequestStep(currentChangeRequest,failuredetailsField.getText());

	    }

	    @FXML
	    void setTest(MouseEvent event) {
	    	switch (testapprovalComboBox.getSelectionModel().getSelectedItem()) {
			case "Approve":
				// Update Information and move to next step
				break;
			case "Deny":
				FailDetailsPane.setVisible(true);
				
				break;
			
			default:
				break;
			}
	    }
	    public void updateTesterPageToDBSuccessfully(int affectedRows) {
	    	if(affectedRows == 1) {
	    		Toast.makeText(ProjectFX.mainStage, "Updated successfully", 1500, 500, 500);
	    	}else {
	    		Toast.makeText(ProjectFX.mainStage, "Updated failed", 1500, 500, 500);
	    	}
	    }

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		FailDetailsPane.setVisible(false);
		testapprovalComboBox.getItems().add("Deny");
		testapprovalComboBox.getItems().add("Approval");
		
		
	}

	@Override
	public void initData(Object data) {
		// TODO Auto-generated method stub
		currentChangeRequest = (ChangeRequest)data;
	}

	}
