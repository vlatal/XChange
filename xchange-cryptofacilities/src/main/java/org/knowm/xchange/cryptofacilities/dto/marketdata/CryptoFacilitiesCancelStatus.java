package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Panchen
 */

public class CryptoFacilitiesCancelStatus {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime receivedTime;
  private final String status;

  public CryptoFacilitiesCancelStatus(@JsonProperty("receivedTime") String strReceivedTime,
      @JsonProperty("status") String status) throws ParseException {

    this.receivedTime = strReceivedTime == null ? null : ZonedDateTime.parse(strReceivedTime, DATE_FORMAT);
    this.status = status;
  }

  public ZonedDateTime getReceivedTime() {
    return receivedTime;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "CryptoFacilitiesCancelStatus [status=" + status + ", receivedTime=" + (receivedTime == null ? "" : DATE_FORMAT.format(receivedTime))
        + "]";
  }
}
