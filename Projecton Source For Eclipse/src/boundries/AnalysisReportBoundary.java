package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.AnalysisReportController;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnalysisReportBoundary implements DataInitializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/*Text */
    @FXML
    private Text reportPageTitle;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea advavtagesTextArea;
    @FXML
    private TextArea constraintsTextArea;
    @FXML
    private Text authorTxt;
    @FXML
    private Text durationTxt;
    @FXML
    private Text subsystemTxt;
    @FXML
    private Text headerText;
    /*Button */
    @FXML
    private Button btnClosePage;
    
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    private ChangeRequest currentChangeRequest;
    private AnalysisReportController myController = new AnalysisReportController(this);
	
    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
	@Override
	public void initData(Object data) {
		this.currentChangeRequest = (ChangeRequest)data;
		myController.getAnalysisReportByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		descriptionTextArea.setEditable(false);
		advavtagesTextArea.setEditable(false);
		constraintsTextArea.setEditable(false);
		descriptionTextArea.setWrapText(true);
		advavtagesTextArea.setWrapText(true);
		constraintsTextArea.setWrapText(true);;
	}
    
	/**
	 * this method gets array list with all the report details
	 * the method display the report on the page
	 * @param resultList
	 */
    public void displayAnalysisReport(ArrayList<Object> resultList) {
    	if(resultList==null) {
    		Toast.makeText(ProjectFX.mainStage, "Could not load analysis report", 1500, 500, 500);
    		Stage stage = (Stage) btnClosePage.getScene().getWindow();
    		stage.close();
		} else {
			reportPageTitle.setText("Report Of Request No." + currentChangeRequest.getChangeRequestID().toString());
			subsystemTxt.setText(currentChangeRequest.getSelectedSubsystem());
			authorTxt.setText((String) resultList.get(0));
			headerText.setText("Header:		"+(String) resultList.get(1));
			descriptionTextArea.setText((String) resultList.get(2));
			advavtagesTextArea.setText((String) resultList.get(3));
			durationTxt.setText((String) resultList.get(4));
			constraintsTextArea.setText((String) resultList.get(5));
		}
    }
    
    /* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
    @FXML
    void closingPage(MouseEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

}