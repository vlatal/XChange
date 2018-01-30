package org.knowm.xchange.bittrex;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A central place for shared Bittrex properties
 */
public final class BittrexUtils {

  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
  private static final String DATE_FORMAT_NO_MILLIS = "yyyy-MM-dd'T'HH:mm:ss";

  private static final ZoneId TIME_ZONE = ZoneOffset.UTC;

  /**
   * private Constructor
   */
  private BittrexUtils() {
  }

  public static String toPairString(CurrencyPair currencyPair) {
    return currencyPair.counter.getCurrencyCode().toUpperCase() + "-" + currencyPair.base.getCurrencyCode().toUpperCase();
  }

  public static ZonedDateTime toDate(String dateString) {

    if(dateString == null)
      return null;

    try {
      return ZonedDateTime.parse(dateString, dateParser());
    } catch (DateTimeParseException e) {
      try {
        return ZonedDateTime.parse(dateString, dateParserNoMillis());
      } catch (DateTimeParseException e1) {
        throw new ExchangeException("Illegal date/time format", e1);
      }
    }
  }

  private static DateTimeFormatter dateParserNoMillis() {
    DateTimeFormatter dateParserNoMillis = DateTimeFormatter.ofPattern(DATE_FORMAT_NO_MILLIS);
    dateParserNoMillis.withZone(TIME_ZONE);
    return dateParserNoMillis;
  }

  private static DateTimeFormatter dateParser() {
    DateTimeFormatter dateParser = DateTimeFormatter.ofPattern(DATE_FORMAT);
    dateParser.withZone(TIME_ZONE);
    return dateParser;
  }

}
