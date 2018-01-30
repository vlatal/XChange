package org.knowm.xchange.kuna.util;

import org.knowm.xchange.currency.CurrencyPair;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Dat Bui
 */
public class KunaUtils {

  public static final String DATE_FORMAT_NO_MILLIS = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  /**
   * Hide default constructor.
   */
  private KunaUtils() {
  }

  public static String toPairString(CurrencyPair currencyPair) {
    return currencyPair.base.getCurrencyCode().toLowerCase() + currencyPair.counter.getCurrencyCode().toLowerCase();
  }

  public static ZonedDateTime toDate(String dateString) {
    try {
      return ZonedDateTime.parse(dateString, dateParserNoMillis());
    } catch (DateTimeParseException e) {
      OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString);
      return ZonedDateTime.ofInstant(offsetDateTime.toInstant(), ZoneOffset.UTC);
    }
  }

  public static String format(ZonedDateTime date) {
    return dateParserNoMillis().format(date);
  }

  private static DateTimeFormatter dateParserNoMillis() {
    DateTimeFormatter dateParserNoMillis = DateTimeFormatter.ofPattern(DATE_FORMAT_NO_MILLIS);
    return dateParserNoMillis;
  }
}
