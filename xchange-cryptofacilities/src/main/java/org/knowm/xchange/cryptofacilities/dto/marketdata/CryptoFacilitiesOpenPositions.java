package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Panchen
 */

public class CryptoFacilitiesOpenPositions extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private final List<CryptoFacilitiesOpenPosition> openPositions;

  public CryptoFacilitiesOpenPositions(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("openPositions") List<CryptoFacilitiesOpenPosition> openPositions) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.openPositions = openPositions;
  }

  public List<CryptoFacilitiesOpenPosition> getOpenPositions() {
    return openPositions;
  }

  public ZonedDateTime getServerTime() {
    return serverTime;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      StringBuilder res = new StringBuilder("CryptoFacilitiesOpenPositions [serverTime=" + DATE_FORMAT.format(serverTime) + ", openPositions=");
      for (CryptoFacilitiesOpenPosition openPosition : openPositions)
        res.append(openPosition.toString()).append(", ");
      res.append(" ]");

      return res.toString();
    } else {
      return super.toString();
    }
  }

}
