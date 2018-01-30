package org.knowm.xchange.wex.v3.service.trade.params;

import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamPaging;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.wex.v3.WexAuthenticated;

import java.time.ZonedDateTime;

/**
 * Transaction History paging params which combine id and time parameters and sort order.
 *
 * @author Peter N. Steinmetz Date: 4/3/15 Time: 8:29 AM
 */
public class WexTransHistoryParams extends DefaultTradeHistoryParamPaging implements TradeHistoryParamsIdSpan, TradeHistoryParamsTimeSpan {
  private WexAuthenticated.SortOrder sortOrder;
  private String startId;
  private String endId;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;

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

  public void setSortOrder(WexAuthenticated.SortOrder sortOrder) {
    this.sortOrder = sortOrder;
  }

  public WexAuthenticated.SortOrder getSortOrder() {
    return sortOrder;
  }
}
