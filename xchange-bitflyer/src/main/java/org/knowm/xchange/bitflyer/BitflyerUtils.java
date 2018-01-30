package org.knowm.xchange.bitflyer;

import org.knowm.xchange.exceptions.ExchangeException;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BitflyerUtils {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

  static {
    DATE_FORMAT.withZone(ZoneOffset.UTC);
  }

  private BitflyerUtils() {
  }

  public static ZonedDateTime parseDate(final String date) {
    try {
      return ZonedDateTime.parse(date, DATE_FORMAT);
    } catch (final DateTimeParseException e) {
      throw new ExchangeException("Illegal date/time format: " + date, e);
    }
  }
}
