package controllers;

import java.sql.Date;
import java.util.Calendar;

public class TimeManager {

	public static Date getCurrentDate()
	{
		return new Date(Calendar.getInstance().getTime().getTime());
	}
	
	
}
