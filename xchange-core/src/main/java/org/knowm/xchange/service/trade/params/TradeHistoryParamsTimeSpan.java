package org.knowm.xchange.service.trade.params;

import org.knowm.xchange.service.trade.TradeService;

import java.time.ZonedDateTime;

/**
 * Parameters type for {@link TradeService#getTradeHistory(TradeHistoryParams)} with start and end timestamps.
 */
public interface TradeHistoryParamsTimeSpan extends TradeHistoryParams {

  void setStartTime(ZonedDateTime startTime);

  ZonedDateTime getStartTime();

  void setEndTime(ZonedDateTime endTime);

  ZonedDateTime getEndTime();

}
