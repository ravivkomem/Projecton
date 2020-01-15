package entities;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {

	/**
	 * Instantiates a new user.
	 *
	 * @param userID the user ID
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param department the department
	 * @param jobDescription the job description
	 * @param permission the permission
	 * @param phoneNumber the phone number
	 * @param isLogged the is logged
	 */
	public User(int userID, String userName, String password, String firstName, String lastName, String email,
			String department, String jobDescription, String permission, String phoneNumber, boolean isLogged) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.jobDescription = jobDescription;
		this.permission = permission;
		this.phoneNumber = phoneNumber;
		this.isLogged = isLogged;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userID the user ID
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param department the department
	 * @param jobDescription the job description
	 * @param permission the permission
	 * @param phoneNumber the phone number
	 */
	public User(int userID, String userName, String password, String firstName, String lastName, String email,
			String department, String jobDescription, String permission, String phoneNumber) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.jobDescription = jobDescription;
		this.permission = permission;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 */
	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}



	/** The user ID. */
	private int userID;
	
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The email. */
	private String email;
	
	/** The department. */
	private String department;
	
	/** The job description. */
	private String jobDescription;
	
	/** The permission. */
	private String permission;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The is logged. */
	private boolean isLogged;
	
	/**
	 * Checks if is logged.
	 *
	 * @return true, if is logged
	 */
	public boolean isLogged() {
		return isLogged;
	}
	
	/**
	 * Sets the logged.
	 *
	 * @param isLogged the new logged
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	/**
	 * Gets the user ID.
	 *
	 * @return the user ID
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * Sets the user ID.
	 *
	 * @param userID the new user ID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
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
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * Gets the job description.
	 *
	 * @return the job description
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	
	/**
	 * Sets the job description.
	 *
	 * @param jobDescription the new job description
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	/**
	 * Gets the permission.
	 *
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	
	/**
	 * Sets the permission.
	 *
	 * @param permission the new permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return firstName+" "+lastName;
	}
	
}
