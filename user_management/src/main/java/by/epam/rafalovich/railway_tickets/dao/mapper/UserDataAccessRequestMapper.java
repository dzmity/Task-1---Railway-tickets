package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import by.epam.rafalovich.railway_tickets.entity.RequestStatus;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;

public class UserDataAccessRequestMapper implements RowMapper<UserDataAccessRequest>{

	private static final String REQUEST_ID = "REQUEST_ID";
	private static final String GROUP_ID = "GROUP_ID";
	private static final String REQUEST_DATE_TIME = "REQUEST_DATE_TIME";
	private static final String REQUEST_RECIPIENT_ID = "REQUEST_RECIPIENT_ID";
	private static final String REQUEST_STATUS = "TYPE_NAME";
			
	
	@Override
	public UserDataAccessRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDataAccessRequest request = new  UserDataAccessRequest();
		request.setId(rs.getLong(REQUEST_ID));
		request.setGroupId(rs.getLong(GROUP_ID));
		request.setDateTime(rs.getTimestamp(REQUEST_DATE_TIME).toLocalDateTime());
		request.setRecepientId(rs.getLong(REQUEST_RECIPIENT_ID));
		RequestStatus status = RequestStatus.valueOf(rs.getString(REQUEST_STATUS));
		request.setRequestStatus(status);
		return request;
	}
	
}
