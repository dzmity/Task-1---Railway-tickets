package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface UserDataAccessRequestDAO extends GenericDAO<UserDataAccessRequest>{

	@Override
	default Collection<UserDataAccessRequest> findAll() throws DAOException {
		throw new DAOException("Unsopported opperation.");
	}

	
}
