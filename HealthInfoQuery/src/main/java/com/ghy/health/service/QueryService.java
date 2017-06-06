package com.ghy.health.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghy.health.dao.InfoQueryDAO;
import com.ghy.health.model.HealthInfo;
import com.ghy.health.model.StudentInfo;

public class QueryService {
	private static InfoQueryDAO infoQueryDAO;
	
	public void setInfoQueryDAO(InfoQueryDAO infoQueryDAO) {
		this.infoQueryDAO = infoQueryDAO;
	}

	public static StudentInfo getStudentInfo(int id,String name){
		StudentInfo studentInfo = new StudentInfo();
		List<HealthInfo> healthInfos = new ArrayList<HealthInfo>();
		int[] years = getYear(id);
		for( int i=years[0]; i<=years[1] ;i++ ){
			HealthInfo info = infoQueryDAO.queryInfo(i,id,name);
			healthInfos.add(info);
		}
		studentInfo.setName(name);
		studentInfo.setId(id);
		studentInfo.setHealthInfos(healthInfos);
		return studentInfo;
	}

	private static int[] getYear(int id) {
		int year_in,year_out;
		year_in = id/100000%100+2000;
		year_out = Integer.min(year_in+4, Calendar.getInstance().get(Calendar.YEAR));
		return new int[]{year_in,year_out};
	}
}
