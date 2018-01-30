package org.knowm.xchange.coinbase.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

import java.util.List;

@SuppressWarnings("serial")
public class CoinbaseException extends HttpStatusExceptionSupport {

  public CoinbaseException(@JsonProperty("errors") List<CoinbaseError> errors) {
    super(errors.get(0).message);
  }
  
  static class CoinbaseError {
    
    @JsonProperty
    String id;
    @JsonProperty
    String message;
    
    @Override
    public String toString() {
      return "CoinbaseError [id=" + id + ", message=" + message + "]";
    }
  }
}
