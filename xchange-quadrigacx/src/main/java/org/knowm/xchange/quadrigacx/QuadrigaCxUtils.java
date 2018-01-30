package org.knowm.xchange.quadrigacx;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A central place for shared QuadrigaCx properties
 */
public final class QuadrigaCxUtils {

  private static final String TIMEZONE = "UTC";
  private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
  private static final DateTimeFormatter DATE_FORMAT;

  static {
    DATE_FORMAT = DateTimeFormatter.ofPattern(PATTERN);
    DATE_FORMAT.withZone(ZoneId.of(TIMEZONE));
  }

  /**
   * private Constructor
   */
  private QuadrigaCxUtils() {

  }

  /**
   * Format a date String for QuadrigaCx
   *
   * @param dateString
   * @return
   */
  public static ZonedDateTime parseDate(String dateString) {
    try {
      synchronized (DATE_FORMAT) { // DateTimeFormatter is not thread safe, therefore synchronize it
        return ZonedDateTime.parse(dateString, DATE_FORMAT);
      }
    } catch (DateTimeParseException e) {
      throw new ExchangeException("Illegal date/time format", e);
    }
  }

  public static String currencyPairToBook(CurrencyPair currencyPair) {
    return currencyPair.base.getCurrencyCode().toLowerCase() + "_" + currencyPair.counter.getCurrencyCode().toLowerCase();
  }

}
