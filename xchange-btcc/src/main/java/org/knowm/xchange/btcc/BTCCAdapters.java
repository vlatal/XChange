package org.knowm.xchange.btcc;

import org.knowm.xchange.btcc.dto.marketdata.BTCCTicker;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BTCCAdapters {

  public static Ticker adaptTicker(BTCCTicker btccTicker, CurrencyPair currencyPair) {

    BigDecimal last = btccTicker.getLast();
    BigDecimal high = btccTicker.getHigh();
    BigDecimal low = btccTicker.getLow();
    BigDecimal ask = btccTicker.getAskPrice();
    BigDecimal bid = btccTicker.getBidPrice();
    BigDecimal volume = btccTicker.getVolume();
    ZonedDateTime timestamp = DateUtils.fromMillisToZonedDateTime(btccTicker.getTimestamp());

    return new Ticker.Builder().currencyPair(currencyPair).last(last).high(high).low(low).volume(volume).ask(ask).bid(bid).timestamp(timestamp).build();
  }

}
