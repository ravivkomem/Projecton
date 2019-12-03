package other;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlResult { 
	
	private ArrayList<ArrayList<Object>> resultData;
	private SqlQueryType actionType;

	public SqlResult (ResultSet rs, SqlQueryType actionType)
	{
		this.actionType = actionType;
		
		resultData = new ArrayList<ArrayList<Object>>();
		ResultSetMetaData rsmd;
		try {
			rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()) {
				ArrayList<Object> objectsRow = new ArrayList<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					objectsRow.add(rs.getObject(i));
				}
				resultData.add(objectsRow);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<Object>> getResultData()
	{
		return resultData;
	}
	
	public SqlQueryType getActionType()
	{
		return actionType;
	}
}
