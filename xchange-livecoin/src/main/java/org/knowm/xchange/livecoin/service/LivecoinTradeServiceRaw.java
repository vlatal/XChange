package org.knowm.xchange.livecoin.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.livecoin.Livecoin;
import org.knowm.xchange.livecoin.LivecoinAdapters;
import org.knowm.xchange.livecoin.LivecoinExchange;
import org.knowm.xchange.service.trade.params.CancelOrderByCurrencyPair;
import org.knowm.xchange.service.trade.params.CancelOrderByIdParams;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.utils.DateUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LivecoinTradeServiceRaw extends LivecoinBaseService<Livecoin> {

  public LivecoinTradeServiceRaw(LivecoinExchange exchange) {
    super(Livecoin.class, exchange);
  }

  public List<LimitOrder> getAllOpenOrders() throws
      ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    LivecoinPaginatedResponse response = service.allClientOrders(apiKey, signatureCreator, "OPEN");

    List<LimitOrder> resp = new ArrayList<>();
    if (response.data == null)
      return resp;

    for (Map map : response.data) {
      Object statusRaw = map.get("orderStatus");
      if (statusRaw != null && (statusRaw.toString().equals("OPEN") || statusRaw.toString().equals("PARTIALLY_FILLED"))) {
        resp.add(LivecoinAdapters.adaptOpenOrder(map));
      }
    }
    return resp;
  }

  public List<UserTrade> tradeHistory(ZonedDateTime start, ZonedDateTime end, Integer limit, Long offset) throws IOException {
    List<Map> response = service.transactions(
        apiKey,
        signatureCreator,
        String.valueOf(DateUtils.toMillisNullSafe(start)),
        String.valueOf(DateUtils.toMillisNullSafe(end)),
        "BUY,SELL",
        limit,
        offset
    );

//        if (!response.success)
//            throw new ExchangeException("Failed to get trade history: " + response.errorMessage);

    List<UserTrade> resp = new ArrayList<>();
    for (Map map : response) {
      UserTrade fundingRecord = LivecoinAdapters.adaptUserTrade(map);

      resp.add(fundingRecord);
    }

    return resp;
  }

  public String makeMarketOrder(MarketOrder order) throws IOException {
    Map response;
    if (order.getType().equals(Order.OrderType.BID)) {
      response = service.buyWithMarketOrder(apiKey, signatureCreator, order.getCurrencyPair().toString(), order.getOriginalAmount());
    } else {
      response = service.sellWithMarketOrder(apiKey, signatureCreator, order.getCurrencyPair().toString(), order.getOriginalAmount());
    }

    return response.get("orderId").toString();
  }

  public String makeLimitOrder(LimitOrder order) throws IOException {
    Map response;
    if (order.getType().equals(Order.OrderType.BID)) {
      response = service.buyWithLimitOrder(apiKey, signatureCreator, order.getCurrencyPair().toString(), order.getLimitPrice(), order.getOriginalAmount());
    } else {
      response = service.sellWithLimitOrder(apiKey, signatureCreator, order.getCurrencyPair().toString(), order.getLimitPrice(), order.getOriginalAmount());
    }

    if (response.containsKey("success") && !Boolean.valueOf(response.get("success").toString()))
      throw new ExchangeException("Failed to place order " + response);

    return response.get("orderId").toString();
  }

  public boolean cancelOrder(String orderId) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException {
    throw new ExchangeException("You need to provide the currency pair to cancel an order.");
  }

  public boolean cancelOrder(CurrencyPair currencyPair, String orderId) throws ExchangeException, NotAvailableFromExchangeException,
      NotYetImplementedForExchangeException,
      IOException {
    return cancelOrder(new LiveCoinCancelOrderParams(currencyPair, orderId));
  }

  public boolean cancelOrder(CancelOrderParams params) throws IOException {
    if (!(params instanceof CancelOrderByCurrencyPair) && !(params instanceof CancelOrderByIdParams)) {
      throw new ExchangeException("You need to provide the currency pair and the order id to cancel an order.");
    }
    CancelOrderByCurrencyPair paramCurrencyPair = (CancelOrderByCurrencyPair) params;
    CancelOrderByIdParams paramId = (CancelOrderByIdParams) params;

    Map response = service.cancelLimitOrder(apiKey, signatureCreator, paramCurrencyPair.getCurrencyPair().toString(), Long.valueOf(paramId.getOrderId()));

    if (response.containsKey("success") && !Boolean.valueOf(response.get("success").toString()))
      throw new ExchangeException("Failed to cancel order " + response);

    return Boolean.valueOf(response.get("cancelled").toString());
  }

  public static class LiveCoinCancelOrderParams implements CancelOrderByIdParams, CancelOrderByCurrencyPair {
    public final CurrencyPair currencyPair;
    public final String orderId;

    public LiveCoinCancelOrderParams(CurrencyPair currencyPair, String orderId) {
      this.currencyPair = currencyPair;
      this.orderId = orderId;
    }

    @Override
    public String getOrderId() {
      return orderId;
    }

    @Override
    public CurrencyPair getCurrencyPair() {
      return currencyPair;
    }
  }
}
