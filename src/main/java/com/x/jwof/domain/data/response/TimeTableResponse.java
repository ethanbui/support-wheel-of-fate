package com.x.jwof.domain.data.response;

import java.util.List;

import com.x.jwof.domain.data.entity.TimeTable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeTableResponse {
	private List<TimeTable> timeTable;
}
