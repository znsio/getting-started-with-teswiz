package com.znsio.sample.e2e.screen.web;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.zomato.ZomatoCityPageScreen;
import com.znsio.sample.e2e.screen.zomato.ZomatoHomePageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ZomatoHomePageScreenWeb extends ZomatoHomePageScreen {

    public static final By byZomatoHeaderXpath = By.xpath("//div[@class='contents-wrapper']");
    public static final By byClickingOnDropdown = By.xpath("(//div[@class='searchContainer']//input)[1]");
    public static final By byZomatoDropdown = By.xpath("//div[@class='searchContainer']//p");


    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonCartScreenWeb.class.getSimpleName();
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
    public boolean isHomepageVisible() {
        String homePageUrl = driver.getInnerDriver().getCurrentUrl();
        boolean isZomatoHomepageVisble = driver.findElement(byZomatoHeaderXpath).isDisplayed();
        visually.checkWindow(SCREEN_NAME, "On Zomato Homepage Screen");
        if (isZomatoHomepageVisble) {
            LOGGER.info("Zomato Homepage is visible:-" + homePageUrl);
        } else {
            LOGGER.error("Zomato Homepage is not visible:-" + homePageUrl);
        }
        return false;
    }

    @Override
    public ZomatoCityPageScreen selectLocationFromDropDown(String location) {
        driver.findElement(byClickingOnDropdown).click();
        driver.findElement(byClickingOnDropdown).sendKeys(location);
        driver.waitTillElementIsVisible(String.valueOf(byZomatoDropdown),10);
        LOGGER.info("Selecting city from Dropdown");
        List<WebElement> dropDownContent =driver.findElements(byZomatoDropdown);
        for (WebElement zomatoDropDownlist : dropDownContent) {
            if (zomatoDropDownlist.getText().equalsIgnoreCase(location)) {
                zomatoDropDownlist.click();
                LOGGER.info("City selected:-" +zomatoDropDownlist.getText().trim());
                break;
            }
        }
        return ZomatoCityPageScreen.get();
    }

    @Override
    public ZomatoCityPageScreen selectDetectLocation() {
        driver.findElement(byClickingOnDropdown).click();

        return null;
    }
}
