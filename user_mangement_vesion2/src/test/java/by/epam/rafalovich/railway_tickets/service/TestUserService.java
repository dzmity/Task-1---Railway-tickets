package by.epam.rafalovich.railway_tickets.service;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import by.epam.rafalovich.raillway_tickets.service.UserService;
import by.epam.rafalovich.railway_tickets.dao.UserDAO;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.entity.UserRole;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class TestUserService {
	
	private static final String USER_ID = "userId";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String PASS = "password";
    private static final String CONFIRM = "confirm";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String CITY_ID = "cityId";
    private static final String ROLE = "role";
    private static final String ADDRESS = "address";

	private UserService userService;
	@Mock
	private UserDAO userDAO;
	
	@Before
    public void setup() {
		
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDAO);
    }
	
	
	private HashMap<String,String> initMap() {
		
		HashMap< String, String> param = new HashMap<>();
		param.put(NAME,"dimas");
		param.put(SURNAME,"rafalovich");
		param.put(EMAIL,"email@gmail.com");
		param.put(LOGIN,"dimas");
		param.put(PASS,"qwerty123");
		param.put(PHONE_NUMBER ,"336012781");
		param.put(CITY_ID ,"12");
		param.put(ADDRESS,"222310 Molodechno");
		return param;
	}
	
	private User createUser(Map<String, String> param) {
		
		User user = new User();
		user.setName(param.get(NAME));
		user.setSurname(param.get(SURNAME));
		user.setEmail(param.get(EMAIL));
		user.setLogin(param.get(LOGIN));
		
		if (param.get(PASS) != null) {
			user.setPassword(DigestUtils.md5Hex(param.get(PASS)));
		}
		
		user.setPhoneNumber(param.get(PHONE_NUMBER));
		user.setCityId(new Long(param.get(CITY_ID)));
		user.setAddress(param.get(ADDRESS));
		
		if (param.get(ROLE) != null) {
			user.setUserRole(UserRole.valueOf(param.get(ROLE).toUpperCase()));
		} else {
			user.setUserRole(UserRole.USER);
		}		
		return user;		
	}
	
	@Test
	public void testRegister() throws ServiceException, DAOException {
		
		User user = new User();
		
		String login = "dima";
		String email = "email@gmail.com";		
		String login2 = "dimas";
		String email2 = "1email@gmail.com";
		
		HashMap< String,String> param = initMap();
		
		when ( userDAO.existLogin(login) ).thenReturn( false );
		when ( userDAO.existEmail(email) ).thenReturn( false );
		
		when ( userDAO.existLogin(login2) ).thenReturn( true );
		when ( userDAO.existEmail(email2) ).thenReturn( true );
		
		boolean result = userService.register(param);
		assertFalse(result);
		verify(userDAO, never()).create(user);
		
		param.put(LOGIN,login);
		param.put(EMAIL,email2);		
		result = userService.register(param);
		assertFalse(result);
		verify(userDAO, never()).create(user);
		
		param.put(LOGIN,login2);
		result = userService.register(param);
		assertTrue(result);		
		user = createUser(param);			
		verify(userDAO, times(1)).create(user);
		
	}

	@Test
	public void testAuthorize() throws ServiceException, DAOException {
		
		String login = "dima";
		String pass = "qwerty123";	
		String md5pass = DigestUtils.md5Hex(pass);
				
		HashMap< String,String> param = initMap();
		User user = createUser(param);		
		
		when ( userDAO.findByLoginPassword(login, md5pass) ).thenReturn( user );
		User result = userService.authorize(login, pass);
		assertEquals(user, result);
		verify(userDAO, times(1)).findByLoginPassword(login, md5pass);
		verifyNoMoreInteractions( userDAO );
	}

	@Test
	public void testChangePassword() throws ServiceException, DAOException {
				
		long id = 5;
		long anotherId = 3;
		String wrongPass = "1234567";
		String newPass = "12345Qwerty";
		String oldPass = "qwerty123";
		String md5oldPass = DigestUtils.md5Hex(oldPass);
		String md5newPass = DigestUtils.md5Hex(newPass);
		String md5wrongPass = DigestUtils.md5Hex(wrongPass);		
		
		HashMap< String,String> param = initMap();
		User user = createUser(param);		
		user.setId(id);				
		
		when ( userDAO.changePasswordById(id, md5oldPass, md5newPass)).thenReturn( true );
		boolean result = userService.changePassword(user, oldPass, newPass);
		verify(userDAO, times(1)).changePasswordById(id, md5oldPass, md5newPass);		
		assertTrue(result);
		
		result = userService.changePassword(user, wrongPass, newPass);
		verify(userDAO, times(1)).changePasswordById(id, md5wrongPass, md5newPass);
		assertFalse(result);
		
		
		user.setId(anotherId);
		result = userService.changePassword(user, oldPass, newPass);
		verify(userDAO, times(1)).changePasswordById(anotherId, md5oldPass, md5newPass);
		assertFalse(result);
		
		verifyNoMoreInteractions( userDAO );		
		
	}

	@Test
	public void testEditProfile() throws ServiceException, DAOException {
		
		long id = 5;
		HashMap< String,String> param = initMap();
		param.put(USER_ID, "" + id);		
		User user = createUser(param);	
		user.setId(id);
		userService.editProfile(param);
		verify(userDAO).updateById(id, user);
		
		verifyNoMoreInteractions( userDAO );	
	}

}
