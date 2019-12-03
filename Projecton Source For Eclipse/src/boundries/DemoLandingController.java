package boundries;

import temp.ClientConsole;

import java.util.ArrayList;

import client.ChatClient;
import javafx.application.Platform;
import other.ServerEvent;
import other.SqlAction;
import other.SqlQueryType;
import other.SqlResult;

public class DemoLandingController extends ServerEvent {

	private DemoLandingBoundries myBoundry;
	
	public DemoLandingController(DemoLandingBoundries myBoundry) 
	{
		this.myBoundry = myBoundry;
	}
	
	public void getChangeRequestById (String changeRequestId)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CHANGE_REQUEST_BY_ID, varArray);
		ChatClient.changeRequestByIdListeners.add(this);
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getChangeRequestByIdResultDelivery(SqlResult results) {
		Platform.runLater(() -> {
			myBoundry.displayChangeRequestTable(results);
		});
	}
}