package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantListScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class RestaurantListScreenWeb extends RestaurantListScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By ratingsButton = By.xpath("//div[@class='_3Ynv-']/div[3]");
    private final String restaurantTile = "//div/a[contains(@href,'/restaurants/";
//  "//div/div/div[2]/div[contains(text(),'";
    private final By restaurantNameSelected = By.xpath("//h1");
    private final TestExecutionContext context;

    public RestaurantListScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurants list screen for Swiggy");
    }

    @Override
    public RestaurantListScreen sortRestaurants() {
        driver.waitForClickabilityOf(ratingsButton).click();
        return this;
    }

    @Override
    public RestaurantListScreen selectRestaurantFromList(String restaurantName) {
        restaurantName= restaurantName.toLowerCase();
        restaurantName= restaurantName.replace(' ','-');
        System.out.println("Restaurant name ==> "+restaurantName);
        By restName= By.xpath(restaurantTile+restaurantName+"')]");
        driver.waitForClickabilityOf(restName).click();
        return this;
    }

    @Override
    public String getRestaurantNameSelected() {
        return driver.waitTillElementIsPresent(restaurantNameSelected).getText();
    }
}
