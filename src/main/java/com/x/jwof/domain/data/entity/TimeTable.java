package com.x.jwof.domain.data.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTable {
	private int id;
	private List<Engineer> engineers;
	private LocalDate shiftDate;
	private String shiftDay;
}
