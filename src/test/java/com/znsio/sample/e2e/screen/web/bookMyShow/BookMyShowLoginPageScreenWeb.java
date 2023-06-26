package com.znsio.sample.e2e.screen.web.bookMyShow;

import com.context.TestExecutionContext;
import com.znsio.sample.e2e.entities.bookMyShow.BOOKMYSHOW_SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.exceptions.bookMyShow.bookMyShowException;
import com.znsio.sample.e2e.exceptions.bookMyShow.continueButtonNotEnabledException;
import com.znsio.sample.e2e.screen.bookMyShow.BookMyShowLoginPageScreen;
import com.znsio.sample.e2e.screen.bookMyShow.MoviesListPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.teswiz.tools.Wait.waitFor;

import java.util.logging.Logger;

public class BookMyShowLoginPageScreenWeb extends BookMyShowLoginPageScreen {
    private final Driver driver;
    private final Visual visually;
    private final TestExecutionContext context;
    private static final String SCREEN_NAME = BookMyShowLoginPageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By cityName = By.xpath("//span[contains(text(),'Delhi-NCR')]");
    private static final By location = By.xpath("//span[@class='sc-fQejPQ knlJQD ellipsis']");
    private static final By moviePage = By.xpath("//a[@class='sc-gpHHfC bZPWwF']");
    private static final By popularCities = By.xpath("//span[@class='sc-hARARD hsHJDY']");
    private static final By bug = By.xpath("//*[contains(text(),'Refresh')]");
    private static final By SignInButton = By.xpath("//div[@class='sc-iGPElx jsSlsO']");
    private static final By continueWithMobileNumber = By.xpath("//*[contains(text(),'Continue with mobile number')]");
    private static final By continueWithMobileNumberSend = By.id("mobileNo");
    private static final By clickContinueButton = By.xpath("//button[contains(text(),'Continue')]");
    private static final By guestUser = By.xpath("//span[@class='sc-etwtAo kqixli']");

    public BookMyShowLoginPageScreenWeb(Driver driver, Visual visually) {
        long threadId = Thread.currentThread().getId();
        this.context = Runner.getTestExecutionContext(threadId);
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(SCREEN_NAME, "BookMyShow Home Page Screen");
    }

    @Override
    public Object isValidLogin() {
        boolean checkUsername = false;
        visually.takeScreenshot(SCREEN_NAME, "Valid Login");
        driver.waitForClickabilityOf(SignInButton).click();
        visually.takeScreenshot(SCREEN_NAME, "Sign button clicked");
        driver.waitForClickabilityOf(continueWithMobileNumber);
        visually.takeScreenshot(SCREEN_NAME, "clicked continue With MobileNumber");
        driver.waitForClickabilityOf(continueWithMobileNumberSend).sendKeys(BOOKMYSHOW_SAMPLE_TEST_CONTEXT.MOBILE_NUMBER);
        visually.takeScreenshot(SCREEN_NAME, "Mobile Number Entered");
        driver.waitForClickabilityOf(clickContinueButton).click();
        waitFor(10);
        visually.takeScreenshot(SCREEN_NAME, "Continued after entering mobile number");
        WebElement continueButton = driver.findElement(clickContinueButton);
        String continueButtonOpacity = continueButton.getCssValue("opacity");
        LOGGER.info(String.format("opacity: ", continueButtonOpacity));
        try{
                if(continueButtonOpacity.equals("0.1")) {
                    throw new continueButtonNotEnabledException("Continue Button is Not Enabled");
                }else{
                    String username = driver.waitForClickabilityOf(guestUser).getText();
                    LOGGER.info(String.format("User Name: ", username));
                    checkUsername =  checkGuestUser(username);
                }
        }catch (continueButtonNotEnabledException e){
            System.out.println("Catch error: "+e.getMessage());
        }
        return checkUsername;
    }

    private boolean checkGuestUser(String username) {
        return username.contains("Guest");
    }

    @Override
    public boolean isBookMyShowHomepageLaunched() {
        return driver.isElementPresent(popularCities);
    }

    public boolean verifyLocation(String location, String expectedLocation){
        return location.equals(expectedLocation);
    }

    @Override
    public boolean isLocationDelhi(String expectedLocation) throws bookMyShowException {
        driver.waitForClickabilityOf(cityName).click();
        String currentLocation = driver.findElement(location).getText();
        LOGGER.info(String.format("Current Location: ", currentLocation));
        visually.takeScreenshot(SCREEN_NAME, "Selected Location");
        try{
            if(driver.isElementPresent(bug)){
                throw new bookMyShowException("Bug on Book My Show");
            }
        }catch (bookMyShowException e){
            System.out.println("Catch error: "+e.getMessage());
        }
        return verifyLocation(currentLocation, expectedLocation);
    }

    @Override
    public MoviesListPageScreen moviesPage() {
        driver.waitForClickabilityOf(moviePage).click();
        visually.takeScreenshot(SCREEN_NAME, "Movies Page");
        return MoviesListPageScreen.get();
    }

}
