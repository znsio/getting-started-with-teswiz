package com.znsio.sample.e2e.screen.web.zomato;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.zomato.ZomatoScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ZomatoScreenWeb extends ZomatoScreen {
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = ZomatoScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(ZomatoScreenWeb.class.getName());
    private final TestExecutionContext context;
    private final String homePagePartialUrl = "www.zomato.com";
    private final String dineoutPagePartialUrl = "dine-out";
    private final By byDiningCardText = By.xpath("//p[text()='Dining']");
    private final By byDiningOutTabText = By.xpath("//div[text()='Dining Out']");
    private final By byLocationSearchInput = By.xpath("(//ul[starts-with(@id,'navigation')]/li/div//div/div/input)[1]");
    private final By byLocationSearchDropdown = By.xpath("(//ul[starts-with(@id,'navigation')]/li/div/div/div/i)[2]");
    private String subLocationCityNameText = "//ul[starts-with(@id,'navigation')]/li/div/div/div/div/div/p[starts-with(@class,'sc') and text()='%s']";
    private final By byRestaurantNameText = By.xpath("//div[@id='root']/div/div[9]/div/div[3]/div/div/a/div/h4");
    private String restaurantNameHeading = "//h1[text()='%s']";
    private final By byBookATable = By.linkText("Book a Table");
    private final By byBookATableVerificationText = By.xpath("//h4[text()='Please select your booking details']");
    private final By bySelectDateDropdown = By.xpath("(//span[starts-with(@class,'sc-qnejpk-6')])[1]");
    private String bySelectDateDropdownOption = "//*[contains(text(),'%s')]";
    private final By bySelectGuestsDropdown = By.xpath("(//span[starts-with(@class,'sc-qnejpk-6')])[2]");
    private final By bySelectGuestsDropdownOption = By.xpath("(//span[starts-with(@class,'sc-qnejpk-0')])[2]/section/div/span/span[contains(text(),'4')]");
    private final By bySelectTimeSlot = By.xpath("//li[text()='08:00 PM']");
    private final By byFirstNameInputField = By.name("firstName");
    private final By byLastNameInputField = By.name("lastName");
    private final By byEmailInputField = By.name("email");
    private final By byISDCodeInputField = By.name("isdCode");
    private final By byPhoneNumberInputField = By.name("phone");
    private final By byBookButton = By.xpath("//button/span/span[text()='Book']");

    public ZomatoScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        context = SessionContext.getTestExecutionContext(Thread.currentThread().getId());
    }

    @Override
    public boolean isHomePageLaunchedSuccessfully() {
        driver.getInnerDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        return driver.getInnerDriver().getCurrentUrl().contains(homePagePartialUrl);
    }

    @Override
    public ZomatoScreen clickOnDiningOption() {
        driver.waitTillElementIsPresent(byDiningCardText);
        driver.findElement(byDiningCardText).click();
        return this;
    }

    @Override
    public boolean verifyRedirectionToDineoutPage() {
        driver.getInnerDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        return driver.getInnerDriver().getCurrentUrl().contains(dineoutPagePartialUrl) && driver.isElementPresent(byDiningOutTabText);
    }

    @Override
    public ZomatoScreen selectLocationForRestaurants(String location) {
        driver.waitTillElementIsPresent(byLocationSearchInput);
        driver.findElement(byLocationSearchInput).sendKeys(location);
        driver.findElement(byLocationSearchInput).sendKeys(Keys.RETURN);

        String[] subLocations = location.split(",");
        subLocationCityNameText = String.format(subLocationCityNameText, subLocations[0]);

        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.isElementPresent(By.xpath(subLocationCityNameText))) {
            if (!driver.findElement(By.xpath(subLocationCityNameText)).isDisplayed()) {
                driver.findElement(byLocationSearchDropdown).click();
            }
            driver.findElement(By.xpath(subLocationCityNameText)).click();
        }
        return this;
    }

    @Override
    public boolean isCorrectLocationSelected(String location) {
//        driver.getInnerDriver().manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        return driver.findElement(byLocationSearchInput).getAttribute("value").contains(location);
    }

    @Override
    public ZomatoScreen selectSpecificRestaurant() {
        context.addTestState(SAMPLE_TEST_CONTEXT.RESTAURANT_NAME, driver.findElement(byRestaurantNameText).getText());
        driver.findElement(byRestaurantNameText).click();
        return this;
    }

    public boolean isCorrectRestaurantSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.isElementPresent(By.xpath(String.format(restaurantNameHeading, context.getTestState(SAMPLE_TEST_CONTEXT.RESTAURANT_NAME))));
    }

    public ZomatoScreen clickOnBookATable() {
        driver.waitTillElementIsPresent(byBookATable);
        driver.findElement(byBookATable).click();
        return this;
    }

    public boolean isBookATableOptionSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.isElementPresent(byBookATableVerificationText);
    }

    public ZomatoScreen selectDate() {
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
        return driver.findElement(bySelectDateDropdown).getText().contains(calculateDateInFutureFormat(2));
    }

    public ZomatoScreen selectNumberOfGuests() {
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

    public ZomatoScreen selectTimeSlot() {
        driver.waitTillElementIsPresent(bySelectTimeSlot);
        driver.findElement(bySelectTimeSlot).click();
        return this;
    }

    public boolean isCorrectTimeSlotSelected() {
        driver.getInnerDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver.findElement(bySelectGuestsDropdown).getText().contains("4");
    }

    public ZomatoScreen fillGuestBasicDetails() {
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
        driver.waitTillElementIsPresent(byBookButton);
        driver.findElement(byBookButton).click();
        return this;
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

    private String calculateDateInFutureFormat(int numberOfDaysInFuture) {
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(numberOfDaysInFuture);
        return DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(localDateTime);
    }

}
