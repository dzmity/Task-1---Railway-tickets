package by.epam.rafalovich.raillway_tickets.service;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import by.epam.rafalovich.railway_tickets.dao.UserDAO;
import by.epam.rafalovich.railway_tickets.entity.User;
import by.epam.rafalovich.railway_tickets.entity.UserRole;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class UserService {
	
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
	
	private UserDAO userDAO;		
	
	public UserService(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
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

	public boolean register(Map<String, String> param) throws ServiceException{
		
		String email = param.get(EMAIL);
		String login = param.get(LOGIN);	
		
		try {			
			
			boolean possibilityVer = userDAO.existLogin(login) && userDAO.existEmail(email);				
			if (!possibilityVer) return possibilityVer;
			userDAO.create(createUser(param));
			return true;	
			
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserService register method.", e);
		}			
	}
	
	public User authorize(String login, String pass) throws ServiceException {
		
		User user;
		
		try {				
			
			user = userDAO.findByLoginPassword(login, DigestUtils.md5Hex(pass));
			return user;
			
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserService authorize method.", e);
		}				
	}
	
	public boolean changePassword(User user, String oldPass, String newPass) throws ServiceException {
		
		long id = user.getId();
		
		try {				
			
			return userDAO.changePasswordById(id, DigestUtils.md5Hex(oldPass), DigestUtils.md5Hex(newPass));
			
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserService changePassword method.", e);
		}	
	}
	
	public void editProfile(Map<String, String> param) throws ServiceException{
		
		long id = new Long(param.get(USER_ID));		
		User user = createUser(param);
		
		try {				
			
			userDAO.updateById(id, user);
			
		} catch(DAOException e) {
			throw new ServiceException("Exception in UserService editProfile method.", e);
		}			
	}
	
	public UserQuery query() {
		return new UserQuery(this.userDAO);
	}
	
	public class UserQuery {
		
		private UserDAO userDAO;		
		private UserFilter userFilter;

        public UserQuery(UserDAO userDAO) {        	
			
			this.userDAO = userDAO;
			userFilter = new UserFilter();
		}

		public UserQuery isName(String name) {
			
			if (name == null) return this;
			userFilter.addFilter(NAME, name);
			return this;
		}

        public UserQuery isSurname(String surname) {
        	
        	if (surname == null) return this;
			userFilter.addFilter(SURNAME, surname);
			return this;
        }

        public UserQuery hasEmail(String email) {
        	
        	if (email == null) return this;
			userFilter.addFilter(EMAIL, email);
			return this;
        }

        public UserQuery hasPhoneNumber(String phoneNumber) {
        	
        	if (phoneNumber == null) return this;
			userFilter.addFilter(PHONE_NUMBER, phoneNumber);
			return this;
        }

        public UserQuery isLogin(String login) {
        	
        	if (login == null) return this;
			userFilter.addFilter(LOGIN, login);
			return this;
        }

        public UserQuery fromCountryRegion(List<Integer> cities) {
        	
        	if (cities == null) return this;
        	
        	StringBuilder builder = new StringBuilder();
        	ListIterator<Integer> listIter = cities.listIterator();
            
            while(listIter.hasNext()){
            	builder.append(listIter.next());
            	if (listIter.nextIndex() != cities.size()) {
            		builder.append(',');
            	}
            }
            
			userFilter.addFilter(CITY_ID, builder.toString());
			return this;
        }               
        
        public UserQuery fromCity(String city_id) {
        	
        	if (city_id == null) return this;
			userFilter.addFilter(CITY_ID, city_id);
			return this;
        }

        public Collection<User> find() throws ServiceException {
        	
        	try {				
    			
    			return userDAO.find(userFilter);
    			
    		} catch(DAOException e) {
    			throw new ServiceException("Exception in UserService editProfile method.", e);
    		}	
        }

    }
	
}
