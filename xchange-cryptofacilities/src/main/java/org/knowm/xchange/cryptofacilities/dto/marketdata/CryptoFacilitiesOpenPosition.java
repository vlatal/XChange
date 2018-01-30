package org.knowm.xchange.cryptofacilities.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cryptofacilities.dto.CryptoFacilitiesResult;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Panchen
 */

public class CryptoFacilitiesOpenPosition extends CryptoFacilitiesResult {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final ZonedDateTime fillTime;
  private final String symbol;
  private final String side;
  private final BigDecimal size;
  private final BigDecimal price;

  public CryptoFacilitiesOpenPosition(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("fillTime") String strfillTime, @JsonProperty("symbol") String symbol, @JsonProperty("side") String side,
      @JsonProperty("size") BigDecimal size, @JsonProperty("price") BigDecimal price) throws ParseException {

    super(result, error);

    this.fillTime = strfillTime == null ? null : ZonedDateTime.parse(strfillTime, DATE_FORMAT);
    this.symbol = symbol;
    this.side = side;
    this.size = size;
    this.price = price;
  }

  public String getSymbol() {
    return symbol;
  }

  public ZonedDateTime getFillTime() {
    return fillTime;
  }

  public String getSide() {
    return side;
  }

  public BigDecimal getSize() {
    return size;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "CryptoFacilitiesOpenPosition [fillTime=" + DATE_FORMAT.format(fillTime) + ", symbol=" + symbol + ", side=" + side + ", size=" + size
        + ", price=" + price + " ]";
  }
}
