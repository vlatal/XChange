package org.knowm.xchange.binance.service;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.FundingRecord.Type;
import org.knowm.xchange.service.trade.params.HistoryParamsFundingType;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrency;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;

import java.time.ZonedDateTime;

public class BinanceFundingHistoryParams implements TradeHistoryParamCurrency, TradeHistoryParamsTimeSpan, HistoryParamsFundingType {

  private Currency currency;
  private Type type;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;

  @Override
  public Currency getCurrency() {
    return currency;
  }

  @Override
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public Type getType() {
    return type;
  }

  @Override
  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  @Override
  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  @Override
  public ZonedDateTime getEndTime() {
    return endTime;
  }

  @Override
  public void setEndTime(ZonedDateTime endTime) {
    this.endTime = endTime;
  }
}
