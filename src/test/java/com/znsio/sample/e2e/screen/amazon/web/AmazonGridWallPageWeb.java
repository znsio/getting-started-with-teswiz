package com.znsio.sample.e2e.screen.amazon.web;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.amazon.GridWallPageBL;
import com.znsio.sample.e2e.businessLayer.amazon.ProductPageBL;
import com.znsio.sample.e2e.screen.amazon.AmazonGridWallPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonGridWallPageWeb extends AmazonGridWallPageScreen {
    private final Visual visually;
    private final Driver driver;
    public static final String SCREEN_NAME = AmazonGridWallPageWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By header_Results = By.xpath("//span[text()='RESULTS']");
    private final By list_SearchResults = By.xpath("//div[@data-component-type='s-search-result']");

    public By productFromTheSearchResults(String productToBeSelected) {
       By productFromTheSearchResults = By.xpath("//div[@data-component-type='s-search-result'][1]//h2//a//span[text()='"+productToBeSelected+"']");
        return productFromTheSearchResults;
    }

    public AmazonGridWallPageWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public Boolean checkForResultsHeader() {
        Boolean isText_RESULTS_IsPresent=false;
        String headerText = driver.waitTillElementIsPresent(header_Results,10)
                .getText()
                .trim();
        if (headerText.equals("RESULTS")) {
            isText_RESULTS_IsPresent = true;
        }
        return isText_RESULTS_IsPresent;
    }

    @Override
    public Boolean checkForProductResultsSize() {
        Boolean isResultsSizeIsMoreThanOne = false;
        int resultsSize = driver.findElements(list_SearchResults).size();
        if (resultsSize>1) {
            isResultsSizeIsMoreThanOne = true;
        }
        return isResultsSizeIsMoreThanOne;
    }

    @Override
    public ProductPageBL selectProduct(String productToBeSelected) {
        LOGGER.info(String.format("ProductDesc: "+productToBeSelected+""));
        driver.waitTillElementIsPresent(productFromTheSearchResults(productToBeSelected),10).click();
        visually.takeScreenshot(SCREEN_NAME, "After Selecting the product from the serach results");
        return new ProductPageBL();
    }


}
