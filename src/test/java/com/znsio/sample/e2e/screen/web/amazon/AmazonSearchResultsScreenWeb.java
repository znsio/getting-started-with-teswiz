package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchResultsScreenWeb extends AmazonSearchResultsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By productInSearchResultsStringByCSS = By.cssSelector("[data-component-type=\"s-result-info-bar\"] .a-color-state");
    private static final By totalInSearchResultsStringByCSS = By.xpath("//span[contains(text(), \"results for\")]");
    private static final By searchResultsProductTitlesByCSS = By.cssSelector(".s-result-item .s-card-container .s-title-instructions-style .a-size-medium");

    public AmazonSearchResultsScreenWeb(Driver driver, Visual visually) {

        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Results Page");
    }

    @Override
    public int getNumberOfProductsFound() {

        LOGGER.info("Fetching products count from the search result string");
        String searchString = driver.waitTillElementIsPresent(totalInSearchResultsStringByCSS).getText();
        String totalProductsInString = StringUtils.substringBefore(StringUtils.substringAfter(searchString, "of"), "results").strip();
        int totalProductsFound = Integer.parseInt(totalProductsInString);
        LOGGER.info(String.format("Total products found : %d", totalProductsFound));
        return totalProductsFound;
    }

    @Override
    public String getActualSearchString() {

        LOGGER.info("Fetching product name in actual search string");
        String productInSearchString = driver.waitTillElementIsPresent(productInSearchResultsStringByCSS).getText().replaceAll("\"", "");
        LOGGER.info(String.format("Product found in search string : %s", productInSearchString));
        return productInSearchString;
    }

    @Override
    public AmazonProductViewPageScreenWeb clickOnFirstProductInSearchResultsList() {

        WebDriverWait wait = new WebDriverWait(driver.getInnerDriver(), 30);
        List<WebElement> productTitleWebElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultsProductTitlesByCSS));

        productTitleWebElements.get(4).click();
        driver.switchToNextTab();
        LOGGER.info("Clicked on first product in search results list");

        visually.checkWindow(SCREEN_NAME, "First product selected from search results");
        return new AmazonProductViewPageScreenWeb(driver, visually);
    }

    @Override
    public List<String> getTitleOfProductsInSearchResultsList() {

        LOGGER.info("Fetching title of all products in search results list");
        List<String> productTitles = new ArrayList<>();

        WebDriverWait wait = new WebDriverWait(driver.getInnerDriver(), 30);
        List<WebElement> productTitleWebElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultsProductTitlesByCSS));

        for (WebElement element : productTitleWebElements) {
            productTitles.add(element.getText().trim());
        }
        return productTitles;
    }
}
