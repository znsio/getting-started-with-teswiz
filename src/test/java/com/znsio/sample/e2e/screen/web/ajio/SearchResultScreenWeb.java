package com.znsio.sample.e2e.screen.web.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import com.znsio.sample.e2e.screen.ajio.SearchResultsScreen;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultScreenWeb extends SearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchResultScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byNumberOfProductsFoundId = By.cssSelector("[class=\"length\"]");
    private static final By bySearchStringId = By.cssSelector("[class=\"header2\"]");
    private static final By byExpandGenderCategoryXpath = By.xpath("//span[contains(text(), 'gender')]//parent::div");
    private static final By byExpandSizeCategoryXpath = By.xpath("//span[contains(text(), 'size & fit')]//parent::div");
    private static final By bySizeMoreOptionXpath = By.xpath("//span[contains(text(), 'size')]//following::div[@id=\"verticalsizegroupformat\"]");
    private static final By byApplyButtonXpath = By.xpath("//button[text()='Apply']");
    private static final By byAppliedFiltersXpath = By.xpath("//span[@class = 'pull-left']");
    private static final String selectGenderCategory = "//input[@name='genderfilter' and @value = '%s']//following-sibling::label";
    private static final String selectSizeCategory = "//input[@name='verticalsizegroupformat' and @value = '%s']//parent::label";
    private static final By byFirstProductXpath = By.xpath("(//a[@class=\"rilrtl-products-list__link\"]//div[@class='brand'])[1]");

    public SearchResultScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getNumberOfProductsFound() {
        String numberOfProducts = driver.waitTillElementIsPresent(byNumberOfProductsFoundId)
                .getText();
        LOGGER.info(String.format("getNumberOfProductsFound: Found '%s'", numberOfProducts));
        return Integer.parseInt(numberOfProducts.split(" ")[0]);
    }

    @Override
    public String getActualSearchString() {
        String actualSearchString = driver.waitTillElementIsPresent(bySearchStringId)
                .getText();
        LOGGER.info(String.format("getActualSearchString: Actual search was for: '%s'", actualSearchString));
        visually.checkWindow(SCREEN_NAME, "Search results screen");
        return actualSearchString;
    }

    @Override
    public SearchResultsScreen refineOnGender(String gender){
        LOGGER.info(String.format("refineOnGender: Refine products on size category '%s'", gender));
        expendCategory(byExpandGenderCategoryXpath);
        driver.waitForClickabilityOf(By.xpath(String.format(selectGenderCategory, gender))).click();
        return this;
    }
    @Override
    public SearchResultsScreen refineOnSize(String size){
        LOGGER.info(String.format("refineOnSize: Refine products on size category '%s'", size));
        expendCategory(byExpandSizeCategoryXpath);
        selectMoreOption();
        driver.waitTillElementIsPresent(By.xpath(String.format(selectSizeCategory, size))).click();
        return this;
    }

    @Override
    public SearchResultsScreen selectApply(){
        LOGGER.info("selectApply: Apply selected filter");
        driver.findElement(byApplyButtonXpath).click();
        visually.checkWindow(SCREEN_NAME, "Applied filter Screen");
        return this;
    }
    @Override
    public int getAppliedFilters() {
        LOGGER.info("getAppliedFilters: get all the applied filter");
        List<WebElement> elements = driver.findElements(byAppliedFiltersXpath);
        return elements.size();
    }
    @Override
    public SearchResultsScreen selectFirstProduct(){
        WebElement webElement = driver.waitTillElementIsPresent(byFirstProductXpath);
        LOGGER.info(String.format("selectFirstProduct: Product Selected: '%s'", webElement.getText()));
        webElement.click();
        return this;
    }

    private void expendCategory(By elementLocator){
        WebElement element = driver.waitTillElementIsPresent(elementLocator);
        if(!element.getAttribute("class").contains("facet-xpndicon"))
            element.click();
    }

    private SearchResultsScreen selectMoreOption(){
        driver.waitForClickabilityOf(bySizeMoreOptionXpath).click();
        return this;
    }

}
