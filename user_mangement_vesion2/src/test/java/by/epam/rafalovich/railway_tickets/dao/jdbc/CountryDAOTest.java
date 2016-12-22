package by.epam.rafalovich.railway_tickets.dao.jdbc;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
//import org.unitils.inject.annotation.TestedObject;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import by.epam.rafalovich.railway_tickets.dao.CountryDAO;
import by.epam.rafalovich.railway_tickets.entity.Country;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

//@DatabaseTearDown("classpath:dbunit/countryDataSet.xml") // not required
//@DataSet(value ="dbunit/countryDataSet.xml", loadStrategy = CleanInsertLoadStrategy.class)  // don't work
//@Transactional(TransactionMode.ROLLBACK)

/*@RunWith( SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testbeans.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection="dataSource")
@DatabaseSetup(value="classpath:dbunit/countryDataSet.xml")*/

@SpringApplicationContext("testbeans.xml")

public class CountryDAOTest extends UnitilsJUnit4{	
 
	@SpringBean("countryDAO")
	private CountryDAO countryDAO;
	
	private Country initTestCountry() {
		Country country = new Country();
		country.setName("Test country");
		return country;
	}
	
	@Test	
	public void testFindById() throws DAOException{
		
		Country country = countryDAO.findById(0);
		assertPropertyLenientEquals("name", "Russia", country);
	}

	@Test
	public void testCreateWithTrigger() throws DAOException{
		
		Country country = initTestCountry();		
		countryDAO.create(country);
		countryDAO.create(country);
		assertEquals("Test country", countryDAO.findById(278).getName());
		assertEquals("Test country", countryDAO.findById(279).getName());
	}	


	@Test
	@ExpectedDataSet("dbunit/countryUpdateTest.xml")
	public void testUpdateById()  throws DAOException{
		Country country = initTestCountry();		
		countryDAO.updateById(0, (country));
	}

	@Test
	@ExpectedDataSet("dbunit/countryDeleteTest.xml")
	public void testDeleteById() throws DAOException{
		
		countryDAO.deleteById(4);		
	}

	
	@Test		
	public void testFindAll() throws DAOException {
		List<Country> result = (List) countryDAO.findAll();
        assertPropertyLenientEquals("name", Arrays.asList("Russia", "Ukrain", "Belarus", "Australia", "Belgium"), result);
	}

}
