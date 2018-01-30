package org.knowm.xchange.gatecoin;

import org.knowm.xchange.exceptions.ExchangeException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A central place for shared Gatecoin properties
 */
public final class GatecoinUtils {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * private Constructor
   */
  private GatecoinUtils() {

  }

  /**
   * Format a date String for Gatecoin
   *
   * @param dateString
   * @return
   */
  public static ZonedDateTime parseDate(String dateString) {

    try {
      return ZonedDateTime.parse(dateString, DATE_FORMAT);
    } catch (DateTimeParseException e) {
      throw new ExchangeException("Illegal date/time format", e);
    }
  }

}
