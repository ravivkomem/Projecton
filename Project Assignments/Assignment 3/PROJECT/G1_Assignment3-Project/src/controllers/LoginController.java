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
 * The Class LoginController.
 *
 * @author Raviv Komem
 * This controller handles all communication between the OFSF client and the Login page
 */
@SuppressWarnings("serial")
public class LoginController extends BasicController{

	/** The my boundary. */
	private LoginPageBoundary myBoundary;

	/**
	 * Instantiates a new login controller.
	 *
	 * @param loginPageboundary the login pageboundary
	 */
	public LoginController (LoginPageBoundary loginPageboundary)
	{
		this.myBoundary = loginPageboundary;
	}
	
	/**
	 * Verify login credtinals.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	public void verifyLoginCredtinals (String userName, String password)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		varArray.add(password);
		SqlAction sqlAction = new SqlAction(SqlQueryType.VERIFY_LOGIN, varArray);
		
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * Change user login status.
	 *
	 * @param user the user
	 * @param loginStatus the login status
	 */
	public void changeUserLoginStatus(User user, boolean loginStatus)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(loginStatus);
		varArray.add(user.getUserID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_USER_LOGIN_STATUS, varArray);
		
		this.sendSqlActionToClient(sqlAction);
	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
				case UPDATE_USER_LOGIN_STATUS:
					int affectedRows = (int)result.getResultData().get(0).get(0);
					this.unsubscribeFromClientDeliveries();
					
					if (affectedRows != 1)
					{
						System.out.println("Error with user login");
					}
					
					break;
				default:
					break;
			}
		});
		return;
		
	}
	
	/**
	 * Parses the result to user.
	 *
	 * @param result the result
	 * @return the user
	 */
	private User parseResultToUser(SqlResult result)
	{
		User resultUser = null;
		if (!result.getResultData().isEmpty())
		{
			ArrayList<Object> resultList = result.getResultData().get(0);
			
			int userID = (Integer) resultList.get(0);
			String userName = (String) resultList.get(1);
			String password = (String) resultList.get(2);
			String firstName = (String) resultList.get(3);
			String lastName = (String) resultList.get(4);
			String email = (String) resultList.get(5);
			String department = (String) resultList.get(6);
			String jobDescription = (String) resultList.get(7);
			String permission = (String) resultList.get(8);
			String phoneNumber= (String) resultList.get(9);
			boolean isLogged = (Boolean) resultList.get(10);

			resultUser = new User(userID, userName, password, firstName, lastName, email,
					department, jobDescription, permission, phoneNumber, isLogged);
		}
		return resultUser;
	}
	
}
