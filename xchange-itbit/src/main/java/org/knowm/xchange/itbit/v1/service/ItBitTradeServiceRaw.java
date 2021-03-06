package org.knowm.xchange.itbit.v1.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.itbit.v1.ItBitAdapters;
import org.knowm.xchange.itbit.v1.dto.ItBitException;
import org.knowm.xchange.itbit.v1.dto.trade.ItBitOrder;
import org.knowm.xchange.itbit.v1.dto.trade.ItBitPlaceOrderRequest;
import org.knowm.xchange.itbit.v1.dto.trade.ItBitTradeHistory;

import java.io.IOException;
import java.time.ZonedDateTime;

public class ItBitTradeServiceRaw extends ItBitBaseService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public ItBitTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public ItBitOrder[] getItBitOpenOrders(CurrencyPair currencyPair) throws IOException {
    CurrencyPair exchangePair = ItBitAdapters.adaptCurrencyPairToExchange(currencyPair);
    ItBitOrder[] orders = itBitAuthenticated.getOrders(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(),
        exchangePair.base.getCurrencyCode() + exchangePair.counter.getCurrencyCode(), "1",
        "1000", "open", walletId);

    return orders;
  }

  /**
   * Retrieves the set of orders with the given status.
   *
   * @param status
   * @return
   * @throws IOException
   */
  public ItBitOrder[] getItBitOrders(String status) throws IOException {

    ItBitOrder[] orders = itBitAuthenticated.getOrders(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), "XBTUSD", "1", "1000",
        status, walletId);

    return orders;
  }

  public ItBitOrder getItBitOrder(String orderId) throws IOException {

    ItBitOrder order = itBitAuthenticated.getOrder(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), walletId, orderId);

    return order;
  }

  public ItBitOrder placeItBitLimitOrder(LimitOrder limitOrder) throws IOException {

    String side = limitOrder.getType().equals(OrderType.BID) ? "buy" : "sell";
    CurrencyPair exchangePair = ItBitAdapters.adaptCurrencyPairToExchange(limitOrder.getCurrencyPair());
    String amount = ItBitAdapters.formatCryptoAmount(limitOrder.getOriginalAmount());
    String price = ItBitAdapters.formatFiatAmount(limitOrder.getLimitPrice());

    ItBitOrder postOrder = itBitAuthenticated.postOrder(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), walletId,
        new ItBitPlaceOrderRequest(side, "limit", exchangePair.base.getCurrencyCode(), amount, price,
            exchangePair.base.getCurrencyCode() + exchangePair.counter.getCurrencyCode()));

    return postOrder;
  }

  public void cancelItBitOrder(String orderId) throws IOException {

    itBitAuthenticated.cancelOrder(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), walletId, orderId);
  }

  public ItBitOrder[] getItBitOrderHistory(String currency, String pageNum, String pageLen) throws IOException {

    ItBitOrder[] orders = itBitAuthenticated.getOrders(signatureCreator, ZonedDateTime.now().toInstant().toEpochMilli(), exchange.getNonceFactory(), currency, pageNum, pageLen,
        "filled", walletId);
    return orders;
  }

  public ItBitTradeHistory getUserTradeHistory(String lastExecutionId, Integer page, Integer perPage, ZonedDateTime rangeStart,
      ZonedDateTime rangeEnd) throws IOException, ItBitException {
    return itBitAuthenticated.getUserTradeHistory(signatureCreator, System.currentTimeMillis(), exchange.getNonceFactory(), walletId, lastExecutionId,
        page, perPage, rangeStart, rangeEnd);
  }
}
