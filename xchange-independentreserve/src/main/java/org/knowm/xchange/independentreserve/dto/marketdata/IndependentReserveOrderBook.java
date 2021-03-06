package org.knowm.xchange.independentreserve.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Author: Kamil Zbikowski Date: 4/9/15
 */
public class IndependentReserveOrderBook {
  private final ZonedDateTime createdTimestamp;
  private final List<OrderBookOrder> buyOrders;
  private final List<OrderBookOrder> sellOrders;

  private final String primaryCurrencyCode;
  private final String secondaryCurrencyCode;

  public IndependentReserveOrderBook(@JsonProperty("BuyOrders") List<OrderBookOrder> buyOrders,
      @JsonProperty("SellOrders") List<OrderBookOrder> sellOrders, @JsonProperty("PrimaryCurrencyCode") String primaryCurrencyCode,
      @JsonProperty("SecondaryCurrencyCode") String secondaryCurrencyCode,
      @JsonProperty("CreatedTimestampUtc") String createdTimestampUtc) throws com.fasterxml.jackson.databind.exc.InvalidFormatException, ParseException {
    this.buyOrders = buyOrders;
    this.createdTimestamp = org.knowm.xchange.utils.DateUtils.fromISODateStringToZonedDateTime(createdTimestampUtc) ;
    this.sellOrders = sellOrders;
    this.primaryCurrencyCode = primaryCurrencyCode;
    this.secondaryCurrencyCode = secondaryCurrencyCode;
  }

  public List<OrderBookOrder> getBuyOrders() {
    return buyOrders;
  }

  public ZonedDateTime getCreatedTimestamp() {
    return createdTimestamp;
  }

  public String getPrimaryCurrencyCode() {
    return primaryCurrencyCode;
  }

  public String getSecondaryCurrencyCode() {
    return secondaryCurrencyCode;
  }

  public List<OrderBookOrder> getSellOrders() {
    return sellOrders;
  }
}
