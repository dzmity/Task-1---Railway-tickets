package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.rafalovich.railway_tickets.entity.Document;
import by.epam.rafalovich.railway_tickets.entity.DocumentType;

public class DocumentMapper implements RowMapper<Document>{

	private static final String DOCUMENT_ID = "DOCUMENT_ID";
	private static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
	private static final String USER_ID = "USER_ID";
	private static final String DOCUMENT_DATA = "DOCUMENT_DATA";
	
	@Override
	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
		Document document = new Document();
		document.setId(rs.getLong(DOCUMENT_ID));
		document.setData(rs.getString(DOCUMENT_DATA));
		document.setUserId(rs.getLong(USER_ID));
		DocumentType type = DocumentType.valueOf(rs.getString(DOCUMENT_TYPE).toUpperCase());
		document.setDocumentType(type);
		return document;
	}
	
}
