package org.knowm.xchange.truefx;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.truefx.dto.marketdata.TrueFxTicker;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TrueFxAdapters {
  public static Ticker adaptTicker(TrueFxTicker rawTicker) {
    ZonedDateTime timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(rawTicker.getTimestamp()), ZoneOffset.UTC);
    CurrencyPair pair = CurrencyPairDeserializer.getCurrencyPairFromString(rawTicker.getPair());
    return new Ticker.Builder().currencyPair(pair).timestamp(timestamp).bid(rawTicker.calcBid()).ask(rawTicker.calcAsk()).high(rawTicker.getHigh())
        .low(rawTicker.getLow()).build();
  }
}
