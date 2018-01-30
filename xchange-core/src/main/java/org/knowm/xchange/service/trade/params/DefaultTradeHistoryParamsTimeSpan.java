package org.knowm.xchange.service.trade.params;

import java.time.ZonedDateTime;

/**
 * Common implementation of {@link TradeHistoryParamsTimeSpan}.
 */
public class DefaultTradeHistoryParamsTimeSpan implements TradeHistoryParamsTimeSpan {

  private ZonedDateTime endTime;
  private ZonedDateTime startTime;

  public DefaultTradeHistoryParamsTimeSpan() {
  }

  public DefaultTradeHistoryParamsTimeSpan(ZonedDateTime startTime, ZonedDateTime endTime) {

    this.endTime = endTime;
    this.startTime = startTime;
  }

  public DefaultTradeHistoryParamsTimeSpan(ZonedDateTime startTime) {

    this.startTime = startTime;
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
  public void setStartTime(ZonedDateTime time) {

    startTime = time;
  }

  @Override
  public ZonedDateTime getStartTime() {

    return startTime;
  }
}
