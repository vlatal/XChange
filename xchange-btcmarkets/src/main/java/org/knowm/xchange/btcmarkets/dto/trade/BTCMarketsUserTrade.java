package org.knowm.xchange.btcmarkets.dto.trade;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.knowm.xchange.utils.jackson.BtcToSatoshi;
import org.knowm.xchange.utils.jackson.MillisecTimestampDeserializer;
import org.knowm.xchange.utils.jackson.SatoshiToBtc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BTCMarketsUserTrade {

  private Long id;

  private Long orderId;

  private String description;

  @JsonSerialize(using = BtcToSatoshi.class)
  @JsonDeserialize(using = SatoshiToBtc.class)
  private BigDecimal price;

  @JsonSerialize(using = BtcToSatoshi.class)
  @JsonDeserialize(using = SatoshiToBtc.class)
  private BigDecimal volume;

  @JsonSerialize(using = BtcToSatoshi.class)
  @JsonDeserialize(using = SatoshiToBtc.class)
  private BigDecimal fee;

  private BTCMarketsOrder.Side side;

  @JsonDeserialize(using = MillisecTimestampDeserializer.class)
  private ZonedDateTime creationTime;

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public ZonedDateTime getCreationTime() {
    return creationTime;
  }

  public BTCMarketsOrder.Side getSide() {
    return side;
  }

  public Long getOrderId() {
    return orderId;
  }

  @Override
  public String toString() {
    return String.format("BTCMarketsUserTrade{id=%d, side='%s', description='%s', price=%s, volume=%s, fee=%s, creationTime=%s, orderId=%s}", id,
        side, description, price, volume, fee, creationTime, orderId);
  }
}
