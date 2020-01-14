package controllers;

import java.sql.Date;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.TechManagerBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.SubsystemSupporter;
import entities.User;
import javafx.application.Platform;

/**
 * 
 * @author Lee Hugi
 *This controller handle with the Tech Manager page
 */

public class TechManagerController extends BasicController {
	private TechManagerBoundary myBoundary;

	public TechManagerController(TechManagerBoundary myBoundary) {
		this.myBoundary = myBoundary;
	}

	public void getAllTheActiveChangeRequest() {
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUESTS, new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getAllTheEmployee() {
				SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_EMPLOYEE, new ArrayList<Object>());
				this.subscribeToClientDeliveries();		//subscribe to listener array
				ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getSubsystemSupporterByUserName(String userName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(userName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_SUBSYSTEM_BY_USER_NAME, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
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
	
	private ArrayList<SubsystemSupporter> createSubsystemSupporter(SqlResult result){
		ArrayList<SubsystemSupporter> list = new ArrayList<>();
		for(ArrayList<Object> s: result.getResultData()) {
			SubsystemSupporter subsystem = new SubsystemSupporter((String)s.get(0),(String)s.get(1));
			list.add(subsystem);
		}
		return list;
	}
	
	/**
	 * The method create from result ChangeRequest list
	 * @param result
	 * @return change request list
	 */
	private ArrayList<ChangeRequest> createChangeRequestFromResult(SqlResult result){
		ArrayList<ChangeRequest> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			ChangeRequest changeRequest=new ChangeRequest((Integer)a.get(0), (String)a.get(1), (Date)a.get(2), (String)a.get(3),
					(String)a.get(4), (String)a.get(5), (String)a.get(6),(String)a.get(7), (String)a.get(8),
					(String)a.get(9), (String)a.get(10), (Date)a.get(11),(String)a.get(15),(Date)a.get(16));
			resultList.add(changeRequest);
		}
		return resultList;
	}
	
	/**
	 * The method create from result User list
	 * @param result
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
