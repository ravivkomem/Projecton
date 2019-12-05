package other;

public enum SqlQueryType {
	
	FIRST_SQL_QUERY,	/* == -1 */
	
	LOGIN,
	GET_ALL_CHANGE_REQUESTS,
	GET_CHANGE_REQUEST_BY_ID,
	UPDATE_CHANGE_REQUEST_BY_ID,
	
	MAX_SQL_QUERY; /* == number of queries */
	
	public int getCode() {
        return ordinal()-1;
	}
}


