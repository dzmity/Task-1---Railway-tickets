package by.epam.rafalovich.railway_tickets.entity;

import java.time.LocalDateTime;

public class UserDataAccessRequest extends Entity{

	private long groupId;
	private LocalDateTime dateTime;
	private long recepientId;
	private RequestStatus requestStatus;
	
	public long getGroupId() {
		return groupId;
	}
	
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public long getRecepientId() {
		return recepientId;
	}
	
	public void setRecepientId(long recepientId) {
		this.recepientId = recepientId;
	}
	
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}
	
	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	
	
	
	
}
