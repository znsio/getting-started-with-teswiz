package com.znsio.sample.e2e.screen.web.zomato;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.zomato.DiningOutScreen;
import com.znsio.sample.e2e.screen.zomato.RestaurantDetailScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class DiningOutScreenWeb extends DiningOutScreen {
    private static final String SCREEN_NAME = DiningOutScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byRestaurantNameXpath = By.xpath("((//img[@alt='Restaurant Card'])[3]//following::h4)[1]");
    private final By byDineOutXpath = By.xpath("//div[text()='Dining Out']");
    private By byRestaurantsDetailCardXpath = By.ByCssSelector.xpath("//img[@alt='Restaurant Card']//ancestor::div[@class = 'jumbo-tracker']");
    private final By byRestaurantCityXpath = By.xpath("//h1[contains(text(), 'Best Dining Restaurants near')]");

    public DiningOutScreenWeb(Driver driver, Visual visually) {
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
        String restaurantName = driver.findElement(byRestaurantNameXpath).getText();
        LOGGER.info(String.format("Selected restaurant name: %s", restaurantName));
        return restaurantName;
    }

    @Override
    public String getRestaurantPageHeading() {
        return driver.waitTillElementIsVisible(byRestaurantCityXpath).getText();
    }

    @Override
    public DiningOutScreen selectDineOutOption() {
        driver.waitTillElementIsVisible(byDineOutXpath);
        driver.findElement(byDineOutXpath).click();
        visually.check(SCREEN_NAME, "Dine Out Page", Target.window().fully().layout(byRestaurantsDetailCardXpath));
        return this;
    }
}
