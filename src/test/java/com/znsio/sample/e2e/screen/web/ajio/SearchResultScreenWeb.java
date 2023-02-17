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
        LOGGER.info(String.format("Found '%s'", numberOfProducts));
        return Integer.parseInt(numberOfProducts.split(" ")[0]);
    }

    @Override
    public String getActualSearchString() {
        String actualSearchString = driver.waitTillElementIsPresent(bySearchStringId)
                .getText();
        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
        visually.checkWindow(SCREEN_NAME, "Search results screen");
        return actualSearchString;
    }

    @Override
    public SearchResultsScreen refineOnGender(String gender){
        expendCategory(byExpandGenderCategoryXpath);
        driver.waitForClickabilityOf(By.xpath(String.format(selectGenderCategory, gender))).click();
        LOGGER.info(String.format("Refined products on gender category '%s'", gender));
        return this;
    }
    @Override
    public SearchResultsScreen refineOnSize(String size){
        expendCategory(byExpandSizeCategoryXpath);
        selectMoreOption();
        driver.waitTillElementIsPresent(By.xpath(String.format(selectSizeCategory, size))).click();
        LOGGER.info(String.format("Refined products on size category '%s'", size));
        return this;
    }

    private SearchResultsScreen selectMoreOption(){
        driver.waitForClickabilityOf(bySizeMoreOptionXpath).click();
        return this;
    }

    @Override
    public SearchResultsScreen selectApply(){
        driver.findElement(byApplyButtonXpath).click();
        LOGGER.info(String.format("Select apply filters"));
        visually.checkWindow(SCREEN_NAME, "Applied filter Screen");
        return this;
    }
    @Override
    public List<String> getAppliedFilters() {
        List<String> appliedFilters = new ArrayList<>();
        List<WebElement> elements = driver.findElements(byAppliedFiltersXpath);
        for(int ele=0; ele<elements.size(); ele++){
            appliedFilters.add(elements.get(ele).getText());
            LOGGER.info(String.format("Applied filter: '%s'", elements.get(ele).getText()));
        }
        return appliedFilters;
    }
    @Override
    public SearchResultsScreen selectFirstProduct(){
        WebElement webElement = driver.waitTillElementIsPresent(byFirstProductXpath);
        LOGGER.info(String.format("Product Selected: '%s'", webElement.getText()));
        webElement.click();
        return this;
    }

    private void expendCategory(By elementLocator){
        WebElement element = driver.waitTillElementIsPresent(elementLocator);
        if(!element.getAttribute("class").contains("facet-xpndicon"))
            element.click();
    }


}
