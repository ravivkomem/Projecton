package boundries;

import java.net.URL;
import java.sql.Date;
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

// TODO: Auto-generated Javadoc
/**
 * This class control the analysis report page.
 *
 * @author Lee Hugi
 */
public class AnalysisReportBoundary implements DataInitializable{

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/** The report page title. */
	/*Text */
    @FXML
    private Text reportPageTitle;
    
    /** The description text area. */
    @FXML
    private TextArea descriptionTextArea;
    
    /** The advavtages text area. */
    @FXML
    private TextArea advavtagesTextArea;
    
    /** The constraints text area. */
    @FXML
    private TextArea constraintsTextArea;
    
    /** The author txt. */
    @FXML
    private Text authorTxt;
    
    /** The duration txt. */
    @FXML
    private Text durationTxt;
    
    /** The subsystem txt. */
    @FXML
    private Text subsystemTxt;
    
    /** The header text. */
    @FXML
    private Text headerText;
    
    /** The btn close page. */
    /*Button */
    @FXML
    private Button btnClosePage;
    
    /** The current change request. */
    /* *************************************
	 * ******* Private Objects *************
	 * *************************************/
    private ChangeRequest currentChangeRequest;
    
    /** The my controller. */
    private AnalysisReportController myController = new AnalysisReportController(this);
	
    /* (non-Javadoc)
     * @see boundries.DataInitializable#initData(java.lang.Object)
     */
    /* *************************************
	 * ******* Public Methods *************
	 * *************************************/
	@Override
	public void initData(Object data) {
		this.currentChangeRequest = (ChangeRequest)data;
		myController.getAnalysisReportByChangeRequestId(currentChangeRequest.getChangeRequestID());
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
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
	 * the method display the report on the page.
	 *
	 * @param resultList the result list
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
			durationTxt.setText(((Date) resultList.get(4)).toString());
			constraintsTextArea.setText((String) resultList.get(5));
		}
    }
    
    /* *************************************
	 * ******* FXML Methods *************
	 * *************************************/
    
    /**
     * Closing page.
     *
     * @param event the event
     */
    @FXML
    void closingPage(MouseEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

}