package org.knowm.xchange.therock.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.therock.TheRock;
import org.knowm.xchange.therock.dto.TheRockException;
import org.knowm.xchange.therock.dto.marketdata.TheRockOrderBook;
import org.knowm.xchange.therock.dto.marketdata.TheRockTicker;
import org.knowm.xchange.therock.dto.marketdata.TheRockTrades;
import org.knowm.xchange.utils.DateUtils;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.time.ZonedDateTime;

public class TheRockMarketDataServiceRaw extends TheRockBaseService {

  private final TheRock theRock;

  public TheRockMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
    this.theRock = RestProxyFactory.createProxy(TheRock.class, exchange.getExchangeSpecification().getSslUri(), getClientConfig());
  }

  public TheRockTicker getTheRockTicker(TheRock.Pair currencyPair) throws TheRockException, IOException {
    return theRock.getTicker(currencyPair);
  }

  public TheRockOrderBook getTheRockOrderBook(TheRock.Pair currencyPair) throws TheRockException, IOException {
    return theRock.getOrderbook(currencyPair);
  }

  public TheRockTrades getTheRockTrades(TheRock.Pair currencyPair, Object[] args) throws IOException {
    ZonedDateTime after = null;
    if (args.length == 1) {
      Object arg = args[0];
      if (arg instanceof Number) {
        after = DateUtils.fromSecondsToZonedDateTime(((Number) arg).longValue());
      } else if (arg instanceof ZonedDateTime) {
        after = (ZonedDateTime) arg;
      }

    }
    return theRock.getTrades(currencyPair, after);
  }
}
