package by.epam.rafalovich.raillway_tickets.service;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.dao.RegionDAO;
import by.epam.rafalovich.railway_tickets.entity.Region;
import by.epam.rafalovich.railway_tickets.exception.DAOException;
import by.epam.rafalovich.railway_tickets.exception.ServiceException;

public class RegionService {

	private RegionDAO regionDAO;

	public RegionService(RegionDAO regionDAO) {		
		this.regionDAO = regionDAO;
	}
	
	public Collection<Region> findRegionsByCountryId(long id) throws ServiceException{
		
		try {
			return regionDAO.findByCountryId(id);
		} catch(DAOException e) {
			throw new ServiceException("Exceprion in RegionService getRegionsByCountryId method.", e);
		}
	}	
	
}
