package boundries;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assets.Toast;
import controllers.AnalysisReportController;
import controllers.CommitteDecisionController;
import entities.ChangeRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
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
    
    @FXML
    private Button btnClosePage;
    
    private ChangeRequest currentChangeRequest;

    private AnalysisReportController myController = new AnalysisReportController(this);
    
	public void setCurrentChangeRequest(ChangeRequest currentChangeRequest) {
		this.currentChangeRequest = currentChangeRequest;
		myController.getAnalysisReportByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		descriptionTextArea.setEditable(false);
		advavtagesTextArea.setEditable(false);
		constraintsTextArea.setEditable(false);
	}
    
    public void displayAnalysisReport(ArrayList<Object> resultList) {
    	if(resultList==null) {
    		return;
    	}
    	else {
    	reportPageTitle.setText("Report Of Request No."+currentChangeRequest.getChangeRequestID().toString());
    	subsystemTxt.setText(currentChangeRequest.getSelectedSubsystem());
    	authorTxt.setText((String)resultList.get(0));
    	descriptionTextArea.setText((String)resultList.get(1));
    	advavtagesTextArea.setText((String)resultList.get(2));
    	constraintsTextArea.setText((String)resultList.get(3));
    	durationTxt.setText((String)resultList.get(4));
    	}
    }
    
    @FXML
    void closingPage(MouseEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

}