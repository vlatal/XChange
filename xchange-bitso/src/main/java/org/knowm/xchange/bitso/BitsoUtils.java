package org.knowm.xchange.bitso;

import org.knowm.xchange.exceptions.ExchangeException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A central place for shared Bitso properties
 */
public final class BitsoUtils {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * private Constructor
   */
  private BitsoUtils() {

  }

  /**
   * Format a date String for Bitso
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
