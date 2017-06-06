package com.ghy.health.dao;

import com.ghy.health.model.HealthInfo;

/**
 * @author JY
 * 查询某个学生某年的体测信息
 */
public interface InfoQueryDAO {
	public HealthInfo queryInfo(int year,int id,String name);
}
