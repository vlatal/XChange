package org.knowm.xchange.ripple.dto.trade;

import org.knowm.xchange.ripple.dto.RippleAmount;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public interface IRippleTradeTransaction {
  List<RippleAmount> getBalanceChanges();

  BigDecimal getFee();

  long getOrderId();

  String getHash();

  ZonedDateTime getTimestamp();
}
