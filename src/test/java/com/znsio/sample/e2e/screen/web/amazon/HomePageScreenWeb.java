package com.znsio.sample.e2e.screen.web.amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.HomePageScreen;
import com.znsio.sample.e2e.screen.amazon.SearchPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class HomePageScreenWeb extends HomePageScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = HomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private final By byProductSearchTextBoxXpath = By.xpath("//input[@id='twotabsearchtextbox']");
    private final By bySearchButtonXpath = By.xpath("//input[@id='nav-search-submit-button']");

    public HomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean isHomePageLoaded() {
        return true;
    }

    @Override
    public SearchPageScreen searchProduct(String productName) {
        driver.waitTillElementIsPresent(byProductSearchTextBoxXpath).sendKeys(productName);
        driver.findElement(bySearchButtonXpath).click();
        return this.waitForSearchPageScreenToLoad();
    }

    private SearchPageScreen waitForSearchPageScreenToLoad() {
        SearchPageScreen searchPageScreen = SearchPageScreen.get().waitTillSearchResultMessageIsDisplayed();
        return searchPageScreen;
    }
}
