package com.x.jwof.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
		List<Engineer> engineers = DataUtil.getEngineerList();
		List<TimeTable> timeTable = generateTimeTable();

		for (TimeTable schedule : timeTable) {
			schedule.setEngineers(new ArrayList<>());			
			assignEngineers(engineers, timeTable, schedule);
		}
		
		return timeTable;
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
	
	private void assignEngineers(List<Engineer> engineers, List<TimeTable> timeTable, TimeTable schedule) {
		// init unavailable engineers in order to reduce loop count
		List<Engineer> unavailable = new ArrayList<>();
		
		for (Engineer engineer : engineers) {
			if(ruleService.performRuleChecking(schedule, engineer, timeTable, unavailable)) {
				schedule.getEngineers().add(engineer);
			}
			
			if(schedule.getEngineers().size() == shiftConfig.getPerday()) {					
				break;
			}
		}
		
		// remove unavailable engineers
		engineers.removeAll(unavailable);
		
		if(schedule.getEngineers().size() < 2) {
			assignEngineers(engineers, timeTable, schedule);
		}		
	}
}
