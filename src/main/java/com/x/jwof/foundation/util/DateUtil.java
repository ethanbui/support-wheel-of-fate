package com.x.jwof.foundation.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.EnumSet;
import java.util.Set;

public class DateUtil {
	public static LocalDate getNextMonday() {	
		return LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	}
	
	public static boolean isWeekend(LocalDate localDate) {
		Set<DayOfWeek> weekend = EnumSet.of( DayOfWeek.SATURDAY , DayOfWeek.SUNDAY );
		return weekend.contains(localDate.getDayOfWeek());
	}
}
