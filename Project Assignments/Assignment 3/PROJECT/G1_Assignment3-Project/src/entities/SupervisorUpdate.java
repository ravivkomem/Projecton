package entities;

import java.sql.Date;

public class SupervisorUpdate {
	
	private int updateId;
	private int changerRequestId;
	private String supervisorUserName;
	private String essence;
	private Date updateDate;
	private String fullName;
	


	public SupervisorUpdate(int updateId, int changerRequestId, String supervisorUserName, String essence,
			Date updateDate, String fullName) {
		this.updateId = updateId;
		this.changerRequestId = changerRequestId;
		this.supervisorUserName = supervisorUserName;
		this.essence = essence;
		this.updateDate = updateDate;
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public int getChangerRequestId() {
		return changerRequestId;
	}

	public void setChangerRequestId(int changerRequestId) {
		this.changerRequestId = changerRequestId;
	}

	public String getSupervisorUserName() {
		return supervisorUserName;
	}

	public void setSupervisorUserName(String supervisorUserName) {
		this.supervisorUserName = supervisorUserName;
	}

	public String getEssence() {
		return essence;
	}

	public void setEssence(String essence) {
		this.essence = essence;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
