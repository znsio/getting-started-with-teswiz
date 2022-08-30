package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.ProductPageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchPageScreen;
import com.znsio.sample.e2e.screen.android.jiomeet.LandingScreenAndroid;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageScreenWeb extends SearchPageScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = LandingScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By bySearchResultPageMessageBarXpath = By.xpath("//span[@data-component-type='s-result-info-bar']");
    private final By bySearchResultPageNoResultFoundXpath = By.xpath("//span[@data-component-type='s-search-results']");
    private final By byProductWidgetsOnSearchResultPageXpath = By.xpath("//span[@data-component-type='s-product-image']");

    public SearchPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean isSearchResultPageLoaded() {
        return false;
    }

    @Override
    public String getSearchResultLabelText() {
        return null;
    }

    @Override
    public SearchPageScreen waitTillSearchResultMessageIsDisplayed(){
        driver.waitTillElementIsPresent(bySearchResultPageMessageBarXpath);
        return this;
    }

    @Override
    public ProductPageScreen selectProductFromSearchResultPage() {
        WebElement firstWidget = driver.findElement(byProductWidgetsOnSearchResultPageXpath);
        firstWidget.click();
        driver.switchToNextTab();
        return this.waitForSelectProductPageScreenToLoad();
    }

    private ProductPageScreen waitForSelectProductPageScreenToLoad() {
        ProductPageScreen productPageScreen = ProductPageScreen.get().waitTillProductPageIsDisplayed();
        return productPageScreen;
    }
}
