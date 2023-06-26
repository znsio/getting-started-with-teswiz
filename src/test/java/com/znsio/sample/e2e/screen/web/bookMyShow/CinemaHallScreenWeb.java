package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.CinemaHallScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class CinemaHallScreenWeb extends CinemaHallScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By selectThirdHall = By.xpath("(//a[contains(@class, '__venue-name')])[2]");
    private static final By selectSlot = By.xpath("(//div[contains(@class, 'body   __cn-list')])[1]");
    private static final By accept = By.xpath("//div[@id='btnPopupAccept']");
    private static final By selectNumberOfSeats = By.xpath("//li[@id='pop_2']");
    private static final By selectSeatButton = By.xpath("//div[@id='proceed-Qty']");

    public CinemaHallScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Cinema Hall Screen");
    }

    @Override
    public CinemaHallScreen selectTheThirdHall() {
        WebElement thirdHall = driver.findElement(selectThirdHall);
        String thirdHallTitle = thirdHall.getText();
        LOGGER.info(String.format("Third Hall Name: ", thirdHallTitle));
        context.addTestState(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.THIRD_HALL_TITLE, thirdHallTitle);
        thirdHall.click();
        return CinemaHallScreen.get();
    }

    @Override
    public CinemaHallScreen selectSecondSlot(String cinemaHallTitle) {
        driver.waitForClickabilityOf(selectSlot).click();
        return CinemaHallScreen.get();
    }

    @Override
    public CinemaHallScreen selectNumberOfPeople(int numberOfSeats) {
        driver.waitForClickabilityOf(accept).click();
        driver.waitForClickabilityOf(selectNumberOfSeats).click();
        WebElement numberOfSeatsSelected = driver.findElement(selectSeatButton);
        String seatSelected = numberOfSeatsSelected.getText();
        LOGGER.info(String.format("Number Of seats selected: ", seatSelected));
        if(seatSelected.equals(String.valueOf(numberOfSeats))){
            numberOfSeatsSelected.click();
        }
        return CinemaHallScreen.get();
    }
}
