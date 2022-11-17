package com.znsio.sample.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.zomato.ZomatoCityPageScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomePageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ZomatoHomePageScreenWeb extends ZomatoHomePageScreen {

    public static final By byZomatoHeaderXpath = By.xpath("//div[@class='contents-wrapper']");
    public static final By byClickingOnDropdown = By.xpath("(//div[@class='searchContainer']//input)[1]");
    public static final By byZomatoDropdown = By.xpath("//div[@class='sc-geAPOV sc-bJTOcE brXqlb']//p[1]");
    public static final By byDetectLocationXpath = By.xpath("//p[contains(text(),'Detect')]");
    public static final By byCityPageId = By.id("root");
    public static final By byLocationDisabledMsgXpath = By.xpath("//div[@type='error']");
    public static final By validadtingCityPage = By.xpath("(//section[@role='tablist'])[1]");


    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = ZomatoHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public ZomatoHomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public ZomatoHomePageScreen isHomepageVisible() {
        String homePageUrl = driver.getInnerDriver().getCurrentUrl();
        boolean isZomatoHomepageVisble = driver.findElement(byZomatoHeaderXpath).isDisplayed();
        visually.checkWindow(SCREEN_NAME, "On Zomato Homepage Screen");
        if (isZomatoHomepageVisble) {
            LOGGER.info("Zomato Homepage is visible:-" + homePageUrl);
        } else {
            LOGGER.error("Zomato Homepage is not visible:-" + homePageUrl);
        }
        return ZomatoHomePageScreen.get();
    }

    @Override
    public ZomatoCityPageScreen selectLocationFromDropDown(String location) {
        driver.findElement(byClickingOnDropdown).click();
        driver.findElement(byClickingOnDropdown).sendKeys(location);
    //    wait(4);
    //    LOGGER.info("Selecting city from Dropdown");
    //    driver.findElement(byClickingOnDropdown).sendKeys(" ",Keys.ARROW_DOWN,Keys.ENTER);
     //      LOGGER.info("Selecting selected from Dropdown");
    //    driver.waitTillElementIsPresent(validadtingCityPage,10);

        driver.waitTillPresenceOfAllElements(byZomatoDropdown);
        List<WebElement> dropDownContent =driver.findElements(byZomatoDropdown);
    //    wait(3);
        driver.waitTillPresenceOfAllElements(byZomatoDropdown);

        for (WebElement zomatoDropDownlist : dropDownContent) {
    //        wait(2);
            driver.waitTillPresenceOfAllElements(byZomatoDropdown);
            String cityData = zomatoDropDownlist.getText();
            //        wait(2);
            driver.waitTillPresenceOfAllElements(byZomatoDropdown);

            if (cityData.equalsIgnoreCase(location)) {
                driver.waitTillElementIsPresent((By)zomatoDropDownlist,10);
                wait(3);
                zomatoDropDownlist.click();
                driver.waitTillElementIsPresent(validadtingCityPage,10);
                LOGGER.info("City selected:-" +location);
                break;
            } else {
                LOGGER.error("City not selected");
            }
        }
        return ZomatoCityPageScreen.get();
    }

    @Override
    public ZomatoCityPageScreen selectDetectLocation() {
        LOGGER.info("Selecting city using Detect location");
        driver.findElement(byClickingOnDropdown).click();
        driver.waitTillElementIsPresent(byDetectLocationXpath,10);
        driver.findElement(byDetectLocationXpath).click();
        driver.waitTillElementIsPresent(byCityPageId,10);
        boolean isDetectLocationcliked = driver.findElement(byCityPageId).isDisplayed();
        if (isDetectLocationcliked) {
            LOGGER.info("City page opened" +isDetectLocationcliked);
        } else {
            LOGGER.info("City page opened" +isDetectLocationcliked);
        }
        return ZomatoCityPageScreen.get();
    }

    @Override
    public String getLocationErrorMessage() {
        driver.waitTillElementIsPresent(byLocationDisabledMsgXpath,10);
        String getMessage = driver.findElement(byLocationDisabledMsgXpath).getText().trim();
        LOGGER.info("Validating location error message" +getMessage);
        return getMessage;
    }

    public void wait(int value) {
        try {
            Thread.sleep(value *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
