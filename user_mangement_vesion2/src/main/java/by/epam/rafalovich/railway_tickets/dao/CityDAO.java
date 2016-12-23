package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;

import by.epam.rafalovich.railway_tickets.entity.City;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface CityDAO extends GenericDAO<City>{
	
	Collection<City> findCitiesByRegionId(long regionId) throws DAOException;

}
