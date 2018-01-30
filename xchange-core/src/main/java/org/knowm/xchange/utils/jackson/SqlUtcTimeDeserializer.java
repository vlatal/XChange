package org.knowm.xchange.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * @author Matija Mazi
 */
public class SqlUtcTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

  private DateTimeFormatter dateFormat;

  public SqlUtcTimeDeserializer() {
    dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    dateFormat.withZone(ZoneOffset.UTC);
  }

  @Override
  public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

    String str = jp.getValueAsString();
    try {
      return ZonedDateTime.parse(str, dateFormat);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", str, Date.class);
    }
  }
}
