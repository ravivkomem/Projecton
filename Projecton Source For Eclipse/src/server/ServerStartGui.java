package server;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ServerStartGui {

    @FXML
    private Button ConnectButton;
    @FXML
    private Button DissconnectButton;

    private static boolean isConnected = false;
    private MysqlConnection sqlConnection = new MysqlConnection();
    
    @FXML
    void DissconnectServer(ActionEvent event) throws IOException {
    	
    	if (isConnected == true) {
    		isConnected = false;
    		sqlConnection.disconnectAllLoggedUsers();
    		EchoServer.temp.close();
    	}
    }

    @FXML
    void connectServer(ActionEvent event) {
    	EchoServer.startServer(ServerApp.newargs); 
    	isConnected = true;
    }
}