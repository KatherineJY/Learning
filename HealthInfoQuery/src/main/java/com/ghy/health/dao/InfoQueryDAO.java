package com.ghy.health.dao;

import com.ghy.health.model.HealthInfo;

/**
 * @author JY
 * ��ѯĳ��ѧ��ĳ��������Ϣ
 */
public interface InfoQueryDAO {
	public HealthInfo queryInfo(int year,int id,String name);
}
