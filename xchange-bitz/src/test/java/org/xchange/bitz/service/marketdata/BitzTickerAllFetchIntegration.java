package org.xchange.bitz.service.marketdata;

import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.xchange.bitz.BitZExchange;
import org.xchange.bitz.service.BitZMarketDataService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BitzTickerAllFetchIntegration {

  @Test
  public void tickerFetchTest() throws Exception {
    // Get Specific Exchange
    BitZExchange exchange = (BitZExchange) ExchangeFactory.INSTANCE.createExchange(BitZExchange.class.getName());
    BitZMarketDataService marketDataService = (BitZMarketDataService) exchange.getMarketDataService();
    
    // Poll All Tickers
    List<Ticker> tickers = marketDataService.getTickers();
    
    // Verify Not Null Values
    assertThat(tickers).isNotNull();
    assertThat(tickers).isNotEmpty();
    
    // TODO: Logical Verification Of Values
  }

}
