package org.knowm.xchange.dsx.dto.account;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrency;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;

import java.time.ZonedDateTime;

public class DSXTransactionHistoryParams implements TradeHistoryParamsTimeSpan, TradeHistoryParamsIdSpan, TradeHistoryParamCurrency {

  private Currency currency = null;
  private String startId = null;
  private String endId = null;
  private ZonedDateTime startTime = null;
  private ZonedDateTime endTime = null;

  @Override
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public Currency getCurrency() {
    return currency;
  }

  @Override
  public void setStartId(String startId) {
    this.startId = startId;
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
}
