import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
	
	/* Initialize database constants */
	/*TODO: Update constants name to suit the project */
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/ICM?serverTimezone=IST"; // URL requires Update
    private static final String USERNAME = "root";  // UserName requires update
    private static final String PASSWORD = "Aa123456";		// Password requires update
	
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
    
}


