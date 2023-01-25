package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class LocalhostReadTest extends ReadTest {

    @Override
    ProtocolBuilder get() {
        return Destination.LOCALHOST;
    }

}