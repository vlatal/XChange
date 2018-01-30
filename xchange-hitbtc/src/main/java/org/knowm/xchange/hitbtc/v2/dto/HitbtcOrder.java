package org.knowm.xchange.hitbtc.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class HitbtcOrder {

  public final String id;
  public final String clientOrderId;
  public final String symbol;
  public final String side;
  public final String status;
  public final String type;
  public final String timeInForce;
  public final BigDecimal quantity;
  public final BigDecimal price;
  public final BigDecimal cumQuantity;

  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;

  public HitbtcOrder(@JsonProperty("id") String id, @JsonProperty("clientOrderId") String clientOrderId
      , @JsonProperty("symbol") String symbol, @JsonProperty("side") String side, @JsonProperty("status") String status
      , @JsonProperty("type") String type, @JsonProperty("timeInForce") String timeInForce
      , @JsonProperty("quantity") BigDecimal quantity, @JsonProperty("price") BigDecimal price
      , @JsonProperty("cumQuantity") BigDecimal cumQuantity, @JsonProperty("createdAt") ZonedDateTime createdAt
      , @JsonProperty("updatedAt") ZonedDateTime updatedAt) {
    super();
    this.id = id;
    this.clientOrderId = clientOrderId;
    this.symbol = symbol;
    this.side = side;
    this.status = status;
    this.type = type;
    this.timeInForce = timeInForce;
    this.quantity = quantity;
    this.price = price;
    this.cumQuantity = cumQuantity;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public String toString() {
    return "HitbtcNewOrderResponse [id=" + id + ", clientOrderId=" + clientOrderId + ", symbol=" + symbol + ", side=" + side
        + ", status=" + status + ", type=" + type + ", timeInForce=" + timeInForce + ", quantity=" + quantity + ", price="
        + price + ", cumQuantity=" + cumQuantity + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
  }

}
