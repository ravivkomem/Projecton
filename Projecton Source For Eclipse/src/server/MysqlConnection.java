package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import assets.*;

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
    
    public void disconnectAllLoggedUsers()
    {
    	this.connect();
    	try {
			Statement statement = connection.createStatement();
			statement.execute("UPDATE icm.user SET IsLogged = '0'");
		} 
    	catch (SQLException e) 
    	{
    		
		}
    	finally
    	{
    		this.disconnect();
    	}

    }
    
    public static void initSqlArray()  
    {
    	sqlArray = new String[SqlQueryType.MAX_SQL_QUERY.getCode()];
    	
    	/* *****************************************************
		 * *************** Common Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP.getCode()]=
    			"UPDATE icm.change_request SET CurrentStep = ?,HandlerUserName = ? WHERE ChangeRequestId = ?";
    	sqlArray[SqlQueryType.AUTOMATIC_CLOSE_NEW_TIME_EXTENSION.getCode()]=
    			"UPDATE icm.time_extension SET Status = 'CLOSED' "
    			+ "WHERE StepID = ? AND StepType = ? AND Status = 'NEW'";
    	sqlArray[SqlQueryType.INSERT_NEW_CLOSING_STEP.getCode()]=
    			"INSERT INTO icm.closing_step(ChangeRequestId,StartDate,Status)"
    			+ " VALUES (?,?,?)";
    	
    	/* *****************************************************
		 * *************** Login Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.VERIFY_LOGIN.getCode()] = "SELECT * FROM icm.user "
    			+ "WHERE UserName = ? AND Password = ?";
    	sqlArray[SqlQueryType.UPDATE_USER_LOGIN_STATUS.getCode()] = "UPDATE icm.user "
    			+ "SET isLogged = ? WHERE UserID = ?";
    	
    	/* *****************************************************
		 * ***************Analyzer Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ANALYSIS_STEP_BY_CHANGE_REQUEST_ID.getCode()] =
    			"SELECT AnalysisStepID, ChangeRequestID, HandlerUserName, StartDate, Status, EstimatedEndDate, EndDate"
				+ " FROM icm.analysis_step WHERE ChangeRequestID = ?"
		     	+ " ORDER BY AnalysisStepID DESC LIMIT 1";
    	sqlArray[SqlQueryType.UPDATE_ANALYSIS_STEP_ESTIMATED_DATE.getCode()]=
                "UPDATE icm.analysis_step SET EstimatedEndDate = ? WHERE AnalysisStepID = ?";
        sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENTSTEP_HANDLERNAME.getCode()]=
                "UPDATE icm.change_request SET CurrentStep = ?,HandlerUserName = ?,EstimatedDate=? WHERE ChangeRequestID = ?";
        
        sqlArray[SqlQueryType.UPDATE_ANALYSIS_STEP_CLOSE.getCode()]=
                "UPDATE icm.analysis_step SET EndDate = ?,Status = ?,AnalysisReportHeader = ?,AnalysisReportDescription = ?,AnalysisReportAdvantages = ?,AnalysisReportDuration = ?,AnalysisReportConstraints = ? WHERE ChangeRequestID = ?";
        
        sqlArray[SqlQueryType.GET_COMMITTEE_DIRECTOR.getCode()]=
        		"SELECT UserName FROM icm.user WHERE Permission = 'COMMITTEE_DIRECTOR' OR Permission = 'SUPERVISOR_COMMITTEE_DIRECTOR'";
        sqlArray[SqlQueryType.INSERT_NEW_COMMITTEE_STEP_FROM_ANALYZER.getCode()]=
       " INSERT INTO icm.committee_step(ChangeRequestId,HandlerUserName,StartDate,Status,EstimatedEndDate) VALUES (?,?,?,?,?)";
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
    			+ "OR CurrentStep = 'TESTER_COMMITTEE_DIRECTOR_APPOINT')"; /* Committee director */
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
    			+ "WHERE Status = 'Active' AND CurrentStep = 'TESTER_COMMITTEE_DIRECTOR_APPOINT'";//changed from TESTER_APPOINT to TESTER_COMMITTEE_DIRECTOR_APPOINT
    	
    	/* *****************************************************
		 * *************** Tester Queries **************
		 * *****************************************************/
    	sqlArray[SqlQueryType.UPDATE_TESTER_STEP.getCode()] = "UPDATE icm.tester_step "
    			+ "SET TesterFailReport = ?, Status = ?,EndDate = ? WHERE TesterStepId = ?";
		sqlArray[SqlQueryType.SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID.getCode()] = 
				"SELECT * FROM icm.analysis_step WHERE ChangeRequestId = ?"
				+ " ORDER BY AnalysisStepID DESC LIMIT 1";
		sqlArray[SqlQueryType.SELECT_TESTER_STEP_BY_CHANGE_REQUEST_ID.getCode()] = 
				"SELECT TesterStepID, ChangeRequestID, HandlerUserName, StartDate, Status, EstimatedEndDate, EndDate"
				+ " FROM icm.tester_step WHERE ChangeRequestID = ?"
		     	+ " ORDER BY TesterStepID DESC LIMIT 1";
	
		
		
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
    			+ " WHERE requestId = ? AND committeeStepId = ?";
    	sqlArray[SqlQueryType.INSERT_NEW_COMMITTEE_COMMENT.getCode()]=
    			"INSERT INTO icm.committee_comment(requestId,userName,comment,committeeStepId)"
    			+ " VALUES (?,?,?,?)";
    	sqlArray[SqlQueryType.UPDATE_COMMITTEE_STEP.getCode()]=
    			"UPDATE icm.committee_step SET Status = ?,EndDate = ?,DenyComment = ? WHERE ChangeRequestId = ?" + 
    			" ORDER BY CommitteeStepId DESC LIMIT 1";
    	sqlArray[SqlQueryType.SELECT_COMMITTEE_STEP_DETAILS.getCode()] = 
    			"SELECT * FROM icm.committee_step WHERE ChangeRequestId = ? "
    			+ "ORDER BY CommitteeStepId DESC LIMIT 1";
    	
    	/* *****************************************************
		 * *************** Tech Manager Queries **************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS.getCode()]=
    			"SELECT * FROM icm.change_request";
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_DATE.getCode()]=
    			"SELECT * FROM icm.change_request WHERE StartDate BETWEEN ? AND ?";
    	sqlArray[SqlQueryType.SELECT_ALL_EMPLOYEE.getCode()] = 
    			"SELECT * FROM icm.user WHERE Permission = 'SUPERVISOR' OR Permission = 'INFORMATION_ENGINEER'" + 
    			" OR Permission = 'COMMITTEE_MEMBER' OR Permission = 'COMMITTEE_DIRECTOR' " +
    			"OR Permission = 'SUPERVISOR_COMMITTEE_MEMBER' OR Permission = 'SUPERVISOR_COMMITTEE_DIRECTOR' " +
    			"OR Permission = 'INFORMATION_ENGINEERING_DEPARTMENT_HEAD'";
    	sqlArray[SqlQueryType.SELECT_SUBSYSTEM_BY_USER_NAME.getCode()] =
    			"SELECT *  FROM icm.subsystem_support WHERE ResponsibleUserName = ?";
    	sqlArray[SqlQueryType.UPDATE_EMPLOYEE_PERMISSION.getCode()] = 
    			"UPDATE icm.user SET Permission = ?, JobDescription = ? WHERE UserID = ?";
    	sqlArray[SqlQueryType.UPDATE_SUBSYSTEM_SUPPORTER.getCode()] =
    			"UPDATE icm.subsystem_support SET ResponsibleUserName = ? WHERE Subsystem = ?";
    	/* Performance report */
    	sqlArray[SqlQueryType.SELECT_ALL_APPROVED_TIME_EXTNESIONS.getCode()]=
    			"SELECT * FROM icm.time_extension "
    			+ "WHERE Status = 'APPROVED'";
    	sqlArray[SqlQueryType.SELECT_ALL_REPEATRING_STEPS.getCode()] =
    			"SELECT * FROM icm.repeating_committee RC " + 
    			"WHERE RC.CommitteeStepID != (SELECT MIN(RC2.CommitteeStepID) FROM ICM.repeating_committee RC2 " + 
    			"WHERE RC2.ChangeRequestID = RC.ChangeRequestID) " + 
    			"UNION " + 
    			"SELECT * FROM icm.repeating_analysis RA " + 
    			"WHERE RA.AnalysisStepID != (SELECT MIN(RA2.AnalysisStepID) FROM ICM.repeating_analysis RA2 " + 
    			"WHERE RA2.ChangeRequestID = RA.ChangeRequestID) " + 
    			"UNION " + 
    			"SELECT * FROM icm.repeating_execution RE " + 
    			"WHERE RE.ExecutionStepID != (SELECT MIN(RE2.ExecutionStepID) FROM ICM.repeating_execution RE2 " + 
    			"WHERE RE2.ChangeRequestID = RE.ChangeRequestID) " + 
    			"UNION " + 
    			"SELECT * FROM icm.repeating_tester RT " + 
    			"WHERE RT.TesterStepID != (SELECT MIN(RT2.TesterStepID) FROM ICM.repeating_tester RT2 " + 
    			"WHERE RT2.ChangeRequestID = RT.ChangeRequestID)";
    	sqlArray[SqlQueryType.SELECT_ALL_DEVIATION_CHANGE_REQUEST.getCode()]=
    			"SELECT * FROM icm.change_request "
    			+ "WHERE CurrentStep = 'FINISH' AND EndDate>EstimatedDate";
    	sqlArray[SqlQueryType.SELECT_DATES_FROM_ALL_STEPS.getCode()]= 
    			"SELECT icm.change_request.SelectedSubsystem , icm.committee_step.EstimatedEndDate ," + 
    			"icm.committee_step.EndDate " + 
    			"FROM icm.committee_step " + 
    			"INNER JOIN icm.change_request " + 
    			"ON icm.committee_step.ChangeRequestID = icm.change_request.ChangeRequestID " + 
    			"WHERE icm.committee_step.Status = 'CLOSED' " + 
    			"UNION " + 
    			"SELECT icm.change_request.SelectedSubsystem , icm.analysis_step.EstimatedEndDate ," + 
    			"icm.analysis_step.EndDate " + 
    			"FROM icm.analysis_step " + 
    			"INNER JOIN icm.change_request " + 
    			"ON icm.analysis_step.ChangeRequestID = icm.change_request.ChangeRequestID " + 
    			"WHERE icm.analysis_step.Status = 'CLOSED' " + 
    			"UNION " + 
    			"SELECT icm.change_request.SelectedSubsystem , icm.execution_step.EstimatedEndDate ," + 
    			"icm.execution_step.EndDate " + 
    			"FROM icm.execution_step " + 
    			"INNER JOIN icm.change_request " + 
    			"ON icm.execution_step.ChangeRequestID = icm.change_request.ChangeRequestID " + 
    			"WHERE icm.execution_step.Status = 'CLOSED' " + 
    			"UNION " + 
    			"SELECT icm.change_request.SelectedSubsystem , icm.tester_step.EstimatedEndDate ," + 
    			"icm.tester_step.EndDate " + 
    			"FROM icm.tester_step " + 
    			"INNER JOIN icm.change_request " + 
    			"ON icm.tester_step.ChangeRequestID = icm.change_request.ChangeRequestID " + 
    			"WHERE icm.tester_step.Status = 'CLOSED'";
    	
    	sqlArray[SqlQueryType.SELECT_DATES_FROM_CLOSED_CHANGE_REQUEST.getCode()]=
    			"SELECT SelectedSubsystem , EstimatedDate , EndDate FROM icm.change_request"
    			+ " WHERE Status = 'CLOSED'";
    	/* *****************************************************
		 * *********** Upload Change Request Queries ***********
		 * *****************************************************/
    	sqlArray[SqlQueryType.INSERT_NEW_CHANGE_REQUEST.getCode()]= 
    			"INSERT INTO icm.change_request(InitiatorUserName,StartDate,"
    			+ "SelectedSubSystem,CurrentStateDescription,DesiredChangeDescription,DesiredChangeExplanation,DesiredChangeComments,"
    			+ "Status,CurrentStep,HandlerUserName,Email,JobDescription,FullName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";//Changed
    	sqlArray[SqlQueryType.SELECT_ALL_INFROMATION_ENGINEERS.getCode()]=
    			"SELECT UserName FROM icm.user "
    			+ "WHERE JobDescription = 'Information Engineer' OR JobDescription = 'Supervisor' "
    			+ "OR JobDescription = 'Committee member' OR JobDescription = 'Committee Director'"
    			+ "OR JobDescription = 'Supervisor Committee Director' Or JobDescription = 'Supervisor Committee Member'";
    	sqlArray[SqlQueryType.INSERT_NEW_FILE.getCode()] =
    			"INSERT INTO icm.file(ChangeRequestID,FileEnding) "
    			+ "VALUES (?,?)";
    	sqlArray[SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID.getCode()]=
    			"SELECT FileID, FileEnding FROM icm.file "
    			+ "WHERE ChangeRequestID = ?";
    	sqlArray[SqlQueryType.SELECT_HANDLER_USER_NAME_BY_SYSTEM.getCode()]=
    			"SELECT ResponsibleUserName FROM icm.subsystem_support "
    			+ "WHERE Subsystem=?";
    
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
    	 *******************************************************/
    	sqlArray[SqlQueryType.SELECT_EXECUTIOM_STEP_DETAILS.getCode()] = 
				"SELECT * FROM icm.execution_step WHERE ChangeRequestID = ?"
     			+ " ORDER BY ExecutionStepID DESC LIMIT 1";
    	sqlArray[SqlQueryType.UPDATE_EXECUTION_STEP_ESTIMATED_END_DATE_BY_STEP_ID.getCode()]=
    			"UPDATE icm.execution_step SET EstimatedEndDate = ? WHERE ExecutionStepID = ?";
    	
    	
    	sqlArray[SqlQueryType.UPDATE_STATUS_AND_DATE_IN_EXECUTION_STEP.getCode()]=
    			"UPDATE icm.execution_step SET Status = ?,EndDate = ?,ExecutionComment = ? WHERE ChangeRequestID = ?" + 
    			" ORDER BY ExecutionStepID DESC LIMIT 1";
    	sqlArray[SqlQueryType.SELECT_IF_CURRENT_STEP_CHANGED_TO_EXECUTION_WORK.getCode()] = 
				"SELECT CurrentStep FROM icm.change_request WHERE ChangeRequestID = ?";
    	
    	
    	sqlArray[SqlQueryType.SELECT_ESTIMATED_DATE_MINUS_START_DATE.getCode()] = 
				"SELECT EstimatedEndDate FROM icm.execution_step WHERE ChangeRequestID = ?";
    	
     	
     	
     	sqlArray[SqlQueryType.UPDATE_NEW_EXECUTION_APPROVE_TIME_STATUS.getCode()]=             // update change request status
    			"UPDATE icm.change_request SET CurrentStep = ? WHERE ChangeRequestID = ?";
     	
     	sqlArray[SqlQueryType.UPDATE_CURRENT_STEP_TO_TESTER.getCode()]=
    			"UPDATE icm.change_request SET CurrentStep = ?,HandlerUserName = ? WHERE ChangeRequestID = ?";
     	
     	
     	
     	
     	
     	
     	
     	
     	/* ******************************************************
    	 * *************Supervisor Queries ****************
    	 *******************************************************
    	 *******************************************************/
     	
     	
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUEST.getCode()] = 
				"SELECT * FROM icm.change_request";
    	
    	
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS.getCode()] = 
				"SELECT * FROM icm.change_request" +
						" WHERE CurrentStep = 'ANALYZER_AUTO_APPOINT' OR CurrentStep = 'EXECUTION_LEADER_SUPERVISOR_APPOINT' OR CurrentStep = 'ANALYZER_SUPERVISOR_APPOINT' ";
    	
    	
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS.getCode()] = 
				"SELECT * FROM icm.change_request" +
						" WHERE CurrentStep = 'ANALYSIS_APPROVE_TIME' OR CurrentStep = 'EXECUTION_APPROVE_TIME'";
    	
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE.getCode()] = 
				"SELECT * FROM icm.change_request WHERE Status = 'ACTIVE' AND (CurrentStep = 'CLOSING_STEP' OR CurrentStep = 'DENY_STEP')";

    	sqlArray[SqlQueryType.UPDATE_CURRENT_STEP_TO_ANALYZER_SUPERVISOR_APPOINT.getCode()] =
    			"UPDATE icm.change_request SET CurrentStep  = ? WHERE ChangeRequestID = ?";
    			
    	sqlArray[SqlQueryType.UPDATE_ANALYZER_BY_SUPERVISOR.getCode()] = "UPDATE icm.change_request SET CurrentStep  = ?,HandlerUserName = ? WHERE ChangeRequestID = ?";
    	
    	
    	sqlArray[SqlQueryType.INSERT_NEW_ANALYSIS_STEP.getCode()]=                  
    			"INSERT INTO icm.analysis_step(ChangeRequestID,HandlerUserName,StartDate,Status) "
    	    	+ "VALUES (?,?,?,?)";
    	
    	sqlArray[SqlQueryType.UPDATE_STEP_TO_ANALYSIS_SET_TIME.getCode()] = "UPDATE icm.change_request SET CurrentStep  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.INSERT_NEW_ANALYSIS_STEP_AFTER_APPROVE.getCode()]=                  
    			"INSERT INTO icm.analysis_step(ChangeRequestID,HandlerUserName,StartDate,Status) "
    	    	+ "VALUES (?,?,?,?)";
    	
    	sqlArray[SqlQueryType.UPDATE_NEW_EXECUTION_LEADER.getCode()] = "UPDATE icm.change_request SET HandlerUserName = ?, CurrentStep  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.INSERT_NEW_EXECUTION_STEP.getCode()]=                  
    			"INSERT INTO icm.execution_step(ChangeRequestID,HandlerUserName,StartDate,Status) "
    	    	+ "VALUES (?,?,?,?)";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_ANALYSIS_SET_TIME.getCode()] = "UPDATE icm.change_request SET CurrentStep  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_ANALYSIS_SET_TIME.getCode()] = "UPDATE icm.change_request SET CurrentStep  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_EXECUTION_SET_TIME.getCode()] = "UPDATE icm.change_request SET CurrentStep  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_EXECUTION_SET_TIME.getCode()] = "UPDATE icm.change_request SET CurrentStep  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_SUSPENDED.getCode()] = "UPDATE icm.change_request SET Status  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_ACTIVE.getCode()] = "UPDATE icm.change_request SET Status  = ? WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_CLOSED.getCode()] = "UPDATE icm.change_request SET Status  = ?, CurrentStep  = ?, EndDate = ?  WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.SELECT_EXECUTION_ESTIMATED_DATE.getCode()] = 
				"SELECT EstimatedEndDate FROM icm.execution_step" +
						" WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.SELECT_ANALYSIS_ESTIMATED_DATE.getCode()] = 
				"SELECT EstimatedEndDate FROM icm.analysis_step" +
						" WHERE ChangeRequestID = ?";
    	
    	sqlArray[SqlQueryType.SELECT_ALL_ENGINEERS.getCode()] = 
    			"SELECT * FROM icm.user WHERE Permission = 'SUPERVISOR' OR Permission = 'INFORMATION_ENGINEER'" + 
    			" OR Permission = 'COMMITTEE_MEMBER' OR Permission = 'COMMITTEE_DIRECTOR' "
    			+ "OR Permission = 'SUPERVISER_COMMITTEE_MEMBER' OR Permission = 'SUPERVISER_COMMITTEE_DIRECTOR' ";
    			
    	
    	sqlArray[SqlQueryType.UPDATE_END_DATE_IN_CLOSING_STEP.getCode()] = "UPDATE icm.closing_step SET EndDate = ?, Status  = ? WHERE ChangeRequestID = ?";
    	
    	/* *****************************************************
		 * ************* Time Watcher Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.GET_ALL_STEPS_ONE_DAY_REMAINING.getCode()] =
    			"SELECT * FROM icm.mergred_steps "
    			+ "WHERE datediff(EstimatedEndDate, now()) = 1 AND "
    			+ "STATUS = 'Active';";
    	sqlArray[SqlQueryType.GET_USER_EMAIL.getCode()] =
    			"SELECT Email FROM icm.user "
    			+ "WHERE UserName = ?";
    	sqlArray[SqlQueryType.GET_ALL_STEPS_TIME_PASSED_TODAY.getCode()] =
    			"SELECT * FROM icm.mergred_steps "
    			+ "WHERE datediff(EstimatedEndDate, now()) = 0 AND "
    			+ "STATUS = 'Active';";
    	sqlArray[SqlQueryType.GET_HIGH_MANGEMENT_MAILS.getCode()] =
    			"SELECT Email From icm.user "
    			+ "WHERE Permission = 'SUPERVISOR' OR Permission = 'INFORMATION_ENGINEERING_DEPARTMENT_HEAD' "
    			+ "OR Permission = 'SUPERVISOR_COMMITTEE_DIRECTOR' OR Permission = 'SUPERVISOR_COMMITTEE_MEMBER';";
    	sqlArray[SqlQueryType.GET_USER_FULL_NAME.getCode()] =
    			"SELECT FirstName, LastName From icm.user "
    			+ "WHERE UserName = ?";
    	/* *****************************************************
		 * ************* Request List Page Queries ****************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER.getCode()]=
				"SELECT * FROM icm.change_request WHERE InitiatorUserName=?";
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER_WITH_DATE_FILTER.getCode()]=
    			"SELECT * FROM icm.change_request WHERE InitiatorUserName=? AND StartDate BETWEEN ? AND ?";
    	sqlArray[SqlQueryType.SELECET_SPECIFIC_CHANGE_REQUEST_FOR_USER_WITH_ID_FILTER.getCode()]=
    			"SELECT * FROM icm.change_request WHERE InitiatorUserName=? AND ChangeRequestID=?";
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER_WITH_STATUS_FILTER.getCode()]=
    			"SELECT * FROM icm.change_request WHERE InitiatorUserName=? AND Status=?";
    	/* *****************************************************
		 * *************** Extra Details Queries ***************
		 * *****************************************************/
    	sqlArray[SqlQueryType.UPDATE_STATUS_BY_SUPERVISOR.getCode()]=
    			"UPDATE icm.change_request SET Status  = ? WHERE ChangeRequestID = ?";
    }
    
}




