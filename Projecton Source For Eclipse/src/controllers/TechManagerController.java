package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.TechManagerBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.SubsystemSupporter;
import entities.User;
import javafx.application.Platform;

/**
 * The Class TechManagerController.
 *
 * @author Lee Hugi
 * This controller handle with the Tech Manager page
 */

@SuppressWarnings("serial")
public class TechManagerController extends BasicController {
	
	/** The my boundary. */
	private TechManagerBoundary myBoundary;

	/**
	 * Instantiates a new tech manager controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public TechManagerController(TechManagerBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	/**
	 * this method create sql query that ask for all the change request in the data base.
	 *
	 * @return the all the active change request
	 */
	public void getAllTheActiveChangeRequest() {
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS, new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * this method crate sql query that ask for all the information engineer from the data base.
	 *
	 * @return the all the employee
	 */
	public void getAllTheEmployee() {
				SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_EMPLOYEE, new ArrayList<Object>());
				this.subscribeToClientDeliveries();		//subscribe to listener array
				ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * this method create sql query that ask for all the subsystem for specific user.
	 *
	 * @param userName the user name
	 * @return the subsystem supporter by user name
	 */
	public void getSubsystemSupporterByUserName(String userName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_SUBSYSTEM_BY_USER_NAME, varArray);
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
			case SELECT_ALL_CHANGE_REQUESTS:
				ArrayList<ChangeRequest> changeRequestList = createChangeRequestFromResult(result);
				myBoundary.displayChangeRequestTable(changeRequestList);
				break;
			case SELECT_ALL_EMPLOYEE:
				ArrayList<User> userList = createUserListFromResult(result);
				myBoundary.displayAllTheEmployeesTable(userList);
				break;
			case SELECT_SUBSYSTEM_BY_USER_NAME:
				myBoundary.displaySubsystemTable(createSubsystemSupporter(result));
				break;
			default:
				break;
			}

		});
		return;
	}
	
	/**
	 * return array list of subsystem supporter from the data base result.
	 *
	 * @param result the result
	 * @return the array list
	 */
	private ArrayList<SubsystemSupporter> createSubsystemSupporter(SqlResult result){
		ArrayList<SubsystemSupporter> list = new ArrayList<>();
		for(ArrayList<Object> s: result.getResultData()) {
			SubsystemSupporter subsystem = new SubsystemSupporter((String)s.get(0),(String)s.get(1));
			list.add(subsystem);
		}
		return list;
	}
	
	/**
	 * The method create from result ChangeRequest list.
	 *
	 * @param result the result
	 * @return change request list
	 */
	private ArrayList<ChangeRequest> createChangeRequestFromResult(SqlResult result){
		ArrayList<ChangeRequest> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			ChangeRequest changeRequest=new ChangeRequest((Integer)a.get(0), (String)a.get(1), (Date)a.get(2), (String)a.get(3),
					(String)a.get(4), (String)a.get(5), (String)a.get(6),(String)a.get(7), (String)a.get(8),
					(String)a.get(9), (String)a.get(10), (Date)a.get(11),(String)a.get(14),(Date)a.get(15));
			resultList.add(changeRequest);
		}
		return resultList;
	}
	
	/**
	 * The method create from result User list.
	 *
	 * @param result the result
	 * @return user list
	 */
	private ArrayList<User> createUserListFromResult(SqlResult result){
		ArrayList<User> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			User user=new User((Integer)a.get(0), (String)a.get(1), (String)a.get(2),(String)a.get(3), 
				(String)a.get(4), (String)a.get(5),(String)a.get(6),(String)a.get(7), (String)a.get(8), (String)a.get(9));
			resultList.add(user);
		}
		return resultList;
	}

}
