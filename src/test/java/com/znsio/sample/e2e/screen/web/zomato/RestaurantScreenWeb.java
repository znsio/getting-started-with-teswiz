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
    private String bySelectDateDropdownOption = "//*[contains(text(),'%s')]";
    private final By bySelectGuestsDropdown = By.xpath("(//span[starts-with(@class,'sc-qnejpk-6')])[2]");
    private final By bySelectGuestsDropdownOption = By.xpath("(//span[starts-with(@class,'sc-qnejpk-0')])[2]/section/div/span/span[contains(text(),'4')]");
    private String bySelectTimeSlot = "//li[text()='%s']";
    private By byAllTimeSlotsXPath = By.xpath("//section/ul/li[starts-with(@class,'sc')]");
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

    public boolean isCorrectRestaurantSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.isElementPresent(By.xpath(String.format(restaurantNameHeading, context.getTestState(SAMPLE_TEST_CONTEXT.RESTAURANT_NAME))));
    }

    public RestaurantScreen clickOnBookATable() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened, after Restaurant selection");
        driver.waitTillElementIsPresent(byBookATable);
        driver.findElement(byBookATable).click();
        return this;
    }

    public boolean isBookATableOptionSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.isElementPresent(byBookATableVerificationText);
    }

    public RestaurantScreen selectDate() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened, Book a Table selected");
        driver.waitTillElementIsPresent(bySelectDateDropdown);
        driver.findElement(bySelectDateDropdown).click();
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String futureDate = calculateDateInFuture(2);
        if (driver.isElementPresent(By.xpath(String.format(bySelectDateDropdownOption, futureDate))))
            driver.findElement(By.xpath(String.format(bySelectDateDropdownOption, futureDate))).click();
        return this;
    }

    public boolean isCorrectDateSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver.findElement(bySelectDateDropdown).getText().contains(calculateDateInFuture(2, "yyyy-MM-dd"));
    }

    public RestaurantScreen selectNumberOfGuests() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened, correct Date Selected");
        driver.waitTillElementIsPresent(bySelectGuestsDropdown);
        driver.findElement(bySelectGuestsDropdown).click();
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (driver.isElementPresent(bySelectGuestsDropdownOption))
            driver.findElement(bySelectGuestsDropdownOption).click();
        return this;
    }

    public boolean areCorrectNumberOfGuestsSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver.findElement(bySelectGuestsDropdown).getText().contains("4");
    }

    public RestaurantScreen selectTimeSlot() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened, correct number of Guests selected");
        driver.getInnerDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        for (int hour = 7; hour <= 11; hour++) {
            if (driver.isElementPresent(By.xpath(String.format(bySelectTimeSlot, "0" + hour + ":00 PM")))) {
                driver.findElement(By.xpath(String.format(bySelectTimeSlot, "0" + hour + ":00 PM"))).click();
                context.addTestState(SAMPLE_TEST_CONTEXT.TIMESLOT_BOOKED, "0" + hour + ":00 PM");
                break;
            } else if (driver.isElementPresent(By.xpath(String.format(bySelectTimeSlot, "0" + hour + ":30 PM")))) {
                driver.findElement(By.xpath(String.format(bySelectTimeSlot, "0" + hour + ":30 PM"))).click();
                context.addTestState(SAMPLE_TEST_CONTEXT.TIMESLOT_BOOKED, "0" + hour + ":00 PM");
                break;
            }
        }

        return this;
    }

    public boolean isCorrectTimeSlotSelected() {
        boolean flag = false;
        driver.waitTillPresenceOfAllElements(byAllTimeSlotsXPath);
        List<WebElement> timeSlotList = driver.findElements(byAllTimeSlotsXPath);
        if (timeSlotList.size() > 1) {
            for (int i = 1; i < timeSlotList.size(); i++) {
                if (!timeSlotList.get(0).getAttribute("class").equals(timeSlotList.get(i).getAttribute("class"))) {
                    flag = true;
                }
            }
        } else if (timeSlotList.size() == 1) {
            if (timeSlotList.get(0).getText().equals(context.getTestState(SAMPLE_TEST_CONTEXT.TIMESLOT_BOOKED))) {
                flag = true;
            }
        }
        return flag;
    }

    public RestaurantScreen fillGuestBasicDetails() {
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened, dinner time slot selected");
        driver.waitTillElementIsPresent(byFirstNameInputField);
        driver.findElement(byFirstNameInputField).sendKeys("Max");
        driver.waitTillElementIsPresent(byLastNameInputField);
        driver.findElement(byLastNameInputField).sendKeys("Payne");
        driver.waitTillElementIsPresent(byEmailInputField);
        driver.findElement(byEmailInputField).sendKeys("maxpayne@gmail.com");
        driver.waitTillElementIsPresent(byISDCodeInputField);
        if (driver.findElement(byISDCodeInputField).getAttribute("value").trim().isEmpty()) {
            driver.findElement(byISDCodeInputField).sendKeys("91");
        }
        driver.waitTillElementIsPresent(byPhoneNumberInputField);
        driver.findElement(byPhoneNumberInputField).sendKeys("9980768956");
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened, basic guest details filled");
        return this;
    }

    public boolean areGuestDetailsCorrect() {
        boolean flag= driver.findElement(byFirstNameInputField).getAttribute("value").equals("Max") &&
                driver.findElement(byLastNameInputField).getAttribute("value").equals("Payne") &&
                driver.findElement(byEmailInputField).getAttribute("value").equals("maxpayne@gmail.com") &&
                driver.findElement(byISDCodeInputField).getAttribute("value").equals("91") &&
                driver.findElement(byPhoneNumberInputField).getAttribute("value").equals("9980768956");
        driver.waitTillElementIsPresent(byBookButton);
        driver.findElement(byBookButton).click();
        return flag;
    }

    public boolean isLoginPopUpMessageVisible() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.getInnerDriver().switchTo().frame("auth-login-ui");
        visually.checkWindow(SCREEN_NAME, "Restaurant Screen opened,, switched to frame, checking pop up");
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
