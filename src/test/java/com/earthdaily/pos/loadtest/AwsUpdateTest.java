package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ProtocolBuilder;

public class AwsUpdateTest extends UpdateTest {

    @Override
    ProtocolBuilder get() {
        return Destination.AWS;
    }
}