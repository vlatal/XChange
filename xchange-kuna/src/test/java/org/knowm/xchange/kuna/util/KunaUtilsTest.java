package org.knowm.xchange.kuna.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.DateUtils;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class KunaUtilsTest {

  @Test
  public void test_toPairString() {
    assertThat(KunaUtils.toPairString(CurrencyPair.BTC_UAH)).isEqualTo("btcuah");
    assertThat(KunaUtils.toPairString(CurrencyPair.ETH_UAH)).isEqualTo("ethuah");
    assertThat(KunaUtils.toPairString(CurrencyPair.BCH_UAH)).isEqualTo("bchuah");
  }

  @Test
  public void test_to_date() {
    String dateString = "2018-01-16T14:19:24+02:00";
    ZonedDateTime actual = KunaUtils.toDate(dateString);
    try {
      assertThat(actual).isEqualToIgnoringHours(DateUtils.fromISODateStringToZonedDateTime(dateString));
    } catch (InvalidFormatException e) {
      e.printStackTrace();
    }

    assertThat(KunaUtils.toDate("2018-01-16T09:28:05Z")).isEqualTo("2018-01-16T09:28:05Z");
  }
}