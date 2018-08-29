package com.x.jwof.foundation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ShiftConfig {
	@Value("${shift.cycle:14}")
	private int cycle;
	
	@Value("${shift.perday:2}")
	private int perday;	
}
