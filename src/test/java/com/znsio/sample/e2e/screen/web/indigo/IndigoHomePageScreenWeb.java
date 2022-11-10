package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.IndigoHomePageScreen;
import com.znsio.sample.e2e.screen.web.amazon.AmazonHomePageScreenWeb;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class IndigoHomePageScreenWeb extends IndigoHomePageScreen {

    public static final By byIndigoPannelXpath = By.xpath("//div[@class='ig-hp-search-form english']");
    public static final By byClickingOnFromXpath = By.xpath("//input[@placeholder='From']");
    public static final By byClickingOnFromDropdown = By.xpath("(//div[@class='autocomplete-result station-result clearfix airport-item pop-dest-stn selected'])[1]");
    public static final By byGettingFromTextXpath = By.xpath("//div[@id='bookFlightTab']//input[@placeholder='From']");
    public static final By byClickingOnToXpath = By.xpath("//input[@placeholder='To']");
    public static final By byClickingOnToDropdown = By.xpath("(//div[@class='autocomplete-result station-result clearfix airport-item pop-dest-stn selected'])[2]");
    public static final By byGettingToTextXpath = By.xpath("//div[@id='bookFlightTab']//input[@placeholder='To']");
    public static final By byClickingOnSerchFlightClassName = By.className("hp-src-btn");
    public static final By byFlightDetailPageId = By.id("flightSelectMount");
    public static final By byClickingOnDate = By.xpath("//input[@data-parsley-indigodate-message='Please select a valid date']");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;
    Map<String,String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));

    public IndigoHomePageScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);

    }

    @Override
    public boolean validateHomePage() {
        driver.waitTillElementIsPresent(byIndigoPannelXpath,10);
        boolean isIndigoPageVisible = driver.findElement(byIndigoPannelXpath).isDisplayed();
        visually.checkWindow(SCREEN_NAME,"Validating goindigo Homepage");
        if (isIndigoPageVisible) {
            LOGGER.info("Is Indigo Homepage Displayed:" +isIndigoPageVisible);
            return true;
        } else {
            LOGGER.error("Is Indigo Homepage Displayed:" +isIndigoPageVisible);
            return false;
        }
    }

    @Override
    public IndigoHomePageScreen addCurrentLocationDetails() {
        driver.waitTillElementIsPresent(byClickingOnFromXpath,20);
    //     driver.findElement(byClickingOnFromXpath).click();
     //   driver.findElement(byClickingOnFromXpath).click();
   //     driver.findElement(byClickingOnFromXpath).sendKeys(testData.get("to"));
      //  driver.findElement(byClickingOnFromDropdown).click();
     //    String fromFlightData = driver.findElement(byGettingFromTextXpath).getText();
 //       LOGGER.info("Validate From details:" +fromFlightData);
        return this;
    }

    @Override
    public IndigoHomePageScreen addDestinationDetails() {
        driver.waitTillElementIsPresent(byClickingOnFromXpath,20);
        driver.findElement(byClickingOnToXpath).click();
        driver.findElement(byClickingOnToXpath).sendKeys(testData.get("from"));
    //    driver.waitTillElementIsPresent(byClickingOnToDropdown,20);
     //   driver.findElement(byClickingOnToDropdown).click();
    //    String toFlightData = driver.findElement(byGettingToTextXpath).getText();
     //   LOGGER.info("Validate From details:" +toFlightData);
        return this;
    }

    @Override
    public IndigoHomePageScreen addDateDetails() {

        driver.findElement(byClickingOnDate).click();
        return this;
    }

    @Override
    public boolean validateFlightDetails() {
  //      visually.checkWindow(SCREEN_NAME,"Validating details entered on goindigo site");
        driver.findElement(byClickingOnSerchFlightClassName).click();
        driver.findElement(byClickingOnSerchFlightClassName).click();
        driver.waitTillElementIsPresent(byFlightDetailPageId,25);
        boolean isFlightDetailsVisible = driver.findElement(byFlightDetailPageId).isDisplayed();
        if (isFlightDetailsVisible) {
            LOGGER.info("Is Indigo Flight Detail Page Displayed:" +isFlightDetailsVisible);
            return true;
        } else {
            LOGGER.error("Is Indigo Flight Detail Page Displayed:" +isFlightDetailsVisible);
            return false;

        }
    }
}
