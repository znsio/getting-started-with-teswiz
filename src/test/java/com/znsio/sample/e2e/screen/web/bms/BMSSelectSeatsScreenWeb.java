package com.znsio.sample.e2e.screen.web.bms;

import com.znsio.sample.e2e.screen.bms.BMSSelectSeatsScreen;
import com.znsio.sample.e2e.screen.web.jiomeet.InAMeetingScreenWeb;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BMSSelectSeatsScreenWeb extends BMSSelectSeatsScreen {

    private static final String SCREEN_NAME = InAMeetingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By byNoOfAvailableSeats = By.xpath("//a[@class='_available']");
    private final By byNoOfBestAvailableSeats = By.xpath("//a[@class='_available _best']");
    private final By byTotalBlockedSeats = By.xpath("//a[@class='_blocked']");
    private final By byTotalSeats = By.xpath("//div[@class='seatI']/a");

    public BMSSelectSeatsScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public int getTotalAvailableSeats() {

        LOGGER.info("Calculating Count of total available seats");
        visually.takeScreenshot(SCREEN_NAME,"Seat availablity and selection page.");
        int totalAvailableSeats;
        List<WebElement> numberOfAvailableSeats = driver.findElements(byNoOfAvailableSeats);
        totalAvailableSeats = numberOfAvailableSeats.size();
        if(driver.findElement(byNoOfBestAvailableSeats).isDisplayed())
        {
            LOGGER.info("Some seats are markes as BEST AVAILABLE SEATS");
            List<WebElement> numberOfAvailableBestSeats = driver.findElements(byNoOfBestAvailableSeats);
            totalAvailableSeats = totalAvailableSeats + numberOfAvailableBestSeats.size();
        }

        LOGGER.info("Total number of seats available are : " + totalAvailableSeats);
        return totalAvailableSeats;
    }

    @Override
    public int getTotalSeats() {

        LOGGER.info("Finding out total number of seats in the given cinema hall");
        int totalSeats;
        List<WebElement> totalSeatsInTheHall = driver.findElements(byTotalSeats);
        totalSeats = totalSeatsInTheHall.size();
        return totalSeats;
    }
}
