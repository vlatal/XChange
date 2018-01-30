package org.knowm.xchange.btcmarkets.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import si.mazi.rescu.serialization.jackson.serializers.TimestampDeserializer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BTCMarketsTicker {

  //@JsonSerialize(using = BtcToSatoshi.class)
  //@JsonDeserialize(using = SatoshiToBtc.class)
  private final BigDecimal bestBid;

  //@JsonSerialize(using = BtcToSatoshi.class)
  //@JsonDeserialize(using = SatoshiToBtc.class)
  private final BigDecimal bestAsk;

  //@JsonSerialize(using = BtcToSatoshi.class)
  //@JsonDeserialize(using = SatoshiToBtc.class)
  private final BigDecimal lastPrice;

  private final String currency;

  private final String instrument;

  private final ZonedDateTime timestamp;

  public BTCMarketsTicker(@JsonProperty("bestBid") BigDecimal bestBid, @JsonProperty("bestAsk") BigDecimal bestAsk,
      @JsonProperty("lastPrice") BigDecimal lastPrice, @JsonProperty("currency") String currency, @JsonProperty("instrument") String instrument,
      @JsonProperty("timestamp") @JsonDeserialize(using = TimestampDeserializer.class) ZonedDateTime timestamp) {
    this.bestBid = bestBid;
    this.bestAsk = bestAsk;
    this.lastPrice = lastPrice;
    this.currency = currency;
    this.instrument = instrument;
    this.timestamp = timestamp;
  }

  public BigDecimal getBestBid() {
    return bestBid;
  }

  public BigDecimal getBestAsk() {
    return bestAsk;
  }

  public BigDecimal getLastPrice() {
    return lastPrice;
  }

  public String getCurrency() {
    return currency;
  }

  public String getInstrument() {
    return instrument;
  }

  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return String.format("BTCMarketsTicker{bestBid=%s, bestAsk=%s, lastPrice=%s, currency='%s', instrument='%s', timestamp=%s}", bestBid, bestAsk,
        lastPrice, currency, instrument, timestamp);
  }
}
