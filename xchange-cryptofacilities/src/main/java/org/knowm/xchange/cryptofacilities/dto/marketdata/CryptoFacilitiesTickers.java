package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Neil Panchen
 */

public class CryptoFacilitiesTickers extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private final List<CryptoFacilitiesTicker> tickers;

  public CryptoFacilitiesTickers(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("tickers") List<CryptoFacilitiesTicker> tickers) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.tickers = tickers;
  }

  public ZonedDateTime getServerTime() {
    return serverTime;
  }

  public List<CryptoFacilitiesTicker> getTickers() {
    return tickers;
  }

  public CryptoFacilitiesTicker getTicker(String symbol) {
    if (isSuccess() && tickers != null) {
      for (CryptoFacilitiesTicker ticker : tickers) {
        if (ticker != null && ticker.getSymbol().equalsIgnoreCase(symbol)) {
          return ticker;
        }
      }
    }
    return null;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      StringBuilder res = new StringBuilder("CryptoFacilitiesTickers [serverTime=" + DATE_FORMAT.format(serverTime) + ", tickers=");
      for (CryptoFacilitiesTicker ticker : tickers) {
        res.append(ticker.toString()).append(", ");
      }
      res.append(" ]");

      return res.toString();
    } else {
      return super.toString();
    }
  }

}
