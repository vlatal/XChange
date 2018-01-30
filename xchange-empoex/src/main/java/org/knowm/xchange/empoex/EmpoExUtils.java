package org.knowm.xchange.empoex;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.DateUtils;

import java.time.ZonedDateTime;

public final class EmpoExUtils {

  public static String toPairString(CurrencyPair currencyPair) {

    return currencyPair.base.getCurrencyCode() + "-" + currencyPair.counter.getCurrencyCode();
  }

  public static CurrencyPair toCurrencyPair(String pairString) {

    String[] currencies = pairString.split("-");
    return new CurrencyPair(currencies[0], currencies[1]);
  }

  public static ZonedDateTime toDate(long unix) {

    return DateUtils.fromSecondsToZonedDateTime(unix);
  }

  public static ZonedDateTime toDate(String dateString) {

    return DateUtils.fromSecondsToZonedDateTime(Long.parseLong(dateString));
  }
}
