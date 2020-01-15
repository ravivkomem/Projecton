package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import client.ClientConsole;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

// TODO: Auto-generated Javadoc
/**
 * This boundary is the first display on the client side,
 * allows us to pick the IP where the server is running.
 *
 * @author Raviv Komem
 */
public class ConnectToServer implements Initializable {

	/** The First pane. */
	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
    @FXML
    private Pane FirstPane;
    
    /** The Server I ptext. */
    @FXML
    private TextField ServerIPtext;
    
    /** The Connectbtn. */
    @FXML
    private Button Connectbtn;

    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    
    /**
     * Method is called when the connection button is pressed
     * Checks if the server ip field is filled, and if it is starts the connection sequence
     * and moves us to the login page.
     *
     * @param event the event
     */
    @FXML
    void connectionToServer(MouseEvent event) {
    	String serverIp = ServerIPtext.getText();
    	if (serverIp.equals(""))
    	{
    		Toast.makeText(ProjectFX.mainStage, "Must fill the server IP", 1500, 500, 500);
    	}
    	else
    	{
    		String [] args = {serverIp};
    		ClientConsole.connection(args);
    		ProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());//change back to LOGIN_PAGE
    	}
    	
    }

    /**
     * Sets key pressed handler on the text field, that enter will also 
     * be considered as click on the connection button.
     *
     * @param location the location
     * @param resources the resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ServerIPtext.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER))
	            {
					connectionToServer(null);
	            }
				
			}
	
		});
		
	}

}