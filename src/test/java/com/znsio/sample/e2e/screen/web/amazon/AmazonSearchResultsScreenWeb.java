package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProductDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import com.znsio.sample.e2e.screen.android.ajio.AjioSearchResultsScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AmazonSearchResultsScreenWeb extends AmazonSearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By bySearchStringXpath = By.xpath("//span[contains(text(),\"results\")]/following-sibling::span[2]");

    private static final By byProductFoundXpath = By.xpath("(//span[contains(text(),'iPhone 13')])[3]");

    private static final By byProductCostXpath = By.xpath("(//span[@class='a-price'])[1]/span");


    public AmazonSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

//    @Override
//    public int getNumberOfProductsFound() {
//        String numberOfProductsText = driver.findElementByXpath(byNumberOfProductsFoundText).getText();
//
//        String numberOfProducts = driver.waitTillElementIsPresent(byNumberOfProductsFoundId)
//                .getText();
//        LOGGER.info(String.format("Found '%s'", numberOfProducts));
//        return Integer.parseInt(numberOfProducts.split(" ")[0]);
//    }

    @Override
    public String getActualSearchString() {
        String searchString = driver.waitTillElementIsPresent(bySearchStringXpath)
                .getText();
        String actualSearchString = searchString.substring(1, searchString.length() - 1);
        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
        visually.checkWindow(SCREEN_NAME, "Search results screen");
        return actualSearchString;
    }

    @Override
    public AmazonProductDetailsScreen clickOnFirstItem(){

        driver.waitTillElementIsPresent(byProductFoundXpath).click();
        return AmazonProductDetailsScreen.get();
    }

    @Override
    public String getFirstItemName(){
        String itemName = driver.waitTillElementIsPresent(byProductFoundXpath)
                .getText();

        LOGGER.info(String.format("Expected item name: '%s'", itemName));
        return itemName;
    }

    @Override
    public String getFirstItemCost(){
        String itemCost = driver.waitTillElementIsPresent(byProductCostXpath)
                .getText();

        LOGGER.info(String.format("Expected item cost: '%s'", itemCost));
        return itemCost;
    }
}
