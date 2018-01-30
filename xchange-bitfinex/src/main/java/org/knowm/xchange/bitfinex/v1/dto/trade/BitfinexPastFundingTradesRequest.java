package org.knowm.xchange.bitfinex.v1.dto.trade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class BitfinexPastFundingTradesRequest {

  @JsonProperty("request")
  protected String request;

  @JsonProperty("nonce")
  protected String nonce;

  @JsonProperty("symbol")
  protected String symbol;

  /**
   * Trades made after this timestamp won’t be returned.
   */
  @JsonProperty("until")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  protected ZonedDateTime until;

  @JsonProperty("limit_trades")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  protected Integer limitTrades;

  public BitfinexPastFundingTradesRequest(String nonce, String symbol, ZonedDateTime until, Integer limitTrades) {

    this.request = "/v1/mytrades_funding";
    this.nonce = nonce;
    this.symbol = symbol;
    this.until = until;
    this.limitTrades = limitTrades;
  }
}
