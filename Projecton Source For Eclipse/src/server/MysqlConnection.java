package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import assets.*;
import assets.SqlQueryType.SqlExecutionType;

public class MysqlConnection {
	
	/* Initialize database constants */
	/*TODO: Update constants name to suit the project */
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/icm?serverTimezone=IST"; // URL requires Update
    private static final String USERNAME = "root";  // UserName requires update
    private static final String PASSWORD = "Aa123456";		// Password requires update
    private static String[] sqlArray;
    
    /* Private variables declaration */
    private Connection connection;

    /* Public methods */
    public Connection connect() 
    {
        if (connection == null)
        {
            try 
            {
                Class.forName(DATABASE_DRIVER).newInstance();
                System.out.println("Driver definition succeed");
            } 
            catch (Exception ex)
            {
            	System.out.println("Driver definition failed");
                ex.printStackTrace();
            }
            
            try 
            {
            	connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            	System.out.println("SQL connection succeed");
            } 
            catch (SQLException ex)
            {
            	/* handle any errors*/
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return connection;
    }
    
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public SqlResult getResult(SqlAction sqlAction)
    {
    	SqlResult sqlResult = null;
    	PreparedStatement ps = null;
    
    	this.connect();
    	try {
    		if (sqlAction.getActionType().getExecutionType() == SqlQueryType.SqlExecutionType.INSERT_GET_AUTO_INCREMENT_ID)
    		{
    			ps = connection.prepareStatement(sqlArray[sqlAction.getActionType().getCode()], Statement.RETURN_GENERATED_KEYS);
    		}
    		else
    		{
    			ps = connection.prepareStatement(sqlArray[sqlAction.getActionType().getCode()]);
    		}
			for (int i = 1; i<=sqlAction.getActionVars().size(); i++)
			{
				/* In Array List we start from 0 */
				Object obj = sqlAction.getActionVars().get(i-1); 
				if (obj instanceof Integer) {
					Integer num = (Integer) obj;
					ps.setInt(i, num);
				}
				if (obj instanceof Double) {
					Double dNum = (Double) obj;
					ps.setDouble(i, dNum);
				}
				if (obj instanceof Float) {
					Float fNum = (Float) obj;
					ps.setFloat(i, fNum);
				}
				if (obj instanceof String) {
					String string = (String) obj;
					ps.setString(i, string);
				}
				if (obj instanceof Date) {
					Date date = (Date) obj;
					ps.setDate(i, date);
				}
				if (obj instanceof Boolean) {
					Boolean bool = (Boolean) obj;
					ps.setBoolean(i, bool);
				}
			}
			
			switch(sqlAction.getActionType().getExecutionType())
			{
				case EXECUTE_QUERY:
					sqlResult = new SqlResult(ps.executeQuery(), sqlAction.getActionType());
					break;
				case UPDATE_QUERY:
					sqlResult = new SqlResult(ps.executeUpdate(), sqlAction.getActionType());
					break;
				case INSERT_GET_AUTO_INCREMENT_ID:
					if (ps.executeUpdate() != 0)
					{
						sqlResult = new SqlResult(ps.getGeneratedKeys(), sqlAction.getActionType());
					}
				default:
					break;
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		this.disconnect();
    	}
    	
    	return sqlResult;
    }
    
    public static void initSqlArray()  
    {
    	sqlArray = new String[SqlQueryType.MAX_SQL_QUERY.getCode()];
    	
    	/* *****************************************************
		 * *************** General Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS.getCode()]=
    			"SELECT * FROM icm.change_request";
    	
    	/* *****************************************************
		 * *************** Login Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.VERIFY_LOGIN.getCode()] = "SELECT * FROM icm.user "
    			+ "WHERE UserName = ? AND Password = ?";
    	sqlArray[SqlQueryType.UPDATE_USER_LOGIN_STATUS.getCode()] = "UPDATE icm.user "
    			+ "SET isLogged = ? WHERE UserID = ?";
 
    	/* *****************************************************
		 * *************** Work Station Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_NOT_COMMITTEE.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' AND HandlerUserName = ? "
    			+ "AND (CurrentStep = 'ANALYSIS_SET_TIME' OR CurrentStep = 'ANALYSIS_APPROVE_TIME' OR CurrentStep = 'ANALYSIS_WORK' " /*Analysis Step*/
    			+ "OR CurrentStep = 'EXECUTION_SET_TIME' OR CurrentStep = 'EXECUTION_APPROVE_TIME' OR CurrentStep = 'EXECUTION_WORK' " /*Execution Step*/
    			+ "OR CurrentStep = 'TESTING_WORK') "; /*Tester Step*/
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_MEMBER.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' "
    			+ "AND ((HandlerUserName = ? AND (CurrentStep ='ANALYSIS_SET_TIME' OR CurrentStep = 'ANALYSIS_APPROVE_TIME' OR CurrentStep = 'ANALYSIS_WORK' " /*Analysis Step*/
    			+ "OR CurrentStep = 'EXECUTION_SET_TIME' OR CurrentStep = 'EXECUTION_APPROVE_TIME' OR CurrentStep = 'EXECUTION_WORK' " /*Execution Step*/
    			+ "OR CurrentStep = 'TESTING_WORK')) " /*Tester Step*/
    			+ "OR CurrentStep = 'COMMITTEE_WORK')"; /* Committee Step */
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_HANDLER_NAME_COMMITTEE_DIRECTOR.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' "
    			+ "AND ((HandlerUserName = ? AND (CurrentStep ='ANALYSIS_SET_TIME' OR CurrentStep = 'ANALYSIS_APPROVE_TIME' OR CurrentStep = 'ANALYSIS_WORK' " /*Analysis Step*/
    			+ "OR CurrentStep = 'EXECUTION_SET_TIME' OR CurrentStep = 'EXECUTION_APPROVE_TIME' OR CurrentStep = 'EXECUTION_WORK' " /*Execution Step*/
    			+ "OR CurrentStep = 'TESTING_WORK')) " /*Tester Step*/
    			+ "OR CurrentStep = 'COMMITTEE_WORK' " /* Committee Step */
    			+ "OR CurrentStep = 'TESTER_APPOINT')"; /* Committee director */
    	sqlArray[SqlQueryType.SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' AND HandlerUserName = ? "
    			+ "AND (CurrentStep ='ANALYSIS_SET_TIME' OR CurrentStep = 'ANALYSIS_APPROVE_TIME' OR CurrentStep = 'ANALYSIS_WORK')";
    	sqlArray[SqlQueryType.SELECT_COMMITTEE_STEP_CHANGE_REQUESTS.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' AND CurrentStep ='COMMITTEE_WORK'";
    	sqlArray[SqlQueryType.SELECT_EXECUTION_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' AND HandlerUserName = ? "
    			+ "AND (CurrentStep = 'EXECUTION_SET_TIME' OR CurrentStep = 'EXECUTION_APPROVE_TIME' OR CurrentStep = 'EXECUTION_WORK')";
    	sqlArray[SqlQueryType.SELECT_TESTER_STEP_CHANGE_REQUESTS_BY_HANDLER_NAME.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' AND HandlerUserName = ? AND CurrentStep = 'TESTING_WORK'";
    	sqlArray[SqlQueryType.SELECT_TESTER_APPOINT_CHANGE_REQUESTS.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE Status = 'Active' AND CurrentStep = 'TESTER_APPOINT'";
    	
    	/* *****************************************************
		 * *************** Tester Queries **************
		 * *****************************************************/
    	
    	sqlArray[SqlQueryType.UPDATE_TESTER_STEP.getCode()] = "UPDATE icm.tester_step "
    			+ "SET TesterFailReport = ?, Status = ?,EndDate = ? WHERE ChangeRequestID = ? ORDER BY TesterStepId DESC LIMIT 1";
		sqlArray[SqlQueryType.SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID.getCode()] = 
				"SELECT * FROM icm.analysis_step WHERE ChangeRequestId = ?"
				+ " ORDER BY AnalysisStepID DESC LIMIT 1";
		sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER.getCode()]=
				"SELECT * FROM icm.change_request WHERE InitiatorUserName=?";
		sqlArray[SqlQueryType.SELECT_TESTER_STEP_START_DATE.getCode()] = 
				"SELECT EstimatedEndDate FROM icm.tester_step WHERE ChangeRequestId = ?"
				+ " ORDER BY TesterStepId DESC LIMIT 1";
		
		
		/* *****************************************************
		 * *************** Time Extension Queries **************
		 * *****************************************************/
		sqlArray[SqlQueryType.INSERT_NEW_TIME_EXTENSION.getCode()] =
				"INSERT INTO icm.time_extension(StepID,StepType,OldDate,NewDate,Reason) " + 
				"VALUES (?,?,?,?,?)";
		sqlArray[SqlQueryType.COUNT_TIME_EXTENSION_BY_STEP.getCode()]=
				"SELECT COUNT(*) FROM icm.time_extension "
				+ "WHERE StepID = ? AND StepType = ?";
		
		/* *****************************************************
		 * *************** Committee Queries **************
		 * *****************************************************/
		sqlArray[SqlQueryType.SELECT_COMMITTEE_STEP_START_DATE.getCode()] = 
				"SELECT EstimatedEndDate FROM icm.committee_step WHERE ChangeRequestId = ?"
				+ " ORDER BY CommitteeStepId DESC LIMIT 1";
		sqlArray[SqlQueryType.SELECT_COMMENTS_BY_REQUEST_ID.getCode()]="SELECT * FROM icm.committee_comment"
    			+ " WHERE requestId = ?";
    	sqlArray[SqlQueryType.INSERT_NEW_COMMITTEE_COMMENT.getCode()]=
    			"INSERT INTO icm.committee_comment(requestId,userName,comment)"
    			+ " VALUES (?,?,?)";
    	sqlArray[SqlQueryType.UPDATE_COMMITTEE_STEP.getCode()]=
    			"UPDATE icm.committee_step SET Status = ?,EndDate = ? WHERE ChangeRequestId = ?" + 
    			" ORDER BY CommitteeStepId DESC LIMIT 1";
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP.getCode()]=
    			"UPDATE icm.change_request SET CurrentStep = ?,HandlerUserName = ? WHERE ChangeRequestId = ?";
    	sqlArray[SqlQueryType.INSERT_NEW_CLOSING_STEP.getCode()]="INSERT INTO icm.closing_step(ChangeRequestId,StartDate,Status)"
    			+ " VALUES (?,?,?)";
    	sqlArray[SqlQueryType.SELECT_COMMITTEE_STEP_DETAILS.getCode()] = 
    			"SELECT * FROM icm.committee_step WHERE ChangeRequestId = ? "
    			+ "ORDER BY CommitteeStepId DESC LIMIT 1";
    	
    	/* *****************************************************
		 * *************** Tech Manager Queries **************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_ACTIVE_CHANGE_REQUESTS.getCode()]=
    			"SELECT * FROM icm.change_request WHERE Status = 'Active'";
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_DATE.getCode()]=
    			"SELECT * FROM icm.change_request WHERE StartDate BETWEEN ? AND ?";
    	sqlArray[SqlQueryType.SELECT_ALL_EMPLOYEE.getCode()] = 
    			"SELECT * FROM icm.user WHERE Permission = 'SUPERVISOR' OR Permission = 'INFORMATION_ENGINEER'" + 
    			" OR Permission = 'COMMITTEE_MEMBER' OR Permission = 'COMMITTEE_DIRECTOR'";
    	sqlArray[SqlQueryType.UPDATE_EMPLOYEE_PERMISSION.getCode()] = 
    			"UPDATE icm.user SET Permission = ?, JobDescription = ? WHERE UserID = ?";
    	/* Performance report */
    	sqlArray[SqlQueryType.SELECT_ALL_APPROVED_TIME_EXTNESIONS.getCode()]=
    			"SELECT * FROM icm.time_extension "
    			+ "WHERE Status = 'APPROVED'";
    	sqlArray[SqlQueryType.SELECT_ALL_REPEATRING_STEPS.getCode()] =
    			"(SELECT 'Analysis' AS 'StepType', A1.StartDate, A1.EndDate " + 
    			"FROM icm.analysis_step A1 " + 
    			"WHERE  EXISTS  " + 
    			"( SELECT A2.ChangeRequestID FROM icm.analysis_step A2 " + 
    			"WHERE A2.ChangeRequestID = A1.ChangeRequestId " + 
    			"GROUP BY A2.ChangeRequestID " + 
    			"HAVING COUNT(A2.changeRequestID) >= 2 )) " + 
    			"UNION " + 
    			"(SELECT 'Committee' AS 'StepType', C1.StartDate, C1.EndDate  " + 
    			"FROM icm.committee_step C1 " + 
    			"WHERE  EXISTS  " + 
    			"( SELECT C2.ChangeRequestID FROM icm.committee_step C2 " + 
    			"WHERE C2.ChangeRequestID = C1.ChangeRequestId " + 
    			"GROUP BY C2.ChangeRequestID " + 
    			"HAVING COUNT(C2.changeRequestID) >= 2 )) " + 
    			"UNION " + 
    			"(SELECT 'Execution' AS 'StepType', E1.StartDate, E1.EndDate  " + 
    			"FROM icm.execution_step E1 " + 
    			"WHERE  EXISTS  " + 
    			"( SELECT E2.ChangeRequestID FROM icm.execution_step E2 " + 
    			"WHERE E2.ChangeRequestID = E1.ChangeRequestId " + 
    			"GROUP BY E2.ChangeRequestID " + 
    			"HAVING COUNT(E2.changeRequestID) >= 2 )) " + 
    			"UNION " + 
    			"(SELECT 'Testing' AS 'StepType', T1.StartDate, T1.EndDate  " + 
    			"FROM icm.tester_step T1 " + 
    			"WHERE  EXISTS  " + 
    			"( SELECT T2.ChangeRequestID FROM icm.committee_step T2 " + 
    			"WHERE T2.ChangeRequestID = T1.ChangeRequestId " + 
    			"GROUP BY T2.ChangeRequestID " + 
    			"HAVING COUNT(T2.changeRequestID) >= 2 ))";
    	
    	/* *****************************************************
		 * *********** Upload Change Request Queries ***********
		 * *****************************************************/
    	sqlArray[SqlQueryType.INSERT_NEW_CHANGE_REQUEST.getCode()]= 
    			"INSERT INTO icm.change_request(InitiatorUserName,StartDate,"
    			+ "SelectedSubSystem,CurrentStateDescription,DesiredChangeDescription,DesiredChangeExplanation,DesiredChangeComments,"
    			+ "Status,CurrentStep,HandlerUserName) VALUES (?,?,?,?,?,?,?,?,?,?)";
    	sqlArray[SqlQueryType.SELECT_ALL_INFROMATION_ENGINEERS.getCode()]=
    			"SELECT UserName FROM icm.user "
    			+ "WHERE JobDescription = 'Information Engineer' OR JobDescription = 'Supervisor' "
    			+ "OR JobDescription = 'Committee member' OR JobDescription = 'Committee Director'";
    	sqlArray[SqlQueryType.INSERT_NEW_FILE.getCode()] =
    			"INSERT INTO icm.file(ChangeRequestID,FileEnding) "
    			+ "VALUES (?,?)";
    	sqlArray[SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID.getCode()]=
    			"SELECT FileID, FileEnding FROM icm.file "
    			+ "WHERE ChangeRequestID = ?";
    
    	/* *****************************************************
		 * ************* Appoint Tester Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_COMMITTEE_MEMBERS.getCode()] =
    			"SELECT UserName FROM icm.user "
    			+ "WHERE Permission = 'COMMITTEE_MEMBER' OR Permission = 'COMMITTEE_DIRECTOR'";
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AND_HANDLER.getCode()] =
    			"UPDATE icm.change_request "
    			+ "SET CurrentStep = ?, HandlerUserName = ? "
    			+ "WHERE ChangeRequestID = ?";
    	sqlArray[SqlQueryType.INSERT_NEW_TESTER_STEP.getCode()] =
    			"INSERT INTO icm.tester_step(ChangeRequestId,HandlerUserName,Status,StartDate,EstimatedEndDate) "
    	    	+ "VALUES (?,?,'Active',?,?)";
    	
    	/* ******************************************************
    	 * *************Execution Leader Queries****************
    	 *******************************************************
    	 *******************************************************/
    	sqlArray[SqlQueryType.UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP.getCode()]=
    			"UPDATE icm.execution_step SET Status = ?,EndDate = ? WHERE ChangeRequestID = ?" + 
    			" ORDER BY ExecutionStepID DESC LIMIT 1";
    	
    	
    	sqlArray[SqlQueryType.SELECT_IF_CURRENT_STEP_CHANGED_TO_EXECUTION_WORK.getCode()] = 
				"SELECT CurrentStep FROM icm.change_request WHERE ChangeRequestID = ?";
    	
    	
    	sqlArray[SqlQueryType.SELECT_ESTIMATED_DATE_MINUS_START_DATE.getCode()] = 
				"SELECT EstimatedEndDate FROM icm.execution_step WHERE ChangeRequestID = ?";
    	
     	sqlArray[SqlQueryType.INSERT_NEW_EXECUTION_ESTIMATED_TIME.getCode()]=                   // update estimated end time in execution step
    			"UPDATE icm.execution_step SET EstimatedEndDate = ? WHERE ChangeRequestID = ?";
     	
     	sqlArray[SqlQueryType.UPDATE_NEW_EXECUTION_APPROVE_TIME_STATUS.getCode()]=             // update change request status
    			"UPDATE icm.change_request SET CurrentStep = ? WHERE ChangeRequestID = ?";
     	
     	sqlArray[SqlQueryType.UPDATE_CURRENT_STEP_TO_TESTER.getCode()]=
    			"UPDATE icm.change_request SET CurrentStep = ?,HandlerUserName = ? WHERE ChangeRequestID = ?";
     	
     	sqlArray[SqlQueryType.SELECT_EXECUTIOM_STEP_DETAILS.getCode()] = 
				"SELECT * FROM icm.execution_step WHERE ChangeRequestID = ?"
     			+ " ORDER BY ExecutionStepID DESC LIMIT 1";
    	
    }
    
}




