package com.znsio.e2e.screen.swiggynew.web;

import com.znsio.e2e.screen.swiggynew.RestaurantListScreen;
import com.znsio.e2e.screen.swiggynew.RestaurantScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.znsio.e2e.tools.Wait.waitFor;

public class RestaurantListScreenWeb extends RestaurantListScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(RestaurantListScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantListScreenWeb.class.getSimpleName();

    private String sortExpressionString = "//div[contains(text(),'%s')]";
    private By searchedRestaurantsCount = By.xpath("//div[@id='all_restaurants']//div[contains(text(),'restaurants')]");
    private By searchResultRestaurantListXpath = By.xpath("//div[@id='all_restaurants']//div/a[not(text()='Search')]");
    private String restaurantNameXpathStr = "//div[@id='all_restaurants']//div[contains(text(),'%s')]";
    private By deliveryLocationNameSetXpath = By.xpath("//a[contains(@title,'Swiggy')]/following-sibling::div/span[2]");

    public RestaurantListScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant List screen");
    }

    @Override
    public RestaurantListScreen sortBy(String sortCriteria) {
        By sortExpressionXpath = By.xpath(String.format(sortExpressionString, sortCriteria));
        driver.waitForClickabilityOf(sortExpressionXpath);
        driver.findElement(sortExpressionXpath).click();
        waitFor(3);
        return this;
    }

    @Override
    public String getDeliveryLocationName() {
        waitFor(1);
        return driver.waitTillElementIsPresent(deliveryLocationNameSetXpath).getText();
    }

    @Override
    public RestaurantScreen selectRestaurantByRandomIndex() {
        try {
            Random rand = new Random();
            List<WebElement> restaurantWebEleList = driver.findElements(searchResultRestaurantListXpath);
            int urlCounter=0;
            for(WebElement restaurantWe:restaurantWebEleList){
                //LOGGER.info(restaurantWe);
                LOGGER.info("Restaurant URL "+(urlCounter+1)+":"+restaurantWe.getAttribute("href"));
                urlCounter+=1;
            }
            LOGGER.info("Total Web Elements captured:"+restaurantWebEleList.size());
            int randomIndx = rand.nextInt(restaurantWebEleList.size());
            while(restaurantWebEleList.get(randomIndx).getAttribute("href")==null){
                LOGGER.info("Result in loop(Reset index if true):"+(restaurantWebEleList.get(randomIndx).getAttribute("href") == null));
                randomIndx = rand.nextInt(restaurantWebEleList.size());
                LOGGER.info("New Index to try:"+randomIndx);
            }

            LOGGER.info("Total Restaurants discovered on page:" + restaurantWebEleList.size());
            LOGGER.info("Attempting to select restaurant at location:" + (randomIndx + 1));
            restaurantWebEleList.get(randomIndx).click();
            LOGGER.info("Navigating to Selected Restaurant's profile page:" + restaurantWebEleList.get(randomIndx).getAttribute("href"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return RestaurantScreen.get();
    }

    @Override
    public RestaurantScreen selectRestaurantAtFirstIndex() {
        try{
            List<WebElement> restaurantWebEleList = driver.findElements(searchResultRestaurantListXpath);
            restaurantWebEleList.get(0).click();
            LOGGER.info("Navigating to Selected Restaurant's profile page:" + restaurantWebEleList.get(0).getAttribute("href"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return RestaurantScreen.get();
    }


}
