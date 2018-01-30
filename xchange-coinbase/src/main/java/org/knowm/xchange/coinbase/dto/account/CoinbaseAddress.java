package org.knowm.xchange.coinbase.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.coinbase.dto.CoinbaseBaseResponse;
import org.knowm.xchange.utils.jackson.ISO8601DateDeserializer;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author jamespedwards42
 */
public class CoinbaseAddress extends CoinbaseBaseResponse {

  private final String address;
  private final String callbackUrl;
  private final String label;
  private final ZonedDateTime createdAt;

  CoinbaseAddress(String address, final String callbackUrl, final String label, final ZonedDateTime createdAt) {

    super(true, null);
    this.address = address;
    this.callbackUrl = callbackUrl;
    this.label = label;
    this.createdAt = createdAt;
  }

  private CoinbaseAddress(@JsonProperty("address") final String address, @JsonProperty("callback_url") final String callbackUrl,
      @JsonProperty("label") final String label,
      @JsonProperty("created_at") @JsonDeserialize(using = ISO8601DateDeserializer.class) final ZonedDateTime createdAt,
      @JsonProperty("success") final boolean success, @JsonProperty("errors") final List<String> errors) {

    super(success, errors);
    this.address = address;
    this.callbackUrl = callbackUrl;
    this.label = label;
    this.createdAt = createdAt;
  }

  public String getAddress() {

    return address;
  }

  public String getCallbackUrl() {

    return callbackUrl;
  }

  public String getLabel() {

    return label;
  }

  public ZonedDateTime getCreatedAt() {

    return createdAt;
  }

  @Override
  public String toString() {

    return "CoinbaseAddress [address=" + address + ", callbackUrl=" + callbackUrl + ", label=" + label + ", createdAt=" + createdAt + "]";
  }

}
