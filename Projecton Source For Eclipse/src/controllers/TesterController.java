package controllers;

import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.TesterBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.CommitteeComment;
import javafx.application.Platform;

public class TesterController extends BasicController {
private TesterBoundary MyBoundary;

	

	public TesterController(TesterBoundary myBoundary) {
		
		this.MyBoundary = myBoundary;
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
		switch (result.getActionType()) {
		
		case UPDATE_TESTER_STEP:
			int affectedRows;
			affectedRows = (Integer) (result.getResultData().get(0).get(0));
			this.unsubscribeFromClientDeliveries();
			MyBoundary.updateTesterPageToDBSuccessfully(affectedRows);
			
			break;

		default:
			break;
		}
		});
		return;
	}
	public void updateChangeRequestStep(ChangeRequest changerequest, String failReport) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(failReport);
		varArray.add(changerequest.getChangeRequestID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_TESTER_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	

	

}
