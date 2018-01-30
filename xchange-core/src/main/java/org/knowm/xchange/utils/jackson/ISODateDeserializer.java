package org.knowm.xchange.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.utils.DateUtils;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Deserializes an ISO formatted ZonedDateTime String to a Java ZonedDateTime ISO format: 'yyyy-MM-dd'T'HH:mm:ss.SSS'Z''
 *
 * @author jamespedwards42
 */
public class ISODateDeserializer extends JsonDeserializer<ZonedDateTime> {

  @Override
  public ZonedDateTime deserialize(JsonParser jp, final DeserializationContext ctxt) throws IOException {

    return DateUtils.fromISODateStringToZonedDateTime(jp.getValueAsString());
  }
}
