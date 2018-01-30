package org.knowm.xchange.bitfinex.v1;

import org.knowm.xchange.bitfinex.v1.dto.BitfinexException;
import org.knowm.xchange.bitfinex.v1.dto.account.*;
import org.knowm.xchange.bitfinex.v1.dto.trade.*;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BitfinexAuthenticated extends Bitfinex {

  @POST
  @Path("account_infos")
  BitfinexAccountInfosResponse[] accountInfos(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexNonceOnlyRequest accountInfosRequest) throws IOException, BitfinexException;

  @POST
  @Path("order/new")
  BitfinexOrderStatusResponse newOrder(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexNewOrderRequest newOrderRequest) throws IOException, BitfinexException;

  @POST
  @Path("order/new/multi")
  BitfinexNewOrderMultiResponse newOrderMulti(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature,
      BitfinexNewOrderMultiRequest newOrderMultiRequest) throws IOException, BitfinexException;

  @POST
  @Path("offer/new")
  BitfinexOfferStatusResponse newOffer(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexNewOfferRequest newOfferRequest) throws IOException, BitfinexException;

  @POST
  @Path("balances")
  BitfinexBalancesResponse[] balances(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexBalancesRequest balancesRequest) throws IOException, BitfinexException;

  @POST
  @Path("order/cancel")
  BitfinexOrderStatusResponse cancelOrders(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexCancelOrderRequest cancelOrderRequest) throws IOException, BitfinexException;

  @POST
  @Path("order/cancel/multi")
  BitfinexCancelOrderMultiResponse cancelOrderMulti(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature,
      BitfinexCancelOrderMultiRequest cancelOrderRequest) throws IOException, BitfinexException;

  @POST
  @Path("order/cancel/replace")
  BitfinexOrderStatusResponse replaceOrder(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexReplaceOrderRequest newOrderRequest) throws IOException, BitfinexException;

  @POST
  @Path("offer/cancel")
  BitfinexOfferStatusResponse cancelOffer(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexCancelOfferRequest cancelOfferRequest) throws IOException, BitfinexException;

  @POST
  @Path("orders")
  BitfinexOrderStatusResponse[] activeOrders(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexNonceOnlyRequest nonceOnlyRequest) throws IOException, BitfinexException;

  @POST
  @Path("orders/hist")
  BitfinexOrderStatusResponse[] ordersHist(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
		  @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexOrdersHistoryRequest ordersHistoryRequest) throws IOException, BitfinexException;

  @POST
  @Path("offers")
  BitfinexOfferStatusResponse[] activeOffers(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexNonceOnlyRequest nonceOnlyRequest) throws IOException, BitfinexException;

  @POST
  @Path("positions")
  BitfinexActivePositionsResponse[] activePositions(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexNonceOnlyRequest nonceOnlyRequest) throws IOException, BitfinexException;

  @POST
  @Path("order/status")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  BitfinexOrderStatusResponse orderStatus(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexOrderStatusRequest orderStatusRequest) throws IOException, BitfinexException;

  @POST
  @Path("offer/status")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  BitfinexOfferStatusResponse offerStatus(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexOfferStatusRequest offerStatusRequest) throws IOException, BitfinexException;

  @POST
  @Path("mytrades")
  BitfinexTradeResponse[] pastTrades(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexPastTradesRequest pastTradesRequest) throws IOException, BitfinexException;

  @POST
  @Path("mytrades_funding")
  BitfinexFundingTradeResponse[] pastFundingTrades(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexPastFundingTradesRequest bitfinexPastFundingTradesRequest) throws IOException, BitfinexException;

  @POST
  @Path("credits")
  BitfinexCreditResponse[] activeCredits(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature,
      BitfinexActiveCreditsRequest activeCreditsRequest) throws IOException, BitfinexException;

  @POST
  @Path("margin_infos")
  BitfinexMarginInfosResponse[] marginInfos(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexMarginInfosRequest marginInfosRequest) throws IOException, BitfinexException;

  @POST
  @Path("withdraw")
  BitfinexWithdrawalResponse[] withdraw(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexWithdrawalRequest withdrawalRequest) throws IOException, BitfinexException;

  @POST
  @Path("deposit/new")
  BitfinexDepositAddressResponse requestDeposit(@HeaderParam("X-BFX-APIKEY") String apiKey, @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload,
      @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature, BitfinexDepositAddressRequest depositRequest) throws IOException, BitfinexException;

  @POST
  @Path("history/movements")
  BitfinexDepositWithdrawalHistoryResponse[] depositWithdrawalHistory(@HeaderParam("X-BFX-APIKEY") String apiKey,
      @HeaderParam("X-BFX-PAYLOAD") ParamsDigest payload, @HeaderParam("X-BFX-SIGNATURE") ParamsDigest signature,
      BitfinexDepositWithdrawalHistoryRequest request) throws IOException, BitfinexException;
}
