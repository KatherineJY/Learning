package com.ghy.health.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ghy.health.model.StudentInfo;
import com.ghy.health.service.QueryService;

/**
 * @author JY
 * Controller查询学生在校期间的体测成绩
 */
@Controller
@RequestMapping("")
public class QueryController {
	@RequestMapping(value="/query/{id}/{name}")
	public ResponseEntity<StudentInfo> getCourseInJson2(@PathVariable Integer id,@PathVariable String name){
		StudentInfo info = QueryService.getStudentInfo(id, name);
		return new ResponseEntity<StudentInfo>(info,HttpStatus.OK);
	}
	
}
