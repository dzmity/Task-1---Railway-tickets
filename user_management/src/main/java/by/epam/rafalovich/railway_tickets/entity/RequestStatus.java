package by.epam.rafalovich.railway_tickets.entity;

public enum RequestStatus {

	OPEN(1), ACCEPTED(2), DISCARTED(3);
	
	private int statusId;	

	public int getStatusId() {
		return statusId;
	}

	private RequestStatus(int statusId) {
		this.statusId = statusId;
	}	
	
}
