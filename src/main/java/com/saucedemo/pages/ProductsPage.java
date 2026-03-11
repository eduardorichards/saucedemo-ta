package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.utils.WaitUtils;

public class ProductsPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(ProductsPage.class);

    private static final String DASHBOARD_TITLE = "div.app_logo";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getDashboardTitle() {
        log.info("Getting dashboard title");
        return WaitUtils.waitForElementVisible(driver, DASHBOARD_TITLE).getText();
    }
}
