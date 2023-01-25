package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ProtocolBuilder;

public class AwsReadTest extends ReadTest {

    @Override
    ProtocolBuilder get() {
        return Destination.AWS;
    }
}