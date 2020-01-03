package assets;

public enum StepType {

	ERROR("ERROR"),
	ANALYSIS("Analysis"),
	COMMITTEE("Committee"),
	EXECUTION("Execution"),
	TESTING("Testing"),
	CLOSING("Closing");
	
	private String name;
	
	private StepType(String name)
	{
		this.name = name;
	}
	
	public String getStepName()
	{
		return name;
	}
}
