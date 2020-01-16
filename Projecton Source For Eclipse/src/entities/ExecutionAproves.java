package entities;

public class ExecutionAproves {
	private int aproveID;
	private String aproveTime;
	public ExecutionAproves(int aproveID,String aproveTime)
	{
		this.aproveID = aproveID;
		this.aproveTime = aproveTime;
	}
	public ExecutionAproves(String aproveTime)
	{
		this.aproveTime=aproveTime;
	}
	public int getAproveId()
	{
		return aproveID;
	}
	public String getAproveTime()
	{
		return aproveTime;
	}
	public void setAproveTime(String aproveTime)
	{
		this.aproveTime=aproveTime;
	}
	public void setAproveID(int aproveID)
	{
		this.aproveID = aproveID;
	}
}
