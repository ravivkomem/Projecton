package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.EmployeePermissionBoundary;
import client.ClientConsole;
import javafx.application.Platform;

/**
 * 
 * @author Lee Hugi
 * This controller handle with the employee permission page
 *
 */
public class EmployeePermissionController extends BasicController{

	private EmployeePermissionBoundary myBoundary;
	
	public EmployeePermissionController(EmployeePermissionBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	/**
	 * this method create sql query that update employee permission in user table
	 * @param permission
	 * @param jobDescription
	 * @param id
	 */
	public void updateEmployeePermission(String permission, String jobDescription, int id) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(permission);
		varArray.add(jobDescription);
		varArray.add(id);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_EMPLOYEE_PERMISSION, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * this method create sql query that update subsystem supporter table
	 * @param subsystem
	 * @param userName
	 */
	public void updateSubsystemSupporter(String subsystem,String userName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		varArray.add(subsystem);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_SUBSYSTEM_SUPPORTER, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch (result.getActionType()) {
			case UPDATE_EMPLOYEE_PERMISSION:
				this.unsubscribeFromClientDeliveries();
			default:
				break;
			}
		});
		return;	
	}

}
