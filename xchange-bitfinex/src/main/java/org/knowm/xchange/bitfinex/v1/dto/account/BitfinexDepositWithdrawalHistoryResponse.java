package org.knowm.xchange.bitfinex.v1.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BitfinexDepositWithdrawalHistoryResponse {

  @JsonProperty("id")
  private long id;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("method")
  private String method;

  @JsonProperty("type")
  private FundingRecord.Type type;

  @JsonProperty("amount")
  private BigDecimal amount;

  @JsonProperty("description")
  private String description;

  @JsonProperty("address")
  private String address;

  @JsonProperty("status")
  private String status;

  @JsonProperty("timestamp")
  private BigDecimal timestamp;

  @Override
  public String toString() {
    return "BitfinexDepositWithdrawalHistoryResponse [id=" + id + ", currency=" + currency + ", method=" + method + ", type=" + type + ", amount="
        + amount + ", description=" + description + ", address=" + address + ", status=" + status + ", timestamp=" + getTimestamp() + "]";
  }

  public long getId() {
    return id;
  }

  public String getCurrency() {
    return currency;
  }

  public String getMethod() {
    return method;
  }

  public FundingRecord.Type getType() {
    return type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }

  public String getAddress() {
    return address;
  }

  public String getStatus() {
    return status;
  }

  public ZonedDateTime getTimestamp() {
    return DateUtils.fromMillisToZonedDateTime(timestamp.scaleByPowerOfTen(3).longValue());
  }

}
