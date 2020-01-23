package controllers;

import java.util.Collections;
import java.util.List;

/**
 * The Class Utilizer.
 *
 * @author Lee Hugi
 * this class provides mathematical calculations for report pages
 */
public class Utilizer {
	
	/**
	 * this method calculate the median.
	 *
	 * @param list the list
	 * @return the double
	 */
	public static double calcMedian(List<Long> list) {
		if (list.isEmpty())
		{
			return 0.0;
		}
		
		Collections.sort(list);
		double median;
		if (list.size() % 2 == 0)
		    median = ((double)list.get(list.size()/2) + (double)list.get(list.size()/2 - 1))/2;
		else
		    median = (double) list.get(list.size()/2);
		return median;	
	}
	
	/**
	 * this method calculate the standard deviation.
	 *
	 * @param list the list
	 * @return the double
	 */
	public static double calcStd(List<Long> list) {
		if (list.isEmpty())
		{
			return 0.0;
		}
		
		double avg = calcAvg(list);
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += (double)Math.pow((list.get(i)-avg), 2);
		}
		return (double)Math.sqrt((1.0/list.size())*sum);
	}
	
	/**
	 * this method calculate the average.
	 *
	 * @param list the list
	 * @return the double
	 */
	public static double calcAvg(List<Long> list) {
		if (list.isEmpty())
		{
			return 0.0;
		}
		long sum=0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return (double)sum/list.size();
	}
	
	
}
