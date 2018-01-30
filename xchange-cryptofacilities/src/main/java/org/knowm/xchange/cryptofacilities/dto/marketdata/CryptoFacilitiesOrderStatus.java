package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Panchen
 */

public class CryptoFacilitiesOrderStatus {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime receivedTime;
  private final String status;
  private final String order_id;

  public CryptoFacilitiesOrderStatus(@JsonProperty("receivedTime") String strReceivedTime, @JsonProperty("status") String status,
      @JsonProperty("order_id") String order_id) throws ParseException {

    this.receivedTime = strReceivedTime == null ? null : ZonedDateTime.parse(strReceivedTime, DATE_FORMAT);
    this.status = status;
    this.order_id = order_id;
  }

  public ZonedDateTime getReceivedTime() {
    return receivedTime;
  }

  public String getStatus() {
    return status;
  }

  public String getOrderId() {
    return order_id;
  }

  public String toString() {
    return "CryptoFacilitiesOrderStatus [order_id=" + order_id + ", status=" + status + ", receivedTime=" + DATE_FORMAT.format(receivedTime) + "]";
  }
}
