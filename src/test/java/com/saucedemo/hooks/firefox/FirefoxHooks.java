package com.saucedemo.hooks.firefox;

import io.cucumber.java.BeforeAll;

public class FirefoxHooks {

    @BeforeAll
    public static void setUpBrowser() {
        System.setProperty("browser", "firefox");
    }
}
