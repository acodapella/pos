package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.core.ProtocolBuilder;

public class LocalhostWriteTest extends WriteTest {

    @Override
    ProtocolBuilder get() {
        return Destination.LOCALHOST;
    }

}