package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.sample.e2e.screen.ajio.AjioProductDetailsScreen;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AjioSearchResultsScreenWeb
        extends AjioSearchResultsScreen {
    private static final String SCREEN_NAME = AjioSearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byitemsFoundClassName = By.className("length");
    private static final By bysearchedProductResultXpath = By.xpath("//div[@class=' info search-info']//div[@class='header2']");
    private static final By byitemsListXpath = By.xpath("//a[@class='rilrtl-products-list__link' and not(contains(@id,'jioAdsContainer'))]");


    private final Driver driver;
    private final Visual visually;

    public AjioSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getNumberOfProductsFound() {
        String itemsFound = driver.findElement(byitemsFoundClassName).getText().split("\\s+")[0];
        LOGGER.info("number of items found for the searched product : " + itemsFound);
        return Integer.parseInt(itemsFound.replaceAll(",",""));
    }

    @Override
    public String getActualSearchString() {
        String actualSearchProduct = driver.waitTillElementIsVisible(bysearchedProductResultXpath).getText();
        LOGGER.info("Search  Product Result : " + actualSearchProduct);
        return actualSearchProduct;
    }

    @Override
    public AjioProductDetailsScreen goToProductDetails(int itemNumber) {
        LOGGER.info(String.format("Selecting %s th product from the item list ", itemNumber));
        getProductList().get(itemNumber-1).click();
        return AjioProductDetailsScreen.get();
    }


    private List<WebElement> getProductList() {
        LOGGER.info("Getting list of items for searched product");
        return driver.findElements(byitemsListXpath);
    }
}
