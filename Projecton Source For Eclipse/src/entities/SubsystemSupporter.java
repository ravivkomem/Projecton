package entities;

public class SubsystemSupporter {

	private String subsystem;
	private String userName;
	
	public SubsystemSupporter(String subsystem, String userName) {
		this.subsystem = subsystem;
		this.userName = userName;
	}

	public String getSubsystem() {
		return subsystem;
	}

	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
