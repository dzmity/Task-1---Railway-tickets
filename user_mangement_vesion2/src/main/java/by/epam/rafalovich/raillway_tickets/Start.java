package by.epam.rafalovich.raillway_tickets;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.rafalovich.railway_tickets.dao.UserDAO;
import by.epam.rafalovich.railway_tickets.entity.User;

public class Start {

	public static void main(String args[]) throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
		/*User user = new User();
		user.setName("dima");
		user.setSurname("reader");
		user.setEmail("234@ipam.com");
		user.setPhoneNumber("1234");
		user.setLogin("jigj");
		user.setPassword("jghkjh");
		user.setCityId(59943);
		//user.setAddress("2jkfhkjlh");
		userDAO.create(user);*/
		String s = "59941,59942,59943";
		List <User> users = (List<User>) userDAO.findByCountry(s);
		//List <User> users = (List<User>) userDAO.findByCity(59941);
		
		System.out.println(users.size());
	}

}
