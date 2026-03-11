package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.core.Config;
import com.saucedemo.utils.WaitUtils;

public class LoginPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    private static final String USERNAME_INPUT = "input[data-test='username']";
    private static final String PASSWORD_INPUT = "input[data-test='password']";
    private static final String LOGIN_BUTTON = "input[data-test='login-button']";
    private static final String ERROR_MESSAGE = "[data-test='error']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        log.info("Opening login page");
        goTo(Config.BASE_URL);
    }

    public void enterUsername(String username) {
        log.info("Entering username: {}", username);
        driver.findElement(By.cssSelector(USERNAME_INPUT)).sendKeys(username);
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        driver.findElement(By.cssSelector(PASSWORD_INPUT)).sendKeys(password);
    }

    public void clearUsername() {
        log.info("Clearing username field");
        WebElement field = driver.findElement(By.cssSelector(USERNAME_INPUT));
        Keys selectAllKey = System.getProperty("os.name").toLowerCase().contains("mac")
                ? Keys.COMMAND : Keys.CONTROL;
        field.sendKeys(Keys.chord(selectAllKey, "a"), Keys.BACK_SPACE);
    }

    public void clearPassword() {
        log.info("Clearing password field");
        WebElement field = driver.findElement(By.cssSelector(PASSWORD_INPUT));
        Keys selectAllKey = System.getProperty("os.name").toLowerCase().contains("mac")
                ? Keys.COMMAND : Keys.CONTROL;
        field.sendKeys(Keys.chord(selectAllKey, "a"), Keys.BACK_SPACE);
    }

    public void clickLogin() {
        log.info("Clicking login button");
        driver.findElement(By.cssSelector(LOGIN_BUTTON)).click();
    }

    public String getErrorMessage() {
        log.info("Getting error message");
        return WaitUtils.waitForElementVisible(driver, ERROR_MESSAGE).getText();
    }
}
