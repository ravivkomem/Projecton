package controllers;

import java.util.ArrayList;

import boundries.LoginPageBoundry;
import client.ChatClient;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;
import other.ServerEvent;
import other.SqlAction;
import other.SqlQueryType;
import other.SqlResult;

public class LoginController extends ServerEvent{

	private LoginPageBoundry myBoundry;

	
	public LoginController (LoginPageBoundry loginPageboundry)
	{
		this.myBoundry = loginPageboundry;
	}
	
	public String verifyLoginCredtinals (String userName, String password)
	{
		String result = null;
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		varArray.add(password);
		/*TODO: Update the login sql and the var array */
		SqlAction sqlAction = new SqlAction(SqlQueryType.VERIFY_LOGIN, new ArrayList<>());
		
		ChatClient.changeRequestByIdListeners.add(this);
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
		return result;
		
	}
	
	public void getResultFromClient(SqlResult result)
	{
		Platform.runLater(() -> {
			myBoundry.displayLoginError();
		});
		return;
		
	}
	
}
