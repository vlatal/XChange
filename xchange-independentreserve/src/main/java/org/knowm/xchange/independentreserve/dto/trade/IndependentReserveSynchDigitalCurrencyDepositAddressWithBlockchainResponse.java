package org.knowm.xchange.independentreserve.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.independentreserve.util.Util;

import java.time.ZonedDateTime;

public class IndependentReserveSynchDigitalCurrencyDepositAddressWithBlockchainResponse {
  /**
   * Digital currency deposit address to be updated
   */
  private final String depositAddress;
  /**
   * UTC timestamp of when this address was last checked against Blockchain
   */
  private final ZonedDateTime lastChecked;
  /**
   * UTC timestamp of when this address is scheduled to next be checked against Blockchain
   */
  private final ZonedDateTime nextUpdate;

  public IndependentReserveSynchDigitalCurrencyDepositAddressWithBlockchainResponse(
      @JsonProperty("DepositAddress") String depositAddress
      , @JsonProperty("LastCheckedTimestampUtc") String lastChecked
      , @JsonProperty("NextUpdateTimestampUtc") String nextUpdate
  ) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {
    this.depositAddress = depositAddress;
    this.lastChecked = Util.toDate(lastChecked);
    this.nextUpdate = Util.toDate(nextUpdate);
  }

  public String getDepositAddress() {
    return depositAddress;
  }

  public ZonedDateTime getLastChecked() {
    return lastChecked;
  }

  public ZonedDateTime getNextUpdate() {
    return nextUpdate;
  }
}
