package controllers;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class TimeManager {

	public static Date getCurrentDate()
	{
		return new Date(Calendar.getInstance().getTime().getTime());
	}
	
	/**
	 * Get the time difference in days between start date and end date
	 * If it is negative it means the end date is before the start date
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDaysBetween(Date startDate, Date endDate) {
		long daysBetween;
		daysBetween = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
		/* Because we want difference included the first date */
		daysBetween +=1;

		return daysBetween;
	}
	
	public static Date getMaxDate (Date first, Date second)
	{
		long dayDiffrence = getDaysBetween(first, second);
		if (dayDiffrence > 0)
		{
			return second;
		}
		else
		{
			return first;
		}
	}
	
	public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }
}
