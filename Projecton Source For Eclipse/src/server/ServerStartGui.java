package server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import assets.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


// TODO: Auto-generated Javadoc
/**
 * The Class ServerStartGui.
 */
public class ServerStartGui implements Initializable{

    /** The Connect button. */
    @FXML
    private Button ConnectButton;
    
    /** The Dissconnect button. */
    @FXML
    private Button DissconnectButton;
    
    @FXML
    private Label serverStatusLabel;
    
    /** The is connected. */
    private static boolean isConnected = false;
    
    /** The sql connection. */
    private MysqlConnection sqlConnection = new MysqlConnection();
    
    /**
     * Dissconnect server.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void DissconnectServer(ActionEvent event) throws IOException {
    	
    	if (isConnected == true) {
    		isConnected = false;
    		sqlConnection.disconnectAllLoggedUsers();
    		EchoServer.temp.close();
    		DissconnectButton.setDisable(true);
    		ConnectButton.setDisable(false);
    		serverStatusLabel.setText("Server status is: DISCONNECTED");
    	}
    }

    /**
     * Connect server.
     *
     * @param event the event
     */
    @FXML
    void connectServer(ActionEvent event) {
    	EchoServer.startServer(ServerApp.newargs); 
    	isConnected = true;
    	DissconnectButton.setDisable(false);
		ConnectButton.setDisable(true);
		serverStatusLabel.setText("Server status is: CONNECTED");
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DissconnectButton.setDisable(true);
		ConnectButton.setDisable(false);
		serverStatusLabel.setText("Server status is: DISCONNECTED");
		
	}
}