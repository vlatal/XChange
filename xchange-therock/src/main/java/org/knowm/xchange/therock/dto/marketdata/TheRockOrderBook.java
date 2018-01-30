package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

import java.time.ZonedDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockOrderBook {

  @JsonProperty("fund_id")
  @JsonDeserialize(using = CurrencyPairDeserializer.class)
  private CurrencyPair currencyPair;

  private ZonedDateTime date;

  private List<TheRockBid> bids;

  private List<TheRockBid> asks;

  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public List<TheRockBid> getBids() {
    return bids;
  }

  public List<TheRockBid> getAsks() {
    return asks;
  }
}
