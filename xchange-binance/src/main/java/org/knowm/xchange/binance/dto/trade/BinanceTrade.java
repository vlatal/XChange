package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public final class BinanceTrade {

  public final long id;
  public final long orderId;
  public final BigDecimal price;
  public final BigDecimal qty;
  public final BigDecimal commission;
  public final String commissionAsset;
  public final long time;
  public final boolean isBuyer;
  public final boolean isMaker;
  public final boolean isBestMatch;

  public BinanceTrade(@JsonProperty("id") long id
      , @JsonProperty("orderId") long orderId
      , @JsonProperty("price") BigDecimal price
      , @JsonProperty("qty") BigDecimal qty
      , @JsonProperty("commission") BigDecimal commission
      , @JsonProperty("commissionAsset") String commissionAsset
      , @JsonProperty("time") long time
      , @JsonProperty("isBuyer") boolean isBuyer
      , @JsonProperty("isMaker") boolean isMaker
      , @JsonProperty("isBestMatch") boolean isBestMatch
  ) {
    this.id = id;
    this.orderId = orderId;
    this.price = price;
    this.qty = qty;
    this.commission = commission;
    this.commissionAsset = commissionAsset;
    this.time = time;
    this.isBuyer = isBuyer;
    this.isMaker = isMaker;
    this.isBestMatch = isBestMatch;
  }

  public ZonedDateTime getTime() {
    return DateUtils.fromMillisToZonedDateTime(time);
  }
}
