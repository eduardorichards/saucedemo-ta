package com.saucedemo.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirefoxBrowserFactory implements BrowserFactory {

    private static final Logger log = LoggerFactory.getLogger(FirefoxBrowserFactory.class);

    @Override
    public WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();

        if (Config.HEADLESS) {
            log.info("Creating Firefox browser in headless mode");
            options.addArguments("--headless");
        } else {
            log.info("Creating Firefox browser in headed mode");
        }

        return new FirefoxDriver(options);
    }
}
