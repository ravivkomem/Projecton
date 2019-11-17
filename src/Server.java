import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main (String[] args)
	{
		ServerSocket serverSocket;
		Socket socket;
		Scanner clientScanner;
		PrintStream clientPrintStream;
		int number;
		
		
		try {
			/* Needs the port number used by the client */
			serverSocket = new ServerSocket(1342);
			socket = serverSocket.accept();
			clientScanner = new Scanner(socket.getInputStream());
			clientPrintStream = new PrintStream(socket.getOutputStream());
			
			number = clientScanner.nextInt();
			/* Sending the client the number times two */
			clientPrintStream.println(number*2);
			
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
