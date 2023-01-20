package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonSearch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonSearchWeb extends AmazonSearch {
    private final Driver driver;
    private final WebDriver innerDriver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By bySearchBoxId = By.xpath("//input[@id='twotabsearchtextbox']");
    private static final By bySearchResultXpath=By.xpath("//div[contains(@class,\"s-search-results\")]");
    private static final By bySearchIconId =By.xpath("//input[@id='nav-search-submit-button']");
    private static final By byProductResultXpath=By.xpath("//span[contains(text(),'Apple iPhone 13')]");

    public AmazonSearchWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        visually.checkWindow(SCREEN_NAME, "Home page");
    }
    @Override
    public AmazonSearchWeb searchProductUsingSearchBar(String productName)
    {
        WebElement searchElement=driver.waitTillElementIsPresent(bySearchBoxId);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(productName);
        visually.checkWindow(SCREEN_NAME, "Search string entered");
        WebElement searchIcon=driver.waitTillElementIsPresent(bySearchIconId);
        searchIcon.click();
        LOGGER.info("Searching for the product in progress");
        return this;

    }

    public boolean isSearchComplete()
    {
        visually.checkWindow(SCREEN_NAME, "Searching");
        try {
            driver.waitTillElementIsPresent(bySearchResultXpath);
            LOGGER.info(System.out.printf("searching related products"));
            return true;
        }
        catch (Exception e)
        {
            LOGGER.info("exception in search completion");
        }
        return false;

    }

    public boolean viewProductResultsAfterSearching()
    {
        List<WebElement> actualProducts=driver.findElements(byProductResultXpath);
        visually.checkWindow(SCREEN_NAME, "viewing after search");
        if(actualProducts.size()!=0)
        {
            WebElement firstProduct=driver.waitTillElementIsPresent(byProductResultXpath);
            JavascriptExecutor js = (JavascriptExecutor) innerDriver;
            js.executeScript("arguments[0].click()", firstProduct);
            LOGGER.info(System.out.printf("Clicked on first search related product"));
            return true;
        }
        return  false;
    }

}
