package entities;

public class CommitteeComment {
	int requestId;
	int employeeId;
	String comment;
	
	public CommitteeComment(int requestId, int employeeId, String comment) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.comment = comment;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
