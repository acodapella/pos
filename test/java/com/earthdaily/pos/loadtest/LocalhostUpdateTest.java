package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ProtocolBuilder;

public class LocalhostUpdateTest extends UpdateTest {

    @Override
    ProtocolBuilder get() {
        return Destination.LOCALHOST;
    }

}