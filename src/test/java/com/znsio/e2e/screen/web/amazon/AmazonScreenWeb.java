package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.AmazonScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class AmazonScreenWeb extends AmazonScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(AmazonScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AmazonScreenWeb.class.getSimpleName();
    private final By loginFormLinkText = By.linkText("Form Authentication");

    public AmazonScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    @Override
    public AmazonScreen login(String page) {
        // Login steps
        System.out.println("Login");
        return this;
    }

    @Override
    public AmazonScreen searchForItem(String keyWord) {
        // Search steps
        System.out.println("Search");
        return this;
    }

    @Override
    public AmazonScreen addToCart() {
        // Add to cart steps
        System.out.println("Add to cart");
        return this;
    }

    @Override
    public AmazonScreen verifyCart() {
        // Verify cart steps
        System.out.println("Verify Cart");
        return this;
    }
}