package org.knowm.xchange.independentreserve.dto.trade;

import org.knowm.xchange.independentreserve.dto.auth.AuthAggregate;
import org.knowm.xchange.independentreserve.dto.trade.IndependentReserveTransaction.Type;
import org.knowm.xchange.independentreserve.util.Util;

import java.time.ZonedDateTime;

/**
 * https://www.independentreserve.com/API#GetTransactions
 */
public class IndependentReserveTransactionsRequest extends AuthAggregate {

  public IndependentReserveTransactionsRequest(String apiKey, Long nonce, String accountGuid, ZonedDateTime fromTimestampUtc, ZonedDateTime toTimestampUtc,
                                               Type[] txTypes, int pageIndex, int pageSize) {
    super(apiKey, nonce);

    this.parameters.put("accountGuid", accountGuid);
    if (fromTimestampUtc != null) {
      this.parameters.put("fromTimestampUtc", Util.formatDate(fromTimestampUtc));
    }
    if (toTimestampUtc != null) {
      this.parameters.put("toTimestampUtc", Util.formatDate(toTimestampUtc));
    }
    if (txTypes != null) {
      this.parameters.put("txTypes", txTypes);
    }
    this.parameters.put("pageIndex", pageIndex);
    this.parameters.put("pageSize", pageSize);
  }
}
