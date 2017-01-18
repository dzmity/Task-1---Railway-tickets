package by.epam.rafalovich.railway_tickets.entity;

public class Document extends Entity{

	private DocumentType documentType;
	private String data;
	private long userId;
	
	public DocumentType getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
