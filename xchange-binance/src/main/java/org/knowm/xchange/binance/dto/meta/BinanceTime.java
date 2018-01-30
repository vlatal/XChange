package org.knowm.xchange.binance.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.utils.DateUtils;

import java.time.ZonedDateTime;

public class BinanceTime {

  @JsonProperty
  private long serverTime;

  public ZonedDateTime getServerTime() {
    return DateUtils.fromMillisToZonedDateTime(serverTime);
  }
}
