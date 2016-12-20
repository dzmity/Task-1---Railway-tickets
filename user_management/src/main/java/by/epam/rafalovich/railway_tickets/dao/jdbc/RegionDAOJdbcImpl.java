package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import java.util.List;

import by.epam.rafalovich.railway_tickets.dao.RegionDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.RegionMapper;
import by.epam.rafalovich.railway_tickets.entity.Region;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class RegionDAOJdbcImpl extends AbstractJdbcDAO implements RegionDAO{

	private static final String SQL_CREATE_REGION = "INSERT INTO regions (region_name, country_id) VALUES (?,?)";
	private static final String SQL_FIND_REGION_BY_ID = "SELECT region_id, region_name, country_id FROM regions WHERE region_id = ?";
	private static final String SQL_FIND_ALL_REGIONS = "SELECT region_id, region_name, country_id FROM regions";
	private static final String SQL_UPDATE_REGION = "UPDATE regions SET region_name = ?, country_id = ? WHERE region_id = ?";
	private static final String SQL_DELETE_REGION = "DELETE FROM regions WHERE region_id = ?";
	
	@Override
	public void create(Region region) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_REGION, region.getRegionName(), region.getCountryId());
		
	}

	@Override
	public Region findById(long id) throws DAOException {
		Region region = jdbcTemplate.queryForObject(SQL_FIND_REGION_BY_ID, new Object[]{id}, new RegionMapper());
		return region;
	}

	@Override
	public void updateById(long id, Region region) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_REGION, region.getRegionName(), region.getCountryId(), id);
		
	}

	@Override
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_REGION, id);
		
	}

	@Override
	public Collection<Region> findAll() throws DAOException {
		List<Region> regions = jdbcTemplate.query(SQL_FIND_ALL_REGIONS, new RegionMapper());
		return regions;
	}
	
	

}
