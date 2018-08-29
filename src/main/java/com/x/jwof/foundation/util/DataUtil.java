package com.x.jwof.foundation.util;

import java.util.List;

import com.x.jwof.domain.data.entity.Engineer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataUtil {
	private static final String ENGINEER_FILE = "static/engineers.json";
	
	public static List<Engineer> getEngineerList() {
		log.info("getting engineer list from {}", ENGINEER_FILE);
		
		String fileContent = FileUtil.readFile(FileUtil.getResourceFile(ENGINEER_FILE));		
		return ObjectMapperUtil.getList(fileContent, Engineer.class);
	}
}
