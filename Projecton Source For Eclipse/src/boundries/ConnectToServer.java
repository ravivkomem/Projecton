package boundries;

import assets.ProjectPages;
import assets.Toast;
import client.ClientConsole;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class ConnectToServer {

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
    		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
    		ProjectFX.pagingController.loadBoundray(ProjectPages.DEMO_LANDING_PAGE.getPath());
    	}
    	
    }

}