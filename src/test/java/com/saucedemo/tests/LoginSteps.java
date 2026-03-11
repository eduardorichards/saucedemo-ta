package com.saucedemo.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.core.BrowserFactory;
import com.saucedemo.core.ChromeBrowserFactory;
import com.saucedemo.core.FirefoxBrowserFactory;
import com.saucedemo.core.LoggingDriverDecorator;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        log.info("Setting up {} browser", browserName);

        BrowserFactory factory;
        if ("firefox".equalsIgnoreCase(browserName)) {
            factory = new FirefoxBrowserFactory();
        } else {
            factory = new ChromeBrowserFactory();
        }

        WebDriver rawDriver = factory.createDriver();
        driver = new LoggingDriverDecorator().decorate(rawDriver);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
        }
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.open();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I clear the username field")
    public void iClearTheUsernameField() {
        loginPage.clearUsername();
    }

    @When("I clear the password field")
    public void iClearThePasswordField() {
        loginPage.clearPassword();
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        log.info("Verifying error message: expected='{}', actual='{}'", expectedMessage, actualMessage);
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Then("I should see the dashboard title {string}")
    public void iShouldSeeTheDashboardTitle(String expectedTitle) {
        String actualTitle = productsPage.getDashboardTitle();
        log.info("Verifying dashboard title: expected='{}', actual='{}'", expectedTitle, actualTitle);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
