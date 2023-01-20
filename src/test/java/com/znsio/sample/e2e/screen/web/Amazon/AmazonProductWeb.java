package com.znsio.sample.e2e.screen.web.Amazon;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.AmazonProduct;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonProductWeb extends AmazonProduct {

    private final Driver driver;
    private final WebDriver innerDriver;
    private final Visual visually;
    private static final String SCREEN_NAME = AmazonSearchWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final By byAddToCartId = By.id("add-to-cart-button");
    private static final By byViewCartXpath =By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']");
    private static final By byProductInCartXpath=By.xpath("//span[contains(text(),'Apple iPhone 13') and @aria-hidden='true']");
    private static  final By byShoppingCartId =By.id("sc-active-cart");
    private static  final By byCartId =By.id("attach-desktop-sideSheet");

    public AmazonProductWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        visually.checkWindow(SCREEN_NAME, "Product Detailed page");
    }
    @Override
    public AmazonProductWeb verifyAddedProductToCart()
    {
        visually.checkWindow(SCREEN_NAME, "Cart side screen");
        driver.switchToNextTab();
        WebElement addCartElement=driver.waitTillElementIsPresent(byAddToCartId);
        addCartElement.click();
        driver.waitTillElementIsVisible(byCartId);
        LOGGER.info("Added to cart is displayed");
        return this;
    }

    public boolean viewCart()
    {
        LOGGER.info("Navigating from view cart");
        try {
            WebElement viewCartElement = driver.waitForClickabilityOf(byViewCartXpath);
            JavascriptExecutor js = (JavascriptExecutor) innerDriver;
            js.executeScript("arguments[0].click()", viewCartElement);
            driver.waitTillElementIsPresent(byShoppingCartId);
            visually.checkWindow(SCREEN_NAME, "Cart navigation");
            return true;
        }
        catch (Exception e)
        {
            LOGGER.info("exception in navigating from cart");
        }
        return false;
    }

    @Override
    public boolean verifyProductInCart() {
        visually.checkWindow(SCREEN_NAME, " Shopping cart items displayed");
        try {
            driver.waitTillElementIsPresent(byProductInCartXpath);
            LOGGER.info("verifying the searched product in shopping cart");
            return true;
        }
        catch (Exception e)
        {
            LOGGER.info("Exception in verification of cart");
        }

        return false;
    }



}
