package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.AnalysisReportController;
import controllers.CommitteDecisionController;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class AnalysisReportBoundary implements Initializable{

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
    
    private ChangeRequest currentChangeRequest;

    private AnalysisReportController myController = new AnalysisReportController(this);
    
	public void setCurrentChangeRequest(ChangeRequest currentChangeRequest) {
		this.currentChangeRequest = currentChangeRequest;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		reportPageTitle.setText("Report Of Request No."+currentChangeRequest.getChangeRequestID());
		myController.askForAnalysisReportByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}
    
    

}