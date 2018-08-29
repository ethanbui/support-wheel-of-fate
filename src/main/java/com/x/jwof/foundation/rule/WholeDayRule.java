package com.x.jwof.foundation.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.x.jwof.domain.data.entity.Engineer;
import com.x.jwof.domain.data.entity.TimeTable;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WholeDayRule implements RuleEngine {

	@Override
	public boolean run(TimeTable schedule, Engineer engineer, List<TimeTable> timeTable, List<Engineer> unavailable) {
		log.info("Whole day rule checker for engineer {} on day {}", engineer.getId(), schedule.getId());
		
		int index = 0;
		int count = 0;		
		
		for (TimeTable scheduled : timeTable) {
			if(scheduled.getEngineers() == null) {
				break;
			}
			
			if(scheduled.getEngineers().contains(engineer)) {
				// check previous shift indicator
				index = scheduled.getEngineers().indexOf(engineer);
				count++;
				
				if(count > 1) {
					unavailable.add(engineer);
					return false;
				}
			}
		}
		
		// avoid engineer to take same (day or night) day with previous shift by
		// checking current shift indicator and compare with previous shift
		if(count == 1 && index == schedule.getEngineers().size()) {
			return false;
		}
		
		// engineer should take shift next week
		if(count == 1 && timeTable.indexOf(schedule) < 5) {
			return false;
		}
		
		return true;
	}
}
