package assets;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to send sql queries to the database
 * @author Raviv Komem
 * @parm sqlQueryType - Which query we want to execute
 * @parm actionVars - the variables that are needed for the query
 */
@SuppressWarnings("serial")
public class SqlAction implements Serializable
{
	private SqlQueryType actionType;
	private ArrayList<Object> actionVars;
	
	public SqlAction(SqlQueryType actionType, ArrayList<Object> actionVars) {
		this.actionType = actionType;
		this.actionVars = actionVars;
	}
	
	public SqlAction(SqlQueryType actionType)
	{
		this.actionType = actionType;
		actionVars = new ArrayList<Object>();
	}
	
	public SqlQueryType getActionType() {
		return actionType;
	}
	public ArrayList<Object> getActionVars() {
		return actionVars;
	}

	
}
