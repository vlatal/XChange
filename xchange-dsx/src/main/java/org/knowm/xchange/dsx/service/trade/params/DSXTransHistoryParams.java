package org.knowm.xchange.dsx.service.trade.params;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dsx.dto.trade.DSXTransHistoryResult;
import org.knowm.xchange.service.trade.params.*;

import java.time.ZonedDateTime;

public class DSXTransHistoryParams implements TradeHistoryParamsIdSpan, TradeHistoryParamsTimeSpan, TradeHistoryParamCurrency, TradeHistoryParamsSorted, TradeHistoryParamLimit {

  private DSXTransHistoryResult.Status status;
  private DSXTransHistoryResult.Type type;
  private String startId;
  private String endId;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;
  private Currency currency;
  private Order order;
  private Integer limit;

  public DSXTransHistoryResult.Status getStatus() {
    return status;
  }

  public void setStatus(DSXTransHistoryResult.Status status) {
    this.status = status;
  }

  public DSXTransHistoryResult.Type getType() {
    return type;
  }

  public void setType(DSXTransHistoryResult.Type type) {
    this.type = type;
  }

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
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public Currency getCurrency() {
    return currency;
  }
}
