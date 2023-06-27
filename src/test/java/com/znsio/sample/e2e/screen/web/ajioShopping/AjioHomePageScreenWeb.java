package com.znsio.sample.e2e.screen.web.ajioShopping;

import com.znsio.sample.e2e.screen.ajioShopping.AjioHomePageScreen;
import com.znsio.sample.e2e.screen.ajioShopping.ProductListPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.znsio.teswiz.tools.Wait.waitFor;
import static org.mockito.ArgumentMatchers.contains;

public class AjioHomePageScreenWeb extends AjioHomePageScreen {
    private static final String SCREEN_NAME = AjioHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private final static By closePopUp = By.xpath("//div[@class='ic-close-quickview']");
    private final static By signInButton = By.xpath("//*[@class='  login-form login-modal']");
    private final static By enterUsername = By.xpath("//input[@class='username']");
    private final static By continueButton = By.xpath("//input[@class='login-btn']");
    private final static By loginWithPassword = By.xpath("//input[@class='login-form-inputs only-border-btn']");
    private final static By enterPassword = By.id("pwdInput");
    private final static By enterCaptcha = By.xpath("//input[@class='login-form-inputs']");
    private final static By startShoppingButton = By.xpath("//input[@class='login-form-inputs login-btn']");
    private final static By userName = By.xpath("//span[@class='ignore-sentence-case']");
    private final static By search = By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']");
    private final static By searchButton = By.xpath("//span[@class='ic-search']");
    private boolean isTure = false;

    public AjioHomePageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    private boolean isCorrectUserSignedIn(String firstName) {
        String actualUserName = driver.waitTillElementIsPresent(userName).getText();
        System.out.println("actualUserName: "+actualUserName);
        if(actualUserName.equalsIgnoreCase(firstName)){
            return isTure = true;
        }
        return isTure;
    }

    @Override
    public boolean signIn(String username, String password, String firstName) {
        driver.waitForClickabilityOf(closePopUp).click();
        driver.waitForClickabilityOf(signInButton).click();
        visually.takeScreenshot(SCREEN_NAME, "Clicked SignIn");
        driver.waitForClickabilityOf(enterUsername).sendKeys(username);
        visually.takeScreenshot(SCREEN_NAME, "Username Entered");
        driver.waitForClickabilityOf(continueButton).click();
        visually.takeScreenshot(SCREEN_NAME, "Continued after entering username");
        driver.waitForClickabilityOf(loginWithPassword).click();
        visually.takeScreenshot(SCREEN_NAME, "Clicked on Continued with Password");
        driver.waitForClickabilityOf(enterPassword).sendKeys(password);
        driver.waitForClickabilityOf(enterCaptcha).click();
        waitFor(5);
        visually.takeScreenshot(SCREEN_NAME, "Password and Captcha Entered");
        driver.waitForClickabilityOf(startShoppingButton).click();
        visually.takeScreenshot(SCREEN_NAME, "Continued shopping");
        waitFor(5);
        return isCorrectUserSignedIn(firstName);
    }

    @Override
    public ProductListPageScreen searchProduct(String product) {
        LOGGER.info(String.format("Search product: ", product));
        driver.waitForClickabilityOf(search).sendKeys(product);
        visually.takeScreenshot(SCREEN_NAME, "product name entered");
        driver.waitForClickabilityOf(searchButton).click();
        visually.takeScreenshot(SCREEN_NAME, "After Clicking search");
        waitFor(5);
        return ProductListPageScreen.get();
    }
}
