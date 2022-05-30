package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantListingScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.znsio.e2e.tools.Wait.waitFor;

public class RestaurantListScreenWeb extends RestaurantListingScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantListScreenWeb.class.getSimpleName();

    private static String searchTypeByXpath = "//div[text()='%s']";

    private final By ratingTabByXpath = By.xpath("//div[text()='%s']");

    private final By location = By.xpath("//span[@class='_3HusE']");
    private final By restaurantListByXpath = By.xpath("//div[@class='_3XX_A']/a[1]//div[@class='nA6kb']");
    private final TestExecutionContext context;


    public RestaurantListScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant Listing Screen");
    }


    @Override
    public String getLocationName() {
        driver.waitTillElementIsPresent(location);
        return driver.findElement(location).getText();
    }

    @Override
    public RestaurantListingScreen clickOnRating(String searchCriteria) {
        waitFor(2);
        String searchType = String.format(searchTypeByXpath, searchCriteria);
        driver.waitTillElementIsPresent(By.xpath(searchType)).click();
        return this;
    }

    @Override
    public Integer getRestaurantList() {
        driver.waitTillElementIsPresent(restaurantListByXpath);
        List<WebElement> restaurantList = driver.findElements(restaurantListByXpath);
        return restaurantList.size();
    }

    @Override
    public RestaurantListingScreen selectRestaurant() {
        driver.waitTillElementIsPresent(restaurantListByXpath);
        List<WebElement> restaurantList = driver.findElements(restaurantListByXpath);
        if(restaurantList.size()>0){
            context.addTestState(SAMPLE_TEST_CONTEXT.RESTAURANTNAME,restaurantList.get(0).getText());
            restaurantList.get(0).click();
        }
        return this;
    }
}
