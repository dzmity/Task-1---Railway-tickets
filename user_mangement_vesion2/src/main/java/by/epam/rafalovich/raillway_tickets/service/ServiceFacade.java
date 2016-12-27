package by.epam.rafalovich.raillway_tickets.service;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.entity.RequestStatus;
import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class ServiceFacade {

	private UserDataAccessRequestService requestService;
	private UserGroupService groupService;
	
	
	

	public void createRequest(UserDataAccessRequest request) throws ServiceException {
		
		request.setRequestStatus(RequestStatus.OPEN);
		requestService.createRequest(request);
	}
	
	//transaction
	public void acceptRequest(UserDataAccessRequest request) throws ServiceException {
		
		request.setRequestStatus(RequestStatus.ACCEPTED);
		requestService.updateRequest(request);
		
		long groupId = request.getGroupId();
		long userId = request.getRecepientId();
		groupService.addUserInGroup(groupId, userId);
	}
	
	
	public void rejectRequest(UserDataAccessRequest request) throws ServiceException {
		
		request.setRequestStatus(RequestStatus.DISCARTED);
		requestService.updateRequest(request);
	}
	
	public void deleteRequest(long id) throws ServiceException {
		
		requestService.deleteRequest(id);
	}
	
	public Collection<UserDataAccessRequest> showAllRequests(long id) throws ServiceException {
		
		return requestService.findRequestsByUserId(id);
	}
	
	public Collection<UserDataAccessRequest> showIncomingRequests(long id) throws ServiceException {
		
		return requestService.findIncomingRequests(id);
	}
	
	public Collection<UserDataAccessRequest> showOutcomingRequests(long id) throws ServiceException {
		
		return requestService.findOutcomingRequests(id);
	}
	
}
