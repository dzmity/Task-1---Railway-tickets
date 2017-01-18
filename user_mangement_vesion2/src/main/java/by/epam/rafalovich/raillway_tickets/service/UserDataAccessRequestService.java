package by.epam.rafalovich.raillway_tickets.service;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.dao.UserDataAccessRequestDAO;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class UserDataAccessRequestService {

	private UserDataAccessRequestDAO requestDAO;

	public UserDataAccessRequestService(UserDataAccessRequestDAO requestDAO) {
		super();
		this.requestDAO = requestDAO;
	}
	
	public void createRequest(UserDataAccessRequest request) throws ServiceException {
		
		try{
			requestDAO.create(request);
		} catch (DAOException e) {
			throw new ServiceException("Exception in UserDataAccessRequestService createRequest method.", e);
		}
	}
	
	public void updateRequest(UserDataAccessRequest request) throws ServiceException {
		
		long id = request.getId();
		
		try{
			requestDAO.updateById(id, request);
		} catch (DAOException e) {
			throw new ServiceException("Exception in UserDataAccessRequestService updateRequest method.", e);
		}
	}
	
	public void deleteRequest(long id) throws ServiceException {
		
		try{
			requestDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException("Exception in UserDataAccessRequestService deleteRequest method.", e);
		}
	}
	
	public Collection<UserDataAccessRequest> findRequestsByUserId(long id) throws ServiceException {
		
		try{
			return requestDAO.findRequestsByUserId(id);
		} catch (DAOException e) {
			throw new ServiceException("Exception in UserDataAccessRequestService findRequestsByUserId method.", e);
		}
	}
	
	public Collection<UserDataAccessRequest> findIncomingRequests(long id) throws ServiceException {
		
		try{
			return requestDAO.findIncomingRequests(id);
		} catch (DAOException e) {
			throw new ServiceException("Exception in UserDataAccessRequestService findIncomingRequests method.", e);
		}
	}
	
	public Collection<UserDataAccessRequest> findOutcomingRequests(long id) throws ServiceException {
		
		try{
			return requestDAO.findOutcomingRequests(id);
		} catch (DAOException e) {
			throw new ServiceException("Exception in UserDataAccessRequestService findOutcomingRequests method.", e);
		}
	}
}
