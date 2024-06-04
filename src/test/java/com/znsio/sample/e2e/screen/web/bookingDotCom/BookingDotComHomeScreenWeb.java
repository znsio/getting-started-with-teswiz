package com.znsio.sample.e2e.screen.web.bookingDotCom;

import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComFlightsScreen;
import com.znsio.sample.e2e.screen.bookingDotCom.BookingDotComHomeScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import com.znsio.teswiz.tools.Wait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookingDotComHomeScreenWeb
        extends BookingDotComHomeScreen {
    private static final String SCREEN_NAME = BookingDotComHomeScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;

    public BookingDotComHomeScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public BookingDotComFlightsScreen goToFlights() {
        driver.waitTillElementIsPresent(By.id("flights")).click();
        driver.waitTillElementIsPresent(By.xpath("//h1[text()='Compare and book flights with ease']"));
        visually.checkWindow(SCREEN_NAME, "Flights");
        return BookingDotComFlightsScreen.get();
    }

    @Override
    public BookingDotComHomeScreen dismissPopup() {
        Wait.waitFor(15);

        if (driver.findElements(By.xpath("//button[@aria-label='Dismiss sign in information.']")).isEmpty()) {
            LOGGER.info("Popup not seen.");
        } else {
            LOGGER.info("Popup seen. Dismiss it");
            driver.findElement(By.xpath("//button[@aria-label='Dismiss sign in information.']")).click();
        }
        visually.checkWindow(SCREEN_NAME, "Home screen");
        return this;
    }
}
