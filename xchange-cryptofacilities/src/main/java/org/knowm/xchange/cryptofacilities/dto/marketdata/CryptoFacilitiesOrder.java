package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Jean-Christophe Laruelle
 */

public class CryptoFacilitiesOrder extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private final CryptoFacilitiesOrderStatus orderStatus;

  private final String orderId;

  public CryptoFacilitiesOrder(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("sendStatus") CryptoFacilitiesOrderStatus orderStatus, @JsonProperty("error") String error,
      @JsonProperty("orderId") String orderId) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.orderStatus = orderStatus;
    this.orderId = orderStatus == null ? orderId : orderStatus.getOrderId();
  }

  public String getOrderId() {
    return orderId;
  }

  public String getStatus() {
    return (orderStatus == null ? this.getError() : orderStatus.getStatus());
  }

  @Override
  public String toString() {

    if (isSuccess() && serverTime != null && orderStatus != null) {
      String res = "CryptoFacilitiesOrder [result=" + this.getResult() + ", serverTime=" + DATE_FORMAT.format(serverTime) + ", "
          + orderStatus.toString() + "]";
      return res;
    } else
      return super.toString();
  }

}
