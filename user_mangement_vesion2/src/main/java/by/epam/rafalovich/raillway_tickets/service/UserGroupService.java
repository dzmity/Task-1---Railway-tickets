package by.epam.rafalovich.raillway_tickets.service;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.dao.UserGroupDAO;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.entity.UserGroup;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class UserGroupService {
	
	UserGroupDAO userGroupDAO;	

	public UserGroupService(UserGroupDAO userGroupDAO) {
		super();
		this.userGroupDAO = userGroupDAO;
	}

	public void create(UserGroup group) throws ServiceException {
		
		try {
			userGroupDAO.create(group);
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserGroupService create method.", e);
		}		
		
	}
	
	public void deleteGroup(long id) throws ServiceException {
		
		try {
			userGroupDAO.deleteById(id);
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserGroupService deleteGroup method.", e);
		}
	}
	
	public void deleteUserFromGroup(long groupId, long userId) throws ServiceException {
		
		try {
			userGroupDAO.deleteUserFromGroup(groupId ,userId);
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserGroupService deleteUserFromGroup method.", e);
		}
	}
	
	public void addUserInGroup(long groupId, long userId) throws ServiceException {
		
		try {
			userGroupDAO.addUserInGroup(groupId, userId);
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserGroupService addUserInGroup method.", e);
		}
	}

	public Collection<User> findUsersInGroup(long groupId) throws ServiceException {
		
		try {
			return userGroupDAO.findUsersInGroup(groupId);
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserGroupService findUsersInGroup method.", e);
		}
	}

	public Collection<UserGroup> findListOfGroups(long userId) throws ServiceException {
		
		try {
			return userGroupDAO.findListOfGroups(userId);
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserGroupService findListOfGroups method.", e);
		}
	}
	

}
