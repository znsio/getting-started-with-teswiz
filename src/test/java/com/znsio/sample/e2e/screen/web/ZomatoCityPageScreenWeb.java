package com.znsio.sample.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.zomato.ZomatoCityPageScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoDishPageScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoResturantPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ZomatoCityPageScreenWeb extends ZomatoCityPageScreen {
    public static final By byClickingOnResturantSearchBoxXpath = By.xpath("//input[@placeholder='Search for restaurant, cuisine or a dish']");
    public static final By byResturantDropdownDataXpath = By.xpath("//input[@placeholder='Search for restaurant, cuisine or a dish']/parent::div//p");
    public static final By byLocationInfoMessageXpath = By.xpath("//div[@class='sc-aewfc itTtbE']");

    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = ZomatoCityPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public ZomatoCityPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public boolean validateLocation(String location) {
        String cityPageHeader = driver.getInnerDriver().getCurrentUrl();
        visually.checkWindow(SCREEN_NAME, "On Zomato City Page Screen");
        if(cityPageHeader.contains(location)) {
            LOGGER.info("Zomato city page opened" +location);
            return true;
        } else {
            LOGGER.info(location+ "city page not opened");
            return false;
        }
    }

    @Override
    public ZomatoResturantPageScreen selectResturantFromDropdown(String resturant) {
        driver.findElement(byClickingOnResturantSearchBoxXpath).click();
        driver.findElement(byClickingOnResturantSearchBoxXpath).sendKeys(resturant);
        driver.waitTillElementIsVisible(String.valueOf(byClickingOnResturantSearchBoxXpath),10);
        LOGGER.info("Select Resturant from dropdown");
        List<WebElement> resturantDropDownContent =driver.findElements(byResturantDropdownDataXpath);
        for (WebElement resturantDropdown : resturantDropDownContent) {
            if(resturantDropdown.getText().equalsIgnoreCase(resturant)) {
                resturantDropdown.click();
                LOGGER.info("Resturant selected:-" +resturantDropdown.getText().trim());
                break;
            }
        }
        return ZomatoResturantPageScreen.get();
    }

    @Override
    public boolean validateDetectLocation() {

        return false;
    }

    @Override
    public String getQuerryWarning() {
        String getMessage = driver.findElement(byLocationInfoMessageXpath).getText().trim();
        LOGGER.info("Validating location info message" +getMessage);
        return getMessage;
    }

    @Override
    public ZomatoDishPageScreen selectDish(String dish, String foodStatus) {
        driver.findElement(byClickingOnResturantSearchBoxXpath).click();
        driver.findElement(byClickingOnResturantSearchBoxXpath).sendKeys(dish);
        driver.waitTillElementIsVisible(String.valueOf(byClickingOnResturantSearchBoxXpath),10);
        LOGGER.info("Validating"+dish+ "for" +foodStatus);
        List<WebElement> dishDropDownContent =driver.findElements(byResturantDropdownDataXpath);
        for (WebElement dishDropdown : dishDropDownContent) {
            if(dishDropdown.getText().trim().equalsIgnoreCase(dish+" - "+foodStatus)) {
                dishDropdown.click();
                LOGGER.info("Dish selected:-" +dishDropdown.getText().trim());
                break;
            }
        }
        return ZomatoDishPageScreen.get();
    }
}
