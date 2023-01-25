package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ScenarioBuilder;

import java.util.concurrent.atomic.AtomicLong;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.time.Duration.ofSeconds;

public abstract class WriteTest extends AbstractApplicaationTestCommon {
    private static final AtomicLong WRITE_COUNTER = new AtomicLong();
    private final ScenarioBuilder scn;

    {
        System.out.println("***** " + get().protocol() + " " + WriteTest.class.getSimpleName() + "***** ");
        scn = scenario("POS create scenario")
                .repeat(REPEAT)
                .on(
                        exec(http("repeat creating POS")
                                .post(BASE)
                                .body(StringBody(session -> generateJsonBodyString(WRITE_COUNTER, null))).asJson()
                                .check(
                                        status()
                                                .is(201))));
        setUp(scn.injectOpen(rampUsers(USERS).during(ofSeconds(1))).protocols(get()));
    }
}