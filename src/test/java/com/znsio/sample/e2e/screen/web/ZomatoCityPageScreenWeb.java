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
import java.util.Map;

public class ZomatoCityPageScreenWeb extends ZomatoCityPageScreen {
    public static final By byClickingOnResturantSearchBoxXpath = By.xpath("//input[@placeholder='Search for restaurant, cuisine or a dish']");
    public static final By byResturantDropdownDataXpath = By.xpath("//input[@placeholder='Search for restaurant, cuisine or a dish']/parent::div//p");
    public static final By validadtingCityPage = By.xpath("(//section[@role='tablist'])[1]");
    public static final By validatingInfoMsg = By.xpath("//div[@class='sc-hvvHee ldTYpO']");


    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = ZomatoCityPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    Map<String,String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
    String place = testData.get("place");

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
        if(cityPageHeader.contains(location.toLowerCase())) {
            LOGGER.info("Zomato city page opened " +location);
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
        visually.checkWindow(SCREEN_NAME, "Validating Resturant Dropdown");
        visually.checkWindow(SCREEN_NAME,"");
        LOGGER.info("Selecting resturant from dropdown");
        List<WebElement> resturantDropDownContent =driver.findElements(byResturantDropdownDataXpath);
        for (WebElement resturantDropdown : resturantDropDownContent) {
            String resturantData = resturantDropdown.getText();
            if(resturantData.contains(resturant)) {
                resturantDropdown.click();
                LOGGER.info("Resturant selected:- " +resturant);
                break;
            } else {
                LOGGER.info("Resturant not selected");
            }
        }
        return ZomatoResturantPageScreen.get();
    }

    @Override
    public boolean validateDetectLocation() {
        driver.waitTillElementIsPresent(validadtingCityPage,20);
        String getCityUrl = driver.getInnerDriver().getCurrentUrl();
        if (getCityUrl.contains(place.toLowerCase())) {
            LOGGER.info("Successfully validated detect location");
            return true;
        } else {
            LOGGER.error("Detect location not working");
            return true;
        }
    }

    @Override
    public String getQuerryWarning() {
        String getMessage = driver.findElement(validatingInfoMsg).getText().trim();
        LOGGER.info("Validating location info message" +getMessage);
        return getMessage;
    }

    @Override
    public ZomatoDishPageScreen selectDish(String dish, String foodStatus) {
        driver.findElement(byClickingOnResturantSearchBoxXpath).click();
        driver.findElement(byClickingOnResturantSearchBoxXpath).sendKeys(dish);
        visually.checkWindow(SCREEN_NAME, "Validating Dish Dropdown");
        LOGGER.info("Validating "+dish+ "for " +foodStatus);
        List<WebElement> dishDropDownContent =driver.findElements(byResturantDropdownDataXpath);
        for (WebElement dishDropdown : dishDropDownContent) {
            String foodItem = dishDropdown.getText().trim();
            if(foodItem.equalsIgnoreCase(dish+" - "+foodStatus)) {
                dishDropdown.click();
                LOGGER.info("Dish selected:-" +dish+ " for " +foodStatus);
                break;
            }
        }
        return ZomatoDishPageScreen.get();
    }

    @Override
    public boolean validateEmptyDropdown(String location) {
        driver.findElement(byClickingOnResturantSearchBoxXpath).click();
        driver.findElement(byClickingOnResturantSearchBoxXpath).sendKeys(location);
        visually.checkWindow(SCREEN_NAME,"Validating location info message");
        boolean isDropdownVisible = driver.findElement(validatingInfoMsg).isDisplayed();
        if (isDropdownVisible) {
            return true;
        } else {
            return false;
        }
    }
}
