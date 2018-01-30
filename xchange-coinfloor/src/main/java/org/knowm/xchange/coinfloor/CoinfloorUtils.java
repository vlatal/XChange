package org.knowm.xchange.coinfloor;

import org.knowm.xchange.exceptions.ExchangeException;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class CoinfloorUtils {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  static {
    DATE_FORMAT.withZone(ZoneOffset.UTC);
  }

  private CoinfloorUtils() {
  }

  public static ZonedDateTime parseDate(final String date) {
    try {
      return ZonedDateTime.parse(date, DATE_FORMAT);
    } catch (final DateTimeParseException e) {
      throw new ExchangeException("Illegal date/time format: " + date, e);
    }
  }
}
