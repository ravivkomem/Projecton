package entities;


// TODO: Auto-generated Javadoc
/**
 * The Class SubsystemSupporter.
 */
public class SubsystemSupporter {

	/** The subsystem. */
	private String subsystem;
	
	/** The user name. */
	private String userName;
	
	/**
	 * Instantiates a new subsystem supporter.
	 *
	 * @param subsystem the subsystem
	 * @param userName the user name
	 */
	public SubsystemSupporter(String subsystem, String userName) {
		this.subsystem = subsystem;
		this.userName = userName;
	}

	/**
	 * Gets the subsystem.
	 *
	 * @return the subsystem
	 */
	public String getSubsystem() {
		return subsystem;
	}

	/**
	 * Sets the subsystem.
	 *
	 * @param subsystem the new subsystem
	 */
	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
