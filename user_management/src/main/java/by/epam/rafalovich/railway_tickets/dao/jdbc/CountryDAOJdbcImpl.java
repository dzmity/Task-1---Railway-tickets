package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import by.epam.rafalovich.railway_tickets.dao.CountryDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.CountryMapper;
import by.epam.rafalovich.railway_tickets.entity.Country;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class CountryDAOJdbcImpl extends AbstractJdbcDAO implements CountryDAO{

	private static final String SQL_CREATE_COUNTRY = "INSERT INTO countries (country_name) VALUES (?)";
	private static final String SQL_FIND_COUNTRY_BY_ID = "SELECT country_name, country_id FROM countries WHERE country_id = ?";
	private static final String SQL_FIND_ALL_COUNTRIES = "SELECT country_name, country_id FROM countries";
	private static final String SQL_UPDATE_COUNTRY = "UPDATE countries SET country_name = ? WHERE country_id = ?";
	private static final String SQL_DELETE_COUNTRY = "DELETE FROM countries WHERE country_id = ?";
	
	@Override
	public void create(Country country) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_COUNTRY, country.getName());
		
	}

	@Override
	public Country findById(long id) throws DAOException {
		Country country = jdbcTemplate.queryForObject(SQL_FIND_COUNTRY_BY_ID, new Object[]{id}, new CountryMapper());
		return country;
	}

	@Override	
	public void updateById(long id, Country country) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_COUNTRY,country.getName(), id);
		
	}

	@Override	
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_COUNTRY, id);
		
	}

	@Override
	public Collection<Country> findAll() throws DAOException {
		List <Country> countries =  jdbcTemplate.query(SQL_FIND_ALL_COUNTRIES, new CountryMapper());
		return countries;
	}	

	
	
	
	
}
