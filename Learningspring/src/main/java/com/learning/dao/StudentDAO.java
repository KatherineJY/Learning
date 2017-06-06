package com.learning.dao;

import java.util.List;

import com.learning.domain.Student;

/**
 * @author Win8
 * StudentDAO���ʴ���
 */
public interface StudentDAO {
	/**
	 * ��ѯ����ѧ��
	 * @return ������������ѧ��
	 * */
	public List<Student> query();
	
	/**
	 * ���һ��ѧ��
	 * @param ����ӵ�ѧ��
	 * */
	public void save(Student student);
}
