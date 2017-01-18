package by.epam.rafalovich.raillway_tickets.service;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.dao.CityDAO;
import by.epam.rafalovich.railway_tickets.entity.City;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class CitySetvice {

	private CityDAO cityDAO;

	public CitySetvice(CityDAO cityDAO) {		
		this.cityDAO = cityDAO;
	}
	
	public Collection<City> findCitiesByRegionId(long regionId) throws ServiceException {
		
		try{
			return cityDAO.findCitiesByRegionId(regionId);
		} catch (DAOException e) {
			throw new ServiceException("Exception in CityService findCitiesByRegionId method.", e);
		}
	}
	
}
