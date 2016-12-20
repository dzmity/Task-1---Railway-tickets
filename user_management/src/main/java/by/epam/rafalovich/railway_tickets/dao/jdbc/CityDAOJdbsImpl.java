package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import java.util.List;

import by.epam.rafalovich.railway_tickets.dao.CityDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.CityMapper;
import by.epam.rafalovich.railway_tickets.entity.City;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class CityDAOJdbsImpl extends AbstractJdbcDAO implements CityDAO{

	private static final String SQL_CREATE_CITY = "INSERT INTO cities (city_name, region_id) VALUES (?,?)";
	private static final String SQL_FIND_CITY_BY_ID = "SELECT city_name, city_id, region_id FROM cities WHERE city_id = ?";
	private static final String SQL_FIND_ALL_CITIES = "SELECT city_name, city_id, region_id FROM cities";
	private static final String SQL_UPDATE_CITY = "UPDATE cities SET city_name = ?, region_id = ? WHERE city_id = ?";
	private static final String SQL_DELETE_CITY = "DELETE FROM cities WHERE city_id = ?";
	
	@Override
	public void create(City city) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_CITY, city.getCityName());
		
	}

	@Override
	public City findById(long id) throws DAOException {
		City city = jdbcTemplate.queryForObject(SQL_FIND_CITY_BY_ID, new Object[]{id}, new CityMapper());
		return city;
	}

	@Override	
	public void updateById(long id, City city) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_CITY, city.getCityName(), city.getRegionId(), id);
		
	}

	@Override	
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_CITY, id);
		
	}

	@Override
	public Collection<City> findAll() throws DAOException {
		List <City> cities =  jdbcTemplate.query(SQL_FIND_ALL_CITIES, new CityMapper());
		return cities;
	}	

	
}
