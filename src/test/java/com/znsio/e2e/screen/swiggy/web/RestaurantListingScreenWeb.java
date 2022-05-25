package com.znsio.e2e.screen.swiggy.web;

import com.znsio.e2e.screen.swiggy.RestaurantListingScreen;
import com.znsio.e2e.screen.swiggy.RestaurantProfileScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.e2e.tools.Wait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.znsio.e2e.tools.Wait.waitFor;

public class RestaurantListingScreenWeb extends RestaurantListingScreen {

    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final Logger LOGGER = Logger.getLogger(RestaurantListingScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantListingScreenWeb.class.getSimpleName();

    private By ratingsSortXpath = By.xpath("//div[contains(text(),'Rating')]");
    private By searchedRestaurantsCount = By.xpath("//div[@id='all_restaurants']//div[contains(text(),'restaurants')]");
    private By searchResultRestaurantListXpath = By.xpath("//div[@id='all_restaurants']//div/a[not(text()='Search')]");
    private String restaurantNameXpathStr = "//div[@id='all_restaurants']//div[contains(text(),'%s')]";
    private By setDeliveryLocationNameXpath = By.xpath("//a[contains(@title,'Swiggy')]/following-sibling::div/span[2]");

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
        driver.moveToElement(restaurantNameXpath);
        driver.waitTillElementIsPresent(restaurantNameXpath).click();
        return RestaurantProfileScreen.get();
    }

    @Override
    public int getRestaurantCountForSearchedLocation() {
        String totalRestaurantsStr=driver.waitTillElementIsPresent(searchedRestaurantsCount).getText();
        String[] arrayOfRestaurangCountsplit=totalRestaurantsStr.split(" ");
        LOGGER.info("Restaurants Found for Searched Delivery Location:"+arrayOfRestaurangCountsplit[0]);
        return Integer.parseInt(arrayOfRestaurangCountsplit[0]);
    }

    /**
     *
     * @param oneBasedIndxValue It's an 1 based Index value that comes as String hence needs to be parsed and has to be subtracted by 1 to derive original index value.
     * @return will be returning page object for Restaurant's profile page.
     */
    @Override
    public RestaurantProfileScreen selectRestaurantByIndex(String oneBasedIndxValue) {
        List<WebElement> restaurantWebEleList=driver.findElements(searchResultRestaurantListXpath);
        for(int i=0;i<restaurantWebEleList.size();i++){
            if(i==(Integer.parseInt(oneBasedIndxValue)-1)){
                LOGGER.info("Navigating to Selected Restaurant's profile page:"+restaurantWebEleList.get(i).getAttribute("href"));
                restaurantWebEleList.get(i).click();
                break;
            }
        }
        return RestaurantProfileScreen.get();
    }


    public RestaurantProfileScreen selectRestaurantByRandomIndex() {
        Random rand = new Random();
        List<WebElement> restaurantWebEleList=driver.findElements(searchResultRestaurantListXpath);
       // int randomIndx=rand.nextInt(((restaurantWebEleList.size()-1)+1)+1);
        int randomIndx=rand.nextInt(restaurantWebEleList.size());
        LOGGER.info("Total Restaurants discovered on page:"+restaurantWebEleList.size());
        LOGGER.info("Attempting to select restaurant at location:"+(randomIndx-1));
        for(int i=0;i<restaurantWebEleList.size();i++){
            if(i==(randomIndx-1)){
                waitFor(1);
                LOGGER.info("Navigating to Selected Restaurant's profile page:"+restaurantWebEleList.get(i).getAttribute("href"));
                restaurantWebEleList.get(i).click();
                break;
            }
        }
        return RestaurantProfileScreen.get();
    }

    @Override
    public String getSetDeliveryLocationName() {
        return driver.waitTillElementIsPresent(setDeliveryLocationNameXpath).getText();
    }

}
