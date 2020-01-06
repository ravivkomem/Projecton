package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import client.ClientConsole;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class ConnectToServer implements Initializable {

    @FXML
    private Pane FirstPane;
    @FXML
    private TextField ServerIPtext;
    @FXML
    private Button Connectbtn;

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