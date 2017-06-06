package com.learning.dao;

import java.util.List;

import com.learning.domain.Student;

/**
 * @author Win8
 * StudentDAO访问窗口
 */
public interface StudentDAO {
	/**
	 * 查询所有学生
	 * @return 返回所有所有学生
	 * */
	public List<Student> query();
	
	/**
	 * 添加一个学生
	 * @param 待添加的学生
	 * */
	public void save(Student student);
}
