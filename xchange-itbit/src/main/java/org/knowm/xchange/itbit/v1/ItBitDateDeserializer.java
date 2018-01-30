package org.knowm.xchange.itbit.v1;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ItBitDateDeserializer extends JsonDeserializer<ZonedDateTime> {

  private DateTimeFormatter simpleDateFormat;

  {
    // For some reason, "yyyy-MM-dd'T'HH:mm:ss.SSS'0000'X" doesn't work, and neither does it without the quotes.
    simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    simpleDateFormat.withZone(ZoneOffset.UTC);
  }

  @Override
  public ZonedDateTime deserialize(JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {

    String value = jp.getValueAsString().replaceFirst("0000Z$", "");
    try {
      return ZonedDateTime.parse(value, simpleDateFormat);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Can't parse date at offset " + e.getErrorIndex(), jp.getCurrentLocation(), value, Date.class);
      //      throw new RuntimeException("Can't parse date at offset " + e.getErrorOffset(), e);
    }
  }
}
