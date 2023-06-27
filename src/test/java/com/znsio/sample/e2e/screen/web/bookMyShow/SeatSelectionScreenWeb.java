package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.SeatSelectionScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class SeatSelectionScreenWeb extends SeatSelectionScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By seatAvailability = By.xpath("//a[@class='_available'] | //a[@class='_available _best']");
    private static final By totalSeats = By.xpath("//a[@class='_available'] | //a[@class='_available _best'] | //a[@class='_blocked']");

    public SeatSelectionScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "Movie Landing Page Screen");
    }

    @Override
    public boolean checkSeatAvailability(int percentage) {
        List<WebElement> seatAvailable = driver.findElements(seatAvailability);
        List<WebElement> totalSeatAvailable = driver.findElements(totalSeats);
        double numberOfSeatsAvailable = seatAvailable.size();
        double totalNumberOfSeats = totalSeatAvailable.size();
        LOGGER.info(String.format("Number Of seats available: ", numberOfSeatsAvailable));
        LOGGER.info(String.format("Total number Of seats: ", totalNumberOfSeats));
        if(((numberOfSeatsAvailable*100)/totalNumberOfSeats) < percentage){
            return false;
        }else{
            return true;
        }
    }
}
