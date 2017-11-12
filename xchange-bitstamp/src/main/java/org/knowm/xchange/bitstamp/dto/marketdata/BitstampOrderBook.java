package org.knowm.xchange.bitstamp.dto.marketdata;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Matija Mazi
 */
public class BitstampOrderBook {

  private final ZonedDateTime timestamp;
  private final List<List<BigDecimal>> bids;
  private final List<List<BigDecimal>> asks;

  /**
   * Constructor
   *
   * @param timestamp
   * @param bids
   * @param asks
   */
  public BitstampOrderBook(@JsonProperty("timestamp") Long timestamp, @JsonProperty("bids") List<List<BigDecimal>> bids,
      @JsonProperty("asks") List<List<BigDecimal>> asks) {

    this.bids = bids;
    this.asks = asks;
    this.timestamp = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp * 1000), ZoneOffset.UTC);
  }

  /**
   * @return Timestamp in Unix milliseconds
   */
  public ZonedDateTime getTimestamp() {

    return timestamp;
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

  @Override
  public String toString() {

    return "BitstampOrderBook [timestamp=" + timestamp + ", bids=" + bids + ", asks=" + asks + "]";
  }

}
