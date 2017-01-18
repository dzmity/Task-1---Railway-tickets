package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import by.epam.rafalovich.railway_tickets.dao.UserDataAccessRequestDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.UserDataAccessRequestMapper;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class UserRequestDAOJdbcImpl extends AbstractJdbcDAO implements UserDataAccessRequestDAO{
	
	private static final String SQL_CREATE_REQUEST = "INSERT INTO requests (group_id, request_recipient_id, request_status) VALUES (?,?,?)";
	
	private static final String SQL_FIND_REQUEST_BY_ID = "SELECT request_id, group_id, request_recipient_id, request_date_type,"
			+ " type_name FROM requests"
			+ " INNER JOIN request_statuses ON request_status = type_id"
			+ " WHERE request_id = ?";
	
	private static final String SQL_UPDATE_REQUEST_STATUS = "UPDATE requests SET request_status = ? WHERE request_id = ?";
	
	private static final String SQL_DELETE_REQUEST = "DELETE FROM requests WHERE request_id = ?";
	
	private static final String SQL_FIND_REQUEST_BY_USER_ID = "SELECT request_id, group_id, request_recipient_id, request_date_type,"
			+ " type_name FROM requests"
			+ " INNER JOIN request_statuses ON request_status = type_id"
			+ " NATURAL JOIN groups ON group_id "
			+ " WHERE recipient_id = ? or group_owner_id = ?";
	
	private static final String SQL_FIND_REQUEST_BY_OWNER_ID = "SELECT request_id, group_id, request_recipient_id, request_date_type,"
			+ " type_name FROM requests"
			+ " INNER JOIN request_statuses ON request_status = type_id"
			+ " NATURAL JOIN groups ON group_id "
			+ " WHERE group_owner_id = ?";
	
	private static final String SQL_FIND_REQUEST_BY_RECIPIENT_ID = "SELECT request_id, group_id, request_recipient_id, request_date_type,"
			+ "type_name FROM requests"
			+ " INNER JOIN request_statuses ON request_status = type_id"
			+ " WHERE recipient_id = ?";
	
	@Override
	public void create(UserDataAccessRequest request) throws DAOException {
		
		try {
			
			jdbcTemplate.update(SQL_CREATE_REQUEST, request.getGroupId(), request.getRecepientId(), request.getRequestStatus());
		} catch(DataAccessException e) {
			throw new DAOException("Exception in UserRequestDAOJdbcImpl findOutcomingRequests method.", e);
		}		
	}

	@Override
	public UserDataAccessRequest findById(long id) throws DAOException {
		
		try {
			
			UserDataAccessRequest request = jdbcTemplate.queryForObject(SQL_FIND_REQUEST_BY_ID, new Object[]{id}, new UserDataAccessRequestMapper());
			return request;				
		} catch(DataAccessException e) {
				throw new DAOException("Exception in UserRequestDAOJdbcImpl findById method.", e);
		}		
	}

	@Override
	public void updateById(long id, UserDataAccessRequest request) throws DAOException {
		
		try {
			
			jdbcTemplate.update(SQL_UPDATE_REQUEST_STATUS, request.getRequestStatus().getStatusId());
		} catch(DataAccessException e) {
			throw new DAOException("Exception in UserRequestDAOJdbcImpl updateById method.", e);
		}		
	}

	@Override
	public void deleteById(long id) throws DAOException {
		
		try {
			
			jdbcTemplate.update(SQL_DELETE_REQUEST, id);
		} catch(DataAccessException e) {
			throw new DAOException("Exception in UserRequestDAOJdbcImpl deleteById method.", e);
		}				
	}

	@Override
	public Collection<UserDataAccessRequest> findRequestsByUserId(long id) throws DAOException {

		try {
			
			return jdbcTemplate.query(SQL_FIND_REQUEST_BY_USER_ID, new Object[]{id, id},  new UserDataAccessRequestMapper());
		} catch(DataAccessException e) {
			throw new DAOException("Exception in UserRequestDAOJdbcImpl findRequestsByUserId method.", e);
		}				
	}

	@Override
	public Collection<UserDataAccessRequest> findIncomingRequests(long id) throws DAOException {
		
		try {
			
			return jdbcTemplate.query(SQL_FIND_REQUEST_BY_RECIPIENT_ID, new Object[]{id},  new UserDataAccessRequestMapper());
		} catch(DataAccessException e) {
			throw new DAOException("Exception in UserRequestDAOJdbcImpl findIncomingRequests method.", e);
		}		
	}

	@Override
	public Collection<UserDataAccessRequest> findOutcomingRequests(long id) throws DAOException {
		
		try {
			
			return jdbcTemplate.query(SQL_FIND_REQUEST_BY_OWNER_ID, new Object[]{id},  new UserDataAccessRequestMapper());
		} catch(DataAccessException e) {
			throw new DAOException("Exception in UserRequestDAOJdbcImpl findOutcomingRequests method.", e);
		}		
	}	
	
}
