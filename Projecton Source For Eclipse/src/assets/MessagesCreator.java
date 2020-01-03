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
	 * @param step
	 * @param fullName
	 * @return
	 */
	public static String lastDayMsg(Step step,String fullName) {
		String msg="Dear "+fullName+",\nYou have ONE DAY left to finish"
	+" your work on change request NO."+step.getChangeRequestID()+" which is in "+
				step.getType()+ " step.\n"+"\n**DO NOT REPLY TO THIS MAIL**";
		return msg;
	}
	
	/**
	 * this method gets step and user full name
	 * the method creates a message thats reminding the user that he delay in handle the change request
	 * @param step
	 * @param fullName
	 * @return
	 */
	public static String delayWorkMsg(Step step,String fullName) {
		String msg = "Dear " + fullName + "\nYour work on change request NO."+step.getChangeRequestID()
		+" which is in " + step.getType() + " step, IS OVERDUE.\nPlease finish your work as fast as possible.\n"
		+"\n**DO NOT REPLY TO THIS MAIL**";
		return msg;
	}
	
}
