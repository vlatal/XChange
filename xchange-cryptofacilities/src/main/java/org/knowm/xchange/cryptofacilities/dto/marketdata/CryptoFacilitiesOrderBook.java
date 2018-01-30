package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;
import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Panchen
 */

public class CryptoFacilitiesOrderBook extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private CurrencyPair currencyPair;
  private final CryptoFacilitiesBidsAsks bidsAsks;

  public CryptoFacilitiesOrderBook(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("orderBook") CryptoFacilitiesBidsAsks bidsAsks) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.bidsAsks = bidsAsks;
  }

  public List<List<BigDecimal>> getBids() {
    return bidsAsks.getBids();
  }

  public List<List<BigDecimal>> getAsks() {
    return bidsAsks.getAsks();
  }

  public ZonedDateTime getServerTime() {
    return serverTime;
  }

  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  public void setCurrencyPair(CurrencyPair currencyPair) {
    this.currencyPair = currencyPair;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      return "CryptoFacilitiesOrderBook [ccyPair=" + currencyPair + ", serverTime=" + DATE_FORMAT.format(serverTime) + ", orderBook="
          + bidsAsks.toString() + "]";
    } else {
      return super.toString();
    }
  }

}
