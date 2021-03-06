package com.techgig.hotcity.utils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class HotCityUtils {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static DecimalFormat df2 = new DecimalFormat(".##");

	public static LocalDateTime coverttoDateFromString(String dateTime) {
		LocalDateTime ldt = LocalDateTime.now().plusDays(1);
		if (dateTime instanceof String) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

			ldt = LocalDateTime.parse(dateTime, dtf);
		}
		return ldt;
	}
}
