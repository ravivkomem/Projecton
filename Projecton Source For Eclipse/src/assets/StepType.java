package assets;

public enum StepType {

	ANALYSIS("Analysis"),
	COMMITTEE("Committee"),
	EXECUTION("Execution"),
	TESTEING("Testing"),
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
