package com.znsio.sample.e2e.screen.web.bms;

import com.znsio.sample.e2e.screen.bms.BMSMoviesScreen;
import com.znsio.sample.e2e.screen.bms.BMSSelectSeatsScreen;
import com.znsio.sample.e2e.screen.web.jiomeet.InAMeetingScreenWeb;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BMSMoviesScreenWeb extends BMSMoviesScreen {

    private static final String SCREEN_NAME = InAMeetingScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;

    private final By byDateAfterTwoDaysXpath = By.xpath("(//a[@class='date-href'])[3]");
    private final By byBookTicketsBtnXpath = By.xpath("(//span[text()='Book tickets'])[1]");
    private final By byScreen2DTypeBtnXpath = By.xpath("(//span[text()='2D'])[1]");
    private final By byAcceptBtnXpath = By.xpath("(//div[text()='Accept'])[1]");
    private final By bySelectSeatsXpath = By.id("proceed-Qty");


    public BMSMoviesScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }


    @Override
    public BMSMoviesScreen selectMovieAtPosition(String moviePosition) {
        LOGGER.info("Trying to select movie number " + moviePosition + " from Now Showing movie page");

        if(driver.findElement(By.xpath("(//div[@class='sc-1ljcxl3-0 iUuHNJ']/a)["+moviePosition+"]")).isDisplayed())
        {
            LOGGER.info("Movie number " + moviePosition + " is present.");
            driver.findElement(By.xpath("(//div[@class='sc-1ljcxl3-0 iUuHNJ']/a)["+moviePosition+"]")).click();

            driver.waitTillElementIsPresent(byBookTicketsBtnXpath).click();
            if(driver.findElement(byScreen2DTypeBtnXpath).isDisplayed())
            {
                LOGGER.info("Screen types present");
                driver.findElement(byScreen2DTypeBtnXpath).click();
                LOGGER.info("Clicked on 2D screen type");
            }
        }
        else
        {
            LOGGER.error("Moviee number " + moviePosition + " is not present");
        }

        return this;
    }

    @Override
    public BMSMoviesScreen selectDateAfterTwoDays() {

        LOGGER.info("Selecting Day after tomorrow's date");
        driver.waitTillElementIsPresent(byDateAfterTwoDaysXpath);
        LOGGER.info("Selected Day after tomorrow's date");
        return this;
    }

    @Override
    public BMSMoviesScreen selectSecondLastTimeSlotForMovie(String cinemaPositionValue) {

        if(driver.findElement(By.xpath("(//div[@class='listing-info'])["+cinemaPositionValue+"]/following-sibling::div/div[@class='showtime-pill-wrapper']/div[last()]/preceding-sibling::div[1]")).isDisplayed())
        {
            LOGGER.info("Second last time slot available for cinema hall : " + cinemaPositionValue);
            WebElement secondLastTimeSlotOfCinemaHall = driver.findElement(By.xpath("//div[@class='listing-info'])["+cinemaPositionValue+"]/following-sibling::div/div[@class='showtime-pill-wrapper']/div[last()]/preceding-sibling::div[1]"));
            secondLastTimeSlotOfCinemaHall.click();
            LOGGER.info("Second last time slot selected");
            driver.waitTillElementIsPresent(byAcceptBtnXpath).click();
            LOGGER.info("Terms and Conditions ACCEPT btn clicked");
        }
        else
        {
            LOGGER.error("Second last time slot is not present for cinema hall : " + cinemaPositionValue);
        }

        return this;
    }

    @Override
    public BMSSelectSeatsScreen selectNoOfSeatsToBook(String noOfSeats) {

        LOGGER.info("Select no of seats as : " + noOfSeats);
        driver.waitTillElementIsPresent(By.xpath("//li[contains(text(),'"+noOfSeats+"')]")).click();
        driver.waitTillElementIsPresent(bySelectSeatsXpath).click();
        LOGGER.info("Seats selected");

        return BMSSelectSeatsScreen.get();
    }
}
