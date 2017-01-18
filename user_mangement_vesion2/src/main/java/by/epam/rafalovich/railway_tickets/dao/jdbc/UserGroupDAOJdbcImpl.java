package by.epam.rafalovich.railway_tickets.dao.jdbc;

import java.util.Collection;
import java.util.List;
import by.epam.rafalovich.railway_tickets.dao.UserGroupDAO;
import by.epam.rafalovich.railway_tickets.dao.mapper.UserGroupMapper;
import by.epam.rafalovich.railway_tickets.entity.UserGroup;
import by.epam.rafalovich.railway_tickets.exception.DAOException;

public class UserGroupDAOJdbcImpl extends AbstractJdbcDAO implements UserGroupDAO{

	private static final String SQL_CREATE_GROUP = "INSERT INTO groups (group_name, group_owner_id) VALUES (?,?)";
	private static final String SQL_FIND_GROUP_BY_ID = "SELECT group_id, group_name, group_owner_id FROM groups WHERE group_id = ?";
	private static final String SQL_FIND_ALL_GROUPS = "SELECT group_id, group_name, group_owner_id FROM groups";
	private static final String SQL_UPDATE_GROUP = "UPDATE groups SET group_name = ?, group_owner_id = ? WHERE group_id = ?";
	private static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE group_id = ?";
	
	@Override
	public void create(UserGroup group) throws DAOException {
		jdbcTemplate.update(SQL_CREATE_GROUP, group.getGroupName(), group.getOwnerId());
		
	}

	@Override
	public UserGroup findById(long id) throws DAOException {
		UserGroup group = jdbcTemplate.queryForObject(SQL_FIND_GROUP_BY_ID, new Object[]{id}, new UserGroupMapper());
		return group;
	}

	@Override
	public void updateById(long id, UserGroup group) throws DAOException {
		jdbcTemplate.update(SQL_UPDATE_GROUP, group.getGroupName(), group.getOwnerId(), id);
		
	}

	@Override
	public void deleteById(long id) throws DAOException {
		jdbcTemplate.update(SQL_DELETE_GROUP, id);
		
	}

	@Override
	public Collection<UserGroup> findAll() throws DAOException {
		List <UserGroup> groups = jdbcTemplate.query(SQL_FIND_ALL_GROUPS, new UserGroupMapper());
		return groups;
	}
	
	
}
