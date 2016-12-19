package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import java.util.List;
import by.epam.rafalovich.railway_tickets.dao.UserDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.UserMapper;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class UserDAOJdbcImpl extends AbstractJdbcDAO implements UserDAO{

	private static final String SQL_CREATE_USER = "INSERT INTO users (name, surname, email, phone_number, login, password, city_id, address, user_role)"
			+ " VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = roles.role_id  WHERE user_id = ?";
	private static final String SQL_FIND_ALL_USERS = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = roles.role_id";
	private static final String SQL_UPDATE_USER = "UPDATE users SET name = ?, surname = ?, email = ?, phone_number = ?, login = ?,"
			+ " city_id = ?, address = ?, user_role = ? WHERE user_id = ?";
	private static final String SQL_DELETE_USER = "DELETE FROM user WHERE user_id = ?";
	
	@Override
	public void create(User user) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_USER, user.getName(), user.getSurnama(), user.getEmail(), user.getPhoneNumber(), user.getLogin(), user.getPassword(),
				user.getCityId(), user.getAddress(), user.getUserRole());
		
	}

	@Override
	public User findById(long id) throws DAOException {
		User user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, new Object[]{id}, new UserMapper());
		return user;
	}

	@Override
	public void updateById(long id, User user) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getSurnama(), user.getEmail(), user.getPhoneNumber(), user.getLogin(), user.getCityId(),
				user.getAddress(), user.getUserRole(), id);
		
	}

	@Override
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_USER, id);
		
	}

	@Override
	public Collection<User> findAll() throws DAOException {
		List <User> users = jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserMapper());
		return users;
	}

	
}
