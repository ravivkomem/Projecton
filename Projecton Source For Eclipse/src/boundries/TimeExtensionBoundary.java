package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.Step;
import assets.Toast;
import entities.ChangeRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TimeExtensionBoundary implements DataInitializable  {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/*Buttons*/
    @FXML
    private Button subimitTimeExtensionButton;
    @FXML
    private Button closeTimeExtensionButton;
    /*Date Picker */
    @FXML
    private DatePicker datePicker;
    /*Text*/
    @FXML
    private Text timeExtensionHeaderText;
    /*Text Fields*/
    @FXML
    private TextField reasonTextField;
    @FXML
    private TextField currentStepTextField;
    @FXML
    private TextField currentEndDateTextField;

    /* *************************************
	 * ******* Private Variables ***********
	 * *************************************/
    private Alert alert = new Alert(AlertType.ERROR);
    private Step myStep;
    
	/* *************************************
	 * ********* FXML Methods **************
	 * *************************************/
    @FXML
    void closeTimeExtensionPage(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void submitTimeExtensionRequest(ActionEvent event) {
    	
    	if (datePicker.getValue() == null)
    	{
    		alert.setTitle("ERROR");
    		alert.setHeaderText("Must fill all required fields");
    		alert.setContentText("Date field is empty");
    		alert.show();
    	}
    	else
    	{
    		java.sql.Date selectedDate = java.sql.Date.valueOf(datePicker.getValue());
    		System.out.println(selectedDate.toString());
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currentStepTextField.setEditable(false);
		currentEndDateTextField.setEditable(false);
		
		reasonTextField.setAlignment(Pos.TOP_LEFT);
	}

	@Override
	public void initData(Object data) {
		
		try 
		{
			Step myStep = (Step) data;
			currentStepTextField.setText(myStep.getType().getStepName());
			currentEndDateTextField.setText(myStep.getEstimatedEndDate().toString());
		}
		catch (ClassCastException e)
		{
			e.printStackTrace();
			Toast.makeText(ProjectFX.mainStage, "Error Loading Time Extension Page", 1500, 500, 500);
			this.closeTimeExtensionPage(new ActionEvent());
		}
	}

}
