package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Neil Panchen
 */

public class CryptoFacilitiesInstrument extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final boolean tradeable;
  private final ZonedDateTime lastTradingTime;
  private final String symbol;
  private final String underlying;
  private final BigDecimal contractSize;
  private final String type;
  private final BigDecimal tickSize;

  public CryptoFacilitiesInstrument(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("tradeable") String strTradeable, @JsonProperty("lastTradingTime") String strLastTradingTime,
      @JsonProperty("symbol") String symbol, @JsonProperty("underlying") String underlying, @JsonProperty("contractSize") BigDecimal contractSize,
      @JsonProperty("type") String type, @JsonProperty("tickSize") BigDecimal tickSize) throws ParseException {

    super(result, error);

    this.tradeable = strTradeable.equalsIgnoreCase("true");
    this.lastTradingTime = strLastTradingTime == null ? null : ZonedDateTime.parse(strLastTradingTime, DATE_FORMAT);
    this.symbol = symbol;
    this.underlying = underlying;
    this.contractSize = contractSize;
    this.type = type;
    this.tickSize = tickSize;
  }

  public Boolean getTradeable() {
    return tradeable;
  }

  public ZonedDateTime getLastTradingTime() {
    return lastTradingTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getUnderlying() {
    return underlying;
  }

  public BigDecimal getContractSize() {
    return contractSize;
  }

  public String getType() {
    return type;
  }

  public BigDecimal getTickSize() {
    return tickSize;
  }

  @Override
  public String toString() {
    return "CryptoFacilitiesInstrument [tradeable=" + tradeable + ", lastTradingTime="
        + (lastTradingTime == null ? "null" : DATE_FORMAT.format(lastTradingTime)) + ", symbol=" + symbol + ", underlying=" + underlying
        + ", contractSize=" + contractSize + ", type=" + type + ", tickSize=" + tickSize + " ]";
  }
}
