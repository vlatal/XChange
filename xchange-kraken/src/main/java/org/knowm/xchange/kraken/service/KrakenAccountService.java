package org.knowm.xchange.kraken.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.kraken.KrakenAdapters;
import org.knowm.xchange.kraken.dto.account.KrakenDepositAddress;
import org.knowm.xchange.kraken.dto.account.KrakenLedger;
import org.knowm.xchange.kraken.dto.account.LedgerType;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class KrakenAccountService extends KrakenAccountServiceRaw implements AccountService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public KrakenAccountService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    return new AccountInfo(exchange.getExchangeSpecification().getUserName(), KrakenAdapters.adaptWallet(getKrakenBalance()));
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws IOException {
    return withdraw(null, currency.toString(), address, amount).getRefid();
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
    KrakenDepositAddress[] depositAddresses = getDepositAddresses(currency.toString(), "Bitcoin", false);
    return KrakenAdapters.adaptKrakenDepositAddress(depositAddresses);
  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {
    return new KrakenFundingHistoryParams(null, null, null, (Currency[]) null);
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {

    ZonedDateTime startTime = null;
    ZonedDateTime endTime = null;
    if (params instanceof TradeHistoryParamsTimeSpan) {
      TradeHistoryParamsTimeSpan timeSpanParam = (TradeHistoryParamsTimeSpan) params;
      startTime = timeSpanParam.getStartTime();
      endTime = timeSpanParam.getEndTime();
    }

    Long offset = null;
    if (params instanceof TradeHistoryParamOffset) {
      offset = ((TradeHistoryParamOffset) params).getOffset();
    }

    Currency[] currencies = null;
    if (params instanceof TradeHistoryParamCurrencies) {
      final TradeHistoryParamCurrencies currenciesParam = (TradeHistoryParamCurrencies) params;
      if (currenciesParam.getCurrencies() != null) {
        currencies = currenciesParam.getCurrencies();
      }
    }

    LedgerType ledgerType = null;
    if (params instanceof HistoryParamsFundingType) {
      final FundingRecord.Type type = ((HistoryParamsFundingType) params).getType();
      ledgerType = type == FundingRecord.Type.DEPOSIT ? LedgerType.DEPOSIT : type == FundingRecord.Type.WITHDRAWAL ? LedgerType.WITHDRAWAL : null;
    }

    if (ledgerType == null) {
      Map<String, KrakenLedger> ledgerEntries = getKrakenLedgerInfo(LedgerType.DEPOSIT, startTime, endTime, offset, currencies);
      ledgerEntries.putAll(getKrakenLedgerInfo(LedgerType.WITHDRAWAL, startTime, endTime, offset, currencies));
      return KrakenAdapters.adaptFundingHistory(ledgerEntries);
    } else {
      return KrakenAdapters.adaptFundingHistory(getKrakenLedgerInfo(ledgerType, startTime, endTime, offset, currencies));
    }
  }

  public static class KrakenFundingHistoryParams extends DefaultTradeHistoryParamsTimeSpan
      implements TradeHistoryParamOffset, TradeHistoryParamCurrencies, HistoryParamsFundingType {

    private Long offset;
    private Currency[] currencies;
    private FundingRecord.Type type;

    public KrakenFundingHistoryParams(final ZonedDateTime startTime, final ZonedDateTime endTime, final Long offset, final Currency... currencies) {
      super(startTime, endTime);
      this.offset = offset;
      this.currencies = currencies;
    }

    @Override
    public void setOffset(final Long offset) {
      this.offset = offset;
    }

    @Override
    public Long getOffset() {
      return offset;
    }

    @Override
    public void setCurrencies(Currency[] currencies) {
      this.currencies = currencies;
    }

    @Override
    public Currency[] getCurrencies() {
      return this.currencies;
    }

    @Override
    public FundingRecord.Type getType() {
      return type;
    }

    @Override
    public void setType(FundingRecord.Type type) {
      this.type = type;
    }
  }

}
