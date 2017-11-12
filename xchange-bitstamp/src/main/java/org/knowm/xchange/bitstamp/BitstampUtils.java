package org.knowm.xchange.bitstamp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.knowm.xchange.exceptions.ExchangeException;

/**
 * A central place for shared Bitstamp properties
 */
public final class BitstampUtils {

	public static final DateTimeFormatter DATE_FORMAT;
	public static final DateTimeFormatter DATE_NANO_FORMAT;
	public static final int MAX_TRANSACTIONS_PER_QUERY = 1000;

	static {
		DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DATE_FORMAT.withZone(ZoneOffset.UTC);
		DATE_NANO_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		DATE_NANO_FORMAT.withZone(ZoneOffset.UTC);
	}

	/**
	 * private Constructor
	 */
	private BitstampUtils() {

	}

	/**
	 * Format a date String for Bitstamp
	 *
	 * @param dateString
	 * @return
	 */
	public static ZonedDateTime parseDate(String dateString) {
		if (dateString == null) {
			return null;
		}

		try {
			LocalDateTime ldt;
			try {
				ldt = LocalDateTime.parse(dateString, DATE_FORMAT);
			} catch (DateTimeParseException e) {
				ldt = LocalDateTime.parse(dateString, DATE_NANO_FORMAT);
			}
			return ZonedDateTime.of(ldt, ZoneOffset.UTC);
		} catch (DateTimeParseException e) {
			throw new ExchangeException("Illegal date/time format", e);
		}
	}
}
