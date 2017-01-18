package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface UserDataAccessRequestDAO extends GenericDAO<UserDataAccessRequest>{

	@Override
	default Collection<UserDataAccessRequest> findAll() throws DAOException {
		throw new DAOException("Unsopported opperation.");
	}

	Collection<UserDataAccessRequest> findRequestsByUserId(long id) throws DAOException;
	
	Collection<UserDataAccessRequest> findIncomingRequests(long id) throws DAOException;
	
	Collection<UserDataAccessRequest> findOutcomingRequests(long id) throws DAOException;
}
