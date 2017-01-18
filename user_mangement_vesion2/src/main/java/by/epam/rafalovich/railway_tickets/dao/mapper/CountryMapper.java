package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import by.epam.rafalovich.railway_tickets.entity.Country;

public class CountryMapper implements RowMapper<Country>{
	
	private static final String COUNTRY_NAME = "COUNTRY_NAME";
	private static final String COUNTRY_ID = "COUNTRY_ID";


	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Country country = new Country();
		country.setId(rs.getLong(COUNTRY_ID));
		country.setName(rs.getString(COUNTRY_NAME));
		return country;
	}

	
}
