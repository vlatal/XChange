package org.knowm.xchange.abucoins.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.abucoins.AbucoinsExchange;
import org.knowm.xchange.abucoins.dto.marketdata.AbucoinsHistoricRate;
import org.knowm.xchange.abucoins.dto.marketdata.AbucoinsProductStat;
import org.knowm.xchange.abucoins.service.AbucoinsMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author bryant_harris
 */
public class TickerFetchIntegration {

  @Test
  public void tickerFetchTest() throws Exception {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(AbucoinsExchange.class.getName());
    MarketDataService marketDataService = exchange.getMarketDataService();
    Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USD);
    System.out.println(ticker.toString());
    assertThat(ticker).isNotNull();
    
    OrderBook orderBook = marketDataService.getOrderBook(CurrencyPair.BTC_USD);
    System.out.println(orderBook.toString());
    
    Trades trades = marketDataService.getTrades(CurrencyPair.BTC_USD);
    System.out.println(trades.toString());

    ZonedDateTime start = ZonedDateTime.now().minusDays(60);
    ZonedDateTime end = start.plusDays(15);
    AbucoinsMarketDataService abucoinsMarketData = (AbucoinsMarketDataService) marketDataService; 
    AbucoinsHistoricRate[] historicRates = abucoinsMarketData.getAbucoinsHistoricRates("BTC-USD", 60, start, end);
    System.out.println( Arrays.asList(historicRates));
    
    AbucoinsProductStat[] stats = abucoinsMarketData.getAbucoinsProductStats();
    System.out.println( Arrays.asList(stats));
  }
}
