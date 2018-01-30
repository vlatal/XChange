package org.knowm.xchange.poloniex;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.knowm.xchange.currency.CurrencyPair;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Zach Holmes
 */

public class PoloniexUtils {

    public static String toPairString(CurrencyPair currencyPair) {

        return currencyPair.counter.getCurrencyCode().toUpperCase() + "_" + currencyPair.base.getCurrencyCode().toUpperCase();
    }

    public static CurrencyPair toCurrencyPair(String pair) {

        String[] currencies = pair.split("_");
        return new CurrencyPair(currencies[1], currencies[0]);
    }

    public static ZonedDateTime stringToDate(String dateString) {

        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            df.withZone(ZoneOffset.UTC);
            return ZonedDateTime.parse(dateString, df);
        } catch (DateTimeParseException e) {
            return ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
        }
    }

    public static class UnixTimestampDeserializer extends JsonDeserializer<ZonedDateTime> {
        @Override
        public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            final String dateTimeInUnixFormat = p.getText();
            ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(dateTimeInUnixFormat)), ZoneOffset.UTC);
            return zdt;
        }
    }
}
