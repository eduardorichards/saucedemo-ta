package com.saucedemo.core;

import java.time.Duration;

public final class Config {

    public static final String BASE_URL = "https://www.saucedemo.com/";

    public static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "false"));

    private Config() {
    }
}
