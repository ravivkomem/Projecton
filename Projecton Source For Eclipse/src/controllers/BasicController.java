package controllers;

import java.io.Serializable;

import assets.SqlAction;
import assets.SqlResult;
import client.ChatClient;
import client.ClientConsole;

public abstract class BasicController implements Serializable {
	
	private Integer subscribtionCounter = 0;
	
	public abstract void getResultFromClient(SqlResult result);
	
	protected void subscribeToClientDeliveries() {
		
		synchronized(subscribtionCounter) 
		{
			if (0 == subscribtionCounter)
			{
				ChatClient.joinSubscription(this);
			}
			subscribtionCounter++;
		}
	}
	
	protected void unsubscribeFromClientDeliveries() {
		
		synchronized(subscribtionCounter) 
		{
			if (1 == subscribtionCounter)
			{
				ChatClient.unSubscribe(this);
			}
			
			if (subscribtionCounter != 0)
			{
				subscribtionCounter--;
			}
			
		}
	}
	
	protected void sendSqlActionToClient(SqlAction sqlAction)
	{
		this.subscribeToClientDeliveries();
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
}
