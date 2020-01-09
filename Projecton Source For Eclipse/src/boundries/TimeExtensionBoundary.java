package boundries;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import assets.Toast;
import controllers.TimeExtensionController;
import controllers.TimeManager;
import entities.Step;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class TimeExtensionBoundary implements DataInitializable  {

	/* *************************************
	 * ********* FXML Objects **************
	 * *************************************/
	
	/*Buttons*/
    @FXML
    private Button submitTimeExtensionButton;
    @FXML
    private Button closeTimeExtensionButton;
    /*Date Picker */
    @FXML
    private DatePicker datePicker;
    /*Text*/
    @FXML
    private Text timeExtensionHeaderText;
    @FXML
    private Label reasonCharsLabel;
    /*Text Fields*/
    @FXML
    private TextArea reasonTextArea;
    @FXML
    private TextField currentStepTextField;
    @FXML
    private TextField currentEndDateTextField;
    /*Image View */
    @FXML
    private ImageView loadingGif;

    /* *************************************
	 * ******* Private Variables ***********
	 * *************************************/
    private Alert alert = new Alert(AlertType.NONE);
    private Step myStep;
    private TimeExtensionController myController = new TimeExtensionController(this);
    private static final int MAX_CHARS = 140; 
    
	/* *************************************
	 * ********* FXML Methods **************
	 * *************************************/
    @FXML
    void closeTimeExtensionPage(ActionEvent event) {
    	closeTimeExtensionButton.getScene().getWindow().hide();
    }

    @FXML
    void updateReasonCharsLabel(KeyEvent event) {
    	reasonCharsLabel.setText(reasonTextArea.getText().length()+"/"+MAX_CHARS);
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
    	else if (reasonTextArea.getText().equals(""))
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
    		/* Decide which is bigger, current date or estimated end date */
    		Date dayToCompare = TimeManager.getMaxDate(myStep.getEstimatedEndDate(), TimeManager.getCurrentDate());
        	/* Date is prior to the current date */
    		long daysBetween = TimeManager.getDaysBetween(dayToCompare, selectedDate);
        	if (daysBetween <= 0)
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
        		String timeExtensionReason = reasonTextArea.getText();
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
    		alert.setAlertType(AlertType.INFORMATION);
    		alert.setTitle("Success");
    		alert.setHeaderText("Your time extension request was submitted successfully");
    		alert.setContentText("The page will now close");
    		alert.show();
    		alert.setOnCloseRequest(new EventHandler <DialogEvent>() {
    			 @Override
		        public void handle (DialogEvent dialogEvent) {
    				 closeTimeExtensionButton.getScene().getWindow().hide();
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
    
    public void recieveTimeExtensionCounter(long timeExtensionCounter)
    {
    	/* Story request - for every step you can submit only one time extension request */
    	if (timeExtensionCounter >= 1)
    	{
    		Toast.makeText(ProjectFX.mainStage, "You already requested time extension for this step", 1500, 500, 500);
			closeTimeExtensionButton.getScene().getWindow().hide();
    	}
    	/* Load all page functions */
    	else
    	{
    		/*Updating text*/
			timeExtensionHeaderText.setText("Requesting Time Extension For Change Request No."+myStep.getChangeRequestID());
			currentStepTextField.setText(myStep.getType().getStepName());
			currentEndDateTextField.setText(myStep.getEstimatedEndDate().toString());
			/*Visibility changes*/
			submitTimeExtensionButton.setVisible(true);
			closeTimeExtensionButton.setVisible(true);
			datePicker.setVisible(true);
			timeExtensionHeaderText.setVisible(true);
			reasonTextArea.setVisible(true);
			currentStepTextField.setVisible(true);
			currentEndDateTextField.setVisible(true);
			
			loadingGif.setVisible(false);
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Setup assignments */
		currentStepTextField.setEditable(false);
		currentEndDateTextField.setEditable(false);
		reasonTextArea.setWrapText(true);
		datePicker.setEditable(false);
		reasonCharsLabel.setText("0/"+MAX_CHARS);
		
		reasonTextArea.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= MAX_CHARS ? change : null));
		
		
		/*Visibility changes */
		submitTimeExtensionButton.setVisible(false);
		closeTimeExtensionButton.setVisible(false);
		datePicker.setVisible(false);
		timeExtensionHeaderText.setVisible(false);
		reasonTextArea.setVisible(false);
		currentStepTextField.setVisible(false);
		currentEndDateTextField.setVisible(false);
		
		loadingGif.setVisible(true);
	}

	@Override
	public void initData(Object data) {
		
		try 
		{
			myStep = (Step) data;
			Date currentDate = TimeManager.getCurrentDate();
			long daysBetween = TimeManager.getDaysBetween(currentDate, myStep.getEstimatedEndDate());
			/* Story requirements - can only request time extension for less than 3 days */
			if (daysBetween > 3)
			{
				long daysUntilRequest = daysBetween-3;
				Toast.makeText(ProjectFX.mainStage, "You can request time extension in " + daysUntilRequest +" days", 1500, 500, 500);
				closeTimeExtensionButton.getScene().getWindow().hide();
			}
			else
			{
				myController.verifyNoPreviousExtensions(myStep);
			}
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
