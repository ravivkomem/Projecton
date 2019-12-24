package entities;

public class CommitteeComment {
	int commentId;
	int requestId;
	String employeeUserName;
	String comment;
	
	
	public CommitteeComment(int requestId, String userName, String comment) {
		super();
		this.requestId = requestId;
		this.employeeUserName = userName;
		this.comment = comment;
	}
	
	public CommitteeComment(int commentId, int requestId, String userName, String comment) {
		super();
		this.commentId = commentId;
		this.requestId = requestId;
		this.employeeUserName = userName;
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getEmployeeUserName() {
		return employeeUserName;
	}
	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
