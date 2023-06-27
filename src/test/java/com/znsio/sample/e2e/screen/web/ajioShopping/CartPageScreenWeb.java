package com.znsio.sample.e2e.screen.web.ajioShopping;

import com.znsio.sample.e2e.screen.ajioShopping.CartPageScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.znsio.teswiz.tools.Wait.waitFor;

public class CartPageScreenWeb extends CartPageScreen {
    private static final String SCREEN_NAME = CartPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final Driver driver;
    private final Visual visually;
    private static final By applyCoupon = By.xpath("//div[@class='voucher-list-item']//child::input");
    private static final By applyButton = By.xpath("//div[@class='input-box-div']//child::button");
    private static final By proceedToShippingButton = By.xpath("//div[@class='button-wrapper cart-fixed-button']//child::button");
    private static final By estimatedDeliveryDate = By.xpath("//span[@class='edd-items-list__header__text__date']");
    private static final By deleteCoupon = By.xpath("//span[@class='ic-delete navigation-icon']");
    private static final By goBackToBag = By.xpath("//span[@class='icons ic-cart']");
    private static final By deleteItem = By.xpath("//div[text()='Delete']");
    private static final By deleteButton = By.xpath("//div[text()='DELETE']");

    public CartPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String applyCouponAndGetETA() {
        List<WebElement> couponList = driver.findElements(applyCoupon);
        LOGGER.info(String.format("Coupon Selected: ", couponList.get(0).getText()));
        couponList.get(0).click();
        visually.takeScreenshot(SCREEN_NAME, "Coupon applied");
        waitFor(5);
        driver.waitForClickabilityOf(applyButton).click();
        waitFor(5);
        driver.waitForClickabilityOf(proceedToShippingButton).click();
        visually.takeScreenshot(SCREEN_NAME, "On ETA Page");
        String estimatedDate = driver.waitTillElementIsPresent(estimatedDeliveryDate).getText();
        LOGGER.info(String.format("Estimated Delivery Date: ", estimatedDate));
        driver.waitForClickabilityOf(goBackToBag).click();
        driver.waitForClickabilityOf(deleteCoupon).click();
        waitFor(5);
        driver.waitForClickabilityOf(deleteItem).click();
        driver.waitForClickabilityOf(deleteButton).click();
        visually.takeScreenshot(SCREEN_NAME, "Deleted Coupon and item from Cart");
        return estimatedDate;
    }
}
