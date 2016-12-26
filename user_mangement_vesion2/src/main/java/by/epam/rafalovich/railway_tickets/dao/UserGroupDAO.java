package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.entity.UserGroup;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface UserGroupDAO extends GenericDAO<UserGroup>{
	
	void deleteUserFromGroup(long groupId, long userId) throws DAOException;
	
	void addUserInGroup(long groupId, long userId) throws DAOException;
	
	Collection<User> findUsersInGroup(long groupId) throws DAOException;
	
	Collection<UserGroup> findListOfGroups(long userId) throws DAOException;	
}
