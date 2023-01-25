package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ScenarioBuilder;

import java.util.concurrent.atomic.AtomicLong;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.time.Duration.ofSeconds;

public abstract class UpdateTest extends AbstractApplicaationTestCommon {

    private static final AtomicLong UPDATE_COUNTER = new AtomicLong();
    private static final AtomicLong UPDATE_COUNTER2 = new AtomicLong();
    private final ScenarioBuilder scn;

    {
        System.out.println("***** " + get().protocol() + " " + UpdateTest.class.getSimpleName() + "***** ");
        scn = scenario("POS create scenario")
                .repeat(REPEAT)
                .on(
                        exec(http("repeat update POS")
                                .put(session -> generateUrlWithId(UPDATE_COUNTER))
                                .body(StringBody(session -> generateJsonBodyString(UPDATE_COUNTER2,"v2"))).asJson()
                                .check(
                                        status()
                                                .is(200))));
        setUp(scn.injectOpen(rampUsers(USERS).during(ofSeconds(1))).protocols(get()));
    }
}