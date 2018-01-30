package org.knowm.xchange.coinbase.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.knowm.xchange.coinbase.dto.common.CoinbaseRecurringPaymentStatus;
import org.knowm.xchange.coinbase.dto.common.CoinbaseRecurringPaymentType;
import org.knowm.xchange.coinbase.dto.common.CoinbaseRepeat;
import org.knowm.xchange.coinbase.dto.marketdata.CoinbaseMoney;
import org.knowm.xchange.coinbase.dto.serialization.CoinbaseMoneyDeserializer;
import org.knowm.xchange.utils.jackson.ISO8601DateDeserializer;

import java.time.ZonedDateTime;

/**
 * @author jamespedwards42
 */
public class CoinbaseRecurringPayment {

  private final CoinbaseRecurringPaymentInfo recurringPayment;

  private CoinbaseRecurringPayment(@JsonProperty("recurring_payment") final CoinbaseRecurringPaymentInfo recurringPayment) {

    this.recurringPayment = recurringPayment;
  }

  public String getId() {

    return recurringPayment.getId();
  }

  public CoinbaseRecurringPaymentType getType() {

    return recurringPayment.getType();
  }

  public CoinbaseRecurringPaymentStatus getStatus() {

    return recurringPayment.getStatus();
  }

  public ZonedDateTime getCreatedAt() {

    return recurringPayment.getCreatedAt();
  }

  public String getTo() {

    return recurringPayment.getTo();
  }

  public String getFrom() {

    return recurringPayment.getFrom();
  }

  public String getStartType() {

    return recurringPayment.getStartType();
  }

  public int getTimes() {

    return recurringPayment.getTimes();
  }

  public int getTimesRun() {

    return recurringPayment.getTimesRun();
  }

  public CoinbaseRepeat getRepeat() {

    return recurringPayment.getRepeat();
  }

  public ZonedDateTime getLastRun() {

    return recurringPayment.getLastRun();
  }

  public ZonedDateTime getNextRun() {

    return recurringPayment.getNextRun();
  }

  public String getNotes() {

    return recurringPayment.getNotes();
  }

  public String getDescription() {

    return recurringPayment.getDescription();
  }

  public CoinbaseMoney getAmount() {

    return recurringPayment.getAmount();
  }

  @Override
  public String toString() {

    return "CoinbaseRecurringPayment [recurringPayment=" + recurringPayment + "]";
  }

  private static class CoinbaseRecurringPaymentInfo {

    private final String id;
    private final CoinbaseRecurringPaymentType type;
    private final CoinbaseRecurringPaymentStatus status;
    private final ZonedDateTime createdAt;
    private final String to;
    private final String from;
    private final String startType;
    private final int times;
    private final int timesRun;
    private final CoinbaseRepeat repeat;
    private final ZonedDateTime lastRun;
    private final ZonedDateTime nextRun;
    private final String notes;
    private final String description;
    private final CoinbaseMoney amount;

    private CoinbaseRecurringPaymentInfo(@JsonProperty("id") final String id, @JsonProperty("type") final CoinbaseRecurringPaymentType type,
        @JsonProperty("status") final CoinbaseRecurringPaymentStatus status,
        @JsonProperty("created_at") @JsonDeserialize(using = ISO8601DateDeserializer.class) final ZonedDateTime createdAt, @JsonProperty("to") final String to,
        @JsonProperty("from") final String from, @JsonProperty("start_type") final String startType, @JsonProperty("times") final int times,
        @JsonProperty("times_run") final int timesRun, @JsonProperty("repeat") final CoinbaseRepeat repeat,
        @JsonProperty("last_run") @JsonDeserialize(using = ISO8601DateDeserializer.class) final ZonedDateTime lastRun,
        @JsonProperty("next_run") @JsonDeserialize(using = ISO8601DateDeserializer.class) final ZonedDateTime nextRun,
        @JsonProperty("notes") final String notes, @JsonProperty("description") final String description,
        @JsonProperty("amount") @JsonDeserialize(using = CoinbaseMoneyDeserializer.class) final CoinbaseMoney amount) {

      this.id = id;
      this.type = type;
      this.status = status;
      this.createdAt = createdAt;
      this.to = to;
      this.from = from;
      this.startType = startType;
      this.times = times;
      this.timesRun = timesRun;
      this.repeat = repeat;
      this.lastRun = lastRun;
      this.nextRun = nextRun;
      this.notes = notes;
      this.description = description;
      this.amount = amount;
    }

    public String getId() {

      return id;
    }

    public CoinbaseRecurringPaymentType getType() {

      return type;
    }

    public CoinbaseRecurringPaymentStatus getStatus() {

      return status;
    }

    public ZonedDateTime getCreatedAt() {

      return createdAt;
    }

    public String getTo() {

      return to;
    }

    public String getFrom() {

      return from;
    }

    public String getStartType() {

      return startType;
    }

    public int getTimes() {

      return times;
    }

    public int getTimesRun() {

      return timesRun;
    }

    public CoinbaseRepeat getRepeat() {

      return repeat;
    }

    public ZonedDateTime getLastRun() {

      return lastRun;
    }

    public ZonedDateTime getNextRun() {

      return nextRun;
    }

    public String getNotes() {

      return notes;
    }

    public String getDescription() {

      return description;
    }

    public CoinbaseMoney getAmount() {

      return amount;
    }

    @Override
    public String toString() {

      return "CoinbaseRecurringPaymentInfo [id=" + id + ", type=" + type + ", status=" + status + ", createdAt=" + createdAt + ", to=" + to
          + ", from=" + from + ", startType=" + startType + ", times=" + times + ", timesRun=" + timesRun + ", repeat=" + repeat + ", lastRun="
          + lastRun + ", nextRun=" + nextRun + ", notes=" + notes + ", description=" + description + ", amount=" + amount + "]";
    }

  }
}
