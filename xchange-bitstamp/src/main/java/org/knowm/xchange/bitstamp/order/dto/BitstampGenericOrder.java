package org.knowm.xchange.bitstamp.order.dto;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BitstampGenericOrder extends Order {

    public BitstampGenericOrder(
            OrderType type,
            BigDecimal originalAmount,
            CurrencyPair currencyPair,
            String id, ZonedDateTime timestamp,
            BigDecimal averagePrice,
            BigDecimal cumulativeAmount,
            OrderStatus status) {

        super(type, originalAmount, currencyPair, id, timestamp, averagePrice, cumulativeAmount, status);
    }

}