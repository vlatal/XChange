package org.knowm.xchange.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.utils.DateUtils;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Deserializes an ISO 8601 formatted ZonedDateTime String to a Java ZonedDateTime ISO 8601 format: yyyy-MM-dd'T'HH:mm:ssX
 *
 * @author jamespedwards42
 */
public class ISO8601DateDeserializer extends JsonDeserializer<ZonedDateTime> {

  @Override
  public ZonedDateTime deserialize(JsonParser jp, final DeserializationContext ctxt) throws IOException {

    return DateUtils.fromISO8601DateStringToZonedDateTime(jp.getValueAsString());
  }
}
