package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.znsio.e2e.tools.Wait.waitFor;

public class RestaurantScreenWeb extends RestaurantScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SwiggyHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By addItemButton = By.xpath("//div[@class='styles_itemAddButton__zJ7-R']/div[contains(.,'ADD')]");
    //div[contains(.,'ADD')]/following-sibling::div[@class='_29Y5Z']
    private final String removeItemButton = "//div[contains(.,'ADD')]/following-sibling::div[@class='_29Y5Z']";
    private final String addItemName = "//h3[@class='styles_itemNameText__3ZmZZ']";
    private final String removeItemName = "//div/i/following-sibling::div[@class='_33KRy']";
    private final TestExecutionContext context;

    public RestaurantScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant screen for Swiggy");
    }

    @Override
    public RestaurantScreen addItem(int noOfItems) {
        driver.waitTillElementIsPresent(addItemButton);
        List<WebElement> itemList = driver.findElements(addItemButton);
        List<WebElement> itemNameList = driver.findElements(By.xpath(addItemName));
        for (int itemCounter = 1; itemCounter <= noOfItems; itemCounter++) {
            itemList.get(itemCounter - 1).click();
            context.addTestState("Item name " + itemCounter, itemNameList.get(itemCounter - 1).getText());
            waitFor(2);
        }
        return this;
    }

    @Override
    public RestaurantScreen removeItem() {
        for (int itemCounter = 1; itemCounter <= (Integer)context.getTestState("ExpectedCartCount"); itemCounter++) {
            context.addTestState("Removed item name " + itemCounter, driver.findElement(By.xpath(removeItemName)).getText());
            System.out.println("Removed item name " + itemCounter + " is " + context.getTestState("Removed item name " + itemCounter));
            driver.findElement(By.xpath(removeItemButton)).click();
            waitFor(2);
        }
        return this;
    }
}