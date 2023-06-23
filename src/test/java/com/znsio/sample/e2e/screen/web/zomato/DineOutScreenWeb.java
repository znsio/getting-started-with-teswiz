package com.znsio.sample.e2e.screen.web.zomato;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.DineOutScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DineOutScreenWeb extends DineOutScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = DineOutScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(DineOutScreenWeb.class.getName());
    private final TestExecutionContext context;
    private final String dineoutPagePartialUrl = "dine-out";
    private final By byDiningOutTabText = By.xpath("//div[text()='Dining Out']");
    private final By byLocationSearchInput = By.xpath("(//ul[starts-with(@id,'navigation')]/li/div//div/div/input)[1]");
    private final By byLocationSearchDropdown = By.xpath("(//ul[starts-with(@id,'navigation')]/li/div/div/div/i)[2]");
    private String subLocationCityNameText = "//ul[starts-with(@id,'navigation')]/li/div/div/div/div/div/p[starts-with(@class,'sc') and text()='%s']";
    private final By byRestaurantNameText = By.xpath("//div[@id='root']/div/div[9]/div/div[3]/div/div/a/div/h4");

    public DineOutScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
    }

    @Override
    public boolean verifyRedirectionToDineoutPage() {
        driver.getInnerDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        LOGGER.info("Verifying redirection to dine out page using url");
        return driver.getInnerDriver().getCurrentUrl().contains(dineoutPagePartialUrl) && driver.isElementPresent(byDiningOutTabText);
    }

    @Override
    public DineOutScreen selectLocation() {
        visually.checkWindow(SCREEN_NAME, "Dineout Screen");
        String location = getLocationFromTestData();
        LOGGER.info("Got location from test data");
        driver.waitTillElementIsPresent(byLocationSearchInput);
        driver.findElement(byLocationSearchInput).sendKeys(location);
        driver.findElement(byLocationSearchInput).sendKeys(Keys.RETURN);
        LOGGER.info("Entered location in search field");
        String[] subLocations = location.split(",");
        subLocationCityNameText = String.format(subLocationCityNameText, subLocations[0]);
        LOGGER.info("Waiting for search options to appear");
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.isElementPresent(By.xpath(subLocationCityNameText))) {
            if (!driver.findElement(By.xpath(subLocationCityNameText)).isDisplayed()) {
                driver.findElement(byLocationSearchDropdown).click();
                LOGGER.info("Clicking on search dropdown, as dropdown is not already open");
            }
            LOGGER.info("Selecting location from options");
            driver.findElement(By.xpath(subLocationCityNameText)).click();
        }
        return this;
    }

    private String getLocationFromTestData() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("src/test/resources/testData.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        jsonObject = (JSONObject) jsonObject.get("prod");
        return (String) jsonObject.get("ZOMATO_RESTAURANT_LOCATION");
    }

    @Override
    public boolean verifyLocationDisplayed() {
        visually.checkWindow(SCREEN_NAME, "Dineout Screen");
        String location = getLocationFromTestData();
        LOGGER.info("Verifying location selected and location displayed after selection are same or not");
        driver.getInnerDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        return driver.findElement(byLocationSearchInput).getAttribute("placeholder").contains(location);
    }

    @Override
    public DineOutScreen selectSpecificRestaurant() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LOGGER.info("Saving restaurant name in context");
        context.addTestState(SAMPLE_TEST_CONTEXT.RESTAURANT_NAME, driver.findElement(byRestaurantNameText).getText());
        LOGGER.info("Selecting a specific restaurant");
        driver.findElement(byRestaurantNameText).click();
        return this;
    }
}
