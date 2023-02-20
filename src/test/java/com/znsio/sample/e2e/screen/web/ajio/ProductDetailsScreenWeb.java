package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.e2e.tools.Wait;
import com.znsio.sample.e2e.screen.ajio.AjioCartScreen;
import com.znsio.sample.e2e.screen.ajio.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Set;

public class ProductDetailsScreenWeb extends ProductDetailsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = ProductDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    private static final By byAddToBagClassName= By.className("pdp-addtocart-button");
    private static final By byGoToBagClassName= By.className("pdp-addtocart-button");
    private static final By bySelectedProductNameClassName= By.className("prod-name");


    public ProductDetailsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "SearchResult page");
    }

    @Override
    public ProductDetailsScreen selectSizeAndAddProductToBag(String productSize) {

        LOGGER.info("Selecting size for product");
        String selectSizeXpath = "//div[@class='circle size-variant-item size-instock ']/span[text()='%s']";
        driver.waitForClickabilityOf(By.xpath(String.format(selectSizeXpath,productSize))).click();
        LOGGER.info("Adding product to bag");
        driver.waitForClickabilityOf(byAddToBagClassName).click();
        return this;
    }

    @Override
    public AjioCartScreen goToBag() {
        LOGGER.info("Going to Ajio cart");
        Wait.waitFor(5);
        driver.waitForClickabilityOf(byGoToBagClassName).click();
        return AjioCartScreen.get();
    }

    @Override
    public String getNameOfselectedProduct() {
        LOGGER.info("Getting name of selected Product");
        return driver.waitTillElementIsPresent(bySelectedProductNameClassName).getText();
    }
}