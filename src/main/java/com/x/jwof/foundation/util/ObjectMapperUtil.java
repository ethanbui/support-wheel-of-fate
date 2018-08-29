package com.x.jwof.foundation.util;

import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMapperUtil {
	public static <T> List<T> getList(String jsonContent, Class<T> valueType) {    	
    	ObjectMapper mapper = initMapper();
		
		List<T> result = null;
		try {
			result = mapper.readValue(jsonContent, mapper.getTypeFactory().constructCollectionType(List.class, valueType));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return result;
    }
    
    public static <T> T getObject(String jsonContent, Class<T> valueType) {    	
    	ObjectMapper mapper = initMapper();
		
		T result = null;
		try {
			result = mapper.readValue(jsonContent, valueType);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return result;
    }
    
    private static ObjectMapper initMapper() {
    	ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return mapper;
    }
}
