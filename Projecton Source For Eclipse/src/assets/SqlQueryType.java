package assets;

/** 
 * 
 * @author Raviv Komem
 * This enum will represent all the queries in our project
 */
public enum SqlQueryType {

	/* *****************************************
	 * ********** Common Queries ****************
	 * *****************************************/
	UPDATE_CHANGE_REQUEST_CURRENT_STEP(SqlExecutionType.UPDATE_QUERY),
	AUTOMATIC_CLOSE_NEW_TIME_EXTENSION(SqlExecutionType.UPDATE_QUERY),
	INSERT_NEW_CLOSING_STEP(SqlExecutionType.UPDATE_QUERY),
	SELECT_CHANGE_REQUEST_BY_ID(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********** Login Queries ****************
	 * *****************************************/
	VERIFY_LOGIN(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_USER_LOGIN_STATUS(SqlExecutionType.UPDATE_QUERY),
	
	/* *****************************************
	 * ********** Analyzer Queries ****************
	 * *****************************************/
	SELECT_ANALYSIS_STEP_BY_CHANGE_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_ANALYSIS_STEP_CLOSE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_ANALYSIS_STEP_ESTIMATED_DATE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_CURRENTSTEP(SqlExecutionType.UPDATE_QUERY),
	GET_COMMITTEE_DIRECTOR(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_COMMITTEE_STEP_FROM_ANALYZER(SqlExecutionType.UPDATE_QUERY),
	
	/* *****************************************
	 * ********** Committee Queries ****************
	 * *****************************************/
	SELECT_COMMENTS_BY_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_COMMITTEE_COMMENT(SqlExecutionType.UPDATE_QUERY),
	UPDATE_COMMITTEE_STEP(SqlExecutionType.UPDATE_QUERY),
	SELECT_COMMITTEE_STEP_START_DATE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_COMMITTEE_STEP_DETAILS(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********** Tester Queries ****************
	 * *****************************************/
	UPDATE_TESTER_STEP(SqlExecutionType.UPDATE_QUERY),
	SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ********Tech Manager Queries*************
	 * *****************************************/
	SELECT_ALL_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_EMPLOYEE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_SUBSYSTEM_BY_USER_NAME(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_EMPLOYEE_PERMISSION(SqlExecutionType.UPDATE_QUERY),
	UPDATE_SUBSYSTEM_SUPPORTER(SqlExecutionType.UPDATE_QUERY),
	// Performance report
	SELECT_ALL_APPROVED_TIME_EXTNESIONS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_REPEATRING_STEPS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_DEVIATION_CHANGE_REQUEST(SqlExecutionType.EXECUTE_QUERY),
	// Activity report
	SELECT_ALL_CHANGE_REQUESTS_BY_DATE(SqlExecutionType.EXECUTE_QUERY),
	// Delay Report
	SELECT_DATES_FROM_ALL_STEPS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_DATES_FROM_CLOSED_CHANGE_REQUEST(SqlExecutionType.EXECUTE_QUERY),
	
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
	
	/* *****************************************
	 * ********Analysis Report Queries**********
	 * *****************************************/
	SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ***** Upload Change Request Queries *****
	 * *****************************************/
	INSERT_NEW_FILE(SqlExecutionType.INSERT_GET_AUTO_INCREMENT_ID),
	INSERT_NEW_CHANGE_REQUEST(SqlExecutionType.INSERT_GET_AUTO_INCREMENT_ID),
	DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID(SqlExecutionType.EXECUTE_QUERY),
	SELECT_HANDLER_USER_NAME_BY_SYSTEM(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************
	 * ***** Request List Queries *****
	 * *****************************************/
	SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER_WITH_DATE_FILTER(SqlExecutionType.EXECUTE_QUERY),
	SELECET_SPECIFIC_CHANGE_REQUEST_FOR_USER_WITH_ID_FILTER(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER_WITH_STATUS_FILTER(SqlExecutionType.EXECUTE_QUERY),
	
	/* *****************************************************
	 * *************** Extra Details Queries ***************
	 * *****************************************************/
	UPDATE_STATUS_BY_SUPERVISOR(SqlExecutionType.UPDATE_QUERY),
	
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
	
	/* *****************************************
	 * ******** Execution Queries **************
	 * *****************************************/
	SELECT_EXECUTIOM_STEP_DETAILS(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID(SqlExecutionType.UPDATE_QUERY),
	CLOSE_EXECUTION_STEP(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CURRENT_STEP_TO_TESTER(SqlExecutionType.UPDATE_QUERY),
	
   /* ******************************************
    * *********Supervisor Queries***************
    * ******************************************/
	SELECT_USER_EMAIL(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_CURRENT_STEP_TO_ANALYZER_SUPERVISOR_APPOINT(SqlExecutionType.UPDATE_QUERY),
	UPDATE_ANALYZER_BY_SUPERVISOR(SqlExecutionType.UPDATE_QUERY),
	INSERT_NEW_ANALYSIS_STEP(SqlExecutionType.UPDATE_QUERY),
	UPDATE_STEP_TO_ANALYSIS_SET_TIME(SqlExecutionType.UPDATE_QUERY),
	INSERT_NEW_ANALYSIS_STEP_AFTER_APPROVE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_NEW_EXECUTION_LEADER(SqlExecutionType.UPDATE_QUERY),
	INSERT_NEW_EXECUTION_STEP(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_ANALYSIS_SET_TIME(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_ANALYSIS_SET_TIME(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_EXECUTION_SET_TIME(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_EXECUTION_SET_TIME(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STATUS_TO_SUSPENDED(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STATUS_TO_ACTIVE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_CHANGE_REQUEST_STATUS_TO_CLOSED(SqlExecutionType.UPDATE_QUERY),
	SELECT_EXECUTION_ESTIMATED_DATE(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ANALYSIS_ESTIMATED_DATE(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_END_DATE_IN_CLOSING_STEP(SqlExecutionType.UPDATE_QUERY),
	SELECT_ALL_ENGINEERS(SqlExecutionType.EXECUTE_QUERY),
	SELECT_ALL_TIME_EXTENSIONS(SqlExecutionType.EXECUTE_QUERY),
	UPDATE_TIME_EXTENSION_STATUS_TO_APPROVED(SqlExecutionType.UPDATE_QUERY),
	UPDATE_ANALYSIS_STEP_ESTIMATED_END_DATE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_COMMITTEE_STEP_ESTIMATED_END_DATE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_TESTER_STEP_ESTIMATED_END_DATE(SqlExecutionType.UPDATE_QUERY),
	UPDATE_TIME_EXTENSION_STATUS_TO_DENY(SqlExecutionType.UPDATE_QUERY),
	SELECT_ALL_SUSPENDED_CHANGE_REQUESTS(SqlExecutionType.EXECUTE_QUERY),
	INSERT_NEW_SUPERVISOR_UPDATE(SqlExecutionType.UPDATE_QUERY),
	
	/* *****************************************
	 * ********Time Watcher Queries*************
	 * *****************************************/
	GET_ALL_STEPS_ONE_DAY_REMAINING(SqlExecutionType.EXECUTE_QUERY),
	GET_ALL_STEPS_TIME_PASSED_TODAY(SqlExecutionType.EXECUTE_QUERY),
	GET_HIGH_MANGEMENT_MAILS(SqlExecutionType.EXECUTE_QUERY),
	GET_USER_EMAIL(SqlExecutionType.EXECUTE_QUERY),
	GET_USER_FULL_NAME(SqlExecutionType.EXECUTE_QUERY),
	
	/* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
	/* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
	/* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
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

