package com.saucedemo.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeBrowserFactory implements BrowserFactory {

    private static final Logger log = LoggerFactory.getLogger(ChromeBrowserFactory.class);

    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        if (Config.HEADLESS) {
            log.info("Creating Chrome browser in headless mode");
            options.addArguments("--headless=new");
        } else {
            log.info("Creating Chrome browser in headed mode");
        }

        return new ChromeDriver(options);
    }
}
