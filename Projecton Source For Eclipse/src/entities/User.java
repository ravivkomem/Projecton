package entities;

public class User {

	public User(String userName, String userEmail, String permission) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.permission = permission;
	}

	private String userName;
	private String userEmail;
	private String permission;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
