package server;

import java.io.IOException;

import client.ChatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import other.ServerEvent;


public class ServerStartGui extends ServerEvent {

    @FXML
    private Button ConnectButton;

    @FXML
    private Button DissconnectButton;

    private static int flag=0;
    
    private MysqlConnection mysql;
    
    @FXML
    void DissconnectServer(ActionEvent event) throws IOException {
    	
    	if (flag==1) {
    		flag=0;
    		mysql = new MysqlConnection();
    		//mysql.exitAllClients();
    		//EchoServer.temp.close();
    	//	ChatClient.AddExitAlllistener(this);
    	//	UserController.exitAllClients();
    	}
    }

    @FXML
    void connectServer(ActionEvent event) {
    	//EchoServer.StartServer(ServerApp.newargs); 
    	flag=1;
    }


    public void closeServer() throws IOException {
    
    }
}