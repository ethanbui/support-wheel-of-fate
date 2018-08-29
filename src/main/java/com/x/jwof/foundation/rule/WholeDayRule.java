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
	public boolean run(TimeTable schedule, Engineer engineer, List<TimeTable> timeTable) {
		log.info("Full day rule checker for engineer {} on day {}", engineer.getId(), schedule.getId());
		
		int count = 0;				
		for (TimeTable item : timeTable) {
			if(item.getEngineers() == null) {
				break;
			}
			
			if(item.getEngineers().contains(engineer)) {
				count++;
				if(count > 1) {
					return false;
				}
			}
		}		
		return true;
	}
}
