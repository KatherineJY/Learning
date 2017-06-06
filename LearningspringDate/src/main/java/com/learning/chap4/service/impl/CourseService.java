package com.learning.chap4.service.impl;

import org.springframework.stereotype.Service;

import com.learning.chap4.model.Course;

@Service
public interface CourseService {
	
	Course getCoursebyId(Integer courseId);
	

}