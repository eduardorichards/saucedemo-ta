package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void goTo(String url) {
        log.info("Navigating to: {}", url);
        driver.get(url);
    }
}
