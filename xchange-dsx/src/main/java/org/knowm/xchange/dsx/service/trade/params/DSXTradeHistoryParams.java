package org.knowm.xchange.dsx.service.trade.params;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.*;

import java.time.ZonedDateTime;

public class DSXTradeHistoryParams implements TradeHistoryParamsIdSpan, TradeHistoryParamsTimeSpan, TradeHistoryParamCurrencyPair, TradeHistoryParamsSorted, TradeHistoryParamLimit {
  private String startId;
  private String endId;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;
  private Order order;
  private Integer limit;
  private CurrencyPair currencyPair;

  @Override
  public void setStartId(String startId) {
    this.startId = startId;
  }

  @Override
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  @Override
  public Integer getLimit() {
    return limit;
  }

  @Override
  public String getStartId() {
    return startId;
  }

  @Override
  public void setEndId(String endId) {
    this.endId = endId;
  }

  @Override
  public String getEndId() {
    return endId;
  }

  @Override
  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  @Override
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  @Override
  public void setEndTime(ZonedDateTime endTime) {
    this.endTime = endTime;
  }

  @Override
  public ZonedDateTime getEndTime() {
    return endTime;
  }

  @Override
  public Order getOrder() {
    return order;
  }

  @Override
  public void setOrder(Order order) {
    this.order = order;
  }

  @Override
  public void setCurrencyPair(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  @Override
  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }
}
