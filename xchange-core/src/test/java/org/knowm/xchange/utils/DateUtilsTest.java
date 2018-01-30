package org.knowm.xchange.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {

  private final DateTimeFormatter isoDateFormat;
  private final DateTimeFormatter rfc3339DateFormat;

  public DateUtilsTest() {
    isoDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    isoDateFormat.withZone(ZoneOffset.UTC);

    rfc3339DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  }

  @Test
  public void testFromISODateString() throws Exception {
    String input = "2016-06-10T12:16:11.717Z";
    ZonedDateTime expectedOutput = ZonedDateTime.parse("2016-06-10T12:16:11.717", isoDateFormat);

    assertEquals(expectedOutput, DateUtils.fromISODateStringToZonedDateTime(input));
  }

  @Test(expected = InvalidFormatException.class)
  public void testFromISODateStringWrongTimezone() throws Exception {

    String input = "2016-06-10T12:16:11.717";
    ZonedDateTime expectedOutput = ZonedDateTime.parse("2016-06-10T12:16:11.717", isoDateFormat);

    assertEquals(expectedOutput, DateUtils.fromISODateStringToZonedDateTime(input));
  }

  @Test
  public void testFromRFC3339DateString() throws Exception {
    String input = "2018-01-15 12:16:11";
    ZonedDateTime expectedOutput = ZonedDateTime.parse("2018-01-15 12:16:11", rfc3339DateFormat);

    assertEquals(expectedOutput, DateUtils.fromRfc3339DateString(input));
  }

}
