package com.x.jwof.foundation.rule;

import java.util.List;

import com.x.jwof.domain.data.entity.Engineer;
import com.x.jwof.domain.data.entity.TimeTable;

public interface RuleEngine {
	boolean run(TimeTable schedule, Engineer engineer, List<TimeTable> timeTable, List<Engineer> unavailable);
}
