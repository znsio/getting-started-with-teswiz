package com.znsio.e2e.screen.swiggy.web;

import com.znsio.e2e.screen.swiggy.RestaurantListingScreen;
import com.znsio.e2e.screen.swiggy.RestaurantProfileScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.e2e.tools.Wait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class RestaurantListingScreenWeb extends RestaurantListingScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(RestaurantListingScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantListingScreenWeb.class.getSimpleName();

    private By ratingsSortXpath = By.xpath("//div[contains(text(),'Rating')]");

    private String restaurantNameXpathStr = "//div[@id='all_restaurants']//div[contains(text(),'%s')]";
    public RestaurantListingScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant Listing screen");
    }


    @Override
    public RestaurantListingScreen sortRestaurantByRating() {
        driver.waitForClickabilityOf(ratingsSortXpath);
        driver.findElement(ratingsSortXpath).click();
        return RestaurantListingScreen.get();
    }

    @Override
    public RestaurantProfileScreen selectRestaurantByName(String restaurantName) {
        By restaurantNameXpath = By.xpath(String.format(restaurantNameXpathStr,restaurantName));
        driver.waitTillElementIsPresent(restaurantNameXpath).click();
        return RestaurantProfileScreen.get();
    }
}
