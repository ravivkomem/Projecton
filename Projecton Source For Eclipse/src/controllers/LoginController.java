package controllers;

import java.util.ArrayList;

import entities.User;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.LoginPageBoundary;
import client.ClientConsole;
import javafx.application.Platform;

/**
 * 
 * @author Raviv Komem
 * This controller handles all communication between the OFSF client and the Login page
 */
public class LoginController extends BasicController{

	private LoginPageBoundary myBoundary;

	public LoginController (LoginPageBoundary loginPageboundary)
	{
		this.myBoundary = loginPageboundary;
	}
	
	public void verifyLoginCredtinals (String userName, String password)
	{
		/*TODO: Check if userName already exists in logged users list */
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		varArray.add(password);
		SqlAction sqlAction = new SqlAction(SqlQueryType.VERIFY_LOGIN, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getResultFromClient(SqlResult result)
	{
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case VERIFY_LOGIN:
					User resultUser = this.parseResultToUser(result);
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleUserAttempInformation(resultUser);
					break;
				
				default:
					break;
			}
		});
		return;
		
	}
	
	private User parseResultToUser(SqlResult result)
	{
		User resultUser = null;
		if (!result.getResultData().isEmpty())
		{
			ArrayList<Object> resultList = result.getResultData().get(0);
			
			String userName = (String) resultList.get(0);
			String userPermission = (String) resultList.get(1);
			String userEmail = (String) resultList.get(2);
			resultUser = new User(userName, userPermission, userEmail);
		}
		return resultUser;
	}
	
}
