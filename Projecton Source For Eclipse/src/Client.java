import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	
	public static void main (String[] args)
	{
		/* Constants */
		final String LOCAL_IP_ADRESS = "127.0.0.1";
		
		/* Variables */
		Scanner userInputScanner;
		Socket socket;
		Scanner serverInputScanner;
		PrintStream serverPrintStream;
		int number;
		int result;
		
		/*The method: communicate with server */
		
		try {
			userInputScanner = new Scanner(System.in);
			/*Two parameters: IP of machine we want to connect, and port number */
			socket = new Socket(LOCAL_IP_ADRESS, 1342);
			/* We now have a way to communicate with the server */
			serverInputScanner = new Scanner (socket.getInputStream());
			serverPrintStream = new PrintStream(socket.getOutputStream());
			
			System.out.println("Enter any number");
			number = userInputScanner.nextInt();
			serverPrintStream.println(number);
			
			result = serverInputScanner.nextInt();
			System.out.println(result);
			
			/*Close all the connections */
			userInputScanner.close();
			serverInputScanner.close();
			serverPrintStream.close();
			socket.close();
			
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
