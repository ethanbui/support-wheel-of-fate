package com.x.jwof.foundation.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.x.jwof.domain.data.entity.Engineer;
import com.x.jwof.domain.data.entity.TimeTable;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsecutiveDayRule implements RuleEngine {

	@Override
	public boolean run(TimeTable schedule, Engineer engineer, List<TimeTable> timeTable) {
		log.info("Consecutive day rule checker for engineer {} on day {}", engineer.getId(), schedule.getId());
		
		boolean result = true;
		
		if(schedule.getId() == 1) {
			return result;
		}
		
		TimeTable previousDay = timeTable.get(timeTable.indexOf(schedule) - 1);				
		Engineer assigned = previousDay.getEngineers().stream().filter(item -> engineer.getId() == item.getId()).findAny().orElse(null);
		
		return assigned == null;
	}

}
