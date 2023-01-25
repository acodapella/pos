package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ScenarioBuilder;

import java.util.concurrent.atomic.AtomicLong;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.time.Duration.ofSeconds;

public abstract class ReadTest extends AbstractApplicaationTestCommon {
    private static final AtomicLong READ_COUNTER = new AtomicLong();
    private final ScenarioBuilder scn;

    {
        System.out.println("***** " + get().protocol() + " " + ReadTest.class.getSimpleName() + "***** ");
        scn = scenario("POS retrieve scenario")
                .repeat(REPEAT)
                .on(
                        exec(http("repeat get POS by id ")
                                .get(session -> generateUrlWithId(READ_COUNTER))
                                .check(
                                        status()
                                                .is(200))));
        setUp(scn.injectOpen(rampUsers(USERS).during(ofSeconds(1))).protocols(get()));
    }

}