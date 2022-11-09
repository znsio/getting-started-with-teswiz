package com.znsio.sample.e2e.screen.web.indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.indigo.IndigoFlightDetailsScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndigoFlightDetailsScreenWeb extends IndigoFlightDetailsScreen {

    public static final By byGetFlightDetailsXpath = By.xpath("//span[@class='flight-sources']");
    public static final By byClickOnTimeFilterXpath = By.xpath("//div[contains(text(),'Time')]");
    public static final By byClickingOn18Hrs = By.xpath("//div[contains(text(),'18-00')]");
    public static final By getFlightCount = By.xpath("//div[@class='flight-list']//div[@class='d-flex col-12 columns-wrapper']");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = IndigoFlightDetailsScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;

    public IndigoFlightDetailsScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public String isFlightDetailsVisible() {
        String flightDetails =  driver.findElement(byGetFlightDetailsXpath).getText();
        LOGGER.info("Get flight details " +flightDetails);
        visually.checkWindow(SCREEN_NAME,"Validating flight details");
        return flightDetails.trim();
    }

    @Override
    public IndigoFlightDetailsScreen applyTimeFilter() {
        driver.findElement(byClickOnTimeFilterXpath).click();
        driver.findElement(byClickingOn18Hrs).click();
        driver.findElement(byClickOnTimeFilterXpath).click();
        WebElement isTimeFilterSelected = (WebElement) driver.findElements(byClickingOn18Hrs);
        Boolean isFilterSelected = isTimeFilterSelected.isSelected();
        LOGGER.info("Time filter is selected " +isFilterSelected);
        return this;

    }

    @Override
    public int getFlightCounts() {
        int flightCounts = driver.findElements(getFlightCount).size();
        LOGGER.info("No. of flight" +flightCounts);
        visually.checkWindow(SCREEN_NAME,"Validating flight details after applying filters");
        return flightCounts;
    }
}
