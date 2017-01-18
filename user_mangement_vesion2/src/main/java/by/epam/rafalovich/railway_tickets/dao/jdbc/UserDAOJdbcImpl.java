package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import by.epam.rafalovich.railway_tickets.dao.UserDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.UserMapper;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class UserDAOJdbcImpl extends AbstractJdbcDAO implements UserDAO{

	private static final String SQL_CREATE_USER = "INSERT INTO users (name, surname, email, phone_number, login, password, city_id, address, user_role)"
			+ " VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id  WHERE user_id = ?";
	private static final String SQL_FIND_ALL_USERS = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id";
	private static final String SQL_UPDATE_USER = "UPDATE users SET name = ?, surname = ?, email = ?, phone_number = ?, login = ?,"
			+ " city_id = ?, address = ?, user_role = ? WHERE user_id = ?";
	private static final String SQL_DELETE_USER = "DELETE FROM user WHERE user_id = ?";
	
	private static final String SQL_FIND_USERS_BY_NAME = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id WHERE NAME LIKE ?";
	
	private static final String SQL_FIND_USERS_BY_SURNAME = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id WHERE surname LIKE ?";
	
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id WHERE email = ?";
	
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id WHERE login = ?";
	
	private static final String SQL_FIND_USERS_BY_CITY = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id WHERE city_id = ?";
	
	private static final String SQL_FIND_USERS_BY_REGION = "SELECT user_id, name, surname, email, phone_number, login, password, city_id, address, role_name"
			+ " FROM users INNER JOIN user_roles ON users.user_role = user_roles.role_id ";
	
	
	@Override
	public void create(User user) throws DAOException {
		
		try {
			jdbcTemplate.update(SQL_CREATE_USER, user.getName(), user.getSurname(), user.getEmail(), user.getPhoneNumber(), user.getLogin(), user.getPassword(),
					user.getCityId(), user.getAddress(), user.getUserRole().getRoleId());
		}  catch (DataAccessException e) {
			throw new DAOException("Exception in create method in UserDAO", e);
		}
		
		
	}

	@Override
	public User findById(long id) throws DAOException {
		try {
			User user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, new Object[]{id}, new UserMapper());
			return user;
		} catch(IncorrectResultSizeDataAccessException  e) {
			//log
			return null;
		} catch (DataAccessException e) {
			throw new DAOException("Exception in findById method in UserDAO", e);
		}
		
	}

	@Override
	public void updateById(long id, User user) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getSurname(), user.getEmail(), user.getPhoneNumber(), user.getLogin(), user.getCityId(),
				user.getAddress(), user.getUserRole().getRoleId(), id);
		
	}

	@Override
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_USER, id);
		
	}
	
	
	
	public Collection<User> find() throws DAOException {
		
	}
	
	
	
	
	
	

	@Override
	public Collection<User> findAll() throws DAOException {
		List <User> users = jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserMapper());
		return users;
	}
	
	
	
	

	@Override
	public boolean existLogin(String login) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existEmail(String login) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByLoginPassword(String login, String password) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePasswordById(long id, String oldPassword, String newPassword) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<User> findByName(String name) throws DAOException {
		List <User> users = jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserMapper());
		return users;
	}

	@Override
	public Collection<User> findBySurname(String surname) throws DAOException {
		List <User> users = jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserMapper());
		return users;
	}

	@Override
	public User findByLogin(String login) throws DAOException {
		return null;
	}

	@Override
	public User findByEmail(String email) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> findByCountry(String cities) throws DAOException {
		
		List <User> users = jdbcTemplate.query(SQL_FIND_USERS_BY_REGION, new Object[]{cities}, new UserMapper());
		return users;
	}

	@Override
	public Collection<User> findByCity(long cityId) throws DAOException {
		List <User> users = jdbcTemplate.query(SQL_FIND_USERS_BY_CITY, new Object[]{cityId}, new UserMapper());
		return users;
	}
	
	
	
}
