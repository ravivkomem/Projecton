package controllers;

import java.util.ArrayList;
import java.util.Collections;

public class Utilizer {
	
	public static double calcMedian(ArrayList<Long> list) {
		Collections.sort(list);
		double median;
		if (list.size() % 2 == 0)
		    median = ((double)list.get(list.size()/2) + (double)list.get(list.size()/2 - 1))/2;
		else
		    median = (double) list.get(list.size()/2);
		return median;	
	}
	
	public static double calcStd(ArrayList<Long> list) {
		double avg = calcAvg(list);
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += (double)Math.pow(2, (list.get(i)-avg));
		}
		return (double)Math.sqrt((1.0/list.size())*sum);
	}
	
	public static double calcAvg(ArrayList<Long> list) {
		long sum=0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return (double)sum/list.size();
	}
	
	
}
