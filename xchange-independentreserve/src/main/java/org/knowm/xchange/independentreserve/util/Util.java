package org.knowm.xchange.independentreserve.util;


import org.knowm.xchange.utils.DateUtils;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Util {

  private static final ZoneId TIMEZONE = ZoneOffset.UTC;
  private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static final DateTimeFormatter DATE_FORMAT;

  static {
    DATE_FORMAT = DateTimeFormatter.ofPattern(PATTERN);
    DATE_FORMAT.withZone(TIMEZONE);
  }

  private Util() {
  }

  /**
   * Format a date String for IR
   *
   * @param d a date
   * @return formatted date for Independent Reserve
   */
  public static String formatDate(ZonedDateTime d) {
    synchronized (DATE_FORMAT) {       // DateTimeFormatter is not thread safe, therefore synchronize it
      return d == null ? null : DATE_FORMAT.format(d);
    }
  }

  public static ZonedDateTime toDate(String date) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {
    return DateUtils.fromISO8601DateStringToZonedDateTime(date);
  }

}
