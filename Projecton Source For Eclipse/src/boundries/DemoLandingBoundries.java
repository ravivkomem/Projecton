package boundries;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DemoLandingBoundries {

    @FXML
    private TextField changeRequestTextField;

    @FXML
    private Button viewButton;

    @FXML
    private TableView<?> changeRequestDetailsTableView;

    @FXML
    private Button updateButton;



    @FXML
    void displayChangeRequestDetails(MouseEvent event) {
    	System.out.println(changeRequestTextField.getText());
    	changeRequestDetailsTableView.setVisible(true);
    	updateButton.setVisible(true);
    	DemoLandingController.sendMsgToServer(changeRequestTextField.getText());
    }
    @FXML
    void updateChangeRequestTable(MouseEvent event) {

    }

}
