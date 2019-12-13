package controllers;

import java.io.Serializable;

import assets.SqlResult;
import client.ChatClient;

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
	
}
