package server;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import assets.EmailTLS;
import assets.MessagesCreator;
import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import entities.Step;
import controllers.TimeManager;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeWatcher.
 */
public class TimeWatcher implements Runnable {

	/** The email TLS. */
	EmailTLS emailTLS;
	
	/** The sql connection. */
	private MysqlConnection sqlConnection;
	
	/** The last day watch. */
	private Date lastDayWatch;
	
	/** The one day remaining steps. */
	private Collection<Step> oneDayRemainingSteps;
	
	/** The time passed steps. */
	private Collection<Step> timePassedSteps;
	
	/** The high mangement mails. */
	private Collection<String> highMangementMails;
	
	/** The Constant MILLIES_DELAY_TIME_BETWEEN_EMAILS. */
	private static final long MILLIES_DELAY_TIME_BETWEEN_EMAILS = 7000;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
		for(;;)
		{
			sleepUntilNextDay();
			getStepsWithOneDayRemaining();
			getStepsWithTimeException();
			getHighMangementMails();
			sendMails();
		}
	}
	
	/**
	 * Sleep until next day.
	 */
	private void sleepUntilNextDay() {
		
		Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long milliesUntilMidnight = (c.getTimeInMillis()-System.currentTimeMillis());
        System.out.println("Time Watcher - Going to sleep " + milliesUntilMidnight + " millisecond until midnight");
        
        try
        {
        	Thread.sleep(milliesUntilMidnight);
        	/* Sleep was successful */
        	lastDayWatch = TimeManager.getCurrentDate();
        	System.out.println("Time Watcher - I just woke up, starting to check time deviations");
        }
        catch (Exception e)
        {
        	if (lastDayWatch.compareTo(TimeManager.getCurrentDate()) == 0)
        	{
        		/* Exception was caught and the date did not change */
        		sleepUntilNextDay();
        	}
        	else
        	{
        		lastDayWatch = TimeManager.getCurrentDate();
            	System.out.println("Time Watcher - I just woke up, starting to check time deviations");
        	}
        }
		
	}

	/**
	 * Gets the steps with one day remaining.
	 *
	 * @return the steps with one day remaining
	 */
	private void getStepsWithOneDayRemaining() 
	{
		oneDayRemainingSteps.clear();
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_STEPS_ONE_DAY_REMAINING);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		Collection<Step> stepCollection = Step.parseSqlResultToStepCollection(sqlResult);
		oneDayRemainingSteps.addAll(stepCollection);
		
	}
	
	/**
	 * Gets the steps with time exception.
	 *
	 * @return the steps with time exception
	 */
	private void getStepsWithTimeException() {
		timePassedSteps.clear();
		
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_STEPS_TIME_PASSED_TODAY);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		Collection<Step> stepCollection = Step.parseSqlResultToStepCollection(sqlResult);
		timePassedSteps.addAll(stepCollection);
	}
	
	/**
	 * Gets the high mangement mails.
	 *
	 * @return the high mangement mails
	 */
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
	
	/**
	 * Send mails.
	 */
	private void sendMails() {
		sendOneDayRemainingMails();
		sendTimePassedMails();
	}

	/**
	 * Send one day remaining mails.
	 */
	private void sendOneDayRemainingMails()
	{
		for (Step s : oneDayRemainingSteps)
		{
			String emailAddress = getEmailAddressOfUser(s.getHandlerUserName());
			String fullName = getFullNameOfUser(s.getHandlerUserName());
			String message = MessagesCreator.lastDayMsg(s, fullName);
			emailTLS.sendMessage(emailAddress, "Notification - One day remainin", message);
			/* Gmail emails are limited to 10 per minute */
			try 
			{
				Thread.sleep(MILLIES_DELAY_TIME_BETWEEN_EMAILS);
			}
			catch (Exception e)
			{
				
			}
		}
	}
	
	/**
	 * Send time passed mails.
	 */
	private void sendTimePassedMails() {
		for (Step s : timePassedSteps)
		{
			String emailAddress = getEmailAddressOfUser(s.getHandlerUserName());
			String fullName = getFullNameOfUser(s.getHandlerUserName());
			String message = MessagesCreator.lastDayMsg(s, fullName);
			emailTLS.sendMessage(emailAddress, highMangementMails, "Notification - TimePassed", message);
			/* Gmail emails are limited to 10 per minute */
			try 
			{
				Thread.sleep(MILLIES_DELAY_TIME_BETWEEN_EMAILS);
			}
			catch (Exception e)
			{
				
			}
		}
		
	}

	/**
	 * Gets the email address of user.
	 *
	 * @param userName the user name
	 * @return the email address of user
	 */
	private String getEmailAddressOfUser(String userName) {
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(userName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_USER_EMAIL, varArray);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		return (String)sqlResult.getResultData().get(0).get(0);
	}
	
	/**
	 * Gets the full name of user.
	 *
	 * @param userName the user name
	 * @return the full name of user
	 */
	private String getFullNameOfUser(String userName)
	{
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(userName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.GET_USER_FULL_NAME, varArray);
		SqlResult sqlResult = sqlConnection.getResult(sqlAction);
		
		String firstName = (String)sqlResult.getResultData().get(0).get(0);
		String lastName = (String)sqlResult.getResultData().get(0).get(1);
		String fullName = firstName + " " + lastName;
		
		return fullName;
	}

}
