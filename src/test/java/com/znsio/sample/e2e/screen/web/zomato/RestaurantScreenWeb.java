package com.znsio.sample.e2e.screen.web.zomato;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.RestaurantScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RestaurantScreenWeb extends RestaurantScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = RestaurantScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(RestaurantScreenWeb.class.getName());
    private final TestExecutionContext context;
    private String restaurantNameHeading = "//h1[text()='%s']";
    private final By byBookATable = By.linkText("Book a Table");
    private final By byBookATableVerificationText = By.xpath("//h4[text()='Please select your booking details']");
    private final By bySelectDateDropdown = By.xpath("(//span[starts-with(@class,'sc-qnejpk-6')])[1]");
    private String bySelectDateDropdownOption = "//span[contains(text(),'%s')]";
    private final By bySelectGuestsDropdown = By.xpath("(//span[starts-with(@class,'sc-qnejpk-6')])[2]");
    private final By bySelectGuestsDropdownOption = By.xpath("(//span[starts-with(@class,'sc-qnejpk-0')])[2]/section/div/span/span[contains(text(),'4')]");
    private String bySelectTimeSlot = "//li[text()='%s']";
    private final By byAllTimeSlotsXPath = By.xpath("//section/ul/li[starts-with(@class,'sc')]");
    private final By byFirstNameInputField = By.xpath("//input[@name='firstName']");
    private final By byLastNameInputField = By.xpath("//input[@name='lastName']");
    private final By byEmailInputField = By.xpath("//input[@name='email']");
    private final By byISDCodeInputField = By.xpath("//input[@name='isdCode']");
    private final By byPhoneNumberInputField = By.xpath("//input[@name='phone']");
    private final By byBookButton = By.xpath("//button/span/span[text()='Book']");
    private final By bySendOneTimePassword = By.xpath("//span[text()='Send One Time Password']");
    private final By byContinueWithGoogle = By.xpath("//span[text()='Continue with Google']");

    public RestaurantScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
    }

    public boolean verifyRestaurantDisplayed() {
        driver.getInnerDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LOGGER.info("Verifying restaurant name using context");
        return driver.isElementPresent(By.xpath(String.format(restaurantNameHeading, context.getTestState(SAMPLE_TEST_CONTEXT.RESTAURANT_NAME))));
    }

    public RestaurantScreen clickOnBookATable() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        LOGGER.info("Clicking on Book a Table");
        driver.waitTillElementIsPresent(byBookATable);
        driver.findElement(byBookATable).click();
        return this;
    }

    public boolean verifyBookATableOptionSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LOGGER.info("Verifying Book a Table element clicked by text verification");
        return driver.isElementPresent(byBookATableVerificationText);
    }

    public RestaurantScreen selectDate() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        LOGGER.info("Clicking on Date dropdown");
        driver.waitTillElementIsPresent(bySelectDateDropdown);
        driver.findElement(bySelectDateDropdown).click();
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String futureDate = calculateDateInFuture(2);
        LOGGER.info("Selecting Today's date + 2 days (ex format, Fri, 22 Jun), if available");
        if (driver.isElementPresent(By.xpath(String.format(bySelectDateDropdownOption, futureDate))))
            driver.findElement(By.xpath(String.format(bySelectDateDropdownOption, futureDate))).click();
        return this;
    }

    public boolean verifyDateSelectedMatchingWithDateDisplayed() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LOGGER.info("Verifying date selected or not in format yyyy-MM-dd (ex format, 2023-06-22)");
        return driver.findElement(bySelectDateDropdown).getText().contains(calculateDateInFuture(2, "yyyy-MM-dd"));
    }

    public RestaurantScreen selectNumberOfGuests() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        LOGGER.info("Clicking on Guests dropdown");
        driver.waitTillElementIsPresent(bySelectGuestsDropdown);
        driver.findElement(bySelectGuestsDropdown).click();
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LOGGER.info("Selecting 4 guests option, if available");
        if (driver.isElementPresent(bySelectGuestsDropdownOption))
            driver.findElement(bySelectGuestsDropdownOption).click();
        return this;
    }

    public boolean verifyNumberOfGuestsSelectedMatchingWithNumberOfGuestsDisplayed() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LOGGER.info("Verifying number of guests selected or not");
        return driver.findElement(bySelectGuestsDropdown).getText().contains("4");
    }

    public RestaurantScreen selectTimeSlot() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        LOGGER.info("Created an array of pre-defined Dinner time slot");
        String[] possibleTimeSlots = {"07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM", "09:00 PM", "09:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM"};
        driver.getInnerDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        LOGGER.info("Trying to select a timeslot available from possible timeslots");
        for (String timeSlot : possibleTimeSlots) {
            if (driver.isElementPresent(By.xpath(String.format(bySelectTimeSlot, timeSlot)))) {
                driver.findElement(By.xpath(String.format(bySelectTimeSlot, timeSlot))).click();
                break;
            }
        }
        return this;
    }

    public boolean verifyTimeSlotSelected() {
        boolean flag = false;
        driver.waitTillPresenceOfAllElements(byAllTimeSlotsXPath);
        LOGGER.info("Getting all available timeslots in list");
        List<WebElement> timeSlotList = driver.findElements(byAllTimeSlotsXPath);
        if (timeSlotList.size() < 1) {
            flag = false;
        } else {
            LOGGER.info("Comparing color of time slot elements, as selected time slot has white color (different from unselected timeslot)");
            for (WebElement timeSlot : timeSlotList) {
                String timeSlotColorAsHex = Color.fromString(timeSlot.getCssValue("color")).asHex();
                if (timeSlotColorAsHex.equals("#ffffff")) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public RestaurantScreen fillGuestBasicDetails() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        driver.waitTillElementIsPresent(byFirstNameInputField);
        LOGGER.info("Entering First Name");
        driver.findElement(byFirstNameInputField).sendKeys("Max");
        driver.waitTillElementIsPresent(byLastNameInputField);
        LOGGER.info("Entering Last Name");
        driver.findElement(byLastNameInputField).sendKeys("Payne");
        driver.waitTillElementIsPresent(byEmailInputField);
        LOGGER.info("Entering Email");
        driver.findElement(byEmailInputField).sendKeys("maxpayne@gmail.com");
        driver.waitTillElementIsPresent(byISDCodeInputField);
        LOGGER.info("Entering ISD, if not already present");
        if (driver.findElement(byISDCodeInputField).getAttribute("value").trim().isEmpty()) {
            driver.findElement(byISDCodeInputField).sendKeys("91");
        }
        driver.waitTillElementIsPresent(byPhoneNumberInputField);
        LOGGER.info("Entering Phone Number");
        driver.findElement(byPhoneNumberInputField).sendKeys("9980768956");
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        return this;
    }

    public boolean verifyGuestDetails() {
        LOGGER.info("Verifying Guest details entered earlier");
        boolean flag = driver.findElement(byFirstNameInputField).getAttribute("value").equals("Max") &&
                driver.findElement(byLastNameInputField).getAttribute("value").equals("Payne") &&
                driver.findElement(byEmailInputField).getAttribute("value").equals("maxpayne@gmail.com") &&
                driver.findElement(byISDCodeInputField).getAttribute("value").equals("91") &&
                driver.findElement(byPhoneNumberInputField).getAttribute("value").equals("9980768956");
        LOGGER.info("Booking Table");
        driver.waitTillElementIsPresent(byBookButton);
        driver.findElement(byBookButton).click();
        return flag;
    }

    public boolean verifyLoginPopUpMessageDisplayed() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Switching to frame and verifying login pop-up display");
        driver.getInnerDriver().switchTo().frame("auth-login-ui");
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen");
        return driver.isElementPresent(bySendOneTimePassword) && driver.isElementPresent(byContinueWithGoogle);
    }

    private String calculateDateInFuture(int numberOfDaysInFuture) {
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(numberOfDaysInFuture);
        String dayOfWeek = String.valueOf(localDateTime.getDayOfWeek()).substring(0, 3);
        dayOfWeek = dayOfWeek.charAt(0) + dayOfWeek.substring(1).toLowerCase();
        String month = String.valueOf(localDateTime.getMonth()).substring(0, 3);
        month = month.charAt(0) + month.substring(1).toLowerCase();
        String dayOfMonth = String.valueOf(localDateTime.getDayOfMonth());
        return dayOfWeek + ", " + dayOfMonth + " " + month;
    }

    private String calculateDateInFuture(int numberOfDaysInFuture, String format) {
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(numberOfDaysInFuture);
        return DateTimeFormatter.ofPattern(format, Locale.ENGLISH).format(localDateTime);
    }
}
