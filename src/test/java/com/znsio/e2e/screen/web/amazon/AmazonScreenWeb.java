package com.znsio.e2e.screen.web.amazon;

import com.znsio.e2e.screen.amazon.AmazonScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;


public class AmazonScreenWeb extends AmazonScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(AmazonScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = AmazonScreenWeb.class.getSimpleName();

    public AmazonScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Home screen");
    }

    @Override
    public AmazonScreen login() {
        System.out.println("login");
        return this;
    }

    @Override
    public AmazonScreen navigateToPage(String page) {
        System.out.println("navigateToPage");
        return this;
    }

    @Override
    public AmazonScreen searchForItem(String keyWord) {
        System.out.println("searchForItem");
        return this;
    }

    @Override
    public AmazonScreen verifySearchedResults() {
        System.out.println("verifySearchedResults");
        return this;
    }

    @Override
    public AmazonScreen selectAnyProductFromListingPage() {
        System.out.println("selectAnyProductFromListingPage");
        return this;
    }

    @Override
    public AmazonScreen validateProductOnDetailsPage() {
        System.out.println("validateProductOnDetailsPage");
        return this;
    }

    @Override
    public AmazonScreen addToCart() {
        System.out.println("addToCart");
        return this;
    }

    @Override
    public AmazonScreen verifyCart() {
        System.out.println("verifyCart");
        return this;
    }
}