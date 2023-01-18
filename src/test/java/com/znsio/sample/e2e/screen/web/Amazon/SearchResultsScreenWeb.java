package com.znsio.sample.e2e.screen.web.Amazon;

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

public class SearchResultsScreenWeb extends SearchResultsScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SearchResultsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchStringXpath= By.xpath("//span[@class='a-color-state a-text-bold']");
    private static final By byProductsXpath = By.xpath("//span[contains(@class,'a-size-medium a-color-base a-text-normal')]");

    public SearchResultsScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread()
                .getId();
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Search Results Screen Web");
    }
    @Override
    public String getSearchText() {
        String actualSearchString = driver.waitTillElementIsPresent(bySearchStringXpath).getText();
        LOGGER.info(String.format("Actual search was for: '%s'", actualSearchString));
        return actualSearchString;
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
        String itemTitle = getItems().get(itemIndex).getText();
        LOGGER.info(String.format("Fetching item title: '%s'", itemTitle));
        return itemTitle;
    }

    @Override
    public List<String> getItemTitles() {
        List<WebElement> items = getItems();
        LOGGER.info("Fetching item titles");
        return items.stream().map(item -> item.getText().toLowerCase()).collect(Collectors.toList());
    }
}
