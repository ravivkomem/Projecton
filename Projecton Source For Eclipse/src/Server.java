import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Server {

	public static void main (String[] args)
	{
		ServerSocket serverSocket;
		Socket socket;
		Scanner clientScanner;
		PrintStream clientPrintStream;
		MysqlConnection sqlConnection = new MysqlConnection();
		int number;
		
		
		try {
			/* Needs the port number used by the client */
			serverSocket = new ServerSocket(1342);
			socket = serverSocket.accept();
			clientScanner = new Scanner(socket.getInputStream());
			clientPrintStream = new PrintStream(socket.getOutputStream());
			
			try {
				Statement statement = sqlConnection.connect().createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM test_table");
				
				while (rs.next())
				{
					System.out.println("ID = " + rs.getString(1) + "Name = " + rs.getString(2));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			number = clientScanner.nextInt();
			/* Sending the client the number times two */
			clientPrintStream.println(number*2);
			
			sqlConnection.disconnect();
			serverSocket.close();
			socket.close();
			clientScanner.close();
			clientPrintStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
