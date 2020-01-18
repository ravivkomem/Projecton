package entities;

/**
 * The Class CommitteeComment.
 *
 * @author Lee Hugi
 * this class represents a comment
 * includes all the fields as described in the committee_comment table
 */
public class CommitteeComment {
	
	/** The comment id. */
	int commentId;
	
	/** The request id. */
	int requestId;
	
	/** The committee step id. */
	int committeeStepId;
	
	/** The employee user name. */
	String employeeUserName;
	
	/** The comment. */
	String comment;
	
	/**
	 * Instantiates a new committee comment.
	 *
	 * @param commentId the comment id
	 * @param requestId the request id
	 * @param committeeStepId the committee step id
	 * @param employeeUserName the employee user name
	 * @param comment the comment
	 */
	public CommitteeComment(int commentId, int requestId, int committeeStepId, String employeeUserName,
			String comment) {
		super();
		this.commentId = commentId;
		this.requestId = requestId;
		this.committeeStepId = committeeStepId;
		this.employeeUserName = employeeUserName;
		this.comment = comment;
	}

	/**
	 * Instantiates a new committee comment.
	 *
	 * @param requestId the request id
	 * @param userName the user name
	 * @param comment the comment
	 */
	public CommitteeComment(int requestId, String userName, String comment) {
		super();
		this.requestId = requestId;
		this.employeeUserName = userName;
		this.comment = comment;
	}
	
	/**
	 * Instantiates a new committee comment.
	 *
	 * @param commentId the comment id
	 * @param requestId the request id
	 * @param userName the user name
	 * @param comment the comment
	 */
	public CommitteeComment(int commentId, int requestId, String userName, String comment) {
		super();
		this.commentId = commentId;
		this.requestId = requestId;
		this.employeeUserName = userName;
		this.comment = comment;
	}

	/**
	 * Gets the comment id.
	 *
	 * @return the comment id
	 */
	public int getCommentId() {
		return commentId;
	}
	
	/**
	 * Sets the comment id.
	 *
	 * @param commentId the new comment id
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	/**
	 * Gets the request id.
	 *
	 * @return the request id
	 */
	public int getRequestId() {
		return requestId;
	}
	
	/**
	 * Sets the request id.
	 *
	 * @param requestId the new request id
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	/**
	 * Gets the employee user name.
	 *
	 * @return the employee user name
	 */
	public String getEmployeeUserName() {
		return employeeUserName;
	}
	
	/**
	 * Sets the employee user name.
	 *
	 * @param employeeUserName the new employee user name
	 */
	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}
	
	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
