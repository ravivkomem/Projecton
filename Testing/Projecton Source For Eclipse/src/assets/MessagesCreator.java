package assets;

import entities.Step;

/**
 * 
 * @author Lee Hugi
 * this class is responsible for creating automatic messages that will be send to users
 *
 */

public class MessagesCreator {
	
	/**
	 * this method gets step and user full name
	 * the method creates a message thats reminding the user to handle the change request,
	 * @param step - The step used for the message
	 * @param fullName - The user full name
	 * @return A proper message for last day notification
	 */
	public static String lastDayMsg(Step step,String fullName) {
		String msg="Dear "+fullName+",\nYou have ONE DAY left to finish"
	+" your work on change request NO."+step.getChangeRequestID()+" which is in "+
				step.getType()+ " step.\n"+"\n**DO NOT REPLY TO THIS MAILL**";
		return msg;
	}
	
	/**
	 * this method gets step and user full name
	 * the method creates a message thats reminding the user that he delay in handle the change request
	 * @param step - the step used for the message
	 * @param fullName - The step handler full name
	 * @return  A proper message for change request delay notification
	 */
	public static String delayWorkMsg(Step step,String fullName) {
		String msg = "Dear " + fullName + ",\nYour work on change request NO."+step.getChangeRequestID()
		+" which is in " + step.getType() + " step, IS OVERDUE.\nPlease finish your work as fast as possible.\n"
		+"\n**DO NOT REPLY TO THIS MAILL**";
		return msg;
	}
	
	/**
	 * this method gets user full name and message from the supervisor
	 * the method creates a message for the change request initiator 
	 * @param fullName - The initator full name
	 * @param supervisorMsg - The message entered by the supervisor
	 * @return - A proper message for closing change request
	 */
	public static String supervisorCloseChangeRequest(String fullName,String supervisorMsg) {
		String msg ="Dear " + fullName+",\n"+supervisorMsg
		+"\nHave A Nice Day,\nICM System\n"+"\n**DO NOT REPLY TO THIS MAILL**";
		return msg;
	}
	
}
