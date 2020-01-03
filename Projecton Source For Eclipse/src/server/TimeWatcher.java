package server;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import assets.EmailTLS;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import entities.Step;
import controllers.TimeManager;

public class TimeWatcher implements Runnable {

	EmailTLS emailTLS;
	private MysqlConnection sqlConnection;
	private Date lastDayWatch;
	private Collection<Step> oneDayRemainingSteps;
	private Collection<Step> timePassedSteps;
	private Collection<String> highMangementMails;
	
	@Override
	public void run() {
		/* Initialize all the private objects */
		sqlConnection = new MysqlConnection();
		MysqlConnection.initSqlArray();
		emailTLS = new EmailTLS();
		oneDayRemainingSteps = new ArrayList<Step>();
		timePassedSteps = new ArrayList<Step>();
		highMangementMails = new ArrayList<String>();
		lastDayWatch = TimeManager.getCurrentDate();
		
		/* Start work horse */
		//for(;;)
		//{
			sleepUntilNextDay();
			getStepsWithOneDayRemaining();
			getStepsWithTimeException();
			getHighMangementMails();
			sendMails();
		//}
	}
	
	private void sleepUntilNextDay() {
		// TODO Auto-generated method stub
//		Calendar c = Calendar.getInstance();
//        c.add(Calendar.DAY_OF_MONTH, 1);
//        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);
//        long howMany = (c.getTimeInMillis()-System.currentTimeMillis());
		
	}

	private void getStepsWithOneDayRemaining() 
	{
		oneDayRemainingSteps.clear();
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_STEPS_ONE_DAY_REMAINING);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		Collection<Step> stepCollection = Step.parseSqlResultToStepCollection(sqlResult);
		oneDayRemainingSteps.addAll(stepCollection);
		
	}
	
	private void getStepsWithTimeException() {
		timePassedSteps.clear();
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_STEPS_TIME_PASSED_TODAY);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		Collection<Step> stepCollection = Step.parseSqlResultToStepCollection(sqlResult);
		timePassedSteps.addAll(stepCollection);
	}
	
	private void getHighMangementMails() {
		highMangementMails.clear();
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_HIGH_MANGEMENT_MAILS);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		for (ArrayList<Object> resultRow : sqlResult.getResultData())
		{
			String currentEmailAddress = (String)resultRow.get(0);
			highMangementMails.add(currentEmailAddress);
		}
	}
	
	private void sendMails() {
		sendOneDayRemainingMails();
		sendTimePassedMails();
	}

	private void sendOneDayRemainingMails()
	{
		for (Step s : oneDayRemainingSteps)
		{
			String emailAddress = getEmailAddressOfUser(s.getHandlerUserName());
			emailTLS.sendMessage(emailAddress, "Notification - One day remainin", "Dear " + s.getHandlerUserName()+"\n your work is due in one day");
			/* Gmail emails are limited to 10 per minute */
			try 
			{
				Thread.sleep(700);
			}
			catch (Exception e)
			{
				
			}
		}
	}
	
	private void sendTimePassedMails() {
		for (Step s : timePassedSteps)
		{
			String emailAddress = getEmailAddressOfUser(s.getHandlerUserName());
			emailTLS.sendMessage(emailAddress, highMangementMails, "Notification - TimePassed", "Dear " + s.getHandlerUserName()+"\n Your work time has passed!!!\n All supervisors will be notified");
			/* Gmail emails are limited to 10 per minute */
			try 
			{
				Thread.sleep(700);
			}
			catch (Exception e)
			{
				
			}
		}
		
	}

	private String getEmailAddressOfUser(String handlerUserName) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(handlerUserName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_USER_EMAIL, varArray);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		return (String)sqlResult.getResultData().get(0).get(0);
	}
	

}
