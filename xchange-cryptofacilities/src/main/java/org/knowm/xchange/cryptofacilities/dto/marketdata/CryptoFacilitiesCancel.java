package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Jean-Christophe Laruelle
 */

public class CryptoFacilitiesCancel extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private final CryptoFacilitiesCancelStatus cancelStatus;

  public CryptoFacilitiesCancel(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("cancelStatus") CryptoFacilitiesCancelStatus cancelStatus, @JsonProperty("error") String error) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.cancelStatus = cancelStatus;
  }

  public String getStatus() {
    return (cancelStatus == null ? this.getError() : cancelStatus.getStatus());
  }

  @Override
  public String toString() {

    if (isSuccess() && serverTime != null && cancelStatus != null) {
      String res = "CryptoFacilitiesCancel [result=" + this.getResult() + ", serverTime=" + DATE_FORMAT.format(serverTime) + ", "
          + cancelStatus.toString() + "]";

      return res;
    } else {
      return super.toString();
    }
  }

}
