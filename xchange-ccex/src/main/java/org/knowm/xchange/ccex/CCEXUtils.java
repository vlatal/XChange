package org.knowm.xchange.ccex;

import org.knowm.xchange.currency.CurrencyPair;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CCEXUtils {

  private static final ZonedDateTime EPOCH = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);

  private CCEXUtils() {

  }

  public static String toPairString(CurrencyPair currencyPair) {

    return currencyPair.counter.getCurrencyCode().toLowerCase() + "-" + currencyPair.base.getCurrencyCode().toLowerCase();
  }

  public static ZonedDateTime toDate(String datetime) {
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    sdf.withZone(ZoneOffset.UTC);

    try {
      return ZonedDateTime.parse(datetime, sdf);
    } catch (DateTimeParseException e) {
      return EPOCH;
    }
  }
}
