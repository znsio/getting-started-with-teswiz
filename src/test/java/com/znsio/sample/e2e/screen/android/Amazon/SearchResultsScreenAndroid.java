package com.znsio.sample.e2e.screen.android.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.OrdinalToNumber;
import com.znsio.sample.e2e.screen.amazon.ItemsDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.SearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsScreenAndroid extends SearchResultsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchResultsScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By byProductsXpath = By.xpath("//android.view.View[contains(@content-desc,'out of')]/preceding-sibling::android.view.View[last()]");

    public SearchResultsScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Results Screen Android");
    }


    @Override
    public boolean isScreenLoaded() {

        boolean loadStatus = driver.waitTillElementIsPresent(byProductsXpath).isDisplayed();
        LOGGER.info(String.format("Search result screen Loaded: '%s'", loadStatus));
        return loadStatus;
    }

    private List<WebElement> getItems() {
        return driver.findElements(byProductsXpath);
    }

    @Override
    public ItemsDetailsScreen clickOnItemByPosition(String itemPosition) {
        int itemIndex = OrdinalToNumber.valueOf(itemPosition.toUpperCase()).ordinal();
        LOGGER.info(String.format("Selecting item at index: '%s'", itemIndex));
        getItems().get(itemIndex).click();
        return ItemsDetailsScreen.get();
    }

    @Override
    public String getItemText(String itemPosition) {
        int itemIndex = OrdinalToNumber.valueOf(itemPosition.toUpperCase()).ordinal();
        String itemTitle = getItems().get(itemIndex).getAttribute("content-desc");
        LOGGER.info(String.format("Fetching item title: '%s'", itemTitle));
        return itemTitle;
    }

    @Override
    public List<String> getItemTitles() {
        List<WebElement> items = getItems();
        LOGGER.info("Fetching item titles");
        return items.stream().map(item -> item.getAttribute("content-desc").toLowerCase()).collect(Collectors.toList());
    }
}
