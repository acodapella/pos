package com.earthdaily.pos.loadtest;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class Destination {
    public static HttpProtocolBuilder LOCALHOST = http.baseUrl("http://localhost:8080");
    public static HttpProtocolBuilder AWS = http.baseUrl("https://l8ps3sls23.execute-api.us-west-2.amazonaws.com/dev");
}
