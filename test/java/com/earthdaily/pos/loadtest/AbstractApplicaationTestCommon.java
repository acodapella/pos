package com.earthdaily.pos.loadtest;

import com.github.javafaker.Faker;
import io.gatling.javaapi.core.ProtocolBuilder;
import io.gatling.javaapi.core.Simulation;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import static java.math.BigDecimal.valueOf;

public abstract class AbstractApplicaationTestCommon extends Simulation {
    protected static final int REPEAT = 100;
    protected static final int USERS = 500;
    protected static final long PRECISIONS = 100_000;
    protected static final String BASE = "/v1/pos";
    private static final Faker faker = new Faker();
    protected static DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);

    protected static String converToString(long input) {
        return formatter.format(input);
    }

    protected static BigDecimal shiftDecimals(long input) {
        return valueOf(input).divide(valueOf(PRECISIONS));
    }

    protected static String generateJsonBodyString(AtomicLong counter, String versionSuffix) {
        formatter.applyPattern("00000.######");

        long l = counter.addAndGet(1);
        long latV = l;
        long lngV = l;

        BigDecimal latB = shiftDecimals(latV);
        BigDecimal lngB = shiftDecimals(latV);

        String latV2 = converToString(latV);
        String lngV2 = converToString(lngV);
        String id = converToString(l);

        String name = faker.address().streetAddress() + (versionSuffix == null ? "" : (" " + versionSuffix));
        String jsonString = null;
        try {
            jsonString = new JSONObject()
                    .put("id", id)
                    .put("lat", latB.toString())
                    .put("lng", lngB.toString())
                    .put("name", name)
                    .toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }


    protected static String generateUrlWithId(AtomicLong counter) {
        formatter.applyPattern("00000.######");
        long l = counter.addAndGet(1);
        return BASE + "/" + converToString(l);
    }

    abstract ProtocolBuilder get();
}