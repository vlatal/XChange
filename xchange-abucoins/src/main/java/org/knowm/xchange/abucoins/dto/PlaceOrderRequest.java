package org.knowm.xchange.abucoins.dto;

import org.knowm.xchange.abucoins.dto.trade.AbucoinsOrder;

import java.math.BigDecimal;

public class PlaceOrderRequest extends AbucoinsRequest {
  public final AbucoinsOrder.Type type;
  public final BigDecimal price;
  public final BigDecimal amount;

  public PlaceOrderRequest(AbucoinsOrder.Type type, BigDecimal price, BigDecimal amount) {
    this.type = type;
    this.price = price;
    this.amount = amount;
  }
}
