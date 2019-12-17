package assets;

/** 
 * 
 * @author Raviv Komem
 * This enum will represent all the queries in our project
 */
public enum SqlQueryType {

	VERIFY_LOGIN(SqlExecutionType.EXECUTE_QUERY),
	GET_USER_CONNECTION_STATUS(SqlExecutionType.EXECUTE_QUERY),
	LOG_USER(SqlExecutionType.UPDATE_QUERY),
	DISCONNENT_USER(SqlExecutionType.UPDATE_QUERY),
	SELECT_ALL_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_CHANGE_REQUEST_BY_ID(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_CHANGE_REQUEST_BY_ID(SqlExecutionType.UPDATE_QUERY),
	SELECT_COMMENTS_BY_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_COMMITTEE_COMMENT(SqlExecutionType.UPDATE_QUERY),
	SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_CHANGE_REQUEST(SqlExecutionType.UPDATE_QUERY),
	SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME(SqlExecutionType.EXECUTE_QUERY),
	/* Number of queries */
	MAX_SQL_QUERY(SqlExecutionType.NOT_QUERY); 

	private SqlExecutionType executionType;

	private SqlQueryType(SqlExecutionType executionType) {
		this.executionType = executionType;
	}

	public SqlExecutionType getExecutionType() {
		return executionType;
	}

	public int getCode() {
		return ordinal();
	}

	/**
	 * 
	 * @author Raviv Komem 
	 * This enum will represent which execution method need to
	 * be used EXECUTE QUERY - will represent PreparedStatement ".executeQuery" method 
	 * Applicable for select queries 
	 * UPDATE QUERY - will represent PreparedStatement ".updateQuery" method 
	 * Applicable for delete, update and insert queries
	 */
	public enum SqlExecutionType {
		NOT_QUERY, EXECUTE_QUERY, UPDATE_QUERY;
	}

}
