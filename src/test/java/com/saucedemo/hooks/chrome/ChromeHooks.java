package com.saucedemo.hooks.chrome;

import io.cucumber.java.BeforeAll;

public class ChromeHooks {

    @BeforeAll
    public static void setUpBrowser() {
        System.setProperty("browser", "chrome");
    }
}
