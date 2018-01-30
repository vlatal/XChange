package org.knowm.xchange.bleutrade.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bleutrade.BleutradeAdapters;
import org.knowm.xchange.bleutrade.dto.account.BleutradeBalance;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.DefaultWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BleutradeAccountService extends BleutradeAccountServiceRaw implements AccountService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BleutradeAccountService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    List<BleutradeBalance> bleutradeBalances = getBleutradeBalances();
    return new AccountInfo(BleutradeAdapters.adaptBleutradeBalances(bleutradeBalances));
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {
    return withdraw(currency, amount, address);
  }

  @Override
  public String withdrawFunds(WithdrawFundsParams params) throws IOException {
    if (params instanceof DefaultWithdrawFundsParams) {
      DefaultWithdrawFundsParams defaultParams = (DefaultWithdrawFundsParams) params;
      return withdrawFunds(defaultParams.currency, defaultParams.amount, defaultParams.address);
    }
    throw new IllegalStateException("Don't know how to withdraw: " + params);
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws IOException {

    return getBleutradeDepositAddress(currency.toString()).getAddress();
  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {
    throw new NotAvailableFromExchangeException();
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {
    List<FundingRecord> fundingRecords = new ArrayList<>();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    dateFormat.withZone(ZoneOffset.UTC);

    try {
      for (WithdrawRecord record : withdrawalHistory()) {
        String label = record.label;

        BigDecimal amount = record.amount;
        BigDecimal fee = null;

        String[] parts = label.split(";");
        String address = null;
        if (parts.length == 3) {
          amount = new BigDecimal(parts[0]);
          address = parts[1];
          fee = new BigDecimal(parts[2]);
        }

        fundingRecords.add(new FundingRecord(
            address,
                ZonedDateTime.parse(record.timestamp, dateFormat),
            Currency.getInstance(record.coin),
            amount,
            record.id,
            record.transactionId,
            FundingRecord.Type.WITHDRAWAL,
            FundingRecord.Status.COMPLETE,
            null,
            fee,
            label
        ));
      }

      for (DepositRecord record : depositHistory()) {
        fundingRecords.add(new FundingRecord(
            null,
                ZonedDateTime.parse(record.timestamp, dateFormat),
            Currency.getInstance(record.coin),
            record.amount,
            record.id,
            null,
            FundingRecord.Type.DEPOSIT,
            FundingRecord.Status.COMPLETE,
            null,
            null,
            record.label
        ));
      }
    } catch (DateTimeParseException e) {
      throw new IllegalStateException("Should not happen", e);
    }

    return fundingRecords;
  }
}
