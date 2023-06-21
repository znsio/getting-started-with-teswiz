package com.znsio.sample.e2e.screen.web.zomato;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.sample.e2e.screen.zomato.RestaurantDetailScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RestaurantDetailScreenWeb extends RestaurantDetailScreen {
    private static final String SCREEN_NAME = RestaurantDetailScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final String byRestaurantNameXpath = "//h1[text()='%s']";
    private final By byPageHeaderCSS = By.cssSelector("header");
    private final By byTabListCSS = By.cssSelector("section[role='tablist']");
    private final By byPageFooterCSS = By.cssSelector("footer");
    private final By byBookingDetailCSS = By.cssSelector("form");
    private final By byBookATableXpath = By.xpath("//a[text()='Book a Table']");
    private final By byDateInputXpath = By.xpath("//span[text()='Select Date']");
    private final String bySelectDateXpath = "//div[contains(@id,'%s')]";
    private final By byGuestCountInput = By.xpath("//span[text()='Select Guests']");
    private final String bySelectGuestCountCSS = "div[aria-label='%s']";
    private final String bySelectedAheadDateXpath = "//span[text()='%s']";
    private final By byBookButtonXpath = By.xpath("//span[text()='Book']");
    private final String byGuestCountXpath = "//span[text()='%s']";
    private final By byLoginHeaderXpath = By.xpath("//h2[text()='Login']");
    private final String byLoginFrameId = "auth-login-ui";
    private final By bySelectTimeOptionXpath = By.xpath("//h4[text()='Select time']//parent::section");
    private final By byFirstNameInputCSS = By.cssSelector("input[name='firstName']");
    private final By byLastNameInputCSS = By.cssSelector("input[name='lastName']");
    private final By byUserEmailInputCSS = By.cssSelector("input[name='email']");
    private final By byUserPhoneNumberCSS = By.cssSelector("input[name='phone']");
    private final By byTimeXpath = By.xpath("//li[text()='08:00 PM']");

    public RestaurantDetailScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home page");
    }

    @Override
    public RestaurantDetailScreen clickBookATableTab() {
        driver.findElement(byBookATableXpath).click();
        return this;
    }

    @Override
    public RestaurantDetailScreen selectBookingDate(int aheadDays) {
        visually.check(SCREEN_NAME, "Booking form", Target.region(byBookingDetailCSS));
        driver.waitTillElementIsVisible(byDateInputXpath).click();
        driver.waitTillElementIsVisible(By.xpath(String.format(bySelectDateXpath, getAheadDate(aheadDays)))).click();
        return this;
    }
    private String getAheadDate(int aheadDays){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(LocalDate.now().plusDays(aheadDays));
    }

    @Override
    public RestaurantDetailScreen selectGuestCount(int guestCount) {
        driver.findElement(byGuestCountInput).click();
        driver.findElement(By.cssSelector(String.format(bySelectGuestCountCSS, guestCount))).click();
        return this;
    }
    
    @Override
    public RestaurantDetailScreen clickBookButton() {
        driver.findElement(byBookButtonXpath).click();
        return this;
    }

    @Override
    public boolean isLoginPopUpWindowVisible() {
        driver.waitTillElementIsPresent(By.id(byLoginFrameId));
        driver.switchToFrame(byLoginFrameId);
        driver.waitTillElementIsVisible(byLoginHeaderXpath, 20);
        visually.checkWindow(SCREEN_NAME, "Login Page");
        return driver.isElementPresent(byLoginHeaderXpath);
    }

    @Override
    public boolean isRestaurantNameVisible(String restaurantName) {
        visually.check(SCREEN_NAME, "Restaurant detail page", Target.window()
                .fully().strict(byTabListCSS, byPageHeaderCSS, byPageFooterCSS));
        driver.waitTillElementIsVisible(By.xpath(String.format(byRestaurantNameXpath, restaurantName)));
        return driver.isElementPresent(By.xpath(String.format(byRestaurantNameXpath, restaurantName)));
    }

    @Override
    public boolean isSelectedAheadDateCorrect(int aheadDays) {
        LOGGER.info(String.format(bySelectedAheadDateXpath, getAheadDate(aheadDays)));
        return driver.isElementPresent(By.xpath(String.format(bySelectedAheadDateXpath, getAheadDate(aheadDays))));
    }

    @Override
    public boolean isNumberOfGuestSelectedCorrect(int guestCount) {
        return driver.isElementPresent(By.xpath(String.format(byGuestCountXpath,guestCount)));
    }

    @Override
    public boolean isSelectTimeOptionEnabled() {
        visually.check(SCREEN_NAME, "Select time option", Target.region(bySelectTimeOptionXpath));
        return driver.isElementPresent(bySelectTimeOptionXpath);
    }

    @Override
    public RestaurantDetailScreen enterFirstName(String firstName) {
        driver.findElement(byFirstNameInputCSS).sendKeys(firstName);
        return this;
    }

    @Override
    public RestaurantDetailScreen enterLastName(String lastName) {
        driver.findElement(byLastNameInputCSS).sendKeys(lastName);
        return this;
    }

    @Override
    public RestaurantDetailScreen enterUserEmail(String userEmail) {
        driver.findElement(byUserEmailInputCSS).sendKeys(userEmail);
        return this;
    }

    @Override
    public RestaurantDetailScreen enterPhoneNumber(String phoneNo) {
        driver.findElement(byUserPhoneNumberCSS).sendKeys(phoneNo);
        return this;
    }

    @Override
    public RestaurantDetailScreen selectTimeSlot() {
        driver.findElement(byTimeXpath).click();
        return this;
    }
}
