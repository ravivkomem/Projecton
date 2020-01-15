package server;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


// TODO: Auto-generated Javadoc
/**
 * The Class ServerStartGui.
 */
public class ServerStartGui {

    /** The Connect button. */
    @FXML
    private Button ConnectButton;
    
    /** The Dissconnect button. */
    @FXML
    private Button DissconnectButton;

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
    }
}