package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    
    	this.connect();
    	try {
    		
			PreparedStatement ps = connection.prepareStatement(sqlArray[sqlAction.getActionType().getCode()]);
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
    	sqlArray[SqlQueryType.SELECT_COMMENTS_BY_REQUEST_ID.getCode()]="SELECT * FROM icm.committee_comment"
    			+ "WHERE requestId = ?";
    	sqlArray[SqlQueryType.INSERT_NEW_COMMITTEE_COMMENT.getCode()]="INSERT INTO `committee_comment`(requestId,employeeId,comment)"
    			+ " VALUES (?,?,?)";
    	sqlArray[SqlQueryType.INSERT_NEW_CHANGE_REQUEST.getCode()]= "INSERT INTO icm.change_request(InitiatorUserName,StartDate,"
    			+ "SelectedSubSystem,CurrentStateDescription,DesiredChangeDescription,DesiredChangeExplanation,DesiredChangeComments,"
    			+ "Status,CurrentStep,HandlerUserName,UploadedFiles) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    	sqlArray[SqlQueryType.SELECT_ALL_CHANGE_REQUESTS_BY_INITIATOR_NAME.getCode()] =
    			"SELECT * FROM icm.change_request "
    			+ "WHERE HandlerUserName = ?";
    	sqlArray[SqlQueryType.SELECT_ANALYSIS_STEP_CHANGE_REQUESTS_BY_INITIATOR_NAME.getCode()] =
    			"SELECT * FROM icm.change_request "
    	    	+ "WHERE HandlerUserName = ? AND CurrentStep = 'Analysis'";
    }
    
}


