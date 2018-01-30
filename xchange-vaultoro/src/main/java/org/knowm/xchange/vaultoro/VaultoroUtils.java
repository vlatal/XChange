package org.knowm.xchange.vaultoro;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A central place for shared Vaultoro properties
 */
public final class VaultoroUtils {

  private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  public static ZonedDateTime parseDate(String dateString) {

    // 2015-04-13T07:56:36.185Z

    try {
      return ZonedDateTime.parse(dateString, format);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

}
