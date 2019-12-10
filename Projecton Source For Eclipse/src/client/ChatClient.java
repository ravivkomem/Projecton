// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import controllers.LoginController;
import javafx.application.Platform;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import assets.ServerEvent;
import assets.SqlResult;


/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  /* Static variables */
  public static List<ServerEvent> changeRequestByIdListeners = new ArrayList<ServerEvent>();
  public static List<ServerEvent> updateChangeRequestByIdListeners = new ArrayList<ServerEvent>();
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  SqlResult result = (SqlResult) msg;

		switch (result.getActionType()) {
			case VERIFY_LOGIN: 
			case GET_CHANGE_REQUEST_BY_ID:
				Platform.runLater(() -> {
					this.getChangeRequestByIdListeners(result);
				});
				break;
			case UPDATE_CHANGE_REQUEST_BY_ID:
				Platform.runLater(() -> {
					this.updateChangeRequestByIdListeners(result);
				});
			default:
		}
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(Object message)  
  {
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
  
  	public static void addChangeRequestByIdListeners(ServerEvent toAdd) {
		if (!changeRequestByIdListeners.contains(toAdd)) {
			changeRequestByIdListeners.add(toAdd);
		}
	}

	public void getChangeRequestByIdListeners(SqlResult result) {
		// Notify everybody that may be interested.
		for (ServerEvent hl : changeRequestByIdListeners) {
			hl.getChangeRequestByIdResultDelivery(result);
			if (hl instanceof LoginController)
			{
				LoginController controller = (LoginController)hl;
				controller.getResultFromClient(result);
			}
		}
	}
	
	public void updateChangeRequestByIdListeners(SqlResult result) {
		// Notify everybody that may be interested.
		for (ServerEvent hl : updateChangeRequestByIdListeners) {
			hl.updateChangeRequestByIdResultDelivery(result);
		}
	}
  
  
}
//End of ChatClient class
