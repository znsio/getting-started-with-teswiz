package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.AjioSearchResultsScreen;
import com.znsio.sample.e2e.screen.ajio.ProductDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AjioSearchResultsScreenWeb extends AjioSearchResultsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = AjioHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By bySearchResultXpath = By.xpath("//div[@class=' info search-info']/h1/div[@class='header2']");
    private static final By byProductsFoundClassName = By.className("length");
    private static final By byGenderFilterAppliedXpath = By.xpath("//div[@class='fnl-plp-afliter'][1]/span");
    private static final By byMoreSizesXpath = By.xpath("//div[@class='facet-size-fit-category-description']/following-sibling::div[@class='facet-more']");
    private static final By byApplyButtonXpath = By.xpath("//button[text()='Apply']");
    private static final By bySizeFilterAppliedXpath = By.xpath("//div[@class='fnl-plp-afliter'][2]/span");


    public AjioSearchResultsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "SearchResult page");
    }

    @Override
    public int getNumberOfProductsFound() {
        LOGGER.info("Getting count of products for search result");
        String productCount = driver.waitTillElementIsPresent(byProductsFoundClassName).getText().split(" ")[0];
        return Integer.parseInt(productCount);
    }

    @Override
    public String getActualSearchString() {
        LOGGER.info("Getting Actual search result");
        String actualSearchResult = driver.waitTillElementIsPresent(bySearchResultXpath).getText();
        return actualSearchResult.toLowerCase();

    }

    @Override
    public AjioSearchResultsScreen applyFilterForGender(String gender) {
        LOGGER.info("Applying Gender filter");
        String genderFilterXpath = "//label[@for='%s']";
        System.out.println(String.format(genderFilterXpath, gender));
        driver.waitForClickabilityOf(By.xpath(String.format(genderFilterXpath, gender))).click();
        return this;
    }

    @Override
    public String getActualGenderFilter() {
        LOGGER.info("Getting Actual gender filter");
        String actualGenderFilter = driver.waitTillElementIsPresent(byGenderFilterAppliedXpath).getText();
        return actualGenderFilter;
    }

    @Override
    public AjioSearchResultsScreen applyFilterForSize(String size) {
        LOGGER.info("Applying filter for Size");
        driver.scrollTillElementIntoView(By.xpath("//span[text()='category']"));
        driver.waitForClickabilityOf(By.xpath("//span[text()='size & fit']")).click();
        driver.waitForClickabilityOf(byMoreSizesXpath).click();
        String sizeXpath = "//span[@class='facet-list-title-name' and text()='%s']";
        driver.waitForClickabilityOf(By.xpath(String.format(sizeXpath, size))).click();
        driver.waitForClickabilityOf(byApplyButtonXpath).click();
        return this;
    }

    @Override
    public String getActualSizeFilter() {
        LOGGER.info("Applying Actual size filter");
        String actualSizeFilter = driver.waitTillElementIsPresent(bySizeFilterAppliedXpath).getText();
        return actualSizeFilter;
    }

    @Override
    public ProductDetailsScreen selectProduct(int productNumber) {
        LOGGER.info("Selecting Product");
        String selectProductXpath = "//div[@class='item rilrtl-products-list__item item'][%s]";
        driver.waitForClickabilityOf(By.xpath(String.format(selectProductXpath, productNumber))).click();
        driver.switchToNextTab();
        return ProductDetailsScreen.get();
    }


}
