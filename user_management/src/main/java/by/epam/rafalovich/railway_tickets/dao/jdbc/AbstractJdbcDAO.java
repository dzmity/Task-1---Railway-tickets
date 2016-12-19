package by.epam.rafalovich.railway_tickets.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public class AbstractJdbcDAO {	

	protected JdbcTemplate jdbcTemplate;
	

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
         this.jdbcTemplate = jdbcTemplate;
    }
}
