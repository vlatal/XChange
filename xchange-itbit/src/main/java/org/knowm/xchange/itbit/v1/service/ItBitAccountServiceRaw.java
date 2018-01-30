package org.knowm.xchange.itbit.v1.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.itbit.v1.ItBitAdapters;
import org.knowm.xchange.itbit.v1.dto.ItBitFundingHistoryResponse;
import org.knowm.xchange.itbit.v1.dto.account.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItBitAccountServiceRaw extends ItBitBaseService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public ItBitAccountServiceRaw(Exchange exchange) {

    super(exchange);

  }

  public ItBitAccountInfoReturn[] getItBitAccountInfo() throws IOException {

    ItBitAccountInfoReturn[] info = itBitAuthenticated.getInfo(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), userId);
    return info;
  }

  public List getAllWallets() {
    return itBitAuthenticated.wallets(signatureCreator, exchange.getNonceFactory(), ZonedDateTime.now().toInstant().toEpochMilli(), userId, 1, 50);
  }

  public ItBitFundingHistoryResponse getFunding(int page, int perPage) {
    return itBitAuthenticated.fundingHistory(signatureCreator, exchange.getNonceFactory(), ZonedDateTime.now().toInstant().toEpochMilli(), walletId, page, perPage);
  }

  public String withdrawItBitFunds(String currency, BigDecimal amount, String address) throws IOException {

    String formattedAmount = ItBitAdapters.formatCryptoAmount(amount);

    ItBitWithdrawalRequest request = new ItBitWithdrawalRequest(currency, formattedAmount, address);
    ItBitWithdrawalResponse response = itBitAuthenticated.requestWithdrawal(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(),
        walletId, request);
    return response.getId();
  }

  public String requestItBitDepositAddress(String currency, String... args) throws IOException {

    Map<String, String> metadata = new HashMap<>();
    for (int i = 0; i < args.length - 1; i += 2) {
      metadata.put(args[i], args[i + 1]);
    }

    ItBitDepositRequest request = new ItBitDepositRequest(currency, metadata);
    ItBitDepositResponse response = itBitAuthenticated.requestDeposit(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), walletId,
        request);
    return response.getDepositAddress();
  }

  public ItBitAccountInfoReturn getItBitAccountInfo(String walletId) throws IOException {

    ItBitAccountInfoReturn itBitAccountInfoReturn = itBitAuthenticated.getWallet(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(),
        walletId);
    return itBitAccountInfoReturn;
  }
}
