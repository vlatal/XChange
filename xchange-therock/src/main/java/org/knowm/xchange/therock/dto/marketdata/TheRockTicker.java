package org.knowm.xchange.therock.dto.marketdata;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.jackson.CurrencyPairDeserializer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author Matija Mazi
 */
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TheRockTicker {

  @JsonDeserialize(using = CurrencyPairDeserializer.class)
  private CurrencyPair fundId;

  private ZonedDateTime date;

  private BigDecimal bid, ask, last, volume, volumeTraded, open, high, low, close;

  public CurrencyPair getFundId() {
    return fundId;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public BigDecimal getLast() {
    return last;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public BigDecimal getVolumeTraded() {
    return volumeTraded;
  }

  public BigDecimal getOpen() {
    return open;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getClose() {
    return close;
  }

  @Override
  public String toString() {
    return String.format(
        "TheRockTicker{currencyPair=%s, date=%s, bid=%s, ask=%s, last=%s, volume=%s, volumeTraed=%s, open=%s, high=%s, low=%s, close=%s}", fundId,
        date, bid, ask, last, volume, volumeTraded, open, high, low, close);
  }
}
