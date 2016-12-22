package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import by.epam.rafalovich.railway_tickets.dao.UserDataAccessRequestDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.UserDataAccessRequestMapper;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class UserRequestDAOJdbcImpl extends AbstractJdbcDAO implements UserDataAccessRequestDAO{
	
	private static final String SQL_CREATE_REQUEST = "INSERT INTO requests (group_id, request_recipient_id, request_status) VALUES (?,?,?)";
	private static final String SQL_FIND_REQUEST_BY_ID = "SELECT request_id, group_id, request_recipient_id, request_date_type,"
			+ "type_name FROM requests INNER JOIN request_statuses ON request_status = type_id WHERE request_id = ?";
	/*private static final String SQL_FIND_ALL_REQUEST = "SELECT request_id, group_id, request_recipient_id, request_date_type,"
			+ "type_name FROM requests INNER JOIN request_statuses ON request_status = type_id";*/
	private static final String SQL_UPDATE_REQUEST_STATUS = "UPDATE requests SET request_status = ? WHERE request_id = ?";
	private static final String SQL_DELETE_REQUEST = "DELETE FROM requests WHERE request_id = ?";

	@Override
	public void create(UserDataAccessRequest request) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_REQUEST, request.getGroupId(), request.getRecepientId(), request.getRequestStatus());
		
	}

	@Override
	public UserDataAccessRequest findById(long id) throws DAOException {
		UserDataAccessRequest request = jdbcTemplate.queryForObject(SQL_FIND_REQUEST_BY_ID, new Object[]{id}, new UserDataAccessRequestMapper());
		return request;
	}

	@Override
	public void updateById(long id, UserDataAccessRequest request) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_REQUEST_STATUS, request.getRequestStatus().getStatusId());
		
	}

	@Override
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_REQUEST, id);
		
	}	

	
}
