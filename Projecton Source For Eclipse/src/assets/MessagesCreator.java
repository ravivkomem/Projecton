package assets;

import entities.Step;

public class MessagesCreator {
	
	public static String lastDayMsg(Step step,String fullName) {
		String msg="Dear "+fullName+",\nYou have ONE DAY left to finish"
	+" your work on change request NO."+step.getChangeRequestID()+" which is in "+
				step.getType()+ "step.\n"+"\n**DO NOT REPEAT TO THIS MAIL**";
		return msg;
	}
	
	public static String delayWorkMsg(Step step,String fullName) {
		String msg = "Dear " + fullName + "\nYour work on change request NO."+step.getChangeRequestID()
		+" which is in " + step.getType() + "step, IS OVERDUE.\nPlease finish your work as fast as possible.\n"
		+"\n**DO NOT REPEAT TO THIS MAIL**";
		return msg;
	}
	
}
