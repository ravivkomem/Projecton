package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlFileAction;
import assets.SqlResult;
import entities.MyFile;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  final public static String FILLE_DIRECTORY ="C:\\ServerFiles\\";
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient (Object msg, ConnectionToClient client)
  {
	  SqlAction sqlAction = (SqlAction) msg;
	  MysqlConnection sqlConnection = new MysqlConnection();
	  SqlResult sqlResult = sqlConnection.getResult(sqlAction);
	  System.out.println("Message received: " + sqlResult.getResultData().toString() + " from " + client);
	 
	  /* If it is a file request also upload it to the server */
	  if(msg instanceof SqlFileAction)
	  {
	
		 SqlFileAction sqlFileAction = (SqlFileAction)msg;
		 if (sqlFileAction.getUpload() == true)
		 {
			 MyFile uploadedFile = sqlFileAction.getMyFile();
			 int fileIndex = ((BigInteger) (sqlResult.getResultData().get(0).get(0))).intValue();
			 String fileExtension = (String)sqlFileAction.getActionVars().get(1);
			 String filePath = FILLE_DIRECTORY+fileIndex+"."+fileExtension;
			 
			 System.out.println("File path is: " + filePath);
			 try (FileOutputStream fileOuputStream = new FileOutputStream(filePath))
			 {
				 fileOuputStream.write(uploadedFile.getMybytearray());
			 } 
			 catch (FileNotFoundException e) {
				e.printStackTrace();
			 } 
			 catch (IOException e) {
				e.printStackTrace();
			 }
		 }
		 else
		 {
			 ArrayList<Object> newResultData = new ArrayList<Object>();
			 /* Create my file from path */
			 if (!sqlResult.getResultData().isEmpty())
			 {
				 String fileName = ((Integer)sqlResult.getResultData().get(0).get(0)).toString();
				 String fileExtension = (String)sqlResult.getResultData().get(0).get(1);
				 String path = FILLE_DIRECTORY+fileName+"."+fileExtension;
				 MyFile myFile = MyFile.parseToMyFile(path);
				 newResultData.add(myFile);
			 }
			 sqlResult.setResultData(newResultData);
			
		 }
		
		 
	  }
	  	
		this.sendToClient(sqlResult, client);
  }

    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void startServer(String[] args) 
  {
	MysqlConnection.initSqlArray();
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    setSv(sv);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
  
  public static EchoServer temp;
  
  public static void setSv(EchoServer sv) {
		temp = sv;
	}
  
}


//End of EchoServer class
