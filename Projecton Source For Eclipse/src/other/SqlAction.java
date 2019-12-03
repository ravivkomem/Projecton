package other;

import java.util.ArrayList;

public class SqlAction
{
	private SqlQueryType actionType;
	private ArrayList<Object> actionVars;
	
	public SqlAction(SqlQueryType actionType, ArrayList<Object> actionVars) {
		this.actionType = actionType;
		this.actionVars = actionVars;
	}
	
	public SqlQueryType getActionType() {
		return actionType;
	}
	public ArrayList<Object> getActionVars() {
		return actionVars;
	}

	
}
