package com.znsio.e2e.screen.swiggy.web;

import com.znsio.e2e.screen.swiggy.RestaurantListingScreen;
import com.znsio.e2e.screen.swiggy.SwiggyHomeScreen;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SwiggyHomeScreenWeb extends SwiggyHomeScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(SwiggyHomeScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private final By locationSearchId = By.id("location");
    private String locationSuggestionStr = "//div/span[2][contains(text(),'%s')]";
    private final By findfoodBtnXpath = By.xpath("//a/span[contains(text(),'FIND FOOD')]");


    public SwiggyHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Swiggy Home screen");
    }

    @Override
    public RestaurantListingScreen searchForDeliveryLocation(String deliveryLoc) {
        driver.findElement(locationSearchId).sendKeys(deliveryLoc);
        By deliveryLocXpath=By.xpath(String.format(locationSuggestionStr,deliveryLoc));
        driver.waitForClickabilityOf(deliveryLocXpath);
        driver.findElement(deliveryLocXpath).click();
        //driver.findElement(findfoodBtnXpath).click();
        return RestaurantListingScreen.get();
    }
}
