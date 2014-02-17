package fi.seco.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

	public static DateTime parseXMLDate(String date) {
		try {
			return xmlDateParser.parseDateTime(date);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public static String parseXMLDateTime(DateTime date) {
		return xmlDateTimeFormat.print(date);
	}

	public static String parseXMLDate(DateTime date) {
		return yearmonthdateFormat.print(date);
	}

	public static DateTimeFormatter xmlDateTimeFormat = ISODateTimeFormat.dateTime();
	public static DateTimeFormatter xmlDateParser = ISODateTimeFormat.dateOptionalTimeParser();
	public static DateTimeFormatter isoBasicDateFormat = ISODateTimeFormat.basicDateTime();
	public static DateTimeFormatter isoDHMSFFormat = ISODateTimeFormat.dateHourMinuteSecondFraction();
	public static DateTimeFormatter sqlDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	public static DateTimeFormatter yearmonthdateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
	public static DateTimeFormatter dateMonthYearFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
	public static DateTimeFormatter yearFormat = DateTimeFormat.forPattern("yyyy");
	public static DateTimeFormatter dateWithZeroesFormat = DateTimeFormat.forPattern("MM.dd.yyyy");
	public static DateTimeFormatter dateWithoutZeroesFormat = DateTimeFormat.forPattern("M.d.yyyy");

}
