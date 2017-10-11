package software.xsolve.springcloud.scanmed.service;


import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class LocalDateTimeConverter {

	protected final Clock clock;

	@Autowired
	public LocalDateTimeConverter(Clock clock) {
		this.clock = clock;
	}

	protected static ImmutableMap<String, Integer> followingDays = ImmutableMap.<String, Integer>builder()
			.put("dziś", 0)
			.put("dzisiaj", 0)
			.put("jutro", 1)
			.put("pojutrze", 2).build();

	protected static ImmutableMap<String, Integer> monthAbbreviationToDigit = ImmutableMap.<String, Integer>builder()
			.put("sty", 1)
			.put("lut", 2)
			.put("mar", 3)
			.put("kwi", 4)
			.put("maj", 5)
			.put("cze", 6)
			.put("lip", 7)
			.put("sie", 8)
			.put("wrz", 9)
			.put("paź", 10)
			.put("lis", 11)
			.put("gru", 12).build();

	public LocalDateTime convert(String dateExpression, String timeExpression) {
		return LocalDateTime.of(convertDate(dateExpression), convertTime(timeExpression));
	}

	protected LocalTime convertTime(String timeExpression) {
		return LocalTime.parse(timeExpression);
	}

	protected LocalDate convertDate(String dateExpression) {
		String[] dateWords = dateExpression.split(" ");
		if (dateWords.length < 2) {
			return handleFollowingDay(dateExpression);
		} else {
			return handleDayAndMonth(dateWords);
		}
	}

	protected LocalDate handleDayAndMonth(String[] dateWords) {
		int day = Integer.parseInt(dateWords[0]);
		int month = monthAbbreviationToDigit.get(dateWords[1]);
		return getFutureDate(LocalDate.now(clock).withMonth(month).withDayOfMonth(day));
	}

	protected LocalDate getFutureDate(LocalDate date) {
		return date.isBefore(LocalDate.now(clock)) ? date.plusYears(1) : date;
	}

	protected LocalDate handleFollowingDay(String dateExpression) {
		if (followingDays.containsKey(dateExpression)) {
			return LocalDate.now(clock).plusDays(followingDays.get(dateExpression));
		} else throw new IllegalArgumentException("Unknown date expression: " + dateExpression);
	}

}
