package com.ghy.health.model;

import java.util.List;

/**
 * @author JY
 * ѧ����������Ϣ  �ܲ鵽��ÿһ������ɼ�
 */
public class StudentInfo {
	private int id;
	private String name;
	private List<HealthInfo> HealthInfos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HealthInfo> getHealthInfos() {
		return HealthInfos;
	}
	public void setHealthInfos(List<HealthInfo> healthInfos) {
		HealthInfos = healthInfos;
	}
	
}
