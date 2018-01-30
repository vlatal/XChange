package org.knowm.xchange.bittrex.dto.trade;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BittrexLimitOrder extends LimitOrder {


  public BittrexLimitOrder(OrderType type, BigDecimal originalAmount, CurrencyPair currencyPair, String id, ZonedDateTime timestamp, BigDecimal limitPrice,
                           BigDecimal quantityRemaining, BigDecimal pricePerUnit) {
    super(type, originalAmount, quantityRemaining == null ? null : originalAmount.subtract(quantityRemaining), currencyPair, id, timestamp, limitPrice);
    this.setAveragePrice(pricePerUnit);

  }

  public BittrexLimitOrder(OrderType type, BigDecimal originalAmount, CurrencyPair currencyPair, String id, ZonedDateTime timestamp, BigDecimal limitPrice,
      BigDecimal quantityRemaining, BigDecimal pricePerUnit, OrderStatus status) {
    super(type,
        originalAmount,
        currencyPair,
        id,
        timestamp,
        limitPrice,
        pricePerUnit,
        quantityRemaining == null ? null : originalAmount.subtract(quantityRemaining),
        status);

  }


}
