package org.knowm.xchange.bitcointoyou.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointoyou.BitcointoyouAdapters;
import org.knowm.xchange.bitcointoyou.dto.trade.BitcointoyouOrderResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.*;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * {@link TradeService} implementation for Bitcointoyou Exchange.
 *
 * @author Jonathas Carrijo
 * @author Danilo Guimaraes
 */
public class BitcointoyouTradeService extends BitcointoyouTradeServiceRaw implements TradeService {

  /**
   * Constructor
   *
   * @param exchange the Bitcointoyou Exchange
   */
  public BitcointoyouTradeService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    BitcointoyouOrderResponse bitcointoyouOpenOrders = returnOpenOrders();
    return BitcointoyouAdapters.adaptBitcointoyouOpenOrders(bitcointoyouOpenOrders);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    throw new NotAvailableFromExchangeException();
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    if (limitOrder.getType() == OrderType.BID) {
      BitcointoyouOrderResponse buy = buy(limitOrder);

      if (buy.getOrderList() != null && !buy.getOrderList().isEmpty()) {
        return buy.getOrderList().get(0).getId();
      }
    } else {
      BitcointoyouOrderResponse sell = sell(limitOrder);

      if (sell.getOrderList() != null && !sell.getOrderList().isEmpty()) {
        return sell.getOrderList().get(0).getId();
      }
    }

    return null;
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    return cancel(orderId);
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
    return false;
  }

  /**
   * @param params Can optionally implement {@link TradeHistoryParamCurrencyPair} and {@link TradeHistoryParamsTimeSpan}. All other TradeHistoryParams
   *        types will be ignored.
   */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }

  /**
   * Create {@link TradeHistoryParams} that supports {@link TradeHistoryParamsTimeSpan} and {@link TradeHistoryParamCurrencyPair}.
   */
  @Override
  public TradeHistoryParams createTradeHistoryParams() {

    return new BitcointoyouTradeHistoryParams();
  }

  public static class BitcointoyouTradeHistoryParams implements TradeHistoryParamCurrencyPair, TradeHistoryParamsTimeSpan {

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

	@Override
	public Collection<Order> getOrder(String... orderIds) throws ExchangeException, NotAvailableFromExchangeException,
			NotYetImplementedForExchangeException, IOException {

    if (orderIds.length == 1) {
      return BitcointoyouAdapters.adaptBitcointoyouOrderToOrdersCollection(returnOrderById(orderIds[0]));
    }

    // Bitcointoyou API doesn't support multiple-orders ID.
    throw new NotAvailableFromExchangeException();
	}

	@Override
	public OpenOrders getOpenOrders(OpenOrdersParams params) throws ExchangeException,
			NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    throw new NotAvailableFromExchangeException();
	}

	@Override
	public OpenOrdersParams createOpenOrdersParams() {
    throw new NotAvailableFromExchangeException();
	}

}
