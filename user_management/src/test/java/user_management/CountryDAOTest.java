package user_management;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;

import by.epam.rafalovich.railway_tickets.dao.CountryDAO;
import by.epam.rafalovich.railway_tickets.entity.Country;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

//@DataSet(value ="/dbunit/countryDataSet.xml") //
//@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class CountryDAOTest extends UnitilsJUnit4{
	
	private static CountryDAO countryDAO;
	private static ApplicationContext ctx;
	private static Savepoint mySavepoin;
	
	@BeforeClass
	public static void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("testbeans.xml");
		countryDAO = (CountryDAO)ctx.getBean("countryDAO");
		Connection connection = null;
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		try {
			connection = ds.getConnection();
	} catch (SQLException e) {
        connection.rollback();
        throw new Exception(e);
    }		
		mySavepoin = connection.setSavepoint("MYSAVEPOINT");
	}
	
	@Before
	public  void setU2p() throws Exception {
		//rollback (mySavepoint);
	}
	/*@After
	public void tearDown() {
	    try {
	        clearDatabase();
	    } catch (Exception e) {
	        fail(e.getMessage());
	    }
	}*/


	/*public void clearDatabase() throws Exception {
	  DataSource ds = (DataSource) ctx.getBean("dataSource");
	  Connection connection = null;
	  try {
	    connection = ds.getConnection();
	    try {
	      Statement stmt = connection.createStatement();
	      try {
	        stmt.execute("ROLLBACK TO SAVEPOINT SP");
	        connection.commit();
	      } finally {
	        stmt.close();
	      }
	    } catch (SQLException e) {
	        connection.rollback();
	        throw new Exception(e);
	    }
	    } catch (SQLException e) {
	        throw new Exception(e);
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}
	
	private Country initTestCountry() {
		Country country = new Country();
		country.setName("Test country");
		return country;
	}*/
	
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
		assertEquals("Test country", countryDAO.findById(238).getName());
		assertEquals("Test country", countryDAO.findById(239).getName());
	}	


	@Test
	@ExpectedDataSet({"dbunit/countryUpdate.xml"})
	public void testUpdateById()  throws DAOException{
		Country country = initTestCountry();		
		countryDAO.updateById(0, (country));
	}

	@Test		
	@ExpectedDataSet({"dbunit/expectedCountriesAfterDelete.xml"})
	public void testDeleteById() throws DAOException{
		
		countryDAO.deleteById(4);		
	}

	
	@Test
	public void testFindAll() throws DAOException {
		List<Country> result = (List) countryDAO.findAll();
        assertPropertyLenientEquals("name", Arrays.asList("Russia", "Ukrain", "Belarus", "Australia", "Belgium"), result);
	}

}
