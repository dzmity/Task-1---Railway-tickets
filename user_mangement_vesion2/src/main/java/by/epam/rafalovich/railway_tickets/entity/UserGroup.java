package by.epam.rafalovich.railway_tickets.entity;

public class UserGroup extends Entity {


	private String groupName;
	private long ownerId;
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public long getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	
}
