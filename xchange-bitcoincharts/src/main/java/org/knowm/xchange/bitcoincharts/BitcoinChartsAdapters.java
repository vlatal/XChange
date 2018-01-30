package org.knowm.xchange.bitcoincharts;

import org.knowm.xchange.bitcoincharts.dto.marketdata.BitcoinChartsTicker;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Various adapters for converting from BitcoinCharts DTOs to XChange DTOs
 */
public final class BitcoinChartsAdapters {

  /**
   * private Constructor
   */
  private BitcoinChartsAdapters() {

  }

  /**
   * Adapts a BitcoinChartsTicker[] to a Ticker Object
   *
   * @param bitcoinChartsTickers
   * @return
   */
  public static Ticker adaptTicker(BitcoinChartsTicker[] bitcoinChartsTickers, CurrencyPair currencyPair) {

    for (int i = 0; i < bitcoinChartsTickers.length; i++) {
      if (bitcoinChartsTickers[i].getSymbol().equals(currencyPair.counter.getCurrencyCode())) {

        BigDecimal last = bitcoinChartsTickers[i].getClose() != null ? bitcoinChartsTickers[i].getClose() : null;
        BigDecimal bid = bitcoinChartsTickers[i].getBid() != null ? bitcoinChartsTickers[i].getBid() : null;
        BigDecimal ask = bitcoinChartsTickers[i].getAsk() != null ? bitcoinChartsTickers[i].getAsk() : null;
        BigDecimal high = bitcoinChartsTickers[i].getHigh() != null ? bitcoinChartsTickers[i].getHigh() : null;
        BigDecimal low = bitcoinChartsTickers[i].getLow() != null ? bitcoinChartsTickers[i].getLow() : null;
        BigDecimal volume = bitcoinChartsTickers[i].getVolume();
        ZonedDateTime timeStamp = DateUtils.fromSecondsToZonedDateTime(bitcoinChartsTickers[i].getLatestTrade());

        return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume).timestamp(timeStamp)
            .build();

      }
    }
    return null;
  }

  public static ExchangeMetaData adaptMetaData(ExchangeMetaData exchangeMetaData, BitcoinChartsTicker[] tickers) {

    Map<CurrencyPair, CurrencyPairMetaData> pairs = new HashMap<>();

    for (BitcoinChartsTicker ticker : tickers) {
      BigDecimal anyPrice = firstNonNull(ticker.getAsk(), ticker.getBid(), ticker.getClose(), ticker.getHigh(), ticker.getHigh());
      int scale = anyPrice != null ? anyPrice.scale() : 0;
      pairs.put(new CurrencyPair(Currency.BTC, Currency.getInstance(ticker.getSymbol())), new CurrencyPairMetaData(null, null, null, scale));
    }

    return new ExchangeMetaData(pairs, exchangeMetaData.getCurrencies(), exchangeMetaData.getPublicRateLimits(),
        exchangeMetaData.getPrivateRateLimits(), exchangeMetaData.isShareRateLimits());
  }

  private static <T> T firstNonNull(T... objects) {

    for (T o : objects) {
      if (o != null) {
        return o;
      }
    }
    return null;
  }
}
