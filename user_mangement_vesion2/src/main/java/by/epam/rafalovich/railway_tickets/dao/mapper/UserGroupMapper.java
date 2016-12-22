package by.epam.rafalovich.railway_tickets.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import by.epam.rafalovich.railway_tickets.entity.UserGroup;

public class UserGroupMapper implements RowMapper<UserGroup>{
	
	private static final String GROUP_ID = "GROUP_ID";
	private static final String GROUP_NAME = "GROUP_NAME";
	private static final String GROUP_OWNER_ID = "GROUP_OWNER_ID";
	

	@Override
	public UserGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserGroup group = new UserGroup();
		group.setGroupName(rs.getString(GROUP_NAME));
		group.setId(rs.getLong(GROUP_ID));
		group.setOwnerId(rs.getLong(GROUP_OWNER_ID));
		return group;
	}

	
}
