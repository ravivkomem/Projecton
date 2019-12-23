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
import javafx.application.Platform;

public class TechManagerController extends BasicController {
	private TechManagerBoundary myBoundry;

	public TechManagerController(TechManagerBoundary myBoundry) {
		this.myBoundry = myBoundry;
	}

	public void getAllTheActiveChangeRequest() {
		//create varArray or send null?
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_ACTIVE_CHANGE_REQUESTS, null);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getAllTheEmployee() {
		//create varArray or send null?
				SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_EMPLOYEE, null);
				this.subscribeToClientDeliveries();		//subscribe to listener array
				ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch (result.getActionType()) {
			case SELECT_ALL_ACTIVE_CHANGE_REQUESTS:
				ArrayList<ChangeRequest> resultList = createChangeRequestFromResult(result);
				
				break;
			default:
				break;
			}

		});
		return;
	}

	private ArrayList<ChangeRequest> createChangeRequestFromResult(SqlResult result){
		ArrayList<ChangeRequest> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			ChangeRequest changeRequest=new ChangeRequest((Integer)a.get(0), (String)a.get(1), (Date)a.get(2), (String)a.get(3),
					(String)a.get(4), (String)a.get(5), (String)a.get(6),(String)a.get(7), (String)a.get(8),
					(String)a.get(9), (String)a.get(10), (Date)a.get(11));
			resultList.add(changeRequest);
		}
		return resultList;
	}

}
