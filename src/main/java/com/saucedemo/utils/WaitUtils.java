package com.saucedemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.core.Config;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebElement waitForElementVisible(WebDriver driver, String cssSelector) {
        return new WebDriverWait(driver, Config.TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }
}
