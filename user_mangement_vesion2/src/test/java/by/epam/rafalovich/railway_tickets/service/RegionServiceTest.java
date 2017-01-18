package by.epam.rafalovich.railway_tickets.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.epam.rafalovich.raillway_tickets.service.RegionService;
import by.epam.rafalovich.railway_tickets.dao.RegionDAO;
import by.epam.rafalovich.railway_tickets.entity.Region;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class RegionServiceTest {

	RegionService regionService;
	@Mock
	RegionDAO regionDAO;
	
	@Before
    public void setup() {
		
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionDAO);
    }
	
	private Collection<Region> createTestCollection() {
		
		String regionName = "Test region";
		long countryId = 1;
		Collection<Region> regions = new ArrayList<>();
		Region region;
		for (int i = 0; i < 5; i++) {
			region = new Region();
			region.setId(i);
			region.setRegionName(regionName + i);
			region.setCountryId(countryId);
			regions.add(region);			
		}
		return regions;
	}
	
	@Test
	public void testFindRegionsByCountryId() throws ServiceException, DAOException {
		
		long countryId = 1;
		Collection<Region> regions = createTestCollection();				
		
		when ( regionDAO.findByCountryId(countryId) ).thenReturn( regions );
		
		Collection<Region> results = regionService.findRegionsByCountryId(countryId);
		assertTrue(CollectionUtils.isEqualCollection(regions, results));
		verify(regionDAO, times(1)).findByCountryId(countryId);
		verifyNoMoreInteractions( regionDAO );
	}	

}
