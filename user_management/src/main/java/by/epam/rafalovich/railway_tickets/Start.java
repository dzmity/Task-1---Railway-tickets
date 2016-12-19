package by.epam.rafalovich.railway_tickets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.rafalovich.railway_tickets.dao.CountryDAO;
import by.epam.rafalovich.railway_tickets.dao.CountryDAOJdbcImpl;
import by.epam.rafalovich.railway_tickets.entity.Country;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class Start {
	public static void main(String args[]) throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		CountryDAO countryDAO = (CountryDAO)ctx.getBean("countryDAO");
		Country country = new Country();
		country.setName("gjk111hjk");
		//country.setId(240);
		countryDAO.create(country);
	}
}
