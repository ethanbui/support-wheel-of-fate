package com.x.jwof.foundation.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.x.jwof.domain.data.entity.Engineer;
import com.x.jwof.domain.data.entity.TimeTable;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HalfDayRule implements RuleEngine {

	@Override
	public boolean run(TimeTable schedule, Engineer engineer, List<TimeTable> timeTable) {
		log.info("Half day rule checker for engineer {} on day {}", engineer.getId(), schedule.getId());
						
		return !schedule.getEngineers().contains(engineer);
	}

}
