package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all page objects.
 * Holds the shared WebDriver instance and provides common navigation.
 */
public abstract class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates the browser to the given URL.
     */
    protected void goTo(String url) {
        log.info("Navigating to: {}", url);
        driver.get(url);
    }
}
