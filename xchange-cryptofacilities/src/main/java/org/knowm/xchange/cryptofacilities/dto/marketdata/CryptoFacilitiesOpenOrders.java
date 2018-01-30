package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Jean-Christophe Laruelle
 */

public class CryptoFacilitiesOpenOrders extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime serverTime;
  private final List<CryptoFacilitiesOpenOrder> orders;

  public CryptoFacilitiesOpenOrders(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("serverTime") String strServerTime, @JsonProperty("openOrders") List<CryptoFacilitiesOpenOrder> orders) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : ZonedDateTime.parse(strServerTime, DATE_FORMAT);
    this.orders = orders;
  }

  public List<CryptoFacilitiesOpenOrder> getOrders() {
    return orders;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      StringBuilder res = new StringBuilder("CryptoFacilitiesOpenOrders [orders=");
      for (CryptoFacilitiesOpenOrder ord : orders)
        res.append(ord.toString()).append(", ");
      res.append(" ]");

      return res.toString();
    } else {
      return super.toString();
    }

  }

}
