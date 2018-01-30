package org.knowm.xchange.taurus.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.utils.jackson.UnixTimestampDeserializer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author Matija Mazi
 */
public final class TaurusTicker {

  private final BigDecimal last;
  private final BigDecimal high;
  private final BigDecimal low;
  private final BigDecimal vwap;
  private final BigDecimal volume;
  private final BigDecimal bid;
  private final BigDecimal ask;
  private final ZonedDateTime timestamp;

  public TaurusTicker(@JsonProperty("last") BigDecimal last, @JsonProperty("high") BigDecimal high, @JsonProperty("low") BigDecimal low,
      @JsonProperty("vwap") BigDecimal vwap, @JsonProperty("volume") BigDecimal volume, @JsonProperty("bid") BigDecimal bid,
      @JsonProperty("ask") BigDecimal ask, @JsonProperty("timestamp") @JsonDeserialize(using = UnixTimestampDeserializer.class) ZonedDateTime timestamp) {
    this.last = last;
    this.high = high;
    this.low = low;
    this.vwap = vwap;
    this.volume = volume;
    this.bid = bid;
    this.ask = ask;
    this.timestamp = timestamp;
  }

  public BigDecimal getLast() {
    return last;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getVwap() {
    return vwap;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "TaurusTicker [last=" + last + ", high=" + high + ", low=" + low + ", vwap=" + vwap + ", volume=" + volume + ", bid=" + bid + ", ask="
        + ask + "]";
  }

}
