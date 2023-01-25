package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ProtocolBuilder;

public class AwsWriteTest extends WriteTest {

    @Override
    ProtocolBuilder get() {
        return Destination.AWS;
    }
}