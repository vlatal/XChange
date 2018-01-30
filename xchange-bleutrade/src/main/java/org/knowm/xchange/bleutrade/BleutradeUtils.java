package org.knowm.xchange.bleutrade;

import org.knowm.xchange.currency.CurrencyPair;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class BleutradeUtils {

  public static String toPairString(CurrencyPair currencyPair) {

    return currencyPair.base.getCurrencyCode().toUpperCase() + "_" + currencyPair.counter.getCurrencyCode().toUpperCase();
  }

  public static CurrencyPair toCurrencyPair(String pairString) {

    String[] currencies = pairString.split("_");
    return new CurrencyPair(currencies[0], currencies[1]);
  }

  public static ZonedDateTime toDate(String timeStamp) {

    try {

      DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'.'SSS");
      sdf.withZone(ZoneOffset.UTC);
      return ZonedDateTime.parse(timeStamp, sdf);

    } catch (DateTimeParseException e) {

      try {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sdf.withZone(ZoneOffset.UTC);
        return ZonedDateTime.parse(timeStamp, sdf);

      } catch (DateTimeParseException e1) {

        return null;
      }
    }
  }
}
