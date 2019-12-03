package boundries;

import temp.ClientConsole;

import java.util.ArrayList;

import other.SqlAction;
import other.SqlQueryType;

public class DemoLandingController {

	public static void getChangeRequestById (String changeRequestId)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CHANGE_REQUEST_BY_ID, varArray);
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
}