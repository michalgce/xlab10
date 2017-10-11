package software.xsolve.springcloud.scanmed.service;

import static org.junit.Assert.assertEquals;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LocalDateTimeConverterTest {

	private static final Clock atBeginningOfYear = Clock.fixed(LocalDateTime.of(2017, 1, 1, 10, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
	private static final Clock atEndOfYear = Clock.fixed(LocalDateTime.of(2017, 12, 30, 10, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());

	@Parameterized.Parameters(name= "{index}: convert[{1}, {2}]={3}, clock: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{atBeginningOfYear, "14 lut", "17:12", LocalDateTime.of(2017, 2, 14, 17, 12, 0)},
				{atBeginningOfYear, "14 paź", "06:00", LocalDateTime.of(2017, 10, 14, 6, 0, 0)},
				{atBeginningOfYear, "30 gru", "11:45", LocalDateTime.of(2017, 12, 30, 11, 45, 0)},
				{atEndOfYear, "14 lut", "17:12", LocalDateTime.of(2018, 2, 14, 17, 12, 0)},
				{atEndOfYear, "dzisiaj", "17:12", LocalDateTime.of(2017, 12, 30, 17, 12, 0)},
				{atEndOfYear, "pojutrze", "17:12", LocalDateTime.of(2018, 1, 1, 17, 12, 0)},
				{atBeginningOfYear, "jutro", "10:45", LocalDateTime.of(2017, 1, 2, 10, 45, 0)},
		});
	}

	private Clock currentClock;
	private String inputDate;
	private String inputTime;
	private LocalDateTime expected;

	public LocalDateTimeConverterTest(Clock currentClock, String inputDate, String inputTime, LocalDateTime expected) {
		this.currentClock = currentClock;
		this.inputDate = inputDate;
		this.inputTime = inputTime;
		this.expected = expected;
	}

	@Test
	public void test() {
		LocalDateTimeConverter converter = new LocalDateTimeConverter(currentClock);
		assertEquals(expected, converter.convert(inputDate, inputTime));
	}

}
