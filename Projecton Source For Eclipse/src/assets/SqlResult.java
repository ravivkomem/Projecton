package assets;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used to hold the result set from the server
 * Used inorder to remove dependices on sql server connection
 * @author Raviv Komem
 *
 */
@SuppressWarnings("serial")
public class SqlResult implements Serializable{ 

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	private ArrayList<ArrayList<Object>> resultData;
	private SqlQueryType actionType;

	/**
	 * Constructor using result set
	 * @param rs - ResultSet object
	 * @param actionType - Which action this result represents
	 */
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
	
	/**
	 * Constructor using num
	 * @param num - For update queries we will receive the number of rows affected
	 * @param actionType - Result for which query
	 */
	public SqlResult (int num, SqlQueryType actionType)
	{
		this.actionType = actionType;
		
		resultData = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> resultRow = new ArrayList<Object>();
		resultRow.add(num);
		resultData.add(resultRow);
	}
	
	public SqlResult(ArrayList<Object> list, SqlQueryType actionType)
	{
		this.actionType = actionType;
		resultData = new ArrayList<ArrayList<Object>>();
		
		if (!list.isEmpty())
		{
			for (Object obj : list)
			{
				ArrayList<Object> resultRow = new ArrayList<Object>();
				resultRow.add(obj);
				resultData.add(resultRow);
			}
			
		}
		//resultData.add(list);
	}

	public ArrayList<ArrayList<Object>> getResultData()
	{
		return resultData;
	}
	
	public SqlQueryType getActionType()
	{
		return actionType;
	}
	
	public void setResultData (ArrayList<Object> dataSet)
	{
		resultData.clear();
		resultData.add(dataSet);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SqlResult other = (SqlResult) obj;
		if (actionType != other.actionType)
			return false;
		if (resultData == null) {
			if (other.resultData != null)
				return false;
		} else if (!resultData.equals(other.resultData))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SqlResult [resultData=" + resultData + ", actionType=" + actionType + "]";
	}
	
	
}
