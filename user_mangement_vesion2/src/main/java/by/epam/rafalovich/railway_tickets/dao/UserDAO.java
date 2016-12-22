package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface UserDAO extends GenericDAO<User>{

	boolean existLogin(String login)  throws DAOException;
	
	boolean existEmail(String login)  throws DAOException;
	
	User findByLoginPassword(String login, String password) throws DAOException;
	
	boolean changePasswordById(long id, String oldPassword, String newPassword) throws DAOException;
	
	
	
	
	public Collection<User> findByName(String name) throws DAOException;
	
	public Collection<User> findBySurname(String surname) throws DAOException;
	
	public User findByLogin(String login) throws DAOException;
	
	public User findByEmail(String email) throws DAOException;
	
	public Collection<User> findByCountry(String cities) throws DAOException;
	
	public Collection<User> findByCity(long cityId) throws DAOException;	
	
}
