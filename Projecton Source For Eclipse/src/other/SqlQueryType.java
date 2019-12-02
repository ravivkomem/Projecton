package other;

public enum SqlQueryType {
	
	FirstSqlQuery,	/* == -1 */
	
	Login,
	GetAllChangeRequests,
	GetChangeRequestById,
	
	MaxSqlQuery; /* == number of queries */
	
	public int getCode() {
        return ordinal()-1;
	}
	
	public static void main(String args[])
	{		
		String[] sqlArray = new String[MaxSqlQuery.getCode()];
		sqlArray[GetAllChangeRequests.getCode()] = "Hello";
		sqlArray[GetChangeRequestById.getCode()] = "SELECT * FROM requirement WHERE userName =?";
		sqlArray[Login.getCode()] = "Empty";
		
		for (String sql : sqlArray) 
		{
			System.out.println(sql);
		}
	}
	
}


