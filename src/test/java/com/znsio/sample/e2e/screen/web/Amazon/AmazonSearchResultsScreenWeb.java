package com.znsio.sample.e2e.screen.web.Amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.OrdinalToNumber;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.amazon.AmazonItemsDetailsScreen;
import com.znsio.sample.e2e.screen.amazon.AmazonSearchResultsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonSearchResultsScreenWeb extends AmazonSearchResultsScreen {

    private final Driver driver;
    private final TestExecutionContext context;

    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchResultsScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private static final By bySearchStringXpath= By.xpath("//span[@class='a-color-state a-text-bold']");
    private static final By byProductsXpath = By.xpath("//span[contains(@class,'a-size-medium a-color-base a-text-normal')]");

    public AmazonSearchResultsScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
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
    public AmazonItemsDetailsScreen clickOnItemByPosition(String itemPosition) {
        int itemIndex = OrdinalToNumber.valueOf(itemPosition.toUpperCase()).ordinal();
        LOGGER.info(String.format("Clicking on Product title: '%s'", getItemText(itemPosition)));
        getItems().get(itemIndex).click();
        return AmazonItemsDetailsScreen.get();
    }

    @Override
    public String getItemText(String itemPosition) {
        int itemIndex = OrdinalToNumber.valueOf(itemPosition.toUpperCase()).ordinal();
        String itemTitle = getItems().get(itemIndex).getText();
        context.addTestState(SAMPLE_TEST_CONTEXT.ITEM_TITLE, itemTitle);
        return itemTitle;
    }


    @Override
    public List<String> getItemTitles() {
        List<WebElement> items = getItems();
        return items.stream().map(item -> item.getText().toLowerCase()).collect(Collectors.toList());
    }
}
