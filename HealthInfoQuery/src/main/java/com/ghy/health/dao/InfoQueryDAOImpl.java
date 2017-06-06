package com.ghy.health.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.ghy.health.model.HealthInfo;

/**
 * @author JY
 * 查询接口的一个实现
 */
public class InfoQueryDAOImpl implements InfoQueryDAO{
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public HealthInfo queryInfo(final int year, int id, String name) {
		String sql = "Select * from health"+year+" where id = ? and name = ?";
		final HealthInfo healthInfo = new HealthInfo();
		jdbcTemplate.query(sql, new Object[]{id,name},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				if( rs!=null ){
					healthInfo.setHeight(rs.getInt("height"));
					healthInfo.setWeight(rs.getInt("weight"));
					healthInfo.setRunning800(rs.getInt("running800"));
					healthInfo.setYear(year);
				}
			}
			
		});
		return healthInfo;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
