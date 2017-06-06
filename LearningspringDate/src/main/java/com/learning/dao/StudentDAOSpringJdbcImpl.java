package com.learning.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.learning.domain.Student;

/**
 * @author Win8
 * Í¨¹ýspring JDBC
 */
public class StudentDAOSpringJdbcImpl implements StudentDAO{
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> query() {
		final List<Student> students = new ArrayList<Student>();
		String sql = "Select id, name, age from student";
		jdbcTemplate.query(sql,new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet resultset) throws SQLException {
				Student student = null;
				while( resultset.next() ){
					int id = resultset.getInt("id");
					String name = resultset.getString("name");
					int age = resultset.getInt("age");
					
					student = new Student();
					student.setId(id);
					student.setName(name);
					student.setAge(age);
					
					students.add(student);
				}
			}
		});
		return students;
	}

	@Override
	public void save(Student student) {
		String sql = "insert into student(name,age) values(?,?)";
		jdbcTemplate.update(sql,new Object[]{student.getName(),student.getAge()});
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
