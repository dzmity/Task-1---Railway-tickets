package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.entity.UserRole;

public class UserMapper implements RowMapper<User>{
	
	private static final String USER_ID = "USER_ID";
	private static final String NAME = "NAME";
	private static final String SURNAME = "SURNAME";
	private static final String EMAIL = "EMAIL";
	private static final String PHONE_NUMBER = "PHONE_NUMBER";
	private static final String LOGIN = "LOGIN";	
	private static final String CITY_ID = "CITY_ID";
	private static final String ADDRESS = "ADDRESS";
	private static final String ROLE_NAME = "ROLE_NAME";

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getLong(USER_ID));
		user.setName(rs.getString(NAME));
		user.setSurname(rs.getString(SURNAME));
		user.setEmail(rs.getString(EMAIL));
		user.setPhoneNumber(rs.getString(PHONE_NUMBER));
		user.setLogin(rs.getString(LOGIN));
		user.setCityId(rs.getLong(CITY_ID));
		user.setAddress(rs.getString(ADDRESS));
		UserRole role = UserRole.valueOf(rs.getString(ROLE_NAME).toUpperCase());
		user.setUserRole(role);
		return user;
	}	
	
}
