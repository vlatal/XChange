package org.knowm.xchange.taurus.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.utils.jackson.UnixTimestampDeserializer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Matija Mazi
 */
public class TaurusOrderBook {

  private final List<List<BigDecimal>> bids;
  private final List<List<BigDecimal>> asks;

  private ZonedDateTime timestamp;

  public TaurusOrderBook(@JsonProperty("bids") List<List<BigDecimal>> bids, @JsonProperty("asks") List<List<BigDecimal>> asks,
      @JsonProperty("timestamp") @JsonDeserialize(using = UnixTimestampDeserializer.class) ZonedDateTime timestamp) {
    this.bids = bids;
    this.asks = asks;
    this.timestamp = timestamp;
  }

  /**
   * (price, amount)
   */
  public List<List<BigDecimal>> getBids() {
    return bids;
  }

  /**
   * (price, amount)
   */
  public List<List<BigDecimal>> getAsks() {
    return asks;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "TaurusOrderBook [timestamp = " + timestamp + ", bids=" + bids + ", asks=" + asks + "]";
  }
}
