package org.knowm.xchange.vircurex;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A central place for shared Vircurex properties
 */
public final class VircurexUtils {

  // Vircurex API parameters
  public static final int UNRELEASED_ORDER = 0;
  public static final int RELEASED_ORDER = 1;

  // Vircurex bid/ask syntax
  public static final String BID = "BUY";
  public static final String ASK = "SELL";

  /**
   * private Constructor
   */
  private VircurexUtils() {

  }

  public static String getUtcTimestamp() {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    format.withZone(ZoneOffset.UTC);
    return format.format(ZonedDateTime.now());
  }

}
