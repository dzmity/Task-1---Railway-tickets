package by.epam.rafalovich.railway_tickets.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.epam.rafalovich.raillway_tickets.service.UserDataAccessRequestService;
import by.epam.rafalovich.railway_tickets.dao.UserDataAccessRequestDAO;

import by.epam.rafalovich.railway_tickets.entity.UserDataAccessRequest;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class UserDataAccessRequestServiceTest {

	private UserDataAccessRequestService requestService;
	@Mock
	private UserDataAccessRequestDAO requestDAO;
	
	@Before
	public void setup() {
		
        MockitoAnnotations.initMocks(this);
        requestService = new UserDataAccessRequestService(requestDAO);
    }	
	
	private UserDataAccessRequest createRequest() {
		long groupId = 1;
		LocalDateTime dateTime = LocalDateTime.now();
		long recepientId = 2;	
		
		UserDataAccessRequest request = new UserDataAccessRequest();
		request.setGroupId(groupId);
		request.setDateTime(dateTime);
		request.setRecepientId(recepientId);
		return request;
	}	

	private Collection<UserDataAccessRequest> createCollection() {
		
		List<UserDataAccessRequest> requests = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			
			UserDataAccessRequest request = createRequest();
			request.setId(i);
			request.setRecepientId(i);
			requests.add(request);
		}
		return requests;
	}
	
	@Test
	public void testCreateRequest() throws ServiceException, DAOException {

		UserDataAccessRequest request = createRequest();
		requestService.createRequest(request);
		verify(requestDAO, times(1)).create(request);
		verifyNoMoreInteractions( requestDAO );
	}

	@Test
	public void testUpdateRequest() throws ServiceException, DAOException {
		
		long requestId = 12;
		
		UserDataAccessRequest request = createRequest();
		request.setId(requestId);
		requestService.updateRequest(request);
		verify(requestDAO, times(1)).updateById(requestId, request);
		verifyNoMoreInteractions( requestDAO );
	}

	@Test
	public void testDeleteRequest() throws ServiceException, DAOException {
		
		long requestId = 12;
		
		requestService.deleteRequest(requestId);
		verify(requestDAO, times(1)).deleteById(requestId);		
		verifyNoMoreInteractions( requestDAO );
	}

	@Test
	public void testFindRequestsByUserId() throws ServiceException, DAOException {
	
		long id = 7;
		long groupId = 2;
		
		Collection<UserDataAccessRequest> expected = createCollection();
		UserDataAccessRequest request = createRequest();
		request.setGroupId(groupId);
		expected.add(request);
		when ( requestDAO.findRequestsByUserId(id) ).thenReturn( expected );
		Collection<UserDataAccessRequest> results = requestService.findRequestsByUserId(id);
		assertTrue(CollectionUtils.isEqualCollection(expected, results));
		verify(requestDAO, times(1)).findRequestsByUserId(id);	
		verifyNoMoreInteractions( requestDAO );
	}

	@Test
	public void testFindIncomingRequests() throws ServiceException, DAOException {
		
		long id = 7;
		
		Collection<UserDataAccessRequest> expected = createCollection();
		
		when ( requestDAO.findIncomingRequests(id) ).thenReturn( expected );
		Collection<UserDataAccessRequest> results = requestService.findIncomingRequests(id);
		assertTrue(CollectionUtils.isEqualCollection(expected, results));
		verify(requestDAO, times(1)).findIncomingRequests(id);	
		verifyNoMoreInteractions( requestDAO );
	}

	@Test
	public void testFindOutcomingRequests() throws ServiceException, DAOException {

		long id = 7;
				
		Collection<UserDataAccessRequest> expected = createCollection();
		
		when ( requestDAO.findOutcomingRequests(id) ).thenReturn( expected );
		Collection<UserDataAccessRequest> results = requestService.findOutcomingRequests(id);
		assertTrue(CollectionUtils.isEqualCollection(expected, results));
		verify(requestDAO, times(1)).findOutcomingRequests(id);	
		verifyNoMoreInteractions( requestDAO );
	}

}
