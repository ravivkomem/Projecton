package entities;

public class CommitteeComment {
	int employeeId;
	String comment;
	
	public CommitteeComment(int employeeId, String comment) {
		this.employeeId = employeeId;
		this.comment = comment;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
