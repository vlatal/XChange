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

public class CryptoFacilitiesInstruments extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private final List<CryptoFacilitiesInstrument> instruments;

  public CryptoFacilitiesInstruments(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("instruments") List<CryptoFacilitiesInstrument> instruments) throws ParseException {

    super(result, error);

    this.serverTime = ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.instruments = instruments;
  }

  public List<CryptoFacilitiesInstrument> getInstruments() {
    return instruments;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      StringBuilder res = new StringBuilder("CryptoFacilitiesInstruments [serverTime=" + DATE_FORMAT.format(serverTime) + ",instruments=");
      for (CryptoFacilitiesInstrument ct : instruments)
        res.append(ct.toString()).append(", ");
      res.append(" ]");

      return res.toString();
    } else {
      return super.toString();
    }
  }

}
