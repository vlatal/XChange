package org.knowm.xchange.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.utils.DateUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * Deserializes an rfc1123 formatted ZonedDateTime String to a Java ZonedDateTime rfc1123 format: 'EEE, dd MMM yyyy HH:mm:ss zzz'
 *
 * @author jamespedwards42
 */
public class Rfc1123DateDeserializer extends JsonDeserializer<ZonedDateTime> {

  @Override
  public ZonedDateTime deserialize(JsonParser jp, final DeserializationContext ctxt) throws IOException {

    return DateUtils.fromRfc1123DateString(jp.getValueAsString(), Locale.US);
  }

}
