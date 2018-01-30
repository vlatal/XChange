package org.knowm.xchange.poloniex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.poloniex.PoloniexAdapters;
import org.knowm.xchange.poloniex.PoloniexUtils;
import org.knowm.xchange.poloniex.dto.trade.PoloniexLimitOrder;
import org.knowm.xchange.poloniex.dto.trade.PoloniexOpenOrder;
import org.knowm.xchange.poloniex.dto.trade.PoloniexTradeResponse;
import org.knowm.xchange.poloniex.dto.trade.PoloniexUserTrade;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.*;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import org.knowm.xchange.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

public class PoloniexTradeService extends PoloniexTradeServiceRaw implements TradeService {

  public PoloniexTradeService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    return getOpenOrders(createOpenOrdersParams());
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws ExchangeException, IOException {
    CurrencyPair currencyPair = null;
    if (params instanceof OpenOrdersParamCurrencyPair) {
      currencyPair = ((OpenOrdersParamCurrencyPair) params).getCurrencyPair();
    }

    final Map<String, PoloniexOpenOrder[]> poloniexOpenOrders;
    if (currencyPair == null) {
      poloniexOpenOrders = returnOpenOrders();
    } else {
      final PoloniexOpenOrder[] cpOpenOrders = returnOpenOrders(currencyPair);
      poloniexOpenOrders = new HashMap<>(1);
      poloniexOpenOrders.put(PoloniexUtils.toPairString(currencyPair), cpOpenOrders);
    }
    return PoloniexAdapters.adaptPoloniexOpenOrders(poloniexOpenOrders);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    throw new NotAvailableFromExchangeException();
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    PoloniexTradeResponse response;
    if (limitOrder.getType() == OrderType.BID || limitOrder.getType() == OrderType.EXIT_ASK) {
      response = buy(limitOrder);
    } else {
      response = sell(limitOrder);
    }

    // The return value contains details of any trades that have been immediately executed as a result  
    // of this order. Make these available to the application if it has provided a PoloniexLimitOrder. 
    if (limitOrder instanceof PoloniexLimitOrder) {
      PoloniexLimitOrder raw = (PoloniexLimitOrder) limitOrder;
      raw.setResponse(response);
    }

    return response.getOrderNumber().toString();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    return cancel(orderId);
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
    if (orderParams instanceof CancelOrderByIdParams) {
      return cancelOrder(((CancelOrderByIdParams) orderParams).getOrderId());
    } else {
      return false;
    }
  }

  /**
   * @param params Can optionally implement {@link TradeHistoryParamCurrencyPair} and {@link TradeHistoryParamsTimeSpan}. All other TradeHistoryParams
   * types will be ignored.
   */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    CurrencyPair currencyPair = null;
    ZonedDateTime startTime = null;
    ZonedDateTime endTime = null;

    if (params instanceof TradeHistoryParamCurrencyPair) {
      currencyPair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
    }
    if (params instanceof TradeHistoryParamsTimeSpan) {
      startTime = ((TradeHistoryParamsTimeSpan) params).getStartTime();
      endTime = ((TradeHistoryParamsTimeSpan) params).getEndTime();
    }
    return getTradeHistory(currencyPair, DateUtils.toUnixTimeNullSafe(startTime), DateUtils.toUnixTimeNullSafe(endTime));
  }

  public BigDecimal getMakerFee() throws IOException {
    String value = getFeeInfo().get("makerFee");
    return new BigDecimal(value);
  }

  public BigDecimal getTakerFee() throws IOException {
    String value = getFeeInfo().get("takerFee");
    return new BigDecimal(value);
  }

  private UserTrades getTradeHistory(CurrencyPair currencyPair, final Long startTime, final Long endTime) throws IOException {

    List<UserTrade> trades = new ArrayList<>();
    if (currencyPair == null) {
      HashMap<String, PoloniexUserTrade[]> poloniexUserTrades = returnTradeHistory(startTime, endTime);
      if (poloniexUserTrades != null) {
        for (Map.Entry<String, PoloniexUserTrade[]> mapEntry : poloniexUserTrades.entrySet()) {
          currencyPair = PoloniexUtils.toCurrencyPair(mapEntry.getKey());
          for (PoloniexUserTrade poloniexUserTrade : mapEntry.getValue()) {
            trades.add(PoloniexAdapters.adaptPoloniexUserTrade(poloniexUserTrade, currencyPair));
          }
        }
      }
    } else {
      PoloniexUserTrade[] poloniexUserTrades = returnTradeHistory(currencyPair, startTime, endTime);
      if (poloniexUserTrades != null) {
        for (PoloniexUserTrade poloniexUserTrade : poloniexUserTrades) {
          trades.add(PoloniexAdapters.adaptPoloniexUserTrade(poloniexUserTrade, currencyPair));
        }
      }
    }

    return new UserTrades(trades, TradeSortType.SortByTimestamp);
  }

  /**
   * Create {@link TradeHistoryParams} that supports {@link TradeHistoryParamsTimeSpan} and {@link TradeHistoryParamCurrencyPair}.
   */

  @Override
  public TradeHistoryParams createTradeHistoryParams() {

    return new PoloniexTradeHistoryParams();
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return new DefaultOpenOrdersParamCurrencyPair();
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {
    //we need to get the open orders
    // for what is not an open order, we need to query one by one.
    // but this returns fills by order, that we need need to calculate the remaining quantity, average fill price, and order type (in adapter).
    throw new NotYetImplementedForExchangeException();
  }

  public final UserTrades getOrderTrades(Order order) throws IOException {
    return getOrderTrades(order.getId(), order.getCurrencyPair());
  }

  public UserTrades getOrderTrades(String orderId, CurrencyPair currencyPair) throws IOException {

    List<UserTrade> trades = new ArrayList<>();

    PoloniexUserTrade[] poloniexUserTrades = returnOrderTrades(orderId);
    if (poloniexUserTrades != null) {
      for (PoloniexUserTrade poloniexUserTrade : poloniexUserTrades) {
        poloniexUserTrade.setOrderNumber(orderId); // returnOrderTrades doesn't fill in orderId
        trades.add(PoloniexAdapters.adaptPoloniexUserTrade(poloniexUserTrade, currencyPair));
      }
    }

    return new UserTrades(trades, TradeSortType.SortByTimestamp);
  }

  public static class PoloniexTradeHistoryParams implements TradeHistoryParamCurrencyPair, TradeHistoryParamsTimeSpan {

    private final TradeHistoryParamsAll all = new TradeHistoryParamsAll();

    @Override
    public void setCurrencyPair(CurrencyPair value) {

      all.setCurrencyPair(value);
    }

    @Override
    public CurrencyPair getCurrencyPair() {

      return all.getCurrencyPair();
    }

    @Override
    public void setStartTime(ZonedDateTime value) {

      all.setStartTime(value);
    }

    @Override
    public ZonedDateTime getStartTime() {

      return all.getStartTime();
    }

    @Override
    public void setEndTime(ZonedDateTime value) {

      all.setEndTime(value);
    }

    @Override
    public ZonedDateTime getEndTime() {

      return all.getEndTime();
    }
  }

}
