package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import by.epam.rafalovich.railway_tickets.entity.Region;

public class RegionMapper implements RowMapper<Region>{
	
	private static final String REGION_NAME = "REGION_NAME";
	private static final String COUNTRY_ID = "CONTRY_ID";
	private static final String REGION_ID = "REGION_ID";

	@Override
	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
		Region region = new Region();
		region.setId(rs.getLong(REGION_ID));
		region.setCountryId(rs.getLong(COUNTRY_ID));
		region.setRegionName(rs.getString(REGION_NAME));
		return region;
	}
	
}
