package com.x.jwof.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.jwof.domain.data.entity.TimeTable;
import com.x.jwof.domain.data.response.Response;
import com.x.jwof.domain.data.response.TimeTableResponse;
import com.x.jwof.service.TimeTableService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class TimeTableController {
	@Autowired
	private TimeTableService timeTableService;
	
	@RequestMapping(value = "/timetable", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Response timeEvents() {
		log.info("Requesting for timetable...");
		
		List<TimeTable> timeTable = timeTableService.retrieveTimeTable();
		
		Response response = new Response();
        response.setSuccess(true);
        response.setData(new TimeTableResponse(timeTable));
        
		return response;
	}
}
