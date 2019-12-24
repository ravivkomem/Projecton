package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ExtraDetailsChangeRequestBoundary implements DataInitializable {

    @FXML
    private Button backBtn;

    @FXML
    private TextField initiatorNameTF;

    @FXML
    private TextField subSystemTF;

    @FXML
    private TextField currentStateDescTF;

    @FXML
    private TextArea RequestedCahgngeDescTF;

    @FXML
    private Button viewUploadFileBtn;

    @FXML
    private TextArea reasonTF;

    @FXML
    private TextArea commentTF;

    @FXML
    private TextField StatusTF;

    @FXML
    private Button logoutUser;

    @FXML
    void backBtn(MouseEvent event) {
    	ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    }

    @FXML
    void logoutUser(MouseEvent event) {
    	ProjectFX.pagingController.userLogout();
		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }

    @FXML
    void viewUploadFileBtn(MouseEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initiatorNameTF.setEditable(false);
		subSystemTF.setEditable(false);
		currentStateDescTF.setEditable(false);
		RequestedCahgngeDescTF.setEditable(false);
		reasonTF.setEditable(false);
		commentTF.setEditable(false);
		StatusTF.setEditable(false);
	}

	@Override
	public void initData(Object data) {
		// TODO Auto-generated method stub
		
	}

}