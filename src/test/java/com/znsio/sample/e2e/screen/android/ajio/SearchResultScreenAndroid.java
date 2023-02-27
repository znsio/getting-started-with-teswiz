package com.znsio.sample.e2e.screen.android.ajio;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.ajio.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SearchResultScreenAndroid extends SearchResultsScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchResultScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byNumberOfProductsFoundId = By.id("toolbar_subtitle_tv");
    private static final By bySearchStringId = By.id("toolbar_title_tv");
    private static final String byCategoryValueXpath = "//android.widget.TextView[contains(@text, '%s')]";
    private static final By byRefineSizeXpath = By.xpath("//android.widget.TextView[contains(@text, 'Size & Fit')]");
    private static final By byApplyFilterId = By.id("filter_view_apply_filter_tv");
    private static final By byAppliedFilterCountId = By.id("plp_filter_subheading_tv");
    private static final By byFirstProductXpath = By.xpath("(//android.widget.ImageView[@resource-id = 'com.ril.ajio:id/plp_row_product_iv'])[1]");
    private static final By byFilterOptionId = By.id("plp_filter_view");
    private static final By byDismissGuideId = By.id("footer_button_2");


    public SearchResultScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }
    @Override
    public int getNumberOfProductsFound() {
        String numberOfProducts = driver.waitTillElementIsPresent(byNumberOfProductsFoundId)
                .getText();
        visually.checkWindow(SCREEN_NAME, "Product result page");
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
    public SearchResultsScreen refineOnGender(String gender) {
        LOGGER.info(String.format("refineOnGender: Refine search on gender: '%s'", gender));
        visually.checkWindow(SCREEN_NAME, "Filter Screen");
        closeGuideOptions();
        selectFilterOption();
        driver.findElement(By.xpath(String.format(byCategoryValueXpath, gender))).click();
        return this;
    }

    @Override
    public SearchResultsScreen refineOnSize(String size) {
        LOGGER.info(String.format("refineOnSize: Refine search on size: '%s'", size));
        selectSizeCategory();
        driver.findElement(By.xpath(String.format(byCategoryValueXpath, size))).click();
        return this;
    }

    @Override
    public int getAppliedFilters() {
        LOGGER.info("getAppliedFilters: Applied filter count");
        String filterCount = driver.findElement(byAppliedFilterCountId).getText();
        return Integer.parseInt(filterCount.trim().split(" ")[0]);
    }

    @Override
    public SearchResultsScreen selectApply() {
        LOGGER.info("selectApply: select apply filter");
        driver.findElement(byApplyFilterId).click();
        return this;
    }

    @Override
    public SearchResultsScreen selectFirstProduct() {
        LOGGER.info("selectFirstProduct: Select first product from the list");
        visually.checkWindow(SCREEN_NAME, "Result page screen");
        driver.waitTillElementIsVisible(byFirstProductXpath).click();
        return this;
    }

    private SearchResultsScreen selectSizeCategory(){
        LOGGER.info("selectSizeCategory: select size category");
        driver.findElement(byRefineSizeXpath).click();
        return this;
    }

    private SearchResultsScreen selectFilterOption(){
        LOGGER.info("selectFilterOption: select apply filter option");
        driver.waitTillElementIsPresent(byFilterOptionId).click();
        return this;
    }

    private SearchResultsScreen closeGuideOptions(){
        LOGGER.info("closeGuideOptions: close user guide");
        driver.waitTillElementIsPresent(byDismissGuideId).click();
        driver.waitTillElementIsPresent(byDismissGuideId).click();
        return this;
    }
}
