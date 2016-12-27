package by.epam.rafalovich.railway_tickets.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.epam.rafalovich.raillway_tickets.service.UserGroupService;
import by.epam.rafalovich.railway_tickets.dao.UserGroupDAO;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.entity.UserGroup;
import by.epam.rafalovich.railway_tickets.entity.UserRole;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class UserGroupServiceTest {

	private UserGroupService userGroupService;
	@Mock
	private UserGroupDAO userGroupDAO;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		userGroupService = new UserGroupService(userGroupDAO);
	}
	
	private UserGroup createGroup() {
		
		String groupName = "Test Group";
		long ownerId = 1;		
		
		UserGroup userGroup = new UserGroup();
		userGroup.setGroupName(groupName);
		userGroup.setOwnerId(ownerId);
		return userGroup;
	}
	
	private Collection<User> createUserCollection() {
		
		String name = "Test user";
		String surname = " Test surname";
		String email = "Test email";
		String phoneNumber = "1234567";
		String login = "Test login";			 
		String address = "Test address";
		UserRole userRole = UserRole.USER;	
		
		List<User> users = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			
			User user = new User();
			user.setId(i);
			user.setCityId(i);
			user.setAddress(address);
			user.setEmail(email);
			user.setLogin(login);
			user.setName(name);
			user.setSurname(surname);
			user.setPhoneNumber(phoneNumber);
			user.setUserRole(userRole);
			
			users.add(user);
		}
		return users;
	}
	
	@Test
	public void testCreate() throws ServiceException, DAOException {			
				
		UserGroup group = createGroup();		
		userGroupService.create(group);
		verify(userGroupDAO, times(1)).create(group);
		verifyNoMoreInteractions( userGroupDAO );		
	}
	
	@Test
	public void testDeleteGroup() throws ServiceException, DAOException {			
		
		long id = 2;
				
		userGroupService.deleteGroup(id);
		verify(userGroupDAO, times(1)).deleteById(id);
		verifyNoMoreInteractions( userGroupDAO );	
	}
	
	@Test
	public void testUpdateGroup() throws ServiceException, DAOException {			
		
		long id = 2;
		UserGroup group = createGroup();
		
		userGroupService.update(id, group);
		verify(userGroupDAO, times(1)).updateById(id, group);
		verifyNoMoreInteractions( userGroupDAO );	
	}
	
	@Test
	public void testDeleteUserFromGroup() throws ServiceException, DAOException {			
		
		long userId = 2;
		long groupId = 3;
		
		userGroupService.deleteUserFromGroup(groupId, userId);
		verify(userGroupDAO, times(1)).deleteUserFromGroup(groupId, userId);
		verifyNoMoreInteractions( userGroupDAO );			
	}
	
	@Test
	public void testAddUserInGroup() throws ServiceException, DAOException {			
		
		long userId = 2;
		long groupId = 3;
		
		userGroupService.addUserInGroup(groupId, userId);
		verify(userGroupDAO, times(1)).addUserInGroup(groupId, userId);
		verifyNoMoreInteractions( userGroupDAO );	
	}
	
	@Test
	public void testFindUsersInGroup() throws ServiceException, DAOException {			
		
		long groupId = 3;
		Collection<User> expected = createUserCollection();
		when ( userGroupDAO.findUsersInGroup(groupId) ).thenReturn( expected );
		Collection<User> result = userGroupService.findUsersInGroup(groupId);
		assertTrue(CollectionUtils.isEqualCollection(expected, result));
		verify(userGroupDAO, times(1)).findUsersInGroup(groupId);
		verifyNoMoreInteractions( userGroupDAO );	
	}
	
	@Test
	public void testFindListOfGroups() throws ServiceException, DAOException {			
		
		long userId = 1;
		
		/*long anotherId = 5;
		UserGroup group3 = createGroup();
		group3.setOwnerId(anotherId);*/
		
		UserGroup group1 = createGroup();
		UserGroup group2 = createGroup();
		group2.setGroupName("Test group 2");
		
		
		Collection<UserGroup> expected = new ArrayList<>();
		expected.add(group1);
		expected.add(group2);
		
		when ( userGroupDAO.findListOfGroups(userId) ).thenReturn( expected );
		Collection<UserGroup> result = userGroupService.findListOfGroups(userId);
		assertTrue(CollectionUtils.isEqualCollection(expected, result));
		verify(userGroupDAO, times(1)).findListOfGroups(userId);
		verifyNoMoreInteractions( userGroupDAO );	
	}	

}
