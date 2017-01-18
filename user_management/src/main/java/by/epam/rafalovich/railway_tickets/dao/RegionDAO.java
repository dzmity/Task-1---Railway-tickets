package by.epam.rafalovich.railway_tickets.dao;

import java.util.Collection;
import by.epam.rafalovich.railway_tickets.entity.Region;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public interface RegionDAO extends GenericDAO<Region>{
	
	Collection<Region> findByCountryId(long id) throws DAOException;
	
}