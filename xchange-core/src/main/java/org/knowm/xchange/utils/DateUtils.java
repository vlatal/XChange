package org.knowm.xchange.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * Utilities to provide the following to application:
 * </p>
 * <ul>
 * <li>Provision of standard date and time handling</li>
 * </ul>
 */
public class DateUtils {
  public static final DateTimeFormatter ISO_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  public static final DateTimeFormatter ISO_DATE_FORMAT_UTC = ISO_DATE_FORMAT.withZone(ZoneOffset.UTC);
  /**
   * private Constructor
   */
  private DateUtils() {

  }

  /**
   * Creates a date from a long representing milliseconds from epoch
   *
   * @param millisecondsFromEpoch
   * @return the ZonedDateTime object
   */
  @Deprecated
  public static ZonedDateTime fromMillisUtc(long millisecondsFromEpoch) {

    return DateUtils.fromMillisToZonedDateTime(millisecondsFromEpoch);
  }

  /**
   * Creates a ZonedDateTime from a long representing milliseconds from epoch
   *
   * @param millisecondsFromEpoch
   * @return the ZonedDateTime object
   */
  public static ZonedDateTime fromMillisToZonedDateTime(long millisecondsFromEpoch) {
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millisecondsFromEpoch), ZoneOffset.UTC);
  }

  /**
   * Creates a ZonedDateTime from a long representing milliseconds from epoch
   *
   * @param secondsFromEpoch
   * @return the ZonedDateTime object
   */
  public static ZonedDateTime fromSecondsToZonedDateTime(long secondsFromEpoch) {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(secondsFromEpoch), ZoneOffset.UTC);
  }

  /**
   * Converts a ZonedDateTime to a UTC String representation
   *
   * @param date
   * @return the formatted date
   */
  public static String toUTCString(ZonedDateTime date) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    dateFormat.withZone(ZoneOffset.UTC);

    return date.format(dateFormat);
  }

  /**
   * Converts an ISO formatted ZonedDateTime String to a Java ZonedDateTime ISO format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
   *
   * @param isoFormattedDate
   * @return ZonedDateTime
   * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
   */
  @Deprecated
  public static ZonedDateTime fromISODateString(String isoFormattedDate) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

    DateTimeFormatter isoDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    // set UTC time zone - 'Z' indicates it
    isoDateFormat.withZone(ZoneOffset.UTC);
    try {
      return ZonedDateTime.parse(isoFormattedDate, isoDateFormat);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", isoFormattedDate, Date.class);
    }
  }

  /**
   * Converts an ISO formatted ZonedDateTime String to a Java ZonedDateTime ISO format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
   *
   * @param isoFormattedDate
   * @return ZonedDateTime
   * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
   */
  public static ZonedDateTime fromISODateStringToZonedDateTime(String isoFormattedDate) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

    try {
      return ZonedDateTime.parse(isoFormattedDate, ISO_DATE_FORMAT_UTC);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", isoFormattedDate, ZonedDateTime.class);
    }
  }

  /**
   * Converts an ISO 8601 formatted ZonedDateTime String to a Java ZonedDateTime ISO 8601 format: yyyy-MM-dd'T'HH:mm:ss
   *
   * @param iso8601FormattedDate
   * @return ZonedDateTime
   * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
   */
  @Deprecated
  public static ZonedDateTime fromISO8601DateString(String iso8601FormattedDate) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

    DateTimeFormatter iso8601Format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    try {
      return ZonedDateTime.parse(iso8601FormattedDate, iso8601Format);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", iso8601FormattedDate, Date.class);
    }
  }

  /**
   * Converts an ISO 8601 formatted ZonedDateTime String to a Java ZonedDateTime ISO 8601 format: yyyy-MM-dd'T'HH:mm:ss
   *
   * @param iso8601FormattedDate
   * @return ZonedDateTime
   * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
   */
  public static ZonedDateTime fromISO8601DateStringToZonedDateTime(String iso8601FormattedDate) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

    DateTimeFormatter iso8601Format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    try {
      return ZonedDateTime.parse(iso8601FormattedDate, iso8601Format);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", iso8601FormattedDate, Date.class);
    }
  }

  /**
   * Converts an rfc1123 formatted ZonedDateTime String to a Java ZonedDateTime rfc1123 format: EEE, dd MMM yyyy HH:mm:ss zzz
   *
   * @param rfc1123FormattedDate
   * @return ZonedDateTime
   * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
   */
  public static ZonedDateTime fromRfc1123DateString(String rfc1123FormattedDate, Locale locale) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

    DateTimeFormatter rfc1123DateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz", locale);
    try {
      return ZonedDateTime.parse(rfc1123FormattedDate, rfc1123DateFormat);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", rfc1123FormattedDate, Date.class);
    }
  }

  /**
   * Converts an RFC3339 formatted ZonedDateTime String to a Java ZonedDateTime RFC3339 format: yyyy-MM-dd HH:mm:ss
   *
   * @param rfc3339FormattedDate RFC3339 formatted ZonedDateTime
   * @return an {@link Date} object
   * @throws InvalidFormatException the RFC3339 formatted ZonedDateTime is invalid or cannot be parsed.
   * @see <a href="https://tools.ietf.org/html/rfc3339">The Internet Society - RFC 3339</a>
   */
  public static ZonedDateTime fromRfc3339DateString(String rfc3339FormattedDate) throws InvalidFormatException {

    DateTimeFormatter rfc3339DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    try {
      return ZonedDateTime.parse(rfc3339FormattedDate, rfc3339DateFormat);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException("Error parsing as date", rfc3339FormattedDate, Date.class);
    }
  }

  /**
   * Convert java time long to unix time long, simply by dividing by 1000
   */
  public static long toUnixTime(long javaTime) {
    return javaTime / 1000;
  }

  /**
   * Convert java time to unix time long, simply by dividing by the time 1000
   */
  public static long toUnixTime(ZonedDateTime time) {
    return time.toInstant().getEpochSecond();
  }

  /**
   * Convert java time to unix time long, simply by dividing by the time 1000. Null safe
   */
  public static Long toUnixTimeNullSafe(ZonedDateTime time) {

    return time == null ? null : time.toInstant().getEpochSecond();
  }

  public static Long toMillisNullSafe(ZonedDateTime time) {

    return time == null ? null : time.toInstant().toEpochMilli();
  }

  /**
   * Convert unix time (seconds) to Java ZonedDateTime
   */
  public static ZonedDateTime fromUnixTime(long unix) {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(unix), ZoneOffset.UTC);
  }

}
