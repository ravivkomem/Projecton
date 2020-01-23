package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.EmployeePermissionBoundary;
import client.ClientConsole;
import javafx.application.Platform;

/**
 * The Class EmployeePermissionController.
 *
 * @author Lee Hugi
 * This controller handle with the employee permission page
 */
@SuppressWarnings("serial")
public class EmployeePermissionController extends BasicController{

	/** My employeePermission boundary */
	@SuppressWarnings("unused")
	private EmployeePermissionBoundary myBoundary;
	
	/**
	 * Instantiates a new employee permission controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public EmployeePermissionController(EmployeePermissionBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	/**
	 * this method create sql query that update employee permission in user table.
	 *
	 * @param permission the permission
	 * @param jobDescription the job description
	 * @param id the id
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
	 * this method create sql query that update subsystem supporter table.
	 *
	 * @param subsystem the subsystem
	 * @param userName the user name
	 */
	public void updateSubsystemSupporter(String subsystem,String userName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		varArray.add(subsystem);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_SUBSYSTEM_SUPPORTER, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
