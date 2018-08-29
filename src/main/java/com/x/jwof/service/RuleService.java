package com.x.jwof.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x.jwof.domain.data.entity.Engineer;
import com.x.jwof.domain.data.entity.TimeTable;
import com.x.jwof.foundation.rule.RuleEngine;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RuleService {
	@Autowired
	private List<RuleEngine> ruleEngines;
	
	public boolean performRuleChecking(TimeTable schedule, Engineer engineer, List<TimeTable> timeTable, List<Engineer> unavailable) {
		log.info("perform rule checking...");
		
		boolean isValid = true;
		for (RuleEngine ruleEngine : ruleEngines) {
			isValid = ruleEngine.run(schedule, engineer, timeTable, unavailable);
			log.info("Result: {}", isValid);
			
			if(!isValid) {
				return isValid;
			}
		}
		return isValid;
	}
}
