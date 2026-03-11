package com.saucedemo.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDriverDecorator implements WebDriverListener {

    private static final Logger log = LoggerFactory.getLogger(LoggingDriverDecorator.class);

    public WebDriver decorate(WebDriver driver) {
        return new EventFiringDecorator<>(this).decorate(driver);
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        log.info("Navigating to: {}", url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        log.info("Navigated to: {}", url);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("Finding element: {}", locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.info("Found element: {}", locator);
    }

    @Override
    public void beforeClick(WebElement element) {
        log.info("Clicking element");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Typing: {}", Arrays.toString(keysToSend));
    }

    @Override
    public void beforeClear(WebElement element) {
        log.info("Clearing element");
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        log.info("Quitting browser");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        log.error("WebDriver error in {}: {}", method.getName(), e.getTargetException().getMessage());
    }
}
