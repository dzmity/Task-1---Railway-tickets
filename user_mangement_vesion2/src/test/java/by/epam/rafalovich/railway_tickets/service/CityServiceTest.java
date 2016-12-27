package by.epam.rafalovich.railway_tickets.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.epam.rafalovich.raillway_tickets.service.CitySetvice;
import by.epam.rafalovich.railway_tickets.dao.CityDAO;
import by.epam.rafalovich.railway_tickets.entity.City;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class CityServiceTest {

	
	CitySetvice cityService;
	@Mock
	CityDAO cityDAO;
	
	@Before
    public void setup() {
		
        MockitoAnnotations.initMocks(this);
        cityService = new CitySetvice(cityDAO);
    }
	
	private Collection<City> createTestCollection() {
		
		String cityName = "Test city";
		long regionId = 1;
		
		Collection<City> cities = new ArrayList<>();
		City city;
		for (int i = 0; i < 5; i++) {
			city = new City();
			city.setId(i);
			city.setCityName(cityName);	
			city.setRegionId(regionId);
		}
		return cities;
	}
	
	@Test
	public void testFindCitiesByRegionId() throws ServiceException, DAOException {
		
		long regionId = 1;
		Collection<City> cities = createTestCollection();			
		
		when ( cityDAO.findCitiesByRegionId(regionId) ).thenReturn( cities );
		
		Collection<City> results = cityService.findCitiesByRegionId(regionId);
		assertTrue(CollectionUtils.isEqualCollection(cities, results));
		verify(cityDAO, times(1)).findCitiesByRegionId(regionId);
		verifyNoMoreInteractions( cityDAO );		
	}

}
