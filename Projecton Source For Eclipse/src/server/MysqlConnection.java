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
    	sqlArray[SqlQueryType.UPDATE_CHANGE_REQUEST_BY_ID.getCode()] = "UPDATE icm.requirements "
    			+ "SET InitaitorName = ?, Subsystem = ?, CurrentState = ?, ChangeDescription = ?, Status = ?,"
    			+ " HandlerName = ? "
    			+ "WHERE ChangeRequestID = ? ";
    	sqlArray[SqlQueryType.SELECT_CHANGE_REQUEST_BY_ID.getCode()] = "SELECT * FROM icm.requirements "
    			+ "WHERE ChangeRequestID = ?";
    	sqlArray[SqlQueryType.VERIFY_LOGIN.getCode()] = "SELECT * FROM icm.user "
    			+ "WHERE UserName = ? AND Password = ?";
    	sqlArray[SqlQueryType.GET_USER_CONNECTION_STATUS.getCode()] = "SELECT IsLogged FROM icm.user "
    			+ "WHERE UserName = ? AND Password = ?";
    	
    	sqlArray[SqlQueryType.INSERT_NEW_CHANGE_REQUEST.getCode()]= 
    			"INSERT INTO icm.change_request(InitiatorUserName,StartDate,"
    			+ "SelectedSubSystem,CurrentStateDescription,DesiredChangeDescription,DesiredChangeExplanation,DesiredChangeComments,"
    			+ "Status,CurrentStep,HandlerUserName) VALUES (?,?,?,?,?,?,?,?,?,?)";
    	
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
		 * *************** XXX Queries **************
		 * *****************************************************/
    	sqlArray[SqlQueryType.UPDATE_TESTER_STEP.getCode()] = "UPDATE icm.tester_step "
    			+ "SET TesterFailReport = ? WHERE ChangeRequestID = ? ORDER BY TesterStepId DESC LIMIT 1";
    	sqlArray[SqlQueryType.SELECT_ALL_INFROMATION_ENGINEERS.getCode()]=
    			"SELECT UserName FROM icm.user "
    			+ "WHERE JobDescription = 'Information Engineer' OR JobDescription = 'Supervisor' "
    			+ "OR JobDescription = 'Committee member' OR JobDescription = 'Committee Director'";
		sqlArray[SqlQueryType.INSERT_NEW_EXECUTION_APROVE.getCode()]=
				"INSERT INTO icm.execution_aproves(ExecutionTime)"
				+ " VALUES (?)";
		sqlArray[SqlQueryType.SELECT_ANALYSIS_REPORT_BY_CHANGE_REQUEST_ID.getCode()] = 
				"SELECT * FROM icm.analysis_step WHERE ChangeRequestId = ?"
				+ " ORDER BY AnalysisStepID DESC LIMIT 1";
		sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_FOR_SPECIFIC_USER.getCode()]=
				"SELECT * FROM icm.change_request WHERE InitiatorUserName=?";
		
		/* *****************************************************
		 * *************** Time Extension Queries **************
		 * *****************************************************/
		sqlArray[SqlQueryType.INSERT_NEW_TIME_EXTENSION.getCode()] =
				"INSERT INTO icm.time_extension(StepType,NewDate,Reason,Status) " + 
				"VALUES (?,?,?,'NEW')";
		
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
    	
    	/* *****************************************************
		 * *************** Tech Manager Queries **************
		 * *****************************************************/
    	sqlArray[SqlQueryType.SELECT_ALL_ACTIVE_CHANGE_REQUESTS.getCode()]=
    			"SELECT * FROM icm.change_request WHERE Status = 'Active'";
    	sqlArray[SqlQueryType.SELECT_ALL_EMPLOYEE.getCode()] = 
    			"SELECT * FROM icm.user WHERE Permission = 'SUPERVISOR' OR Permission = 'INFORMATION_ENGINEER'" + 
    			" OR 'COMMITTEE_MEMBER' OR 'COMMITTEE_DIRECTOR'";
    }
    
}




