package controllers;

import java.util.ArrayList;

import assets.ServerEvent;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.LoginPageBoundary;
import client.ChatClient;
import client.ClientConsole;
import entities.ChangeRequest;
import javafx.application.Platform;

public class LoginController extends ServerEvent{

	private LoginPageBoundary myBoundry;

	
	public LoginController (LoginPageBoundary loginPageboundry)
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
