package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AmazonSearchResultsScreenWeb extends AmazonSearchResultsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By productInSearchResultsStringByCSS = By.cssSelector("[data-component-type=\"s-result-info-bar\"] .a-color-state");
    private static final By totalInSearchResultsStringByCSS = By.xpath("//span[contains(text(), \"results for\")]");
    private static final By firstProductByXpath = By.xpath("(//span[contains(text(),'iPhone 13')])[4]");

    public AmazonSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Results Page");
    }

    @Override
    public int getNumberOfProductsFound() {
        String searchString = driver.waitTillElementIsPresent(totalInSearchResultsStringByCSS).getText();
        String totalProductsInString = StringUtils.substringBefore(StringUtils.substringAfter(searchString, "of"), "results").strip();
        int totalProductsFound = Integer.parseInt(totalProductsInString);
        LOGGER.info(String.format("Total products found : %d", totalProductsFound));
        return totalProductsFound;
    }

    @Override
    public String getActualSearchString() {
        String productInSearchString = driver.waitTillElementIsPresent(productInSearchResultsStringByCSS).getText().replaceAll("\"", "");
        LOGGER.info(String.format("Product found in search string : %s", productInSearchString));
        return productInSearchString;
    }

    @Override
    public AmazonProductViewPageScreenWeb selectFirstProductInSearchResultsList(){
        WebElement firstProduct = driver.waitTillElementIsPresent(firstProductByXpath);
        firstProduct.click();
        driver.switchToNextTab();
        return new AmazonProductViewPageScreenWeb(driver, visually);
    }
}
