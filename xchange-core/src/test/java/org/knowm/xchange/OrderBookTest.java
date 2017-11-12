package org.knowm.xchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.OrderBookUpdate;
import org.knowm.xchange.dto.trade.LimitOrder;

public class OrderBookTest {

  private OrderBook orderBook;

  @Before
  public void setUp() throws Exception {

    LimitOrder askOrder = new LimitOrder(OrderType.ASK, BigDecimal.ONE, CurrencyPair.BTC_USD, "", null, BigDecimal.TEN.add(BigDecimal.ONE));
    LimitOrder bidOrder = new LimitOrder(OrderType.BID, BigDecimal.ONE, CurrencyPair.BTC_USD, "", null, BigDecimal.TEN);

    List<LimitOrder> asks = new ArrayList<>(Arrays.asList(askOrder));
    List<LimitOrder> bids = new ArrayList<>(Arrays.asList(bidOrder));
    ZonedDateTime timeStamp = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    orderBook = new OrderBook(timeStamp, asks, bids);

  }

  @Test
  public void testUpdateAddOrder() {

	ZonedDateTime timeStamp = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    OrderBookUpdate lowerBidUpdate = new OrderBookUpdate(OrderType.BID, BigDecimal.ONE, CurrencyPair.BTC_USD, BigDecimal.TEN.subtract(BigDecimal.ONE),
        timeStamp, BigDecimal.ONE);
    orderBook.update(lowerBidUpdate);
    assertThat(orderBook.getBids().size()).isEqualTo(2);
  }

  @Test
  public void testUpdateRemoveOrder() {

	ZonedDateTime timeStamp = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    OrderBookUpdate lowerBidUpdate = new OrderBookUpdate(OrderType.BID, BigDecimal.ONE, CurrencyPair.BTC_USD, BigDecimal.TEN, timeStamp,
        BigDecimal.ZERO);
    orderBook.update(lowerBidUpdate);
    assertThat(orderBook.getBids().size()).isEqualTo(0);
  }

  @Test
  public void testUpdateAddVolume() {

    ZonedDateTime timeStamp = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    OrderBookUpdate lowerBidUpdate = new OrderBookUpdate(OrderType.BID, BigDecimal.ONE, CurrencyPair.BTC_USD, BigDecimal.TEN, timeStamp,
        BigDecimal.TEN);
    orderBook.update(lowerBidUpdate);
    assertThat(orderBook.getBids().size()).isEqualTo(1);
    assertThat(orderBook.getBids().get(0).getOriginalAmount()).isEqualTo(BigDecimal.TEN);
  }

  @Test
  public void testDateSame() {

	ZonedDateTime timeStamp = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    OrderBookUpdate lowerBidUpdate = new OrderBookUpdate(OrderType.BID, BigDecimal.ONE, CurrencyPair.BTC_USD, BigDecimal.TEN, timeStamp,
        BigDecimal.TEN);
    ZonedDateTime oldDate = orderBook.getTimeStamp();
    orderBook.update(lowerBidUpdate);
    assertThat(orderBook.getTimeStamp()).isEqualTo(oldDate);
  }

  @Test
  public void testDateOther() {

    ZonedDateTime timeStamp = ZonedDateTime.ofInstant(Instant.ofEpochMilli(10), ZoneOffset.UTC);
    OrderBookUpdate lowerBidUpdate = new OrderBookUpdate(OrderType.BID, BigDecimal.ONE, CurrencyPair.BTC_USD, BigDecimal.TEN, timeStamp,
        BigDecimal.TEN);
    ZonedDateTime oldDate = orderBook.getTimeStamp();
    orderBook.update(lowerBidUpdate);
    assertThat(orderBook.getTimeStamp().isAfter(oldDate)).isTrue();
    assertThat(orderBook.getTimeStamp()).isEqualTo(timeStamp);

  }
}
