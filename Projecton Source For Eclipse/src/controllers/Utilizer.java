package controllers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Lee Hugi
 * this class provides mathematical calculations for report pages
 *
 */
public class Utilizer {
	
	/**
	 * this method calculate the median
	 * @param list
	 * @return
	 */
	public static double calcMedian(ArrayList<Long> list) {
		Collections.sort(list);
		double median;
		if (list.size() % 2 == 0)
		    median = ((double)list.get(list.size()/2) + (double)list.get(list.size()/2 - 1))/2;
		else
		    median = (double) list.get(list.size()/2);
		return median;	
	}
	
	/**
	 * this method calculate the standard deviation
	 * @param list
	 * @return
	 */
	public static double calcStd(ArrayList<Long> list) {
		double avg = calcAvg(list);
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += (double)Math.pow((list.get(i)-avg), 2);
		}
		return (double)Math.sqrt((1.0/list.size())*sum);
	}
	
	/**
	 * this method calculate the average
	 * @param list
	 * @return
	 */
	public static double calcAvg(ArrayList<Long> list) {
		long sum=0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return (double)sum/list.size();
	}
	
	
}
