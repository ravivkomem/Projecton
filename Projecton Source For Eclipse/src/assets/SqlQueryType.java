package assets;

/** 
 * 
 * @author Raviv Komem
 * This enum will represent all the queries in our project
 */
public enum SqlQueryType {

	/* *****************************************
	 * ********** Login Queries ****************
	 * *****************************************/
	VERIFY_LOGIN(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_USER_LOGIN_STATUS(SqlExecutionType.UPDATE_QUERY),
	
	SELECT_ALL_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********** Committee Queries ****************
	 * *****************************************/
	SELECT_COMMENTS_BY_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_COMMITTEE_COMMENT(SqlExecutionType.UPDATE_QUERY),
	UPDATE_COMMITTEE_STEP(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_CURRENT_STEP(SqlExecutionType.UPDATE_QUERY),
	INSERT_NEW_CLOSING_STEP(SqlExecutionType.UPDATE_QUERY),
	SELECT_COMMITTEE_STEP_START_DATE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_TESTER_STEP_START_DATE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_COMMITTEE_STEP_DETAILS(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********Tech Manager Queries*************
	 * *****************************************/
	SELECT_ALL_ACTIVE_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_EMPLOYEE(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_EMPLOYEE_PERMISSION(SqlExecutionType.UPDATE_QUERY),
	// Performance report
	SELECT_ALL_APPROVED_TIME_EXTNESIONS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_REPEATRING_STEPS(SqlExecutionType.EXECUTE_QUERY),
	//Activity report
	SELECT_ALL_CHANGE_REQUESTS_BY_DATE(SqlExecutionType.EXECUTE_QUERY),
	
	UPDATE_TESTER_STEP(SqlExecutionType.UPDATE_QUERY),
	SELECT_ALL_INFROMATION_ENGINEERS(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********Work Station Queries*************
	 * *****************************************/
	SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_NOT_COMMITTEE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_MEMBER(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_DIRECTOR(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME(SqlExecutionType.EXECUTE_QUERY),
	SELECT_COMMITTEE_STEP_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_EXECUTION_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME(SqlExecutionType.EXECUTE_QUERY),
	SELECT_TESTER_APPOINT_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_TESTER_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_EXECUTION_ESTIMATED_TIME(SqlExecutionType.UPDATE_QUERY),
	UPDATE_NEW_EXECUTION_APPROVE_TIME_STATUS(SqlExecutionType.UPDATE_QUERY),
	
	UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP(SqlExecutionType.UPDATE_QUERY),
	/*Analysis Report Queries */
	SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ***** Upload Change Request Queries *****
	 * *****************************************/
	INSERT_NEW_FILE(SqlExecutionType.INSERT_GET_AUTO_INCREMENT_ID),
	INSERT_NEW_CHANGE_REQUEST(SqlExecutionType.INSERT_GET_AUTO_INCREMENT_ID),
	UPDATE_CURRENT_STEP_TO_TESTER(SqlExecutionType.UPDATE_QUERY),
	DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	
	/*View Request List Queries*/
	SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********Time Extension Queries***********
	 * *****************************************/
	INSERT_NEW_TIME_EXTENSION(SqlExecutionType.UPDATE_QUERY),
	COUNT_TIME_EXTENSION_BY_STEP(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********Appoint Tester Queries***********
	 * *****************************************/
	SELECT_ALL_COMMITTEE_MEMBERS(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_CHANGE_REQUEST_STEP_AND_HANDLER(SqlExecutionType.UPDATE_QUERY),
	INSERT_NEW_TESTER_STEP(SqlExecutionType.UPDATE_QUERY),
	
	
	SELECT_IF_CURRENT_STEP_CHANGED_TO_EXECUTION_WORK(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ESTIMATED_DATE_MINUS_START_DATE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_EXECUTIOM_STEP_DETAILS(SqlExecutionType.EXECUTE_QUERY),
   
	
	
	
	
	
   /********************************************
    * ******************************************
    * *********Supervisor Queries***************
    ********************************************/
	
	
	
	SELECT_ALL_CHANGE_REQUEST(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE(SqlExecutionType.EXECUTE_QUERY),
	
	
	
	
	/* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
	MAX_SQL_QUERY(SqlExecutionType.NOT_QUERY);  /* Number of queries */

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
		NOT_QUERY, EXECUTE_QUERY, UPDATE_QUERY, INSERT_GET_AUTO_INCREMENT_ID;
	}

}

