package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import by.epam.rafalovich.railway_tickets.entity.City;


public class CityMapper implements RowMapper<City>{
	
	private static final String CITY_NAME = "CITY_NAME";
	private static final String CITY_ID = "CITY_ID";
	private static final String REGION_ID = "REGION_ID";


	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		City city = new City();
		city.setId(rs.getLong(CITY_ID));
		city.setRegionId(rs.getLong(REGION_ID));
		city.setCityName(rs.getString(CITY_NAME));
		return city;
	}

	
}