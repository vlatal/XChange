package org.knowm.xchange.coinbase;

import org.knowm.xchange.coinbase.dto.account.CoinbaseUser;
import org.knowm.xchange.coinbase.dto.marketdata.CoinbaseHistoricalSpotPrice;
import org.knowm.xchange.coinbase.dto.marketdata.CoinbaseMoney;
import org.knowm.xchange.coinbase.dto.marketdata.CoinbasePrice;
import org.knowm.xchange.coinbase.dto.marketdata.CoinbaseSpotPriceHistory;
import org.knowm.xchange.coinbase.dto.trade.CoinbaseTransfer;
import org.knowm.xchange.coinbase.dto.trade.CoinbaseTransferType;
import org.knowm.xchange.coinbase.dto.trade.CoinbaseTransfers;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * jamespedwards42
 */
public final class CoinbaseAdapters {

  private CoinbaseAdapters() {

  }

  public static AccountInfo adaptAccountInfo(CoinbaseUser user) {

    final String username = user.getEmail();
    final CoinbaseMoney money = user.getBalance();
    final Balance balance = new Balance(Currency.getInstance(money.getCurrency()), money.getAmount());

    final AccountInfo accountInfoTemporaryName = new AccountInfo(username, new Wallet(balance));
    return accountInfoTemporaryName;
  }

  public static UserTrades adaptTrades(CoinbaseTransfers transfers) {

    final List<UserTrade> trades = new ArrayList<>();
    for (CoinbaseTransfer transfer : transfers.getTransfers()) {
      trades.add(adaptTrade(transfer));
    }

    return new UserTrades(trades, TradeSortType.SortByTimestamp);
  }

  public static UserTrade adaptTrade(CoinbaseTransfer transfer) {

    final OrderType orderType = adaptOrderType(transfer.getType());
    final CoinbaseMoney btcAmount = transfer.getBtcAmount();
    final BigDecimal originalAmount = btcAmount.getAmount();
    final String tradableIdentifier = btcAmount.getCurrency();
    final CoinbaseMoney subTotal = transfer.getSubtotal();
    final String transactionCurrency = subTotal.getCurrency();
    final BigDecimal price = subTotal.getAmount().divide(originalAmount, RoundingMode.HALF_EVEN);
    final ZonedDateTime timestamp = transfer.getCreatedAt();
    final String id = transfer.getTransactionId();
    final String transferId = transfer.getId();
    final BigDecimal feeAmount = transfer.getCoinbaseFee().getAmount();
    final String feeCurrency = transfer.getCoinbaseFee().getCurrency();

    return new UserTrade(orderType, originalAmount, new CurrencyPair(tradableIdentifier, transactionCurrency), price, timestamp, id, transferId,
        feeAmount, Currency.getInstance(feeCurrency));
  }

  public static OrderType adaptOrderType(CoinbaseTransferType transferType) {

    switch (transferType) {
      case BUY:
        return OrderType.BID;
      case SELL:
        return OrderType.ASK;
    }
    return null;
  }

  public static Ticker adaptTicker(CurrencyPair currencyPair, final CoinbasePrice buyPrice, final CoinbasePrice sellPrice,
      final CoinbaseMoney spotRate, final CoinbaseSpotPriceHistory coinbaseSpotPriceHistory) {

    final Ticker.Builder tickerBuilder = new Ticker.Builder().currencyPair(currencyPair).ask(buyPrice.getSubTotal().getAmount())
        .bid(sellPrice.getSubTotal().getAmount()).last(spotRate.getAmount());

    // Get the 24 hour high and low spot price if the history is provided.
    if (coinbaseSpotPriceHistory != null) {
      BigDecimal observedHigh = spotRate.getAmount();
      BigDecimal observedLow = spotRate.getAmount();
      ZonedDateTime twentyFourHoursAgo = null;
      // The spot price history list is sorted in descending order by timestamp when deserialized.
      for (CoinbaseHistoricalSpotPrice historicalSpotPrice : coinbaseSpotPriceHistory.getSpotPriceHistory()) {

        if (twentyFourHoursAgo == null) {
          twentyFourHoursAgo = DateUtils.fromMillisToZonedDateTime(historicalSpotPrice.getTimestamp().minusDays(1).toInstant().toEpochMilli());
        } else if (historicalSpotPrice.getTimestamp().isBefore(twentyFourHoursAgo)) {
          break;
        }

        final BigDecimal spotPriceAmount = historicalSpotPrice.getSpotRate();
        if (spotPriceAmount.compareTo(observedLow) < 0) {
          observedLow = spotPriceAmount;
        } else if (spotPriceAmount.compareTo(observedHigh) > 0) {
          observedHigh = spotPriceAmount;
        }
      }
      tickerBuilder.high(observedHigh).low(observedLow);
    }

    return tickerBuilder.build();
  }

}
