package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

import assets.StepType;
import assets.Toast;
import controllers.TimeExtensionController;
import entities.Step;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogEvent;
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
    private Alert alert = new Alert(AlertType.NONE);
    //private Step myStep;
    private Step myStep = new Step(StepType.COMMITTEE, 2, 78, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
    private TimeExtensionController myController = new TimeExtensionController(this);
    private TimeExtensionBoundary myInstance = this;
    
	/* *************************************
	 * ********* FXML Methods **************
	 * *************************************/
    @FXML
    void closeTimeExtensionPage(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void submitTimeExtensionRequest(ActionEvent event) {
    	
    	/*Check if any of the field are empty */
    	if (datePicker.getValue() == null)
    	{
    		alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText("Must fill all required fields");
    		alert.setContentText("Date field is empty");
    		alert.show();
    	}
    	else if (reasonTextField.getText().equals(""))
    	{
    		alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText("Must fill all required fields");
    		alert.setContentText("Reason field is empty");
    		alert.show();
    	}
    	else
    	{
    		Date selectedDate = Date.valueOf(datePicker.getValue());
        	/* Date is prior to the current date */
        	if (selectedDate.toLocalDate().isBefore(myStep.getEstimatedEndDate().toLocalDate()))
        	{
        		alert.setAlertType(AlertType.ERROR);
        		alert.setTitle("ERROR");
        		alert.setHeaderText("Date error");
        		alert.setContentText("You can not select a date before the current estimated end date");
        		alert.show();
        	}
        	/* Both fields are full and valid */
        	else
        	{
        		String timeExtensionReason = reasonTextField.getText();
        		myController.submitTimeExtensionRequest(myStep, selectedDate, timeExtensionReason);
        	}
    	}
    }

    /* *************************************
	 * ********* Public Methods ************
	 * *************************************/
    
    public void recieveSubmissionAnswer(int affectedRows)
    {
    	if (affectedRows == 1)
    	{
    		alert.setAlertType(AlertType.CONFIRMATION);
    		alert.setTitle("Success");
    		alert.setHeaderText("Your time extension request was submitted successfully");
    		alert.setContentText("The page will now close");
    		alert.show();
    		alert.setOnCloseRequest(new EventHandler <DialogEvent>() {
    			 @Override
		        public void handle (DialogEvent dialogEvent) {
    				 myInstance.closeTimeExtensionButton.getScene().getWindow().hide();
		        }
    		});
    	}
    	else
    	{
    		alert.setAlertType(AlertType.WARNING);
    		alert.setTitle("WARNING");
    		alert.setHeaderText("SERVER ISSUES");
    		alert.setContentText("The request was not recieved by the server, please try again later");
    		alert.show();
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
			timeExtensionHeaderText.setText("Requesting Time Extension For Change Request No."+myStep.getChangeRequestID());
			currentStepTextField.setText(myStep.getType().getStepName());
			currentEndDateTextField.setText(myStep.getEstimatedEndDate().toString());
		}
		/* Either null pointer exception or class cast */
		catch (Exception e)
		{
			e.printStackTrace();
			Toast.makeText(ProjectFX.mainStage, "Error Loading Time Extension Page", 1500, 500, 500);
			this.closeTimeExtensionPage(new ActionEvent());
		}
	}

}
