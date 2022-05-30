package com.znsio.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.businessLayer.SearchBL;
import com.znsio.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.RestaurantMenuScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.znsio.e2e.tools.Wait.waitFor;

public class RestaurantMenuScreenWeb extends RestaurantMenuScreen {
    private final Driver driver;
    private static final Logger LOGGER = Logger.getLogger(SearchBL.class.getName());
    private final TestExecutionContext context;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantMenuScreenWeb.class.getSimpleName();
    private final By restaurantNameByxpath = By.xpath("//h1[@class='_3aqeL']");

    private final By menuListByXpath = By.xpath("//div[@class='_1RPOp' and text()='ADD']/ancestor::div[3]/preceding-sibling::div//h3[contains(@class,'itemNameText')]");
    private static String addButtonByXpath = "//h3[contains(text(),'%s')]/ancestor::div[2]/following-sibling::div//div[text()='ADD']";

    private final By searchFoodItem = By.xpath("//input[@placeholder='Search for dishes...']");

    private final By customiseItemByXpath = By.xpath("//div[contains(text(),'Customize')]");

    private final By addItemButtonByXpath = By.xpath("//span[text()='Add Item']");

    private final By cartOptionByXpath = By.xpath("//span[text()='Cart']");

    public RestaurantMenuScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Restaurant Screen");
    }


    @Override
    public String getRestaurantName() {
        driver.waitTillElementIsPresent(restaurantNameByxpath);
        return driver.findElement(restaurantNameByxpath).getText();
    }


    @Override
    public RestaurantMenuScreen searchAndSelectFoodItem() {
        driver.waitTillElementIsPresent(menuListByXpath);
        List<WebElement> menuList = driver.findElements(menuListByXpath);
        Random rand = new Random();
        WebElement itemName = menuList.get(rand.nextInt(menuList.size()));
        String addButtonxpath = String.format(addButtonByXpath, itemName.getText());
        ((JavascriptExecutor) driver.getInnerDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", driver.findElement(By.xpath(addButtonxpath)));
        // driver.scrollTillElementIntoView(By.xpath(addButtonxpath));
        driver.findElement(By.xpath(addButtonxpath)).click();
        context.addTestState(SAMPLE_TEST_CONTEXT.FOOD_ITEM_NAME, itemName.getText());
        waitFor(3);
        if (driver.isElementPresent(customiseItemByXpath)) {
            driver.findElement(customiseItemByXpath).click();
            waitFor(3);
        }

        return this;

    }

    @Override
    public RestaurantMenuScreen increaseTheQuanity() {
        return this;
    }
}
