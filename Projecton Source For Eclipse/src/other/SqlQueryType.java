package other;

public enum SqlQueryType {
	
	FIRST_SQL_QUERY,	/* == -1 */
	
	LOGIN,
	GET_ALL_CHANGE_REQUESTS,
	GET_CHANGE_REQUEST_BY_ID,
	MA_KORE,
	
	MAX_SQL_QUERY; /* == number of queries */
	
	public int getCode() {
        return ordinal()-1;
	}
	
	public static void main(String args[])
	{		
		String[] sqlArray = new String[MAX_SQL_QUERY.getCode()];
		sqlArray[GET_ALL_CHANGE_REQUESTS.getCode()] = "SELECT * FROM requirement";
		sqlArray[GET_CHANGE_REQUEST_BY_ID.getCode()] = "SELECT * FROM requirement WHERE userName = ?";
		sqlArray[LOGIN.getCode()] = "SELECT * FROM requirement WHERE userName = ? AND password = ?";
		sqlArray[MA_KORE.getCode()]="SELECT ***";
		
		for (String sql : sqlArray) 
		{
			System.out.println(sql);
		}
	}
	
}


