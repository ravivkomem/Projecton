package other;

public enum SqlQueryType {
	
	FIRST_SQL_QUERY,	/* == -1 */
	
	VERIFY_LOGIN,
	GET_ALL_CHANGE_REQUESTS,
	GET_CHANGE_REQUEST_BY_ID,
	MA_KORE,
	
	MAX_SQL_QUERY; /* == number of queries */
	
	public int getCode() {
        return ordinal()-1;
	}
}


