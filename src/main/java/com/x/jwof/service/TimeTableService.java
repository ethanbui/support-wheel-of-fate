package com.x.jwof.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x.jwof.domain.data.entity.Engineer;
import com.x.jwof.domain.data.entity.TimeTable;
import com.x.jwof.foundation.config.ShiftConfig;
import com.x.jwof.foundation.util.DataUtil;
import com.x.jwof.foundation.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeTableService {	
	@Autowired
	private ShiftConfig shiftConfig;
	
	@Autowired
	private RuleService ruleService;
	
	public List<TimeTable> retrieveTimeTable() {
		List<TimeTable> timeTable = generateTimeTable();
		return assignEngineer(timeTable);
	}
	
	private List<TimeTable> generateTimeTable() {
		log.info("Generating timetable...");
		
		List<TimeTable> schedule = new ArrayList<>();
		LocalDate nextMonday = DateUtil.getNextMonday();
		int id = 0;
		for (int i = 0; i < shiftConfig.getCycle(); i++) {
			LocalDate shiftDate = nextMonday.plusDays(i);
			if(DateUtil.isWeekend(shiftDate)) {
				continue;
			}
			id++;
			schedule.add(new TimeTable(id, null, shiftDate, shiftDate.getDayOfWeek().toString()));
		}
		
		return schedule;
	}
	
	public List<TimeTable> assignEngineer(List<TimeTable> timeTable) {
		log.info("Assign engineer timetable...");
		
		int index = 0;
		List<Engineer> engineers = DataUtil.getEngineerList();		
		for (TimeTable schedule : timeTable) {
			if(schedule.getEngineers() == null) {
				schedule.setEngineers(new ArrayList<>());
			}
			
			if(index == 9) {
				Collections.reverse(engineers);
				index = 0;
			}
			
			for (int i = index; i < engineers.size(); i++) {
				index = i;
				
				if(ruleService.performRuleChecking(schedule, engineers.get(i), timeTable)) {
					schedule.getEngineers().add(engineers.get(i));
				}								
				
				if(schedule.getEngineers().size() == shiftConfig.getPerday()) {					
					break;
				}
			}
		}		

		return timeTable;
	}
}
