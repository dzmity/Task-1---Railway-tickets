package by.epam.rafalovich.railway_tickets.entity;

public enum DocumentType {

	PASSPORT(1),BIRTH_CERTIFICATE(2);
	
	private int typeId;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	private DocumentType(int typeId) {
		this.typeId = typeId;
	}
	
	
	
}
