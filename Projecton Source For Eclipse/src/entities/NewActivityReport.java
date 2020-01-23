package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.Utilizer;

public class NewActivityReport {

	private long[] numberOfChangeRequests;
	private Date startDate;
	private Date endDate;
	private double std;
	private double median;
	
	public NewActivityReport(long[] numberOfChangeRequests, Date startDate, Date endDate) {
		super();
		this.numberOfChangeRequests = numberOfChangeRequests;
		this.startDate = startDate;
		this.endDate = endDate;
		
		List<Long> arrayAsList = new ArrayList<Long>();
		for (int i = 0; i < numberOfChangeRequests.length; i++)
		{
			arrayAsList.add(numberOfChangeRequests[i]);
		}
		std = Utilizer.calcStd(arrayAsList);
		median = Utilizer.calcMedian(arrayAsList);
	}

	public long[] getNumberOfChangeRequests() {
		return numberOfChangeRequests;
	}

	public void setNumberOfChangeRequests(long[] numberOfChangeRequests) {
		this.numberOfChangeRequests = numberOfChangeRequests;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewActivityReport other = (NewActivityReport) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (Double.doubleToLongBits(median) != Double.doubleToLongBits(other.median))
			return false;
		if (!Arrays.equals(numberOfChangeRequests, other.numberOfChangeRequests))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (Double.doubleToLongBits(std) != Double.doubleToLongBits(other.std))
			return false;
		return true;
	}
	
	
}
