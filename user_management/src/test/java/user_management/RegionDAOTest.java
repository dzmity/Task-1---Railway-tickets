package user_management;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.unitils.reflectionassert.ReflectionAssert.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import by.epam.rafalovich.railway_tickets.dao.RegionDAO;
import by.epam.rafalovich.railway_tickets.entity.Region;
import by.epam.rafalovich.railway_tickets.exception.DAOException;


@RunWith( SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:testbeans.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection="dataSource")
@DatabaseSetup(value="classpath:dbunit/regionDataSet.xml")
public class RegionDAOTest {

	@Autowired
	private RegionDAO regionDAO;
	
	private Region initTestRegion() {
		Region region = new Region();
		region.setRegionName("test region");
		region.setCountryId(3);
		return region;
	}
	
	@Test
	@ExpectedDataSet({"dbunit/regionCreate.xml"})
	public void testCreate() throws DAOException{
		regionDAO.create(initTestRegion());
	}

	@Test
	public void testFindById() throws DAOException{
		Region region = regionDAO.findById(1);
		assertPropertyLenientEquals("regionName", "Minskaya voblast", region);
		assertPropertyLenientEquals("id", 1, region);
	}

	@Test
	public void testUpdateById() throws DAOException{
		Region region = initTestRegion();
		regionDAO.updateById(1, region);
		
	}

	@Test
	@ExpectedDataSet({"dbunit/expectedRegionsAfterDelete.xml"})
	public void testDeleteById() throws DAOException{
		regionDAO.deleteById(2);
		regionDAO.deleteById(4);
		regionDAO.deleteById(6);
	}

	@Test
	public void testFindAll() throws DAOException{
		
		List<Region> result = (List) regionDAO.findAll();
        assertPropertyLenientEquals("regionName", Arrays.asList("Minskaya voblast", "Brest. voblast", "Gomel. voblast",
        		"Mogil. voblast", "Vitebs. voblast", "Grod. voblast"), result);
        assertPropertyLenientEquals("countryId", Arrays.asList(1, 1, 1, 1, 2, 3), result);
	}

	@Test
	public void testFindByCountryId() throws DAOException{
		
		List<Region> result = (List) regionDAO.findByCountryId(1);
		assertTrue(result.size() == 4);
	}

}
