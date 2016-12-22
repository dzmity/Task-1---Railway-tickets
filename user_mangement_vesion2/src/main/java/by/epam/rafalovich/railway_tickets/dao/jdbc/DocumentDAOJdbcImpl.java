package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import java.util.List;

import by.epam.rafalovich.railway_tickets.dao.DocumentDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.DocumentMapper;
import by.epam.rafalovich.railway_tickets.entity.Document;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class DocumentDAOJdbcImpl extends AbstractJdbcDAO implements DocumentDAO{
	
	private static final String SQL_CREATE_DOCUMENT = "INSERT INTO documents (document_type, document_data, user_id) VALUES (?,?,?)";
	private static final String SQL_FIND_DOCUMENT_BY_ID = "SELECT document_id, document_type, document_data, user_id FROM documents WHERE document_id = ?";
	private static final String SQL_FIND_ALL_DOCUMENTS = "SELECT document_id, document_type, document_data, user_id FROM documents";
	private static final String SQL_UPDATE_DOCUMENT = "UPDATE documents SET document_type = ?, document_data = ? user_id = ? WHERE document_id = ?";
	private static final String SQL_DELETE_DOCUMENT = "DELETE FROM documents WHERE document_id = ?";
	
	@Override
	public void create(Document document) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_DOCUMENT, document.getDocumentType().toString(), document.getData(), document.getUserId());
		
	}
	
	@Override
	public Document findById(long id) throws DAOException {
		Document document = jdbcTemplate.queryForObject(SQL_FIND_DOCUMENT_BY_ID, new Object[]{id}, new DocumentMapper());
		return document;
	}
	
	@Override
	public void updateById(long id, Document document) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_DOCUMENT, document.getDocumentType().toString(), document.getData(), document.getUserId(), id);
		
	}
	
	@Override
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_DOCUMENT, id);
		
	}
	
	@Override
	public Collection<Document> findAll() throws DAOException {
		List<Document> documents = jdbcTemplate.query(SQL_FIND_ALL_DOCUMENTS, new DocumentMapper());
		return documents;
	}
	
	
}
