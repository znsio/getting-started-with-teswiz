package com.znsio.sample.e2e.screen.web.zomato;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.zomato.DineOutScreen;
import com.znsio.sample.e2e.screen.zomato.RestaurantDetailScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class DineOutScreenWeb extends DineOutScreen {
    private static final String SCREEN_NAME = DineOutScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byRestaurantNameXpath = By.xpath("((//img[@alt='Restaurant Card'])[3]//following::h4)[1]");
    private final By byRestaurantCityXpath = By.xpath("//h1[contains(text(), 'Best Dining Restaurants near')]");
    private final By byRestaurantsDetailCardXpath = By.ByCssSelector.xpath("//img[@alt='Restaurant Card']//ancestor::div[@class = 'jumbo-tracker']");


    public DineOutScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public RestaurantDetailScreen selectRestaurant() {
        driver.findElement(byRestaurantNameXpath).click();
        return RestaurantDetailScreen.get();
    }

    @Override
    public String getRestaurantName() {
        visually.check(SCREEN_NAME, "Dine Out Page", Target.window().fully().layout(byRestaurantsDetailCardXpath));
        String restaurantName = driver.findElement(byRestaurantNameXpath).getText();
        LOGGER.info(String.format("Selected restaurant name: %s", restaurantName));
        return restaurantName;
    }

    @Override
    public String getRestaurantPageHeading() {
        return driver.waitTillElementIsVisible(byRestaurantCityXpath).getText();
    }
}
